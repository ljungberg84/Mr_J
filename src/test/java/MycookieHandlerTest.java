import model.MyCookieHandler;
import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.fail;

public class MycookieHandlerTest {

    MyCookieHandler cookieHandler = new MyCookieHandler("test");

    @Test
    public void saveCookiesExceptionTest(){
        try{
            cookieHandler.saveCookies(null);
            fail("no exception thrown");
        }catch (Exception e){
            System.out.println("successfull exception thrown");
        }
    }

    @Test
    public void loadCookiesExceptionTest(){
        try{
            cookieHandler.loadCookies(null);
            fail("no exception thrown");
        }catch (Exception e){
            System.out.println("successfull exception thrown");
        }
    }
}
