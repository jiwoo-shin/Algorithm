package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_10971 extends Solution {
	static int answer = Integer.MAX_VALUE;
	static int[][] W;
	
	static int[] change(int[] array, int change_index) {
		int N = array.length;
		int tmp;
		for(int k = 0; k < N-1 - change_index ; k++ ) {//k는 그저 횟수 (change_index에 대해 N-1 - change_index 번 반복 필요)
			if(change_index < N-2 )array = change(array, change_index+1); //change_index는 N-2 까지 가능하므로.
			int change = array[change_index];
			
			//change_index와 어디가 바뀌어야하 하는지 확인
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] > change) { 
					array[change_index] = array[i]; 
					array[i] = change;
					
					int sort_limit = ((N-1)-(change_index+1))/2; 
					
					//숫자를 바꾼다. 
					for(int j = 0; j <= sort_limit; j++) {
						tmp = array[change_index+1+j];
						array[change_index+1+j] = array[N-1-j];
						array[N-1-j] = tmp;
					}
					
					break;
				}
			}
			int result = 0;
			for(int i = 0; i<N; i++) { //
				//순열 array에 대해... 이동 경로는 a[0] -> a[1]....a[N-1]->a[0]
				if(W[array[i%N]][array[(i+1)%N]] != 0)result += W[array[i%N]][array[(i+1)%N]];
				else break;
				
				if(i==N-1) {
					if(result < answer)answer = result;
				}
			}
		}
		if(change_index < N-2)array = change(array, change_index+1); // 마지막으로 바뀌는 자리수가 올라가기 전에 이전거 한번 더 해줘야한다.
		return array;
	}

	@Override
	public void solution() {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		W = new int[N][N];
		//모든 가능한 경우 대해 다 해본다.
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = i;// 순열용 배열.
			for(int j =0 ;j<N;j++) {
				W[i][j] = sc.nextInt();
			}
		}
		
		// 모든 자리수에 대해 돌아가면서 change를 가짐.
		change(array, 0);
		
		System.out.println(answer);
	}

}
