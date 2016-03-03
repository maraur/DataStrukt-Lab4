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

    public CompDijkstraPath(int startNode, int endNode, ArrayList edgeList, int noOfNodes){
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeList = edgeList;
        numbOfNodes = noOfNodes;
        prioQueue = new PriorityQueue<>(edgeList.size(), new EdgeComparator());
    }
    public Iterator<E> findShortestPath(){
        prioQueue.add(new NodeEdge(startNode, null, 0));
        
        return null;
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

    public class NodeEdge{
        int nod;
        ArrayList<BusEdge> edges;
        double weight;
        public NodeEdge(int nod, ArrayList list, int weight){
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
