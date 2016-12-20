package studio.baxia.fo.vo;

import studio.baxia.fo.pojo.Recommend;

/**
 * Created by Pan on 2016/12/20.
 */
public class RecommendVo extends Recommend {

    private String content;

    public Recommend toRecommend(){
        Recommend recommend = new Recommend();
        recommend.setId(this.getId());
        recommend.setTitle(this.getTitle());
        recommend.setHasContent(this.isHasContent());
        recommend.setContentId(this.getContentId());
        recommend.setArticleUrl(this.getArticleUrl());
        recommend.setSummary(this.getSummary());
        recommend.setPubTime(this.getPubTime());
        recommend.setHits(this.getHits());
        return recommend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
