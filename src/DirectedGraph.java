
import java.util.*;

public class DirectedGraph<E extends Edge> {
    ArrayList<BusEdge> edgeList;
    int numbOfNodes;
    CompKruskalEdge kruskObject;
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
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		kruskObject = new CompKruskalEdge(edgeList, numbOfNodes);
        return kruskObject.findMinimumSpanningTree();
	}

}
  
