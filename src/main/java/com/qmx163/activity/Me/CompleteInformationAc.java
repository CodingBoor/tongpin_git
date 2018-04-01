package com.qmx163.activity.Me;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.qmx163.R;
import com.qmx163.activity.LoginActivity;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.net.NetField;
import com.qmx163.net.Oss.OssObjectSamples;
import com.qmx163.net.Oss.OssSetting;
import com.qmx163.util.Custom.ImageUtil;
import com.qmx163.util.Custom.PhotoUtil;
import com.qmx163.util.Custom.PhotographDialog;
import com.qmx163.util.PrefUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 注册补充信息
 */
public class CompleteInformationAc extends BaseAc implements PhotographDialog.OnBtnClickLister {

    // 标题栏
    @BindView(R.id.iamgeleft)
    ImageView imageleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.Right_img)
    ImageView imageRight;

    // 页面基本信息
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.activity_complete_information)
    LinearLayout activityCompleteInformation;
    PhotographDialog photographDialog;
    @BindView(R.id.Lay_avatar)
    LinearLayout LayAvatar;
    @BindView(R.id.off)
    ImageView off;
    @BindView(R.id.loginGo)
    com.rey.material.widget.TextView loginGo;
    @BindView(R.id.et_name)
    EditText etName;
    boolean isOff = false;// false  0学生  true 1家长
    int isStu = 0;
    String phone;
    String Password;
    File tempFile;
    Bitmap bitmap;
//    String avatarUrl = PrefUtils.getString(this, Constants.USER_IMG, "");
    String avatarUrl = "";
    String userName = "";
    private static final int REQUEST_CROP_PHOTO = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_information);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        phone = getIntent().getStringExtra("phone");
        Password = getIntent().getStringExtra("Password");
        tvLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("填写姓名");

        ibtnLeft.setVisibility(View.VISIBLE);
        imageleft.setImageDrawable(getResources().getDrawable(R.mipmap.back));
        tvLeft.setVisibility(View.GONE);
        imageRight.setVisibility(View.GONE);

    }

    @OnClick({R.id.tv_left, R.id.Lay_avatar, R.id.off, R.id.loginGo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.Lay_avatar:
                requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
                    @Override
                    public void run() {
                        if (photographDialog == null) {
                            photographDialog = new PhotographDialog(CompleteInformationAc.this);
                            photographDialog.setOnBtnClickLister(CompleteInformationAc.this);
                        }
                        photographDialog.show();
                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });
                break;
            case R.id.off:
                if (isOff) {
                    isStu = 0;
                    Drawable drawable = getResources().getDrawable(R.mipmap.off);
                    off.setBackgroundDrawable(drawable);
                    isOff = false;
                } else {
                    isStu = 1;
                    Drawable drawable = getResources().getDrawable(R.mipmap.on);
                    off.setBackgroundDrawable(drawable);
                    isOff = true;
                }
                break;
            case R.id.loginGo:
                userName = etName.getText().toString();
                if (userName.equals("")){
                    showToas("请输入姓名");
                    return;
                }
                if (avatarUrl == ""){
                    showToas("请您选择头像");
                    return;
                }

                showProgressDialog();
//                if(tempFile!=null) {
//                    dataSource.add(tempFile.getPath());
//                    new OssObjectSamples(this, oss, NetField.BUCKET, dataSource)
//                            .asyncPutObjectFromLocalFiles(new OssUploadCallback() {
//                                @Override
//                                public void uploadCallback(Map<String, List<String>> result) {
//                                    Observable.just(result)
//                                            .filter(new Func1<Map<String, List<String>>, Boolean>() {
//                                                @Override
//                                                public Boolean call(Map<String, List<String>> stringListMap) {
//                                                    for (Map.Entry<String, List<String>> entry : stringListMap.entrySet()) {
//                                                        if (entry.getKey().equals(NetField.UPLOADFAIL)) {
//                                                            new OssObjectSamples(CompleteInformationAc.this, oss, NetField.BUCKET).asyncDeleteObjectFromLocalFile(entry.getValue());
//                                                            return false;
//                                                        }
//                                                    }
//                                                    return true;
//                                                }
//                                            })
//                                            .map(new Func1<Map<String,List<String>>, Object>() {
//                                                @Override
//                                                public Object call(Map<String, List<String>> stringListMap) {
//                                                    return stringListMap.get(NetField.UPLOADSUCCESS);
//                                                }
//                                            }).subscribe(new ApiCallback<Object>(){
//                                                    @Override
//                                                    public void onSuccess(Object model) {
//
//                                                    }
//                                                    @Override
//                                                    public void onFailure(String msg) {
//
//                                                    }
//                                                    @Override
//                                                    public void onFinish() {
//
//                                                    }
//                                    });
//                                }
//                            });
//                }else{
                Regist();
//                }
                break;
        }
    }

    public void Regist() {
        addSubscription(apiStores.Register(phone, Password,userName, isStu + "", avatarUrl), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                RegisteredEn.DataBean user = model.getData();
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_ID, user.getId());
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_NAME,user.getMemberName());
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_TYPE, user.getType()+"");
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_PASSWORD, Password);
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_IMG, user.getImg());
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_TOKEN, user.getToken());



                PrefUtils.setString(CompleteInformationAc.this, Constants.PARENT_ID, "");//测试家长id  b368c9f05ba711e7905400163e323696
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_LOGIN_ID, model.getData().getLoginId());
                PrefUtils.setInt(CompleteInformationAc.this, Constants.USER_SCORE, model.getData().getScore());
                PrefUtils.setString(CompleteInformationAc.this, Constants.USER_SEX, String.valueOf(model.getData().getSex()));
                PrefUtils.saveObjectToShare(CompleteInformationAc.this, "data", model.getData());


                String msg = model.getMessage();
                startAc(LoginActivity.class);
                showToas(msg);
            }

            @Override
            public void onFailure(String msg) {
                dismissProgressDialog();
                showToas(msg);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

        });
    }

    @Override
    public void shooting() {
        PhotoUtil.shoot(this);
    }

    @Override
    public void selection() {
        PhotoUtil.select(this);
    }

    @Override
    public void cancle() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoUtil.PHOTO_REQUEST_CAMERA) { //拍照
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            PhotoUtil.PHOTO_FILE_NAME);
//                    PhotoUtil.crop(this, Uri.fromFile(tempFile));
                    gotoClipActivity(Uri.fromFile(tempFile));
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
                    avatar.setImageBitmap(bitmap);
                    tempFile = new File(this.getFilesDir().getPath(), PhotoUtil.PHOTO_FILE_NAME);
                    //保存到本地
                    ImageUtil.saveImage(bitmap, tempFile.getPath());
                    upImg();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (requestCode == REQUEST_CROP_PHOTO) {

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
                avatar.setImageBitmap(bitMap);
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
                avatarUrl = NetField.DOWNLOADURL + "/" + filePath;
                Log.i("avatarUrl", avatarUrl);

//                ProtocolBill.createGroup(NewGroupActivity.this, RequestCodeSet.CREATE_GROUP, SPUtil.getString(Constants.USER_ID), nameStr, "0", groupInfoStr, avatar, members.toString(), "", lnglat, setId, "");
            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Log.i("11", putObjectRequest.getUploadFilePath());
                showToas("上传头像失败");
            }
        });
    }


}
