//  Это класс-тест

package lesson7;

public class Main {
    @BeforeSuite
    static void beforesuite() {
        System.out.println("Now running BeforeSuite");
    }

    @Test (priority = 7)
    static void test1() {
        System.out.println("Now running Test1");
    }

    @Test (priority = 8)
    static void test2() {
        System.out.println("Now running Test2");
    }

    @Test (priority = 9)
    static void test3() {
        System.out.println("Now running Test3");
    }

    @Test (priority = 10)
    static void test4() {
        System.out.println("Now running Test4");
    }

    @AfterSuite
    static void aftersuite() {
        System.out.println("Now running AfterSuite");
    }
}
