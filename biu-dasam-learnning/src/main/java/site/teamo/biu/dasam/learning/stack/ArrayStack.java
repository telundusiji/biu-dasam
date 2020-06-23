package site.teamo.biu.dasam.learning.stack;

import site.teamo.biu.dasam.learning.array.Array;

/**
 * @author 爱做梦的锤子
 * @create 2020/6/19
 */
public class ArrayStack<E> implements Stack<E>{

    private Array<E> array;

    public ArrayStack(){
        this(16);
    }

    public ArrayStack(int capacity){
        this.array = new Array<>(capacity);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.size()==0;
    }

    @Override
    public E pull() {
        return array.removeLast();
    }

    @Override
    public void push(E e) {
        array.add(e);
    }

    @Override
    public E peek() {
        return array.getLast();
    }
}
