package BOJ_RGB_1149;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 중복있는 순열
//=================================
// 10/29 - 시간초과 (DP로 풀어야함)
//=================================
public class Main {
	static int[][] room;
	static int[] color = new int[3]; // 원본 리스트 (색)
	static int[] result; // 결과 리스트 (집)
	static int N; // 방 수
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_RGB_1149/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 데이터 입력 ====
		N = sc.nextInt(); // 방 수
		room = new int[N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				room[i][j] = sc.nextInt();
			}
		}
		
		//==== 기본 변수 준비 ====
		for (int i = 0; i < 3; i++) {
			color[i] = i + 1;
		} // 원본 리스트 준비 (R : 1 / G : 2 / B : 3)
		result = new int[N];
		
		//==== repeatPermutation() 함수 실행 ====
		repeatPermutation(0);
		
		System.out.println(min);
	} // main
	
	public static void repeatPermutation(int idx) {
		// 1. 탈출 조건
		if (idx == N) {
			// 1-1. 인접한 방이 색이 같으면 pass
			for (int i = 1; i < N - 1; i++) {
				if (result[i - 1] == result[i] || result[i + 1] == result[i]) {
					return;
				}
			}
//			System.out.println(Arrays.toString(result));
			// 1-2. 인접한 방이 색이 같지 않은 경우
			calculation();
			return;
		}
		// 2. 모든 원소 탐색
		for (int i = 0; i < 3; i++) {
			// 2-1. 결과 담기
			result[idx] = color[i];
			// 2-2. 다음 원소 탐색 (재귀)
			repeatPermutation(idx + 1);
		}
	}
	
	public static void calculation() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			int col = result[i];
			for (int j = 0; j < 3; j++) {
				if (j + 1 == col) {
					sum += room[i][j];
				}
			}
		}
		min = (sum < min) ? sum : min;
	}
}
