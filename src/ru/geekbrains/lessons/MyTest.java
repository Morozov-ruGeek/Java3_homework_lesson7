package ru.geekbrains.lessons;

public class MyTest {
    @BeforeSuite
    public static void test1(){
        System.out.println("First Test BeforeSuite");
    }

    @Test
    public static void test3(){
        System.out.println("Third Test");
    }


    @Test (priority = 3)
    public static void test5(){
        System.out.println("Second Test priority = 3");
    }

    @Test (priority = 7)
    public static void test7(){
        System.out.println("Fourth Test priority = 8");
    }

    @Test
    public static void test6(){
        System.out.println("Sixth Test");
    }

    @AfterSuite
    public static void test8(){
        System.out.println("Fifth Test AfterSuite");
    }
}
