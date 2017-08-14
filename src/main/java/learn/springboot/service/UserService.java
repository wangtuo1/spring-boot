package learn.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import learn.springboot.domain.User;
import learn.springboot.dto.UserDTO;
import learn.springboot.mapper.UserMapper;
import learn.springboot.repository.primary.UserPrimaryRepository;
import learn.springboot.repository.secondary.UserSecondaryRepository;
import learn.springboot.repository.tertiary.UserTertiaryRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserPrimaryRepository primaryRepository;

	@Autowired
	private UserSecondaryRepository secondaryRepository;

	@Autowired
	private UserTertiaryRepository tertiaryRepository;
	
	@Autowired
	private UserMapper mapper;

	public UserDTO findOne(Long id) {
		User user = primaryRepository.findOne(id);
		UserDTO dto = mapper.map(user);
		return dto;
	}

	public List<UserDTO> findAll() {
		List<User> users = primaryRepository.findAll();
		List<UserDTO> dtos = mapper.map(users);
		return dtos;
	}
	
	public Page<UserDTO> findAll(Pageable pageable){
		Page<User> users = primaryRepository.findAll(pageable);
		Page<UserDTO> dtos = mapper.map(users);
		return dtos;
	}

	public UserDTO save(UserDTO dto) {
		User user = mapper.map(dto);
		User resultUser = primaryRepository.save(user);
		secondaryRepository.save(user);
		tertiaryRepository.save(user);
		UserDTO resultDto = mapper.map(resultUser);
		return resultDto;
	}

	public UserDTO update(UserDTO dto) {
		UserDTO resultDto = null;
		Long id = dto.getId();
		if (null != findOne(id)) {
			User user = mapper.map(dto);
			User targetUser = primaryRepository.findOne(id);
			if(null != targetUser) {
				User resultUser = primaryRepository.save(user);
				resultDto = mapper.map(resultUser);
			}
			targetUser = secondaryRepository.findOne(id);
			if(null != targetUser) {
				secondaryRepository.save(user);
			}
			targetUser = tertiaryRepository.findOne(id);
			if(null != targetUser) {
				tertiaryRepository.save(user);
			}
			
		}
		return resultDto;
	}
	
	public void delete(Long id) {
		primaryRepository.delete(id);
		secondaryRepository.delete(id);
		tertiaryRepository.delete(id);
	}
	
	public void deleteAll() {
		primaryRepository.deleteAll();
		secondaryRepository.deleteAll();
		tertiaryRepository.deleteAll();
	}
}
