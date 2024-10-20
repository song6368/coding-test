import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] memo = new int[triangle.length][triangle.length];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return dfs(0, 0, triangle, memo);
    }
    
    public int dfs(int idx1, int idx2, int[][] triangle, int[][] memo) {
        
        if (idx1 == triangle.length - 1) {
            return triangle[idx1][idx2];
        }
        
        if (memo[idx1][idx2] != -1) {
            return memo[idx1][idx2];
        }
        
        int leftPath = dfs(idx1 + 1, idx2, triangle, memo);
        int rightPath = dfs(idx1 + 1, idx2 + 1, triangle, memo);
        
        memo[idx1][idx2] = triangle[idx1][idx2] + Math.max(leftPath, rightPath);
        
        return memo[idx1][idx2];
    }
}
