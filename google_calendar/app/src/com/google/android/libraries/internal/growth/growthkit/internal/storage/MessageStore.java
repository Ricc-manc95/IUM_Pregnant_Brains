// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.MessageLite;
import java.util.Map;

public interface MessageStore
{

    public abstract ListenableFuture clearAndPutAll(Map map);

    public abstract ListenableFuture contains(String s);

    public abstract ListenableFuture getAll();

    public abstract ListenableFuture put(String s, MessageLite messagelite);

    public abstract ListenableFuture putAll(Map map);

    public abstract ListenableFuture remove(String s);
}
