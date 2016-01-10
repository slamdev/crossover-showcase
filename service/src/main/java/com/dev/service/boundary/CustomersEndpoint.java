package com.dev.service.boundary;

import static com.dev.service.control.StringUtil.firstNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.model.CustomerDetails;
import com.dev.rest.model.CustomerRow;
import com.dev.rest.model.Reference;
import com.dev.service.entity.Customer;

@RestController
@RequestMapping("customers")
public class CustomersEndpoint extends BaseEndpoint<Customer, CustomerDetails, CustomerRow> {

    @Autowired
    private CustomerRepository customers;

    @Autowired
    private OrderService service;

    @Override
    protected CustomerDetails convertToDetails(Customer entity) {
        CustomerDetails details = new CustomerDetails();
        details.setCode(entity.getCode());
        details.setAddress(entity.getAddress());
        details.setCreditLmit(entity.getCreditLmit());
        details.setCurrentCredit(service.calculateCredit(entity));
        details.setName(entity.getName());
        details.setPhone1(entity.getPhone1());
        details.setPhone2(entity.getPhone2());
        return details;
    }

    @Override
    protected Customer convertToEntity(CustomerDetails details) {
        Customer entity = new Customer();
        entity.setCode(details.getCode());
        entity.setAddress(details.getAddress());
        entity.setCreditLmit(details.getCreditLmit());
        entity.setName(details.getName());
        entity.setPhone1(details.getPhone1());
        entity.setPhone2(details.getPhone2());
        return entity;
    }

    @Override
    protected CustomerRow convertToRow(Customer entity) {
        CustomerRow row = new CustomerRow();
        row.setCode(entity.getCode());
        row.setName(entity.getName());
        row.setPhone(firstNotEmpty(entity.getPhone1(), entity.getPhone2()));
        row.setCurrentCredit(service.calculateCredit(entity));
        return row;
    }

    @Override
    protected JpaRepository<Customer, String> getRepository() {
        return customers;
    }

    @Override
    protected Reference reference(Customer entity) {
        return new Reference(entity.getCode(), entity.getName());
    }
}