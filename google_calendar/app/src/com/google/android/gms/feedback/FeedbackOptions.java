// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.feedback:
//            zzb, LogOptions, ThemeSettings

public class FeedbackOptions extends zza
{
    public static class Builder
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
            return com.google.android.gms.feedback.FeedbackOptions.zza(com.google.android.gms.feedback.FeedbackOptions.zza(com.google.android.gms.feedback.FeedbackOptions.zza(com.google.android.gms.feedback.FeedbackOptions.zza(com.google.android.gms.feedback.FeedbackOptions.zzc(com.google.android.gms.feedback.FeedbackOptions.zza(FeedbackOptions.zzb(com.google.android.gms.feedback.FeedbackOptions.zza(com.google.android.gms.feedback.FeedbackOptions.zza(new FeedbackOptions(new ApplicationErrorReport()), zzbhc), mAccountInUse), mDescription), mPsdBundle), mCategoryTag), mFileTeleporters), mExcludePii), null), null);
        }

        public Builder()
        {
            mPsdBundle = new Bundle();
            mFileTeleporters = new ArrayList();
        }
    }

    public static final class CrashBuilder extends Builder
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
            return FeedbackOptions.zzd(com.google.android.gms.feedback.FeedbackOptions.zza(super.build(), mApplicationErrorReport.crashInfo), null);
        }

        public CrashBuilder()
        {
            mApplicationErrorReport.crashInfo = new android.app.ApplicationErrorReport.CrashInfo();
            mApplicationErrorReport.crashInfo.throwLineNumber = -1;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public String mAccountInUse;
    public ApplicationErrorReport mApplicationErrorReport;
    public BitmapTeleporter mBitmapTeleporter;
    public String mCategoryTag;
    public String mDescription;
    public boolean mExcludePii;
    public List mFileTeleporters;
    public LogOptions mLogOptions;
    public String mPackageName;
    public Bundle mPsdBundle;
    public ThemeSettings mThemeSettings;
    public final int mVersionCode;

    FeedbackOptions(int i, String s, Bundle bundle, String s1, ApplicationErrorReport applicationerrorreport, String s2, BitmapTeleporter bitmapteleporter, 
            String s3, List list, boolean flag, ThemeSettings themesettings, LogOptions logoptions)
    {
        mVersionCode = i;
        mAccountInUse = s;
        mPsdBundle = bundle;
        mDescription = s1;
        mApplicationErrorReport = applicationerrorreport;
        mCategoryTag = s2;
        mBitmapTeleporter = bitmapteleporter;
        mPackageName = s3;
        mFileTeleporters = list;
        mExcludePii = flag;
        mThemeSettings = themesettings;
        mLogOptions = logoptions;
    }

    FeedbackOptions(ApplicationErrorReport applicationerrorreport)
    {
        this(3, null, null, null, applicationerrorreport, null, null, null, null, true, null, null);
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, android.app.ApplicationErrorReport.CrashInfo crashinfo)
    {
        feedbackoptions.mApplicationErrorReport.crashInfo = crashinfo;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, Bitmap bitmap)
    {
        if (bitmap != null)
        {
            feedbackoptions.mBitmapTeleporter = new BitmapTeleporter(bitmap);
        }
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, Bundle bundle)
    {
        feedbackoptions.mPsdBundle = bundle;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, LogOptions logoptions)
    {
        feedbackoptions.mLogOptions = logoptions;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, ThemeSettings themesettings)
    {
        feedbackoptions.mThemeSettings = themesettings;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, String s)
    {
        feedbackoptions.mAccountInUse = s;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, List list)
    {
        feedbackoptions.mFileTeleporters = list;
        return feedbackoptions;
    }

    static FeedbackOptions zza(FeedbackOptions feedbackoptions, boolean flag)
    {
        feedbackoptions.mExcludePii = flag;
        return feedbackoptions;
    }

    static FeedbackOptions zzb(FeedbackOptions feedbackoptions, String s)
    {
        feedbackoptions.mDescription = s;
        return feedbackoptions;
    }

    static FeedbackOptions zzc(FeedbackOptions feedbackoptions, String s)
    {
        feedbackoptions.mCategoryTag = s;
        return feedbackoptions;
    }

    static FeedbackOptions zzd(FeedbackOptions feedbackoptions, String s)
    {
        feedbackoptions.mPackageName = s;
        return feedbackoptions;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int l = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(l);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, mAccountInUse, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, mPsdBundle, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 5, mDescription, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, mApplicationErrorReport, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, mCategoryTag, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, mBitmapTeleporter, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 9, mPackageName, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzc(parcel, 10, mFileTeleporters, false);
        boolean flag = mExcludePii;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 11, 4);
        if (!flag)
        {
            j = 0;
        }
        parcel.writeInt(j);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 12, mThemeSettings, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 13, mLogOptions, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, k);
    }

}
