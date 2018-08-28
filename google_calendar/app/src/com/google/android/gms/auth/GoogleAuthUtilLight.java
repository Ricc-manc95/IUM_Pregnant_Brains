// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.UserRecoverableException;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.common.zza;
import com.google.android.gms.internal.zzabl;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package com.google.android.gms.auth:
//            GoogleAuthException, GooglePlayServicesAvailabilityException, UserRecoverableAuthException, TokenData

public class GoogleAuthUtilLight
{

    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final String KEY_CALLER_UID = "callerUid";
    public static final zzabl zzajA = new zzabl("Auth", new String[] {
        "GoogleAuthUtil"
    });
    private static final String zzajx[] = {
        "com.google", "com.google.work", "cn.google"
    };
    public static final ComponentName zzajy = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");

    static Object zza(Context context, ComponentName componentname, zza zza1)
        throws IOException, GoogleAuthException
    {
        com.google.android.gms.common.zza zza2;
        zzn zzn1;
        zza2 = new com.google.android.gms.common.zza();
        zzn1 = zzn.zzaF(context);
        if (!zzn1.zza(componentname, zza2, "GoogleAuthUtil")) goto _L2; else goto _L1
_L1:
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        }
          goto _L3
        context;
_L5:
        zzabl zzabl1 = zzajA;
        String s = "GoogleAuthUtil";
        Object aobj[] = new Object[2];
        aobj[0] = "Error on service connection.";
        aobj[1] = context;
        zza1 = s;
        if (aobj == null)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        zza1 = s;
        if (aobj.length > 0)
        {
            zza1 = String.format("GoogleAuthUtil", aobj);
        }
        zzabl1.zzaQF.concat(zza1);
        throw new IOException("Error on service connection.", context);
        context;
        zzn1.zzb(componentname, zza2, "GoogleAuthUtil");
        throw context;
_L3:
        if (zza2.zzaIh)
        {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        zza2.zzaIh = true;
        context = ((Context) (zza1.exec((IBinder)zza2.zzaIi.take())));
        zzn1.zzb(componentname, zza2, "GoogleAuthUtil");
        return context;
_L2:
        throw new IOException("Could not bind to service.");
        context;
        if (true) goto _L5; else goto _L4
_L4:
    }

    static void zzad(Context context)
        throws GoogleAuthException
    {
        try
        {
            GooglePlayServicesUtilLight.zzad(context.getApplicationContext());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new GooglePlayServicesAvailabilityException(((GooglePlayServicesRepairableException) (context)).zzajN, context.getMessage(), new Intent(((UserRecoverableException) (context)).mIntent));
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new GoogleAuthException(context.getMessage());
        }
    }

    static void zzb(Account account)
    {
        if (account == null)
        {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (TextUtils.isEmpty(account.name))
        {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        String as[] = zzajx;
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            if (as[i].equals(account.type))
            {
                return;
            }
        }

        throw new IllegalArgumentException("Account type not supported");
    }

    public static TokenData zzc(Context context, final Account final_account, String s, Bundle bundle)
        throws IOException, UserRecoverableAuthException, GoogleAuthException
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        }
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException(String.valueOf("Scope cannot be empty or null."));
        }
        zzb(final_account);
        zzad(context);
        String s1;
        if (bundle == null)
        {
            bundle = new Bundle();
        } else
        {
            bundle = new Bundle(bundle);
        }
        s1 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", s1);
        if (TextUtils.isEmpty(bundle.getString(KEY_ANDROID_PACKAGE_NAME)))
        {
            bundle.putString(KEY_ANDROID_PACKAGE_NAME, s1);
        }
        bundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        final_account = new _cls1(s, bundle);
        return (TokenData)zza(context, zzajy, final_account);
    }

    static Object zzr(Object obj)
        throws IOException
    {
        if (obj == null)
        {
            zzabl zzabl1 = zzajA;
            String s = "GoogleAuthUtil";
            Object aobj[] = new Object[1];
            aobj[0] = "Binder call returned null.";
            String s1 = zzabl1.mTag;
            obj = s;
            if (aobj != null)
            {
                obj = s;
                if (aobj.length > 0)
                {
                    obj = String.format("GoogleAuthUtil", aobj);
                }
            }
            Log.w(s1, zzabl1.zzaQF.concat(((String) (obj))));
            throw new IOException("Service unavailable.");
        } else
        {
            return obj;
        }
    }

    static Account[] zzs(Context context, String s)
        throws RemoteException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        ContentProviderClient contentproviderclient;
        GoogleApiAvailabilityLight.zzar(context);
        if (context == null)
        {
            throw new NullPointerException("null reference");
        }
        contentproviderclient = ((Context)context).getContentResolver().acquireContentProviderClient("com.google.android.gms.auth.accounts");
        if (contentproviderclient == null)
        {
            return new Account[0];
        }
        context = contentproviderclient.call("get_accounts", s, new Bundle()).getParcelableArray("accounts");
        s = new Account[context.length];
        int i = 0;
