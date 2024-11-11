package AlgorithmStudy;

import java.util.*;
/**
 * @title  : 네트워크
 * @author : SI YOUNG
 */

public class 네트워크 {
    private static List<HashSet<Integer>> graph;
    private static boolean[] visited;  // 방문 여부를 추적하는 배열
    private static int count;

    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        visited = new boolean[n];  // 크기가 n인 방문 배열 초기화
        count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                graph.add(new HashSet<>());  // 새로운 네트워크를 위해 HashSet 추가
                dfs(i, n, computers);
                count++;
            }
        }

        return count;
    }

    static void dfs(int num, int n, int[][] computers) {
        visited[num] = true;  // 현재 노드를 방문 처리

        for (int i = 0; i < n; i++) {
            if (computers[num][i] == 1 && !visited[i]) {  // 연결되어 있고 아직 방문하지 않은 경우
                graph.get(count).add(i);
                dfs(i, n, computers);
            }
        }
    }
}
