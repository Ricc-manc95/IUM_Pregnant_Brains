// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpMediaType
{

    private static final Pattern FULL_MEDIA_TYPE_REGEX = Pattern.compile((new StringBuilder(String.valueOf("[^\\s/=;\"]+").length() + 14 + String.valueOf("[^\\s/=;\"]+").length() + String.valueOf(";.*").length())).append("\\s*(").append("[^\\s/=;\"]+").append(")/(").append("[^\\s/=;\"]+").append(")\\s*(").append(";.*").append(")?").toString(), 32);
    private static final Pattern PARAMETER_REGEX;
    public static final Pattern TOKEN_REGEX = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    private static final Pattern TYPE_REGEX = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    private String cachedBuildResult;
    public final SortedMap parameters = new TreeMap();
    private String subType;
    private String type;

    public HttpMediaType(String s)
    {
        type = "application";
        subType = "octet-stream";
        s = FULL_MEDIA_TYPE_REGEX.matcher(s);
        if (!s.matches())
        {
            throw new IllegalArgumentException(String.valueOf("Type must be in the 'maintype/subtype; parameter=value' format"));
        }
        String s1 = s.group(1);
        if (!TYPE_REGEX.matcher(s1).matches())
        {
            throw new IllegalArgumentException(String.valueOf("Type contains reserved characters"));
        }
        type = s1;
        cachedBuildResult = null;
        s1 = s.group(2);
        if (!TYPE_REGEX.matcher(s1).matches())
        {
            throw new IllegalArgumentException(String.valueOf("Subtype contains reserved characters"));
        }
        subType = s1;
        cachedBuildResult = null;
        s = s.group(3);
        if (s != null)
        {
            String s3;
            for (Matcher matcher = PARAMETER_REGEX.matcher(s); matcher.find(); setParameter(s3, s))
            {
                s3 = matcher.group(1);
                String s2 = matcher.group(3);
                s = s2;
                if (s2 == null)
                {
                    s = matcher.group(2);
                }
            }

        }
    }

    private final boolean equalsIgnoreParameters(HttpMediaType httpmediatype)
    {
        return httpmediatype != null && type.equalsIgnoreCase(httpmediatype.type) && subType.equalsIgnoreCase(httpmediatype.subType);
    }

    public static boolean equalsIgnoreParameters(String s, String s1)
    {
        return s1 != null && (new HttpMediaType(s)).equalsIgnoreParameters(new HttpMediaType(s1));
    }

    public final String build()
    {
        if (cachedBuildResult != null)
        {
            return cachedBuildResult;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(type);
        stringbuilder.append('/');
        stringbuilder.append(subType);
        if (parameters != null)
        {
            Object obj;
            for (Iterator iterator = parameters.entrySet().iterator(); iterator.hasNext(); stringbuilder.append(((String) (obj))))
            {
                obj = (java.util.Map.Entry)iterator.next();
                String s = (String)((java.util.Map.Entry) (obj)).getValue();
                stringbuilder.append("; ");
                stringbuilder.append((String)((java.util.Map.Entry) (obj)).getKey());
                stringbuilder.append("=");
                obj = s;
                if (!TOKEN_REGEX.matcher(s).matches())
                {
                    obj = s.replace("\\", "\\\\").replace("\"", "\\\"");
                    obj = (new StringBuilder(String.valueOf(obj).length() + 2)).append("\"").append(((String) (obj))).append("\"").toString();
                }
            }

        }
        cachedBuildResult = stringbuilder.toString();
        return cachedBuildResult;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof HttpMediaType)
        {
            if (equalsIgnoreParameters(((HttpMediaType) (obj = (HttpMediaType)obj))) && parameters.equals(((HttpMediaType) (obj)).parameters))
            {
                return true;
            }
        }
        return false;
    }

    public final Charset getCharsetParameter()
    {
        String s = (String)parameters.get("charset".toLowerCase());
        if (s == null)
        {
            return null;
        } else
        {
            return Charset.forName(s);
        }
    }

    public final int hashCode()
    {
        return build().hashCode();
    }

    public final HttpMediaType setParameter(String s, String s1)
    {
        if (s1 == null)
        {
            cachedBuildResult = null;
            parameters.remove(s.toLowerCase());
            return this;
        }
        if (!TOKEN_REGEX.matcher(s).matches())
        {
            throw new IllegalArgumentException(String.valueOf("Name contains reserved characters"));
        } else
        {
            cachedBuildResult = null;
            parameters.put(s.toLowerCase(), s1);
            return this;
        }
    }

    public final String toString()
    {
        return build();
    }

    static 
    {
        String s = (new StringBuilder(String.valueOf("\"([^\"]*)\"").length() + 1 + String.valueOf("[^\\s;\"]*").length())).append("\"([^\"]*)\"").append("|").append("[^\\s;\"]*").toString();
        PARAMETER_REGEX = Pattern.compile((new StringBuilder(String.valueOf("[^\\s/=;\"]+").length() + 12 + String.valueOf(s).length())).append("\\s*;\\s*(").append("[^\\s/=;\"]+").append(")=(").append(s).append(")").toString());
    }
}
