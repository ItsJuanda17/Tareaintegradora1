package collections.interfaces;

public interface IHashtable<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean contains(K key);
    boolean isEmpty();
    int size();
}
