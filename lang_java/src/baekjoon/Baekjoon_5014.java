package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_5014 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		bfs(F, S, G, U, D);
	}
	static void bfs(int F, int S, int G, int U, int D) {
		Queue<Integer> position =  new LinkedList<Integer>();
		position.add(S);
		boolean[] visited = new boolean[1000000];
		int[] move_position = new int[] {U, -D};
		int press = 0;
		while(!position.isEmpty()) {
			int size = position.size();
			for(int i = 0; i < size; i++) {
				int now = position.poll();
				if(now == G) {
					System.out.println(press);
					return;
				} else {
					for(int move = 0; move < 2; move++) {
						int next = move_position[move] + now;
						if(next > 0 && next <= F && !visited[next-1]) {
							position.add(next);
							visited[next-1] = true;
						} 
					}
				}
			}
			press++;
		}
		System.out.println("use the stairs");
	}
}
