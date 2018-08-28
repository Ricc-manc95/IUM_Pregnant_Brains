// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamite;

import android.content.Context;

// Referenced classes of package com.google.android.gms.dynamite:
//            DynamiteModule

final class zzbgO
    implements b.zza
{

    private final int zzbgO;

    public final int zzB(Context context, String s)
    {
        return zzbgO;
    }

    public final DynamiteModule zza(Context context, String s, int i)
        throws a
    {
        throw new a("local only VersionPolicy should not load from remote");
    }

    public final int zzb(Context context, String s, boolean flag)
    {
        return 0;
    }

    a(int i)
    {
        zzbgO = i;
        super();
    }
}
