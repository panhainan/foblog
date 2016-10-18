package studio.baxia.fo.controller;

import com.sun.tools.internal.jxc.apt.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.Constant;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IArticleService;
import studio.baxia.fo.service.IUserService;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/{username}/article",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Controller("articleController")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;
    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public CommonResult get(@PathVariable("username")String username,@PathVariable("id")Integer articleId){
        Authors authors = iUserService.authorsGetByAccount(username);
        if(authors==null){
            return new CommonResult(Constant.FAIL_CODE,Constant.USER_IS_NO_EXIST);
        }
        Article article = iArticleService.articleGetById(articleId,authors.getId());
        String msg = "hello, "+username+",operate success.";
        return new CommonResult(Constant.SUCCESS_CODE,msg,article);
    }
    @ResponseBody
    @RequestMapping(value = "/{title}",method = RequestMethod.GET)
    public CommonResult getByTitle(@PathVariable("username")String username,@PathVariable("title")String articleTitle){
        Authors authors = iUserService.authorsGetByAccount(username);
        if(authors==null){
            return new CommonResult(Constant.FAIL_CODE,Constant.USER_IS_NO_EXIST);
        }
        Article article = iArticleService.articleGetByTitle(articleTitle, authors.getId());
        String msg = "hello, "+username+",operate success.";
        return new CommonResult(Constant.SUCCESS_CODE,msg,article);
    }
}
