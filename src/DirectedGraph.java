import java.util.*;


/**
 * Class which utilizes the CompKruskalEdge and CompDijkstraPath classes to
 * perform operations on an array of BusEdges.
 * @param <E>
 */
public class DirectedGraph<E extends Edge> {
    ArrayList<BusEdge> edgeList;
    int numbOfNodes;
    CompKruskalEdge kruskObject;
    CompDijkstraPath dijkObject;
	public DirectedGraph(int noOfNodes) {
		edgeList = new ArrayList();
        numbOfNodes = noOfNodes;
    }

    /**
     * Used to add BusEdges to Array
     * @param e
     */
	public void addEdge(E e) {
        if(e != null) {
            edgeList.add((BusEdge) e);
        }
	}

    /**
     * Calculates shortest path from one station to another and returns an
     * iterator that iterates over shortest path.
     * @param from Start station
     * @param to End station
     * @return Iterator for Shortest Path between stations
     */
	public Iterator<E> shortestPath(int from, int to) {
		dijkObject = new CompDijkstraPath(from, to, edgeList,numbOfNodes);
        return dijkObject.findShortestPath();
	}

    /**
     * Calculates and returns the MST of a given list of Edges.
     * Returns an iterator that iterates over the MST.
      * @return Iterator for MST
     */
	public Iterator<E> minimumSpanningTree() {
        kruskObject = new CompKruskalEdge(edgeList, numbOfNodes);
        return kruskObject.findMinimumSpanningTree();
	}

}
  
