package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13549 extends Solution {

	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int[] time = new int[100001];
		if(N != K)queue.add(N); // N==K라면 while문 안돌고 0 출력함.
		time[N] = 1; // 방문하지 않음 : 0과 차이를주기 위해
		while(!queue.isEmpty()) {
			//현재 index 방문
			int index = queue.get(0);
			queue.remove(0);
			if(index == K) break;// 목적지에 도착한 경우 더이상 탐색할필요가 없음
			
			if(index <= K) { // index > K인 경우 2*index > 2*K => 감소는 -1씩만 가능하므로  2*K에서 K로 가려면  K번 추가 이동 필요.. 그럴 바에는 index > K이므로 index에서-1씩 이동함.
				// index > K라면 *2나 +1로 더커질필요 없이 그냥 게속 빼는게 최상의결과임
				
				// 순간이동 하는 경우
				if(index*2 <= 100000 && time[index*2] == 0) {// 방문한 적이 없는 경우 (time[index*2] == 0) && 방문할 정점이 범위 내에있는경우 (index*2 <= 100000)-> index*2 < 100000 아님, 10만까지 가능하므로 정답이 10만일수도 있음
					time[index*2] = time[index];
					queue.add(0, 2*index); 
					}
				// 걸어가는 경우 (전진)
				if(index+1 <= 100000 && time[index+1] == 0) { // 방문한 적이 없는 경우 (time[index+1] == 0) && 방문할 정점이 범위 내에있는경우 (index+1 <= 100000)-> index+1 < 100000 아님, 10만까지 가능하므로 정답이 10만일수도 있음
					queue.add(index+1); 
					time[index+1] = time[index]+1;
				}
			}

			// 걸어가는 경우 (후진)
			// 후진하는 경우는 index > K, inedx <= K인 경우 둘다해봐야함. index가 0보다 크기만하면.
			if(index > 0 && time[index-1] == 0) {
				time[index-1] = time[index]+1;
				queue.add(index-1); // 단 +로 이동하는 경우는+1만있는것이아니라 *2도 있으므로 -1로 후진 후 순간이동 하는 경우 최상의 결과가 나올 수 있음.
			}
		}
		
		// 1부터 시작했으므로
		System.out.println(time[K]-1);
	}
	
}
