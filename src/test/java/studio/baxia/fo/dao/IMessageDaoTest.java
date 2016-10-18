package studio.baxia.fo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.common.Constant;
import studio.baxia.fo.common.TreeInfoResult;
import studio.baxia.fo.common.TreeInfoUtil;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 */
public class IMessageDaoTest extends BaseTest {

    @Autowired
    private IMessageDao iMessageDao;

    @Autowired
    private IArticleDao iArticleDao;


    @Rollback(false)
    @Test
    public void testInsert() {
        int articleId = 1;
        Article article = iArticleDao.selectById(articleId,1);
        Message message = new Message();
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        if (article != null && article.getStatus() == Constant.ACTICLE_STATUS_BLOG) {
            int guestBId = 1;//B
            int authorAId = article.getAuthorId();
            message.setArticleId(article.getId());
            message.setBlockId(1);
            message.setContent("B对A的文章进行了评论");
            message.setUserType(Constant.USER_TYPE_GUEST);
            message.setAuthorId(guestBId);
            Integer result = iMessageDao.insert(message);
            printResultStr(methodName, null, result);


            int guestCId = 2;//C
            Message message1 = new Message();
            message1.setArticleId(article.getId());
            message1.setBlockId(1);
            message1.setParentId(message.getId());
            message1.setContent("C对B的评论进行了评论");
            message1.setUserType(Constant.USER_TYPE_GUEST);
            message1.setAuthorId(guestCId);
            Integer result1 = iMessageDao.insert(message1);
            printResultStr(methodName, null, result1);

            int guestDId = 3;//D
            Message message2 = new Message();
            message2.setArticleId(article.getId());
            message2.setBlockId(1);
            message2.setParentId(message.getId());
            message2.setContent("D对B的评论进行了评论");
            message2.setUserType(Constant.USER_TYPE_GUEST);
            message2.setAuthorId(guestDId);
            Integer result2 = iMessageDao.insert(message2);
            printResultStr(methodName, null, result2);

            int guestEId = 4;//E
            Message message3 = new Message();
            message3.setArticleId(article.getId());
            message3.setBlockId(2);
            message3.setContent("E对A的文章进行了评论");
            message3.setUserType(Constant.USER_TYPE_GUEST);
            message3.setAuthorId(guestEId);
            Integer result3 = iMessageDao.insert(message3);
            printResultStr(methodName, null, result3);

            int guestFId = 5;//F
            Message message4 = new Message();
            message4.setArticleId(article.getId());
            message4.setBlockId(3);
            message4.setContent("F对A的文章进行了评论");
            message4.setUserType(Constant.USER_TYPE_GUEST);
            message4.setAuthorId(guestFId);
            Integer result4 = iMessageDao.insert(message4);
            printResultStr(methodName, null, result4);

            Message message5 = new Message();
            message5.setArticleId(article.getId());
            message5.setBlockId(1);
            message5.setParentId(message1.getId());
            message5.setContent("B对C的评论进行了评论");
            message5.setUserType(Constant.USER_TYPE_GUEST);
            message5.setAuthorId(guestBId);
            Integer result5 = iMessageDao.insert(message5);
            printResultStr(methodName, null, result5);

            Message message6 = new Message();
            message6.setArticleId(article.getId());
            message6.setBlockId(1);
            message6.setParentId(message2.getId());
            message6.setContent("A对D的评论进行了评论");
            message6.setUserType(Constant.USER_TYPE_AUTHOR);
            message6.setAuthorId(authorAId);
            Integer result6 = iMessageDao.insert(message6);
            printResultStr(methodName, null, result6);

        }
    }

    @Test
    public void testSelectByArticleId() {
        int articleId = 1;
        Article article = iArticleDao.selectById(articleId,1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        if (article != null && article.getStatus() == Constant.ACTICLE_STATUS_BLOG) {
            List<Message> result = iMessageDao.selectByArticleId(article.getId(), Constant.MESSAGE_NULL_PARENT_ID, null, Constant.REVERSE_ORDER);
            printResultStr(methodName, null, result);

            TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(result, null);
            System.out.println(treeInfo);

        }

    }

    @Test
    public void testDelete() {
        int deleteMessageArticleId = 1;
        int deleteMessageId = 25;
        int deleteMessageBlockId = 1;
        List<Integer> ids = new ArrayList<>();
        Article article = iArticleDao.selectById(deleteMessageArticleId,1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        if (article != null && article.getStatus() == Constant.ACTICLE_STATUS_BLOG) {
            List<Message> listMsg = iMessageDao.selectByArticleId(article.getId(), Constant.MESSAGE_NULL_PARENT_ID, deleteMessageBlockId, Constant.CORRECT_ORDER);
            if (listMsg != null && listMsg.size() > 0) {
                TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(listMsg, listMsg.get(0));
                if (treeInfo.getData().getId() == deleteMessageId) {
                    TreeInfoUtil.traverseTreeChildGetIds(treeInfo, ids);
                } else {
                    List<TreeInfoResult> list = treeInfo.getChildrens();
                    for (int i = 0; i < list.size(); i++) {
                        TreeInfoResult treeNode = list.get(i);
                        if (treeNode.getData().getId() == deleteMessageId) {
                            TreeInfoUtil.traverseTreeChildGetIds(treeNode, ids);
                            break;
                        }
                    }
                }
            }
            System.out.println(ids);
//            ids = new ArrayList<>();
            Integer result = iMessageDao.delete(ids);
            methodName = new Throwable().getStackTrace()[0].getMethodName();
            printResultStr(methodName, null, result);
        }

    }


    @Test
    public void testSelectCountByArticleId() {
        int articleId = 1;
        Article article = iArticleDao.selectById(articleId,1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        if (article != null && article.getStatus() == Constant.ACTICLE_STATUS_BLOG) {
            Integer result = iMessageDao.selectCountByArticleId(articleId);
            printResultStr(methodName, null, result);
        }
    }
}
