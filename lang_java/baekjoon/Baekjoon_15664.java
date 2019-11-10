package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15664 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    static int[] numbers;
    
    // 남은 숫자가 동일하면 그 이후로 동일한 수열을 생성하므로 length에서 for문이 돌때 array[length]는 모두 달라야함.
    // length 번째에 동일한 숫자가 들어갈 경우, 그 이후 순열이 반복되어 나타남 
    //(for문이 도는것은 이전 순열까지같다는 것이고, length 번째까지같으면 남은 숫자가 동일하므로 그 이후 생성될숫자가 동일해짐)
    
    
    
    // length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), array : 수열, visited: i번째 노드 방문하였는지 확인
	static void dfs(int length, boolean[] visited, int[] array) { 
		if(length == M ) {
			for(int i = 0; i < length; i++) {
				sb.append(numbers[array[i]]).append(" ");
			}
			sb.append('\n');
		} else {
			int start = length == 0? 0 :array[length-1]; // array[length-1] < array[length] 여야하므로
			int prev_num = 0; // 이전에 추가된,, 
			for(int i = start; i < N; i++) { 
				//!visited[i] : 노드 i를 방문하지 않은 경우만 방문
				// prev_num < numbers[i]: 남은 숫자가 같으면 다음 경로도 모두 같게 나온다.
				//(for문 도는게 의미가 이전 까지는 모두동일을 의미하므로 이 같으면 노노 -> 남은 숫자가 같으므로 완전히중복이ㅣㅁ..)
				if(!visited[i] && prev_num < numbers[i]) { //
					visited[i] = true;
					array[length] = i; 
					dfs(length+1, visited, array);
					prev_num = numbers[i];
					visited[i] = false;
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
		dfs( 0, new boolean[N], new int[M]); 
		System.out.print(sb.toString());
		
	}
}
