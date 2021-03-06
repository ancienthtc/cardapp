package com.jd.cardapp.util.password;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Secret {

	//获取秘钥
	public static SecretKey readKey(){

		try {
			String skey = "XXXXXXXXXXXXXXX";
			byte[] bs = skey.getBytes("UTF8");

			//创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(bs);

			//转换DESKeySpec为SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);

			return key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// 加密密码
	public static String enPass(String password){

		try {
			SecretKey key = readKey();

			Cipher cip = Cipher.getInstance("DES");
			cip.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipBytes = cip.doFinal(password.getBytes());
			String sblob = new BASE64Encoder().encode(cipBytes);

			//return sblob; //改造去=
			return sblob.substring(0,sblob.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//解密密码
	public static String dePass(String password){

		try {
			password=password+"=";
			byte[] keyBuffer = new BASE64Decoder().decodeBuffer(password);//改造 +"="
			//byte[] keyBuffer = new BASE64Decoder().decodeBuffer(password+"=");

			SecretKey key = readKey();

			Cipher cip = Cipher.getInstance("DES");
			cip.init(Cipher.DECRYPT_MODE, key);

			String stmp =  new String(cip.doFinal(keyBuffer),"UTF8");

			return stmp;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
