package team.exm.book.mapper;

import team.exm.book.entity.Code;

public interface CodeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Code record);

    int insertSelective(Code record);

    Code selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);

    Code selectByPhone(String phone);

    Integer queryNum(String phone);
}