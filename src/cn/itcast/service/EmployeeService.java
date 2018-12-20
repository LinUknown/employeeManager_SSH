package cn.itcast.service;


import cn.itcast.bean.Employee;
import cn.itcast.bean.PageBean;

/**
 * Ա������ҵ���Ľӿ�
 */
public interface EmployeeService {

    void test();

    Employee login(Employee employee);


    PageBean<Employee> findAll(Integer currPage);

    void save(Employee employee);

    void delete(Employee employee);

    void update(Employee employee);

    Employee findById(Integer eid);
}
