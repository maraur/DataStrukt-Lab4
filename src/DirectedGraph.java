
import java.util.*;

public class DirectedGraph<E extends Edge> {
    ArrayList<BusEdge> edgeList;
    int numbOfNodes;
    CompKruskalEdge kruskObject;
    CompDijkstraPath dijkObject;
	public DirectedGraph(int noOfNodes) {
		edgeList = new ArrayList();
        numbOfNodes = noOfNodes;
    }

	public void addEdge(E e) {
        if(e != null) {
            edgeList.add((BusEdge) e);
        }
	}

	public Iterator<E> shortestPath(int from, int to) {
		dijkObject = new CompDijkstraPath(from, to, edgeList,numbOfNodes);
        return dijkObject.findShortestPath();
	}
		
	public Iterator<E> minimumSpanningTree() {
        kruskObject = new CompKruskalEdge(edgeList, numbOfNodes);
        return kruskObject.findMinimumSpanningTree();
	}

}
  
