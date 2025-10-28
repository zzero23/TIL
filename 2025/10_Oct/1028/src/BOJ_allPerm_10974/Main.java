package BOJ_allPerm_10974;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 중복 없는 순열
public class Main {
	static int[] list; // 원본 리스트
	static int[] result; // 결과 리스트
	static boolean[] visited; // 방문 리스트
	static int N; // 원본 리스트 총 원소 수 / 뽑을 원소 수
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_allPerm_10974/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		// #### 입력 데이터 ####
		N = sc.nextInt();
		
		// #### 원본 리스트 ####
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = i + 1;
		} // 리스트 채우기
		visited = new boolean[N];
		result = new int[N];
		
		// #### permutation 함수 실행 ####
		permutation(0);
	} // main
	
	public static void permutation(int idx) {
		// 1. 탈출 조건
		if (idx == N) {
			for (int r : result) {
				System.out.print(r + " ");
			}
			System.out.println();
			return;
		}
		// 2. 전체 원소 탐색
		for (int i = 0; i < N; i++) {
			// 2-1. 탐색하지 않은 원소일 경우 -> 탐색
			if (!visited[i]) {
				// 2-1-1. 결과 리스트에 담기
				result[idx] = list[i];
				// 2-1-2. 탐색한 원소라고 표시
				visited[i] = true;
				// 2-1-3. 다음 결과리스트에 담길 원소 탐색 (재귀)
				permutation(idx + 1);
				// 2-1-4. 원복 (백트래킹)
				visited[i] = false;
			}
		}
	}

}
