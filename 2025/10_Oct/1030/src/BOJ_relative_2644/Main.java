package BOJ_relative_2644;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// bfs
public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] chon;
	static int endNode;
	static int count = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_relative_2644/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int N = sc.nextInt(); // 사람 수
		int startNode = sc.nextInt(); // 대상A
		endNode = sc.nextInt(); // 대상B
		int M = sc.nextInt(); // 간선 수
		
		//==== 데이터 초기화 ====
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		chon = new int[N + 1];
		
		//==== 데이터 연결 ====
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		//==== bfs 함수 실행 ====
		bfs(startNode);
		
		if (chon[endNode] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(chon[endNode]);
		}

	} // main
	
	public static void bfs(int node) {
		// 1. queue 생성
		Queue<Integer> queue = new LinkedList<>();
		// 2. queue에 현재 노드 담기
		queue.offer(node);
		chon[node] = 0;
		// 3. queue가 빌 때까지 반복
		while (!queue.isEmpty()) {
			// 3-1. 다음 원소 확인
			int currentNode = queue.poll();
			if (currentNode == endNode) {
				return;
			}
			for (int neighbor : graph.get(currentNode)) {
				if (chon[neighbor] == 0) {
					chon[neighbor] = chon[currentNode] + 1;
					queue.offer(neighbor);
				}
			}
		}
	}
	

}
