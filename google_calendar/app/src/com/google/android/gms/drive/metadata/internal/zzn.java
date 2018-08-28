// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zzk

public final class zzn extends zzk
    implements MetadataField
{

    public static final zzf.zza zzbbP = new _cls1();

    public zzn(int i)
    {
        super("parents", Collections.emptySet(), Arrays.asList(new String[] {
            "parentsExtra", "dbInstanceId", "parentsExtraHolder"
        }), 0x3e8fa0);
    }

    protected final Collection zzE(Bundle bundle)
    {
        bundle = super.zzE(bundle);
        if (bundle == null)
        {
            return null;
        } else
        {
            return new HashSet(bundle);
        }
    }

    protected final Object zzz(Bundle bundle)
    {
        return zzE(bundle);
    }


    private class _cls1
        implements zzf.zza
    {

        public final String zzCR()
        {
            return "parentsExtraHolder";
        }

        _cls1()
        {
        }
    }

}
