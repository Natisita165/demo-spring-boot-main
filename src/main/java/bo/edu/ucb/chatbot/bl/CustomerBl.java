package bo.edu.ucb.chatbot.bl;


import bo.edu.ucb.chatbot.dao.*;
import bo.edu.ucb.chatbot.dto.*;
import bo.edu.ucb.chatbot.exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class CustomerBl {

    private CustomerDao customerDao;
    private InventoryDao inventoryDao;
    private TransaccionDao transaccionDao;
    private RentalDao rentalDao;
    private PaymentDao paymentDao;

    @Autowired
    public CustomerBl(PaymentDao paymentDao, CustomerDao customerDao, InventoryDao inventoryDao, TransaccionDao transaccionDao, RentalDao rentalDao) {
        this.paymentDao = paymentDao;
        this.customerDao = customerDao;
        this.inventoryDao = inventoryDao;
        this.transaccionDao = transaccionDao;
        this.rentalDao = rentalDao;
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

    public RentPayInv postNewRent(RentPayInv rentPayInv) {
        Inventory inventory = new Inventory();
        inventory.setFilm_id(rentPayInv.getFilm_id());
        inventory.setStore_id(rentPayInv.getStore_id());
        inventoryDao.postInventory(inventory);

        Rental rental = new Rental();
        rental.setReturn_date(rentPayInv.getReturn_date());
        rental.setCustomer_id(rentPayInv.getCustomer_id());
        rental.setStaff_id(rentPayInv.getStaff_id());
        rental.setInventory_id(transaccionDao.getLastInsertId());
        rentalDao.postRental(rental);


        Payment payment = new Payment();
        payment.setCustomer_id(rentPayInv.getCustomer_id());
        payment.setStaff_id(rentPayInv.getStaff_id());
        payment.setRental_id(transaccionDao.getLastInsertId());
        payment.setAmount(rentPayInv.getAmount());
        paymentDao.postPayment(payment);


        return rentPayInv;

    }

    public Rental postRental(Rental rental) {
        Integer idFilm=rental.getRental_id();
        rental.setInventory_id(inventoryDao.getInventoryIdFilm(idFilm));
        rentalDao.postRental(rental);
        rental.setRental_id(transaccionDao.getLastInsertId());
        return rental;
    }
}

