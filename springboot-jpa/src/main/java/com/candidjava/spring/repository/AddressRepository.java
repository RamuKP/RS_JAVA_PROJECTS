package com.candidjava.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
