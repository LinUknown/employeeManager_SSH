package cn.itcast.service.impl;

import cn.itcast.bean.Department;
import cn.itcast.bean.PageBean;
import cn.itcast.dao.DepartmentDao;
import cn.itcast.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * 部门管理业务层实现类
 */

@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public PageBean<Department> findByPage(Integer currPage) {
        PageBean<Department> pageBean = new PageBean<Department>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页所显示的记录数
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        //封装数据库总记录数
        int totalCount = departmentDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 封装总的页数
        Double tc = Double.valueOf(totalCount);
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 封装每页显示的数据
        int begin = (currPage - 1) * pageSize;
        List<Department> list = departmentDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    // 业务层
    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public Department findById(Integer did) {
        return departmentDao.findById(did);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    // 查询所有的方法
    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

}
