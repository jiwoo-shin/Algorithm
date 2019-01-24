package backjoon;

import java.util.Scanner;

public class Backjoon_1002 extends Solution{

	/*요약 : 두 원의 중심좌표와 반지름이 주어질 때 접점의 개수를 구하세요*/
	
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
			
			if(distance == subR || distance == sumR)Answer = 1;//1인 경우(내접 or 외접)//---(a)
			else if(distance > subR && (distance < sumR) )Answer = 2;//2인 경우(만남)
			else  Answer = 0;//0인 경우 (안만남)
			
			if(distance == 0 && (a[2] == b[2]))Answer = -1;//-1인 경우(같은 원)--(a)에서 조건이 걸릴 수 있으므로 얘는 else 주지 말고 밖으로 뺴던가.. (a)에서 distance가 0이 아닐 경우 조건 추가하던가..
			
			
			System.out.println(Answer);
		}
		
	}

}
