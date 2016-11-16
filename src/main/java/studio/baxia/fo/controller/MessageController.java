package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.pojo.Guest;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.service.IMessageService;

/**
 * Created by Pan on 2016/11/16.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {


    @Autowired
    private IMessageService messageService;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResult save(@RequestBody Guest guest,@RequestBody Message message){
        System.out.println("guest:"+guest);
        System.out.println("message:"+message);
        boolean result = messageService.guestMessage(guest,message);
        return new CommonResult(CommonConstant.SUCCESS_CODE,"",result);
    }



}
