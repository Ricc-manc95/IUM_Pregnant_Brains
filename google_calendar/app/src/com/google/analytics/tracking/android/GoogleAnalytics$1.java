// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


// Referenced classes of package com.google.analytics.tracking.android:
//            GoogleAnalytics

final class this._cls0
    implements pOptOutCallback
{

    private final GoogleAnalytics this$0;

    public final void reportAppOptOut(boolean flag)
    {
        mAppOptOut = Boolean.valueOf(flag);
    }

    pOptOutCallback()
    {
        this$0 = GoogleAnalytics.this;
        super();
    }
}
