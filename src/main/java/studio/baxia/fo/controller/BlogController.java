package studio.baxia.fo.controller;

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
import studio.baxia.fo.service.IBlogService;
import studio.baxia.fo.service.IMessageService;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.vo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/blog")
@Controller("blogController")
public class BlogController {

    @Autowired
    private IBlogService iArticleService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IMessageService messageService;

    @ResponseBody
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig){
        PageInfoResult<Article> list = iArticleService.articleGetAllBy(CommonConstant.ACTICLE_STATUS_BLOG, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }
    @ResponseBody
    @RequestMapping(value = "/article/{articleId}/messages", method = RequestMethod.GET)
    public CommonResult list(@PathVariable int articleId) {
        String message=null;
        boolean isSuccess = true;
        List<ArticleMessageVo> list = null;
        try{
            list = messageService.list(articleId);
        }catch (Exception e){
            isSuccess = false;
            message = "服务器异常，获取留言失败！";
        }finally {
            if(!isSuccess){
                return new CommonResult(CommonConstant.FAIL_CODE, message,null);
            }else{
                return new CommonResult(CommonConstant.SUCCESS_CODE, message,list);
            }
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/article/id/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("id")Integer articleId){
        ArticleVo article = iArticleService.articleVoGetById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",article);
    }
    @ResponseBody
    @RequestMapping(value = "/article/{title:.+}",method = RequestMethod.GET)
    public CommonResult getByTitle(@PathVariable("title")String articleTitle){
        Map<String,Object> map = iArticleService.articleVoGetByTitle(articleTitle);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",map);
    }
    @ResponseBody
    @RequestMapping(value = "/category/{name:.+}",method = RequestMethod.GET)
    public CommonResult listCategoryArticle(@PathVariable("name")String name){
    	Map<String,Object> resultData = iArticleService.articleGetAllByCategoryName(name);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",resultData);
    }
    @ResponseBody
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public CommonResult listCategorys(){
    	List<CategoryVo> listCategorys = iArticleService.categoryGetAllVoBy(CommonConstant.ACTICLE_STATUS_BLOG);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }
    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public CommonResult listTags(){
    	List<TagVo> listTags = iArticleService.tagGetAllVoBy(CommonConstant.ACTICLE_STATUS_BLOG);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listTags);
    }
    @ResponseBody
    @RequestMapping(value = "/tag/{name:.+}",method = RequestMethod.GET)
    public CommonResult listTagArticle(@PathVariable("name")String name){
    	List<ArticleVo> listArticles = iArticleService.articleGetAllByTagName(name);
    	return new CommonResult(CommonConstant.SUCCESS_CODE,"",listArticles);
    }
    @ResponseBody
    @RequestMapping(value = "/archive",method = RequestMethod.GET)
    public CommonResult listArchives(){
    	List<ArchiveVo> list = iArticleService.archiveGetAllVo(CommonConstant.ACTICLE_STATUS_BLOG,CommonConstant.ARCHIVE_TYPE_YEAR,CommonConstant.ARCHIVE_TYPE_YEAR_MONTH);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }
    @ResponseBody
    @RequestMapping(value = "/archive/{name}",method = RequestMethod.GET)
    public CommonResult listArchiveArticles(@PathVariable String name){
    	List<Article> list = iArticleService.archiveGetAllArticles(name,CommonConstant.ACTICLE_STATUS_BLOG,CommonConstant.ARCHIVE_TYPE_YEAR_MONTH);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }
}
