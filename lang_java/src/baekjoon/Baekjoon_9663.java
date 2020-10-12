package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_9663 extends Solution {

	static int N;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] queens = new int[N]; // 퀸들의 위치를 저장 
		System.out.println(nQueen(0, queens));
	}
	// 현재 now 가 가능한 값인지 판단
	static boolean check(int depth, int[] queens, int now) {
		for(int i = 0; i < depth; i++) {
			//if(x == queens[i][0]) return false; // 가로 같은 줄은 depth로 항상 달라지기 때문에 없음
			if(now == queens[i]) return false; // 세로 같은 줄
			if(now+depth == queens[i] + i) return false; // / 대각선
			if(now-depth == queens[i] - i) return false; // \ 대각선
		}
		return true;
	}
	// n-queen 개수 반환
	static int nQueen(int depth, int[] queens) {
		if(depth == N) {
			return 1; // 가능. 현재 가능하므로 1 반환
		} else {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(check(depth, queens, i)) {
					queens[depth] = i;
					sum += nQueen(depth+1, queens); // 끝까지 가면 1인데 그것들을 모아야함
				}
			}
			return sum;
		}
	}
}