package com.EMS.NewEmployeeManagementSystem.repository;

import com.EMS.NewEmployeeManagementSystem.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {

}
