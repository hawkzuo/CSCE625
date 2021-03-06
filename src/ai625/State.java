package ai625;

import java.util.*;

/**
 * Created by Amos on 2017/9/15.
 */
public class State {
    int block;
    int stack;
    // Board has dim stack+1 for convenience
    List<List<Integer>> board;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof State) {
            for(int i=1; i<= stack; i++) {
                if(!board.get(i).equals(((State) obj).board.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=stack; i++) {
            sb.append(i); sb.append(" | ");
            for(Integer element: board.get(i)) {
                sb.append(element); sb.append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // Generate new State based on prevState and Action
    public State(State prevState, Action action) {
        block = prevState.block;
        stack = prevState.stack;
        board = new ArrayList<>();
        for(int i=0;i<=stack; i++) {
            board.add(deepCopyList(prevState.board.get(i)));
        }

        List<Integer> fromStack = board.get(action.fromStackNumber);
        List<Integer> toStack = board.get(action.toStackNumber);

        Integer pop = fromStack.remove(fromStack.size()-1);
        toStack.add(pop);
    }

    private List<Integer> deepCopyList(List<Integer> linkedList) {
        List<Integer> res = new LinkedList<>();
        res.addAll(linkedList);
        return res;
    }

    // Initialize state
    public State(int blocks, int stacks, boolean isRandom) {

        block = blocks;
        stack = stacks;
        board = new ArrayList<>();
        for(int i=0;i<=stack; i++) {
            board.add(new LinkedList<>());
        }

        if(isRandom) {
            Random r = new Random(47);

            // 1st: generate a random sequence of numbers between [1,blocks]
           List<Integer> elements = generateRandom(blocks);
            // Don't generate random sequence
//            List<Integer> elements = new ArrayList<>();
//            for(int j=1; j<=blocks; j++) {
//                elements.add(j);
//            }
            for(Integer element: elements) {
                int stackNum = 1+r.nextInt(stacks);
                board.get(stackNum).add(element);
            }
        } else {
            if(stacks == 3) {
                // Base test case
                board.get(1).add(4);
                board.get(2).add(3);    board.get(2).add(1);
                board.get(3).add(2);    board.get(3).add(5);
            }

            if(stacks == 5) {

                board.get(1).add(4);
                board.get(2).add(5);    board.get(2).add(6);    board.get(2).add(9); board.get(2).add(10);
                board.get(3).add(2);    board.get(3).add(7);
                board.get(4).add(3);    board.get(4).add(8);
                board.get(5).add(1);
            }
        }
    }

    // Initialize from int[][]
    public State(int blocks, int stacks, int[][] inputs) {
        block = blocks;
        stack = stacks;
        board = new ArrayList<>();
        for(int i=0;i<=stack; i++) {
            board.add(new LinkedList<>());
        }

        int cursor = 1;
        for(int[] values: inputs) {
            for(int val: values) {
                board.get(cursor).add(val);
            }
            cursor++;
        }
    }




    private List<Integer> generateRandom(int upperBound) {
        List<Integer> res = new ArrayList<>();
        for(int i=1; i<=upperBound; i++) {
            res.add(i);
        }

        List<Integer> output = new ArrayList<>();
        // Randomly pop out
        Random r = new Random();
        for(int up=upperBound; up>=1; up--) {
            int removeIndex = r.nextInt(up);
            Integer removed = res.remove(removeIndex);
            output.add(removed);
        }
        return output;
    }


}
