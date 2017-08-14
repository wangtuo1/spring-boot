package learn.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import learn.springboot.dto.EmployeeDTO;
import learn.springboot.service.EmployeeService;

@RestController
@Api
public class EmployeeResource {
	@Autowired
	private EmployeeService service;

	@GetMapping("/employees/{id}")
	public EmployeeDTO findOne(@PathVariable Long id) {
		return service.findOne(id);
	}

	@GetMapping("/employees")
	public List<EmployeeDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/employees/pageable")
	public Page<EmployeeDTO> findAll(@ApiParam Pageable pageable) {
		return service.findAll(pageable);
	}

	@PostMapping("/employees")
	public EmployeeDTO save(@RequestBody EmployeeDTO employee) {
		return service.save(employee);
	}

	@PutMapping("/employees")
	public EmployeeDTO update(@RequestBody EmployeeDTO employee) {
		return service.update(employee);
	}

	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@DeleteMapping("/employees")
	public void deleteAll() {
		service.deleteAll();
	}
}
