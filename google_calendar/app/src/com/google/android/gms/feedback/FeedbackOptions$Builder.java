// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.feedback:
//            FeedbackOptions

public static class mFileTeleporters
{

    public String mAccountInUse;
    public String mCategoryTag;
    public String mDescription;
    public boolean mExcludePii;
    public List mFileTeleporters;
    public Bundle mPsdBundle;
    public Bitmap zzbhc;

    public FeedbackOptions build()
    {
        return FeedbackOptions.zza(FeedbackOptions.zza(FeedbackOptions.zza(FeedbackOptions.zza(FeedbackOptions.zzc(FeedbackOptions.zza(FeedbackOptions.zzb(FeedbackOptions.zza(FeedbackOptions.zza(new FeedbackOptions(new ApplicationErrorReport()), zzbhc), mAccountInUse), mDescription), mPsdBundle), mCategoryTag), mFileTeleporters), mExcludePii), null), null);
    }

    public ()
    {
        mPsdBundle = new Bundle();
        mFileTeleporters = new ArrayList();
    }
}
