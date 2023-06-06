package junit.code;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Agregar los test independientes aquí
        LoginTest.class,
        InventaryTest.class,
        CartTest.class,
        CheckoutTest.class,
        LogoutTest.class
})
public class RunTest {


}
