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
import studio.baxia.fo.vo.ArticleMessageVo;

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

    @Override
    public boolean guestMessage(Guest guest, Message message) {
        int guestId = addOrUpdateGuest(guest);
        if(ReturnUtil.returnResult(guestId)){
            message.setAuthorId(guestId);
            message.setUserType(CommonConstant.USER_TYPE_GUEST);
            return addMessage(message);
        }
        return false;
    }

    private int addOrUpdateGuest(Guest guest) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", guest.getEmail());
        map.put("nickname", guest.getNickname());
        Guest isExistGuest = guestDao.queryOneByCondition(map);
        if (isExistGuest == null) {
            //该游客没有在网站评论过，需要保存信息，注意游客guest中必须包含email和nickname
            int temp = guestDao.insert(guest);
            if(temp>0){
                return guest.getId();
            }else{
                return 0;
            }
        } else {
            //该游客已经存在网站数据库中，需要比对更新信息
            if ( guest.getPersonalWebsite()!=null && !guest.getPersonalWebsite().trim().equals(isExistGuest.getPersonalWebsite())) {
                guest.setId(isExistGuest.getId());
                guestDao.updateByPrimaryKey(guest);
            }
            return isExistGuest.getId();
        }

    }

    private boolean addMessage(Message message) {
        message.setPubTime(new Date());
        int result = iMessageDao.insert(message);
        return ReturnUtil.returnResult(result);
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

    @Override
    public List<ArticleMessageVo> list(int messageArticleId) {
        return iMessageDao.selectArticleMessageVo(messageArticleId,CommonConstant.REVERSE_ORDER);
    }

    public TreeInfoResult messageGetAllBy(int messageArticleId, int reverseOrder) {
        List<Message> list = iMessageDao.selectByArticleId(messageArticleId,
                CommonConstant.MESSAGE_NULL_PARENT_ID, null, reverseOrder);
        TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(list,
                null);
        return treeInfo;
    }

}
