package com.manic.manic.security;

import com.manic.manic.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String number;

    public User toUser(PasswordEncoder encoder) {
        return new User(fullname, username, encoder.encode(password), state, city, street, zip, number);
    }
}
