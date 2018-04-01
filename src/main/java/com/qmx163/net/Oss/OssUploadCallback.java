package com.qmx163.net.Oss;

import java.util.List;
import java.util.Map;

/** 上传OSS回调接口
 * Created by LiJing on 2016/4/19.
 */
public abstract interface OssUploadCallback {
    public abstract void uploadCallback(Map<String, List<String>> result);
}
