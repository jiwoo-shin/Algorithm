package baekjoon;

import java.util.Scanner;

public class Baekjoon_1978 extends Solution {
	
	//N개 중에서 소수의 개수 출력
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int x;
		int result = 0;
		for(int i = 0; i < N; i++) {
			x  = sc.nextInt();
			if(x == 1)continue;//1은 소수 아님.
			result++;
			for(int j = 2; j*j <= x; j++) { //x의 약수가 가능한 모든 숫자에 대해 비교(루트n 이하는 루트 n 이상의 약수와 짝지어져있기 때문에 루트 n 까지만 해도 충분.)
				if(x%j == 0) {
					result--;//소수가 아닌 경우
					break;//이거 없으면 계속 돌아가서 음수가 나올수도..있음..
				}
			}
			System.out.println(result);
		}
		System.out.println(result);
	}

}
