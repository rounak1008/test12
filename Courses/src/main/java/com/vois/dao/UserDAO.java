package com.vois.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vois.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{

	Optional<User> findByUserName(String username);

}