_L2:
        if (i >= context.length)
        {
            break; /* Loop/switch isn't completed */
        }
        s[i] = (Account)context[i];
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        contentproviderclient.release();
        return s;
        context;
        context = String.valueOf(context.getMessage());
        if (context.length() == 0)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        context = "Accounts ContentProvider failed: ".concat(context);
_L3:
        throw new RemoteException(context);
        context;
        contentproviderclient.release();
        throw context;
        context = new String("Accounts ContentProvider failed: ");
          goto _L3
    }

    static 
    {
        new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
    }

    private class zza
    {

        public abstract Object exec(IBinder ibinder)
            throws RemoteException, IOException, GoogleAuthException;
    }


    private class _cls1
        implements zza
    {

        private final Bundle val$options;
        private final Account zzajB;
        private final String zzajC;

        public final Object exec(IBinder ibinder)
            throws RemoteException, IOException, GoogleAuthException
        {
            ibinder = (Bundle)GoogleAuthUtilLight.zzr(com.google.android.gms.internal.zzbz.zza.zza(ibinder).zza(zzajB, zzajC, options));
            TokenData tokendata = TokenData.zzd(ibinder, "tokenDetails");
            if (tokendata != null)
            {
                return tokendata;
            }
            String s1 = ibinder.getString("Error");
            Intent intent = (Intent)ibinder.getParcelable("userRecoveryIntent");
            ibinder = Status.fromWireCode(s1);
            boolean flag;
            if (Status.BAD_AUTHENTICATION.equals(ibinder) || Status.CAPTCHA.equals(ibinder) || Status.NEED_PERMISSION.equals(ibinder) || Status.NEED_REMOTE_CONSENT.equals(ibinder) || Status.NEEDS_BROWSER.equals(ibinder) || Status.USER_CANCEL.equals(ibinder) || Status.DEVICE_MANAGEMENT_REQUIRED.equals(ibinder) || Status.DM_INTERNAL_ERROR.equals(ibinder) || Status.DM_SYNC_DISABLED.equals(ibinder) || Status.DM_ADMIN_BLOCKED.equals(ibinder) || Status.DM_ADMIN_PENDING_APPROVAL.equals(ibinder) || Status.DM_STALE_SYNC_REQUIRED.equals(ibinder) || Status.DM_DEACTIVATED.equals(ibinder) || Status.DM_REQUIRED.equals(ibinder) || Status.THIRD_PARTY_DEVICE_MANAGEMENT_REQUIRED.equals(ibinder) || Status.DM_SCREENLOCK_REQUIRED.equals(ibinder))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                zzabl zzabl1 = GoogleAuthUtilLight.zzajA;
                String s = "GoogleAuthUtil";
                Object aobj[] = new Object[1];
                ibinder = String.valueOf(ibinder);
                aobj[0] = (new StringBuilder(String.valueOf(ibinder).length() + 31)).append("isUserRecoverableError status: ").append(ibinder).toString();
                String s2 = zzabl1.mTag;
                ibinder = s;
                if (aobj != null)
                {
                    ibinder = s;
                    if (aobj.length > 0)
                    {
                        ibinder = String.format("GoogleAuthUtil", aobj);
                    }
                }
                Log.w(s2, zzabl1.zzaQF.concat(ibinder));
                throw new UserRecoverableAuthException(s1, intent);
            }
            if (Status.NETWORK_ERROR.equals(ibinder) || Status.SERVICE_UNAVAILABLE.equals(ibinder))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                throw new IOException(s1);
            } else
            {
                throw new GoogleAuthException(s1);
            }
        }

        _cls1(String s, Bundle bundle)
        {
            zzajB = final_account;
            zzajC = s;
            options = bundle;
            super();
        }
    }

}
