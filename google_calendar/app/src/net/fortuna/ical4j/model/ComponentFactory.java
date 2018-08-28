// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import net.fortuna.ical4j.util.CompatibilityHints;

public final class ComponentFactory
{

    public static ComponentFactory instance = new ComponentFactory();

    private ComponentFactory()
    {
    }

    public static boolean allowIllegalNames()
    {
        return CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed");
    }

    public static boolean isExperimentalName(String s)
    {
        return s.startsWith("X-") && s.length() > 2;
    }

}
