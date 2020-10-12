package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_3019 extends Solution {

	static int[][][] block = {
			{{0},{0, 0, 0, 0}},
			{{0, 0}},
			{{0, 0, 1}, {1, 0}},
			{{1, 0, 0}, {0, 1}},
			{{0, 0, 0}, {0, 1}, {1, 0, 1}, {1, 0}},
			{{0, 0, 0}, {0, 0}, {0, 1, 1}, {2, 0}},
			{{0, 0, 0}, {0, 2}, {1, 1, 0}, {0, 0}}
	};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		int[] height = new int[C];
		for(int i = 0; i < C; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		int rotate = block[P].length;
		for(int i = 0; i < C; i++) {
			for(int j = 0; j < rotate; j++) {
				if(isOk(block[P][j], height, i, C)) answer++;
			}
		}
		System.out.println(answer);
	}
	static boolean isOk(int[] block, int[] height, int i, int C) {
		int block_width = block.length;
		if(i + block_width > C) return false; // 블록이 넘치는 경우
		for(int k = 1; k < block_width; k++) {
			if(height[i+k]-height[i+k-1] != block[k]-block[k-1]) return false;
		}
		return true;
	}
}
