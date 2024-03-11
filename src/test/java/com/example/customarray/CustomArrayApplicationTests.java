package com.example.customarray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomArrayApplicationTests {

    private CustomArrayList<Integer> list;
    private CustomArrayList<String> stringList;
    private CustomArrayList<Double> doubleList;
    private CustomArrayList<Boolean> booleanList;

    @BeforeEach
    public void setUp() {
        list = new CustomArrayList<>();
        stringList = new CustomArrayList<>();
        doubleList = new CustomArrayList<>();
        booleanList = new CustomArrayList<>();
    }

    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.add(0, 1);
        list.add(1, 3);
        list.add(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort();
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSortWithComparator() {
        list.add(3);
        list.add(1);
        list.add(2);
        Comparator<Integer> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(2));
    }


    @Test
    public void testAddAndGetStrings() {
        stringList.add("Hello");
        stringList.add("World");
        assertEquals("Hello", stringList.get(0));
        assertEquals("World", stringList.get(1));
    }

    @Test
    public void testAddAndGetDoubles() {
        doubleList.add(3.14);
        doubleList.add(2.71);
        assertEquals(3.14, doubleList.get(0));
        assertEquals(2.71, doubleList.get(1));
    }

    @Test
    public void testAddAndGetBooleans() {
        booleanList.add(true);
        booleanList.add(false);
        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
    }

    @Test
    public void testSortStrings() {
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("orange");
        stringList.sort();
        assertEquals("apple", stringList.get(0));
        assertEquals("banana", stringList.get(1));
        assertEquals("orange", stringList.get(2));
    }

    @Test
    public void testSortDoubles() {
        doubleList.add(3.14);
        doubleList.add(2.71);
        doubleList.add(1.0);
        Comparator<Double> comparator = Comparator.reverseOrder();
        doubleList.sort(comparator);
        assertEquals(3.14, doubleList.get(0));
        assertEquals(2.71, doubleList.get(1));
        assertEquals(1.0, doubleList.get(2));
    }


    @Test
    public void testSortCarsByYear() {
        CustomArrayList<Car> cars = new CustomArrayList<>();
        cars.add(new Car(4, 2010, "sedan"));
        cars.add(new Car(4, 2005, "hatchback"));
        cars.add(new Car(4, 2015, "suv"));

        Comparator<Car> comparator = Comparator.comparingInt(Car::getYear);
        cars.sort(comparator);

        assertEquals(2005, cars.get(0).getYear());
        assertEquals(2010, cars.get(1).getYear());
        assertEquals(2015, cars.get(2).getYear());
    }
}
