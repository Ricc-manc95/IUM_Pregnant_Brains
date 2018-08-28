// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Context

public static abstract class rationException
{

    public abstract Context current();

    public abstract void detach(Context context, Context context1);

    public Context doAttach(Context context)
    {
        current();
        throw new UnsupportedOperationException("Deprecated. Do not call.");
    }

    public rationException()
    {
    }
}
