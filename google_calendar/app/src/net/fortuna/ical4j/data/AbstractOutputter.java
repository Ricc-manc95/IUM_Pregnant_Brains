// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.nio.charset.Charset;
import net.fortuna.ical4j.util.CompatibilityHints;

public class AbstractOutputter
{

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public int foldLength;
    public boolean validating;

    public AbstractOutputter()
    {
        this(true);
    }

    private AbstractOutputter(boolean flag)
    {
        byte byte0;
        if (CompatibilityHints.isHintEnabled("ical4j.compatibility.outlook"))
        {
            byte0 = 75;
        } else
        {
            byte0 = 73;
        }
        this(true, byte0);
    }

    private AbstractOutputter(boolean flag, int i)
    {
        validating = flag;
        foldLength = i;
    }

}
