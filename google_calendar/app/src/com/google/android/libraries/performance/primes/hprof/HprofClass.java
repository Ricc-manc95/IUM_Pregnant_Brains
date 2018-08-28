// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.stitch.util.Preconditions;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, ParseContext

public final class HprofClass extends HprofObject
{

    public static HprofClass javaLangClass;
    public final int classNamePosition;
    public int fieldNamePositions[];
    public int fieldsCount;
    public int instanceSize;
    public int offsets[];
    public int staticFieldNamePositions[];
    public int staticFieldsSize;
    public int staticValueIds[];
    public HprofClass superClass;
    public int totalOffset;

    public HprofClass(int i, int j)
    {
        super(i);
        fieldsCount = -1;
        classNamePosition = j;
    }

    public static void skipClassConstants(ParseContext parsecontext)
    {
        ByteBuffer bytebuffer = parsecontext.buffer;
        short word0 = bytebuffer.getShort();
        for (int i = 0; i < (word0 & 0xffff); i++)
        {
            bytebuffer.getShort();
            byte byte0 = bytebuffer.get();
            int j = parsecontext.typeSizes[byte0];
            boolean flag;
            if (j > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            parsecontext.skipBytes(j);
        }

    }

    public final String buildLeakSegment(ParseContext parsecontext, int i)
    {
        if (i >= 0 && i < staticValueIds.length)
        {
            String s = parsecontext.readString(classNamePosition);
            parsecontext = getChildName(parsecontext, i);
            return (new StringBuilder(String.valueOf(s).length() + 8 + String.valueOf(parsecontext).length())).append("static ").append(s).append("#").append(parsecontext).toString();
        }
        parsecontext = String.valueOf(parsecontext.readString(classNamePosition));
        if (parsecontext.length() != 0)
        {
            return "static ".concat(parsecontext);
        } else
        {
            return new String("static ");
        }
    }

    public final int computeShallowSize(ParseContext parsecontext)
    {
        return staticFieldsSize + javaLangClass.instanceSize;
    }

    public final int getChildCount(ParseContext parsecontext)
    {
        return staticValueIds.length;
    }

    public final String getChildName(ParseContext parsecontext, int i)
    {
        return parsecontext.readString(staticFieldNamePositions[i]);
    }

    public final int getChildValue(ParseContext parsecontext, int i)
    {
        return staticValueIds[i];
    }

    final HprofClass getDeclaringClassForField(int i)
    {
        Object obj = this;
        do
        {
            int j = ((HprofClass) (obj)).fieldsCount;
            if (i < 0 || i >= j)
            {
                if (i < 0)
                {
                    obj = Preconditions.format("%s (%s) must not be negative", new Object[] {
                        "index", Integer.valueOf(i)
                    });
                } else
                {
                    if (j < 0)
                    {
                        throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
                    }
                    obj = Preconditions.format("%s (%s) must be less than size (%s)", new Object[] {
                        "index", Integer.valueOf(i), Integer.valueOf(j)
                    });
                }
                throw new IndexOutOfBoundsException(((String) (obj)));
            }
            if (i < ((HprofClass) (obj)).fieldNamePositions.length)
            {
                return ((HprofClass) (obj));
            }
            HprofClass hprofclass = ((HprofClass) (obj)).superClass;
            i -= ((HprofClass) (obj)).fieldNamePositions.length;
            obj = hprofclass;
        } while (true);
    }

    final String getFieldName(ParseContext parsecontext, int i)
    {
        HprofClass hprofclass = this;
        do
        {
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
            }
            if (i < hprofclass.fieldNamePositions.length)
            {
                return parsecontext.readString(hprofclass.fieldNamePositions[i]);
            }
            HprofClass hprofclass1 = hprofclass.superClass;
            i -= hprofclass.fieldNamePositions.length;
            hprofclass = hprofclass1;
        } while (true);
    }

    final int getFieldValueInternal(ParseContext parsecontext, int i, int j)
    {
        HprofClass hprofclass = this;
        do
        {
            if (j < hprofclass.fieldNamePositions.length)
            {
                return parsecontext.readId(hprofclass.offsets[j] + i);
            }
            HprofClass hprofclass1 = hprofclass.superClass;
            i += hprofclass.totalOffset;
            j -= hprofclass.fieldNamePositions.length;
            hprofclass = hprofclass1;
        } while (true);
    }

    public final void resolveSuperClasses()
    {
        if (fieldsCount == -1)
        {
            fieldsCount = fieldNamePositions.length;
            if (superClass != null)
            {
                superClass.resolveSuperClasses();
                fieldsCount = fieldsCount + superClass.fieldsCount;
                if ((superClass.flags & 2) != 0)
                {
                    flags = flags | 2;
                }
            }
        }
    }
}
