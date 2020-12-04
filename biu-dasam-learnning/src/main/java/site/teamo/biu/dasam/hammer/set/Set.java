package site.teamo.biu.dasam.hammer.set;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/1
 */
public interface Set<E> {
    int size();

    void put(E e);

    E remove(E e);

    boolean contains(E e);

    boolean isEmpty();
}
