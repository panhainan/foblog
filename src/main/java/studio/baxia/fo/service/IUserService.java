package studio.baxia.fo.service;

import studio.baxia.fo.pojo.Authors;

import java.util.List;

/**
 * Created by FirePan on 2016/10/11.
 */
public interface IUserService {
    /**
     * 添加作者
     *
     * @param authors 作者信息(account,password,userStatus)
     * @return id 作者id
     */
    Integer authorsAdd(Authors authors);

    /**
     * 作者修改账户密码（待实现）
     * @param authorsId 作者id
     * @param authorsAccount 作者账户名
     * @param authorsPassword 作者旧密码
     * @param authorsNewPassword 作者新密码
     * @return Boolean
     */
    Boolean authorsEditPassword(Integer authorsId,String authorsAccount,String authorsPassword,String authorsNewPassword);

    /**
     * 作者修改基本信息（待实现）
     * @param authors 作者基本信息(penName,email,profile,otherInfo)
     * @return
     */
    Boolean authorsEditBaseInfo(Authors authors);

    /**
     * 启用或者禁用作者账户
     *
     * @param authorsId  作者id
     * @param userStatus 作者账户状态
     * @return Boolean 操作结果
     */
    Boolean authorsEditStatus(Integer authorsId, Integer userStatus);

    /**
     * 通过作者id获取作者信息
     *
     * @param authorsId 作者id
     * @return Authors 作者信息
     */
    Authors authorsGetById(Integer authorsId);

    /**
     * 通过作者账户名获取作者信息
     *
     * @param authorsAccount 作者账号
     * @return Authors 作者信息
     */
    Authors authorsGetByAccount(String authorsAccount);

    /**
     * 通过作者邮箱获取作者信息
     *
     * @param authorsEmail 作者邮箱
     * @return Authors 作者信息
     */
    Authors authorsGetByEmail(String authorsEmail);

    /**
     * 分页获取作者列表(可选择禁用或者启用的用户)（待实现）
     * @param pageIndex 当前位置
     * @param pageSize 每页数量
     * @param authorsUserStatus 作者的用户状态（0-禁用，1-启用，-1-所有（数据库中没有-1的表示法））
     * @return List<Authors>
     */
    List<Authors> authorsGetList(Integer pageIndex ,Integer pageSize,Integer authorsUserStatus);

}
