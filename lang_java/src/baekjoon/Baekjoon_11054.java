package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11054 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] memo = new int[N][2]; 
		// memo[i][0] : i+1 번째 원소를 사용하고 A[0]부터 시작할 때(왼쪽부터 -> 오른쪽으로) 가능한 증가수열의 가장 긴 길이
		// memo[i][1] : i+1 번째 원소를 사용하고 A[N-1]부터 시작할 때(오른쪽부터 -> 왼쪽으로) 가능한 증가수열의 가장 긴 길이
		// answer = max(memo[i][0] + memo[i][1] - 1) // 0~ i+1 증가 부분 수열 , N-1 ~ i+1 증가부분수열(-> i+1 ~ N-1 감소 수열 ) => 0 ~ N-1 바이토닉 수열
		int[] subsequence = new int[N+1];
		int length = 1;
		subsequence[length] = A[0];
		memo[0][0] = 1;
		// 왼쪽부터 비교 (증가수열)
		for(int i = 1; i < N; i++) {
			memo[i][0] = 1;
			if(subsequence[length] < A[i]) {
				subsequence[++length] = A[i];
				memo[i][0] = length;
			} else {
				int j = 0;
				while(A[i] > subsequence[j]) {
					j++;
				}
				subsequence[j] = A[i];
				memo[i][0] = j;
			}
		}
		length = 1;
		subsequence[length] = A[N-1];
		memo[0][1] = 1;
		// 오른쪽부터 비교 (감소수열)
		for(int i = N-2; i > -1; i--) {
			memo[i][1] = 1;
			if(subsequence[length] < A[i]) {
				subsequence[++length] = A[i];
				memo[i][1] = length;
			} else {
				int j = 0;
				while(A[i] > subsequence[j]) {
					j++;
				}
				subsequence[j] = A[i];
				memo[i][1] = j;
			}
		}
		int answer = 0;
		for(int i = 0; i < N; i++) {
		    // 바이토닉 : 증가수열 + 감소수열
			answer = Math.max(answer, memo[i][0] + memo[i][1] - 1);
		}
		System.out.println(answer);
		
		/*
		int[][] memo = new int[N][2];
		 // memo[i][0] : i+1번째 원소를 사용하는 바이토닉 부분수열 중 i+1 번째에서 증가할 때  가능한 가장 긴 길이 
		 // memo[i][1] : i+1번째 원소를 사용하는 바이토닉 부분수열 중 i+1 번째에서 감소할 때 가능한 가장 긴 길이 
		  
		memo[0][0] = 1; 
		memo[0][1] = 1; 
		int answer = 1;
		
		for(int i = 1; i < N; i++) {
			memo[i][0] = 1;
			memo[i][1] = 1;
			for(int j = 0; j < i; j++) {
				int tmp_1 = memo[j][0] + 1; // 증가는 증가->증가 밖에 못함
				int tmp_2 = Math.max(tmp_1, memo[j][1] + 1); // 감소하는 경우는 증가->감소, 감소->감소 일 수 있으므로  두 가지 모두 따진다.
				if(A[j] < A[i] && memo[i][0] < tmp_1) memo[i][0] = tmp_1; // 
				else if(A[j] > A[i] && memo[i][1] < tmp_2) memo[i][1] = tmp_2; 
			}
			
			// 만들 수 있는 수열의 가장 긴 길이
			answer = Math.max(Math.max(answer, memo[i][0]), memo[i][1]);
		}
		System.out.println(answer);*/
        
	}
	
}