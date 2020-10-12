package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_10973 extends Solution {

	@Override
	public void solution() {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int change_index = -1, change = 0;
		int tmp;
		
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		
		for(int i = N-1; i > 0; i--) {
			if(array[i-1] > array[i]) { //i-1 번째 자리수가 바뀌어야함
				change_index = i-1;
				change = array[change_index]; 
				break;
			}
		}
		if(change_index == -1) { // 오름차순으로 정렬된 경우
			System.out.println(-1);
		} else {
			
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] < change) { 
					array[change_index] = array[i]; 
					array[i] = change;
					// 바꾼 후 change_index 이후 값은 오름차순 정렬을 해야함
					
					int sort_limit = ((N-1)-(change_index+1))/2; // 숫자 바꾸어야하는 횟수
					// 기존이 내림차순 정렬이였으므로 끝과 끝을 바꾸면 된다.
					
					//숫자를 바꾼다. 
					for(int j = 0; j <= sort_limit; j++) {//for(int j = change_index+1; j <= sort_limit; j++) {
						tmp = array[change_index+1+j];
						array[change_index+1+j] = array[N-1-j];
						array[N-1-j] = tmp;
					}
					
					break;
				}
			}
			for(int i = 0;i<N;i++) {
				System.out.print(array[i]+" ");
			}
		}
		
	}

}
