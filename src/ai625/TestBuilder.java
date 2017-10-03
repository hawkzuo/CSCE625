package ai625;

import java.util.*;

/*
 * Created by Amos on 9/15/17.
 *
 */
public class TestBuilder {
    // TestBuilder

    public static void main(String[] args) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

//#EG
//#ACDJ
//#BFHI

//        Node root = new Node(10, 5, false);

//        int[][] values = {{4,6,10},{2,7,8,9},{1,3,5}};
//        Node root = new Node(10 , 3, values);

//        int[][] values = {{},{2,4},{1,3,5}};
//        Node root = new Node(5 , 3, values);

//#DN
//#AEFGHIJKO
//#BCLM
//        Map<Character,Integer> mapping = new HashMap<>();
//        char ch = 'A'; int counter = 1;
//        while(ch <= 'Z') {
//            mapping.put(ch, counter);
//            ch++;
//            counter++;
//        }
//        int[][] values = {{mapping.get('F'),mapping.get('D'),mapping.get('A'),mapping.get('G')},
//                {mapping.get('O'),mapping.get('M'),mapping.get('B'),mapping.get('G'),
//                        mapping.get('C')},
//                {mapping.get('H'),mapping.get('N'),mapping.get('L'),mapping.get('E'),mapping.get('K'),mapping.get('I')}};

        int[][] values = {{},
                {Utils.n('B'), Utils.n('E'), Utils.n('K'),Utils.n('A'),Utils.n('F')},
                {Utils.n('J'),Utils.n('I'),Utils.n('L'),Utils.n('C')},
                {Utils.n('D'),Utils.n('H'),Utils.n('G')},
                {}};

        Node root = new Node(12 , 5, values);

        Node optimal = null;
        pq.add(root);

        int maxRank = 0;

        Integer maxQueueSize = 0;
        Integer maxStatesStored = 0;

        long startTime = System.currentTimeMillis();
        System.out.println("Start time: " + startTime + ".\n");

        // Don't allow duplicate states
        Set<String> visitedStates = new HashSet<>();
        visitedStates.add(root.state.toString());


        while(!pq.isEmpty()) {
            if(maxQueueSize + 10000 < pq.size() ) {
                maxQueueSize = pq.size();
                System.out.println("Current Q size: " +maxQueueSize + "\n");
            }

            Node nextNode = pq.poll();
            if(nextNode.rank > maxRank) {
                maxRank = nextNode.rank;
                System.out.println("Max rank refreshed:"+maxRank+"\n");
            }
//            visitedStates.add(nextNode.state.toString());

            List<Node> nextLevel = nextNode.generateSuccessors();
            boolean found = false;
            for(Node next: nextLevel) {
                if (!visitedStates.contains(next.state.toString())) {
                    if (Utils.checkState(next.state)) {
                        optimal = next;
                        found = true;
                        break;
                    } else {
                        pq.add(next);
                        visitedStates.add(next.state.toString());
                    }
                }
            }
            if(found) {
                break;
            }

            maxStatesStored = visitedStates.size();
        }
        maxQueueSize = Math.max(maxQueueSize, pq.size());

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


//    Max Queue:2319.
//
//    Max Set:2534.
//
//            19
}
