package studio.baxia.fo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.pojo.Authors;
import studio.baxia.fo.service.IUserService;
import studio.baxia.fo.util.ExecuteSecurity;
import studio.baxia.fo.vo.AuthorVo;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Controller("manageController")
@RequestMapping(value = "")
public class ManageController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/author", method = {RequestMethod.GET})
    public CommonResult about(HttpServletRequest request) {
        Authors author = userService.getAuthor(CommonConstant.AUTHOR_ID);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "",
                author);
    }

    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/signout", method = {RequestMethod.DELETE})
    public CommonResult signout(HttpServletRequest request) {
        CommonResult commonResult;
        userService.signOut(request);
        commonResult = new CommonResult(CommonConstant.SUCCESS_CODE,
                "已成功注销！");
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/manage/signin", method = {RequestMethod.POST})
    public CommonResult signin(@RequestBody AuthorVo authorVo,
                               HttpServletRequest request) {
        System.out.println("/signin->参数：" + authorVo);
        String resultData = "";
        String message = null;
        boolean isSuccess = true;
        CommonResult commonResult;
        try {
            if(checkCaptcha(authorVo.getCaptcha(),request)){
                resultData = userService.signInCheck(authorVo, request);
                if(resultData==null){
                    isSuccess = false;
                    message="用户名或密码不正确";
                }
            }else{
                isSuccess = false;
                message="验证码不正确";
            }
        } catch (Exception e) {
            message = e.getMessage();
            isSuccess = false;
        } finally {
            if (isSuccess) {
                commonResult = new CommonResult(CommonConstant.SUCCESS_CODE,
                        message, resultData);
            } else {
                commonResult = new CommonResult(CommonConstant.FAIL_CODE,
                        message);
            }
        }
        return commonResult;
    }

    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/author", method = {RequestMethod.GET})
    public CommonResult getInfo(HttpServletRequest request) {
        Authors author = null;
        author = userService.getInfo(request);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "",
                author);
    }

    @ExecuteSecurity
    @ResponseBody
    @RequestMapping(value = "/manage/author", method = {RequestMethod.PUT})
    public CommonResult updateInfo(HttpServletRequest request, @RequestBody Authors info) {
        Boolean result = userService.updateInfo(request, info);
        return new CommonResult(CommonConstant.SUCCESS_CODE, "",
                result);
    }
    @ResponseBody
    @RequestMapping(value = "/manage/getKeys1")
    public Map<String, Object> generateKeypair1(HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = userService.generateKeypair(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 校验验证码是否正确
     * @param captcha 验证码
     */
    private Boolean checkCaptcha(String captcha,
                                      HttpServletRequest request) {
        String valKey = (String) request.getSession().getAttribute("valKey");
        if (valKey!=null && !valKey.equals(captcha)) {
            return false;
        }
        return true;
    }
    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/manage/getCaptcha")
    @ResponseBody
    public void getCaptcha(HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("image/jpeg;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        HttpSession session = request.getSession();
        int width = 90;
        int height = 33;
        System.setProperty("java.awt.headless", "true");
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(204, 204, 204));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(new Font("Consolas",  Font.ITALIC, 19));
        //绘制干扰线
        g.setColor(new Color(160,160, 200));// 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 10 * i + 15, 22);
        }
        session.setAttribute("valKey", sRand);
        g.dispose();
        ServletOutputStream outStream = response.getOutputStream();
        // ImageIO encoder = ImageIO.createJPEGEncoder(outStream);
        ImageIO.write(image, "JPEG", outStream);
        // encoder.encode(image);
        outStream.close();
    }

    @ResponseBody
    @RequestMapping(value = "/manage/getKeys")
    public CommonResult generateKeypair(HttpServletRequest request) {
        Map<String, Object> map = null;
        String msg = CommonConstant.OPERATE_SUCCESS_MESSAGE;
        CommonResult commonResult = null;
        boolean isSuccess = true;
        try {
            map = userService.generateKeypair(request);
        } catch (Exception e) {
            isSuccess = false;
            msg = e.getMessage();
        }finally {
            if (isSuccess){
                commonResult = new CommonResult(CommonConstant.SUCCESS_CODE,msg,
                        map);
            }else{
                commonResult= new CommonResult(CommonConstant.FAIL_CODE,msg);
            }
        }
        return commonResult;
    }

}
