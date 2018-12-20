package cn.itcast.dao.impl;

import cn.itcast.bean.Department;
import cn.itcast.bean.Employee;
import cn.itcast.dao.EmployeeDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * 员工管理的DAO的实现
 */
public class EmployeeDaoImpl implements EmployeeDao {

	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// 增删改查

	 //根据用户名和密码查询用户
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql = "from Employee where username = ? and password = ?";

		List<Employee> list = (List<Employee>) this.hibernateTemplate.find(hql, employee.getUsername(), employee.getPassword());
		if (list.size() > 0) {
			return  list.get(0);
		}else
			return null;

	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee ";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> list = (List<Employee>) hibernateTemplate.findByCriteria(criteria,begin,pageSize);
		return list;
	}

	@Override
	public void save(Employee employee) {
		hibernateTemplate.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		hibernateTemplate.delete(employee);
	}

	@Override
	public void update(Employee employee) {
		hibernateTemplate.update(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		Employee employee = hibernateTemplate.get(Employee.class, eid);
		return employee;
	}

}
