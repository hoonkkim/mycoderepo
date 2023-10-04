public interface HashTableInterface {

    String get(int k);
    void put(int k, String v);
    boolean contains(int k);
    void delete(int k);
    void printHash();
}
