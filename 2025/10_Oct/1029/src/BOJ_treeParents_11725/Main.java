package BOJ_treeParents_11725;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int[] parent;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_treeParents_11725/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int N = sc.nextInt(); // 노드 개수
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		} // 빈 그래프 생성
		
		//==== 그래프 연결 ====
		for (int i = 0; i < N - 1; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		//==== 변수 초기화 ====
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		
		//==== dfs 함수 실행 ====
		dfs(1);
		for (int i = 2; i < N + 1; i++) {
			System.out.println(parent[i]);
		}
	} // main
	
	public static void dfs(int node) {
		// 1. 탐색한 노드임을 표시
		visited[node] = true;
		// 2. 해당 노드의 이웃 노드 탐색
		for (int neighbor : graph.get(node)) {
			// 2-1. 탐색한 노드가 아닌 경우 -> 탐색
			if (!visited[neighbor]) {
				// 2-1-1. 탐색한 노드임을 표시
				visited[neighbor] = true;
				parent[neighbor] = node;
				// 2-1-2. 다음 노드 탐색 
				dfs(neighbor);
			}
		}
	}

}
