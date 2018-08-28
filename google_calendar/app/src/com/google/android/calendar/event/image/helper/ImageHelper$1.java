// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.helper;

import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.image.EventImageRequestKey;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.event.image.helper:
//            ImageHelper

final class this._cls0
    implements FutureCallback
{

    private final ImageHelper this$0;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(ImageHelper.TAG, throwable, "Key resolution failed.", new Object[0]);
        drawable.bind(null);
    }

    public final void onSuccess(Object obj)
    {
        obj = (EventImageRequestKey)obj;
        drawable.bind(((com.android.bitmap.RequestKey) (obj)));
    }

    ()
    {
        this$0 = ImageHelper.this;
        super();
    }
}
