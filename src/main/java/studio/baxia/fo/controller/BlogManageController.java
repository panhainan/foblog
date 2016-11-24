package studio.baxia.fo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IBlogService;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.util.ExecuteSecurity;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public CommonResult listArticles(PageConfig pageConfig, @RequestParam(required = false) Integer articleStatus) {
        logger.info("参数->pageConfig:" + pageConfig + ",articleStatus:" + articleStatus);
        PageInfoResult<ArticleVo> articlesWithPages = iArticleService.articleGetAllManageBy(articleStatus, pageConfig);
        return new CommonResult(CommonConstant.SUCCESS_CODE, null, articlesWithPages);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public CommonResult getArticle(@PathVariable("id") Integer articleId) {
        ArticleVo article = iArticleService.articleVoGetById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", article);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteArticle(@PathVariable("id") Integer articleId) {
        Boolean result = iArticleService.articleDeleteById(articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public CommonResult listCategorys() {
        List<CategoryVo> listCategorys = iArticleService.categoryGetAllVoBy(null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listCategorys);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public CommonResult listCategoryArticles(@PathVariable("categoryId") Integer categoryId) {
        List<Article> listCategoryArticles = iArticleService.articleGetAllByCategoryId(categoryId, null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listCategoryArticles);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public CommonResult addCategory(@RequestBody @Valid Category category,
                                    BindingResult resultValidator) {
        if (resultValidator.hasErrors()) {
            List<String> resultMessage = new ArrayList<>();
            for (FieldError fe : resultValidator.getFieldErrors()) {
                resultMessage.add(fe.getDefaultMessage());
            }
            return new CommonResult(CommonConstant.FAIL_CODE, resultMessage.toString());
        } else {
            boolean isSuccess = true;
            String message = null;
            boolean result = false;
            try {
                result = iArticleService.categoryAdd(category);
            } catch (Exception e) {
                isSuccess = false;
                message = e.getMessage();
            } finally {
                if (!isSuccess) {
                    return new CommonResult(CommonConstant.FAIL_CODE, message);
                } else {
                    if (result) {
                        return new CommonResult(CommonConstant.SUCCESS_CODE, CommonConstant.OPERATE_SUCCESS_MESSAGE);
                    } else {
                        //这里待思考处理
                        return new CommonResult(CommonConstant.SUCCESS_CODE, CommonConstant.OPERATE_FAIL_MESSAGE + "(该类别已经存在)");
                    }
                }
            }
        }
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/category", method = RequestMethod.PUT)
    public CommonResult updateCategory(@RequestBody @Valid Category category,
                                       BindingResult resultValidator) {
        if (resultValidator.hasErrors()) {
            List<String> resultMessage = new ArrayList<>();
            for (FieldError fe : resultValidator.getFieldErrors()) {
                resultMessage.add(fe.getDefaultMessage());
            }
            return new CommonResult(CommonConstant.FAIL_CODE, resultMessage.toString());
        } else {
            boolean isSuccess = true;
            String message = null;
            boolean result = false;
            try {
                result = iArticleService.categoryEdit(category);
            } catch (Exception e) {
                isSuccess = false;
                message = e.getMessage();
            } finally {
                if (!isSuccess) {
                    return new CommonResult(CommonConstant.FAIL_CODE, message);
                } else {
                    if (result) {
                        return new CommonResult(CommonConstant.SUCCESS_CODE, CommonConstant.OPERATE_SUCCESS_MESSAGE);
                    } else {
                        return new CommonResult(CommonConstant.SUCCESS_CODE, CommonConstant.OPERATE_FAIL_MESSAGE + "(该类别已经存在)");
                    }
                }
            }
        }

    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    public CommonResult deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        //这里由于service层又通过id去数据库查询了一次，要不要考虑直接通过前台传对象进来省去查的那次，但是这样又有悖springmvc的rest设计
        Boolean result = iArticleService.categoryDeleteById(categoryId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public CommonResult listTags() {
        List<TagVo> listTags = iArticleService.tagGetAllVoBy(null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listTags);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag", method = RequestMethod.PUT)
    public CommonResult updateTag(@RequestBody Tag tag) {
        Boolean result = iArticleService.tagEdit(tag);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public CommonResult saveTag(@RequestBody Tag tag) {
        Boolean result = iArticleService.tagAdd(tag);
        ;
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
    public CommonResult listTagArticles(@PathVariable("tagId") Integer tagId) {
        List<Article> listTagArticles = iArticleService.articleGetAllByTagId(tagId, null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listTagArticles);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable("tagId") Integer tagId) {
        Boolean result = iArticleService.tagDeleteById(tagId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/tag/{tagId}/{articleId}", method = RequestMethod.DELETE)
    public CommonResult deleteTag(@PathVariable Integer tagId, @PathVariable Integer articleId) {
        Boolean result = iArticleService.articleDeleteTag(tagId, articleId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article/draft2article", method = RequestMethod.PUT)
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
                resultData = iArticleService.articleEdit(article);
            } else {
                Article articleTmp = iArticleService.articleGetById(article.getId());
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
                    resultData = iArticleService.articleEdit(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        }
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article/update", method = RequestMethod.PUT)
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
                resultData = iArticleService.articleEdit(article);
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
                    resultData = iArticleService.articleEdit(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, "", resultData);
        }
    }
    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/article/save", method = RequestMethod.POST)
    public CommonResult addArticle(@RequestBody ArticleVo article) {
        int resultData = 0;
        StringBuffer message = new StringBuffer();
        if (article.getTitle() == null || article.getTitle().trim().equals("")) {
            message.append("标题不能为空.");
            return new CommonResult(CommonConstant.SUCCESS_CODE, message.toString(), resultData);
        } else {
            if (article.getStatus() == CommonConstant.ACTICLE_STATUS_DRAFT) {
                //草稿
                resultData = iArticleService.articleAdd(article);
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
                    resultData = iArticleService.articleAdd(article);
                }
            }
            return new CommonResult(CommonConstant.SUCCESS_CODE, "", resultData);
        }
    }
}
