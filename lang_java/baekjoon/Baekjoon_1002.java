package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_1002 extends Solution {

	/*
	 * 요약 : 두 원의 중심좌표와 반지름이 주어질 때 접점의 개수를 구하세요 >>좌표와 좌표로 부터 거리가 일정한 점들 : 원이 된다.
	 * 
	 * */
	
	@Override
	public void solution() {
		int Answer = 0;
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[] a = new int[3];//좌표와 위치
		int[] b = new int[3];//좌표와 위치
		
		for(int j = 0; j < T;j++) {
			for(int i = 0; i < 3; i++) {
				a[i] = sc.nextInt();
			}
			for(int i = 0; i < 3; i++) {
				b[i] = sc.nextInt();
			}
			
			int sumR =  a[2] + b[2];
			int subR = Math.abs(a[2]-b[2]);
			double distance = Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
			
			if(distance == subR || distance == sumR) {
				if(distance == 0 && (a[2] == b[2]))Answer = -1;//-1인 경우(같은 원)
				else Answer = 1;//외접 or 내접
				}
			else if(distance > subR && (distance < sumR) )Answer = 2;//2인 경우(만남)
			else  Answer = 0;//0인 경우 (안만남)
			
			
			System.out.println(Answer);
		}
		
	}

}
