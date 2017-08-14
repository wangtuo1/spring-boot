package learn.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import learn.springboot.domain.User;
import learn.springboot.dto.UserDTO;

@Component
public class UserMapper {
	public User map(UserDTO dto) {
		User user=new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
	public UserDTO map(User user) {
		UserDTO dto=new UserDTO();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	public List<UserDTO> map(List<User> users){
		List<UserDTO> dtos = new ArrayList<UserDTO>(users.size());
		for(User user:users) {
			dtos.add(map(user));
		}
		return dtos;
	}
	
	public Page<UserDTO> map(Page<User> users){
		Page<UserDTO> dtos = new PageImpl<UserDTO>(map(users.getContent()));
		BeanUtils.copyProperties(users, dtos);
		return dtos;
	}
}
