package studio.baxia.fo.util;

import studio.baxia.fo.common.CommonConstant;

/**
 * Created by Pan on 2016/11/16.
 */
public class ReturnUtil {
    public static Boolean returnResult(Integer result) {
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static String returnResult(Boolean result){
        if(result){
            return CommonConstant.OPERATE_SUCCESS_MESSAGE;
        }
        return CommonConstant.OPERATE_FAIL_MESSAGE;
    }
}
