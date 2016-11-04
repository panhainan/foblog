package studio.baxia.fo.controller;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.util.JCryption;
import studio.baxia.fo.vo.AuthorVo;

@Controller("manageController")
@RequestMapping(value = "/manage", method = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class ManageController {

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String manage() {
		System.out.println("manage/signin");
		return "manage/index";
	}

	@ResponseBody
	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	public CommonResult author(@RequestBody AuthorVo authorVo) {
		System.out.println("/signin->参数：" + authorVo);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "登录成功", authorVo);
	}

	@ResponseBody
	@RequestMapping(value = "/getKeys")
	public CommonResult generateKeypair(HttpServletRequest request) {
		JCryption jCryption = new JCryption();
		KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
		if (keys == null) {
			keys = jCryption.generateKeypair(512);
			request.getSession().setAttribute("keys", keys);
		}

		String e = JCryption.getPublicKeyExponent(keys);
		String n = JCryption.getPublicKeyModulus(keys);
		String md = String.valueOf(JCryption.getMaxDigits(512));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("e", e);
		map.put("n", n);
		map.put("md", md);
		return new CommonResult(CommonConstant.SUCCESS_CODE, "", map);
	}

}
