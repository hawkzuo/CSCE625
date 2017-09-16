package ai625;

/**
 * Created by Amos on 2017/9/15.
 */
public class Action {
    int fromStackNumber;
    int toStackNumber;

    public Action(int from, int to) {
        fromStackNumber = from;
        toStackNumber = to;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Action &&
                ((Action) obj).fromStackNumber == this.fromStackNumber &&
                ((Action) obj).toStackNumber == this.toStackNumber;
    }

    public String toString() {
        return "(" + String.valueOf(fromStackNumber) + "," + String.valueOf(toStackNumber) + ")";
    }
}
