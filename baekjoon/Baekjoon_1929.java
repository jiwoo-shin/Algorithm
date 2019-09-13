package baekjoon;

import java.util.Scanner;

public class Baekjoon_1929 extends Solution {

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		//N 미만의 소수 아닌 숫자를 제외 (-> N 미만에서의 배수들을 다 지운다.)
		int [] numbers = new int[N-1]; // 0, 1모두 제외(숫자 두개 제외)
		int limit = (int)Math.floor(Math.sqrt(N)); // 루트 n 이하의 배수들을 제외시키면 루트 n 초과일 경우도 함께 자동으로 지워짐
		int real_num;//실제 숫자 index보다 2 크다(index=0,숫자=2로 치환)
		for(int i = 0; i < N-1; i++) { 
			if(numbers[i] == 0) {//초기값일 경우(제외되지 않은 숫자인 경우) 해당 숫자의 배수를 배열에서 지운다.
				real_num = i+2; //실제 숫자. (초기값과 증감 및 출력에 사용됨)
				if(real_num >= M)System.out.println(real_num); //M 보다 클 경우에만 출력
				if(real_num <= limit) { //루트 n 이하의 배수들을 제외시키면 루트 n 초과일 경우도 함께 자동으로 지워지므로 루트 n 이하일 경우만 배수를 지운다.
					// j 초기값이 i*i 아님. i+2의 제곱도 아님. i+2(실제 숫자)의 제곱이 필요한데 이 경우는 실제숫자 제곱의 -2를 해야함.(배열 0, 1을 제외하였으니까)
					for(int j = (real_num)*(real_num)-2; j < N-1; j+=(real_num)) { //그냥 i*i는 0일 경우 무한 루프가 돈다.. 유의...
						numbers[j] = -1; //배수는 배열에서 지움
					}
				}
			}
		}
		
	}

}
