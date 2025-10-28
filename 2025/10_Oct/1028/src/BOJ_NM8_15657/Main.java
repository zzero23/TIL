package BOJ_NM8_15657;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// 중복이 있는 조합
public class Main {
	static int[] list; // 원본 리스트
	static int[] result; // 결과 리스트
	static int R; // 뽑는 개수
	static int N; // 원본 총 개수
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_NM8_15657/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		// #### 입력 데이터 ####
		N = sc.nextInt(); // 원본 총 개수
		R = sc.nextInt(); // 뽑는 개수
		list = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = sc.nextInt();
		} // 원본 리스트 채우기
		
		// #### 리스트 오름차순 ####
		Arrays.sort(list);
		
		// #### 기본 변수 설정 ####
		result = new int[R];
		
		// #### repeatCombination() 실행 ####
		repeatCombination(0, 0);
		
		// #### 결과 출력 ####
		System.out.println(sb.toString());
	} // main
	
	public static void repeatCombination(int start, int idx) {
		// 1. 탈출 조건
		if (idx == R) {
			for (int r : result) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		int prev = -1;
		
		// 2. start부터 원소 탐색
		for (int i = start; i < N; i++) {
			if (prev == list[i]) {
				continue;
			}
			// 2-1. 결과에 원소 담기
			result[idx] = list[i];
			// 2-2. 다음 결과리스트에 올 원소 탐색 (재귀)
			repeatCombination(i, idx + 1);
			prev = list[i];
		}
	}

}
