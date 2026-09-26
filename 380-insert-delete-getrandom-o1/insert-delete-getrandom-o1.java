import java.util.*;

class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> valToIdx;
    private Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        valToIdx = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (valToIdx.containsKey(val)) {
            return false;
        }

        valToIdx.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIdx.containsKey(val)) {
            return false;
        }

        int indexToRemove = valToIdx.get(val);
        int lastElement = nums.get(nums.size() - 1);

        nums.set(indexToRemove, lastElement);
        valToIdx.put(lastElement, indexToRemove);

        nums.remove(nums.size() - 1);
        valToIdx.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = rand.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}