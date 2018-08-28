// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


final class Hit
{

    public final long mHitId;
    public String mHitString;
    public final long mHitTime;
    public String mHitUrl;

    Hit(String s, long l, long l1)
    {
        mHitString = null;
        mHitId = l;
        mHitTime = l1;
    }
}
