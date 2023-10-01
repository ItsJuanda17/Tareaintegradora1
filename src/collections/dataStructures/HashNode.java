package collections.dataStructures;

public class HashNode<K , V>{
    private K key;
    private V value;
    private HashNode<K , V> next;

    public HashNode(K key , V value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public HashNode<K , V> getNext(){
        return next;
    }

    public void setNext(HashNode<K , V> next){
        this.next = next;
    }
}