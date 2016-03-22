import javax.xml.soap.Node;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Marcus on 2016-02-25.
 * Class is used for comparing spans using Dijkstras algorithm
 */
public class CompDijkstraPath<E extends Edge> {
    ArrayList<BusEdge> edgeList;
    int startNode;
    int numbOfNodes;
    int endNode;
    PriorityQueue<NodeEdge> prioQueue;
    ArrayList<ArrayList<BusEdge>> nodeEdgeList;

    public CompDijkstraPath(int startNode, int endNode, ArrayList edgeList, int noOfNodes){
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeList = edgeList;
        numbOfNodes = noOfNodes;
        prioQueue = new PriorityQueue<>(edgeList.size(), new EdgeComparator());
        makeNodeEdgeList();
    }
    public Iterator<E> findShortestPath(){
        prioQueue.add(new NodeEdge(startNode, null, 0));
        ArrayList resultArray = new ArrayList(); //todo don't initate
        boolean[] visited = new boolean[numbOfNodes];
        boolean innerLoop = true;
        boolean outerLoop = true;
        NodeEdge nextElement = new NodeEdge();
        while(outerLoop) {
            while (innerLoop) {
                if (prioQueue.isEmpty()) {
                    return null; //Not very good
                }
                nextElement = prioQueue.poll();
                if (!visited[nextElement.getNod()]) {
                    innerLoop = false;
                }
            }
            if(nextElement.getNod() == endNode){
                outerLoop = false;
                resultArray = nextElement.getEdges();
            }else{
                innerLoop = true;
                int cNode = nextElement.getNod();
                visited[cNode] = true;
                ArrayList<BusEdge> currentNodeList = nodeEdgeList.get(cNode);
                for (BusEdge b : currentNodeList ) {
                        ArrayList list = new ArrayList();
                        list.add(b);
                        list.addAll(nextElement.getEdges());
                        prioQueue.add(new NodeEdge(b.to, list, (b.getWeight() + nextElement.getWeight())));
                }
            }
        }
        return resultArray.iterator();
    }

    private class EdgeComparator implements Comparator<NodeEdge> {
        @Override
        public int compare(NodeEdge o1, NodeEdge o2) {
            if(o1.getWeight() > o2.getWeight()){
                return 1;
            }else if (o1.getWeight() < o2.getWeight()){
                return -1;
            }
            return 0;
        }
    }

    private void makeNodeEdgeList(){
        nodeEdgeList = new ArrayList<>(numbOfNodes);
        for (int i = 0; i < numbOfNodes; i++){
            nodeEdgeList.add(new ArrayList<>());
        }
        for(BusEdge b : edgeList){
            ArrayList tempArray = new ArrayList();
            tempArray.add(b);
            tempArray.addAll(nodeEdgeList.get(b.from));
            nodeEdgeList.set(b.from, tempArray);
        }
    }

    public class NodeEdge{
        int nod;
        ArrayList<BusEdge> edges;
        double weight;
        public NodeEdge(){} //needed to initate empty instances
        public NodeEdge(int nod, ArrayList list, double weight){
            this.nod = nod;
            if(list != null){
                edges = list;
            }else{
                edges = new ArrayList<>();
            }
            this.weight = weight;
        }

        public int getNod() {
            return nod;
        }

        public ArrayList<BusEdge> getEdges() {
            return edges;
        }

        public double getWeight() {
            return weight;
        }
    }
}
