package tests.backend.authentication;

import com.bank.atm.backend.authentication.AuthenticationException;
import com.bank.atm.backend.authentication.Login;
import com.bank.atm.backend.authentication.Register;
import com.bank.atm.backend.collections.CollectionsUtil;
import com.bank.atm.backend.collections.CredentialsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.User;
import com.bank.atm.backend.users.UserType;
import com.bank.atm.util.ID;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class AuthenticationTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/14/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationTest {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String passwordConfirmation;

    public AuthenticationTest() {
        firstName = "Nathan";
        lastName = "Lauer";
        username = "lauernathan";
        password = "Password$123";
        passwordConfirmation = "Password$123";
    }

    @BeforeAll
    public static void init() {
        // Clear the output file
        CollectionsUtil.deleteFile(UsersCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(CredentialsCollectionManager.dataFileName);
    }

    @AfterAll
    public static void clear() {
        UsersCollectionManager.getInstance().clear();
        CredentialsCollectionManager.getInstance().clear();
    }

    @Test
    @Order(1)
    public void register() {
        Register register = new Register(firstName, lastName, username, password, passwordConfirmation, UserType.ADMIN);
        ID userId = null;
        try {
            userId = register.run();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            fail();
        }

        // We should now have a new user
        User user = UsersCollectionManager.getInstance().find(userId);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertTrue(user.isAnAdmin());
        assertFalse(user.isAClient());
        assertTrue(user.hasID(userId));
    }

    @Test
    @Order(2)
    public void mismatchingPasswordConfirmation() {
        Register register = new Register(firstName, lastName, username, password, "Doesn't Match", UserType.CLIENT);
        try {
            register.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Register.passwordAndConfirmationDoNotMatch, e.getMessage());
            // passed
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(3)
    public void noCapitals() {
        String badPassword = "password#";
        Register register = new Register(firstName, lastName, username, badPassword, badPassword, UserType.CLIENT);
        try {
            register.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Register.missingCapitalLetters, e.getMessage());
            // passed
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(4)
    public void tooShort() {
        String badPassword = "Pa!";
        Register register = new Register(firstName, lastName, username, badPassword, badPassword, UserType.CLIENT);
        try {
            register.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Register.passwordTooShort, e.getMessage());
            // passed
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(5)
    public void missingSpecialCharacters() {
        String badPassword = "Password";
        Register register = new Register(firstName, lastName, username, badPassword, badPassword, UserType.CLIENT);
        try {
            register.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Register.missingSpecialCharacters, e.getMessage());
            // passed
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(6)
    public void usernameAlreadyTaken() {
        String otherPassword = "Password#$52";
        Register register = new Register(firstName, lastName, username, otherPassword, otherPassword, UserType.CLIENT);
        try {
            register.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Register.usernameAlreadyTaken, e.getMessage());
            // passed
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(7)
    public void login() {
        Login login = new Login(username, password);
        ID userId = null;
        try {
            userId = login.run();
        } catch (AuthenticationException e) {
            fail();
        }

        // We should now be able to obtain the user in question
        User user = UsersCollectionManager.getInstance().find(userId);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertTrue(user.isAnAdmin());
        assertFalse(user.isAClient());
        assertTrue(user.hasID(userId));

        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(8)
    public void badUsername() {
        Login login = new Login("different", password);
        try {
            login.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Login.invalidCredentials, e.getMessage());
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }

    @Test
    @Order(9)
    public void badPassword() {
        Login login = new Login(username, "wrongPassword");
        try {
            login.run();
            fail();
        } catch (AuthenticationException e) {
            assertEquals(Login.invalidCredentials, e.getMessage());
        }
        assertEquals(1, UsersCollectionManager.getInstance().all().size());
        assertEquals(1, CredentialsCollectionManager.getInstance().all().size());
    }
}
