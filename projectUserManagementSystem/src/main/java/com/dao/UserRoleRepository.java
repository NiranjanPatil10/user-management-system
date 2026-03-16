package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.UserRole;



public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
