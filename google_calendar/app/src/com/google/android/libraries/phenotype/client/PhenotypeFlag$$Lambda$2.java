// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.Context;
import com.google.android.gsf.Gservices;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            PhenotypeFlag

final class arg._cls2
    implements eFunction
{

    private final String arg$1;
    private final boolean arg$2;

    public final Object execute()
    {
        String s = arg$1;
        boolean flag = arg$2;
        return Boolean.valueOf(Gservices.getBoolean(PhenotypeFlag.context.getContentResolver(), s, flag));
    }

    eFunction(String s, boolean flag)
    {
        arg$1 = s;
        arg$2 = flag;
    }
}
