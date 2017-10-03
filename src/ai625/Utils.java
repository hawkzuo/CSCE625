package ai625;

import java.util.*;

/**
 * Created by Amos on 2017/9/15.
 */
public class Utils {

    private static Map<Character,Integer> mapping = new HashMap<>();
    private static Map<Integer,Character> reversedMapping = new HashMap<>();
    static {
        char ch = 'A'; int counter = 1;
        while(ch <= 'Z') {
            mapping.put(ch, counter);
            reversedMapping.put(counter, ch);
            ch++;
            counter++;
        }
    }

    public static boolean checkState(State state) {
        List<Integer> firstStack = state.board.get(1);
        int targetBlocks = state.block;
        if(firstStack.size() != targetBlocks) {
            return false;
        } else {
            Iterator<Integer> it = firstStack.iterator();
            int refer = 1;
            while(it.hasNext()) {
                int val = it.next();
                if(val != refer) {
                    return false;
                }
                refer++;
            }
            return true;
        }
    }


    public static String generatePath(Node cur) {
        List<String> path = new LinkedList<>();
        Node pointer = cur;
        while(pointer != null) {
            path.add(0, pointer.state.toString());
            pointer = pointer.parent;
        }
        StringBuilder sb = new StringBuilder();
        for (String state : path) {
            sb.append(state);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static int n(char ch) {
        return mapping.get(ch);
    }
    public static char c(int i) {
        return reversedMapping.get(i);
    }
}
