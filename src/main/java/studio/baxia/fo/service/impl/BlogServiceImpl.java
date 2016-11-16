package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.dao.IArticleDao;
import studio.baxia.fo.dao.ICategoryDao;
import studio.baxia.fo.dao.IMessageDao;
import studio.baxia.fo.dao.ITagDao;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IBlogService;
import studio.baxia.fo.util.ReturnUtil;
import studio.baxia.fo.vo.ArchiveVo;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Pan on 2016/10/16.
 */
@Service("articleService")
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private ICategoryDao iCategoryDao;
	@Autowired
	private ITagDao iTagDao;
	@Autowired
	private IArticleDao iArticleDao;
	@Autowired
	private IMessageDao iMessageDao;


	@Override
	public Boolean categoryAdd(Category category) {
		Integer result = iCategoryDao.insert(category);
		return ReturnUtil.returnResult(result);
	}

	@Override
	public Boolean categoryEdit(Category category) {
		Integer result = iCategoryDao.update(category);
		return ReturnUtil.returnResult(result);
	}

	@Override
	public Boolean categoryDeleteById(int categoryId) {
		Category cIdCategory = iCategoryDao.selectById(categoryId);
		if (cIdCategory.getName().equals(CommonConstant.NEW_NO_NAME_CATEGORY)) {
			return false;
		} else {
			Category cNameCategory = iCategoryDao
					.selectByName(CommonConstant.NEW_NO_NAME_CATEGORY);
			Integer newCategoryId = null;
			if (cNameCategory == null) {
				Category newCategory = new Category();
				newCategory.setName(CommonConstant.NEW_NO_NAME_CATEGORY);
				newCategory
						.setParentId(CommonConstant.CATEGORY_DEFAULT_PARENT_ID);
				Integer result = iCategoryDao.insert(newCategory);
				newCategoryId = newCategory.getId();
			} else {
				newCategoryId = cNameCategory.getId();
			}
			Integer result1 = iArticleDao.updateCategoryId(categoryId,
					newCategoryId);
			Integer result2 = iCategoryDao.deleteById(categoryId);
			return ReturnUtil.returnResult(result2);
		}

		// 下面的代码是为多级目录准备的，但是此次已经舍弃多级目录
		/*
		 * Category c = iCategoryDao.selectById(categoryId); Integer result =
		 * null; if (c != null && c.getAuthorId() == categoryAuthorId) { if
		 * (c.getParentId() == 0) { // 根级别目录 result = iCategoryDao.deleteBy(1,
		 * categoryId); } result += iCategoryDao.deleteById(categoryId); return
		 * returnResult(result); } else { // categoryId不存在或者没有权限 return false; }
		 */
	}

	@Override
	public List<Category> categoryGetAllBy() {
		List<Category> result = iCategoryDao.selectBy(null);
		return result;
	}

	@Override
	public List<CategoryVo> categoryGetAllVoBy(Integer articleStatus) {
		List<CategoryVo> result = iCategoryDao.selectVoBy(articleStatus, null);
		return result;
	}

	@Override
	public Category categoryGetById(int categoryId) {
		Category result = iCategoryDao.selectById(categoryId);
		return result;
	}

	@Override
	public Boolean tagAdd(Tag tag) {
		Integer result = iTagDao.insert(tag);
		return ReturnUtil.returnResult(result);
	}

	@Override
	public Boolean tagEdit(Tag tag) {
		Integer result = iTagDao.update(tag);
		return ReturnUtil.returnResult(result);
	}

	@Override
	public Boolean tagDeleteById(int tagId) {
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
	public Tag tagGetById(int tagId) {
		Tag result = iTagDao.selectById(tagId);
		return result;
	}

	@Override
	public List<Tag> tagGetAllBy() {
		List<Tag> result = iTagDao.selectBy();
		return result;
	}

	@Override
	public List<TagVo> tagGetAllVoBy(Integer articleStatus) {
		List<TagVo> result = iTagDao.selectVoBy(articleStatus);
		return result;
	}

	private String[] tagGetAllNamesBy(String tagIds) {
		String[] tagIdsArr = tagIds.split(",");
		if (tagIdsArr != null && tagIdsArr[0] != "") {
			String[] tagNames = new String[tagIdsArr.length];
			List<Integer> tagIdList = new ArrayList<Integer>(tagIdsArr.length);
			for (int i = 0; i < tagIdsArr.length; i++) {
				if (tagIdsArr[i] != "") {
					tagIdList.add(Integer.parseInt(tagIdsArr[i]));
					// Integer tagId = Integer.parseInt(tagIdsArr[i]);
					// Tag tag = iTagDao.selectById(tagId, articleAuthorId);
					// tagNames[i]=tag.getName();
				}
			}
			List<Tag> tags = iTagDao.selectByIds(tagIdList);
			for (int i = 0; i < tags.size(); i++) {
				tagNames[i] = tags.get(i).getName();
			}
			return tagNames;
		}
		return null;
	}

	private String tagGetIdsBy(String[] tagNames) {
		if (tagNames != null) {
			StringBuilder strBuilderTagIds = new StringBuilder();
			for (int i = 0; i < tagNames.length; i++) {
				if (tagNames[i] != null && tagNames[i].trim() != "") {
					Tag t = iTagDao.selectByName(tagNames[i]);
					if (t != null) {
						strBuilderTagIds.append(t.getId() + ",");
					} else {
						Tag newTag = new Tag(tagNames[i]);
						Integer r = iTagDao.insert(newTag);
						if (r > 0) {
							strBuilderTagIds.append(newTag.getId() + ",");
						}
					}
				}
			}
			return strBuilderTagIds.toString();
		}
		return null;
	}

	@Override
	public Integer articleAdd(ArticleVo article) {
		if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
			article.setPubTime(new Date());
		}
		article.setTagIds(tagGetIdsBy(article.getTagNames()));
		article.setWriteTime(new Date());
		Integer result = iArticleDao.insert(article);
		if (ReturnUtil.returnResult(result)) {
			return article.getId();
		} else {
			return 0;
		}

	}

	@Override
	public Integer articleEdit(ArticleVo article) {
		Integer result = 0;
		if (article.getOnlyChangeStatus() != null
				&& article.getOnlyChangeStatus() == true) {
			result = iArticleDao.updateStatus(article);
		} else {
			if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
				article.setPubTime(new Date());
			}
			article.setTagIds(tagGetIdsBy(article.getTagNames()));
			result = iArticleDao.update(article);
		}
		if (ReturnUtil.returnResult(result)) {
			return article.getId();
		} else {
			return 0;
		}
	}

	@Override
	public Boolean articleDeleteById(int articleId) {
		Article article = iArticleDao.selectById(articleId);
		if (article != null) {
			// 获取该文章id对应的所有评论记录总数
			Integer counts = iMessageDao.selectCountByArticleId(articleId);
			// 返回删除所有评论的文章为id的受影响行数
			Integer results = iMessageDao.deleteByArticleId(articleId);
			if (results == counts) {
				Integer result = iArticleDao.delete(articleId);
				return ReturnUtil.returnResult(result);
			}
		}
		return false;
	}

	@Override
	public Boolean articleDeleteTag(int tagId, int articleId) {
		Article article = iArticleDao.selectById(articleId);
		if (article != null) {
			String tagIdsStr = article.getTagIds();
			// int index = tagIdsStr.indexOf((tagId + ","));
			// if (index == -1) {
			// return false;
			// } else {
			String str = tagIdsStr.replaceAll((tagId + ","), "");
			article.setTagIds(str);
			Integer result = iArticleDao.update(article);
			return ReturnUtil.returnResult(result);
			// }
		}
		return false;
	}

	@Override
	public ArticleVo articleVoGetById(int articleId) {
		ArticleVo article = iArticleDao.selectVoById(articleId);
		if (article == null) {
			return null;
		}
		article.setTagNames(tagGetAllNamesBy(article.getTagIds()));
		return article;
	}

	@Override
	public Map<String, Object> articleVoGetByTitle(String articleTitle) {
		// String s = urlStrParamTranscoding(articleTitle);
		ArticleVo article = iArticleDao.selectVoByTitle(articleTitle,
				CommonConstant.ACTICLE_STATUS_BLOG);
		if (article == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		article.setTagNames(tagGetAllNamesBy(article.getTagIds()));
		Article preArticle = iArticleDao.selectNextOrPreVoBy(article, false);
		Article nextArticle = iArticleDao.selectNextOrPreVoBy(article, true);
		map.put("currentArticle", article);
		map.put("preArticle", preArticle);
		map.put("nextArticle", nextArticle);
		return map;
	}

	private String urlStrParamTranscoding(String param) {
		String s = null;
		try {
			if (param.equals(new String(param.getBytes("UTF-8"), "UTF-8"))) {
				s = param;
			}
			s = new String(param.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			s = param;
		}
		return s;
	}

	@Override
	public PageInfoResult<Article> articleGetAllBy(Integer articleStatus,
			PageConfig pageConfig) {
		Article article = new Article();
		article.setStatus(articleStatus);
		List<Article> result = iArticleDao.selectBy(article, pageConfig);
		Integer resultCount = iArticleDao.selectCountBy(article);
		PageInfoResult<Article> pageInfoResult = new PageInfoResult(result,
				pageConfig, resultCount);
		return pageInfoResult;
	}

	@Override
	public PageInfoResult<ArticleVo> articleGetAllManageBy(
			Integer articleStatus, PageConfig pageConfig) {
		Article article = new Article();
		article.setStatus(articleStatus);
		List<ArticleVo> result = iArticleDao.selectVoBy(article, pageConfig);
		Integer resultCount = iArticleDao.selectCountBy(article);
		PageInfoResult<ArticleVo> pageInfoResult = new PageInfoResult(result,
				pageConfig, resultCount);
		return pageInfoResult;
	}

	@Override
	public List<ArticleVo> articleGetAllByCategoryName(String categoryName) {
		// String s = urlStrParamTranscoding(categoryName);
		Category category = iCategoryDao.selectByName(categoryName);
		if (category != null) {
			List<ArticleVo> result = iArticleDao.selectVoBy(new Article()
					.setStatus(CommonConstant.ACTICLE_STATUS_BLOG)
					.setCategoryIds(category.getId()), null);
			return result;
		}
		return null;
	}

	@Override
	public List<Article> articleGetAllByTagId(int tagId, Integer articleStatus) {
		List<Article> list = iArticleDao.selectBy(
				new Article().setTagIds(tagId + ",").setStatus(articleStatus),
				null);
		return list;
	}

	@Override
	public List<Article> articleGetAllByCategoryId(int categoryId,
			Integer articleStatus) {
		List<Article> list = iArticleDao.selectBy(
				new Article().setCategoryIds(categoryId).setStatus(
						articleStatus), null);
		return list;
	}

	@Override
	public List<ArticleVo> articleGetAllByTagName(String tagName) {
		Tag tag = iTagDao.selectByName(tagName);
		if (tag != null) {
			List<ArticleVo> result = iArticleDao.selectVoBy(
					new Article().setStatus(CommonConstant.ACTICLE_STATUS_BLOG)
							.setTagIds(tag.getId() + ","), null);
			return result;
		}
		return null;
	}



	@Override
	public List<ArchiveVo> archiveGetAllVo(Integer articleStatus,
			String archiveTypeYear,String archiveTypeYearMonth) {
		return iArticleDao.selectAllArchives(articleStatus, archiveTypeYear,archiveTypeYearMonth);
	}

	@Override
	public List<Article> archiveGetAllArticles(String name, Integer articleStatus,
			String archiveType) {
		return iArticleDao.selectArchiveArticles(articleStatus, archiveType, name);
	}

}
