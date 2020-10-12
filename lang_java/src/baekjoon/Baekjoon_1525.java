package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1525 extends Solution {
	
	static int[] numbers = new int[11];
	// static boolean[][] visited = new boolean[9][9]; // visited[i][j] 숫자 i 가 j 위치에 방문하였는지 여부 -> 이러면 012 345 -> 102 305 -> 142 305 다음 142 305 도 방문한 것으로 된다.
	// static boolean[] used = new boolean[87654322]; // 0~8을 중복없이 한번씩만 사용하므로 앞에 7자리만 같으면 뒤에 한자리도 같음.. key/10으로 판단해도 될듯..
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				int index = 3*i + j;
				numbers[index] = Integer.parseInt(st.nextToken());
				if(numbers[index] == 0) numbers[9] = index;
			}
		}
		numbers[10] = 0;
		System.out.println(bfs());
		
	}
	static int key(int[] array) {
		int key = 0;
		int ten = 1;
		for(int i = 0; i < 9; i++, ten*=10) {
			key += array[8-i]*ten;
		}
		return key;
	}
	static int bfs() {
		// 방향 상하좌우로 이동한다.
		Queue<int[]> queue = new LinkedList<int[]>();
		HashSet<Integer> visited = new HashSet<>();
		queue.add(numbers);
		visited.add(key(numbers));
		int depth = numbers[10];
		int[] move = {-1, 1, 3, -3};
		while(!queue.isEmpty()) {
			int[] present = queue.poll();
			int pos_0 = present[9];
			depth = present[10];
			// print(present);
			if(isAnswer(present)) return depth;
			for(int i = 0; i < 4; i++) { // 좌, 우, 하, 상으로 이동
				int next = pos_0 + move[i];
				int[] tmp = present.clone();
				
				if(isOk(pos_0, next, move[i])) { // next%3 == 0
					tmp[pos_0] = tmp[next];
					tmp[next] = 0;
					int key = key(tmp);
					if(visited.add(key)) { // 방문하지 않은 경우
						tmp[9] = next;
						tmp[10] = depth + 1;
						queue.offer(tmp);
					}
				}
			}
		}
		return -1;
	}
	static boolean isOk(int pos_0, int next, int direction) {
		if(direction == 1 ||direction == -1) {
			return direction*next%3 > direction*pos_0%3 && next >= 0 && next < 9;
		} else {
			return next < 9 && next >= 0;
		}
	}
	static boolean isAnswer(int[] array) {
		for(int i = 0; i < 9; i++) {
			if(array[i] != (i+1)%9) return false;
		}
		return true;
	}
}
