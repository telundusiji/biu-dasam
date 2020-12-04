package site.teamo.biu.dasam.hammer.list;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/1
 */
public interface List<E> {
    /**
     * 存储元素数量
     *
     * @return
     */
    int size();

    /**
     * 添加一个元素到指定索引
     *
     * @param index
     * @param e
     * @return
     */
    void add(int index, E e);

    /**
     * 在尾部添加一个元素
     *
     * @param e
     * @return
     */
    void addLast(E e);

    /**
     * 在头部添加一个元素
     *
     * @param e
     * @return
     */
    void addFirst(E e);

    /**
     * 替换指定索引读的一个元素
     *
     * @param index
     * @param e
     * @return
     */
    void set(int index, E e);

    /**
     * 获取指定索引的一个元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 获取最前面的一个元素
     *
     * @return
     */
    E getFirst();

    /**
     * 获取最尾部的一个元素
     *
     * @return
     */
    E getLast();

    /**
     * 移除一个元素
     *
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 移除尾部的一个元素
     *
     * @return
     */
    E removeLast();

    /**
     * 移除头部的一个元素
     *
     * @return
     */
    E removeFirst();

    /**
     * 判断是否包含一个元素
     *
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 给定一个元素获取其在数组中的索引
     *
     * @param e
     * @return
     */
    int[] indexOf(E e);

    /**
     * 获取该元素第一次出现的索引
     *
     * @param e
     * @return
     */
    int firstIndexOf(E e);

    /**
     * 获取该元素最后一次出现的索引
     *
     * @param e
     * @return
     */
    int lastIndexOf(E e);

    /**
     * 数组是否为空
     *
     * @return
     */
    boolean isEmpty();
}
