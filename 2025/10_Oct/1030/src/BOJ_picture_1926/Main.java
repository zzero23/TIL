package BOJ_picture_1926;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] gallery;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int M;
	static int count;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_picture_1926/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		
		//==== 데이터 초기화 ====
		gallery = new int[N][M];
		visited = new boolean[N][M];
		
		//==== 데이터 채우기 ====
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				gallery[i][j] = sc.nextInt();
			}
		}
		
		//==== dfs 함수 실행 ====
		int picCount = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//1. 탐색하지 않았고, 갤러리 안에 있는 그림의 일부일 경우 -> 탐색
				if (!visited[i][j] && gallery[i][j] == 1) {
					count = 0;
					dfs(i, j);
					picCount++;
					max = Math.max(max, count);
				}
			}
		}
		
		//==== 출력 =====
		System.out.println(picCount);
		if (picCount == 0) {
			System.out.println(0);
		} else {
			System.out.println(max);	
		}
	}
	
	public static void dfs(int r, int c) {
		// 1. 방문했다고 표시
		visited[r][c] = true;
		count++;
		// 2. 사방 탐색
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 갤러리 밖이면 무시 -> continue
			if (curR < 0 || curR >= N || curC < 0 || curC >= M) {
				continue;
			}
			// 2-2. 탐색하지 않았고, 갤러리 안에 있는 그림의 일부일 경우 -> 탐색
			if (!visited[curR][curC] && gallery[curR][curC] == 1) {
				// 2-3. 다음 영역 탐색
				dfs(curR, curC);
			}
		}
	}

}
