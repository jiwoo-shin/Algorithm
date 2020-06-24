package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15683 extends Solution {
	
	static int[][] map;
	static int N, M;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] cctv;
	static int[] cctv_direction = {4, 2, 4, 4, 1};
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new int[8][2];
		int cctv_number = 0;
		int wall_num = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 6) {
					wall_num++;
				} else if(map[i][j] > 0) {
					cctv[cctv_number++] = new int[] {i, j};
				} 
			}
		}
		//int answer = N*M - watch(cctv_number) - cctv_number - wall_num;
		//int answer = N*M - dfs(0, new int[cctv_number], cctv_number) - cctv_number - wall_num;
		int answer = N*M - dfs(0, cctv_number, 0) - cctv_number - wall_num;
		System.out.println(answer);
		
	}
	static int dfs(int depth, int cctv_number, int sum) {
		int answer = 0;
		if(depth == cctv_number) {
			return sum;
		}
		int cctv_type = map[cctv[depth][0]][cctv[depth][1]];
		for(int i = 0; i < cctv_direction[cctv_type-1]; i++) {
			int now_sum = getWatch(i, cctv_type, cctv_number, cctv[depth], 10); // 현재 cctv가 방향 i를 바라볼 경우 감시할 수 있는 숫자
			
			answer = Math.max(answer, dfs(depth+1, cctv_number, sum + now_sum));
			getWatch(i, cctv_type, cctv_number, cctv[depth], -10); // visited 배열을 다시 원상태로 복구
		}
		
		return answer;
	}
	static int getWatch(int direction, int cctv_type, int cctv_number, int[] now, int flag) {
		int answer = 0;
		int one_set = cctv_type > 2 ? cctv_type-1: cctv_type; // 한번에 감시할 수 있는 방향
		int change_direction = cctv_type == 2?2: 1;
		int check_num = -(flag-10)/2; //flag가 10인 경우 0, flag가 -10인 경우 10 이상인 경우
		for(int k = 0; k < one_set; k++) {
			int[] next = new int[] {now[0] + move[direction][0], now[1] + move[direction][1]};
			while(isOk(next[0], next[1], N, M)) { // 가능할 때 까지 쭉 ..
				if(map[next[0]][next[1]] < 1 || map[next[0]][next[1]] > 6) {
					if(map[next[0]][next[1]] == 0)answer++;
					map[next[0]][next[1]] += flag;
				}
				if(map[next[0]][next[1]] == check_num) { // 0인 경우로 해서 flag까지 넣으면 다시 빠지지를 않고/ map을 빼면 cctv도 바꿔버림
				}
				next[0] += move[direction][0];
				next[1] += move[direction][1];
			}
			direction = (direction + change_direction)%4;
		}
		return answer;
	}
	static int dfs(int depth, int[] directions, int cctv_number) {
		int answer = 0;
		if(depth == cctv_number) {
			return getWatch(directions, cctv_number); // 132 , 123 같은 1이라도 여러번 구하게 된다. -> 마지막에 방향들을 보고 모든 방향에 대해 구해보므로
		}
		int cctv_type = map[cctv[depth][0]][cctv[depth][1]];
		for(int i = 0; i < cctv_direction[cctv_type-1]; i++) {
			directions[depth] = i;
			answer = Math.max(answer, dfs(depth+1, directions, cctv_number));
		}
		
		return answer;
	}
	static int getWatch(int[] directions, int cctv_number) {
		int answer = 0;
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < cctv_number; i++) {
			int direction = directions[i];
			int cctv_type = map[cctv[i][0]][cctv[i][1]];
			int one_set = cctv_type > 2 ? cctv_type-1: cctv_type; // 한번에 cctv 몇개가..
			for(int k = 0; k < one_set; k++) {
				if(cctv_type == 2) direction += 2;
				else direction++;
				direction %= 4;
				int[] next = new int[] {cctv[i][0] + move[direction][0], cctv[i][1] + move[direction][1]};
				while(isOk(next[0], next[1], N, M)) {
					if(map[next[0]][next[1]] == 0 && !visited[next[0]][next[1]])answer++;
					visited[next[0]][next[1]] = true;
					next[0] += move[direction][0];
					next[1] += move[direction][1];
				}
			}
		}
		return answer;
	}
	static int watch(int cctv_number) {
		int answer = 0;
		int[][] watch_number = new int[cctv_number][4]; // 상우하좌 일직선 방향에 대해
		for(int i = 0; i < cctv_number; i++) { // 각 cctv에 대해 상, 하, 좌, 우 쭉 가는 경우 몇개 검사할 수 있는지 저장.. 이러면 중복해서 감시되는 부분이 발생한다.
			for(int direction = 0; direction < 4; direction++) {
				int watch = 0;
				int[] next = new int[] {cctv[i][0] + move[direction][0], cctv[i][1] + move[direction][1]};
				while(isOk(next[0], next[1], N, M)) {
					if(map[next[0]][next[1]] == 0)watch++;
					next[0] += move[direction][0];
					next[1] += move[direction][1];
				}
				watch_number[i][direction] = watch;
			}
			answer += getMaxWatch(watch_number[i], map[cctv[i][0]][cctv[i][1]]);
			
		}
		return answer;
	}
	static int getMaxWatch(int[] watch_number, int cctv_type) {
		int max = 0;
		if(cctv_type == 2) {
			max = Math.max(watch_number[0]+watch_number[1], watch_number[2]+watch_number[3]);
		} else if(cctv_type == 5) {
			max = watch_number[0]+watch_number[1]+watch_number[2]+watch_number[3];
		} else {
			int tmp = cctv_type - cctv_type/3; // cctv_type이 1이 아닌 경우 1을 뺀다.
			for(int i = 0; i < 4; i++) { // 네 방향 회전 모두 해봐야함
				int sum = 0;
				for(int t = 0; t < tmp; t++) { // 각 방향을 바라볼 때 cctv가 감시하는 숫자를 구한다.
					sum = watch_number[t];
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}
	static boolean isOk(int i, int j, int N, int M) { 
		return i >=0 && j>= 0 && i < N && j < M && map[i][j] != 6; // 벽인 경우가 아니여야 함
	}
	
}
