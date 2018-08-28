// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.List;

// Referenced classes of package com.google.android.gms.feedback:
//            zza, FileTeleporter, LogOptions, ThemeSettings

public class ErrorReport extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.feedback.zza();
    public String account;
    public String anrStackTraces;
    public ApplicationErrorReport applicationErrorReport;
    public BitmapTeleporter bitmapTeleporter;
    public String board;
    public String brand;
    public String buildFingerprint;
    public String buildId;
    public String buildType;
    public String categoryTag;
    public Bundle classificationSignals;
    public String codename;
    public String color;
    public String description;
    public String device;
    public String eventLog[];
    public String exceptionClassName;
    public String exceptionMessage;
    public boolean excludePii;
    public FileTeleporter fileTeleporterList[];
    public List highlightBounds;
    public String incremental;
    public boolean isCtlAllowed;
    public boolean isSilentSend;
    public String launcher;
    public String localeString;
    public LogOptions logOptions;
    public String model;
    public int networkMcc;
    public int networkMnc;
    public String networkName;
    public int networkType;
    public int packageVersion;
    public String packageVersionName;
    public int phoneType;
    public String product;
    public Bundle psdBundle;
    public String psdFilePaths[];
    public String release;
    public String runningApplications[];
    public String screenshot;
    public byte screenshotBytes[];
    public int screenshotHeight;
    public String screenshotPath;
    public int screenshotWidth;
    public int sdk_int;
    public String stackTrace;
    public String submittingPackageName;
    public String suggestionSessionId;
    public boolean suggestionShown;
    public String systemLog[];
    public ThemeSettings themeSettings;
    public String throwClassName;
    public String throwFileName;
    public int throwLineNumber;
    public String throwMethodName;
    public final int versionCode;

    public ErrorReport()
    {
        applicationErrorReport = new ApplicationErrorReport();
        versionCode = 9;
    }

    ErrorReport(int i, ApplicationErrorReport applicationerrorreport, String s, int j, String s1, String s2, String s3, 
            String s4, String s5, String s6, String s7, int k, String s8, String s9, 
            String s10, String s11, String s12, String as[], String as1[], String as2[], String s13, 
            String s14, byte abyte0[], int l, int i1, int j1, int k1, String s15, 
            String s16, String s17, Bundle bundle, boolean flag, int l1, int i2, boolean flag1, 
            String s18, String s19, int j2, String s20, String s21, String s22, String s23, 
            String s24, String s25, String s26, BitmapTeleporter bitmapteleporter, String s27, FileTeleporter afileteleporter[], String as3[], 
            boolean flag2, String s28, ThemeSettings themesettings, LogOptions logoptions, String s29, boolean flag3, Bundle bundle1, 
            List list)
    {
        applicationErrorReport = new ApplicationErrorReport();
        versionCode = i;
        applicationErrorReport = applicationerrorreport;
        description = s;
        packageVersion = j;
        packageVersionName = s1;
        device = s2;
        buildId = s3;
        buildType = s4;
        model = s5;
        product = s6;
        buildFingerprint = s7;
        sdk_int = k;
        release = s8;
        incremental = s9;
        codename = s10;
        board = s11;
        brand = s12;
        runningApplications = as;
        systemLog = as1;
        eventLog = as2;
        anrStackTraces = s13;
        screenshot = s14;
        screenshotBytes = abyte0;
        screenshotHeight = l;
        screenshotWidth = i1;
        phoneType = j1;
        networkType = k1;
        networkName = s15;
        account = s16;
        localeString = s17;
        psdBundle = bundle;
        isSilentSend = flag;
        networkMcc = l1;
        networkMnc = i2;
        isCtlAllowed = flag1;
        exceptionClassName = s18;
        throwFileName = s19;
        throwLineNumber = j2;
        throwClassName = s20;
        throwMethodName = s21;
        stackTrace = s22;
        exceptionMessage = s23;
        categoryTag = s24;
        color = s25;
        submittingPackageName = s26;
        bitmapTeleporter = bitmapteleporter;
        screenshotPath = s27;
        fileTeleporterList = afileteleporter;
        psdFilePaths = as3;
        excludePii = flag2;
        launcher = s28;
        themeSettings = themesettings;
        logOptions = logoptions;
        suggestionSessionId = s29;
        suggestionShown = flag3;
        classificationSignals = bundle1;
        highlightBounds = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int j = versionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, applicationErrorReport, i, false);
        zzc.zza(parcel, 3, description, false);
        j = packageVersion;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 5, packageVersionName, false);
        zzc.zza(parcel, 6, device, false);
        zzc.zza(parcel, 7, buildId, false);
        zzc.zza(parcel, 8, buildType, false);
        zzc.zza(parcel, 9, model, false);
        zzc.zza(parcel, 10, product, false);
        zzc.zza(parcel, 11, buildFingerprint, false);
        j = sdk_int;
        zzc.zzb(parcel, 12, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 13, release, false);
        zzc.zza(parcel, 14, incremental, false);
        zzc.zza(parcel, 15, codename, false);
        zzc.zza(parcel, 16, board, false);
        zzc.zza(parcel, 17, brand, false);
        zzc.zza(parcel, 18, runningApplications, false);
        zzc.zza(parcel, 19, systemLog, false);
        zzc.zza(parcel, 20, eventLog, false);
        zzc.zza(parcel, 21, anrStackTraces, false);
        zzc.zza(parcel, 22, screenshot, false);
        zzc.zza(parcel, 23, screenshotBytes, false);
        j = screenshotHeight;
        zzc.zzb(parcel, 24, 4);
        parcel.writeInt(j);
        j = screenshotWidth;
        zzc.zzb(parcel, 25, 4);
        parcel.writeInt(j);
        j = phoneType;
        zzc.zzb(parcel, 26, 4);
        parcel.writeInt(j);
        j = networkType;
        zzc.zzb(parcel, 27, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 28, networkName, false);
        zzc.zza(parcel, 29, account, false);
        zzc.zza(parcel, 30, localeString, false);
        zzc.zza(parcel, 31, psdBundle, false);
        boolean flag1 = isSilentSend;
        zzc.zzb(parcel, 32, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        j = networkMcc;
        zzc.zzb(parcel, 33, 4);
        parcel.writeInt(j);
        j = networkMnc;
        zzc.zzb(parcel, 34, 4);
        parcel.writeInt(j);
        flag1 = isCtlAllowed;
        zzc.zzb(parcel, 35, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        zzc.zza(parcel, 36, exceptionClassName, false);
        zzc.zza(parcel, 37, throwFileName, false);
        j = throwLineNumber;
        zzc.zzb(parcel, 38, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 39, throwClassName, false);
        zzc.zza(parcel, 40, throwMethodName, false);
        zzc.zza(parcel, 41, stackTrace, false);
        zzc.zza(parcel, 42, exceptionMessage, false);
        zzc.zza(parcel, 43, categoryTag, false);
        zzc.zza(parcel, 44, color, false);
        zzc.zza(parcel, 45, submittingPackageName, false);
        zzc.zza(parcel, 46, bitmapTeleporter, i, false);
        zzc.zza(parcel, 47, screenshotPath, false);
        zzc.zza(parcel, 48, fileTeleporterList, i, false);
        zzc.zza(parcel, 49, psdFilePaths, false);
        flag1 = excludePii;
        zzc.zzb(parcel, 50, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        zzc.zza(parcel, 51, launcher, false);
        zzc.zza(parcel, 52, themeSettings, i, false);
        zzc.zza(parcel, 53, logOptions, i, false);
        zzc.zza(parcel, 54, suggestionSessionId, false);
        flag1 = suggestionShown;
        zzc.zzb(parcel, 55, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zza(parcel, 56, classificationSignals, false);
        zzc.zzc(parcel, 57, highlightBounds, false);
        zzc.zzJ(parcel, k);
    }

}
