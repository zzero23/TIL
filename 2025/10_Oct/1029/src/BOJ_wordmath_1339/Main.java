package BOJ_wordmath_1339;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//==================================
//1029 - 시간초과 (그리디로 풀어야 한다고 함)
//==================================
public class Main {
	static String[] words; // 단어 리스트
	static List<Character> list = new ArrayList<>(); // 원본 리스트
	static Map<Character, Integer> result = new HashMap<>(); // 결과 리스트
	static boolean[] visited = new boolean[10]; // 방문 리스트
	static int N; // 단어 개수
	static int max; // 최대값
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_wordmath_1339/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		N = sc.nextInt(); // 단어 개수
		sc.nextLine();
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.nextLine();
			//==== 알파벳 사전 생성 ====
			for (int j = 0; j < words[i].length(); j++) {
				if (!list.contains(words[i].charAt(j))) {
					list.add(words[i].charAt(j));
				}
			}
		} // 단어 채우기
		
		//==== permutation() 함수 실행 ====
		permutation(0);
		
		//==== 결과 출력 ====
		System.out.println(max);		

	} // main

	public static void permutation(int idx) {
		// 1. 탈출 조건
		if (idx == list.size()) {
			// 1-1. 계산
			int sum = calculation();
			// 1-2. 최대값 구하기
			max = (sum > max) ? sum : max;
			return;
		}
		// 2. 모든 원소 탐색
		for (int i = 9; i >= 0; i--) {
			// 2-1. 사용하지 않은 숫자일 경우 -> 사용
			if (!visited[i]) {
				// 2-1-1. 결과 리스트에 담기
				result.put(list.get(idx), i);
				// 2-1-2. 방문했다고 표시
				visited[i] = true;
				// 2-1-3. 다음 숫자 탐색 (재귀)
				permutation(idx + 1);
				// 2-1-4. 원복 (백트래킹)
				visited[i] = false;
			}
		}
	} // permutation
	
	public static int calculation() {
		//==== 전체 단어 탐색 ====
		int sum = 0;
		for (int i = 0; i < N; i++) {
			String word = words[i]; // 탐색 대상 단어
			for (int j = 0; j < word.length(); j++) {
				sum += (result.get(word.charAt(j)) * Math.pow(10, word.length() - j - 1));
			}
		}
		return sum;
	}
}
