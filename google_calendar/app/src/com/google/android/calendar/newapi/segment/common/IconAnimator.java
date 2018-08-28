// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import com.google.android.calendar.Utils;

public final class IconAnimator
{

    public static void animate(ImageView imageview, int i)
    {
        if (!ViewCompat.isAttachedToWindow(imageview))
        {
            return;
        } else
        {
            imageview.setColorFilter(i);
            imageview.animate().scaleX(1.65F).scaleY(1.65F).setStartDelay(300L).setInterpolator(Utils.SINE_INTERPOLATOR).setDuration(600L).start();
            Handler handler = new Handler(Looper.getMainLooper());
            imageview.getClass();
            class .Lambda._cls0
                implements Runnable
            {

                private final ImageView arg$1;

                public final void run()
                {
                    arg$1.clearColorFilter();
                }

            .Lambda._cls0(ImageView imageview)
            {
                arg$1 = imageview;
            }
            }

            handler.postDelayed(new .Lambda._cls0(imageview), 2100L);
            return;
        }
    }
}
