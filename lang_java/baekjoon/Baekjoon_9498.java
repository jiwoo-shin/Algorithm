package lang_java.baekjoon;

import java.util.Scanner;

public class Baekjoon_9498 extends Solution {
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		char[] grades = {'F', 'D', 'C', 'B', 'A'};
		
		int score = sc.nextInt(); //점수
		
		// if문 안쓰고 풀어보자.
		// 1. ((score/10)/5)* -> score/10이 5 보다 작은 경우 0이 된다. ****7) 만족
		// 2. ((score/10)/5)/2 -> (score/10)/5가 2보다 작은 경우 0이됨. 2인 경우는 1이 된다.
		//		score/10 - ( 5 + 3*(((score/10)/5)/2)) -> (score/10)/5 가 2보다 작은 경우 5를 뺸다.****2), 3), 4), 5), 6) 만족
		//												  (score/10)/5 가 2인경우 8을 뺸다. ****1) 만족
		int index = ((score/10)/5)*(score/10 - ( 5 + 3*(((score/10)/5)/2)));
		
		//socre/10 에 따라 계산되어야하는 index 값들
		//10 -> 4 ****1)
		// 9  -> 4 ****2)
		// 8 -> 3 ****3)
		// 7 -> 2 ****4)
		// 6 -> 1 ****5)
		// 5 -> 0 ****6)
		// 5 미만 -> 0 ****7)
		
		System.out.print(grades[index]);
	}
}
