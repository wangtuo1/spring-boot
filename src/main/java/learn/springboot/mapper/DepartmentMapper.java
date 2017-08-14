package learn.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import learn.springboot.domain.Department;
import learn.springboot.dto.DepartmentDTO;

@Component
public class DepartmentMapper {
	public Department map(DepartmentDTO dto) {
		Department department=new Department();
		BeanUtils.copyProperties(dto, department);
		return department;
	}
	
	public DepartmentDTO map(Department department) {
		DepartmentDTO dto=new DepartmentDTO();
		BeanUtils.copyProperties(department, dto);
		return dto;
	}
	
	public List<DepartmentDTO> map(List<Department> departments){
		List<DepartmentDTO> dtos = new ArrayList<DepartmentDTO>(departments.size());
		for(Department department:departments) {
			dtos.add(map(department));
		}
		return dtos;
	}
	
	public Page<DepartmentDTO> map(Page<Department> departments){
		Page<DepartmentDTO> dtos = new PageImpl<DepartmentDTO>(map(departments.getContent()));
		BeanUtils.copyProperties(departments, dtos);
		return dtos;
	}
}
