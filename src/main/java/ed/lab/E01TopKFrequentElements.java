package ed.lab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
//https://leetcode.com/problems/top-k-frequent-elements/
public class E01TopKFrequentElements {
    HashMap<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Integer> Maxheap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
    HashSet<Integer> set = new HashSet<>();
    public void add(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
    }

    void set(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        add(nums);
        set(nums);
        for (int value : set) {
            Maxheap.add(value);
        }
        for (int i = 0; i < k; i++) {
            result[i] = Maxheap.poll();
        }
        return result;
    }
}
