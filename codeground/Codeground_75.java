package codeground;

import java.util.Scanner;

public class Codeground_75 extends Solution{
	static String Answer;
	/* 요약 : 회문인 숫자들의 합으로 표현, 단 회문인 숫자가 최소가 되도록
	 * 
	 *  %10...을 /10..에 맞추면 회문
	 *  본인보다 작은 숫자 중 최대 회문을 구한 후 뺀다. 뺀 숫자도 마찬가지로 회문을 구한다.
	 * 0~9 : 무조건 회문
	 * 2자리 숫자 : 11, 22, ... > 회문이 11 차이 > 회문+0~9 = 회문 2개로 표현 가능, 회문+10 : 회문+회문으로 표현 불가.. 
	 *    2자리 숫자 : 회문 2개의 합으로 만들 수 있음 
	 *    
	 * 3자리 숫자 : 111, 121 ...202 > 회문이 10, 11 차이 > 회문 + 0~9:회문 2개로 표현 가능, 
	 *  회문+11 :백의자리=일의자리+1, 십의자리 = 0 인경우.. 이 경우는 3자리수 회문2개로 표현 가능하거나/ 안되거나 >  3자리수 회문 2개로 표현 가능한 경우 : 일의자리 숫자가 2개의 숫자의 합으로 표현 가능한 경우 -> 201제외하고 다 됨...>aba+cdc일때, a+c가 일의자리숫자이고 b+d가 10을 만족하는 아무 숫자.. 
	 *    a+c = 일의자리숫자 +1은 안되는게 이러면 백의자리에서도 반올림이 된다.
	 *    3자리 숫자 : 201 제외하고 회문 2개의 합으로 만들 수 있음 
	 *    
	 * 4자리 숫자 : 1001, 1111, 1221, ... 9999 > 회문이 110, 111 차이 > 회문 + 0~111로 표현 가능 
	 *  0~111 : 회문 2개로 표현 가능.. 4자리숫자 : 회문 3개로 표현 가능. 
	 *  abba+cddc = efgh 일 경우 efgh가 회문이 아닐 때(회문이면 바로 회문 1개로 표현한 거..) e= h+1, f= g+1..  > 4자리 숫자일 때 천의자리=일의자리+1, 백의자리=십의자리+1일 경우 회문2개로 표현가능
	 * */

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = "";
			int n = sc.nextInt();
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
		
	}
}
