package cn.itcast.dao;

import cn.itcast.bean.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findByPage(int begin, int pageSize);

    int findCount();

    void save(Department department);

    Department findById(Integer did);


    void update(Department department);

    void delete(Department department);

    List<Department> findAll();
}
