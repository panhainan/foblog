package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.util.ExecuteSecurity;
import studio.baxia.fo.vo.AuthorVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("manageController")
@RequestMapping(value = "")
public class ManageController {
	@Autowired
	private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/author" ,method={RequestMethod.GET})
    public CommonResult about(HttpServletRequest request) {
        Authors author = userService.getAuthor(CommonConstant.AUTHOR_ID);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "",
                author);
    }
	@ResponseBody
	@RequestMapping(value = "/manage/signin", method = { RequestMethod.POST })
	public CommonResult signin(@RequestBody AuthorVo authorVo,
			HttpServletRequest request) {
		System.out.println("/signin->参数：" + authorVo);
		String resultData="";
		String message = null;
        boolean isSuccess = true;
		CommonResult commonResult;
		try {
            resultData = userService.signInCheck(authorVo, request);
		} catch (Exception e) {
			message = e.getMessage();
            isSuccess = false;
		} finally {
            if(isSuccess){
                commonResult = new CommonResult(CommonConstant.SUCCESS_CODE,
                        message, resultData);
            }else{
                commonResult = new CommonResult(CommonConstant.FAIL_CODE,
                        message, resultData);
            }
            return commonResult;
		}
	}
    @ExecuteSecurity
	@ResponseBody
	@RequestMapping(value = "/manage/author" ,method={RequestMethod.GET})
	public CommonResult getInfo(HttpServletRequest request) {
		Authors author = null;
		author = userService.getInfo(request);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "",
				author);
	}
    @ExecuteSecurity
	@ResponseBody
	@RequestMapping(value = "/manage/author" ,method={RequestMethod.PUT})
	public CommonResult updateInfo(HttpServletRequest request,@RequestBody Authors info){
		Boolean result = userService.updateInfo(request,info);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "",
				result);
	}

	@ResponseBody
	@RequestMapping(value = "/manage/getKeys")
	public Map<String, Object> generateKeypair(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			map = userService.generateKeypair(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
