package codeground;
import java.util.Scanner;

import common.Solution;

public class Codeground_79 extends Solution {
	/*  요약 : Pivot을 만족하는 수가 몇개 존재하는지 구하세요.
	 * 
		a 기준 왼쪽 : 모두 작은수, a 기준 오른쪽 : 모두 큰 수.. > a는 Pivot 조건 만족
		-> a 기준 왼쪽: 최대, 오른쪽 : 최소인 배열 구한 후 비교
	*/
	static int Answer;
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			int N = sc.nextInt();
			
			int[] array = new int[N];
			int[] max = new int[N];//a 기준 왼쪽에서 최대 숫자 : 최대숫자가 a보다 작아야함. 
			int[] min = new int[N];//a 기준 오른쪽에서 최소숫자 : 최소숫자가 a보다 커야함.
			
			array[0] = sc.nextInt();
			max[0] = -1;
			
			for(int i = 1; i < N; i++) {
				array[i] = sc.nextInt();
				if(max[i-1] < array[i-1]) max[i] = array[i-1];
				else max[i] = max[i-1];
			}
			
			min[N-1] = Integer.MAX_VALUE;
			
			for(int i = N-1; i > 0; i--) {
				if(min[i] > array[i]) min[i-1] = array[i];
				else min[i-1] = min[i];
			}
			
			for(int i = 0; i < N; i++) {
				if((min[i]>array[i])&&(max[i]<array[i])) {
					Answer++;
				}
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
