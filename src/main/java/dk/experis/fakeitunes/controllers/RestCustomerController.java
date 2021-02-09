package dk.experis.fakeitunes.controllers;

import dk.experis.fakeitunes.data_access.CustomerRepository;
import dk.experis.fakeitunes.models.Customer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestCustomerController {
    private CustomerRepository crep = new CustomerRepository();

    /*
     This first one just returns all the customers in the database
     it will return a CustomerShort object.
    */
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return crep.getAllCustomers();
    }

    /*
     This adds a new customer.
     It takes the new customer from the body of the request.
    */
    @RequestMapping(value = "api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return crep.addCustomer(customer);
    }

    /*
    This updates an existing customer.
    It takes the new customer data from the body of the request.
    As well as taking the Id of the customer from the path variables, this is
    to do a check if the body matches the id. Just a layer of saftey.
   */
    @RequestMapping(value = "api/customers/{id}", method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable String id, @RequestBody Customer customer){ return crep.updateCustomer(customer); }


    @RequestMapping(value = "api/customers/lists/countries", method = RequestMethod.GET)
    public ArrayList<String> sortCountriesByUsers() { return crep.sortCountries();}

    @RequestMapping(value = "api/customers/spenders", method = RequestMethod.GET)
    public ArrayList<String> getHighestSpenders() { return crep.highestSpenders(); }

    @RequestMapping(value = "api/customers/{id}/genre", method = RequestMethod.GET)
    public ArrayList<String> getPopularGenres(@PathVariable String id, Customer customer){ return crep.popularGenres(customer); }
}
