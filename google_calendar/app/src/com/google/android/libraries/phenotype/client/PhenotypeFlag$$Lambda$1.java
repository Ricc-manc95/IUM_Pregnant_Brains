// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.Context;
import com.google.android.gsf.Gservices;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            PhenotypeFlag

final class arg._cls1
    implements eFunction
{

    private final PhenotypeFlag arg$1;

    public final Object execute()
    {
        PhenotypeFlag phenotypeflag = arg$1;
        return Gservices.getString(PhenotypeFlag.context.getContentResolver(), phenotypeflag.gservicesFlagName, null);
    }

    eFunction(PhenotypeFlag phenotypeflag)
    {
        arg$1 = phenotypeflag;
    }
}
