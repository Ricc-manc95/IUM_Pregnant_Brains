// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzv, zzk

public final class zzm
{

    private final Context mContext;
    public final zzv zzbBW;
    public boolean zzbCk;
    public final Map zzbCl = new HashMap();
    public final Map zzbkB = new HashMap();

    public zzm(Context context, zzv zzv1)
    {
        zzbCk = false;
        mContext = context;
        zzbBW = zzv1;
    }

    public final Location getLastLocation()
    {
        zzbBW.zzyO();
        Location location;
        try
        {
            location = ((zzk)zzbBW.zzyP()).zzeP(mContext.getPackageName());
        }
        catch (RemoteException remoteexception)
        {
            throw new IllegalStateException(remoteexception);
        }
        return location;
    }
}
