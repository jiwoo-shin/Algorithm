package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_10817 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		if(A < B) {
			if(C < A) { // B > A > C
				System.out.println(A);
			} else if (C < B){ //B > C >A
				System.out.println(C);
			} else { // C > B > A
				System.out.println(B);
			}
		} else { // A >= B
			if(C > A) { // C > A > B
				System.out.println(A);
			} else if (C < B){ // A > B > C
				System.out.println(B);
			} else { // A > C > B
				System.out.println(C);
			}
		}
		
		
	}
}
