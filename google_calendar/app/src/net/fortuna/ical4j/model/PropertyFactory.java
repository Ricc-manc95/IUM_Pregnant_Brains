// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;

// Referenced classes of package net.fortuna.ical4j.model:
//            Property

public interface PropertyFactory
    extends Serializable
{

    public abstract Property createProperty(String s);
}