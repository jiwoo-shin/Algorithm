package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14391 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] paper = new int[N][M]; // 보드
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				paper[i][j] = tmp.charAt(j) - '0';
			}
		}
		int max = 1 << (N*M); // 가능한 최대값
		int answer = 0;
		int n_max = N*M-1;
		for(int i = 0; i < max ; i++) {// 가능한 모든 경우의 수에 대해 돌려본다.
			int tmp = 0;
			boolean [] prev_ver = new boolean[M];
			int[] tmp_ver = new int[M];
			for(int j = 0; j < N; j++) {
				boolean prev_hor = false;
				int hor = 0; // 한줄 가로
				for(int k = 0; k < M; k++) {
					int shift = (n_max - (j*M + k)); 
					boolean ishor = (i & (1 << shift)) == 0; // 가로인지 여부
					if(ishor) {	// 가로인 경우
						if(prev_hor) { // (연결된 가로)
							hor = hor*10 + paper[j][k];
						} else { // (끊긴 가로)
							tmp += hor;
							hor = paper[j][k];
						}
					} else { // 세로인 경우 (==1 로 비교할 경우 2 & 2 = 2 (!= 1) 이므로 세로이지만 세로로 안들어 가는 경우 발생)
						if(prev_ver[k]) { // 같은 세로축, 이전에도 세로인 경우 (연결된 세로)
							tmp_ver[k] = tmp_ver[k]*10 + paper[j][k]; 
						} else { // 같은 세로축, 이전에 세로가 아닌 경우(끊긴 세로)
							tmp += tmp_ver[k]; // 기존 k 세로줄의 합을 넣어줌. 연결이 끊겼으므로
							tmp_ver[k] = paper[j][k]; //  이전에 세로가 아니므로 *10 + 가 아닌 그냥 대입만 해준다.
						}
					}
					prev_hor = ishor;
					prev_ver[k] = !ishor;
				}
				tmp += hor;
			}
		
			for(int l = 0; l < M; l++) tmp += tmp_ver[l];
			answer = Math.max(answer, tmp);
		}
		System.out.println(answer);
	}
}