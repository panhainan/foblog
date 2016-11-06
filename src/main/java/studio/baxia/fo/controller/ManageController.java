package studio.baxia.fo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import studio.baxia.fo.vo.AuthorVo;

@Controller("manageController")
@RequestMapping(value = "/manage")
public class ManageController {
	@Autowired
	private IUserService userService;

	/*
	 * @RequestMapping(value = "", method = { RequestMethod.GET }) public String
	 * manage() { System.out.println("manage/signin"); return "manage/index"; }
	 */

	@ResponseBody
	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	public CommonResult signin(@RequestBody AuthorVo authorVo,
			HttpServletRequest request) {
		System.out.println("/signin->参数：" + authorVo);
		Boolean result = false;
		String message = null;
		CommonResult commonResult;
		try {
			result = userService.signInCheck(authorVo, request);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			if (result) {
				commonResult = new CommonResult(CommonConstant.SUCCESS_CODE,
						"登录成功", authorVo);
			} else {
				commonResult = new CommonResult(CommonConstant.FAIL_CODE,
						"登录失败:" + message, null);
			}
		}
		return commonResult;
	}
	@ResponseBody
	@RequestMapping(value = "/author" ,method={RequestMethod.GET})
	public CommonResult getInfo(HttpServletRequest request) {
		Authors author = null;
		author = userService.getInfo(request);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "",
				author);
	}
	@ResponseBody
	@RequestMapping(value = "/author" ,method={RequestMethod.PUT})
	public CommonResult updateInfo(HttpServletRequest request,@RequestBody Authors info){
		Boolean result = userService.updateInfo(request,info);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "",
				result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getKeys")
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
