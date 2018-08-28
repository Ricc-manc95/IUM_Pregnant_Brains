// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// Referenced classes of package com.google.android.gms.drive.metadata:
//            MetadataField

public abstract class zza
    implements MetadataField
{

    public final String zzbbB;

    public zza(String s, int i)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fieldName"));
        } else
        {
            zzbbB = (String)s;
            Collections.singleton(s);
            Collections.emptySet();
            return;
        }
    }

    public zza(String s, Collection collection, Collection collection1, int i)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fieldName"));
        } else
        {
            zzbbB = (String)s;
            Collections.unmodifiableSet(new HashSet(collection));
            Collections.unmodifiableSet(new HashSet(collection1));
            return;
        }
    }

    public final String getName()
    {
        return zzbbB;
    }

    public String toString()
    {
        return zzbbB;
    }

    public final Object zzy(Bundle bundle)
    {
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("bundle"));
        }
        if (bundle.get(zzbbB) != null)
        {
            return zzz(bundle);
        } else
        {
            return null;
        }
    }

    public abstract Object zzz(Bundle bundle);
}
