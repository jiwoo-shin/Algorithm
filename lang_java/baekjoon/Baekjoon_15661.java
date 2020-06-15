package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15661 extends Solution {
	
	static int[][] S;
	static int N;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(new int[N], new int[N], 0, 0, 0));
		
	}
	static int search() {
		int answer = 0;
		int max = 1 << N;
		for(int i = 1; i <= max; i++) { // 한명도 없는 경우는 없다.
			
		}
		return answer;
	}
	static int getDifference(int team) {
		int score = 0;
		int start = 0;
		int link = 0;
		for(int i = 0; i < N; i++) {
			int now = (team >> i) & 1; // 1이면 start, 0이면 link
		}
		return score;
	}
	static int dfs(int[] start, int[] link, int start_index, int link_index, int now) {
		if(now == N) { 
			return Math.abs(getScore(start, start_index) - getScore(link, link_index));
		}
		int answer = Integer.MAX_VALUE;
		if(start_index < N-1) {
			start[start_index] = now;
			answer = dfs(start, link, start_index+1, link_index, now+1); // now를 start 팀에 넣음
		}
		if(link_index < N-1) { // start 팀에도 한명이상 있어야하므로 link팀의 인원수가 N-1명 미만인 경우에만 link 팀에 넣는다.
			link[link_index] = now;
			answer = Math.min(answer, dfs(start, link, start_index, link_index+1, now+1)); //now를 link 팀에 넣음
		}
		return answer;
	}
	static int getScore(int[] team, int length) {
		int score = 0;
		for(int i = 0; i < length; i++) {
			for(int j = i+1; j < length; j++) {
				score += S[team[i]][team[j]] + S[team[j]][team[i]];
			}
		}
		return score;
	}
}
