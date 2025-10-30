package BOJ_area_2583;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] area; // 원본 리스트 (전체 영역)
	static boolean[][] visited; // 방문 리스트
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N; 
	static int M;
	static int count;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_area_2583/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열 
		int K = sc.nextInt(); // 영역 개수
		
		//==== 변수 초기화 ====
		area = new int[N][M];
		visited = new boolean[N][M];
		List<Integer> range = new ArrayList<>();
		
		//==== 영역 채우기 ====
		int[] X = new int[2];
		int[] Y = new int[2];
		for (int i = 0; i < K; i++) {
			//==== 좌표 재설정 ====
			for (int j = 0; j < 2; j++) {
				Y[j] = sc.nextInt();
				X[j] = N - sc.nextInt();
			}
			Arrays.sort(X); Arrays.sort(Y); // 정렬
			//==== 색칠하기 ====
			for (int x = X[0]; x < X[1]; x++) {
				for (int y = Y[0]; y < Y[1]; y++) {
					area[x][y] = 1;
				}
			}
		} // for : 영역 개수만큼 반복
		
		//==== DFS 함수 실행 ====
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 1. 방문한 곳이 아니고, 벽이 아닐 경우
				if (!visited[i][j] && area[i][j] == 0) {
					count = 0;
					dfs(i, j);
					range.add(count);
				}
			}
		}
		
		//==== 출력 ====
		Collections.sort(range);
		System.out.println(range.size());
		for (int i : range) {
			System.out.print(i + " ");
		}

	}
	public static void dfs(int r, int c) {
		// 1. 방문 했다고 표시
		visited[r][c] = true;
		count++;
		// 2. 사방 탐색
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 영역 밖이면 넘기기 -> continue
			if (curR < 0 || curR >= N || curC < 0 || curC >= M) {
				continue;
			}
			// 2-2. 방문 하지 않았고, 벽이 아닐 경우
			if (!visited[curR][curC] && area[curR][curC] == 0) {
				// 2-2-1. 다음 영역 방문
				dfs(curR, curC);
			}
		}
	}

}
