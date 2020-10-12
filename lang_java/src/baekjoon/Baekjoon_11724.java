package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11724 extends Solution {
	
    static ArrayList[] relation; // 관계들을 저장 (relation[x] 에 저장되는 ArrayList<int>는 x 정점에 연결된 점들)
    static boolean[] visited; // 방문 여부(dfs 시 사용)
    
    // DFS
    static void dfs(int index) { // index: 현재 방문한 정점
    	visited[index] = true; // index를 방문함
    	for(int i = 0; i < relation[index].size(); i++) { // index 정점에 연결된 모든 정점을 검색
    		int next = (int)(relation[index].get(i));
    		if(!visited[next]) { // 방문하지 않은 정점인 경우
    			dfs(next);
    		}
    	}
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		relation = new ArrayList[N];
		visited = new boolean[N];
		
		for(int i = 0; i< N; i++) {
			relation[i] = new ArrayList();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			//방향 없는 그래프이므로
			relation[x].add(y); 
			relation[y].add(x); 
		}
		int answer = 0;
		for(int i = 0; i < N; i++) { //모든 정점에 대해 시작
			
			if(!visited[i]) {//i가 방문하지 않은 정점인 경우 i정점 방문
				answer++;
				dfs(i);
			}
			
			// dfs로 한 점에서 시작하여 갈 수 있는 모든 점들을 밤문
			// 한 점에  대해 dfs 끝나면 방문하지 않은 정점 중 하나를 선택하여 dfs -> 모두 방문할 때 까지 반복
		}
		
		System.out.println(answer);
	}
}
