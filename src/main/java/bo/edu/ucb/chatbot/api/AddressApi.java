package bo.edu.ucb.chatbot.api;


import bo.edu.ucb.chatbot.bl.AddressBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/address")
public class AddressApi {
    AddressBl addressBl;

    @Autowired
    public AddressApi(AddressBl addressBl) {
        this.addressBl = addressBl;
    }
}
