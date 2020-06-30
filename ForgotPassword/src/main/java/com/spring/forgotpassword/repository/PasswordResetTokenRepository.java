package com.spring.forgotpassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.forgotpassword.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer>{
	 PasswordResetToken findByToken(String token);
}
