package uyutov.flower_shop.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uyutov.flower_shop.dao.CustomerDao;
import uyutov.flower_shop.dao.StuffDao;
import uyutov.flower_shop.models.users.Admin;
import uyutov.flower_shop.models.users.Customer;
import uyutov.flower_shop.models.users.Packer;
import uyutov.flower_shop.models.users.Provider;

@Service
public class RegistrationService {
    private final CustomerDao customerDao;
    private final StuffDao stuffDao;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public RegistrationService(CustomerDao customerDao, StuffDao stuffDao) {
        this.customerDao = customerDao;
        this.stuffDao = stuffDao;
    }

    public void registerCustomer(Customer customer)
    {
        customer.setPassword(encoder.encode(customer.getPassword()));
        customerDao.createCustomer(customer);
    }
    public void registerPacker(Packer packer)
    {
        packer.setPassword(encoder.encode(packer.getPassword()));
        stuffDao.createPacker(packer);
    }
    public void registerProvider(Provider provider)
    {
        provider.setPassword(encoder.encode(provider.getPassword()));
        stuffDao.createProvider(provider);
    }
}
