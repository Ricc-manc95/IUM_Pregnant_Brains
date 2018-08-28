// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;


// Referenced classes of package com.google.apps.xplat.tracing:
//            TracerBackend, TraceSampler

public interface TracerConfig
{

    public abstract TracerBackend getBackend();

    public abstract int getMinLevel();

    public abstract TraceSampler getSampler();
}
