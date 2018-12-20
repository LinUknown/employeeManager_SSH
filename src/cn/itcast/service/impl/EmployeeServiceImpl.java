package cn.itcast.service.impl;


import cn.itcast.bean.Employee;
import cn.itcast.bean.PageBean;
import cn.itcast.dao.EmployeeDao;
import cn.itcast.service.EmployeeService;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Ա�������ʵ����
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//ע��DAO��Ķ���
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}


	@Override
	public void test() {
		System.out.println("employee test.....");
	}

	@Override
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	//Service���װ��PageBean
	@Override
	public PageBean<Employee> findAll(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
//		private  int currPage; // ��ǰҳ��
		pageBean.setCurrPage(currPage);
//		private int pageSize; // ÿҳ��ʾ�ļ�¼
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
//		private int totalCount; // �ܵļ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
//		private int totalPage; // ��ҳ�����ּ�ҳ��ʾ
		Double tc = Double.valueOf(totalCount);
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());

//		private List<T> list; //ÿҳ��ʾ��ITEM

		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public void save(Employee employee) {
			employeeDao.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}


}
