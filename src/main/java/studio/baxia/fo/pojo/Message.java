package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章留言实体信息.
 */
public class Message {
    private Integer id;
    private Integer parentId;//父id
    private String content;//内容
    private Integer userType;//留言作者类别（author作者，guest访客）
    private Integer authorId;//作者id

}
