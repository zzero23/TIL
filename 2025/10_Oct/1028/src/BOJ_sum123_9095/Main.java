package BOJ_sum123_9095;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 중복 순열
public class Main {
	static int[] list = {1, 2, 3}; // 원본 리스트
	static int[] result; // 결과 리스트
	static int N; // 만들어야 하는 값
	static int R; // 뽑을 원소 수
	static int count; // 횟수
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_sum123_9095/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수
		for (int tc = 0; tc < T; tc++) {
			// #### 데이터 입력 및 초기화 ####
			N = sc.nextInt(); // 만들어야 하는 값
			count = 0;
			
			// #### permutation() 함수 실행 ####
			for (int r = 1; r < N + 1; r++) {
				R = r;
				result = new int[R];
				permutation(0);
			}
			
			System.out.println(count);
		} // 테스트 반복
	} // main
	
	public static void permutation(int idx) {
		// 1. 탈출 조건
		if (idx == R) {
			int sum = 0;
			for (int r : result) {
				sum += r;
			}
			// 1-1. 합이 나온 경우 -> count
			if (sum == N) {
				count++;
			}
			return;
		}
		
		// 2. 모든 원소 탐색
		for (int i = 0; i < 3; i++) {
			// 2-1. 결과 리스트에 원소 담기
			result[idx] = list[i];
			// 2-2. 다음 원소 탐색 (재귀)
			permutation(idx + 1);
		}
	}

}
