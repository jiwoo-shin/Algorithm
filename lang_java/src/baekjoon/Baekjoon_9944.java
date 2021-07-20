package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_9944 extends Solution {
	
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, obstacleCount;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmpInput = br.readLine();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int caseCount = 0;
		
		while(tmpInput!= null) {
			//  입력값을 입력받는다
			int answer = Integer.MAX_VALUE;
			st = new StringTokenizer(tmpInput);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			obstacleCount = 0;
			caseCount++;
			boolean[][] visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				String tmpS = br.readLine();
				for(int j = 0; j < M; j++) {
					char tmp = tmpS.charAt(j);
					if(tmp == '*') {
						obstacleCount++;
						visited[i][j] = true;
					} else {
						visited[i][j] = false;
					}
				}
			}
			
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visited[i][j]) {
						visited[i][j] = true;
						for(int direction = 0; direction < 4; direction++) {
							int nextI = i + move[direction][0];
							int nextJ = j + move[direction][1];
							if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) { // [nextI][nextJ]로 이동이 가능한경우
								answer = Math.min(answer, go(direction, nextI, nextJ, visited, 1, 1));
							} else if(obstacleCount + 1 == N*M){ // 이동을 하지 않아도 되는 경우
								answer = 0;
							}
						}
						visited[i][j] = false; // 다음 for문에서는 경로가 다르므로 i, j가 방문된 곳으로 되면 안되므로..
					}
				}
			}

			if(answer == Integer.MAX_VALUE) answer = -1;
			sb.append("Case ").append(caseCount).append(": ").append(answer).append("\n");
			
			tmpInput = br.readLine();
		}
		
		System.out.println(sb);
	}

	static boolean isAbleToGo(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}
	
	static int go(int direction, int i, int j, boolean[][] visited, int visitedCount, int moveCount) {
		int answer = Integer.MAX_VALUE;
		// i, j를 방문한다.
		visited[i][j] = true;
		visitedCount++;
		
		if(visitedCount + obstacleCount == N*M) { // 방문가능한 모든 곳을 방문한경우
			visited[i][j] = false;
			return moveCount;
		}
		
		// 다음 방문지를 고른다.
		int nextI = i + move[direction][0];
		int nextJ = j + move[direction][1];
		if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) { // 가던 방향으로 갈 수 있는 경우
			answer = go(direction, nextI, nextJ, visited, visitedCount, moveCount); // NO MIN
		} else { // 가던 방향으로 갈 수 없는 경우
			for(int l = 0; l < 4; l++) {
				if(l != direction) { // 가던 방향이 아닌 경우
					nextI = i + move[l][0];
					nextJ = j + move[l][1];
					if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) {
						answer = Math.min(go(l, nextI, nextJ, visited, visitedCount, moveCount + 1), answer);
					}
				}
			}
		}

		visited[i][j] = false; // 다른 이동 시에 
		return answer;
	}
	
}