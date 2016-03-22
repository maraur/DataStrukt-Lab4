
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

    /**
     * Constructor
     * @param startNode
     * @param endNode
     * @param edgeList
     * @param noOfNodes
     */
    public CompDijkstraPath(int startNode, int endNode, ArrayList edgeList, int noOfNodes) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeList = edgeList;
        numbOfNodes = noOfNodes;
        prioQueue = new PriorityQueue<>(edgeList.size(), new EdgeComparator());
        makeNodeEdgeList();
    }

    /**
     * Calculates the shortest path between two nodes and returns an iterator over the BusEdges in the
     * shortest path.
     *
     * @return Iterator over BusEdges in the shortest path
     */
    public Iterator<E> findShortestPath() {
        prioQueue.add(new NodeEdge(startNode, null, 0)); //Add an empty NodeEdge
        ArrayList resultArray = new ArrayList();
        boolean[] visited = new boolean[numbOfNodes]; //Array for keeping track of what Nodes have been visited
        boolean innerLoop = true;
        boolean outerLoop = true;
        NodeEdge nextElement = new NodeEdge();
        while (outerLoop) {
            while (innerLoop) {
                if (prioQueue.isEmpty()) {
                    return null; //If reached something went wrong
                }
                nextElement = prioQueue.poll();
                if (!visited[nextElement.getNod()]) { //If node hasn't been visited
                    innerLoop = false;
                }
            }
            if (nextElement.getNod() == endNode) { //If we've reached the endNode - end loops
                outerLoop = false;
                resultArray = nextElement.getEdges();
            } else { //Otherwise loop again, make the new NodeEdges and add them to the priorityQueue
                innerLoop = true;
                int cNode = nextElement.getNod();
                visited[cNode] = true;
                ArrayList<BusEdge> currentNodeList = nodeEdgeList.get(cNode);
                for (BusEdge b : currentNodeList) {
                    ArrayList list = new ArrayList();
                    list.add(b);
                    list.addAll(nextElement.getEdges());
                    prioQueue.add(new NodeEdge(b.to, list, (b.getWeight() + nextElement.getWeight())));
                }
            }
        }
        return resultArray.iterator();
    }

    /**
     * Creates a list over all the BusEdges originating from a specific node
     * and orders them by index
     */
    private void makeNodeEdgeList() {
        nodeEdgeList = new ArrayList<>(numbOfNodes);
        for (int i = 0; i < numbOfNodes; i++) {
            nodeEdgeList.add(new ArrayList<>());
        }
        for (BusEdge b : edgeList) {
            ArrayList tempArray = new ArrayList();
            tempArray.add(b);
            tempArray.addAll(nodeEdgeList.get(b.from));
            nodeEdgeList.set(b.from, tempArray);
        }
    }

    /**
     * Comparator for comparing BusEdges and sorting on their weight
     */
    private class EdgeComparator implements Comparator<NodeEdge> {
        @Override
        public int compare(NodeEdge o1, NodeEdge o2) {
            if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else if (o1.getWeight() < o2.getWeight()) {
                return -1;
            }
            return 0;
        }
    }

    /**
     * Class for holding the different paths for nodes
     * Used for holding several BusEdges at once and summing their weights
     */
    public class NodeEdge {
        int nod;
        ArrayList<BusEdge> edges;
        double weight;

        public NodeEdge() {
        } //needed to initate empty instances

        public NodeEdge(int nod, ArrayList list, double weight) {
            this.nod = nod;
            if (list != null) {
                edges = list;
            } else {
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
