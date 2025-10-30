package BOJ_haeking_1325;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// dfs
public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int count;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_haeking_1325/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int N = sc.nextInt(); // 노드 수
		int M = sc.nextInt(); // 간선 수
		
		//==== 데이터 초기화 ====
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		visited = new boolean[N + 1];
		int[] result = new int[N + 1];
		
		//==== 데이터 연결 ====
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(v).add(u);
		}
		
		//==== bfs로 데이터 탐색 ====
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < N+1; i++) {
			visited = new boolean[N + 1];
			count = 0;
			bfs(i);
			result[i] = count;
			max = Math.max(max, count);
		}
		
		//===== 가장 깊이 탐색된 애들 탐색
		List<Integer> com = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			if (result[i] == max) {
				com.add(i);
			}
		}
		
		//==== 출력 ====
		Collections.sort(com);
		for (int c : com) {
			System.out.print(c + " ");
		}
	}
	
	public static void bfs(int startnode) {
		// 1. queue 생성
		Queue<Integer> queue = new LinkedList<>();
		// 2. queue에 원소 담기
		queue.offer(startnode);
		visited[startnode] = true;
		// 3. queue에 원소가 없을 때까지 탐색
		while(!queue.isEmpty()) {
			// 3-1. 원소 뺴내기
			int current = queue.poll();
			// 3-2. 연관있는 코드 탐색
			for (int neighbor : graph.get(current)) {
				// 3-2-1. 탐색하지 않은 원소인 경우 
				if (!visited[neighbor]) {
					// 3-2-1-1. 탐색했다고 표시
					visited[neighbor] = true;
					count++;
					queue.offer(neighbor);
				}
			}
				
		}
	}

}
