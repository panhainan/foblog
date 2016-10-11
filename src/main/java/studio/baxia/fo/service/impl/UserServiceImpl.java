package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studio.baxia.fo.dao.IAuthorsDao;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;

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
     * @param authors
     * @return id
     */
    @Override
    public Integer addAuthors(Authors authors) {
        return iAuthorsDao.insert(authors);
    }
}
