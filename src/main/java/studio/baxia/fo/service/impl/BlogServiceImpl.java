package studio.baxia.fo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.CommonResult;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.common.TreeInfoResult;
import studio.baxia.fo.common.TreeInfoUtil;
import studio.baxia.fo.dao.IArticleDao;
import studio.baxia.fo.dao.ICategoryDao;
import studio.baxia.fo.dao.IMessageDao;
import studio.baxia.fo.dao.ITagDao;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IBlogService;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

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

	/**
	 * 返回结果
	 * 
	 * @param result
	 *            受影响的行数（=1+：返回true，=0-：返回false）
	 * @return
	 */
	private Boolean returnResult(Integer result) {
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加类别
	 *
	 * @param category
	 *            类别（parent_id,name,author_id）
	 * @return
	 */
	@Override
	public Boolean categoryAdd(Category category) {
		Integer result = iCategoryDao.insert(category);
		return returnResult(result);
	}

	/**
	 * 修改类别
	 *
	 * @param category
	 *            类别（id,author_id,parent_id,name）
	 * @return
	 */
	@Override
	public Boolean categoryEdit(Category category) {
		Integer result = iCategoryDao.update(category);
		return returnResult(result);
	}

	/**
	 * 通过类别id删除类别
	 *
	 * @param categoryId
	 *            类别id
	 * @param categoryAuthorId
	 *            类别作者id
	 * @return
	 */
	@Override
	public Boolean categoryDeleteById(int categoryId, int categoryAuthorId) {
		Category cIdCategory = iCategoryDao.selectById(categoryId);
		if (cIdCategory.getName().equals(CommonConstant.NEW_NO_NAME_CATEGORY)) {
			return false;
		} else {
			Category cNameCategory = iCategoryDao.selectByName(
					categoryAuthorId, CommonConstant.NEW_NO_NAME_CATEGORY);
			Integer newCategoryId = null;
			if (cNameCategory == null) {
				Category newCategory = new Category();
				newCategory.setName(CommonConstant.NEW_NO_NAME_CATEGORY);
				newCategory.setAuthorId(categoryAuthorId);
				newCategory
						.setParentId(CommonConstant.CATEGORY_DEFAULT_PARENT_ID);
				Integer result = iCategoryDao.insert(newCategory);
				newCategoryId = newCategory.getId();
			} else {
				newCategoryId = cNameCategory.getId();
			}
			Integer result1 = iArticleDao.updateCategoryId(categoryId,
					newCategoryId, categoryAuthorId);
			Integer result2 = iCategoryDao.deleteById(categoryId,
					categoryAuthorId);
			return returnResult(result2);
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

	/**
	 * 通过作者id获取所有类别
	 *
	 * @param authorId
	 *            作者id
	 * @return
	 */
	@Override
	public List<Category> categoryGetAllBy(int authorId) {
		List<Category> result = iCategoryDao.selectBy(authorId, null);
		return result;
	}

	@Override
	public List<CategoryVo> categoryGetAllVoBy(int authorId,
			Integer articleStatus) {
		List<CategoryVo> result = iCategoryDao.selectVoBy(authorId,
				articleStatus, null);
		return result;
	}

	/**
	 * 通过类别id获取类别
	 *
	 * @param categoryId
	 *            类别id
	 * @return
	 */
	@Override
	public Category categoryGetById(int categoryId) {
		Category result = iCategoryDao.selectById(categoryId);
		return result;
	}

	/**
	 * 添加标签
	 *
	 * @param tag
	 *            标签（name,authorId）
	 * @return
	 */
	@Override
	public Boolean tagAdd(Tag tag) {
		Integer result = iTagDao.insert(tag);
		return returnResult(result);
	}

	@Override
	public Boolean tagEdit(Tag tag) {
		Integer result = iTagDao.update(tag);
		return returnResult(result);
	}

	/**
	 * 通过标签id删除标签
	 *
	 * @param tagId
	 *            标签id
	 * @param tagAuthorId
	 *            标签作者id
	 * @return
	 */
	@Override
	public Boolean tagDeleteById(int tagId, int tagAuthorId) {
		List<Article> listArticle = iArticleDao.selectBy(new Article()
				.setAuthorId(tagAuthorId).setTagIds(tagId + ","), null);
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
					if(result==0){
						return false;
					}
				}
			}
		}
		Integer result = iTagDao.delete(tagId, tagAuthorId);
		return returnResult(result);
	}

	/**
	 * 通过标签id获取标签
	 *
	 * @param tagId
	 *            标签id
	 * @param tagAuthorId
	 *            标签作者id
	 * @return
	 */
	@Override
	public Tag tagGetById(int tagId, int tagAuthorId) {
		Tag result = iTagDao.selectById(tagId, tagAuthorId);
		return result;
	}

	/**
	 * 通过作者id获取所有标签
	 *
	 * @param authorId
	 *            作者id
	 * @return
	 */
	@Override
	public List<Tag> tagGetAllBy(int authorId) {
		List<Tag> result = iTagDao.selectBy(authorId);
		return result;
	}

	@Override
	public List<TagVo> tagGetAllVoBy(int authorId, Integer articleStatus) {
		List<TagVo> result = iTagDao.selectVoBy(authorId, articleStatus);
		return result;
	}

	private String[] tagGetAllNamesBy(String tagIds, Integer articleAuthorId) {
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
			List<Tag> tags = iTagDao.selectByIds(tagIdList, articleAuthorId);
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
					Tag t = iTagDao.selectByName(tagNames[i], 1);
					if (t != null) {
						strBuilderTagIds.append(t.getId() + ",");
					} else {
						Tag newTag = new Tag(tagNames[i], 1);
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

	/**
	 * 添加文章
	 *
	 * @param article
	 *            文章信息（title,summary,content,categoryIds,tagIds,authorId,status）
	 * @return
	 */
	@Override
	public Integer articleAdd(ArticleVo article) {
		if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
			article.setPubTime(new Date());
		}
		article.setTagIds(tagGetIdsBy(article.getTagNames()));
		article.setWriteTime(new Date());
		article.setAuthorId(1);
		Integer result = iArticleDao.insert(article);
		if (returnResult(result)) {
			return article.getId();
		} else {
			return 0;
		}

	}

	/**
	 * 编辑文章
	 *
	 * @param article
	 *            文章信息（id,[title,summary,content,categoryIds,tagIds,status,
	 *            pubTime](可为空，为空表示该字段不修改)）
	 * @return
	 */
	@Override
	public Integer articleEdit(ArticleVo article) {
		Integer result = 0;
		if (article.getOnlyChangeStatus() != null
				&& article.getOnlyChangeStatus() == true) {
			article.setAuthorId(1);
			result = iArticleDao.updateStatus(article);
		} else {
			if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
				article.setPubTime(new Date());
			}
			article.setTagIds(tagGetIdsBy(article.getTagNames()));
			result = iArticleDao.update(article);
		}
		if (returnResult(result)) {
			return article.getId();
		} else {
			return 0;
		}
	}

	/**
	 * 通过文章id删除文章
	 *
	 * @param articleId
	 *            文章id
	 * @param articleAuthorId
	 *            文章作者id
	 * @return
	 */
	@Override
	public Boolean articleDeleteById(int articleId, int articleAuthorId) {
		Article article = iArticleDao.selectById(articleId, articleAuthorId);
		if (article != null) {
			// 获取该文章id对应的所有评论记录总数
			Integer counts = iMessageDao.selectCountByArticleId(articleId);
			// 返回删除所有评论的文章为id的受影响行数
			Integer results = iMessageDao.deleteByArticleId(articleId);
			if (results == counts) {
				Integer result = iArticleDao.delete(articleId, articleAuthorId);
				return returnResult(result);
			}
		}
		return false;
	}

	@Override
	public Boolean articleDeleteTag(int tagId, int articleId, int authorId) {
		Article article = iArticleDao.selectById(articleId, authorId);
		if (article != null) {
			String tagIdsStr = article.getTagIds();
//			int index = tagIdsStr.indexOf((tagId + ","));
//			if (index == -1) {
//				return false;
//			} else {
				String str = tagIdsStr.replaceAll((tagId + ","), "");
				article.setTagIds(str);
				Integer result = iArticleDao.update(article);
				return returnResult(result);
//			}
		}
		return false;
	}

	/**
	 * 通过文章id获取文章
	 *
	 * @param articleId
	 *            文章id
	 * @param articleAuthorId
	 *            文章作者id
	 * @return
	 */
	@Override
	public ArticleVo articleVoGetById(int articleId, int articleAuthorId) {
		ArticleVo article = iArticleDao
				.selectVoById(articleId, articleAuthorId);
		if (article == null) {
			return null;
		}
		article.setTagNames(tagGetAllNamesBy(article.getTagIds(),
				articleAuthorId));
		return article;
	}

	/**
	 * 通过标题取得文章
	 *
	 * @param articleTitle
	 *            文章标题
	 * @param articleAuthorId
	 *            文章作者id
	 * @return
	 */
	@Override
	public Map<String, Object> articleVoGetByTitle(String articleTitle,
			Integer articleAuthorId) {
		// String s = urlStrParamTranscoding(articleTitle);
		ArticleVo article = iArticleDao.selectVoByTitle(articleTitle,
				articleAuthorId, CommonConstant.ACTICLE_STATUS_BLOG);
		if (article == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		article.setTagNames(tagGetAllNamesBy(article.getTagIds(),
				articleAuthorId));
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

	/**
	 * 通过作者id、文章状态、分页信息获取分页后的文章列表信息
	 *
	 * @param articleAuthorId
	 *            作者id
	 * @param articleStatus
	 *            文章状态
	 * @param pageConfig
	 *            分页信息
	 * @return
	 */
	@Override
	public PageInfoResult<Article> articleGetAllBy(int articleAuthorId,
			Integer articleStatus, PageConfig pageConfig) {
		Article article = new Article();
		article.setAuthorId(articleAuthorId);
		article.setStatus(articleStatus);
		List<Article> result = iArticleDao.selectBy(article, pageConfig);
		Integer resultCount = iArticleDao.selectCountBy(article);
		PageInfoResult<Article> pageInfoResult = new PageInfoResult(result,
				pageConfig, resultCount);
		return pageInfoResult;
	}

	/**
	 * 通过作者id、文章状态、分页信息获取分页后的文章管理列表信息
	 *
	 * @param articleAuthorId
	 *            作者id
	 * @param articleStatus
	 *            文章状态
	 * @param pageConfig
	 *            分页信息
	 * @return
	 */
	@Override
	public PageInfoResult<ArticleVo> articleGetAllManageBy(int articleAuthorId,
			Integer articleStatus, PageConfig pageConfig) {
		Article article = new Article();
		article.setAuthorId(articleAuthorId);
		article.setStatus(articleStatus);
		List<ArticleVo> result = iArticleDao.selectVoBy(article, pageConfig);
		Integer resultCount = iArticleDao.selectCountBy(article);
		PageInfoResult<ArticleVo> pageInfoResult = new PageInfoResult(result,
				pageConfig, resultCount);
		return pageInfoResult;
	}

	@Override
	public List<ArticleVo> articleGetAllByCategoryName(int authorId,
			String categoryName) {
		// String s = urlStrParamTranscoding(categoryName);
		Category category = iCategoryDao.selectByName(1, categoryName);
		if (category != null) {
			List<ArticleVo> result = iArticleDao.selectVoBy(
					new Article().setAuthorId(1)
							.setStatus(CommonConstant.ACTICLE_STATUS_BLOG)
							.setCategoryIds(category.getId()), null);
			return result;
		}
		return null;
	}
	
	@Override
	public List<Article> articleGetAllByTagId(int authorId, int tagId,
			Integer articleStatus) {
		List<Article> list = iArticleDao.selectBy(
				new Article().setAuthorId(authorId).setTagIds(tagId+",")
						.setStatus(articleStatus), null);
		return list;
	}

	@Override
	public List<Article> articleGetAllByCategoryId(int authorId,
			int categoryId, Integer articleStatus) {
		List<Article> list = iArticleDao.selectBy(
				new Article().setAuthorId(authorId).setCategoryIds(categoryId)
						.setStatus(articleStatus), null);
		return list;
	}

	@Override
	public List<ArticleVo> articleGetAllByTagName(int authorId, String tagName) {
		Tag tag = iTagDao.selectByName(tagName, 1);
		if (tag != null) {
			List<ArticleVo> result = iArticleDao.selectVoBy(
					new Article().setAuthorId(1)
							.setStatus(CommonConstant.ACTICLE_STATUS_BLOG)
							.setTagIds(tag.getId() + ","), null);
			return result;
		}
		return null;
	}

	/**
	 * 添加评论
	 *
	 * @param message
	 *            评论信息（articleId，parentId，content，userType，authorId）
	 * @return
	 */
	@Override
	public Boolean messageAdd(Message message) {
		message.setPubTime(new Date());
		Integer result = iMessageDao.insert(message);
		return returnResult(result);
	}

	/**
	 * 通过评论id删除评论
	 *
	 * @param messageId
	 *            评论id
	 * @param authorId
	 *            操作的作者id
	 * @return
	 */
	@Override
	public Boolean messageDeleteById(int messageId, int authorId) {
		Message message = iMessageDao.selectById(messageId);
		Integer result = 0;
		if (message != null) {
			Article article = iArticleDao.selectById(message.getArticleId(),
					authorId);
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
					return returnResult(result);
				}
			}
		}
		return false;
	}

	/**
	 * 通过文章id删除相关评论
	 *
	 * @param messageArticleId
	 *            文章id
	 * @return
	 */
	@Override
	public Boolean messageDeleteBy(int messageArticleId) {
		Integer result = iMessageDao.deleteByArticleId(messageArticleId);
		return returnResult(result);
	}

	/**
	 * 通过文章id和排序状态获取转换树后的信息
	 *
	 * @param messageArticleId
	 *            文章id
	 * @param reverseOrder
	 *            排序状态
	 * @return
	 */
	@Override
	public TreeInfoResult messageGetAllBy(int messageArticleId, int reverseOrder) {
		List<Message> list = iMessageDao.selectByArticleId(messageArticleId,
				CommonConstant.MESSAGE_NULL_PARENT_ID, null, reverseOrder);
		TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(list,
				null);
		return treeInfo;
	}

	

}
