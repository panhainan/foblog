package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Recommend;

/**
 * Created by Pan on 2016/12/20.
 */
public interface IRecommendService {

    long add(Recommend recommendVo);
    long edit(Recommend recommendVo);
    boolean remove(long id);
    PageInfoResult<Recommend> list(PageConfig pageConfig,Recommend recommend);
    boolean hits(Long id);

    Recommend get(long id);
}
