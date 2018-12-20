package cn.itcast.action;


import cn.itcast.bean.Department;
import cn.itcast.bean.Employee;
import cn.itcast.bean.PageBean;
import cn.itcast.service.DepartmentService;
import cn.itcast.service.EmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 员工管理的Action类
 * 利用模型驱动接收页面的参数
 * 然后调用service层
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	//这里的employee会直接被页面传过来的值赋值
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}
	
	//注入业务层类
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	/**
	 * 登陆执行的方法
	 */
	public String login() {
		System.out.println("login执行了...");

		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			//登陆失败
			this.addActionError("用户名或者密码错误");
			return "input";
		}else {
			// 登陆成功
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return "success";
		}
	}

	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	// 分页查询，通过当前第几页，查询成PageBean
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findAll(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);

		return "findAllSuccess";
	}

	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String saveUI() {
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);

		return "saveUI";
	}

	// 员工添加功能的save.action
	public String add() {

		System.out.println("----- Emp   add --------");
		System.out.println("add's Employee " + employee.getSex());
		employeeService.save(employee);
		return "saveSuccess";
	}

	public String delete() {
		// 页面指定了要删除的eid，模型驱动封装
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}

	public String edit() {
		// 修改请求,跳转到edit界面
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		employee = employeeService.findById(employee.getEid());

		return "edit";
	}

	public String update() {
		// 处理更新请求
		employeeService.update(employee);
		return "updateSuccess";
	}

}
