import com.alibaba.fastjson.JSON;
import com.liuyan.study.interview.meituan.Node;
import com.liuyan.study.interview.meituan.NodeB;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

import java.util.*;

/**
 * Created by liuyan on 2017/12/8.
 */
public class MeituanTests {

    private List<Map> nodes() {
        List<Map> nodes = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Map map = new HashMap<>();
            map.put("id", i);
            map.put("patentId", i - 1);
            map.put("code", "");
            map.put("children", new ArrayList());
            nodes.add(map);
        }
        return nodes;
    }

    @Test
    public void Test() {
        List<Map> nodes = nodes();
        for (Map parent : nodes) {
            int parentId = (int) parent.get("id");
            List<Map> childrenList = new ArrayList<>();
            for (Map children : nodes) {
                int childrenId = (int) children.get("id");
                if (parentId + 1 == childrenId) {
                    parent.put("node", "node" + parentId);
                    childrenList.add(children);
                }
            }
            parent.put("code", "node" + parentId);
            parent.put("children", childrenList);
        }
        System.out.println(JSON.toJSON(nodes.get(0)));
    }


    @Test
    public void Test2() {
        List<NodeB> nodeBs = nodesList();
        NodeB nodeB = new NodeB(1, 0, "code" + 1, new ArrayList<>());
        nodeB.setChildrens(nodeBs);
        System.out.println(JSON.toJSON(nodeB));
    }

    private List<NodeB> nodesList() {
        List<NodeB> nodes = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            nodes.add(new NodeB(i, i - 1, "", new ArrayList<>()));
        }
        return nodes;
    }

}
