package BOJ_permutation_1182;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 중복이 없는 조합 - 수열
public class Main {
	static int[] list;
	static int[] result;
	static int N;
	static int S; // 최종 합
	static int count = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_permutation_1182/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		// ==== 입력 데이터 ====
		N = sc.nextInt(); // 원본 데이터 수
		S = sc.nextInt(); // 최종 합
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		} // 리스트 채우기
		
		// ==== combination() 함수 실행 ====
		for (int r = 1; r < N+1; r++) {
			result = new int[r];
			combination(0, 0, r);
		}
		
		// ==== 출력 ====
		System.out.println(count);

	}
	
	public static void combination(int start, int idx, int r) {
		// 1. 탈출 조건
		if (idx == r) {
			int sum = 0;
			for (int e : result) {
				sum += e;
			}
			if (sum == S) {
				count++;
			}
			return;
		}
		// 2. start부터 원소 탐색
		for (int i = start; i < N; i++) {
			// 2-1. 결과 리스트에 원소 담기
			result[idx] = list[i];
			// 2-2. 다음 결과 리스트에 올 원소 찾기 (재귀)
			combination(i + 1, idx + 1, r);
		}
	}

}
