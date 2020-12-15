package com.bank.atm.backend.authentication;

import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.UserType;

import java.util.Arrays;

/**
 * Class DefaultAdmin is a class which represents some default admin, and gives the
 * bank owners a chance to register other admins behind the scenes.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class DefaultAdmin {
    /**
     * Checks to see if some default admin already exists. If not, creates ones
     */
    public static void run() {
        if(UsersCollectionManager.getInstance().defaultAdminExists()) {
            return;
        }

        // Otherwise, register a new User as an admin
        String username = "admin";
        String password = Arrays.toString("BankPass@611".toCharArray());
        Register register = new Register(username, username, username, password, password, UserType.ADMIN);
        try {
            register.run();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            // Shouldn't happen
        }
    }
}
