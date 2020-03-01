package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14889 extends Solution {
	
	static int answer = Integer.MAX_VALUE;
	static int[][] status;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		status = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				status[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		score(0, N, 0, 0, 0, 0);
		System.out.println(answer/2); // length 번째선수 추가 시 모든 선수가 같은팀이라 가정하였으므로 ij가 두번씩 더해짐 i 번째 선수 추가 시 ij, ji /j 번째 선수 추가 시 ji, ij
	}
	
	static void score(int length, int N, int start, int link, int length_start, int length_link) {
		if(length == N) {
			//  
			answer = Math.min(Math.abs(start-link), answer);
		} else {
			int add = 0;
			for(int i = 0; i < N; i++) {
				// length 번째 선수가 추가된다. (단, length 번째 선수 추가 시 모든 선수가 같은 팀이라 가정 > start-link를 하므로 어차피 불필요하게 추가된 애들은 제거됨) 
				add += (status[i][length]+status[length][i]);
			}
			// 1. start 팀
			if(length_start < N/2) {
				score(length+1, N, start+add, link, length_start+1, length_link);
			}
			// 2. link 팀
			if(length_link < N/2) {
				score(length+1, N, start, link+add, length_start, length_link+1);
			}
		}
	}
}