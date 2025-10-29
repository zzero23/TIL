package BOJ_RGB_1149;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] color = new int[3]; // 원본 리스트 (색)
	static int[] result = new int[3]; // 결과 리스트 (집)
	static int N; // 방 수
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/BOJ_RGB_1149/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 방 수
		//==== 기본 변수 준비 ====
		for (int i = 0; i < 3; i++) {
			color[i] = i + 1;
		} // 원본 리스트 준비 (R : 1 / G : 2
	}

}
