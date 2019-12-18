package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	
}