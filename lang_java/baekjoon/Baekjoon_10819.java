package lang_java.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baekjoon_10819 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		// 가장 큰 수, 작은 수를 연결하고 그 이후로 차이가 최대가 될 수 있는 값을 붙인다.
		// 큰수, 작은 수 징검다리로 있어야 차이가 최대로 벌어짐
		int N = sc.nextInt();
		ArrayList<Integer> array = new ArrayList<Integer>();		

		int answer = 0;
		
		for(int i = 0; i<N; i++) {
			array.add(sc.nextInt());
		}
		
		Collections.sort(array); //정렬
		
		int left_index = 0;
		int right_index = N-1;

		//가장 작은수 - 가장 큰 수를 연결 
		answer += array.get(right_index) - array.get(left_index); //가장 작은값과 가장 큰 값을 연결
		
		int last = 0; // 순열의 길이가 홀수인 경우 가장 중앙에 있는 숫자를 좌측에 붙일지, 우측에 붙일지 계산 필요 (좌, 우 중에서 차이가 큰 곳을 선택) 
		
		left_index = 1;
		// 큰값, 작은값을 연결하고 그 이후로 차이가 최대가 될 수 있도록 값을 붙임
		// 가장 큰 수 우측에 붙는수열 (가장 큰 수 - 두번째로 작은 수 - 세번째로 큰 수  - 네번째로 작은 수 - 다섯번째로 큰 수... ) 이런식으로
		// 두번째로 작은 수 다음 두번째로 큰 수가 아닌 이유는 두 번째로 큰 수는 가장 작은 수 왼쪽에 붙어야함.
		for(int i = 0; i<N/2-1; i++) {
			answer += array.get(right_index) - array.get(left_index);
			left_index += i%2 * 2; //i가 홀수일 경우 left_index를 2 증가
			right_index -= (i+1)%2 * 2; //i가 짝수인 경우 right_index 2감소
		}
		
		if(N%2 != 0) //N이 홀수인 경우 순열 가장 우측에 있는 숫자와 순열 중앙에 있는 숫자의 차를 구함
			last = Math.abs(array.get(left_index *((N/2-1 )%2) + right_index*((N/2 )%2)) - array.get(N/2)); // 순열의 가장 우측에 올 숫자와 중앙의 숫자의 차를 구한다.
			// (N/2-1)이 홀수인 경우 left_index(작은 숫자)가 가장 우측에 오고 (N/2-1)이 짝수인 경우 right_index(큰 숫자)가 가장 우측에 옴.
			// (N/2-1)이 홀수인 경우 마지막으로 right_index가 변하므로 left_index는 -2를 다시해줄 필요는 없음. 
		
		left_index = 0;
		right_index = N-2;
		// 가장 작은 수 좌측에 붙는 수얼 (...-두번째로 큰 수-가장 작은 수) 이런식
		for(int i =0; i<N/2-1; i++) {
			answer += array.get(right_index) - array.get(left_index);
			left_index += (i +1)%2 * 2; //i가 짝수인 경우 left_index 증가
			right_index -= i%2 * 2; //i가 홀수인 경우 right_index 증가
		}
		
		if(N%2 != 0) { //N이 홀수인 경우 순열 가장 좌측에 있는 숫자와 순열 중앙에 있는 숫자의 차를 구함
			//숫열 가장 중앙 숫자가 좌측에 붙는 경우와 우측에붙는 경우 중 차이가 더 큰 경우를 선택함
			answer += last > Math.abs(array.get(right_index *((N/2-1)%2) + left_index*((N/2)%2) ) - array.get(N/2)) //
					? last : Math.abs(array.get(right_index *((N/2-1)%2) + left_index*((N/2)%2)) - array.get(N/2));
			}
		System.out.println(answer);
	
	}
}
