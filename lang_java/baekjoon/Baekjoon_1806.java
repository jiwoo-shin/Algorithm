package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1806 extends Solution {
	
	static int N, M;
	static int[] numbers;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(get());
		
	}
	static int get() {
		int start = 0, end = 0;
		int sum = 0;
		int answer = N+1;
		
		while(end < N || sum >= M) { // end < N 이거나 sum >= M 이면 돈다. (1. end < N 인 경우 / 2. end >= N 인 경우 sum >= M 이면 start 제거해도 가능한지 해봐야함)
			if(sum < M) { // 값이 더 작은 경우 (더 더할 수 있는 경우) end 추가
				 sum += numbers[end++];
			} else { // 값이 더 큰 경우(더 더할 수 없는 경우) start 제거
				sum -= numbers[start++];
				answer = Math.min(answer, end - start + 1); // 값을 구한 경우
			} 
		}
		
		if(answer == N+1) return 0;
		return answer;
	}
}

