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
 * Ա�������Action��
 * ����ģ����������ҳ��Ĳ���
 * Ȼ�����service��
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	//�����employee��ֱ�ӱ�ҳ�洫������ֵ��ֵ
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}
	
	//ע��ҵ�����
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	/**
	 * ��½ִ�еķ���
	 */
	public String login() {
		System.out.println("loginִ����...");

		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			//��½ʧ��
			this.addActionError("�û��������������");
			return "input";
		}else {
			// ��½�ɹ�
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return "success";
		}
	}

	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	// ��ҳ��ѯ��ͨ����ǰ�ڼ�ҳ����ѯ��PageBean
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

	// Ա����ӹ��ܵ�save.action
	public String add() {

		System.out.println("----- Emp   add --------");
		System.out.println("add's Employee " + employee.getSex());
		employeeService.save(employee);
		return "saveSuccess";
	}

	public String delete() {
		// ҳ��ָ����Ҫɾ����eid��ģ��������װ
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}

	public String edit() {
		// �޸�����,��ת��edit����
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		employee = employeeService.findById(employee.getEid());

		return "edit";
	}

	public String update() {
		// �����������
		employeeService.update(employee);
		return "updateSuccess";
	}

}
