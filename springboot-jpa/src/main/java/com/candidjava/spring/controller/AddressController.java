package com.candidjava.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.Address;
import com.candidjava.spring.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "FER")
@RequestMapping(value = { "/address" })

public class AddressController {

	@Autowired
	AddressService addressService;

	@ApiOperation(value = "getAddressById")
	@GetMapping(value = "/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getAddressById(@PathVariable("addressId") long addressId) {
		System.out.println("Fetching User with expenseId " + addressId);
		Address address = addressService.findByaddressId(addressId);
		if (address == null) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

	@ApiOperation(value = "AddAddress")
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createAddress(@RequestBody Address address, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Address " + address.getAddressLine1());
		addressService.createAddress(address);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/address/{addressId}").buildAndExpand(address.getAddressId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "getAddresses")
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Address> getAllAddress() {
		List<Address> tasks = addressService.getAddreses();
		return tasks;

	}

	@ApiOperation(value = "DeleteAddress")
	@DeleteMapping(value = "/{addressId}", headers = "Accept=application/json")
	public ResponseEntity<Address> deleteAddress(@PathVariable("addressId") long addressId) {
		Address address = addressService.findByaddressId(addressId);
		if (address == null) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		addressService.deleteAddressByaddressId(addressId);
		return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "updateAddress")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateAddress(@RequestBody Address currentAddress) {
		System.out.println("sd");
		Address address = addressService.findByaddressId(currentAddress.getAddressId());
		if (address == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		addressService.update(currentAddress, currentAddress.getAddressId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
