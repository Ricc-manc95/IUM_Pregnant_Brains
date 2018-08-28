// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.widget.ImageView;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            AccountPhotoLoader

final class accountName
    implements ResultCallback
{

    public final String accountName;
    public final int avatarSize = 1;
    public final String pageId = null;
    public final AccountPhotoLoader this$0;
    public final ImageView view;

    public final void onResult(Result result)
    {
        result = (com.google.android.gms.people.tarRequest)result;
        onImageLoaded(result.oaded(), result.scriptor(), this);
    }

    public (ImageView imageview, String s, String s1, int i)
    {
        this$0 = AccountPhotoLoader.this;
        super();
        view = imageview;
        accountName = s;
    }
}
