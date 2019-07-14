package team.exm.book.mapper;

import org.apache.ibatis.annotations.Param;
import team.exm.book.entity.StuBook;
import team.exm.book.web.request.StuBookVO;

import java.util.List;

public interface StuBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StuBook record);

    int insertSelective(StuBook record);

    StuBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuBook record);

    int updateByPrimaryKey(StuBook record);

    List<String> selectByUserAndBook(StuBookVO record);

    List<StuBook> selectByUserId(StuBookVO stuBookVO);

    int selectByUserIdNum(StuBookVO stuBookVO);
}