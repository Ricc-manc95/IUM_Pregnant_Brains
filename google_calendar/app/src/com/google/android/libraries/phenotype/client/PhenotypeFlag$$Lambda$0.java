// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import java.util.Map;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            ConfigurationContentLoader, PhenotypeFlag

final class arg._cls2
    implements eFunction
{

    private final PhenotypeFlag arg$1;
    private final ConfigurationContentLoader arg$2;

    public final Object execute()
    {
        PhenotypeFlag phenotypeflag = arg$1;
        return (String)arg$2.getFlags().get(phenotypeflag.mendelFlagName);
    }

    er(PhenotypeFlag phenotypeflag, ConfigurationContentLoader configurationcontentloader)
    {
        arg$1 = phenotypeflag;
        arg$2 = configurationcontentloader;
    }
}
