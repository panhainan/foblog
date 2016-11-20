package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.pojo.Guest;
import studio.baxia.fo.service.IGuestService;
import studio.baxia.fo.service.IMessageService;
import studio.baxia.fo.util.ReturnUtil;
import studio.baxia.fo.vo.ArticleMessageFormVo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/11/16.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private IGuestService guestService;
    @Autowired
    private IMessageService messageService;


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public CommonResult save(@Valid @RequestBody ArticleMessageFormVo messageFormVo,
                             BindingResult resultValidator) {
        if (resultValidator.hasErrors()) {
            List<String> resultMessage = new ArrayList<>();
            for (FieldError fe : resultValidator.getFieldErrors()) {
                resultMessage.add(fe.getDefaultMessage());
            }
            return new CommonResult(CommonConstant.FAIL_CODE, resultMessage.toString());
        } else {
            boolean resultData = messageService.guestMessage(messageFormVo.getGuest(), messageFormVo.getMessage());
            return new CommonResult(CommonConstant.SUCCESS_CODE, ReturnUtil.returnResult(resultData), resultData);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/guest/email", method = {RequestMethod.POST})
    public CommonResult getGuestByEmail(String email) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("email", email);
        Guest guest = guestService.queryOneByCondition(condition);
        return new CommonResult(CommonConstant.SUCCESS_CODE, null, guest);
    }


}
