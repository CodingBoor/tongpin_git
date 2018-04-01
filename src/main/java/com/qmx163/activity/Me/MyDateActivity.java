package com.qmx163.activity.Me;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.CardBean;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.net.NetField;
import com.qmx163.net.Oss.OssObjectSamples;
import com.qmx163.net.Oss.OssSetting;
import com.qmx163.util.Custom.ImageUtil;
import com.qmx163.util.Custom.PhotoUtil;
import com.qmx163.util.Custom.PhotographDialog;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.qmx163.R.id.civ_head;
import static com.qmx163.R.id.rl_birthday;
import static com.qmx163.R.id.rl_head;
import static com.qmx163.R.id.tv_birthday;
import static com.qmx163.R.id.tv_name;
import static com.qmx163.R.id.tv_phone;
import static com.qmx163.R.id.tv_sex;

/**
 * Created by likai on 2017/7/1.
 * 我的资料界面
 */
public class MyDateActivity extends BaseAc implements View.OnClickListener, PhotographDialog.OnBtnClickLister {

    @BindView(R.id.iamgeleft)
    ImageView iamgeleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.Right_img)
    ImageView RightImg;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(civ_head)
    CircleImageView mCivHead;
    @BindView(tv_name)
    TextView mTvName;
    @BindView(R.id.rl_name)
    RelativeLayout mRlName;
    @BindView(tv_sex)
    TextView mTvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout mRlSex;
    @BindView(tv_birthday)
    TextView mTvBirthday;
    @BindView(rl_birthday)
    RelativeLayout mRlBirthday;
    @BindView(tv_phone)
    TextView mTvPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout mRlPhone;
    @BindView(R.id.tv_name_left)
    TextView mTvNameLeft;
    @BindView(R.id.tv_sex_left)
    TextView mTvSexLeft;
    @BindView(R.id.tv_birthday_left)
    TextView mTvBirthdayLeft;
    @BindView(R.id.tv_phone_left)
    TextView mTvPhoneLeft;
    @BindView(rl_head)
    RelativeLayout mRlHead;
    private PhotographDialog mPhotographDialog;
    private final static int REQUEST_CODE = 10;
    String avatar;

    TimePickerView pvTime;
    OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_date);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        ibtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.my_resouse);
        RightImg.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.data_save);
        tvRight.setTextColor(Color.BLACK);
        tvRight.setOnClickListener(this);
        mRlName.setOnClickListener(this);
        mRlPhone.setOnClickListener(this);
        mRlBirthday.setOnClickListener(this);
        mRlSex.setOnClickListener(this);
        mRlHead.setOnClickListener(this);
        avatar = PrefUtils.getString(this, Constants.USER_IMG, "");
        getDate();
        getCardData();
        initTimePicker();
        initCustomOptionPicker();
    }

    private void getCardData() {
//        for (int i = 0; i < 5; i++) {
        cardItem.add(new CardBean(0, "男"));
        cardItem.add(new CardBean(0, "女"));

//        for (int i = 0; i < cardItem.size(); i++) {
//            if (cardItem.get(i).getCardNo().length() > 6) {
//                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
//                cardItem.get(i).setCardNo(str_item);
//            }
//        }
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                mTvSex.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                        tvAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCardData();
                                pvCustomOptions.setPicker(cardItem);
                            }
                        });

                    }
                })
                .isDialog(true)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据

    }


    //选择日期
    private void initTimePicker() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);    //获取年
        int month = c.get(Calendar.MONTH);   //获取月份，0表示1月份
        int day = c.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1960, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                mTvBirthday.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MyDataItemActivity.class);
        switch (v.getId()) {
            case R.id.ibtn_left:
                finish();
                break;
            case rl_head:  //点击修改头像
                requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
                    @Override
                    public void run() {
                        requestPermission(2, Manifest.permission.READ_EXTERNAL_STORAGE, new Runnable() {
                            @Override
                            public void run() {
                                if (mPhotographDialog == null) {
                                    mPhotographDialog = new PhotographDialog(MyDateActivity.this);
                                    mPhotographDialog.setOnBtnClickLister(MyDateActivity.this);
                                }
                                mPhotographDialog.show();
                            }
                        }, new Runnable() {
                            @Override
                            public void run() {
                                showToas(getResources().getString(R.string.no_permission_to_use_the_read_external));
                            }
                        });
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });
                break;
            //以下4个case 都是跳转同一个界面， 传递不同的值去设置跳转activity的标题，和edittext内容。
            case R.id.rl_sex:
//                intent.putExtra("str", mTvSex.getText());
//                intent.putExtra("title", mTvSexLeft.getText());
//                startAc(intent,REQUEST_CODE);

                if (pvCustomOptions != null) {
                    pvCustomOptions.show(); //弹出自定义条件选择器
                }


