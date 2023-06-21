package com.EMS.NewEmployeeManagementSystem.controlller;

import com.EMS.NewEmployeeManagementSystem.entity.Address;
import com.EMS.NewEmployeeManagementSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public Address saveAddress(@RequestBody Address address){
       Address saveAddress =  addressService.saveAddress(address);
       return  saveAddress;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddress (){
        List<Address> addressList = addressService.getAllAddress();
        return ResponseEntity.ok(addressList);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") int id){
        String deletedAddress = addressService.deleteAddress(id);
        return deletedAddress;
    }

    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address){
        Address address1 = addressService.updateAddress(address);
        return address1;
    }

    @GetMapping("/getbyid/{id}")
    public Optional<Address> getAddressById(@PathVariable("id") int id){
        Optional<Address> address = addressService.getAddressById(id);
        return address;
    }


}
