// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls1
    implements Function
{

    private final CreateGrooveActivity arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (String)obj;
        Resources resources = ((AppCompatActivity) (obj1)).getResources();
        float f = resources.getConfiguration().screenWidthDp;
        int i = Math.round(resources.getDisplayMetrics().density * f);
        int j = ((AppCompatActivity) (obj1)).getResources().getDimensionPixelSize(0x7f0e038b);
        obj1 = new com.google.android.calendar.volley.re();
        obj = new ImageRequest(((String) (obj)), ((com.android.volley.olleyRequestFuture) (obj1)), i, j, android.widget.ts.VolleyRequestFuture, null, ((com.android.volley.olleyRequestFuture) (obj1)));
        boolean flag;
        if (((com.google.android.calendar.volley.re) (obj1)).request == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Already started"));
        }
        obj1.request = ((Request) (obj));
        ((com.google.android.calendar.volley.re) (obj1)).request.mTag = "calendar_volley_request";
        obj = VolleyQueueHolder.requestQueue;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        } else
        {
            ((RequestQueue)obj).add(((com.google.android.calendar.volley.re) (obj1)).request);
            return obj1;
        }
    }

    re(CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
