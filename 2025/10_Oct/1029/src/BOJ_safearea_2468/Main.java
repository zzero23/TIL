package BOJ_safearea_2468;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// dfs
public class Main {
	static int[][] area;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_safearea_2468/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		N = sc.nextInt(); // 행열 수
		int maxRain = Integer.MIN_VALUE;
		area = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				area[i][j] = sc.nextInt();
				maxRain = (area[i][j] > maxRain) ? area[i][j] : maxRain;
			}
		} // 지역 높이 채우기
		
		//==== 변수 초기화 ====
		visited = new boolean[N][N];
		
		//==== 모든 경우의 수 탐색 ====
		int max = Integer.MIN_VALUE;
		for (int rain = 0; rain < maxRain; rain++) {
			int count = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 1. 잠기지 않고 탐색하지 않은 지역일 경우 탐색
					if (area[i][j] > rain && !visited[i][j]) {
						dfs(rain, i, j);
						count++;
					}
				}
			}
			max = (count > max) ? count : max;
		}
		
		//==== 출력 ==== 
		System.out.println(max);

	}
	
	public static void dfs(int rain, int r, int c) {
		// 1. 탐색한 지역 표시
		visited[r][c] = true;
		// 2. 사방탐색
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 지역에서 벗어나면 무시 -> continue
			if (curR < 0 || curR >= N || curC < 0 || curC >= N) {
				continue;
			}
			// 2-2. 잠기지 않고 탐색하지 않은 지역일 경우 -> 탐색
			if (area[curR][curC] > rain && !visited[curR][curC]) {
				// 2-2-1. 다음 영역 탐색
				dfs(rain, curR, curC);
			}
		}
	}

}
