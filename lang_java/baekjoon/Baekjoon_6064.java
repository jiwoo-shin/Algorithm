package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_6064 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int M, N, x, y;
		int gap, tmp, k, j;
		StringBuilder answer = new StringBuilder();
		boolean[] visited;
		
		for(int i = 0; i < T; i++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken()) - 1; // 숫자의 범위가 1~M이므로 0 ~ M-1로 바꾸기 위해
			y = Integer.parseInt(st.nextToken()) - 1;// 숫자의 범위가 1~N이므로 0 ~ N-1로 바꾸기 위해  (중간에 (tmp-1)%N +1 이 아니라 tmp%N만 철해주기 위해)
			visited = new boolean[N];
			gap = N > M ? M : M-N; 
		   	tmp = x % N; // 처음값. M > N 인 경우도 있으므로 그냥 x가 아니다. N의 나머지
			k = -1; // start ~ start 일 때 까지 y가 불가능할 경우 k = -1  (불가능)
			j = 0; // 0부터 시작 (몇회 사이클 돌았는지)
			
			do {
				visited[tmp] = true;
				if(tmp == y) {
					k =  x + M*j + 1; // 초반에 x만큼 움직인 후 M 이 반복되는 사이클을 j 번 돈다. // 0부터 기준으로 잡았으므로 +1을 해준다.
					break;
				} else {
					tmp = (tmp+gap) % N; //M 고정이므로 N으로 나눈다.
					// visited[tmp-1] = true; 여기있으면 안됨. 항상 true가 되므로
					j++;
				}
			} while(!visited[tmp]);// 방문한 적이 없는 경우 돈다.

			answer.append(k).append("\n");
			
		}
		
		System.out.println(answer.toString());
	}
}