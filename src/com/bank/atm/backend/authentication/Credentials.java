package com.bank.atm.backend.authentication;

import java.util.UUID;

/**
 * Class Credentials
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Credentials {
    private final String username;
    private final String passwordHash;
    private final UUID userUUID;

    public Credentials(String username, String password, String confirmPassword, UUID userUUID) {
        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirmPassword must match!");
        }
        this.username = username;
        this.passwordHash = PasswordHash.hashPassword(password);
        this.userUUID = userUUID;
    }
}
