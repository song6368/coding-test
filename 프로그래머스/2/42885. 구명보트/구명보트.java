import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<>();
        
        for(int i : people){
            list.add(i);
        }
        
        Collections.sort(list);
        
        int total = 0;
        
        int left = 0;
        
        int right = list.size()-1;
        
        while(left<=right){
            if(list.get(left) + list.get(right) <= limit){
                left++;
            }
            
            right--;
            answer++;
        }
        
        return answer;
    }
}