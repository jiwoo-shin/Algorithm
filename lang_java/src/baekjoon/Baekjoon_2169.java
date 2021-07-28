package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2169 extends Solution {
	
	
	static int[][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // �� �� �� ��
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] memo = new int[N][M];
		// int[][][] memo = new int[N][M][3]; // �Ʒ���, ��������, ����������
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i == 0) {
					if(j == 0) memo[i][j] = map[i][j];
					else memo[i][j] = map[i][j] + memo[i][j-1]; // �ֻ�� ���ǵ��� �׻� ���������� �������� �ۿ�.. ���������� ������ ���� �ö���� ���ۿ� ���¿� ���δ� �����ϱ�..
				}
			}
		}
		
		//  ����, ������, �Ʒ����⿡�� -> i, j �� �� �� �󸶳� ���� ������ ì�� �� �ִ���
		for(int i = 1; i < N; i++) {
			// toLeft, toRight �����̴�. �Բ� ���� ���� ��ġ�� ������ �湮�ϴ� ���� ���ȴ�.
			// ��������
			int[] toLeft = new int[M];
			toLeft[M-1] = memo[i-1][M-1] + map[i][M-1]; // memo[i-1][M-1] + map[i][M-1] : �Ʒ���
			for(int j = M-2; j >= 0; j--) {
				toLeft[j] = Math.max(toLeft[j+1], memo[i-1][j]) + map[i][j]; // toLeft[j+1] : ��������, memo[i-1][j]: �Ʒ���
			}
			
			// ����������
			int[] toRight = new int[M];
			toRight[0] = memo[i-1][0] + map[i][0];//memo[i][0]; // Integer.MIN_VALUE.. �̷��� ������ ���������� �̵��� �� �ǵ����� ���� ���ڸ� ����ϰ� �ȴ�.
			for(int j = 1; j < M; j++) {
				// toRight[j+1] : ����������, memo[i-1][j]: �Ʒ���
				toRight[j] =  Math.max(toRight[j-1], memo[i-1][j]) + map[i][j]; // toRight[j-1] + map[i][j];�� �ƴѰ� toLeft ���� memo�� Ŀ�� toRIght ���ٴ� ���� �� �����Ƿ� ���⼭�� max, min Ȯ�� �ʿ�
			}
			for(int j = 0; j < M; j++) memo[i][j] = Math.max(toRight[j], toLeft[j]); 
		}
		System.out.println(memo[N-1][M-1]);
	}
	
	
}