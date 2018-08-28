// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;


interface CellValue
{

    public abstract void record(Object obj);

    public abstract com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value toValueProto();
}
