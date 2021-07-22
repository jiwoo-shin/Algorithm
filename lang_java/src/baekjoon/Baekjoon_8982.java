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
		int maxWater = 0; // ������ ���� �� �ִ� ���� ����
		int answer = 0;
		int numOfHorizon = (N-2)/2; // ������ � �����ϴ��� : ���� ó���� �������� �����ϰ� ������ ��������� ��������� : N-2. ������ 2���� ���������� �����ǹǷ� /2
		int[][] horizonList = new int[numOfHorizon][5]; // ���� y ��ġ, x ������, x ������ ����
		int[] searchArray = new int[40001]; //
		
		st = new StringTokenizer(br.readLine()); // ó�� ������ (0, 0) : ���򼱰� �����ϹǷ� horizonList�� �������� �ʴ´�.

		for(int i = 0; i < numOfHorizon; i++) {
			st = new StringTokenizer(br.readLine()); // ������ �������� �Է¹���
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine()); // ������ ������ �Է¹���
			int endX = Integer.parseInt(st.nextToken());
			
			horizonList[i] = new int[] {startY, startX, endX, 0, endX-startX};;
			maxWater += horizonList[i][0]*horizonList[i][4];
			searchArray[horizonList[i][1]] = i; // ������ ���� x�� ��ġ�� �����Ƿ�
		}
		
		
		st = new StringTokenizer(br.readLine()); // ������ ������ (k, 0) : ���򼱰� �����ϹǷ� horizonList�� �������� �ʴ´�.
		answer = maxWater;
		
		
		int K = Integer.parseInt(br.readLine()); //������ ����
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int indexHorizon = searchArray[startX];

			horizonList[indexHorizon][3] = horizonList[indexHorizon][0];
			for(int direction = 0; direction < 2; direction++) { // �¿�� �̵��ϸ� �˻��Ѵ�.
				for(int searchIndex = indexHorizon + move[direction]; searchIndex >=0 && searchIndex < numOfHorizon; searchIndex += move[direction]) {
					horizonList[searchIndex][3] = 
							Math.max(
									Math.min(horizonList[searchIndex][0], horizonList[searchIndex-move[direction]][3]), // �¿�� �̵��Ҷ��� ���� ���� �������� ..
									horizonList[searchIndex][3]); // ������ ����Ǿ� �ִ� ���� �� �� �ִ� ���� ��.. ���� ���� �� �ִ� ��ŭ �ִ�� �� �������Ѵ�.
				}
			}
		}
		for(int i = 0; i < numOfHorizon; i++) answer -= horizonList[i][3]*horizonList[i][4];
		
		System.out.println(answer);
	}
	
}