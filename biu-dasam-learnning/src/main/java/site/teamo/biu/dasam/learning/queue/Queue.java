package site.teamo.biu.dasam.learning.queue;

/**
 * @author 爱做梦的锤子
 * @create 2020/6/23
 */
public interface Queue<E> {

    /**
     * 查看队列的元素数据
     * @return 队列元素数量
     */
    int size();

    /**
     * 判读队列是否为空
     * @return 判读结果
     */
    boolean isEmpty();

    /**
     * 出队一个元素
     * @return 出队的元素
     */
    E dequeue();

    /**
     * 入队一个元素
     * @param e 要入队的元素
     */
    void enqueue(E e);

    /**
     * 查看对头元素
     * @return 队首元素
     */
    E getFront();

    /**
     * 查看队尾元素
     * @return 队尾元素
     */
    E getTail();
}
