
import java.util.*;

public class DirectedGraph<E extends Edge> {
    ArrayList nodList;
	public DirectedGraph(int noOfNodes) {
		nodList = new ArrayList(noOfNodes);
    }

	public void addEdge(E e) {
        if(e != null) {
            nodList.add(e);
        }
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
