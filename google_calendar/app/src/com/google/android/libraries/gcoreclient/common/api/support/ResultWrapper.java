// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.api.Result;
import com.google.android.libraries.gcoreclient.common.api.GcoreResult;

public interface ResultWrapper
{

    public abstract GcoreResult wrap(Result result);
}
