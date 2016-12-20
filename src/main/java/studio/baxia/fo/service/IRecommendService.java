package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Recommend;
import studio.baxia.fo.vo.RecommendVo;

/**
 * Created by Pan on 2016/12/20.
 */
public interface IRecommendService {

    long add(RecommendVo recommendVo);
    long edit(RecommendVo recommendVo);
    boolean remove(Long id);
    PageInfoResult<Recommend> list(PageConfig pageConfig,Recommend recommend);
    boolean hits(Long id);
}
