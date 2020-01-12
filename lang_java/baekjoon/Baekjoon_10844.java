package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_10844 extends Solution {
	
	@Override
	public void solution() throws IOException {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] memo = new long[N];
        memo[0] = 9; // memo[i] = i+1 자리수일 때 만들 수 있는 계단수의 개수
        long[] end = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}; // end[i] = i로 끝나는 개수
        long[] tmp = new long[10];
        long disabled;
        
        // // f(n) = f(n-1)에서 마지막 수 -1인 경우 + f(n-1)에서 마지막 수 +1인 경우 - 마지막수 +1이나 -1이 불가능한 경우
        // f(n) = f(n-1)*2 - f(n-1) 에서 9나 0으로 끝나는 경우
        for(int i = 1; i < N; i++) {
        	tmp = new long[10];
        	disabled = (end[0] + end[9])%1000000000;
        	tmp[1] = end[0];
        	tmp[8] = end[9];
        	for(int j = 1; j < 9; j++) {
        		tmp[j-1] = (tmp[j-1] + end[j])%1000000000;
        		tmp[j+1] = (tmp[j+1] + end[j])%1000000000;
        	}
        	end = tmp;
        	
        	// memo[i] = (memo[i-1]*2 - disabled + 1000000000)%1000000000;
        	
        	memo[i] = (memo[i-1]*2 - disabled)%1000000000;
        	if(memo[i] < 0) memo[i] += 1000000000; // memo[i-1]이 1000000000보다 커서 disabled보다 작아진 경우도 있음. 그럴 경우 결과는 memo[i-1]이 1000000000보다 작아지지않은경우로 하면 된다. 
        }
        
		System.out.println(memo[N-1]);
		*/

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] memo = new long[N][10]; // memo[i] = i-1 자리숫자에서의 계단 수의 개수, memo[i][j] = i-1 번째에서 j로 끝나는 계단수의개수
        
        // 초기화
        memo[0][0] = 0;
        for(int i = 1; i < 10; i++)memo[0][i] = 1;
        long answer = 0;
        
        // // f(n) = f(n-1)에서 마지막 수 -1인 경우 + f(n-1)에서 마지막 수 +1인 경우 - 마지막수 +1이나 -1이 불가능한 경우
        // f(n) = f(n-1)*2 - f(n-1) 에서 9나 0으로 끝나는 경우
        for(int i = 1; i < N; i++) {
        	memo[i][0] = memo[i-1][1];
        	memo[i][9] = memo[i-1][8]; 
        	for(int j = 1; j < 9; j++) {
        		memo[i][j] = (memo[i-1][j-1] + memo[i-1][j+1])%1000000000; // 결과가 1000000000을 넘을 수 있으므로 % 해줘야함
        	}
        }
        for(int i = 0; i < 10; i++) {
        	answer = (answer + memo[N-1][i])%1000000000;
        }
        
		System.out.println(answer);
	}
}