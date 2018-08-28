// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut;

import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;

public interface GcoreClearcutLogEventBuilder
{

    public abstract GcorePendingResult logAsync();

    public abstract GcoreClearcutLogEventBuilder setEventCode(int i);

    public abstract GcoreClearcutLogEventBuilder setLogSourceName(String s);

    public abstract GcoreClearcutLogEventBuilder setUploadAccountName(String s);
}
