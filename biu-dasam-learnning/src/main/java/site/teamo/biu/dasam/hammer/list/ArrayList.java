package site.teamo.biu.dasam.hammer.list;


import java.util.Objects;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/3
 */
public class ArrayList<E> implements List<E> {

    private static final int minCapacity = 10;

    private E[] data;
    private int size;
    /**
     * 缩容负载因子
     */
    private double narrowLoadFactor = 0.25;

    /**
     * 扩容负载因子
     */
    private double expandLoadFactor = 0.75;

    public ArrayList() {
        this(minCapacity);
    }

    public ArrayList(int capacity) {
        if (capacity < minCapacity) {
            capacity = minCapacity;
        }
        data = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E e) {
        checkIndexForAdd(index);
        resize();
        for (int i = size; i > index; i--) {
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
    public void set(int index, E e) {
        checkIndex(index);
        data[index] = e;
    }


    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
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
    public E remove(int index) {
        checkIndex(index);
        resize();
        E result = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return result;
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
        return firstIndexOf(e) != -1;
    }

    @Override
    public int[] indexOf(E e) {
        ArrayList<Integer> tempResult = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(e, data[i])) {
                tempResult.addLast(i);
            }
        }
        int[] result = new int[tempResult.size];
        for (int i = 0; i < result.length; i++) {
            result[i] = tempResult.get(i);
        }
        return result;
    }

    @Override
    public int firstIndexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(e, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(e, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newCapacity = 0;
        if (size > data.length * expandLoadFactor) {
            newCapacity = data.length * 2;
        }
        if (size < data.length * narrowLoadFactor) {
            newCapacity = data.length / 2;
        }
        if (newCapacity < minCapacity) {
            return;
        }
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("ArrayList index out of bounds of:" + index);
        }
    }

    public void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("ArrayList index out of bounds of:" + index);
        }
    }
}
