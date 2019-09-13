package lang_java.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_2309 extends Solution {
	
	//9개중 택 7의 합이 100 -> 9개중 2 개 제외하면 합이 100
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		short [] height = new short[9]; //범위 작으니까.
		short sum = 0;
		for(int i = 0; i < 9; i++) {
			height[i] = sc.nextShort();
			sum+= height[i];
		}
		Arrays.sort(height);
		//9개중 2개를  택해서 합이 sum-100과 같으면 된다.
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(height[i] + height[j] == sum-100) {
					for(int k = 0; k < 9; k++) {
						if(k!=i && k!=j) {
							System.out.println(height[k]);
						}
					}
					i = 9; //바깥의 for문을 끝내기 위해
					break;
				}
			}
		}
		
	}

}
