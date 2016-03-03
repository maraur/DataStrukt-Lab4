
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
        ArrayList<ArrayList> resultList = new ArrayList<>(numbOfNodes-1);
        for (int i = 0; i < numbOfNodes; i++) {
            resultList.add(new ArrayList());
        }
        ArrayList fromList;
        ArrayList toList;
        do {
            BusEdge smallest = prioQueue.poll();
            toList = resultList.get(smallest.to);
            fromList = resultList.get(smallest.from);
            if(toList != fromList) {
                toList.add(smallest);
                toList.addAll(fromList);
                resultList.set(smallest.to, toList);
                resultList.set(smallest.from, toList);
                System.out.println(toList.size()); //todo dodoodadwd
            }
        }while(toList.size() < numbOfNodes-1);
        return toList.iterator();
    }

    private static class EdgeComparator implements Comparator<BusEdge> {

        @Override
        public int compare(BusEdge o1, BusEdge o2) {
            return (int) (o1.getWeight() - o2.getWeight());
        }
    }
}
