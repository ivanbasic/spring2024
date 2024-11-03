package com.ivanbasic.learnspring.repository.db1;

import com.ivanbasic.learnspring.model.db1.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

}
