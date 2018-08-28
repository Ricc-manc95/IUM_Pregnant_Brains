// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace.unsafe;

import io.grpc.Context;

public final class ContextUtils
{

    public static final io.grpc.Context.Key CONTEXT_SPAN_KEY = Context.key("opencensus-trace-span-key");

}
