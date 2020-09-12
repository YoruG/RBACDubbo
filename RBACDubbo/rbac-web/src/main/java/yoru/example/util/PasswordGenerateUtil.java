package yoru.example.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Yorushika
 * @date 2020/9/12-0:08
 **/
public class PasswordGenerateUtil {
    public static String getMD5Password(String account,String password,int hashTimes)
    {
        Md5Hash md5Hash=new Md5Hash(password,account,hashTimes);
        return md5Hash.toString();
    }
}
