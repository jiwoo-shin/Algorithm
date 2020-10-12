package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1707 extends Solution {
	
    static ArrayList[] relation; // 관계들을 저장 (relation[x] 에 저장되는 ArrayList<int>는 x 정점에 연결된 점들)
    static boolean[] visited; // 방문 여부(dfs 시 사용)
    static boolean[] graph; // 정점 index가 그룹 A에 포함되는지 여부 (그룹 A는 정점 0이 포함되는 )
    static boolean is_binary;// 이분그래프인지 여부
    static StringBuilder answer;
    
    // DFS
    static void dfs(int index, boolean A) { // index: 현재 방문한 정점, A : 시작점과 같은 그래프에 포함될 수 있는지 여부 (그룹 A/B로 나누었을 때 A에 포함되는지 (시작점은 A))
    	visited[index] = true; // index를 방문함
    	graph[index] = A; // index는 A 그룹에 포함됨
    	
    	for(int i = 0; i < relation[index].size(); i++) { // index 정점에 연결된 모든 정점을 검색
    		int next = (int)(relation[index].get(i)); // index와 연결된 다음에 방문할 정점
    		
    		if(!visited[next]) { // next가 방문하지 않은 정점인 경우
    			dfs(next, !A); // index와 바로 연결된 정점이므로 index와 다른 그래프임
    		} else { // next가 방문한 정점인 경우 // dfs를 다시 할 필요는 없음 -> 방문 했다는게 해당정점에서 dfs 해본거니까
    			if(graph[next] == A) { // 모순인 경우, index에 속하는 그룹이A 인데 구해진 next의 그룹이 A인 경우,, > 이분그래프 아님 // graph[next] != (!A) -> !graph[next] == (!A) -> graph[next] == A
    				is_binary = false;
    			}
    		}
    		
    		if(!is_binary)break;
    	}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 테스트 개수
		int V;
		int E;
    	answer = new StringBuilder();
		
		for(int test_case = 0; test_case < K; test_case++) {
			is_binary = true; // 매번 초기화해줌
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			relation = new ArrayList[V];
			visited = new boolean[V];
			graph = new boolean[V];
			
			for(int i = 0; i < V; i++) {
				relation[i] = new ArrayList();
			}
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				//방향 없는 그래프이므로
				relation[x].add(y); 
				relation[y].add(x); 
			}
			for(int i = 0; i < V; i++) {
				// i는 연결그래프에서 시작점 (연결그래프가 아닐수도 있으므로 모든 정점에 대해 확인)
				if(!visited[i])dfs(i, true);
				// 여기서도 is_binary가 false인 경우 나감 -> 이후 탐색은 불필요하므로 
				if(!is_binary)break;
			}
			if(is_binary) {
				answer.append("YES").append("\n");
			} else {
				answer.append("NO").append("\n");
			}
		}
		System.out.print(answer.toString());
	}
}
