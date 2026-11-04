import java.util.*;

class Solution {
    private PriorityQueue<Integer> lo, hi;
    private Map<Integer, Integer> delayed;
    private int loSize, hiSize;
    private long loSum, hiSum;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        lo = new PriorityQueue<>(Collections.reverseOrder()); // max-heap (smaller half)
        hi = new PriorityQueue<>();                           // min-heap (larger half)
        delayed = new HashMap<>();
        loSize = hiSize = 0;
        loSum = hiSum = 0;

        // cost[i] = operations to make nums[i..i+x-1] all equal
        long[] cost = new long[n - x + 1];
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) remove(nums[i - x]);
            if (i >= x - 1) {
                long m = lo.peek();
                cost[i - x + 1] = (m * loSize - loSum) + (hiSum - m * hiSize);
            }
        }

        // dp: choose k non-overlapping windows with minimum total cost
        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1]; // j = 0 -> all zeros
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = x; i <= n; i++) {
                cur[i] = Math.min(cur[i - 1], prev[i - x] + cost[i - x]);
            }
            prev = cur;
        }
        return prev[n];
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int v = heap.peek();
            Integer c = delayed.get(v);
            if (c != null && c > 0) {
                if (c == 1) delayed.remove(v);
                else delayed.put(v, c - 1);
                heap.poll();
            } else break;
        }
    }

    private void rebalance() {
        if (loSize > hiSize + 1) {
            int v = lo.poll();
            loSize--; loSum -= v;
            hi.offer(v);
            hiSize++; hiSum += v;
            prune(lo);
        } else if (loSize < hiSize) {
            int v = hi.poll();
            hiSize--; hiSum -= v;
            lo.offer(v);
            loSize++; loSum += v;
            prune(hi);
        }
    }

    private void add(int num) {
        if (lo.isEmpty() || num <= lo.peek()) {
            lo.offer(num);
            loSize++; loSum += num;
        } else {
            hi.offer(num);
            hiSize++; hiSum += num;
        }
        rebalance();
    }

    private void remove(int num) {
        delayed.merge(num, 1, Integer::sum);
        if (num <= lo.peek()) {
            loSize--; loSum -= num;
            if (num == lo.peek()) prune(lo);
        } else {
            hiSize--; hiSum -= num;
            if (!hi.isEmpty() && num == hi.peek()) prune(hi);
        }
        rebalance();
    }
}