package cn.itcast.action;

import cn.itcast.bean.Department;
import cn.itcast.bean.PageBean;
import cn.itcast.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {
    Department department = new Department();
    @Override
    public Department getModel() {
        return department;
    }
    private Integer currPage = 1;
    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }


    private DepartmentService departmentService;
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 提供一个查询的方法
    public String findAll() {
        PageBean<Department> pageBean = departmentService.findByPage(currPage);

        // 将pageBean存入值栈
        ActionContext.getContext().getValueStack().push(pageBean);

        return "findAll";
    }

    // 跳转到添加部分的页面
    public String saveUI() {
        return "saveUI";
    }

    // 添加部门的处理
    public String add() {

        System.out.println("department add ...");
        departmentService.save(department);
        return "saveSuccess";
    }

    // 点击编辑后，查询出所点的部门，通过模型驱动传递到department
    public String edit() {

        department = departmentService.findById(department.getDid());
        return "editSuccess";
    }

    // 更新数据库

    public String update() {
//        System.out.println("new departMent: " + department.getDdesc());
        departmentService.update(department);
        return "updateSuccess";
    }

    // 删除数据
    public String delete() {

        departmentService.delete(department);
        return "deleteSuccess";
    }

}
