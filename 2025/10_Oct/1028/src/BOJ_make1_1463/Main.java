package BOJ_make1_1463;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 중복 순열
//==================================
// 1028 - 시간초과 (DP로 풀어야 한다고 함)
//==================================
public class Main {
	static int[] list = {1, 2, 3}; // 원본 리스트
	static int[] result; // 결과 리스트
	static int N; // 계산할 데이터
	static int realN;
	static boolean success = false;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_make1_1463/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		// #### 입력 데이터 ####
		N = sc.nextInt(); // 계산할 데이터
		realN = N;
		// #### repeatPermutation() 함수 사용 ####
		int r = 0;
		while (!success) {
			r++;
			result = new int[r];
			permutation(0, r);
		}
		
		System.out.println(r);
	}
	
	public static void permutation(int idx, int r) {
		// 1. 탈출 조건
		if (idx == r) {
			for (int e : result) {
				if (N % e == 0) { N /= e; }
				if (e == 1) { N -= e; }
			}
			if (N == 1) {
				success = true;
			}
			N = realN;
			return;
		}
		
		// 2. 모든 데이터 탐색
		for (int i = 0; i < 3; i++) {
			// 2-1. 데이터 담기
			result[idx] = list[i];
			// 2-2. 다음 데이터 탐색
			permutation(idx + 1, r);
		}
	}

}
