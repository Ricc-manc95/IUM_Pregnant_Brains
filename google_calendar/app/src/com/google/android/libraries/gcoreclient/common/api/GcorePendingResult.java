// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api;


// Referenced classes of package com.google.android.libraries.gcoreclient.common.api:
//            GcoreResultCallback

public interface GcorePendingResult
{

    public abstract void cancel();

    public abstract void setResultCallback(GcoreResultCallback gcoreresultcallback);
}
