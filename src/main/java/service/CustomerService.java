package service;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    void addCustomer(String id, String name, String contact, String email);

    void updateCustomer(String id, String name, String contact, String email);

    void deleteCustomer(String id);

    ObservableList<CustomerDTO> getAllCustomers();
}