package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_9019 extends Solution {
	
	static int T;
	static char[] static_operation = {'D', 'S', 'L', 'R'};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			answer.append(bfs(A, B)).append("\n");
		}
		System.out.println(answer.toString());
		
	}
	static StringBuilder bfs(int A, int B) {
		boolean[] visited = new boolean[10000];
		char[] operations = new char[10000];
		int[] from = new int[10000];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(A);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int operation = 0; operation < 4;operation++) {
				int next = opertate(now, operation);
				if(!visited[next]) {
					queue.offer(next);
					from[next] = now;
					operations[next] = static_operation[operation];
					visited[next] = true;
				}
				if(next == B) {
					int tmp = B;
					StringBuilder sb = new StringBuilder();
					while(tmp != A) {
						sb.insert(0, operations[tmp]);
						tmp = from[tmp];
					}
					return sb;
				}
			}
		}
		return null;
	}
	static int opertate(int number, int operation) {
		switch (operation) {
		case 0:
			return number > 4999? number*2 - 10000 : number*2;
		case 1:
			return number==0? 9999: number-1;
		case 2:
			return (number % 1000)*10 + number/1000;
		default:
			return (number / 10) + number%10 *1000;
		}
	}
}
