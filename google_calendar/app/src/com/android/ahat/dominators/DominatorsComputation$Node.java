// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ahat.dominators;


// Referenced classes of package com.android.ahat.dominators:
//            DominatorsComputation

public static interface 
{

    public abstract Object getDominatorsComputationState();

    public abstract Iterable getReferencesForDominators();

    public abstract void setDominator( );

    public abstract void setDominatorsComputationState(Object obj);
}
