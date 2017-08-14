package learn.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import learn.springboot.domain.Employee;
import learn.springboot.dto.EmployeeDTO;
import learn.springboot.mapper.EmployeeMapper;
import learn.springboot.repository.mongo.EmployeeMongoRepository;
import learn.springboot.repository.primary.EmployeePrimaryRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeePrimaryRepository repository;
	
	@Autowired
	private EmployeeMongoRepository mongoRepository;
	
	@Autowired
	private EmployeeMapper mapper;

	public EmployeeDTO findOne(Long id) {
		Employee employee = repository.findOne(id);
		EmployeeDTO dto = mapper.map(employee);
		return dto;
	}

	public List<EmployeeDTO> findAll() {
		List<Employee> employees = repository.findAll();
		List<EmployeeDTO> dtos = mapper.map(employees);
		return dtos;
	}
	
	public Page<EmployeeDTO> findAll(Pageable pageable){
		Page<Employee> employees = repository.findAll(pageable);
		Page<EmployeeDTO> dtos = mapper.map(employees);
		return dtos;
	}

	public EmployeeDTO save(EmployeeDTO dto) {
		Employee employee = mapper.map(dto);
		Employee resultEmployee = repository.save(employee);
		mongoRepository.save(resultEmployee);
		EmployeeDTO resultDto = mapper.map(resultEmployee);
		return resultDto;
	}

	public EmployeeDTO update(EmployeeDTO dto) {
		if (null != findOne(dto.getId())) {
			Employee employee = mapper.map(dto);
			Employee resultEmployee = repository.save(employee);
			mongoRepository.save(employee);
			EmployeeDTO resultDto = mapper.map(resultEmployee);
			return resultDto;
		}
		return null;
	}
	
	public void delete(Long id) {
		repository.delete(id);
		mongoRepository.delete(id);
	}
	
	public void deleteAll() {
		repository.deleteAll();
		mongoRepository.deleteAll();
	}
}
