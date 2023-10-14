package collections;

import static org.junit.jupiter.api.Assertions.*;

import collections.dataStructures.HashTable;
import collections.interfaces.IHashtable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashTableTest {

    private IHashtable<String, Integer> hashtable;

    @BeforeEach
    void setUp() {
        hashtable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        // Arrange
        String key = "key";
        int value = 42;

        // Act
        hashtable.put(key, value);
        int retrievedValue = hashtable.get(key);

        // Assert
        assertEquals(value, retrievedValue);
    }

    @Test
    void testPutAndGetMultiple() {
        // Arrange
        String key1 = "key1";
        int value1 = 42;
        String key2 = "key2";
        int value2 = 123;

        // Act
        hashtable.put(key1, value1);
        hashtable.put(key2, value2);
        int retrievedValue1 = hashtable.get(key1);
        int retrievedValue2 = hashtable.get(key2);

        // Assert
        assertEquals(value1, retrievedValue1);
        assertEquals(value2, retrievedValue2);
    }

    @Test
    void testRemove() {
        // Arrange
        IHashtable<String, Integer> hashtable = new HashTable<>();


        hashtable.put("key1", 1);

        // Act
        hashtable.remove("key1");

        // Assert
        assertFalse(hashtable.contains("key1"));
        assertNull(hashtable.get("key1"));
    }

    @Test
    void testRemoveNonExistentKey() {
        // Arrange
        String key = "nonExistentKey";

        // Act
        hashtable.remove(key);

        // Assert
        assertFalse(hashtable.contains(key));
        assertNull(hashtable.get(key));
    }



    @Test
    void testContains() {
        // Arrange
        String key = "key";
        int value = 42;

        // Act
        hashtable.put(key, value);
        boolean containsKey = hashtable.contains(key);

        // Assert
        assertTrue(containsKey);
    }

    @Test
    void testContainsNonExistentKey() {
        // Arrange
        String key = "nonExistentKey";

        // Act
        boolean containsKey = hashtable.contains(key);

        // Assert
        assertFalse(containsKey);
    }

    @Test
    void testIsEmpty() {
        // Act - Assert
        assertTrue(hashtable.isEmpty());
    }

    @Test
    void testIsEmptyAfterInsert() {
        // Arrange
        String key = "key";
        int value = 42;

        // Act
        hashtable.put(key, value);

        // Assert
        assertFalse(hashtable.isEmpty());
    }

    @Test
    void testSize() {
        // Arrange
        String key1 = "key1";
        int value1 = 42;
        String key2 = "key2";
        int value2 = 123;

        // Act
        hashtable.put(key1, value1);
        hashtable.put(key2, value2);
        int size = hashtable.size();

        // Assert
        assertEquals(2, size);
    }

    @Test
    void testSizeAfterInsertAndRemove() {
        // Arrange
        String key1 = "key1";
        int value1 = 42;
        String key2 = "key2";
        int value2 = 123;

        // Act
        hashtable.put(key1, value1);
        hashtable.put(key2, value2);
        hashtable.remove(key1);
        int size = hashtable.size();

        // Assert
        assertEquals(1, size);
    }


}
