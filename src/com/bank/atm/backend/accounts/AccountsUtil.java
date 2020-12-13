package com.bank.atm.backend.accounts;

import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class AccountsUtil provides some common helper methods for Accounts
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/12/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AccountsUtil {
    /**
     * Given a User, returns a list of Account managers that includes this User.
     * @param user the User to be a manager for some Account
     * @return list of IDs of Account managers that includes this User's ID.
     */
    public static List<ID> buildManagerListFromUser(User user) {
        return new ArrayList<>(Collections.singletonList(user.getID()));
    }
}
