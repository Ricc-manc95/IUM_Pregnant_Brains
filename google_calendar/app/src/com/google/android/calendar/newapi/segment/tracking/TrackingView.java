// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.newapi.segment.tracking:
//            TrackingCircleView

public final class TrackingView extends LinearLayout
{

    public TextView bottomText;
    private TextView centerText;
    public TrackingCircleView circle;

    public TrackingView(Context context)
    {
        super(context);
        setOrientation(1);
        inflate(getContext(), 0x7f05017f, this);
        circle = (TrackingCircleView)findViewById(0x7f10038c);
        centerText = (TextView)findViewById(0x7f10038d);
        bottomText = (TextView)findViewById(0x7f10038e);
        setIsPrimary(true);
        setImportantForAccessibility(1);
        setFocusable(true);
    }

    public final CharSequence getContentDescription()
    {
        String s = String.valueOf(bottomText.getText());
        String s1 = String.valueOf(centerText.getContentDescription());
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("\n").append(s1).toString();
    }

    public final void setColor(int i)
    {
        circle.setColor(i);
    }

    public final void setIsPrimary(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0x7f0d031b;
        } else
        {
            i = 0x7f0d031c;
        }
        i = getResources().getColor(i);
        centerText.setTextColor(i);
        bottomText.setTextColor(i);
    }

    public final void setProgress(int i, int j, boolean flag)
    {
        TrackingCircleView trackingcircleview = circle;
        float f;
        if (j <= 0)
        {
            f = 0.0F;
        } else
        {
            f = (360F * (float)i) / (float)j;
        }
        if (trackingcircleview.animator != null)
        {
            trackingcircleview.animator.cancel();
        }
        if (!flag)
        {
            trackingcircleview.angle = f;
            trackingcircleview.invalidate();
        } else
        {
            if (trackingcircleview.animator != null)
            {
                trackingcircleview.animator.cancel();
            }
            float f1 = Math.abs(f - trackingcircleview.angle);
            trackingcircleview.animator = ValueAnimator.ofFloat(new float[] {
                trackingcircleview.angle, f
            });
            trackingcircleview.animator.setDuration((long)(f1 * 1.67F + 200F));
            trackingcircleview.animator.addUpdateListener(new TrackingCircleView._cls1(trackingcircleview));
            trackingcircleview.animator.start();
        }
        centerText.setText(getResources().getString(0x7f130494, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        }));
        centerText.setContentDescription(getResources().getString(0x7f130006, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        }));
    }
}
