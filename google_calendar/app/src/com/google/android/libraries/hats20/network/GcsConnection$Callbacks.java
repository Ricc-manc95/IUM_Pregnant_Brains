// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.network;

import java.util.Map;

public interface I
{

    public abstract void onFailed(Exception exception);

    public abstract void onSucceeded(int i, String s, Map map);
}
