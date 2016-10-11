package studio.baxia.fo.service;

import studio.baxia.fo.pojo.Authors;

/**
 * Created by FirePan on 2016/10/11.
 */
public interface IUserService {
    /**
     * 添加作者
     *
     * @param authors
     * @return id
     */
    Integer addAuthors(Authors authors);
}
