// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.gms.pseudonymous.PseudonymousIdToken;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousIdToken;

public final class GcorePseudonymousIdTokenImpl
    implements GcorePseudonymousIdToken
{

    private final PseudonymousIdToken token;

    public GcorePseudonymousIdTokenImpl(PseudonymousIdToken pseudonymousidtoken)
    {
        token = pseudonymousidtoken;
    }

    public final String getValue()
    {
        return token.mValue;
    }
}
