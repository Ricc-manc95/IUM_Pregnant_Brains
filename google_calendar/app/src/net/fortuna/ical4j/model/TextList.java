// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.fortuna.ical4j.util.Strings;

public final class TextList
    implements Serializable
{

    public static final long serialVersionUID = 0xfa34ff9cf39f46b4L;
    private List texts;

    public TextList()
    {
        texts = new CopyOnWriteArrayList();
    }

    public TextList(String s)
    {
        texts = new CopyOnWriteArrayList();
        Matcher matcher = Pattern.compile("([^\\\\](?:\\\\{2})),|([^\\\\]),").matcher(s);
        int i;
        if (matcher.find())
        {
            s = matcher.replaceAll("$1$2&quot;").split("&quot;");
        } else
        {
            s = s.split("(?<!\\\\),");
        }
        for (i = 0; i < s.length; i++)
        {
            texts.add(Strings.unescape(s[i]));
        }

    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = texts.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuffer.append(Strings.escape((String)iterator.next()));
            if (iterator.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
