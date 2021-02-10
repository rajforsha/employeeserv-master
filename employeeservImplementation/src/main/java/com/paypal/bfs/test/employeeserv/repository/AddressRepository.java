package com.paypal.bfs.test.employeeserv.repository;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
