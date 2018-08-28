// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.inject;


// Referenced classes of package com.google.android.libraries.hats20.inject:
//            CommonHatsModule, HatsModule

public final class CommonHatsModuleInitializer
{

    public CommonHatsModuleInitializer()
    {
    }

    static 
    {
        CommonHatsModule commonhatsmodule = new CommonHatsModule();
        if (HatsModule.instance == null)
        {
            HatsModule.instance = commonhatsmodule;
        }
    }
}
