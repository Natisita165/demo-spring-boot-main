package bo.edu.ucb.chatbot.bl;


import bo.edu.ucb.chatbot.dao.CustomerDao;
import bo.edu.ucb.chatbot.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Customer putCustomer(Customer customer) {
        return customerDao.putCustomer(customer);
    }
}
