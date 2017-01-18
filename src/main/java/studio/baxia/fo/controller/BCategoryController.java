package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.service.IBArticleService;
import studio.baxia.fo.service.IBCategoryService;
import studio.baxia.fo.vo.CategoryVo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/12/4.
 */
@RequestMapping(value = "")
@Controller
public class BCategoryController {
    @Autowired
    private IBCategoryService categoryService;
    @Autowired
    private IBArticleService articleService;
    @ResponseBody
    @RequestMapping(value = "/blog/category/{code:.+}",method = RequestMethod.GET)
    public CommonResult listCategoryArticle(@PathVariable("code")String code){
        Map<String,Object> resultData = articleService.getAllByCategoryCode(code, CommonConstant.CATEGORY_ALL);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",resultData);
    }
    @ResponseBody
    @RequestMapping(value = "/blog/category",method = RequestMethod.GET)
    public CommonResult listCategorys(){
        List<CategoryVo> listCategorys = categoryService.getAllVoBy(CommonConstant.ACTICLE_STATUS_BLOG,CommonConstant.CATEGORY_SHOW);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",listCategorys);
    }




    /*管理url开始*/

    @ResponseBody
    @RequestMapping(value = "/manage/category", method = RequestMethod.GET)
    public CommonResult listMCategorys() {
        List<CategoryVo> listCategorys = categoryService.getAllVoBy(null,CommonConstant.CATEGORY_ALL);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listCategorys);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/category/{categoryId}", method = RequestMethod.GET)
    public CommonResult listCategoryArticles(@PathVariable("categoryId") Integer categoryId) {
        List<Article> listCategoryArticles = articleService.getAllByCategoryId(categoryId, null);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", listCategoryArticles);
    }

    @ResponseBody
    @RequestMapping(value = "/manage/category", method = RequestMethod.POST)
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
                result = categoryService.add(category);
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

    @ResponseBody
    @RequestMapping(value = "/manage/category", method = RequestMethod.PUT)
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
                result = categoryService.edit(category);
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

    @ResponseBody
    @RequestMapping(value = "/manage/category/{categoryId}", method = RequestMethod.DELETE)
    public CommonResult deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        //这里由于service层又通过id去数据库查询了一次，要不要考虑直接通过前台传对象进来省去查的那次，但是这样又有悖springmvc的rest设计
        Boolean result = categoryService.deleteById(categoryId);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "", result);
    }
}
