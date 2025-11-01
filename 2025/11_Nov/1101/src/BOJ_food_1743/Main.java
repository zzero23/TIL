package BOJ_food_1743;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	static int[][] room;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int M;
	static int count;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_food_1743/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		int K = sc.nextInt(); // 음식물 쓰레기 개수
		
		//==== 데이터 초기화 ====
		room = new int[N][M];
		visited = new boolean[N][M];
		int max = Integer.MIN_VALUE;
		
		//==== 행렬 채우기 ====
		for (int i = 0; i < K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			room[r-1][c-1] = 1;
		}
		
		//==== 탐색 ====
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && room[i][j] == 1) {
					count = 0;
					dfs(i, j);
					max = (count > max) ? count : max;
				}
			}
		}
		
		//==== 출력 ====
		System.out.println(max);

	}
	
	public static void dfs(int r, int c) {
		// 1. 방문 영역 표시
		visited[r][c] = true;
		count++;
		// 2. 사방 탐색 
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 벽에 막히면 돌아가기
			if (curR < 0 || curR >= N || curC < 0 || curC >= M) {
				continue;
			}
			// 2-2. 방문한 곳이 아니고 음식물일 경우 -> 탐색
			if (!visited[curR][curC] && room[curR][curC] == 1) {
				dfs(curR, curC);
			}
		}
	}

}
