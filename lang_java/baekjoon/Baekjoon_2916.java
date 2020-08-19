package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2916 extends Solution {
	
	@Override
	public void solution() throws IOException {
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, K;
		int[] angles, goals;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		angles = new int[N];
		goals = new int[K];
		boolean[] ableAngles = new boolean[360];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			angles[i] = Integer.parseInt(st.nextToken());
			ableAngles[angles[i]] = true;
			getAbleAngle(angles[i], ableAngles, getNumber(angles[i]));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			goals[i] = Integer.parseInt(st.nextToken());
			if(isAble(goals[i], ableAngles)) answer.append("YES");
			else answer.append("NO");
			answer.append('\n');
		}
		System.out.print(answer);
	}
	static boolean isAble(int angle, boolean[] ableAngles) {
		int mid = (angle+1)/2;
		int mod = angle%2;
		for(int i = 0; i < mid; i++) 
			if(ableAngles[i] && ableAngles[angle - i]) return true;
		if(mod == 0 && ableAngles[mid]) return true;
		return false;
	}
	static int getNumber(int angle) { // angle 각도에 대해 몇번 사용가능한지 (360과 angle과 최소공배수를 구한 후 angle로 나놈)
		if(angle != 0) {
			int x = angle;
			int y = 360;
			while(y != 0) {
				int r = x%y;
				x = y;
				y = r;
			}
			return 360/x;//(360*angle/x)/angle; // 
		} else return 0;
	}
	static boolean[] getAbleAngle(int angle, boolean[] ableAngles, int ableNumber) { // 만들 수 있는 각도를 구함
		int tmp = angle;
		for(int i = 1; i < ableNumber; i++) {
			tmp = (tmp+angle)%360;
			ableAngles[tmp] = true;
		}
		return ableAngles;
	}
	
}
