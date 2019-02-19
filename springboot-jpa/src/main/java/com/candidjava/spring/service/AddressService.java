package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Address;

public interface AddressService {

	public void createAddress(Address address);

	public List<Address> getAddreses();

	public Address findByaddressId(long addressId);

	public Address update(Address address, Long l);

	public void deleteAddressByaddressId(long addressId);
	
}
