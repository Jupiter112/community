package com.example.community.service;

import com.example.community.dto.CommentDTO;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionExpMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExpMapper questionExpMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                // 数据库中不存在该父级评论
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            // 回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(dbQuestion==null){
                // 数据库中不存在该父级问题
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Question record = new Question();
            record.setId(comment.getParentId());
            record.setCommentCount(1);
            questionExpMapper.incComment(record);
        }
    }

    public List<CommentDTO> listByParentId(Long id, CommentTypeEnum type) {
        // 根据parentid查找对应的评论并返回所有评论列表
        CommentExample example = new CommentExample();
        example.createCriteria()
                        .andParentIdEqualTo(id)
                        .andTypeEqualTo(type.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        if(comments.size()==0){
            // 没有评论
            return new ArrayList<>();
        }
        // 使用lambda函数编程获取去重后的评论人，使用map和set
        Set<Long> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        // 获取评论人并转换为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));

        // 转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).toList();

        return commentDTOS;
    }
}
