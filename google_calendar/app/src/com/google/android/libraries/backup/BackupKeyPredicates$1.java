// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.backup;

import java.util.Collection;

// Referenced classes of package com.google.android.libraries.backup:
//            BackupKeyPredicate

public final class val.collection
    implements BackupKeyPredicate
{

    private final Collection val$collection;

    public final boolean shouldBeBackedUp(String s)
    {
        return val$collection.contains(s);
    }

    public ()
    {
        val$collection = collection1;
        super();
    }
}
