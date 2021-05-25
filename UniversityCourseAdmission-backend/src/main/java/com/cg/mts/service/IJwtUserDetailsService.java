package com.cg.mts.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cg.mts.entities.DAOUser;
import com.cg.mts.model.PasswordDTO;
import com.cg.mts.model.UserDTO;
import com.cg.mts.model.UserdetailsResponse;

public interface IJwtUserDetailsService {
	
	public DAOUser save(UserDTO user);
	
	public void setLoggedIn(String username);
	
	public boolean changePassword(String userName, PasswordDTO passwordDTO) throws UsernameNotFoundException;
	
	public boolean logOut(String userName);
	
	public String getUserNameToken(String token);
	
	public String getRoleFromToken(String token) throws UsernameNotFoundException;
	
	public UserdetailsResponse getUserDetailsFromToken(String token) throws UsernameNotFoundException;

	public UserdetailsResponse toUserDetailsResponse(DAOUser user);

}
