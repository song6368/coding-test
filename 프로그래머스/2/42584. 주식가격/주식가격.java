class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++;  // 기간을 증가시킴
                if (prices[i] > prices[j]) {
                    // 가격이 떨어지면 종료
                    break;
                }
            }
        }

        return answer;
    }
}
