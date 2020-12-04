package site.teamo.biu.dasam.hammer.set;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/1
 */
public interface UFSet<E> {

    /**
     * 连接两个元素
     *
     * @param p
     * @param q
     */
    void union(E p, E q);

    /**
     * 判断两个元素是否连接
     *
     * @param p
     * @param q
     * @return
     */
    boolean isConnect(E p, E q);

}
