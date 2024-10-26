import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 그래프 초기화
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        // 각 도시에 대해 연결된 도시와 요금을 추가
        for (int[] fare : fares) {
            graph.putIfAbsent(fare[0], new ArrayList<>());
            graph.putIfAbsent(fare[1], new ArrayList<>());
            graph.get(fare[0]).add(new int[]{fare[1], fare[2]}); // fare[1]에 도달하는 요금 fare[2]
            graph.get(fare[1]).add(new int[]{fare[0], fare[2]}); // fare[0]에 도달하는 요금 fare[2]
        }

        // 출발지에서 모든 도시까지의 최단 경로 계산
        int[] distanceFromS = dijkstra(n, s, graph);
        int[] distanceFromA = dijkstra(n, a, graph);
        int[] distanceFromB = dijkstra(n, b, graph);

        // 최소 요금 계산
        int answer = Integer.MAX_VALUE; // 최솟값 초기화
        for (int i = 1; i <= n; i++) {
            if (distanceFromS[i] == Integer.MAX_VALUE || distanceFromA[i] == Integer.MAX_VALUE || distanceFromB[i] == Integer.MAX_VALUE) {
                continue; // 경로가 존재하지 않는 경우
            }
            // 출발지에서 i까지의 요금 + A가 i까지의 요금 + B가 i까지의 요금
            answer = Math.min(answer, distanceFromS[i] + distanceFromA[i] + distanceFromB[i]);
        }

        return answer; // 최소 요금 반환
    }

    // Dijkstra 알고리즘 구현
    private int[] dijkstra(int n, int start, Map<Integer, List<int[]>> graph) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0]; // 현재 도시
            int dist = current[1]; // 현재 도시까지의 요금

            if (dist > distance[node]) {
                continue; // 더 큰 요금이면 무시
            }

            // 현재 도시와 연결된 모든 도시에 대해
            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = neighbor[0]; // 다음 도시
                int fare = neighbor[1]; // 다음 도시까지의 요금

                // 최소 요금 업데이트
                if (distance[node] + fare < distance[nextNode]) {
                    distance[nextNode] = distance[node] + fare;
                    pq.offer(new int[]{nextNode, distance[nextNode]});
                }
            }
        }

        return distance; // 시작 도시에서 모든 도시까지의 최소 요금 반환
    }
}