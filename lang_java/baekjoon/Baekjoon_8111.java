package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_8111 extends Solution {
	static String[] add_number = {"0", "1"};
	static class PrinceNumber {
		int mod_number;
		StringBuilder number;
		PrinceNumber(int mod_number, StringBuilder number) {
			this.mod_number = mod_number;
			this.number = number;
		}
	}
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); 
		StringBuilder answer = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
	    	st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); 
			answer.append(bfs(N)).append('\n');
		}
		System.out.println(answer);
	}
	
	static StringBuilder bfs(int N) {
		if(N == 1) return new StringBuilder("1"); // 1인 경우 1
		
		Queue<PrinceNumber> queue = new LinkedList<PrinceNumber>();
		boolean[] visited = new boolean[N]; // 0, 1로 이루어진 수 Y , Y%N = K 일때 (Y*10)%N = (Y%N * 10%N)%N = (K * 10%N)%N 이므로 나머지만 저장하여 다음 계산에 사용해도 된다.
		PrinceNumber start = new PrinceNumber( 1 , new StringBuilder("1"));
		queue.add(start);
		visited[1] = true;
		while(!queue.isEmpty()) {
			PrinceNumber now = queue.poll();
			int mod_now = now.mod_number;
			int tmp_next = (mod_now*10)%N;
			int[] next = { tmp_next, (tmp_next+1)%N };
			for(int i = 0; i < 2; i++) {
				if(!visited[next[i]]) {
					PrinceNumber nextPrinceNumber = new PrinceNumber(next[i], new StringBuilder(now.number).append(add_number[i])); // now.number.append(add_number[i]) 은 now.number도 바뀜
					if(next[i] == 0) return nextPrinceNumber.number;
					else {
						queue.offer(nextPrinceNumber);
						visited[next[i]] = true;
					}
				}
			}
		}
		
		return new StringBuilder("BRAK");
	}
}
