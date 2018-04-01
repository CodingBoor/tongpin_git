package com.qmx163.util;

import com.qmx163.data.bean.Mebean.StudyComment;

import java.util.ArrayList;
import java.util.List;

/**
 * 制造测试数据
 * Created by lzp on 2016/10/12.
 */
public class DataUtil {
    public final static int SHOOT_ID = 1;
    public final static int SELECT_ID = 2;
    public final static int CANCLE_ID = 3;

    public final static int FIREND = 4;
    public final static int MINE_ATT = 5;
    public final static int ATT_MINE = 6;
    public final static int ORGANIZATION = 7;
    public final static int PERSONBG = 8;
    public final static int GROUP = 9;

    public final static String AVATAR="http://www.qq1234.org/uploads/allimg/150402/1504544K2-8.jpg";






    public static List<StudyComment> commentListSun(){
        String content1 = "comment child";
        String name = "我是好人";
        List<StudyComment> studyComments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudyComment studyComment = new StudyComment();
            studyComment.setMemberName(name);
            studyComment.setContent(content1 + i);
            studyComments.add(i,studyComment);
        }
        return studyComments;
    }


    public static List<StudyComment> commentList(){
        List<StudyComment> studyComments = new ArrayList<>();
        String content1 = "parent comment";
        String name = "我是好人";

        for (int i = 0; i < 5; i++) {
            StudyComment studyComment = new StudyComment();
            studyComment.setMemberName(name);
            studyComment.setContent(content1 + i);
            studyComment.setChildComments(commentListSun());
            studyComments.add(i,studyComment);
            }
        return studyComments;
    }

