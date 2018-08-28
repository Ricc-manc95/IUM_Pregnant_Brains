// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.googlehelp:
//            zzb

public final class GoogleHelp extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public Bundle mPsdBundle;
    public ThemeSettings mThemeSettings;
    public final int mVersionCode;
    public boolean zzbwA;
    public String zzbwc;
    public Account zzbwd;
    public String zzbwe;
    public String zzbwf;
    public Bitmap zzbwg;
    public boolean zzbwh;
    public boolean zzbwi;
    public List zzbwj;
    public Bundle zzbwk;
    public Bitmap zzbwl;
    public byte zzbwm[];
    public int zzbwn;
    public int zzbwo;
    public String zzbwp;
    public Uri zzbwq;
    public List zzbwr;
    public int zzbws;
    public List zzbwt;
    public boolean zzbwu;
    public ErrorReport zzbwv;
    public TogglingData zzbww;
    public int zzbwx;
    public PendingIntent zzbwy;
    public int zzbwz;

    GoogleHelp(int i, String s, Account account, Bundle bundle, String s1, String s2, Bitmap bitmap, 
            boolean flag, boolean flag1, List list, Bundle bundle1, Bitmap bitmap1, byte abyte0[], int j, 
            int k, String s3, Uri uri, List list1, int l, ThemeSettings themesettings, List list2, 
            boolean flag2, ErrorReport errorreport, TogglingData togglingdata, int i1, PendingIntent pendingintent, int j1, boolean flag3)
    {
        zzbwv = new ErrorReport();
        mVersionCode = i;
        zzbwz = j1;
        zzbwA = flag3;
        zzbwc = s;
        zzbwd = account;
        mPsdBundle = bundle;
        zzbwe = s1;
        zzbwf = s2;
        zzbwg = bitmap;
        zzbwh = flag;
        zzbwi = flag1;
        zzbwj = list;
        zzbwy = pendingintent;
        zzbwk = bundle1;
        zzbwl = bitmap1;
        zzbwm = abyte0;
        zzbwn = j;
        zzbwo = k;
        zzbwp = s3;
        zzbwq = uri;
        zzbwr = list1;
        if (mVersionCode >= 4) goto _L2; else goto _L1
_L1:
        themesettings = new ThemeSettings();
        themesettings.zzbhg = l;
_L4:
        mThemeSettings = themesettings;
        zzbwt = list2;
        zzbwu = flag2;
        zzbwv = errorreport;
        if (zzbwv != null)
        {
            zzbwv.launcher = "GoogleHelp";
        }
        zzbww = togglingdata;
        zzbwx = i1;
        return;
_L2:
        if (themesettings == null)
        {
            themesettings = new ThemeSettings();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public GoogleHelp(String s)
    {
        this(10, s, null, null, null, null, null, true, true, ((List) (new ArrayList())), null, null, null, 0, 0, null, null, ((List) (new ArrayList())), 0, null, ((List) (new ArrayList())), false, new ErrorReport(), null, 0, null, -1, false);
    }

    public static Bitmap getScreenshot(Activity activity)
    {
        Bitmap bitmap;
        activity = activity.getWindow().getDecorView().getRootView();
        bitmap = Bitmap.createBitmap(activity.getWidth(), activity.getHeight(), android.graphics.Bitmap.Config.RGB_565);
        activity.draw(new Canvas(bitmap));
        return bitmap;
        activity;
_L2:
        Log.w("GOOGLEHELP_GoogleHelp", "Get screenshot failed!", activity);
        return null;
        activity;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzbwc, false);
        zzc.zza(parcel, 3, zzbwd, i, false);
        zzc.zza(parcel, 4, mPsdBundle, false);
        boolean flag1 = zzbwh;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        flag1 = zzbwi;
        zzc.zzb(parcel, 6, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        zzc.zzb(parcel, 7, zzbwj, false);
        zzc.zza(parcel, 10, zzbwk, false);
        zzc.zza(parcel, 11, zzbwl, i, false);
        zzc.zza(parcel, 14, zzbwp, false);
        zzc.zza(parcel, 15, zzbwq, i, false);
        zzc.zzc(parcel, 16, zzbwr, false);
        j = zzbws;
        zzc.zzb(parcel, 17, 4);
        parcel.writeInt(j);
        zzc.zzc(parcel, 18, zzbwt, false);
        zzc.zza(parcel, 19, zzbwm, false);
        j = zzbwn;
        zzc.zzb(parcel, 20, 4);
        parcel.writeInt(j);
        j = zzbwo;
        zzc.zzb(parcel, 21, 4);
        parcel.writeInt(j);
        flag1 = zzbwu;
        zzc.zzb(parcel, 22, 4);
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        zzc.zza(parcel, 23, zzbwv, i, false);
        zzc.zza(parcel, 25, mThemeSettings, i, false);
        zzc.zza(parcel, 28, zzbwe, false);
        zzc.zza(parcel, 31, zzbww, i, false);
        j = zzbwx;
        zzc.zzb(parcel, 32, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 33, zzbwy, i, false);
        zzc.zza(parcel, 34, zzbwf, false);
        zzc.zza(parcel, 35, zzbwg, i, false);
        i = zzbwz;
        zzc.zzb(parcel, 36, 4);
        parcel.writeInt(i);
        flag1 = zzbwA;
        zzc.zzb(parcel, 37, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, k);
    }

}
