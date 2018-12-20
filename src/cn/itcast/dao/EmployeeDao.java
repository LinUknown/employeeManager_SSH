package cn.itcast.dao;


import cn.itcast.bean.Employee;

import java.util.List;

/**
 * 员工管理的DAO的接口
 */
public interface EmployeeDao {

    Employee findByUsernameAndPassword(Employee employee);

    int findCount();

    List<Employee> findByPage(int begin, int pageSize);

    void save(Employee employee);

    void delete(Employee employee);

    void update(Employee employee);

    Employee findById(Integer eid);
}
