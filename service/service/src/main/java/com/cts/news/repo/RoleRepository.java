package com.cts.news.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.news.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
