package bo.edu.ucb.chatbot.bl;


import bo.edu.ucb.chatbot.dao.AddressDao;
import bo.edu.ucb.chatbot.dto.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressBl {
    private AddressDao addressDao;

    @Autowired
    public AddressBl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    public Address postAddress(Address address) {

        return addressDao.postAddress(address);
    }

    public Integer putAddress(Integer address) {

        return addressDao.putAddress(address);
    }

}
