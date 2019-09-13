package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_10972 extends Solution {

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
			if(array[i-1] < array[i]) { //i-1 번째 자리수가 바뀌어야함
				change_index = i-1;
				change = array[change_index]; // ---1
				break;
			}
		}
		if(change_index == -1) { // 내림차순으로 정렬된 경우
			System.out.println(-1);
		} else {
			
			//여기서 따로 for 안돌리고 ---1에서 바꾸고 여기는 change_index+1 이후 정렬만 해도 될듯.
			for(int i = N-1; i >= change_index+1; i-- ) { //change_index와 어디가 바뀌어야하 하는지 확인. change_index 우측에 있는 숫자와 바뀜, change_index 좌측의 숫자도 바뀌면 더 높은 자리의 숫자도 바뀌는 것ㅇ이므로 사전 순 바로 다음이 아님.
				// N-1이 오른쪽이고 -1 될수록 왼쪽으로 가는거.. 
				
				//i를 change_index+1부터 N-1 까지 +1 해가면서 
				// if(array[i] < change) 이걸로 비교해서 i-1을 바꾸는 기준점으로 삼으면 N-1>=i>=change_index+1 이므로
				// N-2 = change_index일 경우  N-1>=i>=N-1이 되어서 array[N-1] > array[N-2] 가능하지만
				// 기준이 모두 오름차순 정렬되었을 경우 (N-2 = change_index 인 경우 ) 바뀌는 기준점은 i이고 (더 큰 수보다 작은 수가 없는 경우)
				// (N-2 != change_index 인 경우 ) 바뀌는 기준점은 i-1임 (더 큰 수보다 하나 작은 숫자가 있는 경우)
				
				
				//array[change_index] 우측은 내림차순 정렬이므로 index가 클수록 숫자가 크다. 
				//-> array[i]가 change 보다 크면 array[i]이 change 보다 큰 가장 작은 수 (array[N-1] 부터 비교해쓸 깨)
				if(array[i] > change) { 
					array[change_index] = array[i]; // array[i] = change; 먼저하면 안됨
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
