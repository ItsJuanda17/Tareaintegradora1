package collections.dataStructures;

import collections.interfaces.IHashtable;

public class HashTable<K, V> implements IHashtable<K, V> {
    private static final int TABLE_SIZE = 100;
    private HashNode<K, V>[] table;

    public HashTable() {
        table = new HashNode[TABLE_SIZE];
    }

    @Override
    public void put(K key, V value) {
        int index = customHash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            HashNode<K, V> current = table[index];
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    @Override
    public V get(K key) {
        int index = customHash(key);
        HashNode<K, V> current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = customHash(key);
        HashNode<K, V> current = table[index];
        HashNode<K, V> previous = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    table[index] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    @Override
    public boolean contains(K key) {
        int index = customHash(key);
        HashNode<K, V> current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            HashNode<K, V> current = table[i];
            while (current != null) {
                count++;
                current = current.getNext();
            }
        }
        return count;
    }

    private int customHash(K key) {
        String keyString = key.toString();
        int hash = 0;
        for(int i = 0 ; i < keyString.length(); i ++){
            hash += keyString.charAt(i);

        }

        return hash % TABLE_SIZE;
    }
}
