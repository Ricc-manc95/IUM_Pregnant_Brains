// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.internal.zzabl;
import com.google.android.gms.internal.zzbz;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.auth:
//            GoogleAuthException, GoogleAuthUtilLight, TokenData, UserRecoverableAuthException

final class val.options
    implements a
{

    private final Bundle val$options;
    private final Account zzajB;
    private final String zzajC;

    public final Object exec(IBinder ibinder)
        throws RemoteException, IOException, GoogleAuthException
    {
        ibinder = (Bundle)GoogleAuthUtilLight.zzr(com.google.android.gms.internal.options(ibinder).zza(zzajB, zzajC, val$options));
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

    eption(String s, Bundle bundle)
    {
        zzajB = final_account;
        zzajC = s;
        val$options = bundle;
        super();
    }
}
