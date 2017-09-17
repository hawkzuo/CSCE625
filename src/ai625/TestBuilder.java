package ai625;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Created by Amos on 9/15/17.
 *
 */
public class TestBuilder {
    // TestBuilder

    public static void main(String[] args) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

//        Node root = new Node(10, 5, false);
        int[][] values = {{2,3,5},{6,9},{1,4,7,8,10}};
        Node root = new Node(10 , 3, values);
//        int[][] values = {{},{2,4},{1,3,5}};
//        Node root = new Node(5 , 3, values);
        Node optimal = null;
        pq.add(root);

        Integer maxQueueSize = 0;
        Integer maxStatesStored = 0;

        long startTime = System.currentTimeMillis();
        System.out.println("Start time: " + startTime + ".\n");

        // Don't allow duplicate states
        Set<State> visitedStates = new HashSet<>();

        while(!pq.isEmpty()) {
            Node nextNode = pq.poll();
            visitedStates.add(nextNode.state);

            List<Node> nextLevel = nextNode.generateSuccessors();
            boolean found = false;
            for(Node next: nextLevel) {
                if (!visitedStates.contains(next.state)) {
                    if (Utils.checkState(next.state)) {
                        optimal = next;
                        found = true;
                        break;
                    } else {
                        pq.add(next);
                    }
                }
            }
            if(found) {
                break;
            }

            maxQueueSize = pq.size();
            maxStatesStored = visitedStates.size();
        }


        long endTime = System.currentTimeMillis();
        System.out.println("End time: " + endTime + ".\n");
        System.out.println("Whole running time (ms):" + (endTime-startTime) + ".\n");


        System.out.println(Utils.generatePath(optimal));
        System.out.println("Max Queue:" + maxQueueSize + ".\n");
        System.out.println("Max Set:" + maxStatesStored + ".\n");
        System.out.println(optimal.rank);
    }

    // Brute-force for 3 case:
//    Max Queue:3716876.
//
//    Max Set:969998.
// h0:
//    Max Queue:22671.
//
//    Max Set:5960.
// h1:
//    Max Queue:17441.
//
//    Max Set:4574.

}
