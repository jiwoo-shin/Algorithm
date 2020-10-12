package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2178 extends Solution {

	static boolean[][] visited; //  방문여부
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j <M; j++) {
				if(tmp.charAt(j) == '0')visited[i][j] = true; //0 인 경우는 방문할 필요가 없음
			}
		}
		// int[][] dist, ->i,j  에서의 가중치를 저장
		//ArrayList queue -> 다음에방문해야할노드들.. 요런식으로 나눠서도 가능..
		
		int[][] queue = new int[N][M]; // int[][] queue = new int[N*M][3]; // BFS를 위한 큐 (index가저장됨 >> 큐에최대로 저장가능한 개수는 N*M개,표현하기 위해서는 2개 좌표 필요)
		int index = 0; // 현재탐색중인 큐의위치
		int end = 1; // 추가할 큐의위치
		
		queue[index][0] = 0; //i좌표
		queue[index][1] = 0; //j좌표
		queue[index][2] = 1; // 현재까지 거친 칸의 수 (맨 처음칸 포함)
		visited[0][0] = true; // 초반 검색했다는 표시추가 필요 안그러면 뒤로 가서 큐 인덱스가 배열 길이보다 크게참조될수도..
		
		int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		boolean arrive = false;
		
		while(index <= end && !arrive) { // 마지막까지 모두 탐색한 경우 탐색 종료 index!=end 아님 이러면 마지막 정점 탐색을안함 -> 마지막 정점까지 탐색했는데 더이상 추가할 것이 없는 경우(index > end)에 끝나야함
			
			for(int i = 0; i < 4; i++) { // 동서남북에 대해 확인
				int next_i = queue[index][0] + move[i][0];
				int next_j = queue[index][1] + move[i][1];
				if(next_i >= 0 && next_i < N && next_j >= 0 && next_j < M) { // 탐색 가능한 정점인 경우
					if(!visited[next_i][next_j]) { //방문하지 않은 정점인 경우
						queue[end][0] = next_i;
						queue[end][1] = next_j;
						queue[end][2] = queue[index][2]+1;
						visited[next_i][next_j] = true; // 방문했다는 표시를 해야함
						end++;
						if(next_i == N-1 && next_j == M-1) { // 목적지에 도착한 경우
							arrive = true;
							break; // 동서남북 확인 코드에 대해 탈출
						}
					}
				}
			}
			index++;
		}
		System.out.println(queue[end-1][2]); //마지막 end는 추가되어야하는 위치이므
		
	}
}
