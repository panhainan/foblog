package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.service.IBArticleService;
import studio.baxia.fo.service.IMessageService;
import studio.baxia.fo.vo.ArchiveVo;
import studio.baxia.fo.vo.ArticleMessageVo;
import studio.baxia.fo.vo.ArticleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/12/4.
 */
@RequestMapping(value = "")
@Controller
public class BArticleController {
    @Autowired
    private IBArticleService articleService;
    @Autowired
    private IMessageService messageService;
    @ResponseBody
    @RequestMapping(value = "/blog/article",method = RequestMethod.POST)
    public CommonResult list(PageConfig pageConfig){
        String message=null;
        boolean isSuccess = true;
        PageInfoResult<Article> list = null;
        CommonResult commonResult;
        try{
             list = articleService.getAllBy(CommonConstant.ACTICLE_STATUS_BLOG, pageConfig);
        }catch (Exception e){
            isSuccess = false;
            message = "服务器异常，获取文章失败！";
        }finally {
            if(!isSuccess){
                commonResult= new CommonResult(CommonConstant.FAIL_CODE, message);
            }else{
                commonResult= new CommonResult(CommonConstant.SUCCESS_CODE, message,list);
            }
        }
        return commonResult;
    }
    @ResponseBody
    @RequestMapping(value = "/blog/article/{articleId}/messages", method = RequestMethod.GET)
    public CommonResult list(@PathVariable int articleId) {
        String message=null;
        boolean isSuccess = true;
        List<ArticleMessageVo> list = null;
        CommonResult commonResult=null;
        try{
            list = messageService.list(articleId);
        }catch (Exception e){
            isSuccess = false;
            message = "服务器异常，获取留言失败！";
        }finally {
            if(!isSuccess){
                commonResult= new CommonResult(CommonConstant.FAIL_CODE, message);
            }else{
                commonResult= new CommonResult(CommonConstant.SUCCESS_CODE, message,list);
            }
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/blog/article/id/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("id")Integer articleId){
        ArticleVo article = articleService.getVoById(articleId);
        String msg = null;
        CommonResult commonResult=null;
        if(article==null){
            msg="抱歉，你要查看的文章不存在！";
            commonResult = new CommonResult(CommonConstant.FAIL_CODE,msg);
        }else{
            commonResult =  new CommonResult(CommonConstant.SUCCESS_CODE,msg,article);
        }
        return commonResult;
    }
    @ResponseBody
    @RequestMapping(value = "/blog/article/{code:.+}",method = RequestMethod.GET)
    public CommonResult getByTitle(@PathVariable("code")String articleCode){
        Map<String,Object> map = articleService.getVoByCode(articleCode, CommonConstant.ACTICLE_STATUS_BLOG);
        String msg = null;
        CommonResult commonResult=null;
        if(map==null){
            msg="抱歉，你要查看的文章不存在！";
            commonResult = new CommonResult(CommonConstant.FAIL_CODE,msg);
        }else{
            commonResult =  new CommonResult(CommonConstant.SUCCESS_CODE,msg,map);
        }
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/blog/archive",method = RequestMethod.GET)
    public CommonResult listArchives(){
        List<ArchiveVo> list = articleService.getAllArchiveVo(CommonConstant.ACTICLE_STATUS_BLOG, CommonConstant.ARCHIVE_TYPE_YEAR, CommonConstant.ARCHIVE_TYPE_YEAR_MONTH);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }
    @ResponseBody
    @RequestMapping(value = "/blog/archive/{name}",method = RequestMethod.GET)
    public CommonResult listArchiveArticles(@PathVariable String name){
        List<Article> list = articleService.getAllArchiveArticles(name,CommonConstant.ACTICLE_STATUS_BLOG,CommonConstant.ARCHIVE_TYPE_YEAR_MONTH);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",list);
    }










/*管理url开始*/

    @ResponseBody
    @RequestMapping(value = "/manage/article", method = RequestMethod.POST)
    public CommonResult listArticles(PageConfig pageConfig, @RequestParam(required = false) Integer articleStatus) {
//        logger.info("参数->pageConfig:" + pageConfig + ",articleStatus:" + articleStatus);
        PageInfoResult<ArticleVo> articlesWithPages = articleService.getAllManageBy(articleStatus, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE, null, articlesWithPages);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/{id}", method = RequestMethod.GET)
    public CommonResult getArticle(@PathVariable("id") Integer articleId) {
        ArticleVo article = articleService.getVoById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", article);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteArticle(@PathVariable("id") Integer articleId) {
        Boolean result = articleService.deleteById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/draft2article", method = RequestMethod.PUT)
    public CommonResult draftToArticle(@RequestBody ArticleVo article) {
        int resultData = 0;
        StringBuffer message = new StringBuffer();
        if (article.getId() == null || article.getId() < 0) {
            message.append("文章不存在.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        }
        if (article.getOnlyChangeStatus() == null
                || article.getOnlyChangeStatus() == false) {
            message.append("不合法的操作.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        } else {
            if (article.getStatus() != CommonConstant.ACTICLE_STATUS_BLOG) {
                resultData = articleService.edit(article);
            } else {
                Article articleTmp = articleService.getById(article.getId());
                if (articleTmp.getContent() == null || articleTmp.getContent().trim().equals("")) {
                    message.append("内容不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (articleTmp.getCategoryIds() == null || articleTmp.getCategoryIds() <= 0) {
                    message.append("类别不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (articleTmp.getSummary() == null || articleTmp.getSummary().trim().equals("")) {
                    message.append("摘要不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else {
                    resultData = articleService.edit(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/update", method = RequestMethod.PUT)
    public CommonResult updateArticle(@RequestBody ArticleVo article) {
        int resultData = 0;
        StringBuffer message = new StringBuffer();
        if (article.getId() == null || article.getId() < 0) {
            message.append("文章不存在.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        }
        if (article.getTitle() == null || article.getTitle().trim().equals("")) {
            message.append("标题不能为空.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        } else {
            if (article.getStatus() == CommonConstant.ACTICLE_STATUS_DRAFT) {
                //草稿
                resultData = articleService.edit(article);
            } else {
                if (article.getContent() == null || article.getContent().trim().equals("")) {
                    message.append("内容不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (article.getCategoryIds() == null || article.getCategoryIds() <= 0) {
                    message.append("类别不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (article.getSummary() == null || article.getSummary().trim().equals("")) {
                    message.append("摘要不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else {
                    resultData = articleService.edit(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, "", resultData);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/save", method = RequestMethod.POST)
    public CommonResult addArticle(@RequestBody ArticleVo article) {
        int resultData = 0;
        StringBuffer message = new StringBuffer();
        if (article.getTitle() == null || article.getTitle().trim().equals("")) {
            message.append("标题不能为空.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        } else {
            if (article.getStatus() == CommonConstant.ACTICLE_STATUS_DRAFT) {
                //草稿
                resultData = articleService.add(article);
            } else {
                if (article.getContent() == null || article.getContent().trim().equals("")) {
                    message.append("内容不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (article.getCategoryIds() == null || article.getCategoryIds() <= 0) {
                    message.append("类别不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else if (article.getSummary() == null || article.getSummary().trim().equals("")) {
                    message.append("摘要不能为空.");
                    return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
                } else {
                    resultData = articleService.add(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, "", resultData);
        }
    }
}
