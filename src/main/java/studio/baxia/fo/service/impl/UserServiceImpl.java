package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.baxia.fo.dao.IAuthorsDao;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.util.CookieUtil;
import studio.baxia.fo.util.JCryptionUtil;
import studio.baxia.fo.util.MDUtil;
import studio.baxia.fo.util.TokenManagerUtil;
import studio.baxia.fo.vo.AuthorVo;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FirePan on 2016/10/11.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private TokenManagerUtil tokenManager;
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
	public Map<String,Object> generateKeypair(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JCryptionUtil jCryption = new JCryptionUtil();
			KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
			if (keys == null) {
//                jCryption=null;
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
			throw new RuntimeException("服务器异常，获取密钥失败！",e);
		}
		return map;
	}
	@Override
	public String signInCheck(AuthorVo authorVo, HttpServletRequest request){
		KeyPair keys = (KeyPair) request.getSession().getAttribute("keys");
        String token =null;
		if (keys == null) {
			return null;
		}
		String account;
		try {
			account = URLDecoder
					.decode(JCryptionUtil.decrypt(authorVo.getAccount(), keys),
							"utf-8");
			Authors au = iAuthorsDao.selectByAccount(account);
			if (au == null) {
				return null;
			}
			String password = URLDecoder.decode(
					JCryptionUtil.decrypt(authorVo.getPassword(), keys),
					"utf-8");
            String cryptPwd = MDUtil.encodeMD5ToStr(account + password);
            if(cryptPwd==null||!cryptPwd.equals(au.getPassword())){
                return null;
            }
            token = tokenManager.createToken(au.getAccount());
            System.out.println("用户'" + au.getAccount() + "'生成的token:" + token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器异常，解码失败！",e);
		}
        return token;
	}
    @Override
    public void signOut(HttpServletRequest request) {
        tokenManager.deleteToken(getAuthorInSession(request));
    }

    private String getAuthorInSession(HttpServletRequest request){
        String token = request.getHeader(CookieUtil.DEFAULT_TOKEN_NAME);
        return tokenManager.getUserName(token);
    }

    @Override
	public Authors getInfo(HttpServletRequest request) {
		String account = getAuthorInSession(request);
		if(account ==null){
			return null;
		}
		Authors author = iAuthorsDao.selectByAccount(account);
		author.setAccount(null);
		author.setId(0);
		author.setPassword(null);
		return author;
	}
	@Override
	public Boolean updateInfo(HttpServletRequest request, Authors info) {
		String account = getAuthorInSession(request);
		if(account ==null){
			return false;
		}
        Authors author = iAuthorsDao.selectByAccount(account);
        if(author==null){
            return false;
        }
        info.setId(author.getId());
		info.setAccount(account);
		int result = iAuthorsDao.update(info);
		if(result<=0){
			return false;
		}
		return true;
	}

    @Override
    public Authors getAuthor(int authorId) {
        return iAuthorsDao.selectById(authorId);
    }


	

	

}
