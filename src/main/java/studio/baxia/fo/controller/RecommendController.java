package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Recommend;
import studio.baxia.fo.service.IRecommendService;

/**
 * Created by Pan on 2016/12/20.
 */
@Controller
@RequestMapping(value = "/recommend")
public class RecommendController {
    @Autowired
    private IRecommendService iRecommendService;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig){
        PageInfoResult<Recommend> list = iRecommendService.list(pageConfig,null);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,list);
    }

    @ResponseBody
    @RequestMapping(value = "/hits",method = RequestMethod.POST)
    public CommonResult hits(Long id){
        iRecommendService.hits(id);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null);
    }
}
