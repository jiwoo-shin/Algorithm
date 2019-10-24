package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15654 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    static int[] numbers;
	static void dfs(int number, int length, boolean[] visited, int[] array) { // number: 이전에 추가한 노드가 몇번째인지 (0부터 시작), length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), visited : 방문한 배열, array : 수열
		if(length == M) { // 수열을 생성(array[i]는 numbers의 인덱스 수열임)
			for(int i = 0; i< length; i++)sb.append(numbers[array[i]]).append(" ");
			sb.append("\n");
		} else { // 다음 방문 노드를 더 계산해야함
			for(int i = 0; i < N; i++) { // i는 방문할 예정인 노드 (number는 방문하였으므로 number 이후부터 방문한다.)
				if(!visited[i]) { // 방문한적이 없다면 방문 (1번노드 방문 시 visited[0] = true, visited의 인덱스는 실제 방문 노드보다 1 작음)
					visited[i] = true;
					array[length] = i; // 노드 방문 .. (index)
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
		numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		
		// index를 수열로 만들어준다.
		dfs(-1, 0, new boolean[N], new int[M]); // 실제로 노드가 추가되지는 않았으므로 -1부터 시작
		System.out.print(sb.toString());
	}
}
