// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.people.model.AvatarReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzayo, zzayt

public interface zzayp
    extends IInterface
{

    public abstract zzs zza(zzayo zzayo, AvatarReference avatarreference, zzayt zzayt)
        throws RemoteException;

    public abstract zzs zza(zzayo zzayo, String s, String s1, boolean flag, String s2, String s3, int i, 
            int j, int k, boolean flag1)
        throws RemoteException;

    public abstract zzs zzb(zzayo zzayo, String s, String s1, int i, int j)
        throws RemoteException;
}
