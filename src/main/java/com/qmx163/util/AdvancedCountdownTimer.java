package com.qmx163.util;

/**
 * Created by Administrator on 2017/11/21.
 */

import android.os.Handler;
import android.os.Message;

public abstract class AdvancedCountdownTimer {

    private final long mCountdownInterval;

    private long mTotalTime;

    private long mRemainTime;

    private boolean isStart = false;


    public AdvancedCountdownTimer(long millisInFuture, long countDownInterval) {
        mTotalTime = millisInFuture;
        mCountdownInterval = countDownInterval;

        mRemainTime = millisInFuture;
    }

    public final void seek(int value) {
        synchronized (AdvancedCountdownTimer.this) {
            mRemainTime = ((100 - value) * mTotalTime) / 100;
        }
    }


    public final void cancel() {
        mHandler.removeMessages(MSG_RUN);
        mHandler.removeMessages(MSG_PAUSE);
    }

    public final void resume() {
        if (!isStart) {
            isStart = true;
        mHandler.removeMessages(MSG_PAUSE);
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
        }
    }

    public final void pause() {
        if (isStart){
            isStart = false;
        mHandler.removeMessages(MSG_RUN);
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_PAUSE));
        }
    }


    public synchronized final AdvancedCountdownTimer start() {
        if (!isStart) {
            isStart = true;
        if (mRemainTime <= 0) {
            onFinish();
            return this;
        }
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN),
                mCountdownInterval);
        }
        return this;
    }

    public abstract void onTick(long millisUntilFinished, int percent);


    public abstract void onFinish();

    private static final int MSG_RUN = 1;
    private static final int MSG_PAUSE = 2;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (AdvancedCountdownTimer.this) {
                if (msg.what == MSG_RUN) {
                    mRemainTime = mRemainTime - mCountdownInterval;

                    if (mRemainTime <= 0) {
                        onFinish();
                    } else if (mRemainTime < mCountdownInterval) {
                        sendMessageDelayed(obtainMessage(MSG_RUN), mRemainTime);
                    } else {

                        onTick(mTotalTime - mRemainTime, new Long(100
                                * (mTotalTime - mRemainTime) / mTotalTime)
                                .intValue());


                        sendMessageDelayed(obtainMessage(MSG_RUN),
                                mCountdownInterval);
                    }
                } else if (msg.what == MSG_PAUSE) {

                }
            }
        }
    };
}
