package BOJ_sumofNum2_2003;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 중복 없는 조합
//==================================
//1028 - 시간초과 (투포인터로 풀어야 한다고 함)
//==================================
public class Main {
	static int[] list; // 원본 리스트
	static int[] result; // 결과 리스트
	static int N; // 전체 원소 수
	static int S; // 합
	static int count = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_sumofNum2_2003/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		// ==== 데이터 입력 ====
		N = sc.nextInt(); // 전체 원소 수
		S = sc.nextInt(); // 합
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		} // 데이터 채우기
		
		// ==== combination() 함수 실행 ====
		for (int r = 1; r < N + 1; r++) {
			result = new int[r];
			for (int n = 0; n < N - r + 1; n++) {
				combination(n, 0, r);
			}
		}
		
		// ==== 출력 ====
		System.out.println(count);

	} // main
	
	public static void combination(int start, int idx, int r) {
		// 1. 탈출 조건
		if (idx == r) {
			// 1-1. 합 구하기
			int sum = 0;
			for (int e : result) {
				sum += e;
			}
			// 1-2. S가 나온 경우 카운트
			if (sum == S) {
				count++;
			}
			return;
		}
	
		// 2-1. 결과 리스트에 담기
		result[idx] = list[start];
		// 2-2. 결과 리스트에 담길 다음 원소 탐색
		combination(start+1, idx+1, r);
	}

}
