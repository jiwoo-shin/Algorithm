package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_1476 extends Solution {
	
	// 나머지로 만족하는 숫자 구하기 // (x-1)%15+1=E , (x-1)%28+1=S, (x-1)%19+1 = M인 숫자 구하기
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		char E = (char)sc.nextShort(); //char 하면. 
		char S = (char)sc.nextShort();
		char M = (char)sc.nextShort();
		// (x-1)%15+1=E , (x-1)%28+1=S, (x-1)%19+1 = M인 숫자 구하기
		//각각 15, 28, 19를 넘어가면 1로 초기화 되고 증가하다 다시 15, 28, 19번 더 증가하면 다시 1로 초기화됨. 나머지 연산과 유사한 그래프..
		//-> 범위는 1<= <=15, 28, 19 이므로 %15,28, 19 +1을 해야 범위가 같아짐. 다만 최대값을 갖는 숫자가 15*k, 28*k, 19*k 이므로 -1 한 후 나머지를구하고 거기에 +1을한것과 동일
		
		for(int i = S-1; i < Integer.MAX_VALUE; i+= 28) { //0*28+E 1*28+E...
			if(i%15 == E-1 && i%19 == M-1) { 
				System.out.println(i+1);//x-1이 i이므로 실제값 x는 i+1
				break;
			}
		}
	}

}
