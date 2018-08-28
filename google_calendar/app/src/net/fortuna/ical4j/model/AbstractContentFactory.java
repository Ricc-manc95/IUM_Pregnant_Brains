// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.util.HashMap;
import java.util.Map;

public class AbstractContentFactory
{

    public final Map defaultFactories = new HashMap();
    public final Map extendedFactories = new HashMap();

    public AbstractContentFactory()
    {
    }
}
