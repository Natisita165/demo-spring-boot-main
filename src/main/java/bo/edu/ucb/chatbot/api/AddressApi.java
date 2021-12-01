package bo.edu.ucb.chatbot.api;


import bo.edu.ucb.chatbot.bl.AddressBl;
import bo.edu.ucb.chatbot.dto.Address;
import bo.edu.ucb.chatbot.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/address")
public class AddressApi {
    AddressBl addressBl;

    @Autowired
    public AddressApi(AddressBl addressBl) {
        this.addressBl = addressBl;
    }

    @RequestMapping(path = "/post",method = RequestMethod.POST)
    public Address postAddress(@RequestBody Address address){
        Address addressCorr = addressBl.postAddress(address);
        return addressCorr;
    }

    @RequestMapping(path = "/put",method = RequestMethod.PUT)
    public Integer putAddress(@RequestBody Integer address){
        Integer addressAdd = addressBl.putAddress(address);
        return addressAdd;
    }

}
