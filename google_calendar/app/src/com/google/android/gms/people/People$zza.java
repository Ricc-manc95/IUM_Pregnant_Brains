// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;

import com.google.android.gms.common.api.GoogleApiClient;

// Referenced classes of package com.google.android.gms.people:
//            People

public abstract class piClient extends com.google.android.gms.internal.Client
{

    public piClient(GoogleApiClient googleapiclient)
    {
        super(People.API_1P, googleapiclient);
    }
}
