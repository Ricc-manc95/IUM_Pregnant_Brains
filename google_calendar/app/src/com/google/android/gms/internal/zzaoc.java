// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.ApplicationErrorReport;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.FileTeleporter;
import java.io.File;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzaod

public final class zzaoc extends zzl
{

    public Context mContext;

    public zzaoc(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, zzg zzg)
    {
        super(context, looper, 29, zzg, connectioncallbacks, onconnectionfailedlistener);
        mContext = context;
    }

    public static ErrorReport zza(FeedbackOptions feedbackoptions, File file)
    {
        ErrorReport errorreport = new ErrorReport();
        if (feedbackoptions == null)
        {
            return errorreport;
        }
        if (feedbackoptions.mPsdBundle != null && feedbackoptions.mPsdBundle.size() > 0)
        {
            errorreport.psdBundle = feedbackoptions.mPsdBundle;
        }
        if (!TextUtils.isEmpty(feedbackoptions.mAccountInUse))
        {
            errorreport.account = feedbackoptions.mAccountInUse;
        }
        if (!TextUtils.isEmpty(feedbackoptions.mDescription))
        {
            errorreport.description = feedbackoptions.mDescription;
        }
        Object obj;
        if (feedbackoptions.mApplicationErrorReport == null)
        {
            obj = null;
        } else
        {
            obj = feedbackoptions.mApplicationErrorReport.crashInfo;
        }
        if (obj != null)
        {
            errorreport.throwMethodName = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).throwMethodName;
            errorreport.throwLineNumber = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).throwLineNumber;
            errorreport.throwClassName = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).throwClassName;
            errorreport.stackTrace = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).stackTrace;
            errorreport.exceptionClassName = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).exceptionClassName;
            errorreport.exceptionMessage = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).exceptionMessage;
            errorreport.throwFileName = ((android.app.ApplicationErrorReport.CrashInfo) (obj)).throwFileName;
        }
        if (feedbackoptions.mThemeSettings != null)
        {
            errorreport.themeSettings = feedbackoptions.mThemeSettings;
        }
        if (!TextUtils.isEmpty(feedbackoptions.mCategoryTag))
        {
            errorreport.categoryTag = feedbackoptions.mCategoryTag;
        }
        if (!TextUtils.isEmpty(feedbackoptions.mPackageName))
        {
            errorreport.applicationErrorReport.packageName = feedbackoptions.mPackageName;
        }
        if (feedbackoptions.mBitmapTeleporter != null && file != null)
        {
            errorreport.bitmapTeleporter = feedbackoptions.mBitmapTeleporter;
            obj = errorreport.bitmapTeleporter;
            if (file == null)
            {
                throw new NullPointerException("Cannot set null temp directory");
            }
            obj.zzaNM = file;
        }
        if (feedbackoptions.mFileTeleporters != null && feedbackoptions.mFileTeleporters.size() != 0 && file != null)
        {
            for (Iterator iterator = feedbackoptions.mFileTeleporters.iterator(); iterator.hasNext();)
            {
                FileTeleporter fileteleporter = (FileTeleporter)iterator.next();
                if (file == null)
                {
                    throw new NullPointerException("Cannot set null temp directory");
                }
                fileteleporter.zzaNM = file;
            }

            errorreport.fileTeleporterList = (FileTeleporter[])feedbackoptions.mFileTeleporters.toArray(new FileTeleporter[feedbackoptions.mFileTeleporters.size()]);
        }
        if (feedbackoptions.mLogOptions != null)
        {
            errorreport.logOptions = feedbackoptions.mLogOptions;
        }
        errorreport.excludePii = feedbackoptions.mExcludePii;
        return errorreport;
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.feedback.internal.IFeedbackService";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.feedback.internal.IFeedbackService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.feedback.internal.IFeedbackService");
        if (iinterface != null && (iinterface instanceof zzaod))
        {
            return (zzaod)iinterface;
        } else
        {
            return new zzaod.zza.zza(ibinder);
        }
    }
}
