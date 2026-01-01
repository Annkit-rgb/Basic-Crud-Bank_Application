package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

  public  CustomerService(CustomerRepository customerRepository){
      this.customerRepository=customerRepository;
  }


  public Customer createCustomer(Customer customer){
      return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers(){
      return customerRepository.findAll();
  }

  public Optional<Customer> getCustomerById(Long id){
      return customerRepository.findById(id);

  }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {  // rename param
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedCustomer.getFirstName());  // add ()
                    existing.setLastName(updatedCustomer.getLastName());// add ()
                    existing.setEmail(updatedCustomer.getEmail());// add ()
                    existing.setPhoneNumber(updatedCustomer.getPhoneNumber());// add ()
                    return customerRepository.save(existing);                // ← ADD THIS LINE!
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id "+id));  // unwrap
    }

    public void deleteCustomer(Long id){
      if(!customerRepository.existsById(id)){
          throw new RuntimeException("Customer not found with id "+id);

      }

      customerRepository.deleteById(id);
    }




}
