package BOJ_virus_2606;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// dfs
public class Main {
	static boolean[] visited;
	static List<List<Integer>> graph = new ArrayList<>();
	static int count = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_virus_2606/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int N = sc.nextInt(); // 컴퓨터 (노드) 수
		int M = sc.nextInt(); // 간선 수 (연결된 수)
		
		//==== 데이터 연결 (양방향) ====
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		} // 빈 노드 생성
		
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		} // 데이터 연결
		
		//==== 변수 초기화 ====
		visited = new boolean[N + 1];
		
		//==== dfs 함수 실행 ====
		dfs(1);
		
		//==== 출력 ====
		System.out.println(count);
	}
	
	public static void dfs(int node) {
		// 1. 탐색했다고 표시
		visited[node] = true;
		// 2. 연관되어 있는 모든 노드 탐색
		for (int neighbor : graph.get(node)) {
			// 2-1. 탐색하지 않은 노드일 경우 -> 탐색 
			if (!visited[neighbor]) {
				// 2-1-1. 탐색했다고 표시
				visited[neighbor] = true;
				count++;
				// 2-1-2. 다음 노드 탐색
				dfs(neighbor);
			}
		}
	}

}
