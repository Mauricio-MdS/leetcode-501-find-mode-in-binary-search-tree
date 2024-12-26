import java.util.*;

class Solution {

    private Map<Integer, Integer> valueToFrequency = new HashMap<>();
    private Map<Integer, Set<Integer>> frequencyToValues = new HashMap<>();

    public int[] findMode(TreeNode root) {
        calculateFrequencies(root);
        int maxFrequency =  frequencyToValues.keySet().stream().max(Comparator.naturalOrder()).orElseThrow();
        return frequencyToValues.get(maxFrequency).stream().mapToInt(x -> x).toArray();
    }

    private void calculateFrequencies(TreeNode root) {
        if (root == null) return;

        calculateFrequencies(root.left);

        int frequency = valueToFrequency.getOrDefault(root.val, 0);
        if (frequency != 0) frequencyToValues.get(frequency).remove(root.val);
        frequency++;
        valueToFrequency.put(root.val, frequency);
        Set<Integer> numbers = frequencyToValues.getOrDefault(frequency, new HashSet<>());
        numbers.add(root.val);
        frequencyToValues.put(frequency, numbers);

        calculateFrequencies(root.right);
    }


}