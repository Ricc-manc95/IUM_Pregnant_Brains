// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;


final class  extends Exception
{

    public (String s, int i)
    {
        super(String.format("%s, CODE: %s", new Object[] {
            s, Integer.valueOf(i)
        }));
    }
}
