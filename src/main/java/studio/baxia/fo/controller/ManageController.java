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
import studio.baxia.fo.util.JCryptionUtil;
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
	public CommonResult author(@RequestBody AuthorVo authorVo,HttpServletRequest request) {
		System.out.println("/signin->参数：" + authorVo);
		KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
		
		return new CommonResult(CommonConstant.SUCCESS_CODE, "登录成功", authorVo);
	}

	@ResponseBody
	@RequestMapping(value = "/getKeys")
	public Map<String, Object> generateKeypair(HttpServletRequest request) {
		JCryptionUtil jCryption;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jCryption = new JCryptionUtil();
			KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
			if (keys == null) {
				keys = jCryption.generateKeypair(512);
				request.getSession().setAttribute("keys", keys);
			}
			
			String e = JCryptionUtil.getPublicKeyExponent(keys);
			String n = JCryptionUtil.getPublicKeyModulus(keys);
			String md = String.valueOf(JCryptionUtil.getMaxDigits(512));
			map.put("e", e);
			map.put("n", n);
			map.put("maxdigits", md);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
