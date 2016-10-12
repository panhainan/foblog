package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.baxia.fo.dao.IAuthorsDao;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;

import java.util.List;

/**
 * Created by FirePan on 2016/10/11.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IAuthorsDao iAuthorsDao;


    /**
     * 添加作者
     *
     * @param authors 作者信息(account,password,userStatus)
     * @return id 作者id
     */
    @Override
    public Integer authorsAdd(Authors authors) {
        return iAuthorsDao.insert(authors);
    }

    /**
     * 作者修改账户密码
     *
     * @param authorsId          作者id
     * @param authorsAccount     作者账户名
     * @param authorsPassword    作者旧密码
     * @param authorsNewPassword 作者新密码
     * @return Boolean
     */
    @Override
    public Boolean authorsEditPassword(Integer authorsId, String authorsAccount, String authorsPassword, String authorsNewPassword) {
        return null;
    }

    /**
     * 作者修改基本信息
     *
     * @param authors 作者基本信息(penName,email,profile,otherInfo)
     * @return
     */
    @Override
    public Boolean authorsEditBaseInfo(Authors authors) {
        return null;
    }

    /**
     * 启用或者禁用作者账户
     *
     * @param authorsId  作者id
     * @param userStatus 作者账户状态
     * @return Boolean 操作结果
     */
    @Override
    public Boolean authorsEditStatus(Integer authorsId, Integer userStatus) {
        iAuthorsDao.updateAuthorsStatus(authorsId, userStatus);
        return true;
    }


    /**
     * 通过作者id获取作者信息
     *
     * @param authorsId 作者id
     * @return Authors 作者信息
     */
    @Override
    public Authors authorsGetById(Integer authorsId) {
        return iAuthorsDao.selectById(authorsId);
    }


    /**
     * 通过作者账户名获取作者信息
     *
     * @param authorsAccount 作者账号
     * @return Authors 作者信息
     */
    @Override
    public Authors authorsGetByAccount(String authorsAccount) {
        return iAuthorsDao.selectByAccount(authorsAccount);
    }

    /**
     * 通过作者邮箱获取作者信息
     *
     * @param authorsEmail 作者邮箱
     * @return Authors 作者信息
     */
    @Override
    public Authors authorsGetByEmail(String authorsEmail) {
        return iAuthorsDao.selectByEmail(authorsEmail);
    }

    /**
     * 分页获取作者列表(可选择禁用或者启用的用户)（待实现）
     *
     * @param pageIndex         当前位置
     * @param pageSize          每页数量
     * @param authorsUserStatus 作者的用户状态（0-禁用，1-启用，-1-所有（数据库中没有-1的表示法））
     * @return List<Authors>
     */
    @Override
    public List<Authors> authorsGetList(Integer pageIndex, Integer pageSize, Integer authorsUserStatus) {
        return null;
    }


}
