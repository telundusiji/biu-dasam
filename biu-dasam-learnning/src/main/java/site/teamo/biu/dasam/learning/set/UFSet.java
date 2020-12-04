package site.teamo.biu.dasam.learning.set;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/28
 */
public interface UFSet<E> {

    void union(E p, E q);

    boolean isConnected(E p, E q);
}
