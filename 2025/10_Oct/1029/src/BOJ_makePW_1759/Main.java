package BOJ_makePW_1759;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// 최소 한 개의 모음 + 두 개의 자음
public class Main {
	static List<Character> list = new ArrayList<>(); // 원본 리스트
	static char[] result; // 결과 리스트
	static int R; // 뽑을 숫자 수
	static int N; // 전체 숫자 수
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_makePW_1759/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		R = sc.nextInt(); // 뽑을 숫자 수
		N = sc.nextInt(); // 전체 숫자 수
		sc.nextLine();
		String pws = sc.nextLine().replace(" ", "");
		for (int i = 0; i < N; i++) {
			list.add(pws.charAt(i));
		} // 알파벳 리스트에 담기
		
		//==== 기본 설정 ====
		list.sort(Comparator.naturalOrder());
		result = new char[R];
		
		//==== permutation 함수 실행 ====
		combination(0, 0);
	} // main
	
	public static void combination(int start, int idx) {
		// 1. 탈출 조건
		if (idx == R) {
			if (valid()) {
				for (char c : result) {
					System.out.print(c);
				}
				System.out.println();
			}
			return;
		}
		// 2. 모든 원소 탐색
		for (int i = start; i < N; i++) {
			// 2-1-1. result 리스트에 담기
			result[idx] = list.get(i);
			// 2-1-3. 다음 원소 탐색 (재귀)
			combination(i + 1, idx + 1);
		}
	}
	
	public static boolean valid() {
		int mo = 0;
		int ja = 0;
		for (char c : result) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				mo++;
			} else {
				ja++;
			}
		}
		
		if (mo >= 1 && ja >= 2) {
			return true;
		} else {
			return false;
		}
	}

}
