package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_6588 extends Solution {

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		/* // 소수인 배열 생성
		int max_n = 1000000;
		boolean [] numbers = new boolean[max_n-1]; 
		int [] primes = new int[max_n-1];//numbers에서 처음부터 빠졌는지 비교하면서 소수 찾는 것 보다 n 미만의 i 번째 소수 한번에 찾으려 배열 따로 만들었는데 생각보다 작은 소수 찾는 데 얼마 안걸려서. 
		int limit = (int)Math.floor(Math.sqrt(max_n)); 
		int real_num;
		int prime_num = 0;
		for(int i = 0; i < limit; i++) { //소수 전부 보는게 아니라 빼기만 하면 되므로 limit 까지만 하면 됨. 
			if(!numbers[i]) {
				real_num = i+2; 
				primes[++prime_num]=real_num; 
                //i < limit이므로 real_num은 항상 n보다 작음.
				for(int j = (real_num)*(real_num)-2; j < max_n-1; j+=(real_num)) { 
						numbers[j] = true; //배수는 배열에서 지움
				}
			}
		}
		
		int n;
		do {
			n = sc.nextInt();
			//primes[i] : i번째 소수
			for(int i = 0; primes[i] < n; i++) {
				if(!numbers[n-primes[i]-2]) {//n-소수도 소수일 경우
					System.out.println(n+" = "+primes[i]+" + "+(n-primes[i]));
					break;
				}
			}
		}while(n>0);*/
		int max_n = 1000000;
		boolean [] numbers = new boolean[max_n-1];  
		//int limit = (int)Math.floor(Math.sqrt(max_n)); 
		int real_num;
		for(int i = 0; i < 1000; i++) { //소수 전부 보는게 아니라 빼기만 하면 되므로 limit 까지만 하면 됨. 
			if(!numbers[i]) {
				real_num = i+2; 
                //i < limit이므로 real_num은 항상 n보다 작음.
				for(int j = (real_num)*(real_num)-2; j < max_n-1; j+=(real_num)) { 
						numbers[j] = true; //배수는 배열에서 지움
				}
			}
		}
		
		int n;
		do {
			n = sc.nextInt();
			//primes[i] : i+2가 소수인지 여부
			for(int i = 0; i < n; i++) { //n/2까지만해도 되는데 (합이라서.) 어차피 중간에 나오므로 .
				//[n] 은 n+2가 소수인지 여부 
				//이 for문은 0부터 시작이므로 여기서의 i도 현실 숫자보다 2 작음.
				// i 는 실제숫자 i+2
				//실제숫자 n-(i+2) 가 소수인지 판별 -> [n-(i+2)-2]
				if(!numbers[n-i-4] && !numbers[i]) {//n-소수도 소수일 경우
					System.out.println(n+" = "+(i+2)+" + "+(n-i-2)); //실제숫자 출력
					break;
				}
			}
		}while(n>0);
		
	}

}
