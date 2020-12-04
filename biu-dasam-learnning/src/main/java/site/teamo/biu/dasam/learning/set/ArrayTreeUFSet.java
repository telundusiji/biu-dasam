package site.teamo.biu.dasam.learning.set;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/29
 */

/**
 * 基于数组实现的并查集，要求存入的元素可以通过其索引唯一确定一个元素
 * @param <E>
 */
public class ArrayTreeUFSet<E> implements UFSet<E> {

    private Object[] data;
    private int[] flag;
    private Indexer<E> indexer;

    /**
     * 使用该构造方法构造的并查集中的元素需要实现Indexable接口
     * 若元素不实现Indexable接口，则会使用元素的hashCode来作为索引确定唯一元素，可能存在性能损失
     */
    public ArrayTreeUFSet() {
        this(null);
    }

    /**
     * 使用该构造器构造的并查集在构造时需传入一个索引器，用来获取存入元素的索引，并查集中使用索引来确定唯一元素
     *
     * @param indexer 索引器
     */
    public ArrayTreeUFSet(Indexer<E> indexer) {
        data = new Object[8];
        flag = new int[8];
        for (int i = 0; i < flag.length; i++) {
            flag[i] = i;
        }
        this.indexer = indexer;
    }

    @Override
    public void union(E p, E q) {
        int pIndex = getIndex(p);
        int qIndex = getIndex(q);
        resize(pIndex, qIndex);
        int pFlagIndex = findFlagNode(p);
        int qFlagIndex = findFlagNode(q);
        if (pFlagIndex == -1 || qFlagIndex == -1) {
            return;
        }
        data[pIndex] = p;
        data[qIndex] = q;
        flag[pIndex] = flag[qFlagIndex];
    }

    @Override
    public boolean isConnected(E p, E q) {
        int pFlagIndex = findFlagNode(p);
        if (pFlagIndex == -1) {
            return false;
        }
        int qFlagIndex = findFlagNode(q);
        if (qFlagIndex == -1) {
            return false;
        }
        return flag[pFlagIndex] == flag[qFlagIndex];
    }

    private void resize(int pIndex, int qIndex) {
        int newSize = pIndex > qIndex ? pIndex + 1 : qIndex + 1;
        if (newSize > data.length) {
            Object[] newData = new Object[newSize * 2];
            int[] newFlag = new int[newSize * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            System.arraycopy(flag, 0, newFlag, 0, flag.length);
            for (int i = flag.length; i < newFlag.length; i++) {
                newFlag[i] = i;
            }
            data = newData;
            flag = newFlag;
        }
    }

    private int findFlagNode(E e) {
        if (getIndex(e) > data.length) {
            return -1;
        }
        int eIndex = getIndex(e);
        while (flag[eIndex] != eIndex) {
            flag[eIndex] = flag[flag[eIndex]];
            eIndex = flag[eIndex];
        }
        return eIndex;
    }

    private int getIndex(E e) {
        if (indexer == null) {
            if (!(e instanceof Indexable)) {
                return e.hashCode();
            }
            return ((Indexable) e).index();
        }
        return indexer.index(e);
    }

    public interface Indexable {
        int index();
    }

    public interface Indexer<E> {
        int index(E t);
    }
}
