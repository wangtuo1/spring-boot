package learn.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import learn.springboot.domain.Department;
import learn.springboot.domain.Employee;
import learn.springboot.dto.EmployeeDTO;

@Component
public class EmployeeMapper {
	public Employee map(EmployeeDTO dto) {
		Employee employee=new Employee();
		BeanUtils.copyProperties(dto, employee);
		Department department = new Department();
		department.setId(dto.getDepartmentId());
		employee.setDepartment(department);
		return employee;
	}
	
	public EmployeeDTO map(Employee employee) {
		EmployeeDTO dto=new EmployeeDTO();
		BeanUtils.copyProperties(employee, dto);
		dto.setDepartmentId(employee.getDepartment().getId());
		return dto;
	}
	
	public List<EmployeeDTO> map(List<Employee> employees){
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>(employees.size());
		for(Employee employee:employees) {
			dtos.add(map(employee));
		}
		return dtos;
	}
	
	public Page<EmployeeDTO> map(Page<Employee> employees){
		Page<EmployeeDTO> dtos = new PageImpl<EmployeeDTO>(map(employees.getContent()));
		BeanUtils.copyProperties(employees, dtos);
		return dtos;
	}
}
