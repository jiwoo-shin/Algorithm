package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_2667 extends Solution {

	static boolean visited[][]; // 정점 방문 여부
	static int N; // 입력받은 N 값
	static int complex = 0; // 단지 (현재 탐색중인)
	static int[] number; // 각 단지의 개수
	
	static void search(int i, int j) {
		visited[i][j] = true; //방문
		number[complex]++; // 단지에 포함된 집의 개수

		// 현재 집에서 방문 가능한 다음집을 방문
		if(j < N-1)if(!visited[i][j+1])search(i, j+1);
		if(j > 0)if(!visited[i][j-1])search(i, j-1);
		if(i > 0)if(!visited[i-1][j])search(i-1, j);
		if(i < N-1)if(!visited[i+1][j])search(i+1, j);
		
	}
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][N];
		int max_complex = N*N/2+1;// 단지의 최개 개수는 띄엄띄엄 있는 경우니까 (N*N)/2 반올림 개수만큼 있을 수 있ㅇ음
		number = new int[max_complex]; 
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for(int j = 0; j <N; j++) {
				if(tmp.charAt(j) == '0')visited[i][j] = true; //0 인 경우는 방문할 필요가 없음
			}
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) { // 방문하지 않은 정점인 경우
					search(i, j); // 방문
					complex++; // 방문 후 다음 시작점에서 시작하는 경우는 다른 단지임
				}
			}
		}
		Arrays.sort(number); // 정렬
		System.out.println(complex); // 개수는complex-1개지만 단지는 1부터 시작하고 complex는 0부터 시작하므로 complex단지 까지 있음
		for(int i = 0; i < complex; i++) { //개수는 complex-1개
			System.out.println(number[max_complex - complex + i]); // 0에서 complex로  출력하면 max_complex의 채워지지 않은 부분인 0부터 출력되므로..의도하지 않은 결과 출력됨
		}
	}
}
