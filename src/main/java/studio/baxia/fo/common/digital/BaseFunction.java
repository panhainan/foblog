package studio.baxia.fo.common.digital;

import java.math.BigInteger;

/**
 * 大数之间的逻辑与、或、非、异或
 *<p> 64步轮询的F、H、I、G操作，及其常用表T、S
 * 
 * @author 北辰不落雪
 *
 */
public class BaseFunction {
	
	/**
	 * @Des 逻辑与
	 * @param a
	 * @param b
	 * @return
	 */
	public static String logicAnd(String a, String b){
		if(a.length()!=b.length()){
			System.err.println("---logicAnd error----");
			return "";
		}
		StringBuffer c = new StringBuffer();
		char a_;
		char b_;
		for (int i = 0; i < a.length(); i++) {
			a_ = a.charAt(i);
			b_ = b.charAt(i);
			if(a_ == '1' && b_ == '1'){
				c.append(1);
			}else{
				c.append(0);
			}
		}
		return c.toString();
	}
	
	/**
	 * @Des 逻辑或
	 * @param a
	 * @param b
	 * @return
	 */
	public static String logicOr(String a, String b){
		if(a.length()!=b.length()){
			System.err.println("---logicOr error----");
			return "";
		}
		StringBuffer c = new StringBuffer();
		char a_;
		char b_;
		for (int i = 0; i < a.length(); i++) {
			a_ = a.charAt(i);
			b_ = b.charAt(i);
			if(a_ == '1' || b_ == '1'){
				c.append(1);
			}else{
				c.append(0);
			}
		}
		return c.toString();
	}
	
	/**
	 * @Des 逻辑非
	 * @param a
	 * @return
	 */
	public static String logicNon(String a){
		StringBuffer c = new StringBuffer();
		char a_;
		for (int i = 0; i < a.length(); i++) {
			a_ = a.charAt(i);
			if(a_ == '1'){
				c.append(0);
			}else{
				c.append(1);
			}
		}
		return c.toString();
	}
	
	/**
	 * @Des 逻辑异或
	 * @param a
	 * @param b
	 * @return
	 */
	public static String logicXOR(String a,String b){
		if(a.length()!=b.length()){
			System.err.println("---logicXOR error----");
			return "";
		}
		StringBuffer c = new StringBuffer();
		char a_;
		char b_;
		for (int i = 0; i < a.length(); i++) {
			a_ = a.charAt(i);
			b_ = b.charAt(i);
			if((a_ == '1' && b_ == '1') || (a_ == '0' && b_ == '0')){
				c.append(0);
			}else{
				c.append(1);
			}
		}
		return c.toString();
	}
	
	public static String F(String b,String c,String d){
//		Long b_ = Long.parseLong(b, 2); 
//		Long c_ = Long.parseLong(c, 2); 
//		Long d_ = Long.parseLong(d, 2); 
//		Long I = Long.valueOf((b_ & c_) | (~b_ & d_));
//		return Long.toBinaryString(I);

		return logicOr(logicAnd(b, c),logicAnd(logicNon(b), d));
	}
	
	public static String G(String b,String c,String d){
		return logicOr(logicAnd(b, d),logicAnd(c, logicNon(d)));
	}
	
	public static String H(String b,String c,String d){
		return logicXOR(logicXOR(b, c),d);
	}
	
	public static String I(String b,String c,String d){
		return logicXOR(c, logicOr(b, logicNon(d)));
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < T_Con.T.length; i++) {
			System.out.println("T["+i+"]="+T_Con.T[i]);
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				System.err.print(i+"-"+j+":"+S_Con.s[i][j]+" ");
			}
			System.out.println();
		}
		String b = "1";
		String c = "0";
		String d = "0";
		System.err.print(F(b,c,d));
		System.err.print(G(b,c,d));
		System.err.print(H(b,c,d));
		System.err.print(I(b,c,d));
		
//		String a = "1";
//		String b = "0101";
//		
//		BigInteger bigA = new BigInteger(a);
//		BigInteger bigB = new BigInteger(b);
//		System.out.println(bigA.toString(10));
//		System.out.println(bigA.or(bigB));
//		
//		Long d = Long.parseLong(a, 2); 
//		Long o = Long.parseLong(b, 2);
//		System.out.println("---------"+Long.toBinaryString(d | o));
//		System.out.println(d);
//		System.out.println(o);
	}
	
	/**
	 * @Des 压缩函数每步左循环移位的位数(0~3,0~15)
	 * @author tyb
	 *
	 */
	static class S_Con{
		public static int[][] s = new int[4][16];
		static{
			instance();
		}
		private static void instance(){
			s[0][0] = 7;
			s[0][1] = 12;
			s[0][2] = 17;
			s[0][3] = 22;
			
			s[1][0] = 5;
			s[1][1] = 9;
			s[1][2] = 14;
			s[1][3] = 20;
			
			s[2][0] = 4;
			s[2][1] = 11;
			s[2][2] = 16;
			s[2][3] = 23;
			
			s[3][0] = 6;
			s[3][1] = 10;
			s[3][2] = 15;
			s[3][3] = 21;
			for(int a = 0; a < s.length; a++){
				for (int k = 4; k < s[a].length; k++) {
					s[a][k] = s[a][k-4];
				}
			}
		}
	
	}

	/**
	 * @Des 常用表T (0~63)
	 * @author tyb
	 *
	 */
	static class T_Con{
		public static String[] T = new String[64];
		static{
			instance();
		}
		private static void instance(){
			//未采用Biginteger 因为0.xx 无法表示，而long long型足够使用
			for (int i = 0; i < T.length; i++) {
				Long x = (long) Math.pow(2, 32);
				Long res =  (long) ((long)x*Math.abs(Math.sin(Math.toRadians((i+1)*180/Math.PI))));
				T[i] = new BigInteger(res+"").toString(16);
			}
			
		}
	
	}
	
}
