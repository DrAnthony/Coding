package team.exm.book.mapper;

import team.exm.book.entity.Book;
import team.exm.book.web.request.BookVO;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookVO record);

    int insertSelective(BookVO record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookVO record);

    int updateByPrimaryKey(BookVO record);

    List<Book> selectByKeywords(BookVO book);

    List<Book> selectSeletive(BookVO book);

    List<Book> selectAll(BookVO bookVO);

    int selectByKeywordsNum(BookVO bookVO);

    int selectAllNum(BookVO bookVO);

    int selectSeletiveNum(BookVO bookVO);

    List<Book> selectVerify(BookVO bookVO);

    Integer selectVerifyNum(BookVO bookVO);
}