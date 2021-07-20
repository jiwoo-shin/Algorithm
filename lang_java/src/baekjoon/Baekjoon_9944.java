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
			//  �Է°��� �Է¹޴´�
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
							if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) { // [nextI][nextJ]�� �̵��� �����Ѱ��
								answer = Math.min(answer, go(direction, nextI, nextJ, visited, 1, 1));
							} else if(obstacleCount + 1 == N*M){ // �̵��� ���� �ʾƵ� �Ǵ� ���
								answer = 0;
							}
						}
						visited[i][j] = false; // ���� for�������� ��ΰ� �ٸ��Ƿ� i, j�� �湮�� ������ �Ǹ� �ȵǹǷ�..
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
		// i, j�� �湮�Ѵ�.
		visited[i][j] = true;
		visitedCount++;
		
		if(visitedCount + obstacleCount == N*M) { // �湮������ ��� ���� �湮�Ѱ��
			visited[i][j] = false;
			return moveCount;
		}
		
		// ���� �湮���� ����.
		int nextI = i + move[direction][0];
		int nextJ = j + move[direction][1];
		if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) { // ���� �������� �� �� �ִ� ���
			answer = go(direction, nextI, nextJ, visited, visitedCount, moveCount); // NO MIN
		} else { // ���� �������� �� �� ���� ���
			for(int l = 0; l < 4; l++) {
				if(l != direction) { // ���� ������ �ƴ� ���
					nextI = i + move[l][0];
					nextJ = j + move[l][1];
					if(isAbleToGo(nextI, nextJ) && !visited[nextI][nextJ]) {
						answer = Math.min(go(l, nextI, nextJ, visited, visitedCount, moveCount + 1), answer);
					}
				}
			}
		}

		visited[i][j] = false; // �ٸ� �̵� �ÿ� 
		return answer;
	}
	
}