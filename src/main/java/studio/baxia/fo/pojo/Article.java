package studio.baxia.fo.pojo;

import java.util.Date;

/**
 * Created by FirePan on 2016/10/11.
 * 文章实体信息.
 */
public class Article {
    private Integer id;
    private String title;//标题
    private String summary;//概要
    private String content;//内容
    private String categoryIds;//类别id,多个,用/分开
    private String tagIds;//标签id,多个,用/分开
    private Integer authorId;//作者id
    private Integer status;//文章状态：0-草稿，1-博文
    private Date writeTime;//撰写时间
    private Date pubTime; //发布时间

}
