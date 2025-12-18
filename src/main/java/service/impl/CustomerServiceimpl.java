package service.impl;

import repository.CustomerRepository;
import repository.impl.CustomerRepositoryimpl;

public class CustomerServiceimpl {

    private CustomerRepository customerRepository = new CustomerRepositoryimpl();

    public void addCustomer(String id, String name, String contact, String email) {
        customerRepository.addCustomer(id,name,contact,email);
    }
}
