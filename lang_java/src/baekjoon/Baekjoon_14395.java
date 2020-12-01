package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14395 extends Solution {
	static String[] modify_str = {"*", "+"};
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
		System.out.println(get(s, t));
		
	}
	static String get(int s, int t) {
		Queue<long[]> queue = new LinkedList<long[]>(); // 숫자, move, visited위치
		int max = (int) Math.ceil(Math.log10(t) / Math.log10(2)); // 2^a*s^b 숫자 와 2^k 숫자를 만들 수 있으므로. a,b<= log2(t)
		String[][] visited = new String[max+1][max+1];
		if(s > t) { // 무조건 작게 만든 후 적용 필요
			///if(t == 0) return "-"; // t는 1 이상임
			if(t == 1) return "/";
			else {
				queue.add(new long[] {1, 0, 0});
				visited[0][0] = "/";
			}
		} else if(s == t) {
			return "0";
		} else { 
			queue.add(new long[] {s, 0, 1});
			visited[0][1] = "";
			// 여기서 하면안된다. 순서 떄문에 다르게 나올 수 있음
			//queue.add(new long[] {1, 0, 0});
			//visited[0][0] = "/";
		}
		boolean add = false;
		while(!queue.isEmpty()) {
			long[] now = queue.remove();
			// 빼기 : 항상 0, 나누기 : 항상 1이므로 while 전에 0과 1이 필요한 경우는 미리 추가하여서 while에서는 굴리지 않음
			for(int i = 0; i < 2; i++) { // 곱하기와 더하기를 한다
				long[] next;
				if(i == 0) { // 곱하기인 경우
					next = new long[]{now[0]*now[0],  now[1]+now[1], now[2]+now[2]};
				} else {//더하기인 경우
					next = new long[]{now[0]+now[0],  now[1]+1, now[2]};
				}
				if(next[0] == t) {
					return visited[(int) now[1]][(int) now[2]]+modify_str[i];
				}
				if(next[1] <= max && next[2] <= max && visited[(int) next[1]][(int) next[2]] == null && next[0] < t) { // 방문하지 않은 경우 && t 보다 작은 경우. 큰 경우는 줄이는 경우의 수가 /밖에 없음
					visited[(int) next[1]][(int) next[2]] = visited[(int) now[1]][(int) now[2]]+modify_str[i];
					queue.add(next);
				}
			}
			if(!add) {
				add = true;
				queue.add(new long[] {1, 0, 0});
				visited[0][0] = "/";
			}
		}
		return "-1";
	}
}
