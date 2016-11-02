package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.pojo.Guest;

import java.util.List;
import java.util.Map;

/*@Repository*/
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

    /**
     *
     *通过给定条件查询
     */
    Guest queryOneByCondition(Map<String, Object> condition);

    /**
     *
     *查询所有
     */
    List<Guest> queryAll();
}