package uyutov.flower_shop.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uyutov.flower_shop.dao.CustomerDao;
import uyutov.flower_shop.dao.StuffDao;
import uyutov.flower_shop.models.users.Admin;
import uyutov.flower_shop.models.users.Customer;
import uyutov.flower_shop.models.users.Packer;
import uyutov.flower_shop.models.users.Provider;
import uyutov.flower_shop.security.AdminDetails;
import uyutov.flower_shop.security.CustomerDetails;
import uyutov.flower_shop.security.PackerDetails;
import uyutov.flower_shop.security.ProviderDetails;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {
    private final CustomerDao customerDao;
    private final StuffDao stuffDao;
    public UsersDetailsService(CustomerDao customerDao, StuffDao stuffDao) {
        this.customerDao = customerDao;
        this.stuffDao = stuffDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = stuffDao.getAdminByPhoneNumber(username);
        if(admin.isPresent())
        {
            return new AdminDetails(admin.get());
        }
        Optional<Provider> provider = stuffDao.getProviderByPhoneNumber(username);
        if(provider.isPresent()){
            return new ProviderDetails(provider.get());
        }
        Optional<Packer> packer = stuffDao.getPackerByPhoneNumber(username);
        if(packer.isPresent()){
            return new PackerDetails(packer.get());
        }
        Optional<Customer> customer = customerDao.getCustomerByPhoneNumber(username);
        if(customer.isEmpty())
        {
            throw new UsernameNotFoundException("Phone number is incorrect");
        }
        return new CustomerDetails(customer.get());
    }
}
