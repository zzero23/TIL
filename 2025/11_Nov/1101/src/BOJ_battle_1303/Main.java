package BOJ_battle_1303;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// dfs
public class Main {
	static int N;
	static int M;
	static char[][] area;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int count;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_battle_1303/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);

		//==== 입력 데이터 ====
		M = sc.nextInt(); // 행
		N = sc.nextInt(); // 열
		sc.nextLine();
		
		//==== 변수 초기화 ====
		area = new char[N][M];
		visited = new boolean[N][M];
		
		//==== 영역 채우기 ====
		for (int i = 0; i < N; i++) {
			char[] line = sc.nextLine().toCharArray();
			area[i] = line;
		}
		
		//==== 탐색 ====
		int white = 0;
		int blue = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && area[i][j] == 'W') {
					count = 0;
					dfs(i, j);
					white += (int)Math.pow(count, 2);
				}
				if (!visited[i][j] && area[i][j] == 'B') {
					count = 0;
					dfs(i, j);
					blue += (int)Math.pow(count, 2);
				}
			}
		}
		
		//==== 출력 ====
		System.out.println(white + " " + blue);
	}
	
	public static void dfs(int r, int c) {
		// 1. 확인 여부 표시
		visited[r][c] = true;
		count++;
		// 2. 사방 탐색 
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			// 2-1. 벽인 경우 pass
			if (curR < 0 || curR >= N || curC < 0 || curC >= M) {
				continue;
			}
			// 2-2. 탐색했고, 같은 팀인지 확인
			if (!visited[curR][curC] && area[curR][curC] == area[r][c]) {
				dfs(curR, curC);
			}
		}
	}

}
