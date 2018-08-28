// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


final class _cls9 extends IllegalArgumentException
{

    _cls9(int i, int j)
    {
        super((new StringBuilder(54)).append("Unpaired surrogate at index ").append(i).append(" of ").append(j).toString());
    }
}
