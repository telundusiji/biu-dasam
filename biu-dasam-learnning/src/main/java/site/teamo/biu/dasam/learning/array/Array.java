package site.teamo.biu.dasam.learning.array;

import java.util.Objects;

/**
 * @author 爱做梦的锤子
 * @create 2020/06/14
 */
public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造方法，初始化data数组和size大小
     *
     * @param capacity 容量大小
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 构造方法，初始化data数组和size大小,使用默认容量大小16
     */
    public Array() {
        this(16);
    }

    /**
     * 指定索引位置插入元素
     *
     * @param index 索引位置
     * @param e     插入的元素
     */
    public void add(int index, E e) {
        checkIndex(index);

        if (size == data.length) {
            resize(size * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 不指定索引位置添加元素，默认添加在末尾
     *
     * @param e 添加元素数据
     */
    public void add(E e) {
        add(size, e);
    }

    /**
     * 指定索引位置更新元素，若指定索引位置为当前size大小，则插入数据
     *
     * @param index 指定索引位置
     * @param e     更新的元素
     */
    public void set(int index, E e) {
        checkIndex(index);

        if (index == size) {
            add(index, e);
        }
        data[index] = e;
    }

    /**
     * 获取指定索引位置元素
     *
     * @param index 索引位置
     * @return 获取到的元素
     */
    public E get(int index) {
        checkIndex(index);
        if (index == size) {
            return null;
        }
        return data[index];
    }

    /**
     * 判断指定元素是否存在
     *
     * @param e 指定元素
     * @return 判断结果
     */
    public boolean contains(E e) {
        int i = findFirst(e);
        return i != -1;
    }

    /**
     * 查找指定元素第一次出现的索引
     *
     * @param e 指定元素
     * @return 查找结果
     */
    public int findFirst(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找指定元素最后出现的索引
     *
     * @param e 指定元素
     * @return 查找到的索引，未查找到时返回-1
     */
    public int findLast(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找指定元素所有出现位置索引
     *
     * @param e 指定元素
     * @return 返回所有查找到的索引，未查找到时返回空数组
     */
    public int[] findAll(E e) {
        int[] result = new int[size];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                result[counter] = i;
                counter++;
            }
        }
        int[] temp = new int[counter];
        for (int i = 0; i < counter; i++) {
            temp[i] = result[i];
        }
        return temp;
    }

    /**
     * 移除指定索引位置元素
     *
     * @param index 指定索引
     * @return 移除的元素
     */
    public E remove(int index) {
        checkIndex(index);
        if (index == size) {
            throw new ArrayIndexOutOfBoundsException("Size: " + size + ",Index: " + index);
        }
        E temp = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length >= 4) {
            resize(data.length / 2);
        }

        return temp;
    }

    /**
     * 移除指定元素第一次出现
     *
     * @param e 指定元素
     */
    public void removeFirstObject(E e) {
        int index = findFirst(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 移除指定元素最后一次出现出现
     *
     * @param e 指定元素
     */
    public void removeLastObject(E e) {
        int index = findLast(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 移除指定元素所有出现
     *
     * @param e 指定的元素
     */
    public void removeAll(E e) {
        int[] index = findAll(e);
        for (int i = 0; i < index.length; i++) {
            remove(i);
        }
    }

    /**
     * 移除最后一个元素
     *
     * @return 移除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 查看当前的size
     *
     * @return 当前数据的size
     */
    public int size() {
        return size;
    }

    /**
     * 查看容量信息
     *
     * @return 当前数组的容量
     */
    public int capacity() {
        return data.length;
    }

    /**
     * 当data存满时进行扩容
     *
     * @param resize 扩容的容量大小
     */
    private void resize(int resize) {
        E[] newData = (E[]) new Object[resize];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 检查指定索引是否合法
     *
     * @param index 索引
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Size: " + size + ",Index: " + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("Array:{Size: %d, Capacity: %d}\n", size, data.length));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
