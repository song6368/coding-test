import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(); // 결과를 담을 StringBuilder
        
        int len = number.length() - k; // 남겨야 할 숫자 개수
        
        // Stack 사용
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : number.toCharArray()) {
            // 현재 숫자가 Stack의 마지막 숫자보다 크고, k가 남아 있다면
            while (!stack.isEmpty() && k > 0 && stack.peekLast() < c) {
                stack.removeLast(); // Stack의 마지막 숫자 제거
                k--; // 제거한 개수 감소
            }
            stack.addLast(c); // 현재 숫자 추가
        }
        
        // 남은 k가 있을 경우, stack에서 뒤에서부터 제거
        while (k > 0) {
            stack.removeLast();
            k--;
        }

        // Stack에서 결과 문자열 생성
        while (!stack.isEmpty()) {
            answer.append(stack.removeFirst()); // 처음부터 제거하며 결과 생성
        }

        return answer.toString(); // 결과 반환
    }
}
