/*
* Copyright 2015 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.qmx163.permissionutil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;


public abstract class PermissionUtil {


/*

单独申请的权限，共分为9组，每组只要有一个权限申请成功了，就默认整组权限都可以使用了。

  group:android.permission-group.CONTACTS
    permission:android.permission.WRITE_CONTACTS
    permission:android.permission.GET_ACCOUNTS
    permission:android.permission.READ_CONTACTS

  group:android.permission-group.PHONE
    permission:android.permission.READ_CALL_LOG
    permission:android.permission.READ_PHONE_STATE
    permission:android.permission.CALL_PHONE
    permission:android.permission.WRITE_CALL_LOG
    permission:android.permission.USE_SIP
    permission:android.permission.PROCESS_OUTGOING_CALLS
    permission:com.android.voicemail.permission.ADD_VOICEMAIL

  group:android.permission-group.CALENDAR
    permission:android.permission.READ_CALENDAR
    permission:android.permission.WRITE_CALENDAR

  group:android.permission-group.CAMERA
    permission:android.permission.CAMERA

  group:android.permission-group.SENSORS
    permission:android.permission.BODY_SENSORS

  group:android.permission-group.LOCATION
    permission:android.permission.ACCESS_FINE_LOCATION
    permission:android.permission.ACCESS_COARSE_LOCATION

  group:android.permission-group.STORAGE
    permission:android.permission.READ_EXTERNAL_STORAGE
    permission:android.permission.WRITE_EXTERNAL_STORAGE

  group:android.permission-group.MICROPHONE
    permission:android.permission.RECORD_AUDIO

  group:android.permission-group.SMS
    permission:android.permission.READ_SMS
    permission:android.permission.RECEIVE_WAP_PUSH
    permission:android.permission.RECEIVE_MMS
    permission:android.permission.RECEIVE_SMS
    permission:android.permission.SEND_SMS
    permission:android.permission.READ_CELL_BROADCASTS


    */


    /**
     * 6.0以后权限申请,onRequestPermissionsResult回调按钮事件
     * <p>
     * Created by 邓靖 on  2017/3/6  14:32
     */
    public static boolean getCameraPermission(Activity context, String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            //showToas("没有权限");
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                //点击不再显示此窗口后，已经禁止窗口提示了
                //showToas("您已禁止该权限，需要重新开启。");
            } else {
                //showToas("---------------");
                //系统弹窗是否获取权限
                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
            }
            return false;

        } else {
            return true;
        }
    }


    /**
     * 回调判断用户是否授权
     * <p>
     * Created by 邓靖 on  2017/3/10  13:33
     */
    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
