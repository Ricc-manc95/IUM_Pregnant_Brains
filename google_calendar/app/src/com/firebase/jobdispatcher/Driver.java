// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


// Referenced classes of package com.firebase.jobdispatcher:
//            JobValidator, Job

public interface Driver
{

    public abstract int cancel(String s);

    public abstract JobValidator getValidator();

    public abstract boolean isAvailable();

    public abstract int schedule(Job job);
}
