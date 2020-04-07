package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_12851 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] visited = new int[100001];
		int[][] move = {{1, -1}, {1, 1}, {2, 0}};
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		visited[N] = 1;
		int answer_depth = 0, answer_length = 0;
		while(!queue.isEmpty()) {
			int depth_size = queue.size();
			int[] depth_visited = new int[3*depth_size];
			for(int i = 0; i < depth_size; i++) {
				int now = queue.poll();
				depth_visited[i] = now;
				if(now == K) {
					int depth_remain = depth_size - 1 - i;
					answer_length = 1;
					for(int j = 0; j < depth_remain; j++) {
						if( queue.poll() == K )answer_length++;
					}
					System.out.println(answer_depth+"\n"+answer_length);
					return;
				} else { 
					for(int t = 0; t < 3; t++) {
						int next = now*move[t][0] + move[t][1];
						if(next >=0 && next <= 100000 && (visited[next] == 0 || visited[next] == visited[now] + 1)) { 
							// 방문한적이 없거나, 방문한 적이 있으면 동일한 depth로 방문한 경우
							// 방문한 적이 있으면 동일한 depth로 방문한 경우 : 1 (*2)-> 2 / 1 (+1) -> 2 로 두 정점모두 2이지만 루트가 다르므로 다른 경우로 쳐야한다. 
							// 따라서 동일한 depth에서의 방문은 방문 여부를 따지지 않고
							// 이전 depth 에서의 방문한 정점만 중복 방문하지 않도록 한다.
							// 1 2(*2) 4 8 7 14, 1 2(+1) 4 8 7 14, 1 2(*2) 3 6 7 14, 1 2(+1) 3 6 7 14 -> 동일한 depth에서의 방문은 방문 여부를 따지면 7은 1번만 방문하여, 잘못된 답을 구하게 된다.
							queue.add(next);
							visited[next] = visited[now] + 1;
						}
						if(next > K) break; // 일 경우 -1만 해준다.
					}
				}
			}
			answer_depth++;
		
		}
	}
}
