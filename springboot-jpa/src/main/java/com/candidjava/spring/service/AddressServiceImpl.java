package com.candidjava.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Address;
import com.candidjava.spring.repository.AddressRepository;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Override
	public void createAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public List<Address> getAddreses() {
		return (List<Address>) addressRepository.findAll();
	}

	@Override
	public Address findByaddressId(long addressId) {
		return addressRepository.findOne(addressId);
	}

	@Override
	public Address update(Address address, Long l) {
		return addressRepository.save(address);
	}

	@Override
	public void deleteAddressByaddressId(long addressId) {
		addressRepository.delete(addressId);

	}

}
