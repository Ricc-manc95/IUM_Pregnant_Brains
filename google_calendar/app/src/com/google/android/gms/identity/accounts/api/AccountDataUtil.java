// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.identity.accounts.api;


// Referenced classes of package com.google.android.gms.identity.accounts.api:
//            zzb

public final class AccountDataUtil
{

    private static final zzb.zza zzbxK;
    public static final zzb zzbxL;

    static 
    {
        zzbxK = new _cls1();
        zzbxL = new zzb(zzbxK);
    }

    private class _cls1
        implements zzb.zza
    {

        public final boolean zzG(Context context, String s)
        {
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException(String.valueOf("Package name must not be empty."));
            } else
            {
                return GoogleSignatureVerifier.getInstance(context).isPackageGoogleSigned(context.getPackageManager(), s);
            }
        }

        _cls1()
        {
        }
    }

}
