package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1260 extends Solution {
	
    static ArrayList[] relation; // 관계들을 저장 (relation[x] 에 저장되는 ArrayList<int>는 x 정점에 연결된 점들)
    static boolean[] visited; // 방문 여부(dfs 시 사용) (dfs의 인자로 받으면안됨 재귀 끝까지 가면 visited 초기화 되어버림..)
    static StringBuilder answer;
    
    // DFS
    static void dfs(int index) { // index: 현재 방문한 정점
    	visited[index] = true; // index를 방문함
		answer.append(index+1).append(" ");
    	for(int i = 0; i < relation[index].size(); i++) { // index 정점에 연결된 모든 정점을 검색
    		int next = (int)(relation[index].get(i));
    		if(!visited[next]) { // 방문하지 않은 정점인 경우
    			dfs(next);
    		}
    	}
    }
    // BFS -> 재귀호출 ㄴㄴ => 한 노드 에서 끝까지 간 후 다음 노드 검색하는 것이아니므로
    static void bfs(int start, int N) { // index: 시작 정점
    	int[] nodes = new int[N]; // 방문한 정점들
    	boolean[] visited = new boolean[N]; // 방문여부
    	int visited_index = 0; // 다음 노드 검색을 위한 현재 노드
    	int add_index = 0; // 노드 추가할 
    	
    	// 초기화 (처음 정점 방문)
    	visited[start] = true;
    	nodes[add_index++] = start;
		answer.append(start+1).append(" ");
    	
    	int node;
    			
    	while(N > visited_index) {
        	node = nodes[visited_index];
        	for(int i = 0; i< relation[node].size(); i++) {
        		int next = (int)relation[node].get(i); // 방문 노드
        		if(!visited[next]) { // 방문하지 않은 노드인 경우 방문한다.
        	    	visited[next] = true;
        	    	nodes[add_index++] = next;
        			answer.append(next+1).append(" ");
        		}
        	}
        	// for문을 통해 node에 인접한 정점 중 방문 가능한 정점들을 모두 탐색하면 다음 정점
        	visited_index++;
    	}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	answer = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int V = Integer.parseInt(st.nextToken()) - 1; // 시작할 정점
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			//양방향 가능하므로
			relation[x].add(y); 
			relation[y].add(x); 
		}
		for(int i = 0; i< N; i++) {
	        Collections.sort(relation[i]); // 작은 점 먼저 방문해야하므로
		}
		
		dfs(V);
		
		answer.append("\n");
		
		bfs(V, N);
		System.out.println(answer.toString());
	}
}
