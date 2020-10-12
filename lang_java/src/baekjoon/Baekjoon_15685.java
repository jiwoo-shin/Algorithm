package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15685 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
		boolean[][] map = new boolean[101][101]; //map[y][x] : 좌표 (x, y) 가 드래곤 커브에 포함되는지
		int[][] move = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 각 우, 상, 좌, 하 선분에 대해 x, y 가 어떻게 변하는지
		int[] curve_len_list = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024}; // g 세대 선분은 길이가 얼마나 되는지

		// 시뮬레이션이 사용할 n세대일 떄 드래곤 커브가 어떻게 이동하는지 저장함
		int[] dragon_curve = new int[1024]; // 0번 방향 시작인 경우에 대해 드래곤 커브를 이루는 선분들에 대해 저장 (우 : 0, 상: 1, 좌: 2, 하: 3)
		dragon_curve[0] = 0;
		int add_len = 1;
		for(int i = 1; i <= 10; i++, add_len*= 2) { //i 세대
			for(int j = 0; j < add_len; j++) { //i 세대 선분은 i-1 세대보다 add_len 만큼 많음
				dragon_curve[add_len + j] = (dragon_curve[add_len-1-j] + 1)%4; // 90도 회전 후 이어 붙이므로 생성되는 첫번째 : 이전에 존재하던 마지막선분의 회전으로 생성
			}
		}
		
		// 입력 및 커브 이동
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x =  Integer.parseInt(st.nextToken());
			int y =  Integer.parseInt(st.nextToken());
			int d =  Integer.parseInt(st.nextToken());
			int g =  Integer.parseInt(st.nextToken());
			int curve_len = curve_len_list[g]; 
			map[y][x] = true;
			
			for(int j = 0; j < curve_len; j++) { // 
				// 입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않으므로 추가 검사는 필요없음
				x = x + (move[(dragon_curve[j]+d)%4][1]); // 이전 x 좌표와 구해둔 드래곤 커브 선분 값을 통해 다음 점을 구함
				y = y + (move[(dragon_curve[j]+d)%4][0]);
				map[y][x] = true;
			}
		}
		
		// 네 모서리에 대해 검사
		int answer = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) answer++; // 네 꼭짓점이 모두 드래곤커브의 일부인 경우
			}
		}
		System.out.println(answer);
		
		
	}
}
