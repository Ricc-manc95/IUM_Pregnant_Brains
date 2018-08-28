// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.stitch.util.Preconditions;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, HprofClass, ParseContext

public final class HprofClassInstance extends HprofObject
{

    public final HprofClass clazz;

    public HprofClassInstance(int i, HprofClass hprofclass)
    {
        super(i);
        if (hprofclass == null)
        {
            throw new NullPointerException();
        } else
        {
            clazz = (HprofClass)hprofclass;
            return;
        }
    }

    public final String buildLeakSegment(ParseContext parsecontext, int i)
    {
        if (i >= 0 && i < clazz.fieldsCount)
        {
            Object obj = clazz.getDeclaringClassForField(i);
            if (obj == clazz)
            {
                String s = parsecontext.readString(clazz.classNamePosition);
                parsecontext = getChildName(parsecontext, i);
                return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(parsecontext).length())).append(s).append('#').append(parsecontext).toString();
            } else
            {
                String s1 = parsecontext.readString(clazz.classNamePosition);
                obj = parsecontext.readString(((HprofClass) (obj)).classNamePosition);
                parsecontext = getChildName(parsecontext, i);
                return (new StringBuilder(String.valueOf(s1).length() + 2 + String.valueOf(obj).length() + String.valueOf(parsecontext).length())).append(s1).append(':').append(((String) (obj))).append('#').append(parsecontext).toString();
            }
        } else
        {
            return parsecontext.readString(clazz.classNamePosition);
        }
    }

    public final int computeShallowSize(ParseContext parsecontext)
    {
        return clazz.instanceSize;
    }

    public final int getChildCount(ParseContext parsecontext)
    {
        return clazz.fieldsCount;
    }

    public final String getChildName(ParseContext parsecontext, int i)
    {
        return clazz.getFieldName(parsecontext, i);
    }

    public final int getChildValue(ParseContext parsecontext, int i)
    {
        HprofClass hprofclass = clazz;
        int j = hprofclass.fieldsCount;
        if (i < 0 || i >= j)
        {
            if (i < 0)
            {
                parsecontext = Preconditions.format("%s (%s) must not be negative", new Object[] {
                    "index", Integer.valueOf(i)
                });
            } else
            {
                if (j < 0)
                {
                    throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
                }
                parsecontext = Preconditions.format("%s (%s) must be less than size (%s)", new Object[] {
                    "index", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
            throw new IndexOutOfBoundsException(parsecontext);
        } else
        {
            return hprofclass.getFieldValueInternal(parsecontext, position + parsecontext.idSize + 4 + parsecontext.idSize + 4, i);
        }
    }
}
