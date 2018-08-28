// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.text.TextUtils;

// Referenced classes of package com.google.android.gms.feedback:
//            FeedbackOptions

public static final class mApplicationErrorReport extends mApplicationErrorReport
{

    public final ApplicationErrorReport mApplicationErrorReport = new ApplicationErrorReport();

    public final FeedbackOptions build()
    {
        if (mApplicationErrorReport.crashInfo.exceptionClassName == null)
        {
            throw new NullPointerException("null reference");
        }
        if (mApplicationErrorReport.crashInfo.throwClassName == null)
        {
            throw new NullPointerException("null reference");
        }
        if (mApplicationErrorReport.crashInfo.throwMethodName == null)
        {
            throw new NullPointerException("null reference");
        }
        if (mApplicationErrorReport.crashInfo.stackTrace == null)
        {
            throw new NullPointerException("null reference");
        }
        if (TextUtils.isEmpty(mApplicationErrorReport.crashInfo.throwFileName))
        {
            mApplicationErrorReport.crashInfo.throwFileName = "unknown";
        }
        return FeedbackOptions.zzd(FeedbackOptions.zza(super.(), mApplicationErrorReport.crashInfo), null);
    }

    public ()
    {
        mApplicationErrorReport.crashInfo = new android.app.nfo();
        mApplicationErrorReport.crashInfo.throwLineNumber = -1;
    }
}
