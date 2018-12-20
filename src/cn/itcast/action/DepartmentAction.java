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

    // �ṩһ����ѯ�ķ���
    public String findAll() {
        PageBean<Department> pageBean = departmentService.findByPage(currPage);

        // ��pageBean����ֵջ
        ActionContext.getContext().getValueStack().push(pageBean);

        return "findAll";
    }

    // ��ת����Ӳ��ֵ�ҳ��
    public String saveUI() {
        return "saveUI";
    }

    // ��Ӳ��ŵĴ���
    public String add() {

        System.out.println("department add ...");
        departmentService.save(department);
        return "saveSuccess";
    }

    // ����༭�󣬲�ѯ������Ĳ��ţ�ͨ��ģ���������ݵ�department
    public String edit() {

        department = departmentService.findById(department.getDid());
        return "editSuccess";
    }

    // �������ݿ�

    public String update() {
//        System.out.println("new departMent: " + department.getDdesc());
        departmentService.update(department);
        return "updateSuccess";
    }

    // ɾ������
    public String delete() {

        departmentService.delete(department);
        return "deleteSuccess";
    }

}
