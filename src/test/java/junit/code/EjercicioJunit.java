package junit.code;

import org.junit.*;

public class EjercicioJunit {
    @BeforeClass
    public static void test1() {
        System.out.println("BeforeClass");
    }
    @Before
    public void test2(){
        System.out.println("Before");
    }
    @Test
    public void test3() {
        System.out.println("Test");
    }
    @After
    public void test4(){
        System.out.println("After");
    }
    @AfterClass
    public static void test5(){
        System.out.println("AfterClass");
    }

}
