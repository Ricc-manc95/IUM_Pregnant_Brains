// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.fortuna.ical4j.util:
//            CompatibilityHints

public final class Uris
{

    private static final Pattern CID_PATTERN = Pattern.compile("(?i)^cid:.*");
    private static final Pattern NOTES_CID_REPLACEMENT_PATTERN = Pattern.compile("[<>]");

    public static URI create(String s)
        throws URISyntaxException
    {
        URI uri = new URI(encode(s));
        return uri;
        Object obj;
        obj;
        int i;
        if (!CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
        {
            break MISSING_BLOCK_LABEL_131;
        }
        obj = encode(s);
        i = ((String) (obj)).indexOf(':');
        if (i == -1 || i >= ((String) (obj)).length() - 1)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        obj = new URI(((String) (obj)).substring(0, i), ((String) (obj)).substring(i + 1), null);
        return ((URI) (obj));
        obj;
        try
        {
            obj = new URI("net.fortunal.ical4j.invalid", s, null);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Could not build URI from ".concat(s);
            } else
            {
                s = new String("Could not build URI from ");
            }
            throw new IllegalArgumentException(s);
        }
        return ((URI) (obj));
        throw obj;
    }

    public static String decode(String s)
    {
        return s;
    }

    public static String encode(String s)
    {
        String s1 = s;
        if (CompatibilityHints.isHintEnabled("ical4j.compatibility.notes"))
        {
            s1 = s;
            if (CID_PATTERN.matcher(s).matches())
            {
                s1 = NOTES_CID_REPLACEMENT_PATTERN.matcher(s).replaceAll("");
            }
        }
        return s1;
    }

}
