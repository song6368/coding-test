function solution(n, wires) {
    let answer = n; // 초기 값은 최대한 큰 값으로 설정
    const graph = Array.from({ length: n + 1 }, () => []);

    // 그래프 구성 (양방향 연결)
    wires.forEach(([v1, v2]) => {
        graph[v1].push(v2);
        graph[v2].push(v1);
    });

    // 송전탑의 개수를 세는 DFS 함수
    function dfs(node, visited) {
        visited[node] = true;
        let count = 1; // 현재 노드 포함
        graph[node].forEach(neighbor => {
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited); // 연결된 모든 노드의 수를 계산
            }
        });
        return count;
    }

    // 모든 전선을 하나씩 끊어가며 계산
    wires.forEach(([v1, v2]) => {
        const visited = Array(n + 1).fill(false);

        // 전선을 끊는다(양방향이므로 둘 다 끊음)
        visited[v1] = true; // v1부터 시작해서 DFS를 돌린다
        const count = dfs(v2, visited); // 끊어진 한쪽의 노드 수 계산

        // 두 그룹의 차이를 계산하여 최소 차이를 찾음
        const diff = Math.abs((n - count) - count);
        answer = Math.min(answer, diff);
    });

    return answer;
}
