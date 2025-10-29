package BOJ_numbering_2667;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] graph;
	static boolean[][] visited;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static int N;
	static int count;
	static List<Integer> countlist = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_numbering_2667/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);

		//==== 입력 데이터 ====
		N = sc.nextInt(); // 지도의 크기
		
		sc.nextLine();
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			String num = sc.nextLine();
			for (int j = 0; j < N; j++) {
				graph[i][j] = num.charAt(j) - '0';
			}
		} // 지도 받아오기
		
		//==== 기본 변수 초기화 ====
		visited = new boolean[N][N];
		
		//==== dfs 함수 실행 ====
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i < 0 || i >= N || j < 0 || j >= N) {
					continue;
				}
				if (graph[i][j] == 1 && !visited[i][j]) {
					count = 0;
					dfs(i, j);
					countlist.add(count);
				}
			}
		}
		
		//==== countlist 정렬 ====
		Collections.sort(countlist);
		
		//==== 출력 ====
		System.out.println(countlist.size());
		for (int i : countlist) {
			System.out.println(i);
		}
		
	}
	
	public static void dfs(int r, int c) {
		// 1. 방문한 노드 표시
		visited[r][c] = true;
		count++;
		
		// 2. 사방 탐색
		for (int i = 0; i < 4; i++) {
			int currentR = r + dr[i];
			int currentC = c + dc[i];
			
			// 2-1. 범위를 벗어나면 pass
			if (currentR < 0 || currentR >= N || currentC < 0 || currentC >= N) {
				continue;
			}
			
			// 2-2. 해당 범위가 1인지 -> 1이면 탐색
			if (graph[currentR][currentC] == 1) {
				// 2-2-1. 방문한 곳이 아닌 경우 -> 탐색
				if (!visited[currentR][currentC]) {
					// 2-2-1-1. 방문한 곳이라고 표시
					visited[currentR][currentC] = true;
					// 2-2-1-2. 다음 원소 탐색
					dfs(currentR, currentC);
				}
			}
		}
	}

}
