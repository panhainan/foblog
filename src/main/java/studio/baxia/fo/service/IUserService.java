package studio.baxia.fo.service;

import studio.baxia.fo.pojo.Authors;

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


}
