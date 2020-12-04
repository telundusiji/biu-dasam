package site.teamo.biu.dasam.hammer.stack;

import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/1
 */
public interface Stack<E> {
    /**
     * 获取栈中元素数量
     *
     * @return 栈中元素数量
     */
    int size();

    /**
     * 判断栈是否为空
     *
     * @return 判断结果
     */
    boolean isEmpty();

    /**
     * 出站一个元素
     *
     * @return 出栈的元素
     */
    E pull();

    /**
     * 入栈一个元素
     *
     * @param e 要入栈的元素
     */
    void push(E e);

    /**
     * 查看栈顶元素
     *
     * @return 栈顶元素
     */
    E peek();
}
