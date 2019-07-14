package team.exm.book.mapper;

import team.exm.book.entity.Press;

public interface PressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Press record);

    int insertSelective(Press record);

    Press selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Press record);

    int updateByPrimaryKey(Press record);
}