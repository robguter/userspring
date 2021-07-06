package com.sisterag.springapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sisterag.springapp.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
