package com.asm.patuan.service.impl;

import com.asm.patuan.config.UserInfoDetails;
import com.asm.patuan.entity.Customer;
import com.asm.patuan.entity.Role;
import com.asm.patuan.repository.CustomerRepository;
import com.asm.patuan.request.CreateAccount;
import com.asm.patuan.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> user = repository.findByAccount(username);
        if (user.isPresent()) {
            session.setAttribute("id", user.get().getId());
            session.setAttribute("name", user.get().getAccount());
        }
        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }


    @Override
    public void createAccount(CreateAccount account) {
        Role idRole = Role.builder().id(8L).build();
        Customer customer = Customer.builder()
                .account(account.getAccount())
                .password(passwordEncoder.encode(account.getPassword()))
                .role(idRole)
                .build();
        repository.save(customer);
    }

}
