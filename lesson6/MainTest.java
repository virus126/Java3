package lesson6;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    Main main = new Main();

    // Tests for task1
    @Test
    public void test1_1() {
        Assert.assertArrayEquals(new int[] {1, 7}, main.task1(new int[] { 1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    public void test1_2() {
        Assert.assertArrayEquals(new int[] {2, 3, 7}, main.task1(new int[] { 4, 2, 3, 7}));
    }

    @Test
    public void test1_3() {
        Assert.assertArrayEquals(new int[] {}, main.task1(new int[] { 1, 2, 2, 3, 1, 7, 4}));
    }

    @Test(expected = RuntimeException.class)
    public void test1_4() {
        Assert.assertArrayEquals(new int[] {}, main.task1(new int[] { 1, 2, 2, 3, 1, 7}));
    }

    // Tests for task2
    @Test
    public void test2_1() {
        Assert.assertTrue(main.task2(new int[] {1, 1, 4, 4, 4, 1}));
    }

    @Test
    public void test2_2() {
        Assert.assertTrue(main.task2(new int[] {1, 4, 1, 4, 4}));
    }

    @Test
    public void test2_3() {
        Assert.assertFalse(main.task2(new int[] {1, 2, 3, 1}));
    }

    @Test
    public void test2_4() {
        Assert.assertFalse(main.task2(new int[] {1, 2, 4, 3}));
    }
}
