package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14890 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 지도 크기
		int L = Integer.parseInt(st.nextToken()); // 경사로 길이
		int[][][] map = new int[2][N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[0][i][j] = Integer.parseInt(st.nextToken());
				map[1][j][i] = map[0][i][j];
			}
		}
		int answer = 0;
		for(int t = 0; t < 2; t++) {
			for(int i = 0; i < N; i++) {
				if(canGo(map[t][i], N, L)) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	static boolean canGo(int[] map, int N, int L) { // 한줄에 대해 지나갈 수 있는지 확인한다.
		boolean[] isSlope = new boolean[N];
		for(int i = 0; i < N-1; i++) {
			int difference = map[i] - map[i+1]; // i 와 i+1의 높이 차이
			if( difference > 1 || map[i] - map[i+1] < -1) return false; // 높이가 다르지만 경사로를 놓을 수 없는 경우 //벽이 높아서 경사로를 놓을 수 없는 경우
			else if(difference == -1 || difference == 1) { // 경사로를 놓을 수 있는 경우
				//  difference가 1인 경우(i+1이 더 낮음) > 경사로의 시작 위치 :i+1 / 경사로의 마지막 위치 : i+1+L-1
				//  difference가 -1인 경우(i+1이 더 높음) > 경사로의 시작 위치 :i-(L-1) / 경사로의 마지막 위치 : i
				int start_slop = i + 1 + (difference - 1)/2*L; // 경사로의 시작 위치
				int last_slop = i + (difference + 1)/2*L; // 경사로의 마지막 위치
				for(i = start_slop;i < last_slop; i++) { // start_slop~last_slop에 경사로를 놓을 수 있는지 판단함
					if(i < 0 || i >= N-1 || isSlope[i] || map[i] != map[i+1]) return false; // 놓을 수 없는 위치거나/ 이미 경사로가 놓아져 있거나/경사로 놓는 중간에 높이가 바뀌어 버리면 경사로를 놓을 수 없다.
					isSlope[i] = true; // 경사로를 놓는다.
				}
				if(isSlope[i]) return false;
				isSlope[i] = true; // 마지막 last_slop도 경사로를 놓는다.
				i = i - (difference + 1)/2; // i+1이 더 낮은 경우 i--를 해준다. 경사로의 끝이 낮기 째문에 다시 올라가야하는지 비교 필요. i+1이 더 높은 경우는 경사로의 끝이  높게 도착한 것이므로 i+1부터 비교해도 문제 없음.
			}
			// 경사로가 필요 없는 경우는 다음 i에 대해서 구한다.
		}
		return true;
	}
}
