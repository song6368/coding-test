import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < dungeons.length; i++){
            list.add(i);
        }
        
        // 모든 경로의 최대 던전 탐험 수를 저장할 Set
        HashSet<Integer> set = new HashSet<>();
        
        // DFS를 통해 던전 탐험 시작
        for(int i = 0; i < dungeons.length; i++){
            set.add(process(k, i, new LinkedList<>(list), dungeons, 0));
        }
        
        // 모든 경로에서 탐험한 던전 수 중 최대값 찾기
        Integer maxValue = Collections.max(set);
        answer = maxValue; // 최대값 설정
        
        return answer;
    }
    
    // 재귀적으로 던전 탐험을 처리하는 메서드
    public int process(int life, int idx, List<Integer> list, int[][] dungeons, int count) {
        list.remove((Integer) idx); // 던전을 방문했으므로 목록에서 제거
        
        // 해당 던전의 최소 피로도를 만족하는 경우
        if (dungeons[idx][0] <= life) {
            life -= dungeons[idx][1]; // 소모 피로도만큼 생명 감소
            
            // 생명이 0 이상일 때만 던전 탐험
            if (life >= 0) {
                count++; // 던전 탐험 횟수 증가
                int maxCount = count; // 현재 경로에서 탐험한 던전 수 추적
                
                // 남은 던전들에 대해 다시 탐험
                for (int i = 0; i < list.size(); i++) {
                    maxCount = Math.max(maxCount, process(life, list.get(i), new LinkedList<>(list), dungeons, count));
                }
                
                return maxCount; // 최대 탐험 횟수 반환
            }
        }
        return count; // 더 이상 탐험할 수 없으면 현재까지 탐험한 횟수 반환
    }
}
