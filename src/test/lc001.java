import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class lc001 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m1 = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (m1.containsKey(target - nums[i]))
                return new int[] {m1.get(target - nums[i]), i};
            if(!m1.containsKey(nums[i]))
                m1.put(nums[i], i);
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

//        int[] nums = {3,2,4};
//        int target = 6;

//        int[] nums = {3,3};
//        int target = 6;

//        //special
//        int[] nums = {3,2,3,5,15};
//        int target = 8;

        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}