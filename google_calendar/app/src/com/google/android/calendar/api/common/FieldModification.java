// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.os.Parcel;
import java.util.Arrays;

public class FieldModification
{

    public FieldModification()
    {
    }

    public static FieldModification readFromParcel(Parcel parcel, ClassLoader classloader)
    {
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            return new _cls1();
        } else
        {
            return new FieldModification();
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null && getClass() == obj.getClass()) goto _L2; else goto _L1
_L1:
        Object obj1;
        return false;
_L2:
        if (shouldModify() != ((FieldModification) (obj1 = (FieldModification)obj)).shouldModify()) goto _L1; else goto _L3
_L3:
        if (!shouldModify())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = getModificationValue();
        obj1 = ((FieldModification) (obj1)).getModificationValue();
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L1; else goto _L4
_L4:
        return true;
    }

    public Object getModificationValue()
    {
        throw new IllegalStateException("Called getModificationValue() when shouldModify() is false");
    }

    public int hashCode()
    {
        if (shouldModify())
        {
            return Arrays.hashCode(new Object[] {
                getModificationValue()
            });
        } else
        {
            return -1;
        }
    }

    public boolean shouldModify()
    {
        return false;
    }

    public String toString()
    {
        if (shouldModify())
        {
            return String.valueOf(getModificationValue());
        } else
        {
            return "<unmodified>";
        }
    }

    private class _cls1 extends FieldModification
    {

        private final Object val$value;

        public final Object getModificationValue()
        {
            return value;
        }

        public final boolean shouldModify()
        {
            return true;
        }

        public _cls1()
        {
            value = obj;
            super();
        }
    }

}
