// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth;

import java.util.Map;

public interface RequestMetadataCallback
{

    public abstract void onFailure(Throwable throwable);

    public abstract void onSuccess(Map map);
}