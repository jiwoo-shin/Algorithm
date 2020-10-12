package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15684 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		boolean[][] ladder = new boolean[H][N-1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a-1][b-1] = true; // N : b, b+1 ¼±ÀÌ ¿¬°áµÊ
		}
		int answer = canGo(0, ladder, N, H, 0, -1);
		if(answer == 100) answer = -1;
		System.out.println(answer);
	}
	static int canGo(int depth, boolean[][] ladder, int N, int H, int now_i, int now_j) { 
		int answer = 100;
		if(depth <= 3 && check(ladder, N, H)) return depth; // Ãß°¡ÇÑ »ç´Ù¸®ÀÇ °³¼ö°¡ 3°³ ÀÌÇÏÀÌ¸é Á¦´ë·Î µµÂøÇÏ´ÂÁö ±¸ÇØº»´Ù.
		else if(depth >= 3) return 100; // 3°³¸¦ Ãß°¡ÇÏ¿´´Âµ¥ Á¦´ë·Î µµÂøÇÏÁö ¸øÇÏ´Â °æ¿ì
		for(int i = now_i; i < H; i++) { //now_h¿¡ ´ëÇØ ¸ğµç °¡·ÎÁÙ¿¡ ´ëÇØ °¡´ÉÇÏ¸é Ãß°¡ÇØº»´Ù. // ÀÌ·¯¸é ±íÀÌ ÇÏ³ª¿¡ ÇÏ³ª¸¸. °¡´ÉÇÔ.
			int start_j = i == now_i?now_j+1: 0;
			for(int j = start_j; j < N-1; j++) {
				if(!ladder[i][j] && ((j > 0 && !ladder[i][j-1]) || j == 0) && ((j < N-2 && !ladder[i][j+1]) || j == N-2)) { // ÇöÀç À§Ä¡¿¡ ¼±ÀÌ ¾ø°í ¿¬¼ÓµÇ´Â °¡·Î ¼±ÀÌ ¾ø´Â °æ¿ì
					ladder[i][j] = true;
					answer = Math.min(answer, canGo(depth+1, ladder, N, H, i, j));// i, j°¡ Æ÷ÇÔµÇ´Â °æ¿ì // °ªÀ» ±¸ÇÏ¸é ¹Ù·Î ¹İÈ¯ÇÏÁö ¾Ê°í 0, 1, 2, 3 ¸ğµÎ ±¸ÇÏ°Ô µÇ¹Ç·Î ½Ã°£ÀÌ ¿À·¡°É¸°´Ù.dfs·Î ÇØ¼­ ¿À·¡°É¸².
					ladder[i][j] = false;
					//answer = Math.min(answer, canGo(depth, check+1, false, ladder, N, H, i, j)); 
					// i, j°¡ Æ÷ÇÔµÇÁö ¾Ê´Â °æ¿ì´Â È£­‹ÇÒ ÇÊ¿ä°¡ ¾øÀ½. È£ÃâÀ» ¾ÈÇÏ¸é Æ÷ÇÔÀ» ¾ÈÇÏ±â ¶§¹®. Æ÷ÇÔµÇ´Â °æ¿ì, ¾ÈµÇ´Â °æ¿ì ¸ğµÎ È£ÃâÇÏ´Â ÄÚµå´Â ¸ğµç Á¤Á¡¿¡ ´ëÇØ Âü¿©, ºñÂü¿© µûÁ®¿Í¾ßÇÒ °æ¿ì. ÀÌ °æ¿ì´Â Âü¿©ÇÒ 3°³¸¸ ±¸ÇÏ¸é µÈ´Ù.
				}
			}
		}
		return answer;
	}
	static boolean check(boolean[][] ladder, int N, int H) { // ÇØ´ç »ç´Ù¸®¸¦ »ç¿ëÇÒ ¶§ i->i·Î °¡´ÂÁö
		for(int i = 0; i < N; i++) {
			int now = i;
			for(int j = 0; j < H; j++) {
				if(now < N-1 && ladder[j][now]) { //¿ìÃøÀ¸·Î ÀÌµ¿
					now++;
				} else if(now > 0&& ladder[j][now-1]) { //ÁÂÃøÀ¸·Î ÀÌµ¿
					now--;
				}
			}
			if(now != i) return false; 
		}
		return true;
	}
}
