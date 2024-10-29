import java.util.*;

class Solution {
    public int[][] solution(int n) {
        // 이동 과정을 저장할 리스트 (각 이동은 [시작 기둥, 도착 기둥] 형태로 저장됨)
        List<int[]> moves = new ArrayList<>();
        
        // 하노이탑 재귀 함수 호출 (n개의 원반을 1번 기둥에서 3번 기둥으로 이동)
        hanoi(n, 1, 3, 2, moves);
        
        // List<int[]> moves를 2차원 배열로 변환하여 answer 배열에 저장
        int[][] answer = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++) {
            answer[i] = moves.get(i);
        }
        
        return answer;  // 전체 이동 과정을 배열로 반환
    }
    
    // 하노이탑 재귀 함수
    // n: 이동할 원반의 개수
    // start: 시작 기둥 번호
    // end: 도착 기둥 번호
    // aux: 보조 기둥 번호
    // moves: 이동 과정을 저장할 리스트
    private void hanoi(int n, int start, int end, int aux, List<int[]> moves) {
        // 기저 사례: 원반이 1개일 때
        if (n == 1) {
            // 원반을 시작 기둥에서 도착 기둥으로 이동
            moves.add(new int[]{start, end});
            return;
        }
        
        // Step 1: n-1개의 원반을 보조 기둥으로 이동 (end를 보조 기둥으로 사용)
        hanoi(n - 1, start, aux, end, moves);
        
        // Step 2: 가장 큰 원반을 목적 기둥으로 이동
        moves.add(new int[]{start, end});
        
        // Step 3: n-1개의 원반을 목적 기둥으로 이동 (start를 보조 기둥으로 사용)
        hanoi(n - 1, aux, end, start, moves);
    }
}
