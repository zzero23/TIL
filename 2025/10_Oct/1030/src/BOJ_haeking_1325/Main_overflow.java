package BOJ_haeking_1325;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// dfs
//==================================
// 10/30 - 시간초과
//==================================
public class Main_overflow {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int count;
	static int[] result;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_haeking_1325/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 데이터 입력 ====
		int N = sc.nextInt(); // 노드 개수
		int M = sc.nextInt(); // 간선 개수
		
		//==== 데이터 초기화 ====
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		visited = new boolean[N + 1];
		result = new int[N + 1];
		
		//==== 데이터 연결 ====
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(v).add(u);
		}
		
		//==== dfs 함수 실행 ====
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < N+1; i++) {
			visited = new boolean[N + 1];
			count = 0;
			dfs(i);
			result[i] = count;
			max = Math.max(max, count);
		}
		
		//==== 한번에 가장 많은 컴퓨터 해킹 ====
		List<Integer> com = new ArrayList<>();
		for (int i = 1; i < N+1; i++) {
			if (result[i] == max) {
				com.add(i);
			}
		}
		
		//==== 출력 ====
		Collections.sort(com);
		for (int i : com) {
			System.out.print(i + " ");
		}

	}
	
	public static void dfs(int node) {
		// 1. 데이터 확인했다고 표시
		visited[node] = true;
		count++;
		// 2. 현재 노드와 관련있는 노드 탐색
		for (int neighbor : graph.get(node)) {
			// 2-1. 탐색한 노드가 아닌 경우 -> 탐색
			if (!visited[neighbor]) {
				dfs(neighbor);
			}
		}
	}

}
