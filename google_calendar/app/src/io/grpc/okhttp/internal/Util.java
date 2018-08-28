// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Util
{

    public static final String EMPTY_STRING_ARRAY[] = new String[0];
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static transient List immutableList(Object aobj[])
    {
        return Collections.unmodifiableList(Arrays.asList((Object[])((Object []) (aobj)).clone()));
    }

    public static Object[] intersect(Class class1, Object aobj[], Object aobj1[])
    {
        ArrayList arraylist = new ArrayList();
        int k = aobj.length;
        int i = 0;
label0:
        do
        {
            if (i < k)
            {
                Object obj = aobj[i];
                int l = aobj1.length;
                int j = 0;
                do
                {
label1:
                    {
                        if (j < l)
                        {
                            Object obj1 = aobj1[j];
                            if (!obj.equals(obj1))
                            {
                                break label1;
                            }
                            arraylist.add(obj1);
                        }
                        i++;
                        continue label0;
                    }
                    j++;
                } while (true);
            }
            return arraylist.toArray((Object[])Array.newInstance(class1, arraylist.size()));
        } while (true);
    }

}
