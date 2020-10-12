package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2003 extends Solution {
	
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
		int answer = 0;
		for(int i = 0; i < N; i++) {
			answer += get(i);
		}
		System.out.println(answer);
		
	}
	static int get(int start) {
		int sum = 0;
		for(int i = start; i < N && numbers[i] <= M && sum < M; i++) {
			sum += numbers[i];
		}
		if(sum == M) return 1;
		else  return 0;
	}
}

