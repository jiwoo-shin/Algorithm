package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14002 extends Solution {
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; //A[i] :i 번째 숫자
        int[][] memo = new int[N][2]; // memo[i][0] : i+1 번째 숫자를 사용할 경우 가능한 최대길이. memo[i][1] : 최대길이일 경우 i이전에 오는 수열 위치
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st =  new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        memo[0][0] = 1;
        int max = 1; // 초기값 1
        int max_i = 0;
        
        // memo[i][0] : memo[j][0] +1, memo[i][1] : j
        // j는 A[i] > A[j]를 만족하고 memo[j]가 최대인 경우
        for(int i = 1; i < N; i++) {
        	int tmp_j = i;
        	for(int j = i-1; j >= 0; j--) { // if(A[tmp_j] > A[j])
        		if(A[i] > A[j] && memo[tmp_j][0] < memo[j][0]) {
        			tmp_j = j;
        		}
        	}
        	
        	if(tmp_j == i) {
            	memo[i][0] = 1; // 아무것도 연결되지 않은.. 자기 자신이 시작인 것
        	} else { // tmp_j와 i와 연결
            	memo[i][0] = memo[tmp_j][0] + 1;
            	memo[i][1] = tmp_j;
        	}
        	
        	// 최대인 경우
        	if(max < memo[i][0]) {
        		max = memo[i][0];
        		max_i = i;
        	}
        }
        
        int print_j = max_i;
        for(int i = 0; i < max-1; i++) {
        	sb.insert(0, A[print_j]).insert(0, ' ');
        	print_j = memo[print_j][1];
        }
    	sb.insert(0, A[print_j]);
    	sb.insert(0, '\n').insert(0, max);
    	System.out.print(sb.toString());
	}
}