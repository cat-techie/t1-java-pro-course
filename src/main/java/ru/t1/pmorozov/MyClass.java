package ru.t1.pmorozov;

import ru.t1.pmorozov.annotations.*;

public class MyClass {
    @BeforeSuite
    public static void myMethodBeforeSuite(String value) {
        System.out.println("Это текст из @BeforeSuite: " + value);
    }

    @BeforeTest
    public void myMethodBeforeTest1() {
        System.out.println("Это текст из @BeforeTest 1");
    }

    @BeforeTest
    public void myMethodBeforeTest2() {
        System.out.println("Это текст из @BeforeTest 2");
    }

    @AfterTest
    public void myMethodAfterTest() {
        System.out.println("Это текст из @AfterTest");
    }

    @Test(priority = 1)
    public void myMethodTest1() {
        System.out.println("Это текст из @Test с priority = 1");
    }

    @Test
    public void myMethodTest5() {
        System.out.println("Это текст из @Test с priority = 5");
    }

    @Test(priority = 10)
    public void myMethodTest10() {
        System.out.println("Это текст из @Test с priority = 10");
    }

    @AfterSuite
    public static void myMethodAfterSuite(int value) {
        System.out.println("Это число из @AfterSuite: " + value);
    }

    @Test
    @CsvSource(source = "21, John, true")
    public void myMethodCsvSource(int age, String name, boolean isIll) {
        System.out.printf("Это текст из @ScvSource | Patient card: age - %s, name - %s, currently sick: %s%n", age, name, isIll);
    }
}
