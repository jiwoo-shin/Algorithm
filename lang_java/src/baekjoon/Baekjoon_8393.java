package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_8393 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		// 1~ n까지 합은 (n*(n+1))/2임
        // (1+n)+ (2 + n-1) + (3 + n-2) ... = 1+n이 n/2개
		// 괄호계산이 느려서... 걍 for문 돌리는게  빠름.
		System.out.print((n*n+n)/2);
	}
}
