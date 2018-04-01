package com.qmx163.data.bean.Mebean;

import com.qmx163.util.View.carousel.Carousel;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据制造厂
 * Created by lzp on 2016/11/24.
 */
public class DataFactory {
    public static String avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498903048743&di=7aaa69f176c771a5610b3def45b97325&imgtype=0&src=http%3A%2F%2Fpic17.nipic.com%2F20111122%2F6759425_152002413138_2.jpg";
    public static String avatar1 = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=3&spn=0&di=186077679260&pi=0&rn=1&tn=baiduimagedetail&is=2590542053%2C2869261091&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=343974559%2C780603549&os=814818044%2C3433279850&simid=3497481262%2C407975682&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=16&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F204%2F22%2FYMG9CAUWUM15.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frtv_z%26e3Byjfhy_z%26e3Bv54AzdH3F8ccAzdH3Fb8nam8cc1_d_z%26e3Bfip4s&gsm=";
    public static String avatar2 = "http://sc.jb51.net/uploads/allimg/140511/10-140511220Z4S5.jpg";

    public static List<Carousel> makeCarouselList() {

        List<Carousel> carousels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Carousel carousel = new Carousel();
            if (i == 0 && i == 3) {
                carousel.setImg(avatar);
            } else if (i == 1 && i == 4) {
                carousel.setImg(avatar1);
            } else {
                carousel.setImg(avatar2);
            }
            carousels.add(carousel);
        }
        return carousels;
    }
}
