package com.dev.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.service.boundary.CustomerRepository;
import com.dev.service.boundary.ProductRepository;
import com.dev.service.boundary.SalesOrderRepository;
import com.dev.service.entity.Customer;
import com.dev.service.entity.OrderedProduct;
import com.dev.service.entity.Product;
import com.dev.service.entity.SalesOrder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @PostConstruct
    public void initData() {
        List<Product> products = new ArrayList<>();
        //
        Product p1 = new Product();
        p1.setCode("dog");
        p1.setDescirption("strong dog");
        p1.setPrice(10);
        p1.setQuantity(4);
        products.add(p1);
        //
        Product p2 = new Product();
        p2.setCode("cat");
        p2.setDescirption("small dog");
        p2.setPrice(5.5f);
        p2.setQuantity(8);
        products.add(p2);
        //
        Product p3 = new Product();
        p3.setCode("toy");
        p3.setDescirption("big toy");
        p3.setPrice(1);
        p3.setQuantity(50);
        products.add(p3);
        //
        Product p4 = new Product();
        p4.setCode("milk");
        p4.setDescirption("white milk");
        p4.setPrice(2);
        p4.setQuantity(40);
        products.add(p4);
        //
        productRepository.save(products);
        ///
        List<Customer> customers = new ArrayList<>();
        //
        Customer c1 = new Customer();
        c1.setAddress("USA");
        c1.setCode("slam");
        c1.setCreditLmit(50);
        c1.setName("Slam");
        c1.setPhone1("+79279829722");
        c1.setPhone2("546768");
        customers.add(c1);
        //
        Customer c2 = new Customer();
        c2.setAddress("Russia");
        c2.setCode("ilya");
        c2.setCreditLmit(45);
        c2.setName("Ilya");
        c2.setPhone1("+79170245459");
        c2.setPhone2("217200");
        customers.add(c2);
        //
        customerRepository.save(customers);
        ///
        List<SalesOrder> orders = new ArrayList<>();
        //
        SalesOrder o1 = new SalesOrder();
        o1.setCustomer(c1);
        o1.setCode("1");
        Set<OrderedProduct> ops1 = new HashSet<>();
        OrderedProduct op1 = new OrderedProduct();
        op1.setProduct(p1);
        op1.setQuantity(2);
        ops1.add(op1);
        OrderedProduct op2 = new OrderedProduct();
        op2.setProduct(p2);
        op2.setQuantity(4);
        ops1.add(op2);
        o1.setOrderedProducts(ops1);
        orders.add(o1);
        //
        SalesOrder o2 = new SalesOrder();
        o2.setCustomer(c2);
        o2.setCode("2");
        Set<OrderedProduct> ops2 = new HashSet<>();
        OrderedProduct op3 = new OrderedProduct();
        op3.setProduct(p3);
        op3.setQuantity(1);
        ops2.add(op3);
        OrderedProduct op4 = new OrderedProduct();
        op4.setProduct(p4);
        op4.setQuantity(15);
        ops2.add(op4);
        o2.setOrderedProducts(ops2);
        orders.add(o2);
        //
        salesOrderRepository.save(orders);
    }
}
