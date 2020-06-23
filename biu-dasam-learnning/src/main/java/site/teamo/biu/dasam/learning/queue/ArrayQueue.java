package site.teamo.biu.dasam.learning.queue;

import site.teamo.biu.dasam.learning.array.Array;

/**
 * @author 爱做梦的锤子
 * @create 2020/6/23
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(){
        this(16);
    }

    public ArrayQueue(int capacity){
        array = new Array<>(16);
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
    public E dequeue() {
        return array.removerFirst();
    }

    @Override
    public void enqueue(E e) {
        array.add(e);
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public E getTail() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder(String.format("ArrayQueue:{Size: %d}\n", array.size()));
        stringBuilder.append("front-[");
        for (int i = 0; i < array.size(); i++) {
            stringBuilder.append(array.get(i));
            if (i != array.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]-tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue.toString());
            if(i % 3 == 1){
                queue.dequeue();
                System.out.println(queue.toString());
            }
        }
    }
}
