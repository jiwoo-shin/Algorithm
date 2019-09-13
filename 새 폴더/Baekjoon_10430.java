package backjoon;

import java.util.Scanner;

public class Backjoon_10430 extends Solution {

	// 첫째 줄에 (A+B)%C, 둘째 줄에 (A%C + B%C)%C, 셋째 줄에 (A×B)%C, 넷째 줄에 (A%C × B%C)%C를 출력
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int a1 = (A+B)%C;
		int a2 = (A%C + B%C)%C;
		int a3 = (A*B)%C;
		int a4 = (A%C * B%C)%C;
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
	}

}
