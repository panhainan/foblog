package studio.baxia.fo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IArticleService;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/blog")
@Controller("articleController")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;
    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig){
        PageInfoResult<Article> list = iArticleService.articleGetAllBy(1, CommonConstant.ACTICLE_STATUS_BLOG, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }
    
    @ResponseBody
    @RequestMapping(value = "/article/id/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("id")Integer articleId){
        ArticleVo article = iArticleService.articleVoGetById(articleId,1);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
    @ResponseBody
    @RequestMapping(value = "/article/{title}",method = RequestMethod.GET)
    public CommonResult getByTitle(@PathVariable("title")String articleTitle){
        ArticleVo article = iArticleService.articleVoGetByTitle(articleTitle, 1);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
    @ResponseBody
    @RequestMapping(value = "/category/{name}",method = RequestMethod.GET)
    public CommonResult listArticle(@PathVariable("name")String name){
    	List<ArticleVo> listArticles = iArticleService.articleGetAllByCategoryName(1,name);// new ArrayList<Category>();
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listArticles);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public CommonResult listCategorys(){
    	List<CategoryVo> listCategorys = iArticleService.categoryGetAllVoBy(1,CommonConstant.ACTICLE_STATUS_BLOG);// new ArrayList<Category>();
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public CommonResult listTags(){
    	List<TagVo> listCategorys = iArticleService.tagGetAllVoBy(1);// new ArrayList<Category>();
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
}
