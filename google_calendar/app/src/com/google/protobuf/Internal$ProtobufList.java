// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.List;
import java.util.RandomAccess;

public interface 
    extends List, RandomAccess
{

    public abstract boolean isModifiable();

    public abstract void makeImmutable();

    public abstract  mutableCopyWithCapacity(int i);
}
