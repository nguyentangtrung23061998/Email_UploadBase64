package com.spring.forgotpassword.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.forgotpassword.entity.PasswordResetToken;
import com.spring.forgotpassword.entity.User;
import com.spring.forgotpassword.repository.PasswordResetTokenRepository;
import com.spring.forgotpassword.service.ISecurityUserService;

import java.util.Arrays;

@Service
public class SecurityUserServiceImpl implements ISecurityUserService {
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	@Override
	public String validatePasswordResetToken(long id, String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}
		
		final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }
        
        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
	}

}
