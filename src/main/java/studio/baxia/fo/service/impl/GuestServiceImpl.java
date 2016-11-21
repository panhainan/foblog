package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.baxia.fo.dao.IGuestDao;
import studio.baxia.fo.pojo.Guest;
import studio.baxia.fo.service.IGuestService;

import java.util.List;
import java.util.Map;

/**
 * Created by 刘俊 on 2016/11/2.
 */
@Service("guestService")
public class GuestServiceImpl implements IGuestService{

    @Autowired
    private IGuestDao guestDao;

    @Override
    public List<Guest> getAllGuests() {
        return guestDao.queryAll();
    }

    @Override
    public Guest queryOneByCondition(Map<String, Object> condition) {
        Guest g =  guestDao.queryOneByCondition(condition);
        return g;
    }

    @Override
    public int addGuest(Guest guest) {
        return guestDao.insert(guest);
    }

    @Override
    public int updateGuest(Guest guest) {
        return guestDao.updateByPrimaryKeySelective(guest);
    }

}
