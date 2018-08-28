// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.lang.builder;


public final class HashCodeBuilder
{

    public final int iConstant = 37;
    public int iTotal;

    public HashCodeBuilder()
    {
        iTotal = 0;
        iTotal = 17;
    }

    public final HashCodeBuilder append(Object obj)
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        int i = 0;
        if (obj == null)
        {
            iTotal = iTotal * iConstant;
        } else
        if (obj.getClass().isArray())
        {
            if (obj instanceof long[])
            {
                obj = (long[])obj;
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (i < obj.length) 
                {
                    long l3 = obj[i];
                    iTotal = iTotal * iConstant + (int)(l3 ^ l3 >> 32);
                    i++;
                }
            } else
            if (obj instanceof int[])
            {
                obj = (int[])obj;
                int j = ((flag) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (j < obj.length) 
                {
                    iTotal = obj[j] + iTotal * iConstant;
                    j++;
                }
            } else
            if (obj instanceof short[])
            {
                obj = (short[])obj;
                int k = ((flag1) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (k < obj.length) 
                {
                    iTotal = obj[k] + iTotal * iConstant;
                    k++;
                }
            } else
            if (obj instanceof char[])
            {
                obj = (char[])obj;
                int l = ((flag2) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (l < obj.length) 
                {
                    iTotal = obj[l] + iTotal * iConstant;
                    l++;
                }
            } else
            if (obj instanceof byte[])
            {
                obj = (byte[])obj;
                int i1 = ((flag3) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (i1 < obj.length) 
                {
                    iTotal = obj[i1] + iTotal * iConstant;
                    i1++;
                }
            } else
            if (obj instanceof double[])
            {
                obj = (double[])obj;
                int j1 = ((flag4) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (j1 < obj.length) 
                {
                    long l4 = Double.doubleToLongBits(obj[j1]);
                    iTotal = iTotal * iConstant + (int)(l4 ^ l4 >> 32);
                    j1++;
                }
            } else
            if (obj instanceof float[])
            {
                obj = (float[])obj;
                int k1 = ((flag5) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (k1 < obj.length) 
                {
                    float f = obj[k1];
                    int j2 = iTotal;
                    int l2 = iConstant;
                    iTotal = Float.floatToIntBits(f) + j2 * l2;
                    k1++;
                }
            } else
            if (obj instanceof boolean[])
            {
                obj = (boolean[])obj;
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                int l1 = 0;
                while (l1 < obj.length) 
                {
                    byte byte0 = obj[l1];
                    int i3 = iTotal;
                    int j3 = iConstant;
                    int k2;
                    if (byte0 != 0)
                    {
                        k2 = 0;
                    } else
                    {
                        k2 = 1;
                    }
                    iTotal = k2 + i3 * j3;
                    l1++;
                }
            } else
            {
                obj = ((Object) ((Object[])obj));
                int i2 = ((flag6) ? 1 : 0);
                if (obj == null)
                {
                    iTotal = iTotal * iConstant;
                    return this;
                }
                while (i2 < obj.length) 
                {
                    append(obj[i2]);
                    i2++;
                }
            }
        } else
        {
            iTotal = iTotal * iConstant + obj.hashCode();
            return this;
        }
        return this;
    }

    public final int hashCode()
    {
        return iTotal;
    }

    static 
    {
        new ThreadLocal();
    }
}
