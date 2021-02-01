package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12996 extends Solution {
	
	static boolean[][][][] visited;
	static long[][][][] memo;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int[] sing_list = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		memo = new long[S+1][sing_list[0]+1][sing_list[1]+1][sing_list[2]+1];
		visited = new boolean[S+1][sing_list[0]+1][sing_list[1]+1][sing_list[2]+1];
		System.out.println(dp(S, sing_list[0], sing_list[1], sing_list[2]));
		
	}
	static long dp(int S, int a, int b, int c) {
		// 0보다 작은 경우 : 존재 x / a,b,c 중 하나라도 S보다 큰 경우 불러야하는 곡의 수만큼 부를 수 없다. / a+b+c가 S보다 작으면 다 불러도 S를 채울 수 없다.
		if(a < 0 || b < 0 || c < 0 || S < 0 || a > S || b > S || c > S || a+b+c < S) return 0; 
		if(S == 1) { // 곡이 하나인 경우 경우의 수는 하나밖에 없다. 걍 다 부르는 수 밖에.. 1곡을 불러야 하는 상황이므로 위에서 제외한 경우가 아니면 다 불러야함.. 경우의수 1가지.
			memo[S][a][b][c] = 1;
		} else if(!visited[S][a][b][c]) { // 방문한 적이 없다면..
			visited[S][a][b][c] = true;
			// S-1 개의 곡을 부르고 나머지 하나의 곡에 대해 추가된다고 생각
			// 곡 하나를 부를 수 있는 경우의 수는 최대 7가지 : a,b,c,ab,ac,cb,abc
			memo[S][a][b][c] = (dp(S-1, a-1, b, c) + dp(S-1, a, b-1, c) + dp(S-1, a, b, c-1) + dp(S-1, a-1, b-1, c) + dp(S-1, a-1, b, c-1) + dp(S-1, a, b-1, c-1) + dp(S-1, a-1, b-1, c-1))% 1000000007;
		}
		return memo[S][a][b][c];
	}
}

