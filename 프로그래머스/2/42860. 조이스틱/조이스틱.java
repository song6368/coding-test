class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int move = name.length() - 1; // 좌우 커서 기본 이동 길이
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            // 알파벳 이동 계산: A부터 위로/아래로 가는 최소 거리
            int upDownMoves = Math.min(c - 'A', 'Z' - c + 1);
            answer += upDownMoves;

            // 좌우 커서 이동 최적화 계산
            int nextIndex = i + 1;
            
            // 연속된 'A'를 지나칠 수 있는 경우 처리
            // 처음에는 문자열길이만큼 A로 채워져있습니다.
            while (nextIndex < name.length() && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }

            // A가 아닌 문자 인덱스로 이동시 좌로 이동하는게 빠른지 우로 이동하는게 빠른지 판별
            move = Math.min(move, i + name.length() - nextIndex + Math.min(i, name.length() - nextIndex));
        }
        
        answer += move;
        
        return answer;
    }
}