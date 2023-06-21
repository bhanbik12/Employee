package com.EMS.NewEmployeeManagementSystem.service;

import com.EMS.NewEmployeeManagementSystem.entity.Address;
import com.EMS.NewEmployeeManagementSystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
   public  AddressRepository addressRepository;

    public  Address saveAddress(Address address) {
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    public List<Address> getAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }


    public String deleteAddress(long id) {
         addressRepository.deleteById(id);
         return "Deleted sucessfully";
    }

    public Address updateAddress(Address address) {
        Optional<Address> address1 = addressRepository.findById(address.getId());
        Address address2 = null;
        if (address1.isPresent()){
            address2 =address1.get();
            address2.setCity(address2.getCity());
            address2.setCountry(address2.getCountry());
            address2.setStreetNo(address2.getStreetNo());
            address2.setPostCode(address.getPostCode());
        }
        addressRepository.save(address);
        return address2;
    }

    public Optional<Address> getAddressById(long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address;
    }
}
