package tests.backend.users;

import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class UserTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/12/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
    private final static ID userId = new ID();
    private final User user;

    public UserTest() {
        String firstName = "Nathan";
        String lastName = "Lauer";
        user = new Client(firstName, lastName, userId);
    }

    @Test
    public void attributes() {
        assertTrue(user.isAClient());
        assertFalse(user.isAnAdmin());
        assertEquals("Nathan Lauer", user.toString());
    }

    @Test
    @Order(1)
    public void testSerialization() {
        // Passes as long as the object is successfully serialized
        try {
            FileOutputStream fos = new FileOutputStream("data/user.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(2)
    public void testDeserialization() {
        User readInUser = null;
        try {
            FileInputStream fis = new FileInputStream("data/user.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readInUser = (User)ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(user.isAClient());
        assertFalse(user.isAnAdmin());
        assertEquals("Nathan Lauer", user.toString());
        assertEquals(user.getID(), readInUser.getID());
    }
}
