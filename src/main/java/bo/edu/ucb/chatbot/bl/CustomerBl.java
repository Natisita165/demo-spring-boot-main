package bo.edu.ucb.chatbot.bl;


import bo.edu.ucb.chatbot.dao.CustomerDao;
import bo.edu.ucb.chatbot.dto.Address;
import bo.edu.ucb.chatbot.dto.Customer;
import bo.edu.ucb.chatbot.dto.Film;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class CustomerBl {

    private CustomerDao customerDao;

    @Autowired
    public CustomerBl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer postCustomer(Customer customer) {


        return customerDao.postCustomer(customer);
    }


        public String getEmailCustomer(String nombre, String appel) {

            return customerDao.getEmailCustomer(nombre, appel);
    }
    public Address getAddressCustomer(Integer customer) {

        return customerDao.getAddressCustomer(customer);
    }
    }

