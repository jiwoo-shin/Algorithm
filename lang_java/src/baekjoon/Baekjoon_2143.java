package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2143 extends Solution {
	
	static int T, n, m;
	static int[] A, B;
	static int[] total_A, total_B;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		A = new int[n];
		st = new StringTokenizer(br.readLine());
		int length_total_A = (n*n + n) >> 1;
		total_A = new int[length_total_A];
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			total_A[i] = A[i];
		}
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		B = new int[m];
		int length_total_B = (m*m + m) >> 1;
		total_B = new int[length_total_B];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			total_B[i] = B[i];
		}
		int index_A = n;
		for(int i = 1; i < n; i++) { // i+1 개를 사용할 때의 합
			for(int j = 0; j < n-i; j++) { //index j 부터 시작
				total_A[index_A] = total_A[index_A++-(n+1-i)] + A[j+i]; // A[j]부터 A[j+i] 까지의 합 = A[j] 부터 A[j] 부터 A[j+i-1] 까지 합 + A[j+i]
			}
		}
		int index_B = m;
		for(int i = 1; i < m; i++) { // i+1 개를 사용할 때의 합
			for(int j = 0; j < m-i; j++) { //index j 부터 시작
				total_B[index_B] = total_B[index_B++-(m+1-i)] + B[j+i]; 
			}
		}
		index_A = 0;
		index_B = length_total_B-1;
		Arrays.sort(total_A);
		Arrays.sort(total_B);
		long answer = 0;
		while(index_A < length_total_A && index_B >= 0) {
			int sum = total_A[index_A] + total_B[index_B];
			if(sum < T) {
				index_A++;
			} else if(sum > T) {
				index_B--;
			} else {
				int same_A = getSameNum(index_A, length_total_A, total_A, 1);
				int same_B = getSameNum(index_B, length_total_B, total_B, -1);
				answer += (long)same_A*same_B;
				index_A += same_A;
				index_B -= same_B;
			}
		}
		System.out.println(answer);
	}
	static int getSameNum(int index, int length, int[] array, int direction) {
		int tmp = index+direction;
		while(tmp >= 0 && tmp < length && array[index] == array[tmp]) {
			tmp += direction;
		}
		return Math.abs(tmp - index);
	}
}

