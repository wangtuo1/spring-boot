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
import learn.springboot.dto.DepartmentDTO;
import learn.springboot.service.DepartmentService;

@RestController
@Api
public class DepartmentResource {
	@Autowired
	private DepartmentService service;

	@GetMapping("/departments/{id}")
	public DepartmentDTO findOne(@PathVariable Long id) {
		return service.findOne(id);
	}

	@GetMapping("/departments")
	public List<DepartmentDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/departments/pageable")
	public Page<DepartmentDTO> findAll(@ApiParam Pageable pageable) {
		return service.findAll(pageable);
	}

	@PostMapping("/departments")
	public DepartmentDTO save(@RequestBody DepartmentDTO dto) {
		return service.save(dto);
	}

	@PutMapping("/departments")
	public DepartmentDTO update(@RequestBody DepartmentDTO dto) {
		return service.update(dto);
	}

	@DeleteMapping("/departments/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@DeleteMapping("/departments")
	public void deleteAll() {
		service.deleteAll();
	}
}
