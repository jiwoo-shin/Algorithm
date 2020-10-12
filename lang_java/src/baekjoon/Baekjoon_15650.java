package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15650 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
	static void dfs(int number, int length, boolean[] visited, int[] array) { // number: 이전에 추가한 노드가 몇번째인지 (0부터 시작), length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), visited : 방문한 배열, array : 수열
		if(length == M) { // 수열을 생성. 더 이상 이어지는 순열을 계산할 필요는 없음
			for(int i = 0; i< length; i++)sb.append(array[i]).append(" ");
			sb.append("\n");
		} else { // 다음 방문 노드를 더 계산해야함
			for(int i = number+1; i < N; i++) { // i는 방문할 예정인 노드 (number는 방문하였으므로 number 이후부터 방문한다.)
				//visited는 사실 불필요 어차피 array[length] >array[length-1]을 항상 만족하기 때문
				if(!visited[i]) { // 방문한적이 없다면 방문 (1번노드 방문 시 visited[0] = true, visited의 인덱스는 실제 방문 노드보다 1 작음)
					visited[i] = true;
					array[length] = (i+1); // 노드 방문 .. 실제 노드이므로 +1을 해준다... 이곳의 i만 실제 값으로 바꿔주고 나머지에서 사용되는 i는 0부터 시작하는 값
					dfs(i, length+1, visited, array); // 이 dfs를 재귀적으로 호출하면서 끝까지 이동함
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
		
		dfs(-1, 0, new boolean[N], new int[M]); // 실제로 노드가 추가되지는 않았으므로 -1부터 시작
		System.out.print(sb.toString());
	}
}
