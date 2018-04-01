package com.qmx163.net.Oss;

/**
 * Created by Administrator on 2017/6/20.
 */

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.qmx163.net.NetField;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//**OSS文件处理
//        *Created by LiJing on 2016/4/18.
//        */
public class OssObjectSamples {
    private Context context;
    private OSS oss;
    private String bucket;
    private String objectKey;
    private String uploadFilePath;
    private List<String> filePaths;
    private Map<String, String> fileMaps;

    private PutObjectRequest put;
    private DeleteObjectRequest delete;

    /**
     *
     * @param context
     * @param client
     * @param bucket
     */
    public OssObjectSamples(Context context, OSS client, String bucket) {
        this.context = context;
        this.oss = client;
        this.bucket = bucket;
    }

    /**
     * 单文件上传
     * @param context
     * @param client
     * @param bucket
     * @param objectKey
     * @param uploadFilePath
     */
    public OssObjectSamples(Context context, OSS client, String bucket, String objectKey, String uploadFilePath) {
        this.context = context;
        this.oss = client;
        this.bucket = bucket;
        this.objectKey = objectKey;
        this.uploadFilePath = uploadFilePath;
    }

    /**
     * 多文件上传，返回路径与原文件有对应关系
     * @param context
     * @param client
     * @param bucket
     * @param fileMaps
     */
    public OssObjectSamples(Context context, OSS client, String bucket, Map<String, String> fileMaps) {
        this.context = context;
        this.oss = client;
        this.bucket = bucket;
        this.fileMaps = fileMaps;
    }







    // 从本地文件上传，使用非阻塞的异步接口
    public void asyncPutObjectFromLocalFile(OSSProgressCallback oSSProgressCallback, OSSCompletedCallback oSSCompletedCallback) {
        // 构造上传请求
        put = new PutObjectRequest(bucket, objectKey, uploadFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(oSSProgressCallback);

        OSSAsyncTask task = oss.asyncPutObject(put, oSSCompletedCallback);
    }


    /**
     * 多文件上传
     * @param context
     * @param client
     * @param bucket
     * @param filePaths
     */
    public OssObjectSamples(Context context, OSS client, String bucket, List<String> filePaths) {
        this.context = context;
        this.oss = client;
        this.bucket = bucket;
        this.filePaths = filePaths;
    }

    // 从本地文件上传，使用非阻塞的异步接口
    public void asyncPutObjectFromLocalFiles(final OssUploadCallback callback) {
        final Map<String, List<String>> res = new HashMap<String, List<String>>();
        final List<String> files = new ArrayList<>();
        if (filePaths == null || filePaths.size() < 1) {
            res.clear();
            res.put(NetField.UPLOADSUCCESS, files);
            callback.uploadCallback(res);
            return;
        }

        for (String str : filePaths) {
            final String filePath = "Android/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + UUID.randomUUID() + str.substring(str.lastIndexOf("."));
            // 构造上传请求
            put = new PutObjectRequest(bucket, filePath, str);

            // 异步上传时可以设置进度回调
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                    if (filePaths.size() == (files.size() + 1) && currentSize == totalSize) {
                    }
                }
            });
            oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                    files.add(NetField.DOWNLOADURL + "/" + filePath);
                    if (filePaths.size() == files.size()) {
                        res.clear();
                        res.put(NetField.UPLOADSUCCESS, files);
                        callback.uploadCallback(res);
                    }
                }
                @Override
                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    // 请求异常
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        clientExcepion.printStackTrace();
                    }
                    if (serviceException != null) {
                        // 服务异常
                    }
//                    dialog.dismiss();
                    res.clear();
                    res.put(NetField.UPLOADFAIL, files);
                    callback.uploadCallback(res);
                }
            });
        }
    }
//
//    // 从本地文件上传，使用非阻塞的异步接口
//    public void asyncPutObjectFromLocalFiles(final OssUploadCallbackForSort callback) {
//        final Map<String, Map<String, String>> res = new HashMap<String, Map<String, String>>();
//        final Map<String, String> files = new HashMap<>();
//        if (fileMaps == null || fileMaps.size() < 1) {
//            res.clear();
//            res.put(AppConfig.UPLOADSUCCESS, files);
//            callback.uploadCallbackForSort(res);
//            return;
//        }
//        final Dialog dialog = DialogUtil.getProgressDialog(context, "正在处理，请稍后...");
//        dialog.show();
//        for (final Map.Entry<String, String> entry : fileMaps.entrySet()) {
//            final String filePath = "Android/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + UUID.randomUUID() + entry.getValue().substring(entry.getValue().lastIndexOf("."));
//            // 构造上传请求
//            put = new PutObjectRequest(bucket, filePath, entry.getValue());
//
//            // 异步上传时可以设置进度回调
//            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
//                @Override
//                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                    if (fileMaps.size() == (files.size() + 1) && currentSize == totalSize) {
//                        dialog.dismiss();
//                    }
//                }
//            });
//
//            oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//                @Override
//                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                    files.put(entry.getKey(), AppConfig.DOWNLOADURL + "/" + filePath);
//                    if (fileMaps.size() == files.size()) {
//                        res.clear();
//                        res.put(AppConfig.UPLOADSUCCESS, files);
//                        callback.uploadCallbackForSort(res);
//                    }
//                }
//
//                @Override
//                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                    // 请求异常
//                    if (clientExcepion != null) {
//                        // 本地异常如网络异常等
//                        clientExcepion.printStackTrace();
//                    }
//                    if (serviceException != null) {
//                        // 服务异常
//                    }
//                    dialog.dismiss();
//                    res.clear();
//                    res.put(AppConfig.UPLOADFAIL, files);
//                    callback.uploadCallbackForSort(res);
//                }
//            });
//        }
//    }

    // 从删除文件，使用非阻塞的异步接口
    public void asyncDeleteObjectFromLocalFile(List<String> filesPath) {
        for (String str : filesPath) {
            // 创建删除请求
            delete = new DeleteObjectRequest(bucket, str);
            // 异步删除
            oss.asyncDeleteObject(delete, new OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult>() {
                @Override
                public void onSuccess(DeleteObjectRequest request, DeleteObjectResult result) {

                }

                @Override
                public void onFailure(DeleteObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    // 请求异常
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        clientExcepion.printStackTrace();
                    }
                    if (serviceException != null) {
                        // 服务异常

                    }
                }

            });
        }
    }

}
