import java.util.Objects;

public class CustomHashMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    static int size = 0;
    int INITIAL_CAPACITY = 16;
    Entry<K, V>[] buckets;

    public CustomHashMap() {
        buckets = new Entry[INITIAL_CAPACITY];
    }

    public static void main(String[] args) {

        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        System.out.println("Putting values...");
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);

        System.out.println("Value for key 'two': " + map.get("two")); // Expected: 2
        System.out.println("Value for key 'five': " + map.get("five")); // Expected: null

        System.out.println("\nUpdating value for key 'one'...");
        map.put("one", 111);
        System.out.println("New value for key 'one': " + map.get("one")); // Expected: 111
        System.out.println("Current size: " + map.size()); // Expected: 4

        System.out.println("\nRemoving key 'three'...");
        Integer removedValue = map.remove("three");
        System.out.println("Removed value: " + removedValue); // Expected: 3
        System.out.println("Value for key 'three' after removal: " + map.get("three")); // Expected: null
        System.out.println("Current size: " + map.size()); // Expected: 3

        System.out.println("\nTesting collision and resizing...");
        // Add more elements to trigger a resize (initial capacity 16 * 0.75 = 12)
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }
        System.out.println("Final size after resizing: " + map.size()); // Expected: 3 + 20 = 23
        System.out.println("Value for 'key15': " + map.get("key15")); // Expected: 15
        System.out.println("Internal array length: " + map.buckets.length); // Expected: 32


    }


    public V get(K key) {
        int index = getKeyIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void put(K key, V value) {

        if (size > buckets.length * LOAD_FACTOR) {
            resize(buckets);
        }

        int index = getKeyIndex(key);


        Entry<K, V> head = buckets[index];
        Entry<K, V> current = head;
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        //if key doesn't exist add
        Entry<K, V> newEntry = new Entry<>(key, value, head);


        buckets[index] = newEntry;
        size++;


    }

    private void resize(Entry<K, V>[] buckets) {
        int newCapacity = buckets.length * 2;

        Entry<K, V>[] newBuckets = new Entry[newCapacity];
        for (Entry<K, V> head : buckets) {

            while (head != null) {
                int newIndex = Math.abs(head.key.hashCode() % newCapacity);
                Entry<K, V> next = head.next;

                // re-insert into new buckets
                head.next = newBuckets[newIndex];
                newBuckets[newIndex] = head;

                head = next;
            }

        }

        ////


        for (Entry<K, V> head : buckets) {
            while (head != null) {
                int newIndex = Math.abs(head.key.hashCode() % newCapacity);
                Entry<K, V> next = head.next;

                // re-insert into new buckets
                head.next = newBuckets[newIndex];
                newBuckets[newIndex] = head;

                head = next;
            }
        }
        buckets = newBuckets;

    }

    public V remove(K key) {

        int index = getKeyIndex(key);
        Entry<K, V> head = buckets[index];
        Entry<K, V> prev = null;
        Entry<K, V> current = head;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;


            }
            prev = current;
            current = current.next;

        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getKeyIndex(K key) {
        return key.hashCode() % INITIAL_CAPACITY;

    }

    public static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
