package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1697 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 
		int[] visited = new int[100001]; // 이걸 안주면 갔던데 또가고 해서 시간 오래걸림.. visited가 있으면 최대 10만번만 탐색

		ArrayList<Integer> queue = new ArrayList<Integer>(); // 방문할 정점들을 저장
		queue.add(N);
		int now, next;//, next = new int[2];
		visited[N] = 1; // 초기를 0과 구분짓기 위해 ㅂ로 시작하였으므로 정답은 -1해줘야함
		// time 변수를만들어서 while문 돌때 마다 올리면 같은 시간에 도착하는 정점이여도 시간이다르게계산됨
		// while문 안에 for => 현재 큐의사이즈 만큼,for안에서 add 하고 .. while 최상단에서는 큐 사이즈 구하고, 시간++ 해주면 되기는 한다.
		
		do {
			now = queue.remove(0); // 큐에 있는 정점을 방문
			// 다음 위치가 방문 가능한 정점인 경우 증가
			if(now > 0 && visited[now-1] == 0) { // now_x - 1 >= 0 >>> now_x - 1 > -1
				next = now-1;
				visited[next] = visited[now] + 1; // 여 안에서 visited[]를 방문했다고 해야지..while 최상단에서 방문했다고 하면 중복 방문이 생길 수 있음. (1-> 1,0,2 -> 2:중복..)
				queue.add(next);
			}
			if(now < K) {// now < K 인 경우만 now를 증가시킴. now가 작아지는 경우는 -1밖에없는데 크게 했다가 감소시키면은 -1을 할 횟수만 증가함
				if(now < 50001 && visited[now*2] == 0) { // now_x*2 <= 100,000 >>> now_x*2 < 100001
					next = now*2;
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
				if(now < 100000 && visited[now+1] == 0) { // now_x + 1 <= 100,000 >>> now_x + 1 < 100001
					next = now+1;
					visited[next] = visited[now] + 1;
					queue.add(next);
				}
			}
		} while(!queue.isEmpty() && now != K); // 큐가 비어있지 않고 현재 정점이 K가 아닌 경우 탐색

		System.out.println(visited[K] - 1);
		
	
		
	}
}
