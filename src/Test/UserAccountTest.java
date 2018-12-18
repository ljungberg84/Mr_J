import model.UserAccount;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {

    private UserAccount account = new UserAccount("kalle", "hemligt");

    @Test
    public void hasLogin() {
        assertTrue(account.hasLogin());
    }

    @Test
    public void getUserName() {
        assertEquals(account.getUserName(), "kalle");
    }

    @Test
    public void setUserName() {
        account.setUserName("Olle");
        assertEquals(account.getUserName(), "Olle");
    }

    @Test
    public void getPassword() {

        assertEquals(account.getPassword(), "hemligt");
    }

    @Test
    public void setPassword() {
        account.setPassword("vanligt");
        assertEquals(account.getPassword(), "vanligt");
    }
}