package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

// 정점을 2번 이상 방문하지  않고 depth 4 가능한지 검색
public class Baekjoon_13023 extends Solution {
	
    static ArrayList[] relation; // 관계들을 저장 (relation[x] 에 저장되는 ArrayList<int>는 x 정점에 연결된 점들)
    static boolean[] visited; // 방문 여부
    static int N;
    static int answer = 0;
    
    static void search(int depth, int index) { // index : 정점 index에서 검색
    	
    	if(depth == 4) { // 이어서 검색 가능한 정점이 5개인 경우,,
    		answer = 1;
    		return;
    	}
    	ArrayList linked = relation[index]; // index에 연결된 모든 정점들..
		for(int i = 0; i < linked.size() && answer != 1; i++) { 
			int next = (int) (linked).get(i); // 다음에 방문 가능한 정점
			// 한번 방문한 정점은 아예 가면 안됨 , B ==> A ==> D는 B ==> D가 아니므로
			// 정점은 한번씩만 방문해야함 알고 알고가 아니라 바로 알아야하므로
			if(!visited[next]) { // 방문하지 않은 경우 방문 (정점을 방문하지 않은 경우) -> 간선을 사용하지 않은 경우가 아님(간선 사용하지 않은 경우로 검색하면 A->B->C->D, B->E 인 경우도 참으로 나옴
				visited[next] = true;
				search(depth+1, next);
				visited[next] = false;
			}
			
		}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 관계의 수
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			relation[x].add(y); 
			relation[y].add(x); // 양방향이 가능하므로
		}
		// DFS
		for(int i = 0; i< N && answer != 1; i++) {
			visited[i] = true;
			search(0, i);
			visited[i] = false; // 여기서도 초기화해줘야함
		}
		System.out.println(answer);
		
	}
}
