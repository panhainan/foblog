package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.pojo.Friendlink;

import java.util.List;

/**
 * Created by Pan on 2016/12/1.
 */
@Repository("iFriendDao")
public interface IFriendDao {

    Friendlink selectById(@Param("id")Integer fId);

    int deleteById(@Param("id")Integer fId);

    int insert(Friendlink friendlink);

    int update(Friendlink friendlink);

    List<Friendlink> selectBy(@Param("pageConfig")PageConfig pageConfig);

    int selectCountBy();

    int updateHits(Friendlink friendlink);
}
