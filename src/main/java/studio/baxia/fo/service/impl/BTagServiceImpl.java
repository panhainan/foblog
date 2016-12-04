package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.baxia.fo.dao.IArticleDao;
import studio.baxia.fo.dao.ITagDao;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IBTagService;
import studio.baxia.fo.util.ReturnUtil;
import studio.baxia.fo.vo.TagVo;

import java.util.List;

/**
 * Created by Pan on 2016/12/4.
 */
@Service("tagService")
public class BTagServiceImpl implements IBTagService {
    @Autowired
    private ITagDao iTagDao;
    @Autowired
    private IArticleDao iArticleDao;
    @Override
    public Boolean add(Tag tag) {
        Integer result = iTagDao.insert(tag);
        return ReturnUtil.returnResult(result);
    }

    @Override
    public Boolean edit(Tag tag) {
        Integer result = iTagDao.update(tag);
        return ReturnUtil.returnResult(result);
    }

    @Override
    public Boolean deleteById(int tagId) {
        List<Article> listArticle = iArticleDao.selectBy(
                new Article().setTagIds(tagId + ","), null);
        if (listArticle != null) {
            for (int i = 0; i < listArticle.size(); i++) {
                Article article = listArticle.get(i);
                String tagIdsStr = article.getTagIds();
                int index = tagIdsStr.indexOf((tagId + ","));
                if (index == -1) {
                    return false;
                } else {
                    String str = tagIdsStr.replaceAll((tagId + ","), "");
                    article.setTagIds(str);
                    Integer result = iArticleDao.update(article);
                    if (result == 0) {
                        return false;
                    }
                }
            }
        }
        Integer result = iTagDao.delete(tagId);
        return ReturnUtil.returnResult(result);
    }

    @Override
    public Tag getById(int tagId) {
        Tag result = iTagDao.selectById(tagId);
        return result;
    }

    @Override
    public List<Tag> getAllBy() {
        List<Tag> result = iTagDao.selectBy();
        return result;
    }

    @Override
    public List<TagVo> getAllVoBy(Integer articleStatus) {
        List<TagVo> result = iTagDao.selectVoBy(articleStatus);
        return result;
    }

}
