package studio.baxia.fo.dao;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.pojo.Authors;

import java.util.List;

/**
 * Created by FirePan on 2016/10/11.
 */
@Repository(value = "iAuthorsDao")
public interface IAuthorsDao {
    List<Authors> list(Integer pageIndex ,Integer pageSize);
    Authors selectById(Integer id);
    void deleteById(Integer id);
    Integer insert(Authors authors);
}
