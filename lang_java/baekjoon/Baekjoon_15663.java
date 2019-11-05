package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15663 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    static int[] numbers;
    static int[] prev;
    
	static void dfs(boolean compare, int length, boolean[] visited, int[] array) { // compare: 비교가 필요한지(length-1 까지 비교했을 때 모두 같거나작은지). length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), array : 수열, visited: i번째 노드 방문하였는지 확인
		if(length == M ) { //여기 &&compare으로하면 안됨
			if(!compare) { // compare이 true인 경우는 이전에 추가된 경로와 현재 추가된 경로와 이전에 추가된 경로가 모두 같은 경우이므로 compare가 false인 경우가 새로운 경로.
				for(int i = 0; i < length; i++) {
					prev[i] = numbers[array[i]]; //그냥 array 아님. 그냥 array는 당연히 다 다름. a < b < c 일때 numbers[a] <= numbers[b] <= numbers[c] 를 만족하므로 bc ca 자체는 bc < ca이지만 경로로 갈 경우 numbers[b]==numbers[c]이고 numbers[c] > numbers[a] 이면 경로의 숫자가 ca 가더 커짐 
					sb.append(prev[i]).append(" ");
				}
				sb.append('\n');
			}
		} else {
			for(int i = 0; i < N; i++) { 
				if(!visited[i]) { //노드 i를 방문하지 않은 경우만 방문
					array[length] = i; 
					visited[i] = true;
					if(length == 0) { // 만약 경로의 시작을 추가할 경우 
						// 이전에 추가된 수열과 비교. 만약 이전에 추가된 수열의 첫번째 자리보다 클경우 그 수열 시작 가능.(같은 경우는 어차피 같은 경로의 반복이므로 호출하지 않음.. 남는 수가 같고 그 수들로 경로를 만드는 거니까)
						if(prev[length] < numbers[array[length]])dfs(false, length+1, visited, array); 						
					} else {
						if(compare) { // length -1까지의 수열이 모두 동일한 경우, 호출은 필요함  모두 같은 경우 마지막이 더 클수도있고.
							
							//prev[length] > numbers[array[length]] 인 경우는 이전에 추가된수열보다 아예 작아짐. 그 이후에뭐를넣든. 따라서 호출하지 않음
							// 같은 경우도 호출이 필요한데, 이전 수열과 같은 자리수의 숫자가 같은 경우 다음 숫자에서 크기를 판별해야하기 때문
							if(prev[length] <= numbers[array[length]])dfs(prev[length] == numbers[array[length]], length+1, visited, array); // length 도 같은지 비교 후 same 재설정	
							
						} else { // length - 1까지의 수열이 모두 동일하지 않은 경우
							dfs(false, length+1, visited, array); 	// 비교 당연히 할필요가 없음. 이전 수열이 ㅋ크다는것은 그뒤로 뭐가 오든 더 크다는것..
						}
					}
					visited[i] = false;
					compare  = true; // for문을 한번 돌면 그다음은 length - 1 까지는 모두 같은 경로이므로 비교가 필요하다.
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
		prev = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);
		
		// index를 수열로 만들어준다.
		dfs(false, 0, new boolean[N], new int[M]); 
		System.out.print(sb.toString());
		
	}
}
