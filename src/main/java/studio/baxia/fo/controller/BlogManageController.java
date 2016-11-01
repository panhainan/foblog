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
import studio.baxia.fo.service.IBlogService;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/manage")
@Controller("blogManageController")
public class BlogManageController {

	Logger logger = Logger.getLogger("ArticleManageController");
	
    @Autowired
    private IBlogService iArticleService;
    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public CommonResult listArticles(PageConfig pageConfig,@RequestParam(required=false) Integer articleStatus){
    	logger.info("参数->pageConfig:"+pageConfig+",articleStatus:"+articleStatus);
        PageInfoResult<ArticleVo> articlesWithPages = iArticleService.articleGetAllManageBy( articleStatus, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE,null,articlesWithPages);
    }
    
    @ResponseBody
    @RequestMapping(value = "/article/{id}",method = RequestMethod.GET)
    public CommonResult getArticle(@PathVariable("id")Integer articleId){
        ArticleVo article = iArticleService.articleVoGetById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
    @ResponseBody
    @RequestMapping(value = "/article/{id}",method = RequestMethod.DELETE)
    public CommonResult deleteArticle(@PathVariable("id")Integer articleId){
        Boolean result = iArticleService.articleDeleteById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public CommonResult listCategorys(){
    	List<CategoryVo> listCategorys = iArticleService.categoryGetAllVoBy(null);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
    @ResponseBody
    @RequestMapping(value = "/category/{categoryId}",method = RequestMethod.GET)
    public CommonResult listCategoryArticles(@PathVariable("categoryId")Integer categoryId){
    	List<Article> listCategoryArticles = iArticleService.articleGetAllByCategoryId(categoryId,null);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategoryArticles);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public CommonResult addCategory(@RequestBody Category category){
    	Boolean result = iArticleService.categoryAdd(category);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.PUT)
    public CommonResult updateCategory(@RequestBody Category category){
    	Boolean result = iArticleService.categoryEdit(category);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    
    @ResponseBody
    @RequestMapping(value = "/category/{categoryId}",method = RequestMethod.DELETE)
    public CommonResult deleteCategory(@PathVariable("categoryId")Integer categoryId){
    	//这里由于service层又通过id去数据库查询了一次，要不要考虑直接通过前台传对象进来省去查的那次，但是这样又有悖springmvc的rest设计
    	Boolean result = iArticleService.categoryDeleteById(categoryId);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public CommonResult listTags(){
    	List<TagVo> listTags = iArticleService.tagGetAllVoBy(null);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listTags);
    }
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.PUT)
    public CommonResult updateTag(@RequestBody Tag tag){
    	Boolean result = iArticleService.tagEdit(tag);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.POST)
    public CommonResult saveTag(@RequestBody Tag tag){
    	Boolean result = iArticleService.tagAdd(tag);;
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}",method = RequestMethod.GET)
    public CommonResult listTagArticles(@PathVariable("tagId")Integer tagId){
    	List<Article> listTagArticles = iArticleService.articleGetAllByTagId( tagId,null);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listTagArticles);
    }
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}",method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable("tagId") Integer tagId){
    	Boolean result = iArticleService.tagDeleteById(tagId);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}/{articleId}",method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable Integer tagId ,@PathVariable Integer articleId){
    	Boolean result = iArticleService.articleDeleteTag(tagId,articleId);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }
    @ResponseBody
    @RequestMapping(value = "/article/save",method = RequestMethod.PUT)
    public CommonResult updateArticle(@RequestBody ArticleVo article){
    	Integer flagId = iArticleService.articleEdit(article);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",flagId);
    }
    @ResponseBody
    @RequestMapping(value = "/article/save",method = RequestMethod.POST)
    public CommonResult addArticle(@RequestBody ArticleVo article){
    	Integer flagId = iArticleService.articleAdd(article);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",flagId);
    }
}
