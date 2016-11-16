package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.TreeInfoResult;
import studio.baxia.fo.common.TreeInfoUtil;
import studio.baxia.fo.dao.IArticleDao;
import studio.baxia.fo.dao.IGuestDao;
import studio.baxia.fo.dao.IMessageDao;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Guest;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.service.IMessageService;
import studio.baxia.fo.util.ReturnUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/11/16.
 */
@Service("messageService")
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private IMessageDao iMessageDao;
    @Autowired
    private IArticleDao iArticleDao;
    @Autowired
    private IGuestDao guestDao;

    public Boolean messageAdd(Message message) {
        message.setPubTime(new Date());
        Integer result = iMessageDao.insert(message);
        return ReturnUtil.returnResult(result);
    }

    /**
     * 游客评论操作
     *
     * @param guest
     * @param message
     * @return
     */
    @Override
    public boolean guestMessage(Guest guest, Message message) {
        int guestId = addOrUpdateGuest(guest);
        message.setAuthorId(guestId);
        addMessage(message);
        return true;
    }

    private int addOrUpdateGuest(Guest guest) {
        Map<String, Object> map = new HashMap<>();
        if (guest != null && !"".equals(guest.getEmail().trim())) {
            map.put("email", guest.getEmail());
        } else {
            //这里需要做异常处理，提示没有填写邮箱
            return -1;
        }
        Guest isExistGuest = guestDao.queryOneByCondition(map);
        if (isExistGuest == null) {
            //该游客没有在网站评论过，需要保存信息，注意游客guest中必须包含email和nickname
            if ("".equals(guest.getNickname().trim())) {
                //异常处理，提示没有填写昵称
                return -1;
            }
            return guestDao.insert(guest);
        } else {
            //该游客已经存在网站数据库中，需要比对更新信息
            if (!isExistGuest.getNickname().equals(guest.getNickname().trim())
                    || !isExistGuest.getPersonalWebsite().equals(guest.getPersonalWebsite().trim())) {
                guest.setId(isExistGuest.getId());
                return guestDao.updateByPrimaryKey(guest);
            }
            return isExistGuest.getId();
        }

    }

    private boolean addMessage(Message message) {
        return true;
    }

    @Override
    public Boolean messageDeleteById(int messageId, int authorId) {
        Message message = iMessageDao.selectById(messageId);
        Integer result = 0;
        if (message != null) {
            Article article = iArticleDao.selectById(message.getArticleId());
            if (article != null) {
                if (message.getParentId() != CommonConstant.MESSAGE_DEFAULT_PARENT_ID) {
                    Integer counts = iMessageDao.selectCountBy(messageId,
                            message.getBlockId());
                    result = iMessageDao.deleteBy(messageId,
                            message.getBlockId());
                    if (result == counts) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    result = iMessageDao.deleteById(messageId);
                    return ReturnUtil.returnResult(result);
                }
            }
        }
        return false;
    }

    @Override
    public Boolean messageDeleteBy(int messageArticleId) {
        Integer result = iMessageDao.deleteByArticleId(messageArticleId);
        return ReturnUtil.returnResult(result);
    }

    public TreeInfoResult messageGetAllBy(int messageArticleId, int reverseOrder) {
        List<Message> list = iMessageDao.selectByArticleId(messageArticleId,
                CommonConstant.MESSAGE_NULL_PARENT_ID, null, reverseOrder);
        TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(list,
                null);
        return treeInfo;
    }

}
