package com.spring.forgotpassword.service;

public interface ISecurityUserService {
	String validatePasswordResetToken(long id, String token);
}
