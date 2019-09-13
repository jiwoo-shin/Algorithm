package baekjoon;

import java.util.Scanner;

public class Baekjoon_1003 extends Solution{

	/*
	 * 요약 : 피보나치 0,1 이호출되는 횟수 구하기
	 * */
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		// 0 ~ 40 사이의 수에서 각각 0과 1이 몇번 호출되는지 계산
		
		/* 2차원 배열로 각각 저장할 수도 있으나 0은 1->0->1->1... 1은 0->1->1->2... 이렇게 되므로 
		 *  0 : 1 0 1 1 2 ...
		 *  1 : 0 1 1 2 3 ...
		 *        0은 1의 n-1과 동일(단, n > 0 n=0일 경우 0 : 1, 1 : 0)  */
		
		/*
		array[n-1][0] = 숫자 n일때 0이 호출되는 횟수, array[n-1][1] = 숫자 n일때 1이 호출되는 횟수
		int[][] array = new int[41][2];
		array[0][0] = 1;
		array[0][1] = 0;
		array[1][0] = 0;
		array[1][1] = 1;
		for(int i = 2; i < 41; i++) {
			array[i][0] = array[i-1][0] + array[i-2][0];
			array[i][1] = array[i-1][1] + array[i-2][1];
		}
		*/
		int[] array = new int[41];
		array[0] = 0;
		array[1] = 1;
		for(int i = 2; i < 41; i++) {
			array[i] = array[i-1] + array[i-2]; 
		}
		
		int time = sc.nextInt();
		for(int i = 0; i < time; i++) {
			int num = sc.nextInt();
			if(num > 0)System.out.printf("%d %d\n", array[num-1], array[num]);
			else System.out.printf("%d %d\n", 1, 0);
		}
		
	}

}
