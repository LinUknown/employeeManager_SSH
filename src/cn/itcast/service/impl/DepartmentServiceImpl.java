package cn.itcast.service.impl;

import cn.itcast.bean.Department;
import cn.itcast.bean.PageBean;
import cn.itcast.dao.DepartmentDao;
import cn.itcast.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * ���Ź���ҵ���ʵ����
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
        //��װ��ǰҳ��
        pageBean.setCurrPage(currPage);
        //��װÿҳ����ʾ�ļ�¼��
        int pageSize = 3;
        pageBean.setPageSize(pageSize);
        //��װ���ݿ��ܼ�¼��
        int totalCount = departmentDao.findCount();
        pageBean.setTotalCount(totalCount);
        // ��װ�ܵ�ҳ��
        Double tc = Double.valueOf(totalCount);
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // ��װÿҳ��ʾ������
        int begin = (currPage - 1) * pageSize;
        List<Department> list = departmentDao.findByPage(begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    // ҵ���
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

    // ��ѯ���еķ���
    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

}
