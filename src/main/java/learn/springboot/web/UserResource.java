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
import learn.springboot.dto.UserDTO;
import learn.springboot.service.UserService;

@RestController
@Api
public class UserResource {
	@Autowired
	private UserService service;

	@GetMapping("/users/{id}")
	public UserDTO findOne(@PathVariable Long id) {
		return service.findOne(id);
	}

	@GetMapping("/users")
	public List<UserDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/users/pageable")
	public Page<UserDTO> findAll(@ApiParam Pageable pageable) {
		return service.findAll(pageable);
	}

	@PostMapping("/users")
	public UserDTO save(@RequestBody UserDTO dto) {
		return service.save(dto);
	}

	@PutMapping("/users")
	public UserDTO update(@RequestBody UserDTO dto) {
		return service.update(dto);
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@DeleteMapping("/users")
	public void deleteAll() {
		service.deleteAll();
	}
}
