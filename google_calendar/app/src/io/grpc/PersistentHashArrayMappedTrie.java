// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public final class PersistentHashArrayMappedTrie
{

    public final Node root;

    PersistentHashArrayMappedTrie()
    {
        this(null);
    }

    private PersistentHashArrayMappedTrie(Node node)
    {
        root = null;
    }
}