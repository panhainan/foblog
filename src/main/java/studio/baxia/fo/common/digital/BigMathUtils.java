package studio.baxia.fo.common.digital;

import java.math.BigInteger;
import java.util.Random;

public class BigMathUtils {

	public static void main(String[] args) {
		//System.err.println(hash("aaa"));
		System.err.println(fastExponent(new BigInteger("7"),new BigInteger("561"),new BigInteger("561")));
	}
	
	/**
	 * @Des 正整数的开方
	 * @param bigNum
	 * @return 返回正整数部分
	 */
	public static BigInteger bigSqrt(BigInteger bigNum){
		BigInteger sqrtNum = null;
		int l = 1;
		while(bigNum.compareTo(new BigInteger(l*l+"")) >= 0){
			sqrtNum = new BigInteger(l+"");
			l++;
		}
		return sqrtNum;
	}
	/**
	 * @Des Miller-Rabin判断是否为素数,为素数的概率至少为1-2^-(a的个数)
	 * @param a 
	 * @param number 模数（需要判断的数）
	 * @author tyb
	 * @return 是否为素数 false为非素数
	 */
	public static boolean MR_isPrime(BigInteger a,BigInteger number){
		String binary = number.subtract(new BigInteger("1")).toString(2);
		char[] chBinary = binary.toCharArray(); //获得二进制的每个字符
		BigInteger d = new BigInteger("1");
		BigInteger x ;	
		//witness核心部分
		for (int i = 0; i < binary.length() ; i++) {
			x = d;
			d = d.pow(2).mod(number);
			//如果d=1并且x!=1并且x!=n-1则返回false
			if(d.compareTo(new BigInteger("1")) == 0 && x.compareTo(new BigInteger("1")) != 0
					&& x.compareTo(number.subtract(new BigInteger("1"))) != 0){
				return false;
			}
			if(chBinary[i] == 49){  //char值49代表数字1
				d = d.multiply(a).mod(number);
			}
		}
		if(d.compareTo(new BigInteger("1")) != 0){
			return false;
		}
		return true;
	}
	
	/**
	 * @Des 通过java自带的方法获得1-2^(len-1) 的之间的随机值
	 * @param number
	 * @return
	 */
	public static BigInteger getBigRandom(BigInteger number){
		BigInteger ran = new BigInteger(number.bitLength()-1,new Random());
		//排除0值
		while(ran.compareTo(new BigInteger("0")) == 0){
			ran = new BigInteger(number.bitLength()-1,new Random());
		}
		return ran;
	}
	
