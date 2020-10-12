package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_13460 extends Solution {
	static class Point {
		int red_x;
		int red_y;
		int blue_x;
		int blue_y;
		int prev_direction;
		public void set(int[] red, int[] blue, int direction) {
			this.red_x = red[0];
			this.red_y = red[1];
			this.blue_x = blue[0];
			this.blue_y = blue[1];
			this.prev_direction = direction;
		}
		@Override
	    public boolean equals(Object obj) {
	    	Point point = (Point)obj; 
	    	if(point.red_x == red_x && point.red_y == red_y && point.blue_x == blue_x && point.blue_y == blue_y) return true; 
	    	else return false;
	    }
	}
	
	static char[][] map;
	static int N, M;
	static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int answer = 10;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		Point start = new Point();
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'R') {
					start.red_x = i;
					start.red_y = j;
				} else if(map[i][j] == 'B') {
					start.blue_x = i;
					start.blue_y = j;
				} 
			}
		}
		start.prev_direction = -1;
		// DFS
		// int answer = move(0, red, blue, -1);
		//System.out.println(answer==11? -1:answer);
		
		// BFS
		System.out.println(bfs(start));
	} 
	static int bfs(Point start) {
		int answer = -1;
		ArrayList<Point> marble = new ArrayList<Point>();
		int depth = 0;
		marble.add(start);
		
		while(marble.size() > 0 && depth < 10 && answer == -1) {
			int depth_size = marble.size();
			for(int k = 0; k < depth_size && answer == -1; k++) {// 현재 depth 모두 
				//System.out.println(depth_size);
				Point now = (Point)marble.get(0); // 현재 값은 동일합니다. 현재에 대해 상하좌우 검색
				marble.remove(0); // 현재값 제거
				for(int i = 0; i < 4; i++) { // now 에 관해 상하좌우 검색
					Point next = new Point();
					if(now.prev_direction == i) continue;
					boolean goalR = false, goalB = false;
					next.set(next(new int[] {now.red_x, now.red_y}, direction[i]), next(new int[] {now.blue_x, now.blue_y}, direction[i]), i);
			
					if(map[next.red_x][next.red_y] == 'O') goalR = true; // R이 골 에 들어간 경우
					if(map[next.blue_x][next.blue_y] == 'O') goalB = true; // R이 골 에 들어간 경우
					if(goalR && !goalB) { // 정답을 구한 경우
						//System.out.println("answer!!!!! depth ="+depth+"  now = (R)"+now.red_x+"  "+now.red_y+"  | (B)"+now.blue_x+", "+now.blue_y+"     next = (R)"+next.red_x+" "+next.red_y+ "  |  (B)"+next.blue_x+", "+next.blue_y+"   "+(!(goalR && goalB) || (!goalR & goalB)) );
						
						answer = depth+1;
						break;
					} else if(next.red_x == next.blue_x && next.red_y == next.blue_y && !goalR) { // 이전과 다음이 같은 경우
						next = correction(now, next);
					} 
					//System.out.println("depth ="+depth+" move = "+i+" |  "+"  now = (R)"+now.red_x+"  "+now.red_y+"  | (B)"+now.blue_x+", "+now.blue_y+"     next = (R)"+next.red_x+" "+next.red_y+ "  |  (B)"+next.blue_x+", "+next.blue_y+"   "+(!(goalR && goalB) || (!goalR & goalB)) );
					
					// 
					if(isCallNext(now, next, goalR, goalB)) {
						marble.add(next);
					}
				}
			}
			depth++;
		}
		return answer;
	}
	static Point correction(Point now, Point next) {
		Point tmp = next;
		// 이동 전의 위치에 따라 R과 B 중 어느것을 한칸 덜 이동시켜야하는지 판단
		if(now.red_y*direction[tmp.prev_direction][1] + now.red_x*direction[tmp.prev_direction][0] < now.blue_y*direction[tmp.prev_direction][1] + now.blue_x*direction[tmp.prev_direction][0]) {
			tmp.red_x -= direction[tmp.prev_direction][0]; 
			tmp.red_y -= direction[tmp.prev_direction][1];
		} else {
			tmp.blue_x -= direction[tmp.prev_direction][0]; 
			tmp.blue_y -= direction[tmp.prev_direction][1];
		}
		return tmp;
	}
	static boolean isCallNext(Point now, Point next, boolean goalR, boolean goalB) {
		if(!(now.equals(next))) {
			if((!(goalR && goalB) || (!goalR & goalB))) return true;
		}
		return false;
	}
	static int[] next(int[] present, int[] move) { // 다음 좌표를 구함
		int[] next = { present[0] , present[1]};
		for(;next[0] >= 0 && next[1] >= 0 && next[0] < N && next[1] < M ;next[0] += move[0], next[1] += move[1]) {
			if(map[next[0]][next[1]] == '#') { // # 인 경우는 바로 이전 위치만 가능
				next[0] -= move[0];
				next[1] -= move[1];
				break;
			}
			if(map[next[0]][next[1]] == 'O') {
				break;
			}
		}
		return next;
	}
	
	// DFS (구할수는 있으나 최적의 해를 구하는 데에는 BFS가 더 좋다..)
	static int move(int depth, int[] red, int[] blue, int prev) {
		int answer = 11;
		if(depth == 11){ // 이동이 불가능한 경우.. 10회 이하이므로 11회 이동한 경우는 불가
			return 11;
		} if(map[red[0]][red[1]] == 'O'){
			return depth;
		} else { // 이동이 가능한 경우
			int[] next_red, next_blue;
			
			// 상하좌우로 이동
			for(int i = 0; i < 4; i++) {
				if(i == prev) continue; // 이전에 왔던 길이면 방문하지 않음
				boolean goalR = false; 
				boolean goalB = false; 
				
				next_red = next(red, direction[i]); // 이동한 R의 위치를 구함
				next_blue = next(blue, direction[i]);// 이동한 B의 위치를 구함
				if(map[next_red[0]][next_red[1]] == 'O') goalR = true; // R이 골 에 들어간 경우
				if(map[next_blue[0]][next_blue[1]] == 'O') goalB = true; // R이 골 에 들어간 경우
				
				// R과 B는 같은 위치에 있을 수 없으므로 같은 위치인 경우 (O 인 경우 둘다 골에 빠지므로 같은 위치 가능)
				if(next_red[0] == next_blue[0] && next_red[1] == next_blue[1] && !goalR) {
					// 이동 전의 위치에 따라 R과 B 중 어느것을 한칸 덜 이동시켜야하는지 판단
					if(red[1]*direction[i][1] + red[0]*direction[i][0] < blue[1]*direction[i][1] + blue[0]*direction[i][0]) {
						next_red[0] -= direction[i][0];
						next_red[1] -= direction[i][1];
					} else {
						next_blue[0] -= direction[i][0];
						next_blue[1] -= direction[i][1];
					}
				}

				// R, B 둘다 들어간 경우가 아니거나 B 먼저 들어간 경우가 아닌 경우 호출
				if((!(goalR && goalB) || (!goalR & goalB))&& answer > depth+1)answer = Math.min(answer, move(depth+1, next_red, next_blue,i));
			}
		}
		return answer;
	}
	
}