package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_10950 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int i = 0;i < T; i++) {

			int A = sc.nextInt();
			int B = sc.nextInt();
			
			System.out.println(A+B);
		}
	}
}
