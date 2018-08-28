// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;


public interface Poolable
{

    public abstract void acquireReference();

    public abstract int getRefCount();

    public abstract boolean isEligibleForPooling();
}
