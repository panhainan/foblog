package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Project;

import java.util.List;

/**
 * Created by Pan on 2016/12/27.
 */
public interface IProjectService {
    long add(Project project);
    long edit(Project project);
    boolean remove(long id);
    List<Project> list(boolean status);
    PageInfoResult<Project> list(PageConfig pageConfig,Project project);
    boolean hits(Long id);

    Project get(long id,boolean status);
}
