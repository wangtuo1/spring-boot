package learn.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import learn.springboot.domain.Department;
import learn.springboot.dto.DepartmentDTO;
import learn.springboot.mapper.DepartmentMapper;
import learn.springboot.repository.mongo.DepartmentMongoRepository;
import learn.springboot.repository.primary.DepartmentPrimaryRepository;

@Service
@Transactional
public class DepartmentService {
	@Autowired
	private DepartmentPrimaryRepository repository;
	
	@Autowired
	private DepartmentMongoRepository mongoRepository;
	
	@Autowired
	private DepartmentMapper mapper;

	public DepartmentDTO findOne(Long id) {
		Department department = repository.findOne(id);
		DepartmentDTO dto = mapper.map(department);
		return dto;
	}

	public List<DepartmentDTO> findAll() {
		List<Department> departments = repository.findAll();
		List<DepartmentDTO> dtos = mapper.map(departments);
		return dtos;
	}
	
	public Page<DepartmentDTO> findAll(Pageable pageable){
		Page<Department> departments = repository.findAll(pageable);
		Page<DepartmentDTO> dtos = mapper.map(departments);
		return dtos;
	}

	public DepartmentDTO save(DepartmentDTO dto) {
		Department department = mapper.map(dto);
		Department resultDepartment = repository.save(department);
		mongoRepository.save(resultDepartment);
		DepartmentDTO resultDto = mapper.map(resultDepartment);
		return resultDto;
	}

	public DepartmentDTO update(DepartmentDTO dto) {
		if (null != findOne(dto.getId())) {
			Department department = mapper.map(dto);
			Department resultDepartment = repository.save(department);
			mongoRepository.save(department);
			DepartmentDTO resultDto = mapper.map(resultDepartment);
			return resultDto;
		}
		return null;
	}
	
	public void delete(Long id) {
//		Department department = new Department();
//		department.setId(4L);
//		department.setName("d4");
//		repository.save(department);
//		mongoRepository.save(department);
		repository.delete(id);
		mongoRepository.delete(id);
	}
	
	public void deleteAll() {
		repository.deleteAll();
		mongoRepository.deleteAll();
	}
}
