package BOJ_island_4963;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] ocean;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	static int N;
	static int M;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_island_4963/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);

		while (true) {
			N = sc.nextInt(); // 행
			M = sc.nextInt(); // 열
			//==== 테스트 종료 조건 ====
			if (N == 0 && M == 0) {
				return;
			} // 0이 두개 주어지면 종료
			
			//==== 바다 범위 채우기 ====
			ocean = new int[M][N];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					ocean[i][j] = sc.nextInt();
				}
			}
			
			//==== 변수 초기화 ====
			int count = 0;
			visited = new boolean[M][N];
			
			//==== 바다 탐색 ====
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					// 1. 바다 밖이면 건너뛰기
					if (i < 0 || i >= M || j < 0 || j >= N) {
						continue;
					}
					// 2. 방문하지 않은 땅이면 탐색
					if (ocean[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			
			//==== 출력 ====
			System.out.println(count);
		} // while : 테스트 케이스
	} // main
	
	public static void dfs(int r, int c) {
		// 1. 탐색한 땅으로 표시
		visited[r][c] = true;
		// 2. 팔방탐색
		for (int i = 0; i < 8; i++) {
			int curR = r + dr[i]; // 현재 행
			int curC = c + dc[i]; // 현재 열
			// 2-1. 바다 밖이면 건너뛰기 -> continue
			if (curR < 0 || curR >= M || curC < 0 || curC >= N) {
				continue;
			}
			// 2-2. 방문하지 않은 땅인 경우 -> 탐색 
			if (ocean[curR][curC] == 1 && !visited[curR][curC]) {
				// 2-2-1. 방문한 땅으로 표시
				visited[curR][curC] = true;
				// 2-2-2. 다음 지역 방문
				dfs(curR, curC);
			}
		}
	}

}
