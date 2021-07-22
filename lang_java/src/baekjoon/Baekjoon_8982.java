package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_8982 extends Solution {
	
	static int[] move = {1, -1};
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int maxWater = 0; // 수조에 받을 수 있는 물의 부피
		int answer = 0;
		int numOfHorizon = (N-2)/2; // 수평선이 몇개 존재하는지 : 가장 처음과 마지막을 제외하고 나머지 꼭짓점들로 만들어진다 : N-2. 수평선은 2개의 꼭짓점으로 생성되므로 /2
		int[][] horizonList = new int[numOfHorizon][5]; // 수평선 y 위치, x 시작점, x 끝점을 저장
		int[] searchArray = new int[40001]; //
		
		st = new StringTokenizer(br.readLine()); // 처음 꼭짓점 (0, 0) : 수평선과 무관하므로 horizonList에 저장하지 않는다.

		for(int i = 0; i < numOfHorizon; i++) {
			st = new StringTokenizer(br.readLine()); // 수평선의 시작점을 입력받은
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine()); // 수평선의 끝점을 입력받음
			int endX = Integer.parseInt(st.nextToken());
			
			horizonList[i] = new int[] {startY, startX, endX, 0, endX-startX};;
			maxWater += horizonList[i][0]*horizonList[i][4];
			searchArray[horizonList[i][1]] = i; // 라인의 시작 x는 겹치지 않으므로
		}
		
		
		st = new StringTokenizer(br.readLine()); // 마지막 꼭짓점 (k, 0) : 수평선과 무관하므로 horizonList에 저장하지 않는다.
		answer = maxWater;
		
		
		int K = Integer.parseInt(br.readLine()); //구멍의 숫자
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int indexHorizon = searchArray[startX];

			horizonList[indexHorizon][3] = horizonList[indexHorizon][0];
			for(int direction = 0; direction < 2; direction++) { // 좌우로 이동하며 검색한다.
				for(int searchIndex = indexHorizon + move[direction]; searchIndex >=0 && searchIndex < numOfHorizon; searchIndex += move[direction]) {
					horizonList[searchIndex][3] = 
							Math.max(
									Math.min(horizonList[searchIndex][0], horizonList[searchIndex-move[direction]][3]), // 좌우로 이동할때는 높은 수평선 기준으로 ..
									horizonList[searchIndex][3]); // 기존에 저장되어 있던 값은 뺄 수 있는 물의 양.. 물은 빠질 수 있는 만큼 최대로 다 빠져야한다.
				}
			}
		}
		for(int i = 0; i < numOfHorizon; i++) answer -= horizonList[i][3]*horizonList[i][4];
		
		System.out.println(answer);
	}
	
}