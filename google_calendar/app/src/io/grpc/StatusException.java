// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Status, Metadata

public final class StatusException extends Exception
{

    public static final long serialVersionUID = 0xf6d3d10447f31100L;
    public final Status status;
    public final Metadata trailers;

    public StatusException(Status status1)
    {
        this(status1, null);
    }

    private StatusException(Status status1, Metadata metadata)
    {
        super(Status.formatThrowableMessage(status1), status1.cause);
        status = status1;
        trailers = null;
    }
}
