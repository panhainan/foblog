package studio.baxia.fo.dao;

import studio.baxia.fo.pojo.Guest;

public interface IGuestDao {
    /**
     *
     * 通过主键删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * 新增
     */
    int insert(Guest record);

    /**
     *
     * 新增具体字段
     */
    int insertSelective(Guest record);

    /**
     *
     * 主键查询
     */
    Guest selectByPrimaryKey(Integer id);

    /**
     *
     * 根据某些条件更新
     */
    int updateByPrimaryKeySelective(Guest record);

    /**
     *
     * 主键更新
     */
    int updateByPrimaryKey(Guest record);
}