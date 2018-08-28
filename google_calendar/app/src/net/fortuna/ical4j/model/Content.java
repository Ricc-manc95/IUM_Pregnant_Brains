// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;

public abstract class Content
    implements Serializable
{

    public static final long serialVersionUID = 0xd7db15711101f14bL;

    public Content()
    {
    }

    public abstract String getValue();
}
