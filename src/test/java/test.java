import org.apache.commons.collections.map.MultiKeyMap;

import java.util.*;

public class test {
    public static void main(String[] args) {
        int nums[] = {0,0,0,0};
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        MultiKeyMap map =new MultiKeyMap();
        Set<String> key=new HashSet<String>();
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (j - i>1 &&j>0) {
            if(nums[i]+nums[j]>0){
                int k=i-1;
            }
        }
        System.out.println(results.toString());
    }
}
