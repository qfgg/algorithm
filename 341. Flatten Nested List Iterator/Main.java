import java.util.*;


/*    // @return true if this NestedInteger holds a single integer, rather than a nested list.
*     public boolean isInteger();
*
*     // @return the single integer that this NestedInteger holds, if it holds a single integer
*     // Return null if this NestedInteger holds a nested list
*     public Integer getInteger();
*
*     // @return the nested list that this NestedInteger holds, if it holds a nested list
*     // Return empty list if this NestedInteger holds a single integer
*     public List<NestedInteger> getList();
 */
public class NestedIterator implements Iterator<Integer> {
    List<Integer> l;
    int idx;
    private void flat(List<NestedInteger> nestedList) {
        for (NestedInteger ni: nestedList) {
            if (ni.isInteger()) {
                l.add(ni.getInteger());
            } else {
                flat(ni.getList());
            }
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        l = new ArrayList<>();
        flat(nestedList);
        idx = 0;
    }

    @Override
    public Integer next() {
        int next = l.get(idx);
        idx++;
        return next;
    }

    @Override
    public boolean hasNext() {
        return idx < l.size();
    }
}
public class Main {
    public static void main(String[] args) {
    }
}