//    //制作朋友圈测试数据
//    public static List<Aynamic> makeAynamicData() {
//        List<Aynamic> aynamics = new ArrayList<>();
//        String content1 = "阿斯兰的空间哈老师看见的拉开见识到了卡时间的垃圾是来得及爱上了肯德基阿拉斯加的拉升就对啦坚实的垃圾堆里看见阿克苏接电话卡酒红色的卡号是打开就阿訇死快点哈萨克的好看拉会计师都拉进来的";
//        String content2 = "阿斯兰的空间哈老师看见的";
//        String content;
//        for (int i = 0; i < 15; i++) {
//            int n = (int) (Math.random() * 6 + 1);
//            List<String> images = new ArrayList<>();
//            for (int j = 0; j < n; j++) {
//                images.add("http://attachments.gfan.com/forum/201507/02/162758lj7fdpwk4pjhl2bk.jpg");
//            }
//            List<Comment> comments = new ArrayList<>();
//            for (int k = 0; k < n; k++) {
//                String toUser = "";
//                if (k % 2 == 1) {
//                    toUser = "小鸡仔";
//                }
//                comments.add(new Comment("刘洋", toUser, "深刻的积分换款式的福克斯的恢复可舒服围殴我如我诶若无人"));
//            }
//            content = i % 2 == 0 ? content1 : content2;
//            aynamics.add(new Aynamic("http://img3.imgtn.bdimg.com/it/u=995974944,1899172305&fm=21&gp=0.jpg", "张闯", "客服总监", "上海曲梦信息科技有限公司", content, images, 21, 3, comments));
//        }
//        return aynamics;
//    }
//
//
//
//    public static List<Option> makeMapPopupData(Context context) {
//        List<Option> options = new ArrayList<>();
//        options.add(new Option(0, "全部", ContextCompat.getColor(context, R.color.color_fd8709)));
//        options.add(new Option(1, "好友", ContextCompat.getColor(context, R.color.white)));
//        options.add(new Option(2, "关注", ContextCompat.getColor(context, R.color.white)));
//        options.add(new Option(3, "粉丝", ContextCompat.getColor(context, R.color.white)));
//        options.add(new Option(4, "机构", ContextCompat.getColor(context, R.color.white)));
//        return options;
//    }
//
//
//    //朋友圈 popupwindow 数据
//    public static List<Option> makeCircleBGData(Context context) {
//
//        List<Option> options = new ArrayList<>();
//        options.add(new Option(PERSONBG, "更换背景图片", ContextCompat.getColor(context, R.color.white)));
//        return options;
//    }
//
//    public final static int ALL = 0;
//    public final static int FRIEND = 1;
//    public final static int MEATTENTION = 2;
//    public final static int ATTENTIONME = 3;
//    public final static int ORGANIZATION1 = 4;
//
//
//    public static ArrayList<Option> makeWhoCanSeeMeData() {
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option(FRIEND, "好友", "仅好友可见", true));
//        options.add(new Option(MEATTENTION, "我关注的人", "仅我关注的人可见", true));
//        options.add(new Option(ATTENTIONME, "关注我的人", "仅关注我的人可见", true));
//        options.add(new Option(ORGANIZATION1, "机构", "", true));
//        return options;
//    }
//
//
//
//
//    public static List<Option> makeMemuData(){
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option("附近",R.mipmap.near));
//        options.add(new Option("活动·集结",R.mipmap.exercise));
//        options.add(new Option("产调·征信",R.mipmap.credit));
//        options.add(new Option("行业招聘",R.mipmap.recruit));
//        options.add(new Option("理财",R.mipmap.money));
//        options.add(new Option("投资",R.mipmap.invest));
//        options.add(new Option("资源管理",R.mipmap.manage));
//        options.add(new Option("资产交易",R.mipmap.deal));
//        return options;
//    }
//
//    public static List<Option> makeMineMemuData(){
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option("账户",R.mipmap.account));
//        options.add(new Option("认证",R.mipmap.approve));
//        options.add(new Option("我的发布",R.mipmap.release));
////        options.add(new Option("二维码名片",R.mipmap.erweima_card));
//        options.add(new Option("我的参与",R.mipmap.dianzi_card));
//        options.add(new Option("我的收藏",R.mipmap.collect));
//        options.add(new Option("分享名片",R.mipmap.share_card));
//        options.add(new Option("位置管理",R.mipmap.location_mannager));
//        options.add(new Option("设置",R.mipmap.setting));
//        return options;
//    }
//
//    public static List<Option> makeMineReleaseData(){
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option("朋友圈",R.mipmap.friends_circle));
//        options.add(new Option("匿名八卦",R.mipmap.gossip));
//        options.add(new Option("产调征信",R.mipmap.credit));
//        options.add(new Option("活动·集结",R.mipmap.exercise));
//        options.add(new Option("理财",R.mipmap.money));
//        options.add(new Option("投资",R.mipmap.invest));
//        options.add(new Option("资源管理",R.mipmap.manage));
//        options.add(new Option("资产交易",R.mipmap.deal));
//        options.add(new Option("行业招聘",R.mipmap.zhaoping));
//        return options;
//    }
//
//    public static List<ForumTitle> makeTitleData(){
//        ArrayList<ForumTitle> options = new ArrayList<>();
//        options.add(new ForumTitle("职业经验分享",true));
//        options.add(new ForumTitle("专栏文章",false));
//        options.add(new ForumTitle("观点话题",false));
//        options.add(new ForumTitle("行业新闻动态",false));
//        return options;
//    }
//
//
//    public static List<User> makeHomePersonData(){
//        ArrayList<User> options = new ArrayList<>();
//        options.add(new User("http://image101.360doc.com/DownloadImg/2016/10/2719/83195451_16.jpg","张闯","总监"));
//        options.add(new User("http://fun.youth.cn/yl24xs/201610/W020161013376651202720.png","尼古拉斯","总监"));
//        options.add(new User("http://image101.360doc.com/DownloadImg/2016/10/2719/83195451_16.jpg","贝克汉姆","副总监"));
//        options.add(new User("http://fun.youth.cn/yl24xs/201610/W020161013376651202720.png","张闯","总监"));
//        options.add(new User("http://image101.360doc.com/DownloadImg/2016/10/2719/83195451_16.jpg","赵四","总监"));
//        options.add(new User("http://fun.youth.cn/yl24xs/201610/W020161013376651202720.png","张闯","总监"));
//        return options;
//    }
//
//    public static ArrayList<CityModel> makeCityData(int ...length){
//        String city[]={"上海","北京","郑州市","南阳市","新乡市","安阳市","洛阳市","信阳市","平顶山市","周口市","商丘市","开封市","焦作市","驻马店市","濮阳市","三门峡市","漯河市","许昌市","鹤壁市","济源市","东莞市","广州市","中山市","深圳市","惠州市","江门市","珠海市","汕头市","佛山市","湛江市","河源市","肇庆市","清远市","潮州市","韶关市","揭阳市","阳江市","梅州市","云浮市","茂名市","汕尾市"};
//        ArrayList<CityModel> citys = new ArrayList<>();
//        for (int i=0;i<(length.length==0?city.length:length[0]);i++) {
//            citys.add(new CityModel(city[i]));
//        }
//        return citys;
//    }
//
//    public static List<Maidian> makeMaidianData(){
//        ArrayList<Maidian> maidian = new ArrayList<>();
//        for (int i=0;i<15;i++){
//            maidian.add(new Maidian("上晒是挥洒东方还是来打开房间后开始的弗兰克",true));
//        }
//        return maidian;
//    }
//
//    public static List<Adjustment> makeAdjustmentData(){
//        ArrayList<Adjustment> adjustments = new ArrayList<>();
//        for (int i=0;i<15;i++){
//            List<String> images=new ArrayList<>();
//            images.add("http://img.ivsky.com/img/bizhi/slides/201611/03/november-012.jpg");
//            images.add("http://e.hiphotos.baidu.com/image/h%3D200/sign=4766e2adfcdcd100d29cff21428a47be/0b55b319ebc4b7453f08ffb6cbfc1e178b8215f5.jpg");
//            images.add("http://img.ivsky.com/img/bizhi/slides/201611/03/november-012.jpg");
//            images.add("http://e.hiphotos.baidu.com/image/h%3D200/sign=4766e2adfcdcd100d29cff21428a47be/0b55b319ebc4b7453f08ffb6cbfc1e178b8215f5.jpg");
//            List<AdjunstmentComment> comments=new ArrayList<>();
//            for (int j=0;j<3;j++){
//                List<Comment> commentss = new ArrayList<>();
//                for (int k = 0; k < 5; k++) {
//                    String toUser = "";
//                    if (k % 2 == 1) {
//
//                        toUser = "小鸡仔";
//                    }
//                    commentss.add(new Comment("刘洋", toUser, "深刻的积分换款式的福克斯的恢复可舒服围殴我如我诶若无人"));
//                }
//                comments.add(new AdjunstmentComment("http://img.ivsky.com/img/bizhi/slides/201611/03/november-012.jpg","尼古拉啦","上海曲梦信息科技有限公司","总监","i时代发挥可是觉得好疯狂","3",commentss));
//            }
//            adjustments.add(new Adjustment("http://v1.qzone.cc/avatar/201508/03/17/47/55bf38c22f3b1735.jpg%21200x200.jpg","张闯","上海曲梦信息科技有限公司","总监","2016-02-01",i%2+"","上海市","长宁区","时代科技孵化十大科技付好款了收到回复看来深刻搭街坊会看上的",i,images,i%2,comments));
//        }
//        return adjustments;
//    }
//
//
//
//
//
//
//    public static List<Forum> makeForumData(){
//        ArrayList<Forum> forums = new ArrayList<>();
//        for (int i=0;i<10;i++){
//        List<String> paths = new ArrayList<>();
//            for (int j=0;j<4;j++){
//                paths.add("http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg");
//            }
//            forums.add(new Forum("中国还有就骂","省略的快放假了上岛咖啡吉林省",paths,"http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg","张闯","上海曲梦信息科技有限公司","总监","1","20",i%2+""));
//        }
//        return forums;
//    }
//
//    public static List<Trading> makeTradingData(){
//        ArrayList<Trading> trading = new ArrayList<>();
//        for (int i=0;i<10;i++){
//            trading.add(new Trading("理财","￥12354541","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg","上海曲梦信息科技有限公司","张闯","总监","30","50",i%2+""));
//        }
//        return trading;
//    }
//
//    public static List<Packet> makePacketData(){
//        ArrayList<Packet> packets = new ArrayList<>();
//        packets.add(new Packet("添加分组"));
//        for (int i=0;i<10;i++){
//            packets.add(new Packet("同事"));
//        }
//        return packets;
//    }
//
//    public static ArrayList<User> makeSelectUserData(){
//        ArrayList<User> users = new ArrayList<>();
//        users.add( new User("1","张闯","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("2","sdf","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("3","尼龟舒服","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("4","我就像大富豪","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("5","胃口进入","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("6","胜多负少","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("7","安顺","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("8","3的","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("9","科技刻录机","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("10","模块机","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("11","款的","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("12","编号","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        users.add( new User("13","的脸孔","http://pic34.nipic.com/20131028/2455348_171218804000_2.jpg",false));
//        return users;
//    }
//
//    public static List<Option> makeAddFriendsDatas(){
//        ArrayList<Option> options = new ArrayList<>();
//        options.add(new Option("手机联系人",R.mipmap.phone));
//        options.add(new Option("微信朋友圈",R.mipmap.wx_circle_1));
//        options.add(new Option("微信好友",R.mipmap.wx_1));
////        options.add(new Option("QQ空间",R.mipmap.qq_zone));
////        options.add(new Option("QQ好友",R.mipmap.qq));
//        return options;
//    }
//
//    public static List<Integral> makeIntegralDatas(){
//        ArrayList<Integral> options = new ArrayList<>();
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        options.add(new Integral("手机联系人","2016-01-12 12:20:30","-100"));
//        return options;
//    }
//    //检测是否完善个人信息
//    public static boolean checkIsPerfectInformation(Context context){
//        if (TextUtils.isEmpty(SPUtil.getString(Constants.ACCOUNT_TYPE))){
////            context.startActivity(new Intent(context,PerfectInformationActivity.class));
//            return false;
//        }
//        return true;
//    }
//
//
//    //检测是否认证
//    public static boolean checksApproval(Context context){
//        if (TextUtils.equals("0",SPUtil.getString(Constants.ISAPPROVAL))){
////            context.startActivity(new Intent(context,PerfectInformationActivity.class));
//            return false;
//        }
//        return true;
//    }
//
//    public static void showSetting(Context context) {
//        new AlertDialog.Builder(context)
//                .setTitle("提示")
//                .setMessage("请先到【我】内完成身份认证后再进行操作")
//                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).create().show();
//    }
//
//    public static String distanceTransformation(int distance){
//        StringBuilder distanceStr=new StringBuilder();
//        if(distance<1000){
//             distanceStr.append(distance);
//        }else{
//            distanceStr.append(distance/1000).append(".");
//            distanceStr .append(distance % 1000/100).append("K");
//        }
//        return distanceStr.append("M").toString();
//    }


}

