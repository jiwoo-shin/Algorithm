package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1912 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] memo = new int[n];
        int[] numbers = new int[n];

        st =  new StringTokenizer(br.readLine());
        int answer = memo[0] = numbers[0] = Integer.parseInt(st.nextToken());
        
        
        for(int i = 1; i < n; i++) {
        	numbers[i] = Integer.parseInt(st.nextToken()); //입력
        	
            // memo[n] = n+1번째 숫자를 사용하였을 때 만들 수 있는 부분수열의 최대 함
            // 쓰거나 쓰지 않거나 따로저장할필요는 없음. 걍 최적만저장하면 다음에 최적을쓰면됨. 딱히사용 가능 조건이 없음
            // memo[n] : n 숫자를 이어서 붙이거나 이어서 붙ㅌ이지않거나
            // memo[n] = max(memo[n-1]+num[n], num[n]);
        	memo[i]= Integer.max(memo[i-1] + numbers[i], numbers[i]);
        	if(answer < memo[i]) answer = memo[i];
        }
        
        // memo[n-1]이 답은 아님.memo[n-1]은 n-1 번째숫자를 사용한 경우 가능한 최적해.
        System.out.println(answer);
	}

}