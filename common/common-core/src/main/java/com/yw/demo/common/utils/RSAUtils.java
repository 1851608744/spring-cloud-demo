package com.yw.demo.common.utils;

import com.yw.demo.common.constant.RSAConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @description RSA非对称加密
 * @data 2021/07/23
 **/
@Slf4j
public class RSAUtils {

    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();


    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeyString =
                    new String(Base64.encodeBase64(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString =
                    new String(Base64.encodeBase64((privateKey.getEncoded())));
            // 将公钥和私钥保存到Map
            //0表示公钥
            keyMap.put(0, publicKeyString);
            //1表示私钥
            keyMap.put(1, privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 私钥加密、公钥解密 ---- 加密
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     */
    private static String privateEncryption(String str, String privateKey) {
        try {
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec =
                    new PKCS8EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey =
                    keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, priKey);
            byte[] result = cipher.doFinal(str.getBytes("UTF-8"));
            String outStr = Base64.encodeBase64String(result);
            log.info("私钥加密、公钥解密 ---- 加密:" + outStr);
            return outStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥加密、公钥解密 ---- 解密
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 铭文
     */
    private static String privateDecrypt(String str, String publicKey) {
        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(publicKey);

            X509EncodedKeySpec x509EncodedKeySpec =
                    new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            String outStr = new String(cipher.doFinal(inputByte));
            log.info("私钥加密、公钥解密 ---- 解密:" + outStr);
            return outStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密、私钥解密 ---- 加密
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 铭文
     * @throws Exception
     */
    private static String publicEncryption(String str, String publicKey) throws Exception {
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        log.info("公钥加密、私钥解密 ---- 加密:" + outStr);
        return outStr;

    }

    /**
     * 私钥解密、公钥加密 ---- 解密
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception
     */
    private static String publicDecrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec =
                new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher3 = Cipher.getInstance("RSA");
        cipher3.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher3.doFinal(inputByte));
        log.info("公钥加密、私钥解密 ---- 解密:" + outStr);
        return outStr;
    }

    public static void main(String[] args) throws Exception{
        genKeyPair();
        String message = "df723820";
        String pubKey = keyMap.get(0);
        String priKey = keyMap.get(1);
        String s = publicEncryption(message, pubKey);
        log.info("加密公钥：" + s);

        String s1 = publicDecrypt(s, priKey);
        log.info("私钥解密：" + s1);

        String s2 = privateEncryption(message, priKey);
        log.info("加密私钥：" + s2);

        String s3 = privateDecrypt(s2, pubKey);
        log.info("公钥解密：" + s3);
    }
}
