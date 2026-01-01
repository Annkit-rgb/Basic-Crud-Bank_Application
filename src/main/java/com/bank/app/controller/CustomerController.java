package com.bank.app.controller;


import com.bank.app.model.Customer;
import com.bank.app.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomers());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomersById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());

    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomers(@PathVariable Long id,
                                                    @RequestBody Customer customer) {

        try{
            return ResponseEntity.ok(customerService.updateCustomer(id, customer));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }



    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteCustomers(@PathVariable Long id) {
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer Deleted!!!");
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }



}
