package studio.baxia.fo.common.digital;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Guillou_Quisquater {
	private static int v;
	public static BigInteger y;
	public static BigInteger n;
	public static String e;
	public static Map<String, Object> data;
	public static void main(String[] args) {
		runGuillou_Quisquater("123");
	}
	public Guillou_Quisquater(){}
	
	/**
	 * @Des生成签名
	 * @param args
	 * @return 
	 */
	public static int runGuillou_Quisquater(String m) {
		BigInteger p;
		BigInteger q;

		BigInteger x;
		
		//1.体制参数（随机产生素数问题是一个重要的问题）
		
		//1.1 参数n及其素数产生(p,q是保密的大素数)
		p = new BigInteger("123456789");
		q = new BigInteger("123456789");
		//产生随机的p、q
		p = BigMathUtils.getRandom(p);
		q = BigMathUtils.getRandom(q);
		p = p.nextProbablePrime();//大于p的最近一个素数
		q = q.nextProbablePrime();//大于q的最近一个素数
		System.out.println("1.1 pq素数产生--完毕");
		
		//1.2素数检验
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.err.println("素数产生出错");
			return 0;
		}else{
			n = p.multiply(q);
			System.out.println("素数p*q=n："+n);
		}
		System.out.println("1.2素数检验--完毕");
		
		//1.3 参数v:gcd(v,(p-1)(p-1))=1
		Random random = new Random();
		v = random.nextInt(10000)+1;
		BigInteger bigV = new BigInteger(v+"");
		while(n.gcd(bigV).compareTo(new BigInteger("1")) != 0){
			v = random.nextInt(10000);
			bigV = new BigInteger(v+"");
		};
		//1.4 x:用户A的秘密钥，x属于RZn^*;
		x = BigMathUtils.getRandom(n);
		
		//1.5 y:用户A的公开钥，y属于RZn^*,且x^v *y三1(mod n);
		y = BigMathUtils.euclidean(n, x.pow(v));
		while(y.compareTo(new BigInteger("1")) < 0){
			y = y.add(n);
		}
		System.out.println(y);
		
		//2签字的产生过程
		//对于待签消息m，A进行以下步骤：
		//2.1随机选择一个数k属于Zn^*,计算T三k^v(mod n)
		BigInteger k = BigMathUtils.getRandom(n);
		BigInteger T = k.pow(v).mod(n);
		
		//2.2计算杂凑值：e=H(m,T),且使1<e<v;否则，返回步骤2.1
		e = BigMathUtils.hash(m+T.toString());
		
		//2.3计算s三kx^e mod n
		BigInteger s = k.mod(n).multiply(BigMathUtils.fastExponent(x, 
				BigMathUtils.binToDecimal(e), n)).mod(n);
		
		//以(e,s)作为对m的签名

		data = new HashMap<String, Object>();
		data.put("e", e);
		data.put("s", s);
		
		data.put("message", m);

		return 1;
	}
	
	/**
	 * @Des Guillou_Quisquater签名的验证
	 * @param datas
	 */
	public static String verification(Map<String, Object> data) {
		//3签字的验证过程
		//接收方在收到消息m和数字签名(e,s)后，用以下步骤来验证
		String m = (String) data.get("message");
		String e = (String) data.get("e");
		BigInteger s = (BigInteger) data.get("s");
		
		//3.1计算出T_三s^v *y^e(mod n)
		BigInteger T_ = s.pow(v).mod(n).multiply(BigMathUtils.fastExponent(y, 
				BigMathUtils.binToDecimal(e), n)).mod(n);
		
		//3.2计算出e_ = H(m,T)
		String e_ = BigMathUtils.hash(m+T_.toString());
		
		//3.3验证：Ver(y,(e,s),m)=True <=> e_ = e。		
		boolean flag = true;	
		for (int i = 0; i < e.length(); i++) {
			//如果有一个比特位不相同，则中断并标记无效
			//if(e.charAt(i) != e_.charAt(i)){
			if(!(e.charAt(i)+"").equals(e_.charAt(i)+"")){
				flag = false;
				break;
			}
		}
				
		if(flag){
			return "Guillou_Quisquater签名有效";
		}else{
			return "Guillou_Quisquater签名无效";
		}
		
	}
}
