package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_12886 extends Solution {
	static int[][] choose_list = {{0, 1}, {0, 2}, {1, 2}}; // 선택할 그룹

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int answer = A == B && B == C ? 1 : 0;
		int sum = A+B+C;
		if(sum % 3 == 0 && (sum / 3)%2 == 0 && answer!= 1) // sum % 3이 0이 아니면 정확히 3등분 불가(sum / 3)%2이 0이 아니면 또 3등분 불가, 결국 숫자가 2X 로 늘어나기 때문에 짝수가 되어버림
			answer = dfs(new int[] {A, B, C});
			//answer = bfs(A, B, C);
		System.out.println(answer);
	}
	
	// dfs
	static boolean[][] visited = new boolean[1501][1501];
	static int dfs(int[] list) {
		print(list);
		if(list[0] == list[1] && list[1] == list[2]) {
			return 1;
		}
		if(visited[list[0]][list[1]]) return 0;
		int answer = 0;
		visited[list[0]][list[1]] = true;
		for(int i = 0; i < 3; i++) {
			int minus = 1, plus = 0;
			int[] next = new int[] {list[0], list[1], list[2]};
			if(list[choose_list[i][0]] > list[choose_list[i][1]]) {
				minus = 0;
				plus = 1;
			}
			next[choose_list[i][minus]] -= next[choose_list[i][plus]];
			next[choose_list[i][plus]] += next[choose_list[i][plus]];
			answer = Math.max(answer, dfs(next));
		}
		return answer;
		
	}
	static int bfs(int A, int B, int C) {
		//boolean [][][] visited = new boolean[1001][1001][1001];
		boolean [][] visited = new boolean[1501][1501]; // 앞에 2개가 마지막 하나를 결정하므로 마지막 하나는 굳이 visited 배열에 넣을 필요는 없음 (A, B, C 는 500개까지 가능하므로 아무리 많아도 하나에 1500개를 넘지 않음)
		visited[A][B] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {A, B, C});
		int[] now;
		int[] next;
		while(!queue.isEmpty()) {
			now = queue.poll();
			for(int i = 0; i < 3; i++) {
				next = new int[] {now[0], now[1], now[2]};
				int minus = 1, plus = 0;
				if(now[choose_list[i][0]] > now[choose_list[i][1]]) {
					minus = 0;
					plus = 1;
				}
				next[choose_list[i][minus]] -= now[choose_list[i][plus]];
				next[choose_list[i][plus]] += now[choose_list[i][plus]];
				if(next[0] == next[1] && next[1] == next[2]) return 1;
				if(!visited[next[0]][next[1]]) {
					visited[next[0]][next[1]] = true;
					queue.add(next);
				}
			}
		}
		
		return 0;
	}
}
