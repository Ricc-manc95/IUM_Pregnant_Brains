// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.auth:
//            GoogleAuthUtilLight, GooglePlayServicesAvailabilityException, GoogleAuthException, TokenData, 
//            UserRecoverableAuthException, UserRecoverableNotifiedException

public final class GoogleAuthUtil extends GoogleAuthUtilLight
{

    public static void clearToken(Context context, String s)
        throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        }
        GoogleAuthUtilLight.zzad(context);
        Bundle bundle = new Bundle();
        String s1 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", s1);
        if (!bundle.containsKey(GoogleAuthUtilLight.KEY_ANDROID_PACKAGE_NAME))
        {
            bundle.putString(GoogleAuthUtilLight.KEY_ANDROID_PACKAGE_NAME, s1);
        }
        s = new GoogleAuthUtilLight._cls2(s, bundle);
        GoogleAuthUtilLight.zza(context, GoogleAuthUtilLight.zzajy, s);
    }

    public static String getAccountId(Context context, String s)
        throws GoogleAuthException, IOException
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException(String.valueOf("accountName must be provided"));
        }
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        } else
        {
            GoogleAuthUtilLight.zzad(context);
            Bundle bundle = new Bundle();
            s = new Account(s, "com.google");
            GoogleAuthUtilLight.zzb(s);
            return GoogleAuthUtilLight.zzc(context, s, "^^_account_id_^^", bundle).zzajP;
        }
    }

    public static Account[] getAccounts(Context context, String s)
        throws RemoteException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return GoogleAuthUtilLight.zzs(context, s);
        } else
        {
            return AccountManager.get(context).getAccountsByType(s);
        }
    }

    public static String getToken(Context context, String s, String s1)
        throws IOException, UserRecoverableAuthException, GoogleAuthException
    {
        s = new Account(s, "com.google");
        Bundle bundle = new Bundle();
        GoogleAuthUtilLight.zzb(s);
        return GoogleAuthUtilLight.zzc(context, s, s1, bundle).zzajP;
    }

    public static String getToken(Context context, String s, String s1, Bundle bundle)
        throws IOException, UserRecoverableAuthException, GoogleAuthException
    {
        s = new Account(s, "com.google");
        GoogleAuthUtilLight.zzb(s);
        return GoogleAuthUtilLight.zzc(context, s, s1, bundle).zzajP;
    }

    public static String getTokenWithNotification(Context context, Account account, String s, Bundle bundle)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        Bundle bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        bundle1.putBoolean("handle_notification", true);
        return zzb(context, account, s, bundle1).zzajP;
    }

    public static String getTokenWithNotification(Context context, String s, String s1, Bundle bundle)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        bundle = new Account(s, "com.google");
        s = null;
        if (true)
        {
            s = new Bundle();
        }
        s.putBoolean("handle_notification", true);
        return zzb(context, bundle, s1, s).zzajP;
    }

    public static String getTokenWithNotification(Context context, String s, String s1, Bundle bundle, String s2, Bundle bundle1)
        throws IOException, UserRecoverableNotifiedException, GoogleAuthException
    {
        bundle = null;
        bundle1 = new Account(s, "com.google");
        if (TextUtils.isEmpty(s2))
        {
            throw new IllegalArgumentException(String.valueOf("Authority cannot be empty or null."));
        }
        if (true)
        {
            s = new Bundle();
        } else
        {
            s = null;
        }
        if (true)
        {
            bundle = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(bundle);
        s.putString("authority", s2);
        s.putBundle("sync_extras", bundle);
        s.putBoolean("handle_notification", true);
        return zzb(context, bundle1, s1, s).zzajP;
    }

    public static void invalidateToken(Context context, String s)
    {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }

    private static TokenData zzb(Context context, Account account, String s, Bundle bundle)
        throws IOException, GoogleAuthException
    {
        Bundle bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        try
        {
            account = GoogleAuthUtilLight.zzc(context, account, s, bundle1);
            GooglePlayServicesUtil.zzas(context);
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            GooglePlayServicesUtil.showErrorNotification(((GooglePlayServicesAvailabilityException) (account)).zzajN, context);
            Log.w("GoogleAuthUtil", "Error when getting token", account);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        // Misplaced declaration of an exception variable
        catch (Account account)
        {
            GooglePlayServicesUtil.zzas(context);
            Log.w("GoogleAuthUtil", "Error when getting token", account);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        return account;
    }
}
