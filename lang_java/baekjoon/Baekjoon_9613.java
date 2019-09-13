package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_9613 extends Solution {
	
	//가능한 모든 쌍의 GCD의 합
	
	static int gcd(int a, int b) {
		int tmp;
		if(a<b) {
			tmp = b;
			b = a;
			a = tmp;
		}
		while(b!=0) {
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i = 0; i<t; i++) {
			int n = sc.nextInt();
			long result = 0; //int 아님에 유의.. 큰 수가 나올 경우 int 범위를 넘치는 숫자 존재.
			int [] array = new int [n];
			for(int j = 0; j < n;j++) {
				array[j] = sc.nextInt();
			}
			for(int k = 0; k < n; k++) {
				for(int u = k+1; u<n;u++) {
					result+= gcd(array[k], array[u]);
				}
			}

			System.out.println(result);
		}
		
	}

}
