package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_2753 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		// % 400
		// %100
		// %4
		// % 400==0 은 %4==0을 만족하므ㄹ %4==0을 만족하는 애 중에서 %100==0이 아닌 애를 찾으면 됨 ->단, %100==0이라고 다 윤년은 아닌게 %100==0인 경우 %400==0인 경우를 만족하는 수가 있기 때문
		if( N%400==0 )
			System.out.println("1");
		else if( N%100 == 0 )
			System.out.println("0");
		else if( N%4 == 0 )
			System.out.println("1");
		else System.out.println("0");
	}
}
