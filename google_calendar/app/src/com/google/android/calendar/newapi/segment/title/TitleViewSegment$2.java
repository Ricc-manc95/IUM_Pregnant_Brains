// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleViewSegment

final class this._cls0
    implements FutureCallback
{

    private final TitleViewSegment this$0;

    public final void onFailure(Throwable throwable)
    {
        throwable = TitleViewSegment.this;
        throwable.hasAttribution = false;
        ((TitleViewSegment) (throwable)).attributionText.setVisibility(4);
    }

    public final void onSuccess(Object obj)
    {
        obj = (CharSequence)obj;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            hasAttribution = true;
            attributionText.setText(((CharSequence) (obj)));
            attributionText.setMovementMethod(LinkMovementMethod.getInstance());
            return;
        } else
        {
            obj = TitleViewSegment.this;
            obj.hasAttribution = false;
            ((TitleViewSegment) (obj)).attributionText.setVisibility(4);
            return;
        }
    }

    I()
    {
        this$0 = TitleViewSegment.this;
        super();
    }
}
