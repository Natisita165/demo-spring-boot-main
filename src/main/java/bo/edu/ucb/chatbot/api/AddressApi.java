package bo.edu.ucb.chatbot.api;


import bo.edu.ucb.chatbot.bl.AddressBl;
import bo.edu.ucb.chatbot.dto.Address;
import bo.edu.ucb.chatbot.dto.Customer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Address putAddress(@RequestBody Address address){
        Address addressAdd = addressBl.putAddress(address);
        return addressAdd;
    }

}
