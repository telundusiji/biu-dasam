package site.teamo.biu.dasam.hammer.map;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/1
 */
public interface Map<K, V> {
    int size();

    int put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean isEmpty();
}
