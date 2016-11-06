package studio.baxia.fo.service.impl;

import java.net.URLDecoder;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.dao.IAuthorsDao;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.util.JCryptionUtil;
import studio.baxia.fo.vo.AuthorSessionVo;
import studio.baxia.fo.vo.AuthorVo;

/**
 * Created by FirePan on 2016/10/11.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IAuthorsDao iAuthorsDao;

	@Override
	public Integer authorsAdd(Authors authors) {
		return iAuthorsDao.insert(authors);
	}

	@Override
	public Boolean authorsEditPassword(Integer authorsId,
			String authorsAccount, String authorsPassword,
			String authorsNewPassword) {
		return null;
	}

	@Override
	public Boolean authorsEditBaseInfo(Authors authors) {
		return null;
	}

	@Override
	public Boolean authorsEditStatus(Integer authorsId, Integer userStatus) {
		iAuthorsDao.updateAuthorsStatus(authorsId, userStatus);
		return true;
	}

	@Override
	public Authors authorsGetById(Integer authorsId) {
		return iAuthorsDao.selectById(authorsId);
	}

	@Override
	public Authors authorsGetByAccount(String authorsAccount) {
		return iAuthorsDao.selectByAccount(authorsAccount);
	}

	@Override
	public Authors authorsGetByEmail(String authorsEmail) {
		return iAuthorsDao.selectByEmail(authorsEmail);
	}

	@Override
	public List<Authors> authorsGetList(Integer pageIndex, Integer pageSize,
			Integer authorsUserStatus) {
		return null;
	}
	@Override
	public Map<String,Object> generateKeypair(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JCryptionUtil jCryption = new JCryptionUtil();
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
			throw new Exception("服务器异常，获取密钥失败！");
		}
		return map;
	}
	@Override
	public Boolean signInCheck(AuthorVo authorVo, HttpServletRequest request) throws Exception {
		KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
		if (keys == null) {
			return false;
		}
		String account;
		try {
			account = URLDecoder
					.decode(JCryptionUtil.decrypt(authorVo.getAccount(), keys),
							"utf-8");
			Authors au = iAuthorsDao.selectByAccount(account);
			if (au == null) {
				return false;
			}
			
			String password = URLDecoder.decode(
					JCryptionUtil.decrypt(authorVo.getPassword(), keys),
					"utf-8");
			if (!au.getPassword().equals(password)) {
				return false;
			}
			setAuthorInSession(request,new AuthorSessionVo(au.getAccount(),au.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("服务器异常，解码失败！");
		}
		return true;
	}
	@Override
	public Authors getInfo(HttpServletRequest request) {
		AuthorSessionVo authorSessionVo = getAuthorInSession(request);
		if(authorSessionVo ==null){
			return null;
		}
		Authors author = iAuthorsDao.selectByAccount(authorSessionVo.getAccount());
		author.setAccount(null);
		author.setId(0);
		author.setPassword(null);
		return author;
	}
	@Override
	public Boolean updateInfo(HttpServletRequest request, Authors info) {
		AuthorSessionVo authorSessionVo = getAuthorInSession(request);
		if(authorSessionVo ==null){
			return false;
		}
		info.setId(authorSessionVo.getId());
		info.setAccount(authorSessionVo.getAccount());
		int result = iAuthorsDao.update(info);
		if(result<=0){
			return false;
		}
		return true;
	}
	private void setAuthorInSession( HttpServletRequest request, AuthorSessionVo authors) {
		request.getSession(true).setAttribute("authorSessionVo", authors);
		request.getSession(true).setMaxInactiveInterval(CommonConstant.USER_TIME_OUT);
	}
	private AuthorSessionVo getAuthorInSession(HttpServletRequest request){
		return (AuthorSessionVo) request.getSession(true).getAttribute("authorSessionVo");
	}

	

	

}
