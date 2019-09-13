package lang_java.codeground;

import java.util.Scanner;

public class Codeground_75 extends Solution{
	static String Answer;
	/* 
	 * 요약 : 회문인 숫자들의 합으로 표현, 단 회문인 숫자가 최소가 되도록
	 * 
	 * 입력값이 10000 이하이므로 자릿수 별로 나누어 확인
	 * 
	 * 참고) 숫자 = 회문1 + not회문1
	 * 			회문1 = 회문2 + not회문2
	 * 		숫자 = 회문2 + not회문1+not회문2
	 * 					not회문1+not회문2->회문이 되는지 확인 필요 ----(2)
	 * 
	 * 1자리수(1~9) : 무조건 회문
	 * 2자리수(10~99) : 회문 = 이전회문 + 11 >> 이므로 모든 숫자 : 회문 + 0~10 ---(1)
	 *									 (회문+n(n>11) = (회문 +11->회문)+(n-11))이므로 --(1)
	 *				회문 + 0~9 : 숫자가 2개의 회문의 합으로 표현 가능
	 *				회문 + 10 : a a-1 = b b + n >> n = a-b a-b-1 이므로 n은 회문이 될 수 없음  >> 회문+10일 경우에만 회문의합 3개로 표현 가능
	 *				>>> 숫자 : 숫자보다 작은 최대 회문 + 숫자1 >>>숫자 : 숫자보다 작은 최대 회문 + 숫자1보다 작은 최대 회문 + 회문(0~9)
	 *				
	 * 3자리수(100~999) : 회문  = 이전회문 + 10 or 회문  = 이전회문 + 11 >>> 이므로 숫자 : 회문+0~10
	 * 					회문 + 0~9 : 숫자가 2개의 회문의 합으로 표현 가능
	 * 					회문 + 10 : 제한된 경우의 수... 201, 302, 403... > n 0 n-1의 꼴을 가짐
	 * 								n+1 0 n이 2개의 회문으로 표현하려면 a k a + h n-a (단, k+h=10인 어떠한 수열도 가능하지만 h n-a가 회문이여야 하므로 h = n-a)
	 * 									n+1 0 n = a 10-n+a a + n-a n-a (n>a)>>> n=1(201)인 경우를 제외하고는 2개의 회문의 합으로 표현 가능
	 * 					>>> 201 제외하고 2개의 회문의 합으로 표현가능
	 * 
	 * 4자리수(1000~9999) : 회문 = 이전회문 + 110 or 회문 = 이전회문 + 2 >>> 이므로 숫자 : 회문 + 0~109
	 * 					  (2) 에 의해 회문이 회문이 아닌과 회문의 합으로 표현가능한지 확인 필요
	 * 
	 * 						110*n + 2*m (n<=9, n<=1)은 회문이 아님 ----(3)
	 * 
	 * 						110*9 + 2 = 1001(회문)> 일반적으로 9번 후 2가 빠짐,, 다음 회문으로..
	 * 							1001 + 110*n도 회문 1001 + 1001*n도 회문
	 * 
	 * 						회문-1001(4자리 수 중 최소 회문) = 회문, 단 1001이전부터는 110 씩 차이가 아니므로 10/11/2(1001과 99) 씩 차이가 남. 회문 없음.. ---(4)
	 * 
	 * 						(3) or (4)를 따지면 된다.
	 * 						
	 * 							
	 * 
	 * 5자리수(10000) : 9999 + 1>> 2개 회문으로 표현 가능
	 *  %10...을 /10..에 맞추면 회문
	 *  본인보다 작은 숫자 중 최대 회문을 구한 후 뺀다. 뺀 숫자도 마찬가지로 회문을 구한다.
	 * 0~9 : 무조건 회문
	 * 2자리 숫자 : 11, 22, ... > 회문이 11 차이 > 회문+0~9 = 회문 2개로 표현 가능, 회문+10 : 회문+회문으로 표현 불가.. 
	 *    2자리 숫자 : 회문 2개의 합으로 만들 수 있음 
	 *    
	 * 3자리 숫자 : 111, 121 ...202 > 회문이 10, 11 차이 > 회문 + 0~9:회문 2개로 표현 가능, 
	 *  회문+11 :백의자리=일의자리+1, 십의자리 = 0 인경우.. 이 경우는 3자리수 회문2개로 표현 가능하거나/ 안되거나 >  3자리수 회문 2개로 표현 가능한 경우 : 일의자리 숫자가 2개의 숫자의 합으로 표현 가능한 경우 -> 201제외하고 다 됨...>aba+cdc일때, a+c가 일의자리숫자이고 b+d가 10을 만족하는 아무 숫자.. 
	 *    a+c = 일의자리숫자 +1은 안되는게 이러면 백의자리에서도 반올림이 된다.
	 *    3자리 숫자 : 201 제외하고 회문 2개의 합으로 만들 수 있음 
	 *    
	 * 4자리 숫자 : 1001, 1111, 1221, ... 9999 > 회문이 110, 111 차이 > 회문 + 0~111로 표현 가능 
	 *  0~111 : 회문 2개로 표현 가능.. 4자리숫자 : 회문 3개로 표현 가능. 
	 *  abba+cddc = efgh 일 경우 efgh가 회문이 아닐 때(회문이면 바로 회문 1개로 표현한 거..) e= h+1, f= g+1..  > 4자리 숫자일 때 천의자리=일의자리+1, 백의자리=십의자리+1일 경우 회문2개로 표현가능
	 * */

	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		//int T = sc.nextInt();
		for(int test_case = 0; test_case < 5000; test_case++) {
			Answer = "";
			int n = test_case+1;//sc.nextInt();
			if(n <= 10) {
				Answer = "1 "+n+"";
				if(n == 10) Answer = "2 9 1";
			}else if(n <= 100) {
				/*
				int digit_ten = n/10;
				int digit_one = n%10;
				if(digit_ten <= digit_one) {
					Answer = (digit_ten * 11) +" "+(digit_one - digit_ten);
				} else {
					Answer = (digit_ten - 1)*11 +" "+(digit_one+10 - (digit_ten-1));
				}
				if(n == 100) {Answer = "99 1";}*/
				if(n == 100) Answer = "2 99 1";
				else if(n % 11 == 10)Answer = 3+" "+(n/11)*11 +" 9 1";
				else {
					if(n%11 == 0) Answer = 1+" "+n;
					else Answer = 2+" "+(n/11)*11 +" "+(n%11);
					}
			}else if(n <= 1000) {
				int digit_hun = n / 100;//백의자리
				int digit_ten = (n / 10)%10;//십의 자리
				int digit_one = n % 10;//일의자리
				if(n==1000) {
					Answer = "2 999 1";
				} else if(n == 201) {
					Answer = "3 191 9 1";
				} else if ((digit_hun == digit_one + 1) && digit_ten == 0){
					Answer = 2+" "+(n - 111) + " 111";//Answer = 2+" "+(n - 101) + " 101";
				} else {
					int near_circle;
					if(digit_hun == digit_one) {
						near_circle = n;
					} else if(digit_hun > digit_one) {
						if(digit_ten == 0) {
							near_circle = (digit_hun-1)*100 + 90 + digit_hun-1;
						} else {
							 near_circle = (digit_hun)*100 + (digit_ten - 1)*10 + digit_hun;
						}
					} else {
						near_circle = digit_hun*100 + digit_ten*10 + digit_hun;
					}
					if((n - near_circle)==0)Answer = 1+" "+near_circle;
					else Answer = 2+" "+near_circle + " " +(n - near_circle);
				}
			}else {
				Answer = "0";
				int left = n /100;
				int right = n % 100;
				int left_reverse = left%10*10 + left/10;
				int near_circle;
				if(left_reverse > right) { //1990 = 1881 + 109
					near_circle = (left-1)*100 + ((left-1)%10*10 + (left-1)/10);
				}else { //1999
					near_circle = left*100 + left_reverse;
				}
				
				if((left/10 == right%10) && (right/10 == left%10)) Answer = 1+" "+n;
				else {
					int B = n - near_circle;
					int m = (near_circle/100)%10;
					if(B<10) {
						Answer = 2+" "+near_circle+" "+B;
					}else if(B<100) {
						if(B%11==10)Answer = "0";//불가능한 경우도 있음. 1001 + 32 -> 1001 22 10
						else Answer = 3+" "+near_circle+" "+(B/11)*11+" "+(B%11);// 기본
						
						
						if(m-B%10>0)  {//1013..조건 여기. 다시 생각.
							if(B%10 + B/10 > 10) {
								if(B%10 + B/10!=10) {
									if(B%10 -1 <= m)Answer = 2+" "+(near_circle-(B%10-1)*110)+" "+(B+(B%10-1)*110);
									if(B%10==1)Answer = 2+" "+(near_circle-110)+" "+(B+110);
								}
								
							}  else if(m+B/10<10) {
								if(B%10 + B/10!=10) if(B%10<=m)Answer =2+" "+(near_circle-(B%10)*110)+" "+(B+(B%10)*110);
							}
							//11들어가는 경우는 m은 최대값ㄷ으로 고정이 아니고 더 클수도 있음 ..n은 0이구나.
							if(B%10-B%10>1) { //그 11로 뺐을 때 1001보다 커야함
								if(B%10+B/10>9) {
									if(B%10 + B/10!=10) if(B%10>=m)Answer = 2+" "+(near_circle-(B%10)*110-11)+" "+(B+(B%10)*110+11);
								}else if(B%10+B/10<9) {
									if(B%10 + B/10!=10) if(B%10+1>=m)Answer = 2+" "+(near_circle-((B%10+1)*110)-11)+" "+(B+(B%10+1)*110+11);
								}
							}
							
						}else if(m==0 && left/10==1){
							if((2+B%10)>10) {
								if((B/10)<=(2+B%10)) {
									if((B/10)==(2+B%10))Answer = 2+" "+999+" "+(B+2+m*110);
									else Answer=2+" "+(999-(B%10+2-B/10)*10)+" "+ (B+2+(B%10+2-B/10)*10);
								}
							} 
						}

						if(B==10)Answer = 3+" "+near_circle+" "+9+" 1";// 기본
						else if(B%11==0)Answer = 2+" "+near_circle+" "+(B);
						else if (B%10==0) Answer = 3+" "+near_circle+" "+(B/11)*11+" "+(B%11);//일의자리가 0이라 m 백의자리가 0될 수 없음
					}else {//110>B>=100
						//if(B==100)Answer = 3+" "+near_circle+" 99 1";
						//else Answer = 3+" "+near_circle+" 101 "+(B-101);
						Answer = 3+" "+near_circle+" 101 "+(B-101);
						if(m-B%10>0) {//이거 아님
							if(B%10-1<=B/10) {
								//System.out.println(1);
								Answer = 2+" "+(near_circle-(B%10-1)*110)+" "+(B+(B%10-1)*110);}
							else if(B/10<=B%10) {
								if(B%10==9) Answer = 3+" "+near_circle+" "+"101 8";//더하기 1하면 1의자리 0됨. 회문 불가..
								else Answer = 2+" "+(near_circle-(B%10)*110-11)+" "+(B+(B%10)*110+11);
								}
						}
						
						if(B==100)Answer = 3+" "+near_circle+" 99 1";
					}
					if(n==10000)Answer = "2 9999 1";
				}
			}
			
			int num = Integer.parseInt(Answer.charAt(0)+"");
			String result = Answer;
			if((result.length())<2)System.err.println("0인 경우  "+n);//System.err.println("0인 경우  "+result);
			else {
				result = result.substring(2);
				int sum=0;
				for(int k=0;k<num;k++) {
					int y;
					if(result.substring(result.lastIndexOf(" ")+1).equals(""))y=Integer.parseInt(result);
					else y = Integer.parseInt(result.substring(result.lastIndexOf(" ")+1));
					sum+=y;
					
					if(result.lastIndexOf(" ")==-1);
					else result = result.substring(0, result.lastIndexOf(" "));
					if(y<10) {
						if(y<0) {System.err.println("0안 맞음 "+n+"  Answer  "+Answer);}
					}
					else if(y<100&&(y>=10)) {
						if(y%11!=0) {System.err.println("1안 맞음 "+n+"  Answer  "+Answer+" "+y);}
					} else if(y<1000){
						if(y%10!=y/100)System.err.println("12안 맞음 "+n+"  Answer  "+Answer+" "+y);
					}else {
						int y_left=y/100;
						int y_right = y%100;
						if((y_left%10==y_right/10)&&(y_left/10==y_right%10));
						else {System.err.println("2안 맞음 "+n+"  Answer  "+Answer);}
					}
				}
				if(sum!=n)System.err.println("3안 맞음 "+n+"  Answer  "+Answer);
			}
			
			
			//System.out.println("Case #"+(test_case+1));
			//System.out.println(Answer);
		}
		
	}
}
