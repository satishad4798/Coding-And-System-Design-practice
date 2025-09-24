public class DesignHashMap<K, V> {

    static final int intialSize = 16;
    int capacity = 16;
    int[] array = new int[10];
    Buckets[] buckets;

    DesignHashMap() {
        buckets = new Buckets[16];
    }

    public static void main(String[] args) {

        DesignHashMap<Integer, Integer> map = new DesignHashMap<>();
        map.set(3, 4);
        map.set(7, 4);
        map.set(4, 4);

        System.out.println(map.get(4));

    }

    public V get(K key) {
        return null;
    }

    public void set(K key, V value) {
        int index = getKeyIndex(key);
    }

    private int getKeyIndex(K key) {
        return key.hashCode() % capacity;
    }

    public static class Buckets {
        int key;
        int value; //linked list endpoint
    }

}
