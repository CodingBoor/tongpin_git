package com.qmx163.util.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by likai on 2017/8/2.
 * 判断是否为手机，邮箱，qq 工具类
 */

public class RegexValidateUtil {
    static boolean flag = false;
    static String regex = "";
    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    /**
     * 验证非空
     * @return
     */
    public static boolean checkNotEmputy(String notEmputy) {
        regex = "^\\s*$";
        return check(notEmputy, regex) ? false : true;
    }
    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        return check(email, regex);
    }
    /**
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        return check(cellphone, regex);
    }
    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return  check(telephone, regex);
    }
    /**
     * 验证传真号码
     *
     * @param fax
     * @return
     */
    public static boolean checkFax(String fax) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(fax, regex);
    }

    /**
     * 验证QQ号码
     *
     * @param QQ
     * @return
     */
//    public static boolean checkQQ(String QQ) {
//        String regex = "^\\d[1-9]{5,10}$";
//        return check(QQ, regex);
//    }

    public static boolean checkQQ(String QQ) {
        // TODO Auto-generated method stub
        String qq = QQ;
        String regex = "[1-9][0-9]{3,10}";//第一位1-9之间的数字，第二位0-9之间的数字，数字范围4-11个之间
        //String regex2 = "[1-9]\\d{4,14}";//此句也可以
        boolean flag = qq.matches(regex);
            return flag;
    }
}
