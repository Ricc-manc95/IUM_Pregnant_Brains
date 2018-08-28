// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared.storage;

import com.google.common.util.concurrent.ListenableFuture;

public interface AsyncAccountService
{

    public abstract ListenableFuture getAccountKey(String s);
}
