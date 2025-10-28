package BOJ_wordmath_1339;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 중복없는 조합
public class Main {
	static List<Character> list = new ArrayList<>(); // 원본 리스트
	static List<char[]> words = new ArrayList<>(); // 단어 사전
	static Map<Character, Integer> result = new HashMap<>(); // 결과 맵
	static boolean[] visited; // 방문 리스트
	static int N; // 뽑을 문자 개수
	static int max;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_wordmath_1339/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		//==== 입력 데이터 ====
		int n = sc.nextInt(); // 단어 개수
		sc.nextLine();
		
		//==== 단어 입력 받기 ====
		for (int i = 0; i < n; i++) {
			//==== 알파벳 사전 생성 ====
			words.add(sc.nextLine().toCharArray());
			for (char c : words.get(i)) {
				list.add(c);
			}
		}
		
		//==== permutation() 함수 실행 ====
		permutation(0);
		
		System.out.println(max);

	}
	
	public static void permutation(int idx) {
		// 1. 탈출 조건
		if (idx == N) {
			// 1-1. 계산
			int sum = calculation(result);
			max = (sum > max) ? sum : max;
			return;
		}
		// 2. start 지점부터 원소 탐색
		for (int i = 9; i >= 0; i--) {
			// 2-1. 사용되지 않은 원소일 경우 -> 사용
			if (!visited[i]) {
				// 2-1-1. 원소 담기
				result.put(list.get(idx), i);
				// 2-1-2. 방문한 원소임을 표시
				visited[i] = true;
				// 2-1-3. 다음 원소 탐색 (재귀)
				permutation(idx + 1);
				// 2-1-4. 원복 (백트래킹)
				visited[i] = false;
			}
		}
	}
	
	
	public static int calculation(Map<Character, Integer> result) {
		int sum = 0;
		for (int i = 0; i < words.size(); i++) {
			char[] c = words.get(i);
			for (int j = 0; j < c.length; j++) {
				sum += result.get(c[j]) * Math.pow(10, words.get(i).length - j - 1);
			}
		}
		return sum;
	}

}
