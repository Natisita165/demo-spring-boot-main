package bo.edu.ucb.chatbot.api;
import bo.edu.ucb.chatbot.bl.CustomerBl;
import bo.edu.ucb.chatbot.dto.Address;
import bo.edu.ucb.chatbot.dto.Customer;
import bo.edu.ucb.chatbot.dto.RentPayInv;
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
    @RequestMapping(path = "/getEmail",method = RequestMethod.GET)
    public String getEmailCustomer(@RequestParam String nombre, @RequestParam String appel){
        String customerAct = customerBl.getEmailCustomer(nombre, appel);
        return customerAct;
    }
    @RequestMapping(path = "/getAddress",method = RequestMethod.GET)
    public Address getAddressCustomer(@RequestParam Integer customer){
        Address customerAct = customerBl.getAddressCustomer(customer);
        return customerAct;
    }

    @RequestMapping(path = "/postNewRent",method = RequestMethod.POST)
    public RentPayInv postNewRent(@RequestBody RentPayInv rentPayInv){
        RentPayInv customerAddRent = customerBl.postNewRent(rentPayInv);
        return customerAddRent;
    }

}
