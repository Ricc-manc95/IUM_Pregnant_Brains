// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Strings
{

    private static final Pattern ESCAPE_BACKSLASH_PATTERN = Pattern.compile("\\\\");
    private static final Pattern ESCAPE_NEWLINE_PATTERN = Pattern.compile("\r?\n");
    private static final Pattern ESCAPE_PUNCTUATION_PATTERN = Pattern.compile("([,;])");
    public static final Pattern PARAM_QUOTE_PATTERN = Pattern.compile("[:;,]|[^\\p{ASCII}]");
    private static final Pattern UNESCAPE_BACKSLASH_PATTERN = Pattern.compile("\\\\\\\\");
    private static final Pattern UNESCAPE_NEWLINE_PATTERN = Pattern.compile("(?<!\\\\)\\\\n");
    private static final Pattern UNESCAPE_PUNCTUATION_PATTERN = Pattern.compile("\\\\([,;\"])");

    public static String escape(String s)
    {
        String s1;
        if (s != null)
        {
            s1 = ESCAPE_BACKSLASH_PATTERN.matcher(s).replaceAll("\\\\\\\\");
        } else
        {
            s1 = s;
        }
        s = s1;
        if (s1 != null)
        {
            s = ESCAPE_NEWLINE_PATTERN.matcher(s1).replaceAll("\\\\n");
        }
        s1 = s;
        if (s != null)
        {
            s1 = ESCAPE_PUNCTUATION_PATTERN.matcher(s).replaceAll("\\\\$1");
        }
        return s1;
    }

    public static String escapeNewline(String s)
    {
        String s1 = s;
        if (s != null)
        {
            s1 = ESCAPE_NEWLINE_PATTERN.matcher(s).replaceAll("\\\\n");
        }
        return s1;
    }

    public static String quote(Object obj)
    {
        if (obj != null)
        {
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(obj).length() + 2)).append("\"").append(((String) (obj))).append("\"").toString();
        } else
        {
            return "\"\"";
        }
    }

    public static String unescape(String s)
    {
        String s1;
        if (s != null)
        {
            s1 = UNESCAPE_PUNCTUATION_PATTERN.matcher(s).replaceAll("$1");
        } else
        {
            s1 = s;
        }
        s = s1;
        if (s1 != null)
        {
            s = UNESCAPE_NEWLINE_PATTERN.matcher(s1).replaceAll("\n");
        }
        s1 = s;
        if (s != null)
        {
            s1 = UNESCAPE_BACKSLASH_PATTERN.matcher(s).replaceAll("\\\\");
        }
        return s1;
    }

    public static String unquote(String s)
    {
        String s1 = s;
        if (s != null)
        {
            s1 = s;
            if (s.startsWith("\""))
            {
                s1 = s;
                if (s.endsWith("\""))
                {
                    s1 = s.substring(0, s.length() - 1).substring(1);
                }
            }
        }
        return s1;
    }

    public static String valueOf(Object obj)
    {
        if (obj == null)
        {
            return "";
        } else
        {
            return obj.toString();
        }
    }

}
