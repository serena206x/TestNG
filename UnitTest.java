package com.wincode.test;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UnitTest {
    @BeforeClass
    public void beforeClass() {
        System.out.println("开始前执行一次");
    }

    public static class NormalClassA {
        public int testAdd(int a, int b) {
            return a + b;
        }
    }

    @Test(priority = 1, enabled = true)
    public void testAdd() {
        NormalClassA classA = new NormalClassA();
        Assert.assertEquals(5, classA.testAdd(1, 4));
    }


    @Test(enabled = false)
    public void divisionWithException() {
        System.out.println("Method is not ready yet");
    }


//    @RunWith(UnitTest.class)

    /*******参数化******/
    public static class ParameterTest {
        int expected = 0;
        int a = 0;
        int b = 0;


        @Parameters
        public static Collection<Object[]> t() {
            return Arrays.asList(new Object[][]{
                    {3, 2, 1},
                    {4, 2, 2}
            });
        }

        public ParameterTest() {
            this.expected = expected;
            this.a = a;
            this.b = b;
        }

        @Test(enabled = false)
        public void testAdd2() {
            NormalClassA classA = new NormalClassA();
            Assert.assertEquals(expected, new ParameterTest());
        }
    }
    @DataProvider(name = "user")
    public Object [][] createUser(Method m) {
        System.out.println(m.getName());
        return new Object[][] {
                {"root", "root"},
                {"test", "root"},
        };
    }

//    @Test(groups = "login", dependsOnGroups = "launch", dataProvider = "user")
@Test(dataProvider = "user")
    public void verifyUser(String username, String password) {
        System.out.println("Verify User : " + username + ":" + password);
        assert username.equals(password);
    }

    @Test
    public void case2() {
        int a = 2;
        int b = 3;
        int sum = a + b;
        Assert.assertEquals(sum, 5, "Result Error");
        System.out.println("case2");
    }

    /**************时间测试：表示如果单元测试所花费的时间超过指定的毫秒数，则测试将会终止，并将其标记为失败******************/
    @Test(timeOut = 1000)
    public void infinity() {
        while (true) ;
    }


    /**************分组测试******************/

    @Test(groups = "method1")
    public void testingMethod1() {
        System.out.println("Method - testingMethod1()");
    }

    @Test(groups = "method2")
    public void testingMethod2() {
        System.out.println("Method - testingMethod2()");
    }


    @Test
    public void method1() {
        System.out.println("This is method 1");
    }

    @Test(dependsOnMethods = {"method1"})
    public void method2() {
        System.out.println("This is method 2");
    }


    @Test
    @Parameters(value = "number")
    public void parameterIntTest(int number) {
        System.out.println("Parameterized Number is : " + number);
    }

    /****dependOnMethods****/
    @Test
    public void assertNotEqual() {
        int a = 1;
        int b = 2;
        Assert.assertNotEquals(a, b, "不相等");
        Assert.assertNotNull(a);
    }

    @Test(enabled = true)
    @Parameters({"myName"})
    public void parameterTest(String myName) {
     System.out.println("Parameterized value is : " + myName);
    }


    //测试结束时执行一次
    @AfterClass
    public void afterClass() {
        System.out.println("结束时执行一次");
    }

}
