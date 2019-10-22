package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15649 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
	static void dfs(int length, boolean[] visited, int[] array) { // length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), visited : 방문한 배열, array : 수열
		if(length == M) { // 수열을 생성. 더 이상 이어지는 순열을 계산할 필요는 없음
			for(int i = 0; i< length; i++)sb.append(array[i]).append(" ");
			sb.append("\n");
		} else { // 다음 방문 노드를 더 계산해야함
			for(int i = 0; i < N; i++) { // i는 방문할 예정인 노드
				if(!visited[i]) { // 방문한적이 없다면 방문
					visited[i] = true;
					array[length] = (i+1); // 노드 방문
					dfs(length+1, visited, array); // 이 dfs를 재귀적으로 호출하면서 끝까지 이동함
					visited[i] = false; //다음 for문에서는 다른 경로이므로 현재 경로가 영향을 주면 안됨
				}
			}
		}
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dfs(0, new boolean[N], new int[M]);
		System.out.print(sb.toString());
	}
}
