package studio.baxia.fo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.pojo.Guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘俊 on 2016/11/2.
 */
public class IGuestServiceTest extends BaseTest {

    @Autowired
    private IGuestService iGuestService;

    @Test
    public void queryOneByCondition(){
        Guest guest = new Guest();
        Map<String, Object> condition = new HashMap<>();
        condition.put("nickname","呵呵");
        guest = iGuestService.queryOneByCondition(condition);
        printResultStr("queryOneByCondition",null,guest);
    }

    @Test
    public void queryAll(){
        List<Guest> guestList = iGuestService.getAllGuests();
        printResultStr("queryAll",null,guestList);
    }
}
