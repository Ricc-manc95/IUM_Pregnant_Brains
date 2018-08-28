// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;


final class alChoreographer extends ThreadLocal
{

    protected final Object initialValue()
    {
        return new alChoreographer();
    }

    alChoreographer()
    {
    }
}
