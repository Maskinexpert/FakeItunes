package dk.experis.fakeitunes.data_access;

import dk.experis.fakeitunes.models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Phone, Country, PostalCode, Email FROM Customer");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Email")
                        ));
            }
        }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return customers;
    }

    public Boolean addCustomer(Customer customer){
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(CustomerId, FirstName, LastName, Phone, Country, PostalCode, Email) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,customer.getCustomerId());
            preparedStatement.setString(2,customer.getContactFirstName());
            preparedStatement.setString(3,customer.getContactLastName());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setString(5,customer.getCountry());
            preparedStatement.setString(6,customer.getPostalCode());
            preparedStatement.setString(7,customer.getEmail());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
        }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer){
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customer SET CustomerId = ?, FirstName = ?, LastName = ?, Phone = ?, Country = ?, PostalCode = ?, Email = ? WHERE CustomerId=?");
            preparedStatement.setString(1,customer.getCustomerId());
            preparedStatement.setString(2,customer.getContactFirstName());
            preparedStatement.setString(3,customer.getContactLastName());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setString(5,customer.getCountry());
            preparedStatement.setString(6,customer.getPostalCode());
            preparedStatement.setString(7,customer.getEmail());
            preparedStatement.setString(8,customer.getCustomerId());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
        }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return success;
    }

    public ArrayList<String> sortCountries() {
        ArrayList<String> countries = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Country, COUNT(*) as total FROM Customer GROUP BY Country ORDER BY 2 DESC");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(
                        resultSet.getString("Country") + ": " +
                        resultSet.getString("total")
                        );
            }
        }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return countries;
    }

    public ArrayList<String> highestSpenders () {
        ArrayList<String> highestSpenders = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, SUM(Total) as sumTotal FROM Invoice GROUP BY CustomerId ORDER BY 2 DESC");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                highestSpenders.add(
                        resultSet.getString("CustomerId") + " spend a total of: " +
                        resultSet.getString("sumTotal")

                );
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
        return highestSpenders;
    }

    public ArrayList<String> popularGenres (Customer customer) {
        ArrayList<String> popularGenres = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("WITH CountQuery AS (SELECT c.FirstName, c.LastName, g.Name, count(g.GenreId) as GenreCount\n" +
                            "    FROM Customer AS c\n" +
                            "    JOIN Invoice AS iv\n" +
                            "    ON iv.CustomerId = c.CustomerId\n" +
                            "    JOIN InvoiceLine AS il\n" +
                            "    ON il.InvoiceId = iv.InvoiceId\n" +
                            "    JOIN Track AS t\n" +
                            "    ON t.TrackId = il.TrackId\n" +
                            "    JOIN Genre AS g\n" +
                            "    ON g.GenreId = t.GenreId\n" +
                            "    WHERE c.CustomerId=?\n" +
                            "    GROUP BY g.GenreId\n" +
                            "    ORDER BY GenreCount)\n" +
                            "    SELECT Name, GenreCount\n" +
                            "    FROM CountQuery\n" +
                            "    WHERE (SELECT MAX(GenreCount) from CountQuery) = GenreCount");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                popularGenres.add(
                        resultSet.getString("g.Name") + ": " +
                        resultSet.getString("GenreCount")
                );
            }
        }
        catch (Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return popularGenres;
    }
}
