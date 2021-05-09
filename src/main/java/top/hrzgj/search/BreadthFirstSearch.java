package top.hrzgj.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 广度优先搜索，是一种盲目搜索算法，目的是系统的展开并检查图中所有的节点，并找出最优解
 * 代码来源：https://blog.csdn.net/qq_37482202/article/details/89513877
 * 《图解算法》
 * @author zhan jp
 * @date 2021-04-28 23:23
 */
public class BreadthFirstSearch {


    public static void main(String[] args) {
        HashMap<String, String[]> hashMap = new HashMap<>();
        hashMap.put("YOU", new String[]{"CLAIRE", "ALICE", "BOB"});
        hashMap.put("CLAIRE", new String[]{"YOU", "JONNY", "THON"});
        hashMap.put("JONNY", new String[]{"CLAIRE"});
        hashMap.put("THOH", new String[]{"CLAIRE"});
        hashMap.put("ALICE", new String[]{"YOU", "PEGGY"});
        hashMap.put("BOB", new String[]{"YOU", "PEGGY", "ANUJ"});
        hashMap.put("PEGGY", new String[]{"BOB", "ALICE"});
        hashMap.put("ANUJ", new String[]{"BOB"});
        Node target = findTarget("YOU", "ANUJ", hashMap);
        //打印出最短路径的各个节点信息
        printSearPath(target);
    }

    /**
     * 打印出到达节点target所经过的各个节点信息
     *
     * @param target
     */
    static void printSearPath(Node target) {
        if (target != null) {
            System.out.print("找到了目标节点:" + target.id + "\n");

            List<Node> searchPath = new ArrayList<Node>();
            searchPath.add(target);
            Node node = target.parent;
            while (node != null) {
                searchPath.add(node);
                node = node.parent;
            }
            String path = "";
            for (int i = searchPath.size() - 1; i >= 0; i--) {
                path += searchPath.get(i).id;
                if (i != 0) {
                    path += "-->";
                }
            }
            System.out.print("步数最短：" + path);
        } else {
            System.out.print("未找到了目标节点");
        }
    }

    static Node findTarget(String startId, String targetId, HashMap<String, String[]> map) {
        // 记录已搜索的节点
        List<String> hasSearchList = new ArrayList<String>();
        // 队列，存放即将搜索的节点
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        // 放入初始节点
        queue.offer(new Node(startId, null));
        // 开始搜索
        while (!queue.isEmpty()) {
            // 出队
            Node node = queue.poll();
            // 判断是否已搜索过，避免死循环
            if (hasSearchList.contains(node.id)) {
                continue;
            }
            System.out.print("判断节点:" + node.id + "\n");
            // 判断是否目标节点
            if (targetId.equals(node.id)) {
                return node;
            }
            // 加入已搜索的队列
            hasSearchList.add(node.id);
            // 把当前搜索节点的下一层依次加入队列中
            if (map.get(node.id) != null && map.get(node.id).length > 0) {
                for (String childId : map.get(node.id)) {
                    // 注意，这里的node记录了父节点（记录路径）
                    queue.offer(new Node(childId, node));
                }
            }
        }
        return null;
    }

    static class Node {
        public String id;
        public Node parent;

        public Node(String id, Node parent) {
            this.id = id;
            this.parent = parent;
        }
    }
}
