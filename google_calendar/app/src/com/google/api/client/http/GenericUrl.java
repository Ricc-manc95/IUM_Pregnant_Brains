// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.api.client.http:
//            UrlEncodedParser

public final class GenericUrl extends GenericData
{

    private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:", false);
    private String fragment;
    private String host;
    public List pathParts;
    private int port;
    private String scheme;
    private String userInfo;

    public GenericUrl()
    {
        port = -1;
    }

    public GenericUrl(String s)
    {
        this(parseURL(s));
    }

    private GenericUrl(String s, String s1, int i, String s2, String s3, String s4, String s5)
    {
        Object obj = null;
        super();
        port = -1;
        scheme = s.toLowerCase();
        host = s1;
        port = i;
        pathParts = toPathParts(s2);
        if (s3 != null)
        {
            s = CharEscapers.decodeUri(s3);
        } else
        {
            s = null;
        }
        fragment = s;
        if (s4 != null)
        {
            UrlEncodedParser.parse(s4, this);
        }
        s = obj;
        if (s5 != null)
        {
            s = CharEscapers.decodeUri(s5);
        }
        userInfo = s;
    }

    public GenericUrl(URL url)
    {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    static void addQueryParams(Set set1, StringBuilder stringbuilder)
    {
        set1 = set1.iterator();
        boolean flag = true;
        do
        {
            if (!set1.hasNext())
            {
                break;
            }
            Object obj1 = (java.util.Map.Entry)set1.next();
            Object obj = ((java.util.Map.Entry) (obj1)).getValue();
            if (obj != null)
            {
                obj1 = (String)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = CharEscapers.URI_QUERY_STRING_ESCAPER.escape(((String) (obj1)));
                if (obj instanceof Collection)
                {
                    obj = ((Collection)obj).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        flag = appendParam(flag, stringbuilder, ((String) (obj1)), ((Iterator) (obj)).next());
                    }
                } else
                {
                    flag = appendParam(flag, stringbuilder, ((String) (obj1)), obj);
                }
            }
        } while (true);
    }

    private static boolean appendParam(boolean flag, StringBuilder stringbuilder, String s, Object obj)
    {
        if (flag)
        {
            flag = false;
            stringbuilder.append('?');
        } else
        {
            stringbuilder.append('&');
        }
        stringbuilder.append(s);
        s = obj.toString();
        s = CharEscapers.URI_QUERY_STRING_ESCAPER.escape(s);
        if (s.length() != 0)
        {
            stringbuilder.append('=').append(s);
        }
        return flag;
    }

    private static URL parseURL(String s)
    {
        try
        {
            s = new URL(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
        return s;
    }

    public static List toPathParts(String s)
    {
        if (s == null || s.length() == 0)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        boolean flag = true;
        int i = 0;
        while (flag) 
        {
            int j = s.indexOf('/', i);
            String s1;
            if (j != -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                s1 = s.substring(i, j);
            } else
            {
                s1 = s.substring(i);
            }
            arraylist.add(CharEscapers.decodeUri(s1));
            i = j + 1;
        }
        return arraylist;
    }

    public final String build()
    {
        Object obj = new StringBuilder();
        Object obj1 = scheme;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        ((StringBuilder) (obj)).append((String)obj1);
        ((StringBuilder) (obj)).append("://");
        if (userInfo != null)
        {
            obj1 = userInfo;
            ((StringBuilder) (obj)).append(CharEscapers.URI_USERINFO_ESCAPER.escape(((String) (obj1)))).append('@');
        }
        obj1 = host;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        ((StringBuilder) (obj)).append((String)obj1);
        int i = port;
        if (i != -1)
        {
            ((StringBuilder) (obj)).append(':').append(i);
        }
        obj = String.valueOf(((StringBuilder) (obj)).toString());
        obj1 = new StringBuilder();
        if (pathParts != null)
        {
            int k = pathParts.size();
            for (int j = 0; j < k; j++)
            {
                String s = (String)pathParts.get(j);
                if (j != 0)
                {
                    ((StringBuilder) (obj1)).append('/');
                }
                if (s.length() != 0)
                {
                    ((StringBuilder) (obj1)).append(CharEscapers.URI_PATH_ESCAPER.escape(s));
                }
            }

        }
        addQueryParams(entrySet(), ((StringBuilder) (obj1)));
        String s1 = fragment;
        if (s1 != null)
        {
            ((StringBuilder) (obj1)).append('#').append(URI_FRAGMENT_ESCAPER.escape(s1));
        }
        obj1 = String.valueOf(((StringBuilder) (obj1)).toString());
        if (((String) (obj1)).length() != 0)
        {
            return ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            return new String(((String) (obj)));
        }
    }

    public final volatile GenericData clone()
    {
        return (GenericUrl)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        GenericUrl genericurl = (GenericUrl)super.clone();
        if (pathParts != null)
        {
            genericurl.pathParts = new ArrayList(pathParts);
        }
        return genericurl;
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof GenericUrl))
        {
            return false;
        } else
        {
            obj = (GenericUrl)obj;
            return build().equals(((GenericUrl) (obj)).toString());
        }
    }

    public final Object getFirst(String s)
    {
label0:
        {
            Object obj = get(s);
            s = ((String) (obj));
            if (obj instanceof Collection)
            {
                s = ((Collection)obj).iterator();
                if (!s.hasNext())
                {
                    break label0;
                }
                s = ((String) (s.next()));
            }
            return s;
        }
        return null;
    }

    public final int hashCode()
    {
        return build().hashCode();
    }

    public final GenericData set(String s, Object obj)
    {
        return (GenericUrl)super.set(s, obj);
    }

    public final String toString()
    {
        return build();
    }

    public final URL toURL(String s)
    {
        try
        {
            s = new URL(parseURL(build()), s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new IllegalArgumentException(s);
        }
        return s;
    }

}
