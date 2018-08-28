// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.os.Handler;
import android.os.Looper;

final class val.handler
    implements ReplyCallback
{

    private final android.support.v4.content.res.llback val$fontCallback;
    private final Handler val$handler;

    public final void onReply(Object obj)
    {
        obj = (pefaceResult)obj;
        if (obj == null)
        {
            android.support.v4.content.res.llback llback = val$fontCallback;
            Handler handler1 = val$handler;
            obj = handler1;
            if (handler1 == null)
            {
                obj = new Handler(Looper.getMainLooper());
            }
            ((Handler) (obj)).post(new android.support.v4.content.res.llback._cls2(llback, 1));
            return;
        }
        if (((pefaceResult) (obj)).mResult == 0)
        {
            android.support.v4.content.res.llback llback1 = val$fontCallback;
            android.graphics.Typeface typeface = ((pefaceResult) (obj)).mTypeface;
            Handler handler2 = val$handler;
            obj = handler2;
            if (handler2 == null)
            {
                obj = new Handler(Looper.getMainLooper());
            }
            ((Handler) (obj)).post(new android.support.v4.content.res.llback._cls1(llback1, typeface));
            return;
        }
        android.support.v4.content.res.llback llback2 = val$fontCallback;
        int i = ((pefaceResult) (obj)).mResult;
        Handler handler3 = val$handler;
        obj = handler3;
        if (handler3 == null)
        {
            obj = new Handler(Looper.getMainLooper());
        }
        ((Handler) (obj)).post(new android.support.v4.content.res.llback._cls2(llback2, i));
    }

    tCallback()
    {
        val$fontCallback = llback;
        val$handler = handler1;
        super();
    }
}
