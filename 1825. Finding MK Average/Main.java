import java.util.*;


class MKAverage {
    int[] arr;
    PriorityQueue<Integer> minHeap, maxHeap, midMinHeap, midMaxHeap;
    HashSet<Integer> small, mid, big;
    int m, k, top, midSum, midN, average, lastCalTop;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        arr = new int[100001];
        minHeap = new PriorityQueue<>((a, b) -> arr[a] - arr[b]);
        maxHeap = new PriorityQueue<>((a, b) -> arr[b] - arr[a]);
        midMinHeap = new PriorityQueue<>((a, b) -> arr[a] - arr[b]);
        midMaxHeap = new PriorityQueue<>((a, b) -> arr[b] - arr[a]);
        small = new HashSet<>();
        mid = new HashSet<>();
        big = new HashSet<>();
        top = 0;
        midSum = 0;
        midN = m - k * 2;
        lastCalTop = -1;
    }

    private int removeMidMin(int curOutdated) {
        int midMin = -1;
        while (!midMinHeap.isEmpty() && midMin <= curOutdated) {
            midMin = midMinHeap.poll();
        }
        if (midMin == -1) {
            return -1;
        }
        mid.remove(midMin);
        midSum -= arr[midMin];
        return midMin;
    }

    private int removeMidMax(int curOutdated) {
        int midMax = -1;
        while (!midMaxHeap.isEmpty() && midMax <= curOutdated) {
            midMax = midMaxHeap.poll();
        }
        if (midMax == -1) {
            return -1;
        }
        mid.remove(midMax);
        midSum -= arr[midMax];
        return midMax;
    }

    private void addSmall(int i) {
        small.add(i);
        maxHeap.add(i);
    }

    private void addBig(int i) {
        minHeap.add(i);
        big.add(i);
    }

    private void initialize() {
        int i, out;
        List<Integer> tmp = new ArrayList<>();
        for (i = 0; i < m; i++) {
            addBig(i);
            if (minHeap.size() == k + 1) {
                out = minHeap.poll();
                big.remove(out);
                tmp.add(out);
            }
        }
        for (Integer it : tmp) {
            addSmall(it);
            if (maxHeap.size() == k + 1) {
                out = maxHeap.poll();
                small.remove(out);
                mid.add(out);
                midSum += arr[out];
            }
        }
        for (Integer it : mid) {
            midMinHeap.add(it);
            midMaxHeap.add(it);
        }
    }

    private int pollMinHeap(int curOutdated) {
        while (!minHeap.isEmpty() && minHeap.peek() <= curOutdated) {
            big.remove(minHeap.poll());
        }
        int out = minHeap.poll();
        big.remove(out);
        return out;
    }

    private int pollMaxHeap(int curOutdated) {
        while (!maxHeap.isEmpty() && maxHeap.peek() <= curOutdated) {
            small.remove(maxHeap.poll());
        }
        int out = maxHeap.poll();
        small.remove(out);
        return out;
    }

    private void addMiddle(int i) {
        mid.add(i);
        midSum += arr[i];
        midMinHeap.add(i);
        midMaxHeap.add(i);
    }

    public void addElement(int num) {
        arr[top] = num;
        top++;
        if (top == m) {
            initialize();
        } else if (top > m) {
            int outdated = top - m - 1, lower, upper, extra;
            boolean isInSmall = small.remove(outdated);
            boolean isInBig = big.remove(outdated);
            mid.remove(outdated);
            lower = maxHeap.peek();
            upper = minHeap.peek();
            if (isInSmall) {
                if (num < arr[lower]) {
                    addSmall(top - 1);
                } else {
                    if (num <= arr[upper]) {
                        addMiddle(top - 1);
                    } else {
                        addBig(top - 1);
                        extra = pollMinHeap(outdated);
                        addMiddle(extra);
                    }
                    addSmall(removeMidMin(outdated));
                }
            } else if (isInBig) {
                if (num > arr[upper]) {
                    addBig(top - 1);
                } else {
                    if (num >= arr[lower]) {
                        addMiddle(top - 1);
                    } else {
                        addSmall(top - 1);
                        extra = pollMaxHeap(outdated);
                        addMiddle(extra);
                    }
                    addBig(removeMidMax(outdated));
                }
            } else {
                midSum -= arr[outdated];
                if (num < arr[lower]) {
                    addSmall(top - 1);
                    extra = pollMaxHeap(outdated);
                    addMiddle(extra);
                } else if (num > arr[upper]) {
                    addBig(top - 1);
                    extra = pollMinHeap(outdated);
                    addMiddle(extra);
                } else {
                    addMiddle(top - 1);
                }
            }
        }
    }

    public int calculateMKAverage() {
        if (top < m) {
            return -1;
        }
        if (lastCalTop != top) {
            lastCalTop = top;
            average = midSum / midN;
        }
        return average;
    }
}
public class Main {
    public static void main(String[] args) {
        MKAverage obj = new MKAverage(7, 2);
        int[] arr = new int[]{2, 3, 6, 16, 9, 6, 5, 4, 7, 16};
        int res;
        for (int i = 0; i < 10; i++) {
            obj.addElement(arr[i]);
            res = obj.calculateMKAverage();
            System.out.print(res);
            System.out.print(' ');
        }
        System.out.println();
    }
}
