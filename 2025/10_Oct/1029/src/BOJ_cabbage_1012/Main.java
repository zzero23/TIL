package BOJ_cabbage_1012;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] field; // 원본 리스트 (배추밭)
	static boolean[][] visited; // 방문 리스트
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static int N; // 열
	static int M; // 행
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_cabbage_1012/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수
		for (int tc = 0; tc < T; tc++) {
			//==== 입력 데이터 ====
			N = sc.nextInt(); // 열
			M = sc.nextInt(); // 행
			int K = sc.nextInt(); // 배추 개수
			
			//==== 배추 심기 ====
			field = new int[M][N];
			for (int i = 0; i < K; i++) {
				int c = sc.nextInt();
				int r = sc.nextInt();
				field[r][c] = 1;
			}
			
			//==== 변수 초기화 ====
			visited = new boolean[M][N];
			
			//==== 배추 탐색 ====
			int count = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					// 1. 밭 바깥이면 무시
					if (i < 0 || i >= M || j < 0 || j >= N) {
						continue;
					}
					// 2. 탐색하지 않은 배추인 경우 -> 탐색
					if (field[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			
			//==== 출력 ====
			System.out.println(count);
		} // for : 테스트 케이스

	} // main
	
	public static void dfs(int r, int c) {
		// 1. 확인했다고 표시
		visited[r][c] = true;
		// 2. 사방 탐색
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 밭 바깥이면 무시 -> continue
			if (curR < 0 || curR >= M || curC < 0 || curC >= N) {
				continue;
			}
			// 2-2. 밭이면, 배추가 심겨있는지 확인 -> 심겨있으면 탐색
			if (field[curR][curC] == 1) {
				// 2-2-1. 탐색한 배추가 아닐 경우 -> 탐색
				if (!visited[curR][curC]) {
					// 2-2-1-1. 확인했다고 표시
					visited[curR][curC] = true;
					// 2-2-1-2. 다음 밭 확인
					dfs(curR, curC);
				}
			}
		}
	}

}
