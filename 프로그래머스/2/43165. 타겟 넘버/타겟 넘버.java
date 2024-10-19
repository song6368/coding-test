class Solution {
    public int solution(int[] numbers, int target) {
        return bfs(0, 0, numbers, target);
    }
    
    public int bfs(int idx, int val, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (val == target) {
                return 1;
            } else {
                return 0;
            }
        } else {
            // 다음 인덱스로 이동할 때 값 추가 및 감소하는 경우 모두 시도
            return bfs(idx + 1, val + numbers[idx], numbers, target)
                + bfs(idx + 1, val - numbers[idx], numbers, target);
        }
    }
}
