package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_11057 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] memo = new int[N][10]; // memo[i] = i-1 자리숫자에서의 오르막수의 개수, memo[i][j] = i-1 번째에서 j로 끝나는 오르막수의개수
        int answer = 0;
        int tmp = 0;
        // 초기화
        for(int i = 0; i < 10; i++) {
        	memo[0][i] = 1;
        }

    	// f(n) = f(n-1)*2 - 마지막수 +1이 불가능한 경우 (9로 끝나는 경우)
        for(int i = 1; i < N; i++) {
    		memo[i][0] = memo[i-1][0];
        	for(int j = 1; j < 10; j++) {
        		tmp = 0;
        		for(int k = 0; k < j+1; k++) // j 로 끝나는 수는  0~ j 로 끝나는 수로 만들 수 있다.
        			tmp += memo[i-1][k];
        		memo[i][j] = tmp%10007;
        	}
        }
        
    	for(int j = 0; j < 10; j++) {
    		answer += memo[N-1][j]; // 여기서 항상 나머지 하지 않고도 출력하기 전 한번만 % 해주면 된다. (10007*10은 int 표현 가능 범위 이므로 다 더해서 마지막에 나눠줘도 됨. 만약 int 범위 밖이였으면 음수가 되어버려 이상한 값이 출력될 수 있음.)
    	}
        
		System.out.println(answer%10007);
	}
}