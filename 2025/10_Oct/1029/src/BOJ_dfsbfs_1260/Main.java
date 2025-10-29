package BOJ_dfsbfs_1260;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static boolean visited[]; // 방문 리스트
	static List<List<Integer>> graph = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_dfsbfs_1260/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 1 ====
		int N = sc.nextInt(); // 정점의 개수
		int M = sc.nextInt(); // 간선의 개수
		int V = sc.nextInt(); // 시작점
		
		//==== 변수 초기화 ====
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		} // 그래프 내에 빈 그래프 생성
		visited = new boolean[N + 1];
		
		//==== 입력 데이터 2 ====
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for (int i = 1; i < N + 1; i++) {
			Collections.sort(graph.get(i));
		}
		
		//==== dfs() 함수 실행 ====
		dfs(V);
		System.out.println();
		visited = new boolean[N + 1];
		//==== bfs() 함수 실행 ====
		bfs(V);

	}
	
	public static void dfs(int node) {
		// 1. 방문 리스트에 탐색 여부 표시
		visited[node] = true;
		System.out.print(node + " ");
		// 2. 현재 노드와 관련있는 모든 노드 탐색
		for (int neighbor : graph.get(node)) {
			// 2-1. 탐색하지 않은 노드일 경우 -> 탐색
			if (!visited[neighbor]) {
				// 2-1-1. 탐색했다고 표시
				visited[neighbor] = true;
				// 2-1-2. 다음 노드 탐색
				dfs(neighbor);
			}
		}
	}
	
	public static void bfs(int node) {
		// 1. queue 생성 및 queue에 담기
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(node);
		
		// 2. 방문 리스트에 탐색 여부 표시
		visited[node] = true;
		// 3. 큐가 빌 때까지 반복
		while(!queue.isEmpty()) {
			// 3-1. 큐에서 노드를 하나 꺼냄
			int currentNode = queue.poll();
			System.out.print(currentNode + " ");
			// 3-2. 현재 노드와 관련된 노드 탐색
			for (int neighbor : graph.get(currentNode)) {
				// 3-2-1. 탐색하지 않았을 경우 -> 탐색
				if (!visited[neighbor]) {
					// 3-2-1-1. 탐색했다고 표시
					visited[neighbor] = true;
					// 3-2-1-2. 다음 노드 탐색
					queue.offer(neighbor);
				}
			}
		}
	}

}
