package studio.baxia.fo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.vo.AuthorVo;

@Controller("manageController")
@RequestMapping(value="/manage",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ManageController {

	@RequestMapping(value="",method={RequestMethod.GET})
	public String manage(){
		System.out.println("manage/signin");
		return "manage/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/signin",method={RequestMethod.POST})
	public CommonResult author(@RequestBody AuthorVo authorVo){
		System.out.println("/signin->参数："+authorVo);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "登录成功",authorVo);
	}
	
	
}
