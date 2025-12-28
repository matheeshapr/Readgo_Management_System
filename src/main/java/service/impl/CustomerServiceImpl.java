package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;
import repository.CustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public void addCustomer(String id, String name, String contact, String email) {
        customerRepository.addCustomer(id, name, contact, email);
    }

    @Override
    public void updateCustomer(String id, String name, String contact, String email) {
        customerRepository.updateCustomer(id, name, contact, email);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomers() {
        ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = customerRepository.getAllCustomers();
            while (rs.next()) {
                list.add(new CustomerDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}