//                CheckSexDialog checkSexDialog = new CheckSexDialog();
//                checkSexDialog.show(getFragmentManager(), SccDateDialog.TAG);
//                checkSexDialog.setOnDialogClickListener(new CheckSexDialog.onDialogClickListener() {
//                    @Override
//                    public void onClickView(String mStringMonth) {
//                        mTvSex.setText(mStringMonth);
//                    }
//                });
                break;
            case rl_birthday:

                if (pvTime != null) {
                    pvTime.show();

                }
//                SccDateDialog sccDateDialog = new SccDateDialog();
//                sccDateDialog.show(getFragmentManager(), SccDateDialog.TAG);
//                sccDateDialog.setOnDialogClickListener(new SccDateDialog.onDialogClickListener() {
//                    @Override
//                    public void onClickView(String mStringYear, String mStringMonth, String mStringDay) {
//                        mTvBirthday.setText(mStringYear + "-" + mStringMonth + "-" + mStringDay);
//                    }
//                });
                break;
            case R.id.rl_name:
                intent.putExtra("str", mTvName.getText());
                intent.putExtra("title", mTvNameLeft.getText());
                startAc(intent, REQUEST_CODE);
//                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_right:
                String tvName = mTvName.getText().toString();
                String tvSex = mTvSex.getText().toString();
                String tvBirthday = mTvBirthday.getText().toString();
                String tvSexs;
                if (tvSex.equals("男")) {
                    tvSexs = "1";
                } else {
                    tvSexs = "0";
                }
                Sava(tvName, tvSexs, tvBirthday);
                break;
        }

    }

    File tempFile;
    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (requestCode == MyDataItemActivity.RESULT_CODE) {
                try {
                    Bundle bundle = data.getExtras();
                    String str = bundle.getString("back");
                    String title = bundle.getString("title");
                    switch (title) {
                        case "姓名":
                            mTvName.setText(str);
                            break;
                        case "性别":
                            mTvSex.setText(str);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (resultCode == RESULT_OK) {
            if (requestCode == PhotoUtil.PHOTO_REQUEST_CAMERA) { //拍照
                if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            PhotoUtil.PHOTO_FILE_NAME);
//                    PhotoUtil.crop(this, Uri.fromFile(tempFile));
                    gotoClipActivity(Uri.fromFile(tempFile));


//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    tempFile = new File(ImageUtil.getImageAbsolutePath(UserDetailActivity.this,data.getData()));
//                    PhotoUtil.crop(this, Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(this, "未检测到sd卡", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == PhotoUtil.PHOTO_REQUEST_GALLERY) { //选取
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    gotoClipActivity(uri);

//                    PhotoUtil.crop(this, uri);
                }
            } else if (requestCode == PhotoUtil.PHOTO_REQUEST_CUT) {//裁剪
                try {
                    bitmap = data.getParcelableExtra("data");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bytes = baos.toByteArray();

                    Glide.with(this)
                            .load(bytes)
                            .into(mCivHead);
//                    mCivHead.setImageBitmap(bitmap);
                    tempFile = new File(this.getFilesDir().getPath(), PhotoUtil.PHOTO_FILE_NAME);

                    //保存到本地
                    ImageUtil.saveImage(bitmap, tempFile.getPath());
                    upImg();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CROP_PHOTO) {

                final Uri uri = data.getData();
//                if (uri == null) {
//                    return;
//                }
//                String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
//                Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//                mCivHead.setImageBitmap(bitMap);
////                Glide.with(this)
////                        .load(bitMap)
////                        .into(mCivHead);
////                tempFile = new File(this.getFilesDir().getPath(), PhotoUtil.PHOTO_FILE_NAME);
//                tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
//                        System.currentTimeMillis() + ".jpg");//将要保存图片的路径
//
//                //保存到本地
//                ImageUtil.saveImage(bitmap, tempFile.getPath());
////                    ImageUtil.saveBitmap(bitmap,"1112121211");
//                upImg();
                if (uri == null) {
                    return;
                }
                String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                mCivHead.setImageBitmap(bitMap);
                saveBitmapFile(bitMap);
                upImg();
            }
        }
    }


    //将bitmap转换成文件
    public void saveBitmapFile(Bitmap bitmap) {
//        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
        tempFile = new File(this.getFilesDir().getPath(),
                System.currentTimeMillis() + ".jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    public void shooting() {
        PhotoUtil.shoot(this);
    }

    @Override
    public void selection() {
        requestPermission(2, Manifest.permission.WRITE_EXTERNAL_STORAGE, new Runnable() {
            @Override
            public void run() {
                PhotoUtil.select(MyDateActivity.this);
            }
        }, new Runnable() {
            @Override
            public void run() {
                ToastUtil.toastShortShow(MyDateActivity.this, R.string.no_permission_to_read_file);
            }
        });
    }

    @Override
    public void cancle() {

    }

    /**
     * 保存
     */
    private void Sava(final String TvName, final String sex, final String birthday) {
        if (avatar == null) {
            showToas("请设置头像");
            return;
        }
        showProgressDialog();
        addSubscription(apiStores.SavePer(PrefUtils.getString(this, Constants.USER_ID, ""), TvName, sex, birthday, avatar), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if ("200".equals(model.getCode())) {
                    PrefUtils.setString(MyDateActivity.this, Constants.USER_IMG, avatar);
                    PrefUtils.setString(MyDateActivity.this, Constants.USER_BIRTHDAY, birthday);
                    PrefUtils.setString(MyDateActivity.this, Constants.USER_NAME, TvName);
                    PrefUtils.setString(MyDateActivity.this, Constants.USER_SEX, sex);
                    showToas(model.getMessage());
                    setResult(RESULT_OK, new Intent());
                    MyDateActivity.this.finish();
                } else {
                    showToas(model.getMessage());
                }
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    public void upImg() {

        if (tempFile == null) {
            showToas("头像获取失败。");
            return;
        }

        OSS oss = OssSetting.getOss(this);
        String str = tempFile.getAbsolutePath();
        final String filePath = "Android/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + UUID.randomUUID() + str.substring(str.lastIndexOf("."));
        new OssObjectSamples(this, oss, NetField.BUCKET, filePath, str).asyncPutObjectFromLocalFile(new OSSProgressCallback<PutObjectRequest>() {

            @Override
            public void onProgress(PutObjectRequest putObjectRequest, long l, long l1) {

            }
        }, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {

            @Override
            public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putOjectResult) {
                avatar = NetField.DOWNLOADURL + "/" + filePath;
                Log.i("avatar", avatar);

//                ProtocolBill.createGroup(NewGroupActivity.this, RequestCodeSet.CREATE_GROUP, SPUtil.getString(Constants.USER_ID), nameStr, "0", groupInfoStr, avatar, members.toString(), "", lnglat, setId, "");
            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Log.i("11", putObjectRequest.getUploadFilePath());
                showToas("上传头像失败");
            }
        });
    }


    /**
     * 获取数据
     */
    private void getDate() {
//        Glide.with(MyDateActivity.this).load(PrefUtils.getString(MyDateActivity.this, Constants.USER_IMG, "")).centerCrop().error(R.mipmap.flunk).crossFade().into((ImageView) mCivHead);

        RegisteredEn.DataBean data = (RegisteredEn.DataBean) getIntent().getSerializableExtra("dataa");
        Glide.with(MyDateActivity.this).load(data.getImg()).centerCrop().error(R.mipmap.flunk).crossFade().into((ImageView) mCivHead);
        mTvName.setText(data.getMemberName());
        try {
            int sex = data.getSex();
            if (sex == 0)
                mTvSex.setText("女");
            else if (sex == 1) {
                mTvSex.setText("男");
            } else {
                mTvSex.setText("未设置");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mTvSex.setText("未设置");
        }
        if (data.getBirthday() == null || TextUtils.equals(data.getBirthday(), "")) {
            mTvBirthday.setText("未设置");
        } else {
            mTvBirthday.setText(data.getBirthday());
        }
        mTvPhone.setText(data.getLoginId());


//        addSubscription(apiStores.SelectPer(PrefUtils.getString(this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
//            @Override
//            public void onSuccess(RegisteredEn model) {
//                if ("200".equals(model.getCode())) {
//                    RegisteredEn.DataBean data = model.getData();
//                    Glide.with(MyDateActivity.this).load(data.getImg()).centerCrop().error(R.mipmap.flunk).crossFade().into((ImageView) mCivHead);
//                    mTvName.setText(data.getMemberName());
//                    try {
//                        int sex = data.getSex();
//                        if (sex == 0)
//                            mTvSex.setText("女");
//                        else if (sex == 1){
//                            mTvSex.setText("男");
//                        }else {
//                            mTvSex.setText("未设置");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        mTvSex.setText("未设置");
//                    }
//                    if (data.getBirthday()==null||TextUtils.equals(data.getBirthday(),"")){
//                        mTvBirthday.setText("未设置");
//                    }else {
//                    mTvBirthday.setText(data.getBirthday());
//                    }
//                    mTvPhone.setText(data.getLoginId());
//                }
//            }
//
//            @Override
//            public void onFailure(String code) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }
}
