package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 이동 좌표 (하, 우, 상, 좌) 이동 시 검색 방향
	static int[] standard_start;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		standard_start = new int[]{N-1, N-1, 0, 0};
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10; //2^10
		int answer = getBlock(0, board);
		for(int i = 0; i < max; i++) {
			 answer = Math.max(answer, getBlock(i, board));
		}
		System.out.println(answer);
		
	}
	static int getBlock(int direction, int[][] board) { // direction : 5회 이동한 방향. 
		int answer = 0;
		int[][] tmp_board = new int[N][N];
		
		// 5회 이동한다.
		for(int k = 0; k < 5; k++) {
			int now = direction & 3; // 현재 방향 
			direction = direction >> 2;
			ArrayList<Integer> queue = new ArrayList<Integer>();
			int index_i = 0, index_j = 0; // 상하는 index_i 가 이동한다.

			for(int i = 0; i < N; i++) {
				queue.clear();
				int queue_index = 0;
				// 상하좌우 다름 // 상하좌우 다름 초기 인덱스 값
				index_i = standard_start[now] + i*move[now][1];
				index_j = standard_start[now] + i*move[now][0]; // 고정이 이동해야함
				
			
				// 한줄 합치고 공백 제거
				for(int j = 0; j < N ; j++) {
					if(k == 0)tmp_board[index_i][index_j] = board[index_i][index_j];
					if(tmp_board[index_i][index_j] != 0) {
						if(queue_index < queue.size() && tmp_board[index_i][index_j] == queue.get(queue_index)) {
							queue.remove(queue_index);
							queue.add(tmp_board[index_i][index_j] << 1); // 합친다.
							queue_index = queue.size(); // 이미 합쳐진 블록이므로 더이상 비교하면 안됨
						} else {
							queue.add(tmp_board[index_i][index_j]); // 들어간 현재 블록은 비교해봐야 하므로 queue_index를 더하면 안된다.
							queue_index = queue.size()-1; // 항상 새로 추가된 애랑 비교해야함
						}
					}
					index_i += move[now][0];
					index_j += move[now][1];
				}
				index_i = standard_start[now] + i*move[now][1];
				index_j = standard_start[now] + i*move[now][0]; 

				// board에 입력
				for(int j = 0; j < N ; j++) {
					if(queue.size() > 0) {
						tmp_board[index_i][index_j] = queue.get(0);
						answer = Math.max(tmp_board[index_i][index_j], answer);
						queue.remove(0);
					}
					else tmp_board[index_i][index_j] = 0;
					index_i += move[now][0];
					index_j += move[now][1];
				}
			}
		}
		return answer;
	}
}

