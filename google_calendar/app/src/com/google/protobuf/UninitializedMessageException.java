// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


public final class UninitializedMessageException extends RuntimeException
{

    public static final long serialVersionUID = 0x986022c4d65db14dL;

    public UninitializedMessageException()
    {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
