package com.example.community.mapper;

import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    long countByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int deleteByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int insert(Question row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int insertSelective(Question row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    List<Question> selectByExampleWithRowbounds(QuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    List<Question> selectByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int updateByExampleSelective(@Param("row") Question row, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int updateByExample(@Param("row") Question row, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int updateByPrimaryKeySelective(Question row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated Thu Aug 03 19:47:37 CST 2023
     */
    int updateByPrimaryKey(Question row);
}