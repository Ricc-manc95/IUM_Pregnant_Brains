// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

public abstract class zzaGo extends zzys
    implements zzaGo
{

    public final Api zzaGo;
    public final com.google.android.gms.common.api.aEP zzaJQ;

    public final void setResult(Object obj)
    {
        super.zzb((Result)obj);
    }

    public final void zzM(Status status)
    {
        boolean flag1 = true;
        boolean flag;
        if (status.zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
        } else
        {
            zzb(zzb(status));
            return;
        }
    }

    public abstract void zza(com.google.android.gms.common.api.aEP aep)
        throws RemoteException;

    public final void zzb(com.google.android.gms.common.api.aEP aep)
        throws DeadObjectException
    {
        boolean flag3 = true;
        boolean flag2 = true;
        try
        {
            zza(aep);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.gms.common.api.aEP aep)
        {
            Status status = new Status(8, aep.getLocalizedMessage(), null);
            boolean flag;
            if (status.zzaEP <= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
            } else
            {
                zzb(zzb(status));
                throw aep;
            }
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.gms.common.api.aEP aep)
        {
            aep = new Status(8, aep.getLocalizedMessage(), null);
        }
        boolean flag1;
        if (((Status) (aep)).zzaEP <= 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            flag1 = flag3;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
        } else
        {
            zzb(zzb(aep));
            return;
        }
    }

    protected piClient(com.google.android.gms.common.api.Client client, GoogleApiClient googleapiclient)
    {
        if (googleapiclient == null)
        {
            throw new NullPointerException(String.valueOf("GoogleApiClient must not be null"));
        }
        super((GoogleApiClient)googleapiclient);
        if (client == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzaJQ = (com.google.android.gms.common.api.aJQ)client;
            zzaGo = null;
            return;
        }
    }

    public piClient(Api api, GoogleApiClient googleapiclient)
    {
        if (googleapiclient == null)
        {
            throw new NullPointerException(String.valueOf("GoogleApiClient must not be null"));
        }
        super((GoogleApiClient)googleapiclient);
        if (api.zzaII != null)
        {
            zzaJQ = api.zzaII;
            zzaGo = api;
            return;
        } else
        {
            throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
        }
    }
}
