package com.asm.patuan.service;

import com.asm.patuan.request.CreateAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void createAccount(CreateAccount account);

}
