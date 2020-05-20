package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14891 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheel = new int[4][8];
		for(int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 8; j++) wheel[i][j] = tmp.charAt(j) - 48; //tmp.charAt(j)
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int index_wheel = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			wheel[index_wheel] = rotation(direction, wheel[index_wheel]); // 회전
			int prev_direction = direction; // 이전 톱니의 회전 방향
			for(int right = index_wheel + 1; right < 4; right++) {
				if(wheel[right][6] != wheel[right-1][2 + prev_direction]) { // right-1이 회전하여 위치가 바뀌었으므로 회전 전일때의 위치를 고려해야함.
					prev_direction = -prev_direction;
					wheel[right] = rotation(prev_direction, wheel[right]); // 회전
				} else break; // 톱니가 회전하지 않을 경우 그 우측의 톱니바퀴도 회전하지 않음
			}
			// 회전하는 톱니 중심으로 좌측의 톱니들에 대해 계산
			prev_direction = direction; // 이전 톱니의 회전 방향
			for(int left = index_wheel - 1; left >= 0; left--) {
				if(wheel[left][2] != wheel[left+1][6 + prev_direction]) {
					prev_direction = -prev_direction;
					wheel[left] = rotation(prev_direction, wheel[left]); // 회전
				} else break; // 톱니가 회전하지 않을 경우 그 좌측의 톱니바퀴도 회전하지 않음
			}
		}
		int sum = 0;
		int[] scores = {1, 2, 4, 8};
		for(int i = 0; i < 4; i++) {
			sum += scores[i]*wheel[i][0];
		}
		System.out.println(sum);
		
	}
	static int[] rotation(int direction, int[] wheel) {
		// 회전을 배열로 안하고 그냥 int 에 저장해서 shift 연산 사용해도 될듯.
		int start, move = -direction;
		if(direction == -1) start = 0; // 반시계 회전인 경우
		else start = 7;
		int tmp = wheel[start];
		int index = start;
		for(int k = 0; k < 7; k++) { // 무조건 7회
			//int next = (index + move + 8) % 8; %연산을 하지 않으려고 반시계 회전은 0부터, 시게회전은 7부터 해서 범위를 넘지 않도록 한것.이므로 %연산은 필요없음
			int next = index + move; // 반시계인 경우 0부터 +1씩, 시계인 경우 7부터 -1씩 되므로 0~7범위를 넘지 않는다.
			wheel[index] = wheel[next];
			index = next;
		}
		wheel[-start+7] = tmp; // start가 0이면 7, start가 7이면 0
		return wheel;
	}	
}
/*
 * package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14891 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheel = new int[4][8];
		int[] rotation_directions = new int[4];
		for(int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 8; j++) wheel[i][j] = tmp.charAt(j) - 48; //tmp.charAt(j)
		}
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int index_wheel = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			rotation_directions[index_wheel] = direction;
			// 회전하는 톱니 중심으로 우측의 톱니들에 대해 회전 방향을 구함
			for(int right = index_wheel + 1; right < 4; right++) {
				rotation_directions[right] = 0;
				if(wheel[right][6] != wheel[right-1][2]) rotation_directions[right] = -rotation_directions[right-1];
			}
			// 회전하는 톱니 중심으로 좌측의 톱니들에 대해 회전 방향을 구함
			for(int left = index_wheel - 1; left >= 0; left--) {
				rotation_directions[left] = 0;
				if(wheel[left][2] != wheel[left+1][6]) rotation_directions[left] = -rotation_directions[left+1];
			}

			for(int j = 0; j < 4; j++) {
				if(rotation_directions[j] != 0) {
					wheel[j] = rotation(rotation_directions[j], wheel[j]); // 회전
				}
			}
		}
		int sum = 0;
		int[] scores = {1, 2, 4, 8};
		for(int i = 0; i < 4; i++) {
			sum += scores[i]*wheel[i][0];
		}
		System.out.println(sum);

	}
	static int[] rotation(int direction, int[] wheel) {
		// 회전을 배열로 안하고 그냥 int 에 저장해서 shift 연산 사용해도 될듯.
		int start, move = -direction;
		if(direction == -1) start = 0; // 반시계 회전인 경우
		else start = 7;
		int tmp = wheel[start];
		int index = start;
		for(int k = 0; k < 7; k++) { // 무조건 7회
			//int next = (index + move + 8) % 8; %연산을 하지 않으려고 반시계 회전은 0부터, 시게회전은 7부터 해서 범위를 넘지 않도록 한것.이므로 %연산은 필요없음
			int next = index + move; // 반시계인 경우 0부터 +1씩, 시계인 경우 7부터 -1씩 되므로 0~7범위를 넘지 않는다.
			wheel[index] = wheel[next];
			index = next;
		}
		wheel[-start+7] = tmp; // start가 0이면 7, start가 7이면 0
		return wheel;
	}	
}*/
