package lang_java.baekjoon;

import java.util.Scanner;

// 줄 세우기.. 숫자 1, 2, 3을 줄 세울 수 있는 경우의 수를 구하는 문제.

public class Baekjoon_9095 extends Solution {
	public static int c(int n, int m) {
		int c = 1;
		for(int i = 0; i < m; i++) {
			c *= (n-i);
			c /= (i+1);
		}
		
		return c;
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //참고.. k += (1 - k)/(k + 1); //k가 0일 경우 1, 0아닐 경우 k
		
		for(int i = 0; i < T; i++) {
			
			int result = 0;
			int n = sc.nextInt();
			
			for(int j = 0; j <= n/3; j++) { //3의 개수.. 등호 있어야함
				
				for(int k = 0; k <= (n-j*3)/2; k++) { //2의 개수
					int nums = (n - 3*j - 2*k) + j + k; //숫자의 개수 (1의 개수 :(n - 3*j - 2*k) / 2의 개수 : j / 3의 개수 : k )
					
					result += c(nums, (n - 3*j - 2*k))* c(j+k, j); //숫자 개수 : 자리의 개수/ 일단 1이 들어갈 자리 뽑음 : c(nums, (n - 3*j - 2*k)) / 그 다음 2가 들어갈 자리 뽑음: 1이 뽑은 자리 제외하고 뽑을 수 있음./c(k, k)는 항상 1이므로 계산 불ㅍㄹ요.. 3은  항상 남은 자리에 꽉차게 들어감.
				}
			}
			System.out.println(result);
		}
		
	}
}
