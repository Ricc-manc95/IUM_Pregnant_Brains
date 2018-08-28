// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.InputStream;

public interface 
{

    public abstract Object parse(InputStream inputstream);

    public abstract InputStream stream(Object obj);
}
