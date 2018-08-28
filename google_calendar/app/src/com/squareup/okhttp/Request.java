// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;


// Referenced classes of package com.squareup.okhttp:
//            Headers, HttpUrl

public final class Request
{

    public final Headers headers;
    private final String method;
    private final Object tag = this;
    public final HttpUrl url;

    public Request(Builder builder)
    {
        url = builder.url;
        method = builder.method;
        headers = new Headers(builder.headers);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("Request{method=")).append(method).append(", url=").append(url).append(", tag=");
        Object obj;
        if (tag != this)
        {
            obj = tag;
        } else
        {
            obj = null;
        }
        return stringbuilder.append(obj).append('}').toString();
    }

    private class Builder
    {

        public Headers.Builder headers;
        public String method;
        public HttpUrl url;

        public final Builder header(String s, String s1)
        {
            Headers.Builder builder = headers;
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
            for (int k = 0; k < builder.namesAndValues.size(); k = i1 + 2)
            {
                i1 = k;
                if (s.equalsIgnoreCase((String)builder.namesAndValues.get(k)))
                {
                    builder.namesAndValues.remove(k);
                    builder.namesAndValues.remove(k);
                    i1 = k - 2;
                }
            }

            builder.namesAndValues.add(s);
            builder.namesAndValues.add(s1.trim());
            return this;
        }

        public Builder()
        {
            method = "GET";
            headers = new Headers.Builder();
        }
    }

}
