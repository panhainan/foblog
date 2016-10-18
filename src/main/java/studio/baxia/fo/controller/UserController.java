package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.service.IUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FirePan on 2016/10/18.
 */
@RequestMapping(value = "/{username}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Controller("userController")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping(value = "")
    public Map<String,Object> test(@PathVariable("username")String username){
        Map<String,Object> map = new HashMap<>();
        System.out.println(username);
        map.put("msg",username);
        return map;
    }
}