/*
 * package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 이동 좌표 (하, 우, 상, 좌) 이동 시 검색 방향
	static int[] standard_start;
	static int[] standard_move = {-1, -1, 1, 1};
	static int answer = 0;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		standard_start = new int[]{N-1, N-1, 0, 0};
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, board[i][j]);
			}
		}
		int max = 1 << 10;
		board = updateBoard(0, board);
		System.out.println("하");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(2, board);
		System.out.println("상");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(1, board);
		System.out.println("우");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(3, board);
		System.out.println("좌");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < 4; i++) {
			//getBlock(i, board);
		}
		System.out.println(answer);
	}
	static void getBlock(int direction, int[][] board) { // direction : 5회 이동한 방향. 
		//int answer = 0;
		// 5회 이동한다.
		for(int i = 0; i < 1; i++) {
			int now = direction & 3; // 현재 방향 
			direction = direction >> 2;
			board = updateBoard(now, board);
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
	}
	static int[][] updateBoard(int now, int[][] board) { // now : 현재 움직이는 방ㅇ향
		
		int index_i = 0, index_j = 0; // 상하는 index_i 가 이동한다.
		for(int k = 0; k < N; k++) {
			
			// 상하좌우 다름 // 상하좌우 다름 초기 인덱스 값
			int tmp_i = standard_start[now] + k*move[now][1], tmp_j = standard_start[now] + k*move[now][0]; // 고정이 이동해야함
			System.out.println("start "+tmp_i+", "+tmp_j);
			
			int input_i = tmp_i, standard_i = tmp_i, standard_j = tmp_j, input_j = tmp_j;
			index_i = tmp_i;
			index_j  = tmp_j; 
			
			while(0 <= tmp_i && tmp_i <= N-1 && tmp_j <= N-1 && tmp_j >= 0 && board[tmp_i][tmp_j] == 0) {
				tmp_i += move[now][0];
				tmp_j += move[now][1];
			}
			// 1. 상단의 빈값을 제거한다.
			if((tmp_i != index_i || tmp_j !=index_j) && 0 <= tmp_i && tmp_i <= N-1 && 0 <= tmp_j && tmp_j <= N-1) {
				board[index_i][index_j] = board[tmp_i][tmp_j];
				board[tmp_i][tmp_j] = 0;
			}
			index_i = tmp_i + move[now][0];
			index_j = tmp_j + move[now][1];
			System.out.println("ONE LINE "+index_i+", "+index_j+"  tmp "+tmp_i+", "+tmp_j);
			
			//System.out.println(tmp);
			for(int l = 0; l < N ; l++) { // j는 현재 위치
				//if(i == 3)System.out.println(standard+" "+input+" "+j+" "+board[j][i]);
				if(index_i < 0 || index_i > N-1 || index_j < 0 || index_j > N-1 || standard_i < 0 || standard_i > N-1 || standard_j < 0 || standard_j > N-1) break;
				if(board[index_i][index_j] == 0) {
					index_i += move[now][0];
					index_j += move[now][1];
					continue;
				}
				System.out.println("VALUE = "+board[index_i][index_j]+ " | index "+index_i+", "+index_j+" | standard "+standard_i+", "+standard_j+" | input "+input_i+", "+input_j+" | tmp "+tmp_i+", "+tmp_j);
				
				// 1. standard와 j 가 같아서 합쳐질 수 있는 경우
				if(board[index_i][index_j] == board[standard_i][standard_j] && board[standard_i][standard_j] != 0) {
					//System.out.println("합친다!!! "+index_i+", "+index_j+"  standard "+standard_i+" , "+standard_j+" input "+input_i+", "+input_j+"  "+( board[index_i][index_j] << 1));
					board[input_i][input_j] = board[index_i][index_j] << 1; // standard와 j 를 합친다.
					board[index_i][index_j] = 0; // j 는 합쳐지느라 항상 이동됨. 빈칸으로 바꾼다.
					answer = Math.max(answer, board[input_i][input_j]);
					if(input_i + input_j < standard_move[now]*(standard_i + standard_j)) { // 입력 위치가 더 위에 있는 경우. standard도 위치를 이동한 경우 input이 더 작은 경우
						board[standard_i][standard_j] = 0;
					//	System.out.println("듀개다 0!!!");
					}
					// input 에 값이 더이상 갱신될 일이 없으므로 
					input_i += move[now][0];
					input_j += move[now][1];
					
					// j 는 합쳐졌으므로 더이상 합쳐질일이 없다.
					standard_i = index_i + move[now][0];
					standard_j = index_j + move[now][1];
				//	System.out.println("!!!합쳤다!!! "+board[input_i][input_j]);

					//System.out.println(standard_i+", "+standard_j+"   "+board[standard_i][standard_i]+" "+board[2][0]);
					while(0 <= standard_i && standard_i <= N-1 && standard_j <= N-1 && standard_j >= 0 && board[standard_i][standard_j] == 0) {
						//System.out.println(standard_i+", "+standard_j+"   "+board[standard_i][standard_i]+" "+board[2][0]);
						// standard는 0이 되지 않도록 한다.
						standard_i += move[now][0];
						standard_j += move[now][1];
					} 
					index_i = standard_i+ move[now][0];
					index_j = standard_j+ move[now][1];
					if(index_i < 0 || index_i > N-1 || index_j < 0 || index_j > N-1 || standard_i < 0 || standard_i > N-1 || standard_j < 0 || standard_j > N-1) break;
					
					System.out.println("중간점검 "+index_i+", "+index_j+" "+standard_i+", "+standard_j);
					while(0 <= index_i && index_i <= N-1 && index_j <= N-1 && standard_j >= 0 && board[index_i][index_j] == 0) {
						// 비교할 숫자 j 역시 빈칸이면 비교하지 않아도 된다.
						index_i += move[now][0];
						index_j += move[now][1];
					}
					// 맨 아래 +move 있으므로
					index_i -= move[now][0];
					index_j -= move[now][1];
					//System.out.println("!!!합쳤다!!! "+index_i+", "+index_j+"  standard "+standard_i+" , "+standard_j+" input "+input_i+", "+input_j+"  ");

					//System.out.println("!!!합쳤다!!! "+board[input_i][input_j]);
				} else { // 2. standard와 j 가 합쳐질 수 없는 경우 (이전 숫자(standard)와, 숫자가 있는(j) 현재값이 합쳐질 수 없는 경우)
					//System.out.println("못합친다!!! index "+index_i+", "+index_j+" |  standard "+standard_i+", "+standard_j+" | input "+input_i+", "+input_j);
					if(board[input_i][input_j] == 0) {// input이 빈 값인 경우. 
						board[input_i][input_j] = board[standard_i][standard_j]; // 빈 값을 제거하기 위해
						board[standard_i][standard_j] = 0;
						input_i += move[now][0];
						input_j += move[now][1];
					} else { // input !=0 인 경우 (standard == input)인 경우
						input_i += move[now][0];
						input_j += move[now][1];
					}
					standard_i = index_i;
					standard_j = index_j;
				}
				index_i += move[now][0];
				index_j += move[now][1];
			}
			System.out.println(index_i+", "+index_j+"   "+standard_i+", "+standard_j+" "+input_i+" "+input_j+"  "+(input_i != standard_i)+"  "+( input_j != standard_j));
			if(0 <= standard_i && 0 <= standard_j && standard_i < N && standard_j < N && board[standard_i][standard_j] != 0 ) {
				board[input_i][input_j] = board[standard_i][standard_j];
				if(input_i != standard_i || input_j != standard_j)board[standard_i][standard_j] = 0;
				System.out.println("LAST "+board[input_i][input_j]+" "+board[standard_i][standard_j]);
			}

			System.out.println();
		}
		return board;
	}
}
 */
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 이동 좌표 (하, 우, 상, 좌)
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10;
		board = updateBoard(3, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < max; i++) {
			
		}
	}
	int getBlock(int direction, int[][] board) { // direction : 5회 이동한 방향. 
		int answer = 0;
		// 5회 이동한다.
		for(int i = 0; i < 5; i++) {
			int now = direction & 3; // 현재 방향 
			direction = direction >> 2;
			
		}
		return answer;
	}
	static int[][] updateBoard(int now, int[][] board) { // now : 현재 움직이는 방ㅇ향
		
		for(int i = 0; i < N; i++) {
			// index_0 : 합쳐지는 위치
			//int index_0 = 0, index_j;
			//int stanard_index = 0; //비교할 숫자의 위치 (j와 합쳐질 수 있는 숫자의 인덳스)
			int standard = 0;
			while(standard <= N-1 && board[standard][i] == 0)standard++;
			int input = standard;
			
			// 빈값을 제거함
			if(standard > 0) {
				board[0][i]=board[standard][i];
				board[standard][i] = 0;
				standard = 0;
				input = 0;
			}
			for(int j = standard+1; j < N; j++) { // j는 현재 위치
				// input (갱신할 위치 > 중간 빈칸때문에 standard와 다를 수 있다.)
				// standard (합쳐질지j 와 비교할 위치)
				// j 현재 
				// standard
				// standard == j 인 경우 standard와 j를 합친다. 
					// >> 한번 이동에 여러번 합칠 수 없으므로 standard는 j+1로 바꾼다. j위치는 합쳐졌으므로 0 빈칸으로 만든다. input = standard+1로 바꿔준다.
				// 합쳐지지 않는 경우 	
					// j == 0인 경우 j++ (다음과 standard 비교)
					// j != 0인 경우 standard는 합칠 수 없다는 것. standard = j로 갱신한다.
				
				//if(board[j][i] == 0) continue;  
				if(now == 3) { // 위
					
					if(board[standard][i] == board[j][i] && board[j][i] != 0) { // 합쳐지는 경우 (빈칸이면 안됨)
						board[input][i] = (board[j][i] << 1); 
						if(standard > input) { // 합쳐지는 위치가 비교할 숫자 2개보다 모두 위에 있는 경우 둘다 0으로 바꿔야함
							board[standard][i] = 0; // 
						}
						board[j][i] = 0; // 빈칸으로 만든다.
						input = standard+1; // 갱신된 값 바로 다음이 추가되어야하는 값 (연속이든 아니든 숫자 하나 0으로 바뀌고 합쳐지는 것이므로)
						standard = j+1; // 1회 이동에 연속으로 합할 수 없으므로 
						j++; // 다음 j 는 검사할필요가 없음 standard가 되었으므로
						
					} else if(board[j][i] != 0){ // 합쳐지지 않는 경우 
						 // standard가 j와 합쳐질 수 없는 경우 
						if(board[input][i] == 0)board[input][i] = board[standard][i]; // j를 이동시킨다. (중간 빈칸 제거)
						else {
							board[++input][i] = board[standard][i]; // 공백이 아닌 경우,, input에 값 입력이 불가능할 경우 
							board[standard][i] = 0; // 이동하 빈칸으로 만든다.
						}
						standard = j; // standard가 더이상 합쳐질 수 없는 것이므로 이제 새로운 기준값인 j 와 비교한다. j+1....// input 에 j값이 들어갔음.
					}
				}
				if(j == N-1 || standard == N-1) {
					 // 마지막 값인 경우
					board[input][i]=board[standard][i];
					if(input!=standard)board[standard][i] = 0;
				}
			}
		}
		
		return board;
	}
}*/
/*
for(int i = 0; i < N; i++) {
int standard = start[now];
while(0<= standard && standard <= N-1 && board[standard][i] == 0)standard += standard_move[now];
int input = standard;
for(int j = standard+1; j < N; j++) { // j는 현재 위치
	// input (갱신할 위치 > 중간 빈칸때문에 standard와 다를 수 있다.)
	// standard (합쳐질지j 와 비교할 위치)
	// j 현재 
	// standard
	// standard == j 인 경우 standard와 j를 합친다. 
		// >> 한번 이동에 여러번 합칠 수 없으므로 standard는 j+1로 바꾼다. j위치는 합쳐졌으므로 0 빈칸으로 만든다. input = standard+1로 바꿔준다.
	// 합쳐지지 않는 경우 	
		// j == 0인 경우 j++ (다음과 standard 비교)
		// j != 0인 경우 standard는 합칠 수 없다는 것. standard = j로 갱신한다.
	
	if(now == 3) { // 위
		if(board[standard][i] == board[j][i] && board[j][i] != 0) { // 합쳐지는 경우 (빈칸이면 안됨)
			board[input][i] = (board[j][i] << 1); 
			if(standard > input) { // 합쳐지는 위치가 비교할 숫자 2개보다 모두 위에 있는 경우 둘다 0으로 바꿔야함
				board[standard][i] = 0; // 
			}
			board[j][i] = 0; // 빈칸으로 만든다.
			input = standard+1; // 갱신된 값 바로 다음이 추가되어야하는 값 (연속이든 아니든 숫자 하나 0으로 바뀌고 합쳐지는 것이므로)
			standard = j+1; // 1회 이동에 연속으로 합할 수 없으므로 
			j++; // 다음 j 는 검사할필요가 없음 standard가 되었으므로
			
		} else if(board[j][i] != 0){ // 합쳐지지 않는 경우 & 현재 탐색한 위치가 빈칸이 아닌 경우
			 // standard가 j와 합쳐질 수 없는 경우 
			if(board[input][i] == 0)board[input][i] = board[j][i]; // j를 이동시킨다. (중간 빈칸 제거)
			else board[++input][i] = board[j][i]; // 공백이 아닌 경우,, input에 값 입력이 불가능할 경우 
			board[j][i] = 0; // 이동하였으므로 빈칸으로 만든다.
			standard = input; // standard가 더이상 합쳐질 수 없는 것이므로 이제 새로운 기준값인 j 와 비교한다. j+1....
		}
	}
	 // 마지막 값인 경우
	board[input][i]=board[standard][i];
	board[standard][i] = 0;
}
}
*/
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_12100 extends Solution {
	
	static int N;
	static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 이동 좌표 (하, 우, 상, 좌)
	static int[] start = {N-1, N-1, 0, 0};
	static int[] standard_move = {-1, -1, 1, 1};
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 1 << 10;
		board = updateBoard(3, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(1, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(2, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		board = updateBoard(0, board);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j]+"  ");
			}
			System.out.println();
		}
		for(int i = 0; i < max; i++) {
			
		}
	}
	int getBlock(int direction, int[][] board) { // direction : 5회 이동한 방향. 
		int answer = 0;
		// 5회 이동한다.
		for(int i = 0; i < 5; i++) {
			int now = direction & 3; // 현재 방향 
			direction = direction >> 2;
			
		}
		return answer;
	}
	static int[][] updateBoard(int now, int[][] board) { // now : 현재 움직이는 방ㅇ향
		
		for(int i = 0; i < N; i++) {
			int standard = start[now];
			while(0<= standard && standard <= N-1 && board[standard][i] == 0)standard += standard_move[now];
			int input = standard;
			for(int j = standard + standard_move[now]; j < N && j >= 0; j+= standard_move[now]) { // j는 현재 위치
				// input (갱신할 위치 > 중간 빈칸때문에 standard와 다를 수 있다.)
				// standard (합쳐질지j 와 비교할 위치)
				// j 현재 
				// standard
				// standard == j 인 경우 standard와 j를 합친다. 
					// >> 한번 이동에 여러번 합칠 수 없으므로 standard는 j+1로 바꾼다. j위치는 합쳐졌으므로 0 빈칸으로 만든다. input = standard+1로 바꿔준다.
				// 합쳐지지 않는 경우 	
					// j == 0인 경우 j++ (다음과 standard 비교)
					// j != 0인 경우 standard는 합칠 수 없다는 것. standard = j로 갱신한다.
				
				if(now == 3 || now == 0) { // 위
					System.out.println(standard+" "+i+" "+j+" "+now);
					if(board[standard][i] == board[j][i] && board[j][i] != 0) { // 합쳐지는 경우 (빈칸이면 안됨)
						board[input][i] = (board[j][i] << 1); 
						if(standard > input*standard_move[now]) { // 합쳐지는 위치가 비교할 숫자 2개보다 모두 위에 있는 경우 둘다 0으로 바꿔야함
							board[standard][i] = 0; // 
						}
						board[j][i] = 0; // 빈칸으로 만든다.
						input = standard+1; // 갱신된 값 바로 다음이 추가되어야하는 값 (연속이든 아니든 숫자 하나 0으로 바뀌고 합쳐지는 것이므로)
						standard = j+1; // 1회 이동에 연속으로 합할 수 없으므로 
						j++; // 다음 j 는 검사할필요가 없음 standard가 되었으므로
						
					} else if(board[j][i] != 0){ // 합쳐지지 않는 경우 & 현재 탐색한 위치가 빈칸이 아닌 경우
						 // standard가 j와 합쳐질 수 없는 경우 
						if(board[input][i] == 0)board[input][i] = board[j][i]; // j를 이동시킨다. (중간 빈칸 제거)
						else board[++input][i] = board[j][i]; // 공백이 아닌 경우,, input에 값 입력이 불가능할 경우 
						board[j][i] = 0; // 이동하였으므로 빈칸으로 만든다.
						standard = input; // standard가 더이상 합쳐질 수 없는 것이므로 이제 새로운 기준값인 j 와 비교한다. j+1....
					}
				} else {
					if(board[i][standard] == board[i][j] && board[i][j] != 0) { // 합쳐지는 경우 (빈칸이면 안됨)
						board[i][input] = (board[i][j] << 1); 
						if(standard > input*standard_move[now]) { // 합쳐지는 위치가 비교할 숫자 2개보다 모두 위에 있는 경우 둘다 0으로 바꿔야함
							board[i][standard] = 0; // 
						}
						board[i][j] = 0; // 빈칸으로 만든다.
						input = standard+1; // 갱신된 값 바로 다음이 추가되어야하는 값 (연속이든 아니든 숫자 하나 0으로 바뀌고 합쳐지는 것이므로)
						standard = j+1; // 1회 이동에 연속으로 합할 수 없으므로 
						j++; // 다음 j 는 검사할필요가 없음 standard가 되었으므로
						
					} else if(board[i][j] != 0){ // 합쳐지지 않는 경우 & 현재 탐색한 위치가 빈칸이 아닌 경우
						 // standard가 j와 합쳐질 수 없는 경우 
						if(board[i][input] == 0)board[i][input] = board[i][j]; // j를 이동시킨다. (중간 빈칸 제거)
						else board[i][++input] = board[i][j]; // 공백이 아닌 경우,, input에 값 입력이 불가능할 경우 
						board[i][j] = 0; // 이동하였으므로 빈칸으로 만든다.
						standard = input; // standard가 더이상 합쳐질 수 없는 것이므로 이제 새로운 기준값인 j 와 비교한다. j+1....
					}
				}
			}
			System.out.println(standard+" "+i+"  "+now);
			 // 마지막 값인 경우
			if(now == 3 || now == 0) {
				board[input][i]=board[standard][i];
				board[standard][i] = 0;
			} else {
				board[i][input]=board[i][standard];
				board[i][standard] = 0;
			}
		}
		
		return board;
	}
}*/