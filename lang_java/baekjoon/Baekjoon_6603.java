package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_6603 extends Solution {
	
	static int[] S;
	static int LOTTO_LEN = 6;
	static void lotto(int[] numbers, int index, int length, int limit) { //numbers : 현재까지 생성된 로또 번호 배열, index: 추가될 숫자의 index, length : 현재까지 생성된 로또의 길이
		//if(length == LOTTO_LEN) { 만 하면 index 안넣는게 계속 돌아가서 난감..
		// length - (index+1) + length <= LOTTO_LEN // 출력 여부를 따지는거지. 이거는 앞으로 더 할 필요가 있는지여부
		// length== LOTTO_LEN && index >= limit -> 이면 else에서 length!= LOTTO_LEN || index < limit  이므로 length > LOTTO_LEN인 경우도 존재..(&&로 묶여야함..) length < index인데 index는 항상 6까지만 있는게 아니라 더 길수도 있거던. > else if(length < LOTTO_LEN && index < limit)
		// length== LOTTO_LEN && index <  limit -> index < limit 인 경우는 출력안해도됨.. length== LOTTO_LEN인 경우만 출력
		// length== LOTTO_LEN || index >= limit  -> 비교하면 OR이라서 index >= limit만 만족해도 출력함. 노노..
		if( length== LOTTO_LEN ) { //length : 현재까지 생성한 로또 길이, index : 현재 index,, 앞으로 최대 limit - (index+1) 개의 로또번호 생성 가능..   limit - (index+1) + length >= 6
			for(int i = 0; i < 6; i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return; //
		} else if(index < limit) {
			numbers[length] = S[index];
			// index : 현재 숫자 index, 
			// index 이후로 K-1 까지 넣거나 빼거나이기 때문에 최대 K-1 - inex개 가능, 현재까지 만든 길이는 length..
			lotto(numbers, index+1, length+1, limit); //index 를 포함하는 경우 .. 이거 먼저하면 역순으로 출력됨. 
			lotto(numbers, index+1, length, limit); //index 를 포함하지 않는 경우
		}
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		for(int K = sc.nextInt(); K > 6; K = sc.nextInt()) { //for(int K = sc.nextInt(); K > 6;)만해주면 K 한번만 입력받고 바뀌지않음.

			S = new int[K];
			for(int i = 0; i < K; i++) {
				S[i] = sc.nextInt(); //배열 S를 입력받음
			}
			lotto(new int[6], 0, 0, K);
			System.out.println();
			/*
			 * // 재귀함수 사용하지 않는 경우
			 * 
			int[] S = new int[K]; //K 길이 숫자 집합
			
			int[] index = {0, 1, 2, 3, 4, 5};// 배열 S에서 선택된 6개 번호의 인덱스 배열
			
			for(int i = 0; i < K; i++) {
				S[i] = sc.nextInt(); //배열 S를 입력받음
			}
			int increase_index = 5; // 증가되는 인덱스
			while(K-6 != index[0]) {
				// 값을 출력한다. 변경 전 값부터 출력해야하므로 increase_index 변경 전에 출력먼저해야함
				for(int i = 0; i < 6; i++) {
					System.out.print(S[index[i]]+" ");
				}
				System.out.println();
				//인덱스가 더이상 증가할 수 없는 경우 이전 인덱스를 증가시킴 (끝에서부터 증가 가능할 때 까지 이동)
				while(index[increase_index] >= K-(6-increase_index)) { // index[increase_index]의 최대값은  K-(6-increase_index). 이 이상은 증가 불가...(중복 가능하지 않으므로..)
					increase_index--; // 한칸 좌측 의 index 변경
				}
				index[increase_index]++;
				for(int j = increase_index+1; j <= 5; j++) {
					index[j] = index[j-1]+1; //변경된 index 이후의 index도 변경 필요
				//	 * 아래와 같이 실행됨
				//	if(index[5] < K-1)
				//		index[5]++;
				//	else {
				//		if(index[4]< K-2)
				//			{
				//				index[3]++;
				//			 		// .....
				//				index[4] = index[3]+1;
				//				index[5] = index[4]+1;
				//			}
				//		else {
				//			index[4]++;
				//			index[5] = index[4]+1;
				//		}
				//		}

				}
				
				increase_index = 5; // 변경할 인덱스는 다시 마지막 인덱스로 변경
				///
				// 1 2 3 4 5 6 7 8 9 10
				// 6, last: 7 8 9 10, 7 last: 8 9 10, 8 last: 9 10,9 last: 10 
				// 7, last: 8 9 10, 9 10, 10 
				/// 이런식으로 index 배열 없이 해도 될지,,
			}
			for(int i = 0; i < 6; i++) {
				System.out.print(S[index[i]]+" ");
			}
			System.out.println("\n");*/
		}
	}
}
