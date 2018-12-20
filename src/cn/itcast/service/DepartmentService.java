package cn.itcast.service;

import cn.itcast.bean.Department;
import cn.itcast.bean.PageBean;

import java.util.List;


public interface DepartmentService {
    PageBean<Department> findByPage(Integer currPage);

    void save(Department department);


    Department findById(Integer did);

    void update(Department department);


    void delete(Department department);

    List<Department> findAll();
}
