package BOJ_linked_11724;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// dfs
public class Main {
	static List<List<Integer>> graph = new ArrayList<>(); // 원본 리스트
	static boolean[] visited;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_linked_11724/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int N = sc.nextInt(); // 정점 개수
		int M = sc.nextInt(); // 간선 개수
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		} // 빈 배열 생성
		
		//==== 그래프 연결 ====
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		//==== 변수 초기화 ====
		visited = new boolean[N + 1];
		//==== dfs 함수 실행 ====
		int count = 0;
		for (int i = 1; i < N + 1; i++) {
			if (!visited[i]) {
				dfs(i);
				count++;
			}
		}
		
		//==== 출력 ====
		System.out.println(count);
	}
	
	public static void dfs(int node) {
		// 1. 노드 탐색 표시
		visited[node] = true;
		// 2. 연결된 노드 탐색
		for (int neighbor : graph.get(node)) {
			// 2-1. 탐색되지 않은 이웃 노드일 경우 -> 탐색
			if (!visited[neighbor]) {
				// 2-1-1. 탐색했다고 표시
				visited[neighbor] = true;
				// 2-1-2. 다음 이웃 노드 탐색
				dfs(neighbor);
			}
		}
	}

}
