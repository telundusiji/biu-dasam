package site.teamo.biu.dasam.learning.set;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/28
 */
public class MapTreeUFSet<E> implements UFSet<E> {

    private AtomicInteger counter;

    private Map<E, Node> e2Node;

    private class Node {
        Integer flag;
        private E e;
        private Node next;

        public Node(int flag) {
            this.flag = flag;
            this.e = null;
            this.next = this;
        }

        public Node(E e, Node next) {
            this.flag = null;
            this.e = e;
            this.next = next;
        }
    }

    public MapTreeUFSet() {
        counter = new AtomicInteger();
        e2Node = new HashMap<>();
    }

    private Node getFlagNode() {
        return new Node(counter.getAndIncrement());
    }

    @Override
    public void union(E p, E q) {
        if (p == null || q == null) {
            return;
        }
        if (p.equals(q)) {
            return;
        }
        Node pNode = e2Node.get(p);
        Node qNode = e2Node.get(q);
        if (pNode == null && qNode == null) {
            Node flagNode = getFlagNode();
            e2Node.put(p, new Node(p, flagNode));
            e2Node.put(q, new Node(q, flagNode));
        } else if (pNode != null && qNode == null) {
            e2Node.put(q, new Node(q, findFlagNode(pNode)));
        } else if (pNode == null && qNode != null) {
            e2Node.put(p, new Node(p, findFlagNode(qNode)));
        } else {
            Node pFlagNode = findFlagNode(pNode);
            Node qFlagNode = findFlagNode(qNode);
            qFlagNode.flag = pFlagNode.flag;
        }
    }

    public Node findFlagNode(Node node) {
        while (node != null) {
            if (node.next == node) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean isConnected(E p, E q) {
        Node pNode = e2Node.get(p);
        if (pNode == null) {
            return false;
        }
        Node qNode = e2Node.get(q);
        if (qNode == null) {
            return false;
        }
        return findFlagNode(pNode).flag == findFlagNode(qNode).flag;
    }
}
