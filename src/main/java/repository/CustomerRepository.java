package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {
    void addCustomer(String id, String name, String contact, String email);

    void updateCustomer(String id, String name, String contact, String email);

    void deleteCustomer(String id);

    ResultSet getAllCustomers() throws SQLException;

    ResultSet searchCustomer(String id) throws SQLException;
}