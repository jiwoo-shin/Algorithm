package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15657 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    static int[] numbers;
	static void dfs(int length, int[] array) { // length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), array : 수열
		if(length == M) {
			for(int i = 0; i< length; i++)sb.append(numbers[array[i]]).append(" ");
			sb.append("\n");
		} else {
			int start = length==0? 0 : array[length-1]; // 마지막으로 추가된 숫자를 확인
			for(int i = start; i < N; i++) { // 비내림차순이므로 마지막으로 방문한 노드 이상의 노드만 확인함
				array[length] = i; 
				dfs(length+1, array); // 이 dfs를 재귀적으로 호출하면서 끝까지 이동함
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
		dfs(0, new int[M]); 
		System.out.print(sb.toString());
	}
}
