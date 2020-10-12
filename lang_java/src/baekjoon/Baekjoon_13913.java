package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13913 extends Solution {
	
	static int N, K;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<int[]> queue = new ArrayList<int[]>();
		boolean[] visited = new boolean[100001];
		queue.add(new int[] {N, -1}); // 현재 위치, 어디에서 왔는지
		int queue_index = 0;
		while(true) {
			int now = queue.get(queue_index)[0];
			if(now == K) break;
			else {
				if(now <= K) {
					if(now < 50001 && !visited[2*now]) {
						queue.add(new int[] {2*now, queue_index});
						visited[2*now] = true;
					}
					if(now < 100000 && !visited[now+1]) {
						queue.add(new int[] {now+1, queue_index});
						visited[now+1] = true;
					}
				} 
				if(now > 0 && !visited[now-1]) {
					queue.add(new int[] {now-1, queue_index}); 
					visited[now-1] = true;
				}
			}
			queue_index++;
		}
		
		StringBuffer sb = new StringBuffer();
		int length = -1; // 자기 자신도 더해지므로
		while(queue_index >= 0) {
			int[] now = queue.get(queue_index);
			sb.insert(0, now[0]+" ");
			queue_index = now[1];
			length++;
		}
		sb.insert(0, length+"\n");
		System.out.println(sb.toString());
		
	}
}
