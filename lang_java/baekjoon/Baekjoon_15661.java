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
		System.out.println(search());
		//System.out.println(dfs(new int[N], new int[N], 0, 0, 0));
		
	}
	static int search() {
		int answer = 0;
		int max = (1 << N)-1;
		int[] score = new int[max]; // 속한 팀원이 i 인 경우 얻을 수 있는 점수
		for(int i = 1; i < max; i++) { // 한명도 없는 경우는 없다. 0... 이거나 1...인 경우는 제외
			int new_student = -1;
			for(int j = 0; j < N; j++) { //j 번 학생이 해당 팀에 속하는지 확인
				int check = i & (1 << j);
				if(check != 0) { // j 번째 학생이 팀에 포함되는 경우 (==1 아님.. 1이 아닐수도 있음 2나.. 4나..)
					if(new_student == -1) { // 이전에 구했던 팀에서 새로운 학생이 들어오는 경우 (왼쪽부터 첫번째 1이 나오면 해당 학생은 새로운 학생)
						int prev_team = i &(~check); // 팀 i 에서 가장 좌측의 1만 제거한 상태 (i 보다 작으므로 이전에 구한 점수가 있음)
						score[i] = score[prev_team];
						new_student = j; //가장 좌측 1이 새로운 학생
					} else { // 새로 들어온 학생과 이전 팀에서의 학생들끼리 조합으로 점수를 구함
						score[i] += S[new_student][j] + S[j][new_student];
					}
				} 
			}
		}
		answer = getDifference(score, max, (max+1)/2);
		print(score);
		return answer;
	}
	static int getDifference(int[] score, int max, int half) {
		int difference = Integer.MAX_VALUE;
		for(int i = 1; i <= half; i++) { // 대칭이므로 반만 한다. 
			difference = Math.min(difference, Math.abs(score[max-i] - score[i])); // i랑 i 반대랑 (1011, 0100) 차를 구함
		}
		return difference;
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
