// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.os.Vibrator;

public final class HapticFeedbackController
{

    public final ContentObserver contentObserver = new _cls1(null);
    public final Context context;
    public boolean isGloballyEnabled;
    private long lastVibrate;
    public Vibrator vibrator;

    public HapticFeedbackController(Context context1)
    {
        context = context1;
    }

    public final void start()
    {
        vibrator = (Vibrator)context.getSystemService("vibrator");
        android.net.Uri uri;
        boolean flag;
        if (android.provider.Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isGloballyEnabled = flag;
        uri = android.provider.Settings.System.getUriFor("haptic_feedback_enabled");
        context.getContentResolver().registerContentObserver(uri, false, contentObserver);
    }

    public final void tryVibrate()
    {
        if (vibrator != null && isGloballyEnabled)
        {
            long l = SystemClock.uptimeMillis();
            if (l - lastVibrate >= 125L)
            {
                vibrator.vibrate(5L);
                lastVibrate = l;
            }
        }
    }

    private class _cls1 extends ContentObserver
    {

        private final HapticFeedbackController this$0;

        public final void onChange(boolean flag)
        {
            flag = true;
            HapticFeedbackController hapticfeedbackcontroller = HapticFeedbackController.this;
            if (android.provider.Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) != 1)
            {
                flag = false;
            }
            hapticfeedbackcontroller.isGloballyEnabled = flag;
        }

        _cls1(Handler handler)
        {
            this$0 = HapticFeedbackController.this;
            super(null);
        }
    }

}
