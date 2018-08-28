// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;

import android.support.v4.util.LruCache;
import com.google.common.base.Function;
import com.google.common.base.Platform;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.flair:
//            FlairAllocator

public final class FlairAllocatorImpl
    implements FlairAllocator
{

    private final LruCache cache;
    private final Map triggers;
    private final Function wordSplitter;

    public FlairAllocatorImpl(List list, Function function)
    {
        boolean flag;
        if (!list.isEmpty())
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
        HashMap hashmap = new HashMap();
        for (list = list.iterator(); list.hasNext();)
        {
            com.google.calendar.v2.libs.proto.FlairProto.Flair flair = (com.google.calendar.v2.libs.proto.FlairProto.Flair)list.next();
            Iterator iterator = flair.trigger_.iterator();
            while (iterator.hasNext()) 
            {
                com.google.calendar.v2.libs.proto.FlairProto.Flair.FlairTrigger flairtrigger = (com.google.calendar.v2.libs.proto.FlairProto.Flair.FlairTrigger)iterator.next();
                hashmap.put(new TriggerKey((String[])function.apply(flairtrigger.word_)), new FlairPair(flairtrigger.score_, flair.key_));
            }
        }

        this(((Map) (hashmap)), function);
    }

    private FlairAllocatorImpl(Map map, Function function)
    {
        cache = new LruCache(250);
        if (map == null)
        {
            throw new NullPointerException();
        }
        triggers = (Map)map;
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            wordSplitter = (Function)function;
            return;
        }
    }

    public final String allocateFlair(String s)
    {
        if (Platform.stringIsNullOrEmpty(s))
        {
            return null;
        }
        Object obj = (String)cache.get(s);
        if (obj != null)
        {
            return Platform.emptyToNull(((String) (obj)));
        }
        obj = new HashSet();
        String as[] = (String[])wordSplitter.apply(s);
        Object obj2 = new StringBuilder();
        ((StringBuilder) (obj2)).append("[");
        int k = Math.min(as.length, 10);
        for (int i = 0; i < k; i++)
        {
            ((StringBuilder) (obj2)).setLength(1);
            ((StringBuilder) (obj2)).append(as[i]).append("]");
            ((Set) (obj)).add(new TriggerKey(((StringBuilder) (obj2))));
            ((StringBuilder) (obj2)).setLength(((StringBuilder) (obj2)).length() - 1);
            int l = Math.min(i + 5, k);
            for (int j = i + 1; j < l; j++)
            {
                ((StringBuilder) (obj2)).append(", ").append(as[j]).append("]");
                ((Set) (obj)).add(new TriggerKey(((StringBuilder) (obj2))));
                ((StringBuilder) (obj2)).setLength(((StringBuilder) (obj2)).length() - 1);
            }

        }

        obj2 = ((Set) (obj)).iterator();
        obj = null;
        do
        {
            if (((Iterator) (obj2)).hasNext())
            {
                Object obj1 = (TriggerKey)((Iterator) (obj2)).next();
                if (triggers.containsKey(obj1))
                {
                    obj1 = (FlairPair)triggers.get(obj1);
                    boolean flag;
                    if (obj == null)
                    {
                        flag = true;
                    } else
                    if (((FlairPair) (obj1)).score > ((FlairPair) (obj)).score)
                    {
                        flag = true;
                    } else
                    if (((FlairPair) (obj1)).score == ((FlairPair) (obj)).score)
                    {
                        if (((FlairPair) (obj1)).key.length() > ((FlairPair) (obj)).key.length())
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        obj = obj1;
                    }
                }
            } else
            {
                if (obj != null)
                {
                    cache.put(s, ((FlairPair) (obj)).key);
                    return ((FlairPair) (obj)).key;
                }
                cache.put(s, "");
                return null;
            }
        } while (true);
    }

    private class TriggerKey
    {

        private final int hashCode;
        private final String wordsAsString;

        public final boolean equals(Object obj)
        {
            if (obj == null || !(obj instanceof TriggerKey))
            {
                return false;
            } else
            {
                return wordsAsString.equals(((TriggerKey)obj).wordsAsString);
            }
        }

        public final int hashCode()
        {
            return hashCode;
        }

        public final String toString()
        {
            return wordsAsString;
        }

        public TriggerKey(StringBuilder stringbuilder)
        {
            wordsAsString = stringbuilder.toString();
            hashCode = wordsAsString.hashCode();
        }

        public TriggerKey(String as[])
        {
            wordsAsString = Arrays.toString(as);
            hashCode = wordsAsString.hashCode();
        }
    }


    private class FlairPair
    {

        public final String key;
        public final int score;

        public FlairPair(int i, String s)
        {
            score = i;
            key = s;
        }
    }

}
