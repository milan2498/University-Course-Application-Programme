package com.cg.mts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.config.JwtTokenUtil;
import com.cg.mts.model.JwtRequest;
import com.cg.mts.model.JwtResponse;
import com.cg.mts.model.PasswordDTO;
import com.cg.mts.model.UserdetailsResponse;
import com.cg.mts.service.JwtUserDetailsService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtuserDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		
		final UserDetails userDetails = jwtuserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		jwtuserDetailsService.setLoggedIn(authenticationRequest.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
	public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordDTO passwordDTO) {
		String jwtToken = token.substring(7);
		String userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		if(jwtuserDetailsService.changePassword(userName,passwordDTO)) {
			return ResponseEntity.ok("Password changed successfully!");
		}
		else {
			return ResponseEntity.ok("Password change failed!");
		}
	}
	
	@GetMapping("/remove")
	public ResponseEntity<?> logOut(@RequestHeader("Authorization") String token) {
		
		String jwtToken = token.substring(7);
		String userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
		
		if(jwtuserDetailsService.logOut(userName)) {
			return new ResponseEntity<>("User Logout successful!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Please Login first!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getUserDetails")
	public ResponseEntity<?> getUserdetails(@RequestHeader("Authorization") String token) {
		
		UserdetailsResponse userDetails = jwtuserDetailsService.getUserDetailsFromToken(token);
		
		return ResponseEntity.ok(userDetails);
	}
	
	@GetMapping("/getRole")
	public ResponseEntity<?> getRole(@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(jwtuserDetailsService.getRoleFromToken(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
}