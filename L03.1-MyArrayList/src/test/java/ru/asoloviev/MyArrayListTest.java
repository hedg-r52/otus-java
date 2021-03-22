package ru.asoloviev;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void whenAddOneElementThenSizeEqualsOne() {
        MyArrayList<String> array = new MyArrayList<>();
        assertEquals(array.size(), 0);
        array.add("first");
        assertEquals(array.size(), 1);
    }

    @Test
    void whenDeleteStringSecondElementFromTwoElementsListThenSizeEqualsOne() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        assertEquals(array.size(), 2);
        assertTrue(array.remove("second"));
        assertEquals(array.size(), 1);
        assertEquals(array.get(0), "first");
    }

    @Test
    void whenClearListThenSizeEqualZero() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        assertFalse(array.isEmpty());
        array.clear();
        assertTrue(array.isEmpty());
        assertEquals(array.size(), 0);
    }

    @Test
    void whenListWithFirstAndSecondValueThenContainsForFirstMustReturnTrue() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        assertTrue(array.contains("first"));
    }

    @Test
    void whenListWithFirstAndSecondValueThenContainsForThirdMustReturnFalse() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        assertFalse(array.contains("third"));
    }

    @Test
    void whenGetIteratorListOfTwoValuesThenWillBeExecutedNextTwiceAndThenHasNextWasFalse() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("first");
        array.add("second");
        Iterator<String> iterator = array.iterator();
        assertEquals("first", iterator.next());
        assertEquals("second", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void whenTwoValuedListAddAllToAnotherTwoValuedListThenSizeMustEqualsFour() {
        MyArrayList<String> array1 = new MyArrayList<>();
        array1.add("first");
        array1.add("second");
        MyArrayList<String> array2 = new MyArrayList<>();
        array2.add("third");
        array2.add("fourth");
        assertTrue(array1.addAll(array2));
        assertEquals(array1.size(), 4);
    }

    @Test
    void whenGetUnsortedListAndApplySortThenListMustBeSorted() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("bbb");
        array.add("aaa");
        array.add("ddd");
        array.add("ccc");
        array.sort(String::compareTo);
        assertEquals(array.get(0), "aaa");
        assertEquals(array.get(1), "bbb");
        assertEquals(array.get(2), "ccc");
        assertEquals(array.get(3), "ddd");
    }

    @Test
    void whenCloneThenClonedListMustHaveSameSizeAndSameValues() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("aaa");
        array.add("bbb");

        MyArrayList<String> cloned = (MyArrayList<String>) array.clone();
        assertEquals(cloned.size(), 2);
        assertEquals(cloned.get(0), "aaa");
        assertEquals(cloned.get(1), "bbb");
    }
}