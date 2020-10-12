package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_1463 extends Solution {
	static int[] memo;
	
	static int dp(int n) { 
		if(n == 1) return 0; // 이게 없으면 안됨. 1이 0이기 때문에 1이 입력으로 들어오는 경우 계속 ㄴ돈다.
		else if(memo[n] == 0) { // memo에 저장된 값이 없는 경우에만 계산
			// memo (cache)에 저장 후 다시 쓰일 경우 memo에서가져옴
			if(n%6 == 0) { // n%2 == 0 && n%3 == 0
				memo[n] = Math.min(Math.min(dp(n/3), dp(n/2)), dp(n-1));
			} else if(n%3 == 0) { // 3으로만 나누어 떨어지는 경우 
				memo[n] = Math.min(dp(n/3), dp(n-1)); //  Math.min(memo[n/3], memo[n-1]); 이거 아님... n일 때 memo에 n/3과 n-1이구해졌다는 보장이 없으므로 확인 후 리텅하는 dp함수를 호출해야함
			} else if(n%2 == 0) { // 2로만 나누어 떨어지는 경우
				memo[n] = Math.min(dp(n/2), dp(n-1));
			} else { //2,3 모두 나누어 떨어지지 않는 경우
				memo[n] = dp(n-1);
			}
			memo[n]++; // n-1 이나 n/2 이나 n/3을 한번 거친 후 n이 될 수 있으므로 1을 더해줌
		}
		
		return memo[n];
	}
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int N = (int)sc.nextInt();
		memo = new int[N+1];
		memo[1] = 0; // 1 만드는데 걸리는
		
		// f(n) = min (f(n/3), f(n/2), f(n-1)) + 1;
		for(int i = 1; i <= N; i++) {
			if(memo[i] == 0) { // n이 구해지지 않은 경우
				if(i%6 == 0) { // n%2 == 0 && n%3 == 0
					memo[i] = Math.min(Math.min(memo[i/3], memo[i/2]), memo[i-1]);
				} else if(i%3 == 0) { 
					memo[i] = Math.min(memo[i/3],memo[i-1]);
				} else if(i%2 == 0) {
					memo[i] = Math.min(memo[i/2], memo[i-1]);
				} else {
					memo[i] = memo[i-1];
				}
				memo[i]++;
			}
		}
		System.out.println(memo[N]);
		
		//System.out.println(dp(N));
		
	}

}
