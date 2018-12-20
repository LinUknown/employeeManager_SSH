package cn.itcast.dao.impl;

import cn.itcast.bean.Department;
import cn.itcast.dao.DepartmentDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;


public class DepartmentImpl implements DepartmentDao {
    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    // 查询数目数
    @Override
    public int findCount() {
        String hql = "select count(*) from Department";
        List<Long> list = (List<Long>) hibernateTemplate.find(hql);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    // Dao中保存
    @Override
    public void save(Department department) {
        hibernateTemplate.save( department);
    }

    @Override
    public Department findById(Integer did) {
        Department department = hibernateTemplate.get(Department.class, did);
        return department;
    }

    @Override
    public void update(Department department) {
        hibernateTemplate.update(department);

    }

    @Override
    public void delete(Department department) {
        hibernateTemplate.delete(department);
    }

    @Override
    public List<Department> findAll() {
        return (List<Department>) hibernateTemplate.find("from Department");
    }


    // 分页查询
    @Override
    public List<Department> findByPage(int begin, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
        List<Department> list = (List<Department>) hibernateTemplate.findByCriteria(criteria,begin,pageSize);

        return list;
    }


}
