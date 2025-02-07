import java.util.ArrayList;
import java.util.List;

public class MyMap<K,V> {
    private List<MapNode<K, V>> bucket = new ArrayList<>();
    private int capacity;
    private int size;
    private final int INITIAL_CAPACITY = 5;

    public MyMap() {
        this.size = 0;
        this.capacity = this.INITIAL_CAPACITY;

        for (int i = 0; i < this.capacity; i++) {
            bucket.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % this.capacity;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;

        MapNode<K, V> newEntry = new MapNode<>(key, value);
        head = bucket.get(bucketIndex);
        newEntry.next = head;
        bucket.set(bucketIndex, newEntry);

        double loadFactor = (size * 1.00) / capacity;
        if (loadFactor > 0.7) {
            rehash();
        }
    }

    public void rehash () {
        List<MapNode<K, V>> temp = bucket;
        this.bucket = new ArrayList<>();
        this.capacity *= 2;

        for (int i=0; i<capacity; i++) {
            bucket.add(null);
        }

        size = 0;
        for (int i=0; i<temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);

            while (head != null) {
                this.put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        MapNode<K, V> previous = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if(previous == null) {
                    bucket.set(bucketIndex, head.next);
                } else {
                    previous.next = head.next;
                }

                size--;
                head.next = null;
                break;
            }
            previous = head;
            head = head.next;
        }
    }


    private class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
