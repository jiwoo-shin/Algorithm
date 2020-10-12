package baekjoon;

import java.util.Scanner;

import common.Solution;

public class Baekjoon_2884 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int H = sc.nextInt();
		int M = sc.nextInt();
		
		int num = 1 - (M / 45); //M이 45 이상인 경우 num은 0, 45보다 작은 경우 num은 1
		int hour;
		if(H < num) { //H = 0, num = 1인 경우
			hour = 23;// 24 - H - num;
		} else {
			hour = H - num;
		}
		
		System.out.println(hour+" "+(M+60*num-45)); // 아니면 그냥 통째로 분으로 바꾼 후 그걸 /60, %60 써서 시간으로 바꿀수도
	}
}
