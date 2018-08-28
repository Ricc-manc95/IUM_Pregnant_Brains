// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import java.util.ArrayList;
import java.util.List;

public final class pragmaStatements
{

    public boolean forceWriteAheadLogging;
    public List pragmaStatements;

    ()
    {
        forceWriteAheadLogging = false;
        pragmaStatements = new ArrayList();
    }
}
