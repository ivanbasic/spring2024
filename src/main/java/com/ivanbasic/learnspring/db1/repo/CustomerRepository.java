package com.ivanbasic.learnspring.db1.repo;

import com.ivanbasic.learnspring.db1.model.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

}
