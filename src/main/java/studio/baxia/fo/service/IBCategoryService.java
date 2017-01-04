package studio.baxia.fo.service;

import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.vo.CategoryVo;

import java.util.List;

/**
 * Created by Pan on 2016/12/4.
 */
public interface IBCategoryService {
    /**
     * 添加类别
     *
     * @param category
     *            类别（parent_id,name,author_id）
     * @return
     */
    Boolean add(Category category);

    /**
     * 修改类别
     *
     * @param category
     *            类别（parent_id,name）
     * @return
     */
    Boolean edit(Category category);

    /**
     * 通过类别id删除类别
     *
     * @param categoryId
     *            类别id
     * @return
     */
    Boolean deleteById(int categoryId);

    /**
     * 通过作者id获取所有类别
     *
     * @return
     */
    List<Category> getAllBy();

    /**
     * 通过类别id获取类别
     *
     * @param categoryId
     *            类别id
     * @return
     */
    Category getById(int categoryId,boolean status);

    /**
     * 通过类别类别状态和文章状态查询类别
     * @param articleStatus
     * @param status
     * @return
     */
    List<CategoryVo> getAllVoBy(Integer articleStatus,boolean status);
}
