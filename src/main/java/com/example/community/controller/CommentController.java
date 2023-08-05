package com.example.community.controller;

import com.example.community.dto.CommentCreateDTO;
import com.example.community.dto.CommentDTO;
import com.example.community.dto.ResultDTO;
import com.example.community.enums.CommentTypeEnum;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.model.Comment;
import com.example.community.model.User;
import com.example.community.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ResponseBody
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(
            @RequestBody CommentCreateDTO commentCreateDTO,
            HttpServletRequest request){
        // 发表评论（回复问题 or 回复父级评论）
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
//            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        // 评论为空
        if (commentCreateDTO==null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1L);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id,
                                                HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
//            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        // 二级评论
        List<CommentDTO> comments = commentService.listByParentId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(comments);
    }
}
