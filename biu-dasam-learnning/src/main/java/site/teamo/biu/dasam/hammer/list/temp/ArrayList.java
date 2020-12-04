package site.teamo.biu.dasam.hammer.list.temp;

import site.teamo.biu.dasam.hammer.list.List;

import java.util.Objects;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/3
 */
public class ArrayList<E> implements List<E> {

    private static final int MIN_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;

    private E[] data;
    private int size;

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(MIN_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    //[a,b,c,d,e] size
    //0 1 2 3
    @Override
    public void add(int index, E e) {
        checkIndexForAdd(index);
        resize();
        for (int i = size; i > index; i++) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public boolean contains(E e) {
        return lastIndexOf(e) != -1;
    }

    @Override
    public int[] indexOf(E e) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                indexes.addLast(i);
            }
        }
        int[] result = new int[indexes.size];
        for (int i = 0; i < result.length; i++) {
            result[i] = indexes.get(i);
        }
        return result;
    }

    @Override
    public int firstIndexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //[a,b,c,d,e] size
    //add(5,f)
    @Override
    public void set(int index, E e) {
        checkIndex(index);
        data[index] = e;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    //[a,c,d,e] size
    //0 1 2 3
    @Override
    public E remove(int index) {
        checkIndex(index);
        E result = data[index];
//        data[index] = null;
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        resize();
        return result;
    }

    private static final double EXPAND_LOAD_FACTOR = 0.75;

    private static final double NARROW_LOAD_FACTOR = 0.25;

    private void resize() {
        int newCapacity = 0;
        //需要扩容
        if (size > data.length * EXPAND_LOAD_FACTOR) {
            if (data.length > MAX_CAPACITY / 2) {
                newCapacity = MAX_CAPACITY;
            } else {
                newCapacity = data.length * 2;
            }
        }
        //需要缩容
        if (size < data.length * NARROW_LOAD_FACTOR) {
            newCapacity = data.length / 2;
        }
        if (newCapacity < MIN_CAPACITY) {
            return;
        }
        //调整容量
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("out of bounds:" + index);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("out of bounds:" + index);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "a");
        list.remove(0);
    }
}
