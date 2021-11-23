package bo.edu.ucb.chatbot.api;
import bo.edu.ucb.chatbot.bl.CustomerBl;
import bo.edu.ucb.chatbot.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/customer")
public class CustumerApi {
    private static Logger LOGGER = LoggerFactory.getLogger(CustumerApi.class);
    CustomerBl customerBl;

    @Autowired
    public CustumerApi(CustomerBl customerBl) {
        this.customerBl = customerBl;
    }

    @RequestMapping(path = "/post",method = RequestMethod.POST)
    public Customer postCustomer(@RequestBody Customer customer){
        Customer customerAdd = customerBl.postCustomer(customer);
        return customerAdd;
    }
    @RequestMapping(path = "/put",method = RequestMethod.PUT)
    public Customer putCustomer(@RequestBody Customer customer){
        Customer customerAct = customerBl.putCustomer(customer);
        return customerAct;
    }

}
