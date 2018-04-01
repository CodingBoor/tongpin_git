package com.qmx163.net.Oss;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.qmx163.net.NetField;

import static com.qmx163.net.NetField.ENDPOINT;

/**
 * 获取默认配置的oss
 * Created by lzp on 2017/1/11.
 */
public class OssSetting {


    public static OSS getOss(Context context) {
        //阿里OSS begin
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(NetField.ACCESSKEYID, NetField.ACCESSKEYSECRET);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        return new OSSClient(context, ENDPOINT, credentialProvider, conf);
    }

//    /**
//     * 删除上传的图片
//     *
//     * @param context
//     * @param path
//     */
//    public static void deleteImage(Context context, String path) {
//        DeleteObjectRequest delete = new DeleteObjectRequest(ENDPOINT.BUCKET, path);
//        OSS oss = getOss(context);
//        try {
//            oss.deleteObject(delete);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 删除上传的图片
//     *
//     * @param context
//     * @param paths
//     */
//    public static void deleteImages(Context context, List<String> paths) {
//        OSS oss = getOss(context);
//        try {
//            for (String path : paths) {
//                DeleteObjectRequest delete = new DeleteObjectRequest(ENDPOINT.BUCKET, path);
//                oss.deleteObject(delete);
//            }
//        } catch (ClientException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//    }


//    /**
//     * 多文件上传
//     *
//     * @param context
//     * @param filePaths
//     * @return
//     * @throws ClientException
//     * @throws ServiceException
//     */
//    public static Observable<List<String>> upImages(Context context, List<String> filePaths) {
//        Observable<List<String>> observable = null;
//        List<String> newList = new ArrayList<>();
//        OSS oss = getOss(context);
//        try {
//            for (String path : filePaths) {
//                if (path.indexOf("http:") != -1) {
//                    newList.add(path);
//                    break;
//                }
//                if (!(new File(path).exists())) {
//                    break;
//                }
//                String newPath = "Android/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + UUID.randomUUID() + path.substring(path.lastIndexOf("."));
//                // 构造上传请求
//                PutObjectRequest put = new PutObjectRequest(ENDPOINT.BUCKET, newPath, path);
//                oss.putObject(put);
//                newList.add(ENDPOINT.DOWNLOADURL + File.separator + newPath);
//            }
//            return Observable.just(newList);
//        } catch (ClientException e) {
//            observable = Observable.create(sub -> {
//                sub.onError(e);
//                e.printStackTrace();
//            });
//            return observable;
//        } catch (ServiceException e) {
//            observable = Observable.create(sub -> {
//                sub.onError(e);
//                e.printStackTrace();
//            });
//            return observable;
//        }
//    }
//
//    /**
//     * 单文件上传
//     *
//     * @param context
//     * @param filePaths
//     * @return
//     * @throws
//     * @throws
//     */
//    public static Observable<String> upImage(Context context, String filePaths) {
//        if (filePaths.indexOf("http:") != -1) {
//            return Observable.just(filePaths);
//        }
//        if (!(new File(filePaths).exists())) {
//            return Observable.create(sub -> {
//                sub.onError(new Exception("Not Found File is -->" + filePaths));
//            });
//        }
//        OSS oss = getOss(context);
//        String newPath = "Android/" + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + UUID.randomUUID() + filePaths.substring(filePaths.lastIndexOf("."));
//        // 构造上传请求SimpleDateFormat
//        PutObjectRequest put = new PutObjectRequest(NetField.BUCKET, newPath, filePaths);
//        try {
//            oss.putObject(put);
//            return Observable.just(NetField.DOWNLOADURL + File.separator + newPath);
//        } catch (ClientException e) {
//            return Observable.create(sub -> {
//                sub.onError(e);
//                e.printStackTrace();
//            });
//        } catch (ServiceException e) {
//            return Observable.create(sub -> {
//                sub.onError(e);
//                e.printStackTrace();
//            });
//        }
//    }


}
