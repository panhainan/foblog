package studio.baxia.fo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IArticleService;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.vo.ArticleVo;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/manage/article")
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
        PageInfoResult<ArticleVo> articlesWithPages = iArticleService.articleGetAllManageBy(1, articleStatus, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,articlesWithPages);
    }
    
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("id")Integer articleId){
        ArticleVo article = iArticleService.articleVoGetById(articleId,1);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CommonResult delete(@PathVariable("id")Integer articleId){
        Boolean result = iArticleService.articleDeleteById(articleId,1);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public CommonResult listCategorys(){
    	List<Category> listCategorys = iArticleService.categoryGetAllBy(1);// new ArrayList<Category>();
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public CommonResult listTags(){
    	List<Tag> listCategorys = iArticleService.tagGetAllBy(1);// new ArrayList<Category>();
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    public CommonResult update(@RequestBody ArticleVo article){
    	Integer flagId = iArticleService.articleEdit(article);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",flagId);
    }
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public CommonResult add(@RequestBody ArticleVo article){
    	Integer flagId = iArticleService.articleAdd(article);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",flagId);
    }
}
