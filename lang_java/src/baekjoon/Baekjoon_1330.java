package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_1330 extends Solution {

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		String answer = "==";
		
		if(A > B) answer = ">";
		else if(A < B) answer = "<";
		
		System.out.println(answer);
		
	}

}
