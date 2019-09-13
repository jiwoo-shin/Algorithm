package backjoon;

import java.util.Scanner;

public class Backjoon_2609 extends Solution {
	
	//두 개의 자연수를 입력받아 최소 공배수를 출력
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int tmp;
		int originX = x;
		int originY = y;
		while(y!=0) {
			tmp = y;
			y = x % y;
			x = tmp;
		}
		int GCD = x;
		int LSD = (originX * originY)/GCD;
		
		System.out.println(LSD);
		
	}

}
