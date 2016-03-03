
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
        System.out.println(numbOfNodes);//todo awdawdawdawdawfawfaw
        ArrayList<ArrayList> resultList = new ArrayList<>(numbOfNodes);
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
                for(int i = 0; i < toList.size(); i++){
                    resultList.set(((BusEdge)toList.get(i)).to, toList);
                    resultList.set(((BusEdge)toList.get(i)).from, toList);
                }
            }
        }while(toList.size() < numbOfNodes-1);
        return toList.iterator();
    }

    private static class EdgeComparator implements Comparator<BusEdge> {

        @Override
        public int compare(BusEdge o1, BusEdge o2) {
            if(o1.getWeight() > o2.getWeight()){
                return 1;
            }else if (o1.getWeight() < o2.getWeight()){
                return -1;
            }
            return 1;
        }
    }
}
