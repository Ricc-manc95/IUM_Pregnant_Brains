// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof.collect;

import android.support.v4.util.SparseArrayCompat;
import java.nio.charset.Charset;

public final class TrieMap
{

    public final Edge head = new Edge();

    public TrieMap()
    {
    }

    public final Object putIfAbsent(String s, Object obj)
    {
        boolean flag;
        if (s.length() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        byte abyte0[] = s.getBytes(Charset.defaultCharset());
        Object obj1 = head;
        int j = abyte0.length;
        for (int i = 0; i < j;)
        {
            byte byte0 = abyte0[i];
            if (((Edge) (obj1)).edges == null)
            {
                obj1.edges = new SparseArrayCompat();
            }
            Edge edge = (Edge)((Edge) (obj1)).edges.get(byte0);
            s = edge;
            if (edge == null)
            {
                s = new Edge();
                ((Edge) (obj1)).edges.put(byte0, s);
            }
            i++;
            obj1 = s;
        }

        if (((Edge) (obj1)).value != null)
        {
            return ((Edge) (obj1)).value;
        } else
        {
            obj1.value = obj;
            return null;
        }
    }

    private class Edge
    {

        public SparseArrayCompat edges;
        public Object value;

        Edge()
        {
        }
    }

}
