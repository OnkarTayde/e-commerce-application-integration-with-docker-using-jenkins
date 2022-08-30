package com.ecommerce.app.security.config;

import java.util.Arrays;
import java.util.List;

import com.ecommerce.app.security.model.DAOUser;
import com.ecommerce.app.security.model.UserDTO;
import com.ecommerce.app.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;


	@Autowired
	private PasswordEncoder bcryptEncoder;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles=null;

		DAOUser user = userDao.findByUsername(username);

//		if(username.equals(user.getUsername()))
//		{
//		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		return new User("admin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe",
//					roles);
//		}
//		else if(username.equals("user"))
//		{
//		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//		return new User("user", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2",
//					roles);
//		}
//		throw new UsernameNotFoundException("User not found with username: " + username);

		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}
	//for Db
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return userDao.save(newUser);
	}


}