	/**
	 * @Des 判断是否为素数,调用miller-Rabin算法
	 * @param number
	 * @author tyb
	 * @return true 为素数
	 */
	public static boolean isPrime(BigInteger number){
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			if(!MR_isPrime(getBigRandom(number), number)){
				flag = false;  //只要有一个为false 则不为素数
			}
		}
		return flag;
	}
	
	/**
	 * @Des 平方剩余：是一个数学概念。假设p是素数，a是整数。 如果存在一个整数x使得x^2≡a(mod p) 
	 * (即x^2-a可以被p整除)， 那么就称a在p的剩余类中是平方剩余的。p的平方剩余个数为(p-1)/2
	 * @param p p的平方剩余
	 * @author tyb
	 * @return 随机p的平方剩余
	 */
	public static BigInteger squResidual(BigInteger p){

		BigInteger a = null;//返回的a值
		try{
			BigInteger exponent = (p.subtract(new BigInteger("1"))).divide(new BigInteger("2"));
			boolean flag = true;
			while(flag){
			    a = getBigRandom(p); //a的值
			    System.out.println("ran="+a);
				BigInteger r = fastExponent(a, exponent, p);//1(平方剩余),-1,0
				if(r.compareTo(new BigInteger("1")) == 0){
					flag = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;	
	}
	
	/**
	 * @Des 平方剩余：是一个数学概念。假设p是素数，a是整数。 如果存在一个整数x使得x^2≡a(mod p) 
	 * (即x^2-a可以被p整除)， 那么就称a在p的剩余类中是平方剩余的。p的平方剩余个数为(p-1)/2
	 * @param p p的平方剩余
	 * @author tyb
	 * @return 随机p的平方剩余
	 */
	public static BigInteger quaResidual(BigInteger p){

		BigInteger a = null;//返回的a值
		try{
			BigInteger x = getRandom(p);
			a = x.pow(2).mod(p);
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;	
	}
	
	/**
	 * @Des 扩展欧几里得算法求逆元，gcb(a,b)=1才存在逆元
	 * @author tyb
	 * @param a 较大的数
	 * @param b 余数
	 * @return a，b最大公约数
	 */
	public static BigInteger euclidean(BigInteger a,BigInteger b){
		//初始化参数
		BigInteger x1 = new BigInteger("1"), x2 = new BigInteger("0"), 
				y1 = new BigInteger("0"), y2 = new BigInteger("1");
		BigInteger Q;
		BigInteger t1, t2, t3;
		
			//a,b最大公约数为1,说明互素
			while(true){
				//互为非素数，返回最大公约数或是0
				if(b.compareTo(new BigInteger("0")) == 0){
					//return a.gcd(b);
					return new BigInteger("0");
				}
				//分解到b=1,则返回逆元y2
				if(b.compareTo(new BigInteger("1")) == 0){
					return y2;
				}
				Q = a.divide(b);
				t1 = x1.subtract(Q.multiply(y1));
				t2 = x2.subtract(Q.multiply(y2));
				t3 = a.subtract(Q.multiply(b));
				
				x1 = y1;
				x2 = y2;
				a = b;
				
				y1 = t1;
				y2 = t2;
				b = t3;
			}
	}
	
	/**
	 * @Des 快速指数算法
	 * @param a 底数a
	 * @param m 指数幂m
	 * @param n 模n
	 * @return a^m(mod n) 
	 */
	public static BigInteger fastExponent(BigInteger a,BigInteger m,BigInteger n){
		String binary = m.toString(2);
		char[] biChar = binary.toCharArray(); //获得幂指数m的二进制char数组
		BigInteger c = new BigInteger("0");
		BigInteger d = new BigInteger("1");
		for (int i =0; i <  binary.length(); i++) {
			c = c.multiply(new BigInteger("2"));
			d = d.pow(2).mod(n);
			if(biChar[i] == '1'){  //ascii值49为数字1(biChar[i] == 49)也可以
				c = c.add(new BigInteger("1"));
				d = d.multiply(a).mod(n);
			}
		}
		return d;
	}
	
	/**
	 * @Des 获取随机数(前后两部分拼接，所以可能单独一部分超出int的表示范围)
	 * @param n 大整数n
	 * @return 返回小余n的随机正整数
	 */
	public static BigInteger getRandom(BigInteger n){
		int len = n.toString().length()-1;
		Random random = new Random();
		int xx = 1;
		for (int i = 0; i < len/2; i++) {
			 xx = xx*10;
		}
		int ranFront = (int) (random.nextDouble()*xx);
		int ranBottom = (int) (random.nextDouble()*xx);
		
		return new BigInteger(ranFront+""+ranBottom);
	}
	
	/**
	 * @Des MD5杂凑算法，又称hash算法
	 * @param m  消息m
	 * @return 前kt比特值
	 */
	public static String hash(String m) {
  
		//准备阶段：将消息转化为二进制信息
		char[] mess =  m.toCharArray();
		StringBuffer result = new StringBuffer();
		//StringBuffer res_start = new StringBuffer();
		for (int i = 0; i < mess.length; i++) {
			Integer x = (int) mess[i];
			String str = fillSeats(Integer.toBinaryString(x)+"",8);
			result.append(str);    
		}
		
		//1.对消息进行填充
		int messLen = result.toString().length(); //消息的长度
		int lastLen = ((messLen/512)+1)*512;  //最终长度，为512的倍数 数
		BigInteger mesBig = new BigInteger(messLen+"");
		
		//其对于模512的长度超过448则不够留下64 需要填充
		if((mesBig.mod(new BigInteger("512"))).compareTo(new BigInteger("448")) >= 0){
			lastLen+=512;
		}
		
		result.append(1);//第一位为1
		for (int i = 0, j = lastLen-64-messLen-1; i < j; i++) {
			result.append(0);
		}
		
		//2.附加消息的长度
		int exLen = messLen; 
		if(messLen > Math.pow(2, 64)){
			exLen = new BigInteger(messLen+"").mod(new BigInteger(Math.pow(2, 64)+"")).intValue();
		}
		String exMess = Integer.toBinaryString(exLen);
		//低地址位填充,先填充长度，再补充0
		exMess = fillSeats(exMess, 32);
		result.append(exMess);
		for (int i = 0; i < 64-exMess.length(); i++) {
			result.append(0);
		}
		
		//分为512*L ,L组;按字表示为M[0,1,.....N-1],32比特为一字
		int L = result.length()/512;
		String[] Y = new String[L];
		for (int i = 0; i < Y.length; i++) {
			Y[i] = result.toString().substring(i*512, (i+1)*512-1);
		}
		int N = L*16;
		String[] M = new String[N];
		StringBuffer MT;
		//前面消息分组，每32位比特以每4位为一单元按照最低字节位存储
		for (int i = 0, j = 0; i < M.length-2; i++) {
//			M[i+3] = result.substring(i*8,i*8+8);
//			M[i+2] = result.substring(i*8+8, i*8+16);
//			M[i+1] = result.substring(i*8+16, i*8+24);
//			M[i] = result.substring(i*8+24, i*8+32);
			MT = new StringBuffer();
			MT.append(result.substring(j*8+24, j*8+32));
			MT.append(result.substring(j*8+16, j*8+24));
			MT.append(result.substring(j*8+8, j*8+16));
			MT.append(result.substring(j*8,j*8+8));
			M[i] = MT.toString();
			j+=4;
		}
		//后面的64位同样
		exMess = fillSeats(exMess, 64);
		M[M.length-2] = exMess.substring(32, 64);
		M[M.length-1] = exMess.substring(0, 32);
		
		//1.3 对MD缓冲区初始化
		String A = "67452301";
		String B = "EFCDAB89";
		String C = "98BADCFE";
		String D = "10325476";
		String A_ = fillSeats(Long.toBinaryString(Long.valueOf(A,16)), 32);
		String B_ = fillSeats(Long.toBinaryString(Long.valueOf(B,16)), 32);
		String C_ = fillSeats(Long.toBinaryString(Long.valueOf(C,16)), 32);
		String D_ = fillSeats(Long.toBinaryString(Long.valueOf(D,16)), 32);
		String[] CV = new String[L];
		CV[0] = A_ + B_ + C_ + D_;
		String[] ABCD = new String[4];
		ABCD[0] = A_;
		ABCD[1] = B_;
		ABCD[2] = C_;
		ABCD[3] = D_;
		
		//1.4以分组为单位对消息进行处理
		StringBuffer x = new StringBuffer();
		StringBuffer binaRes = new StringBuffer();
		for (int i = 0; i < L; i++) {
			//System.out.println("第"+(i+1)+"组");
			String[] outFour = MD5(A_,B_,C_,D_,M,i);
			
			//CV[0] 每个字与第四轮的输出 MD5(A,B,C,D,M,i)做模2^32相加
			
			for (int j = 0; j < 4; j++) {
				//ABCD[j] = mod32Add(CV[0].substring(j*32, (j+1)*32), outFour[j]);
				ABCD[j] = mod32Add(ABCD[j], outFour[j]);
			}
		}
		
		//1.5输出
		//二进制及16进制输出
		for (int i = 0; i < ABCD.length; i++) {
			x.append(new BigInteger(Long.parseLong(ABCD[i], 2)+"").toString(16));
			binaRes.append(ABCD[i]);
		}
//		System.err.println("二进制输出："+binaRes.toString());
		System.err.println("最终输出："+x.toString());
		return binaRes.toString();
	}
	
	/**
	 * @param a,b,c,d 缓冲区的4个字 (二进制)
	 * @param m 按字表示消息的M
	 * @param q 当前分组 
	 * @Des MD5压缩函数：a<-b+CLSs(a+g(b,c,d)+X[k]+T[i])
	 */
	private static String[] MD5(String a, String b, String c, String d, String[] m, int q) {
		//TODO 置换问题，M中值的位置更改了，下次更换的是上一次更改后的位置。
//		a = fillSeats(Long.toBinaryString(Long.valueOf(a,16)), 32);
//		b = fillSeats(Long.toBinaryString(Long.valueOf(b,16)), 32);
//		c = fillSeats(Long.toBinaryString(Long.valueOf(c,16)), 32);
//		d = fillSeats(Long.toBinaryString(Long.valueOf(d,16)), 32);
		String[] m2 = new String[m.length];
		for (int i = 0; i < m2.length; i++) {
			m2[i] = m[i];
		}
		
		for (int i = 0; i < 64; i++) {
			//16轮F函数
			if(i<16){
//				System.out.print(change(a, 2, 10)+" ");
//				System.out.print(change(b, 2, 10)+" ");
//				System.out.print(change(c, 2, 10)+" ");
//				System.out.print(change(d, 2, 10)+"--");
				
				String cls = mod32Add(a,mod32Add(fillSeats(BaseFunction.F(b, c, d),32),
						mod32Add(m[q*16+i],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//左移s并与b 做模2^32加法
				a = mod32Add(b, shift_Left(cls, BaseFunction.S_Con.s[0][i]).toString());
				//a = b + (a + BaseFunction.F(b, c, d) + m[q*16+i] + BaseFunction.T_Con.T[i]);
				
				//右移一个字
					String a_ = a;
					String b_ = b;
					String c_ = c;
					String d_ = d;
					
					a = d_;
					b = a_;
					c = b_;
					d = c_;
					System.out.print(change(a, 2, 10)+" ");
					System.out.print(change(b, 2, 10)+" ");
					System.out.print(change(c, 2, 10)+" ");
					System.out.println(change(d, 2, 10));
			}
			//16轮G函数
			else if(i >= 16 && i < 32){
				if(i==16){
					for (int j = 0; j < 16; j++) {
						int index = (1+5*(j+1))%16;
						if(index == 0) index = 15;
						else index = index - 1; 
						m[q*16+j] = m2[q*16+index];
					}
				}
				String cls = mod32Add(a,mod32Add(fillSeats(BaseFunction.G(b, c, d),32),
						mod32Add(m[q*16+(i-16)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//左移s并与b 做模2^32加法
				a = mod32Add(b, shift_Left(cls, BaseFunction.S_Con.s[1][i-16]).toString());
				//a = b + (a + BaseFunction.G(b, c, d) + m[q*16+(i-16)] + BaseFunction.T_Con.T[i]);
				//右移一个字
				
					String a_ = a;
					String b_ = b;
					String c_ = c;
					String d_ = d;
					
					a = d_;
					b = a_;
					c = b_;
					d = c_;
				
			}
			//16轮H函数
			else if(i >= 32 && i < 48){
				if(i==32){
					for (int j = 0; j < 16; j++) {
						int index = (5+3*(j+1))%16;
						if(index == 0) index = 15;
						else index = index - 1;
						m[q*16+j] = m2[q*16+ index];
					}	
				}
				String cls = mod32Add(a,mod32Add(fillSeats(BaseFunction.H(b, c, d),32),
						mod32Add(m[q*16+(i-32)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//左移s并与b 做模2^32加法
				a = mod32Add(b, shift_Left(cls, BaseFunction.S_Con.s[2][i-32]).toString());
				//a = b + (a + BaseFunction.H(b, c, d) + m[q*16+(i-32)] + BaseFunction.T_Con.T[i]);
				//右移一个字
				
					String a_ = a;
					String b_ = b;
					String c_ = c;
					String d_ = d;
					
					a = d_;
					b = a_;
					c = b_;
					d = c_;
				
			}
			//16轮I函数
			else if(i >= 48 && i < 64){
				if(i==48){
					for (int j = 0; j < 16; j++) {
						int index = (7*(j+1))%16;
						if(index == 0) index = 15;
						else index = index - 1;
						m[q*16+j] = m2[q*16+index];
					}
				}
				String cls = mod32Add(a,mod32Add(fillSeats(BaseFunction.I(b, c, d),32),
						mod32Add(m[q*16+(i-48)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//左移s并与b 做模2^32加法
				a = mod32Add(b, shift_Left(cls, BaseFunction.S_Con.s[1][i-48]).toString());
				//a = b + (a + BaseFunction.I(b, c, d) + m[q*16+(i-48)] + BaseFunction.T_Con.T[i]);
				
				//右移一个字
				
					String a_ = a;
					String b_ = b;
					String c_ = c;
					String d_ = d;
					
					a = d_;
					b = a_;
					c = b_;
					d = c_;
				
			}
		}
		String[] data = new String[4];
		data[0] = a;
		data[1] = b;
		data[2] = c;
		data[3] = d;
		return data;
		
	}
	
	/**
	 * @Des 向左循环移位b
	 * @param a
	 * @param b
	 * @return
	 */
	public static String shift_Left(String a,int b){
		int len = a.length();
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < len; i++) {
			res.append(a.charAt((i+b)%len));
		}
		return res.toString();
	}
	/**
	 * @Des 模2 加法
	 * @param a
	 * @param b
	 * @return
	 */
//	private static String mod2Add(String a, String b) {
//		StringBuffer c = new StringBuffer();
//		char a_;
//		char b_;
//		for (int i = 0; i < a.length(); i++) {
//			a_ = a.charAt(i);
//			b_ = b.charAt(i);
//			if(a_ == '1' || b_ == '1'){
//				c.append(1);
//			}else{
//				c.append(0);
//			}
//		}
//	
//		return c.toString();
//	}
	
	/**
	 * @Des 补齐x位，前面加0
	 * @param mess 二进制数字
	 * @param x 要求达到的位数
	 * @return
	 */
	public static String fillSeats(String mess,int x){
		if(mess.length() > x){
			System.err.println("----fillSeats error---");
			return mess;
		}
		for (int i = 0, j = x-mess.length(); i < j; i++) {
			mess = "0" + mess;
		}
		return mess;
	}
	
	/**
	 * @Des 二进制转化为十进制
	 * @param e
	 * @return
	 */
	public static BigInteger binToDecimal(String e){
		BigInteger s = new BigInteger("0");
		for (int i = 0, j = e.length(); i < j; i++) {
			if(e.charAt(i) == '1'){
				s = s.add(new BigInteger("2").pow(j-i-1));
			}
			else{}
		
		}
		//System.err.println(Long.valueOf(e,2));
		System.err.println(s.toString());
		return s;
	}

	/**
	 * @Des 进制转换
	 * @param num 要转换的数字
	 * @param from 本来的进制
	 * @param to 目标进制
	 * @return
	 */
	public static String change(String num, int from, int to){
		return new BigInteger(num, from).toString(to);
	}
	
	/**
	 * @Des 平方剩余求X
	 * @param y
	 * @param n
	 * @return
	 */
	public static BigInteger getX(BigInteger y,BigInteger n) {
		BigInteger x = new BigInteger("1");
		while(x.pow(2).mod(n).compareTo(y) != 0){
			x = x.add(new BigInteger("1"));
		}
		return x;
	}
	
	/**
	 * @Des 模2^32 加法：相加结果超过2^32从1重新开始计算（2^32mod运算，注意为0情况(为0或是2^32次方)）
	 * @param a
	 * @param b
	 * @return 32比特长度的二进制
	 */
	private static String mod32Add(String a, String b) {
		BigInteger bigA = new BigInteger(change(a,2,10));
		BigInteger bigB = new BigInteger(change(b,2,10));
		
		if(new BigInteger(change(a,2,10)).compareTo(new BigInteger("2").pow(32)) >= 0 || 
				new BigInteger(change(b,2,10)).compareTo(new BigInteger("2").pow(32)) >= 0	) {
			System.out.println(bigA+"--"+bigB);
		}
		
		BigInteger m = new BigInteger("2").pow(32);
		BigInteger res = bigA.mod(m).add(bigB.mod(m)).mod(m);
		if(change(res.toString(),10,2).length()>32){
			System.out.println("max 32 ");
		}
		return fillSeats(change(res.toString(), 10, 2),32);
	}
}
