package studio.baxia.fo.common.digital;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fiat_Shamir {

	public static BigInteger n;
	public static BigInteger[] y;
	public String hash_res;
	public Map<String, Object> datas;
	public static int k;
	public static int t;
	
	public Fiat_Shamir(){}
	
	/**
	 * 
	 * @param m
	 * @return 1,代表成功; 0,代表失败
	 */
	public int runFiat_Shamir(String m){
		if(null == m){
			m = "";
		}
		
		BigInteger p;
		BigInteger q;
		
		BigInteger[] x;
		
		//1.体制参数（随机产生素数问题是一个重要的问题）
		
		//1.1 参数n及其素数产生(p,q是保密的大素数)
		p = new BigInteger("12345789");
		p = BigMathUtils.getRandom(p);
		q = new BigInteger("12345789");
		q = BigMathUtils.getRandom(q);
		p = p.nextProbablePrime();//大于p的最近一个素数
		q = q.nextProbablePrime();//大于q的最近一个素数
		System.out.println("1.1 pq素数产生--完毕");
		
		//1.2素数检验
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.out.println("素数产生出错");
			return 0;
		}else{
			n = p.multiply(q);
			System.out.println("p:"+p+",  q:"+q);
			System.out.println("素数p*q=n："+n);
		}
		System.out.println("1.2素数检验--完毕");
		
		//1.3 参数k(固定的正整数)
		k = 9;
		
		//1.4 参数y1,y2....yk:用户A的公开密钥，对任何i(1<=i<=k),yi都是模n的平方剩余
		//1.5参数x1,x2...xk:用户A的秘密密钥，对任何i(1<=i<=k),xi三sqrt(yi的逆元)(mod n)
				
		y = new BigInteger[k];	
		x = new BigInteger[k];
		//yi的逆元
		for (int i = 0; i < k; i++) {
			//x[i] = BigMathUtils.getX(BigMathUtils.euclidean(n,y[i]).abs(),n);
			BigInteger yN;
			do{
				x[i] = BigMathUtils.getRandom(n);
				yN = x[i].pow(2).mod(n);
			}while(yN.gcd(n).compareTo(new BigInteger("1")) != 0);
			
			y[i] = BigMathUtils.euclidean(n, yN);
			while(y[i].compareTo(new BigInteger("1")) < 0){
				y[i] = y[i].add(n);
			}
//			System.err.println("y*逆元："+y[i].multiply(x[i].pow(2)).mod(n));
//			System.out.println("x"+i+"="+x[i]);
//			System.out.println("y"+i+"="+y[i]);
		}
		System.out.println("1.体制参数--完毕");
		
		
		//2签名的产生过程
		//2.1随机选取一个正整数t
		Random rand = new Random();
		t = rand.nextInt(10)+1;
		System.err.println("t:"+t);
		//2.2随机选取t个介于1和n之间的数r1,r2...ri,并对任何j(1<=j<=t),计算Rj三rj2(mod n)
		BigInteger[] r = new BigInteger[t];
		BigInteger[] Rr = new BigInteger[t];
		
		for (int i = 0; i < t; i++) {
			r[i] = BigMathUtils.getRandom(n);
		}
		
		//获取R(1-t的值)
		for (int i = 0; i < t; i++) {
			Rr[i] = r[i].pow(2).mod(n); //r[i]与R[i]同余
			System.err.print("R:"+Rr[i]+"--");
		}
		System.out.println();
		//2.3计算杂凑值H(m,R1,R2,..Rt),并依次取出H(m,R1,R2,..Rt)的
		//前kt个比特值b11,..b1t,b21,...b2t,....,bk1,...bkt。
		String mess = "";
		for (int i = 0; i < Rr.length; i++) {
			mess = mess + Rr[i];
		}
		hash_res = BigMathUtils.hash(m+mess);
		String[][] b = new String[k][t];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < t; j++) {
				b[i][j] = hash_res.charAt(i*t+j) + "";
			}
		}
		
		//2.4对任何j(1<=j<=t),计算sj三ri (xi^bij)+(1...k) mod n
		//((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))作为对m的数字签名
		BigInteger[] s = new BigInteger[t];
		for (int j = 0; j < t; j++) {
			BigInteger sum = new BigInteger("1");
			for (int i = 0; i < k; i++) {
				 sum = sum.multiply(x[i].pow(Integer.valueOf(b[i][j])).mod(n)).mod(n);
			}
			s[j] = r[j].mod(n).multiply(sum).mod(n);
			System.err.print("s:"+s[j]+"--");
		}
		System.out.println();
		datas = new HashMap<String, Object>();
		datas.put("b", b);
		datas.put("s", s);
		datas.put("m", m);

		return 1;
	}
	
	/**
	 * @Des 验证签名
	 * @param datas
	 */
	public static String verification(Map<String, Object> datas) {
		System.out.println("---------------------验证部分----------------");
		String[][] b = (String[][]) datas.get("b");
		BigInteger[] s = (BigInteger[]) datas.get("s");
		String m = (String) datas.get("m");
		BigInteger[] R_ = new BigInteger[s.length];
		BigInteger n = Fiat_Shamir.n;
		BigInteger[] y = Fiat_Shamir.y;
		//3签字的验证过程
		//接收方收到消息m和签名((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))，用以下步骤来验证
		//3.1对任何j(1<=j<=t),计算R'j三Sj^2 * (yi^bij)+(1...k) mod n
		
		for (int j = 0; j < s.length; j++) {
			BigInteger sum = new BigInteger("1");
			for (int i = 0; i < b.length; i++) {
				 sum = sum.multiply(y[i].pow(Integer.valueOf(b[i][j])).mod(n)).mod(n);
			}
			R_[j] = s[j].pow(2).mod(n).multiply(sum).mod(n);
			System.out.print("R_:"+R_[j]+"--");
		}		
		System.out.println();		
				
		//3.2 计算H(m,R'1,R'2,....R't)
		String mess = "";
		for (int i = 0; i < R_.length; i++) {
			mess = mess + R_[i];
		}
		String hashRes = BigMathUtils.hash(m+mess);
		//3.3验证(b11,....b1t,b21,....,b2t,bk1,...,bkt)是否依旧是H(m,R'1,R'2,....R't)的前kt个比特。如果是，则以上数字签名是有效的
		boolean flag = true;	
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < s.length; j++) {
				//如果有一个比特位不相同，则中断并标记无效
				if(!(b[i][j].toString().equals(hashRes.charAt(i*s.length+j)+""))){
					flag = false;
					break;
				}
			}
		}
				
		if(!flag){
			return "签名无效";
		}else{
			return "签名有效";
		}
		
	}
}
