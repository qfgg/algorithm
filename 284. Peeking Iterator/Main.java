import java.util.*;


class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    Integer current;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        current = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (current != null) {
            return current;
        }
        current = it.next();
        return current;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (current == null) {
            return it.next();
        }
        int ret = current;
        current = null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        if (current != null) {
            return true;
        }
        return it.hasNext();
    }
}
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        PeekingIterator peekingIterator = new PeekingIterator(iterator); // [1,2,3]
        System.out.println(peekingIterator.next());    // return 1, the pointer moves to the next element [1,2,3].
        System.out.println(peekingIterator.peek());    // return 2, the pointer does not move [1,2,3].
        System.out.println(peekingIterator.next());    // return 2, the pointer moves to the next element [1,2,3]
        System.out.println(peekingIterator.next());    // return 3, the pointer moves to the next element [1,2,3]
        System.out.println(peekingIterator.hasNext()); // return False
    }
}
