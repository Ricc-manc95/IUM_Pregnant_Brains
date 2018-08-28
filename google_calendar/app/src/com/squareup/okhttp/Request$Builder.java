// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;

import java.util.List;

// Referenced classes of package com.squareup.okhttp:
//            HttpUrl

public final class <init>
{

    public namesAndValues headers;
    public String method;
    public HttpUrl url;

    public final <init> header(String s, String s1)
    {
        <init> <init>1 = headers;
        if (s == null)
        {
            throw new IllegalArgumentException("name == null");
        }
        if (s.isEmpty())
        {
            throw new IllegalArgumentException("name is empty");
        }
        int l = s.length();
        for (int i = 0; i < l; i++)
        {
            char c = s.charAt(i);
            if (c <= '\037' || c >= '\177')
            {
                throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", new Object[] {
                    Integer.valueOf(c), Integer.valueOf(i), s
                }));
            }
        }

        if (s1 == null)
        {
            throw new IllegalArgumentException("value == null");
        }
        l = s1.length();
        for (int j = 0; j < l; j++)
        {
            char c1 = s1.charAt(j);
            if (c1 <= '\037' || c1 >= '\177')
            {
                throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header value: %s", new Object[] {
                    Integer.valueOf(c1), Integer.valueOf(j), s1
                }));
            }
        }

        int i1;
        for (int k = 0; k < <init>1.namesAndValues.size(); k = i1 + 2)
        {
            i1 = k;
            if (s.equalsIgnoreCase((String)<init>1.namesAndValues.get(k)))
            {
                <init>1.namesAndValues.remove(k);
                <init>1.namesAndValues.remove(k);
                i1 = k - 2;
            }
        }

        <init>1.namesAndValues.add(s);
        <init>1.namesAndValues.add(s1.trim());
        return this;
    }

    public ()
    {
        method = "GET";
        headers = new <init>();
    }
}
