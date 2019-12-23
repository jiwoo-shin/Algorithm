package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_14226 extends Solution {
	static ArrayList<int[]> queue;
	static int S;
	static boolean[][] visited; // visited없으면 S가커지면 느려짐
	
	// 1. 붙여넣기, 2. 복사, 3. 삭제 >> 3가지의 경우의 수를 모두 해본다.
	static void bfs(int size, int depth) { // 큐에서의 index 번째 부터 탐색 시작. 현재depth는 size개가 있음.현재 depth에서 다음큐에저장되는건 depth+1 의 깊이.
		int now; // 현재 이모티콘의 개수
		int copy; // 복사되어있는지 여부
	
		// 같은 depth인경우 모두 탐색 후 다음 bfs를 호출해야 depth 순서대로 탐색인 bfs다. 하나 탐색할 때마다 bfs또 들어가면 하나 시작하면 끝까지 들어가는 dfs임..
		for(int i = 0; i < size;  i++) {
			now = queue.get(0)[0];
			copy = queue.get(0)[1]; //copy가 isCopy로 boolean이 아닌이유는 제거가 있기 때문에 현재 개수와 복사된 개수가 항상 같다는보장이 없음. 즉, 복사 후 개수가 항상 2*now라는 말이아님.
			// 복사 후 제거 후 붙여넣기를 한다면 현재 개수now, 클립보드에는ㄴnow-1개 
			
			//현재 노드를 방문함.
			queue.remove(0); // 현재 탐색한 노드 제거 ..여기서제거안하고 visited[now][copy]가 false인 경우만 제거하면 true일 경우 계속 방문이됨.제거가안되니까똑같은게계속 방문.
			if(visited[now][copy])continue;  // 현재 정점을 방문한 경우 더 이상의 연산은불필요
			
			visited[now][copy] = true;
			if(now == S) {
				System.out.println(depth);
				return;
			}
			if(copy > 0 && now + copy <= S) { // 복사 가능한 경우
				// 붙여넣기
				queue.add(new int[]{now + copy, copy});
			}
			if(now > 0) {
				queue.add(new int[]{now, now}); // 복사 (copy된 것이 없더라도 복사는가능)
				queue.add(new int[]{now-1, copy}); // 제거
			}
		}
		bfs(queue.size(),  depth+1);
	}

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); 
		queue = new ArrayList(); // 현재까지이모티콘 개수와 복사된 이모티콘의개수를 큐에 저장
		visited = new boolean[S+1][S+1]; //현재 이모티콘 개수와 복사된 개수가 동일한 경우 다시 방문할 필요가 업음
		queue.add(new int[]{1, 0});
		bfs(1, 0);
	
	}
}
