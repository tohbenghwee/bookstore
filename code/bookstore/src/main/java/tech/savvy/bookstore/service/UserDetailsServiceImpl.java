package tech.savvy.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.savvy.bookstore.mapper.UserMapper;
import tech.savvy.bookstore.model.User;
import tech.savvy.bookstore.repository.UserRepository;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
     private UserRepository userRepository;
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(username)
             .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
         return UserMapper.userToPrincipal(user);
     }
}
