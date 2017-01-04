package studio.baxia.fo.common;

/**
 * Created by Pan on 2016/10/13.
 */
public class CommonConstant {
    public static final int AUTHOR_ID =1;//设定作者在数据库中的id为1
    public static final int ACTICLE_STATUS_DRAFT = 0;
    public static final int ACTICLE_STATUS_BLOG = 1;

    public static final String OPERATE_SUCCESS_MESSAGE = "操作成功";
    public static final String OPERATE_FAIL_MESSAGE = "操作失败";

    public static final String ACTICLE_STATUS_DRAFT_NAME = "草稿";
    public static final String ACTICLE_STATUS_BLOG_NAME = "博客";

    /**
     * 类别不展示
     */
    public static final boolean CATEGORY_ALL = false;
    /**
     * 类别展示
     */
    public static final boolean CATEGORY_SHOW = true;

    public static final boolean PROJECT_ALL = false;
    public static final boolean PROJECT_PUBLIC = true;
    public static final int USER_TYPE_GUEST = 0;
    public static final int USER_TYPE_AUTHOR = 1;

    public static final int CORRECT_ORDER = 0;
    public static final int REVERSE_ORDER = 1;

    public static final int MESSAGE_DEFAULT_PARENT_ID = 0;
    public static final Integer  MESSAGE_NULL_PARENT_ID=null;

    public static final int CATEGORY_DEFAULT_PARENT_ID = 0;

    public static final int SUCCESS_CODE = 1;
    public static final int FAIL_CODE=0;

    public static final String USER_IS_NO_EXIST = "用户不存在！";
    
    public static final String NEW_NO_NAME_CATEGORY = "默认分类";

    public static final String ARCHIVE_TYPE_YEAR = "%Y年";
    public static final String ARCHIVE_TYPE_YEAR_MONTH = "%Y-%m";
    
    public static final int USER_TIME_OUT = 30*60*1000;
}
