package studio.baxia.fo.controller;

import com.sun.tools.internal.jxc.apt.Const;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IArticleService;
import studio.baxia.fo.service.IUserService;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/manage/article",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Controller("articleManageController")
public class ArticleManageController {

	Logger logger = Logger.getLogger("ArticleManageController");
	
    @Autowired
    private IArticleService iArticleService;
    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig,@RequestParam(required=false) Integer articleStatus){
    	logger.info("参数->pageConfig:"+pageConfig+",articleStatus:"+articleStatus);
        PageInfoResult<Article> articlesWithPages = iArticleService.articleGetAllBy(1, articleStatus, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,articlesWithPages);
    }
    
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("id")Integer articleId){
        Article article = iArticleService.articleGetById(articleId,1);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
}
