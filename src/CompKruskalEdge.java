
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Marcus on 2016-02-25.
 * Class is used for comparing edges using Kruskals algorithm
 */

public class CompKruskalEdge<E extends Edge> {
    PriorityQueue<BusEdge> prioQueue;
    ArrayList edgeList;
    int numbOfNodes;

    public CompKruskalEdge(ArrayList edgeList, int numbOfNodes) {
        this.numbOfNodes = numbOfNodes;
        prioQueue = new PriorityQueue<>(edgeList.size(), new EdgeComparator());
        this.edgeList = edgeList;
        for (int i = 0; i < edgeList.size(); i++) {
            prioQueue.add((BusEdge) edgeList.get(i));
        }

    }
    public Iterator<E> findMinimumSpanningTree() {
        ArrayList<ArrayList> edgeList = new ArrayList<>(this.edgeList.size());
        ArrayList fromList;
        ArrayList toList = new ArrayList();
        while (toList.size() == numbOfNodes-1) {
            BusEdge smallest = prioQueue.poll();
            toList = edgeList.get(smallest.to);
            fromList = edgeList.get(smallest.from);
            if(toList != fromList) {
                toList.add(smallest);
                toList.addAll(fromList);
                fromList = toList;
            }
        }
        return toList.iterator();
    }

    private static class EdgeComparator implements Comparator<BusEdge> {

        @Override
        public int compare(BusEdge o1, BusEdge o2) {
            return (int) (o1.getWeight() - o2.getWeight());
        }
    }
}
