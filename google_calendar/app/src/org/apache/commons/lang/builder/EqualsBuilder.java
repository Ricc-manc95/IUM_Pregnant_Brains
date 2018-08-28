// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.apache.commons.lang.builder;


public final class EqualsBuilder
{

    public boolean isEquals;

    public EqualsBuilder()
    {
        isEquals = true;
    }

    public final EqualsBuilder append(Object obj, Object obj1)
    {
        int i;
        i = 0;
        break MISSING_BLOCK_LABEL_3;
_L11:
        do
        {
            do
            {
                do
                {
                    do
                    {
                        do
                        {
                            do
                            {
                                return this;
                            } while (!isEquals || obj == obj1);
                            if (obj == null || obj1 == null)
                            {
                                isEquals = false;
                                return this;
                            }
                            if (!obj.getClass().isArray())
                            {
                                isEquals = obj.equals(obj1);
                                return this;
                            }
                            if (obj.getClass() != obj1.getClass())
                            {
                                isEquals = false;
                                return this;
                            }
                            if (!(obj instanceof long[]))
                            {
                                break;
                            }
                            obj = (long[])obj;
                            obj1 = (long[])obj1;
                            if (isEquals && obj != obj1)
                            {
                                if (obj == null || obj1 == null)
                                {
                                    isEquals = false;
                                    return this;
                                }
                                if (obj.length != obj1.length)
                                {
                                    isEquals = false;
                                    return this;
                                }
                                i = 0;
                                while (i < obj.length && isEquals) 
                                {
                                    long l1 = obj[i];
                                    long l3 = obj1[i];
                                    if (isEquals)
                                    {
                                        boolean flag;
                                        if (l1 == l3)
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        isEquals = flag;
                                    }
                                    i++;
                                }
                            }
                        } while (true);
                        if (!(obj instanceof int[]))
                        {
                            break;
                        }
                        obj = (int[])obj;
                        obj1 = (int[])obj1;
                        if (isEquals && obj != obj1)
                        {
                            if (obj == null || obj1 == null)
                            {
                                isEquals = false;
                                return this;
                            }
                            if (obj.length != obj1.length)
                            {
                                isEquals = false;
                                return this;
                            }
                            i = 0;
                            while (i < obj.length && isEquals) 
                            {
                                int j = obj[i];
                                int l = obj1[i];
                                if (isEquals)
                                {
                                    boolean flag1;
                                    if (j == l)
                                    {
                                        flag1 = true;
                                    } else
                                    {
                                        flag1 = false;
                                    }
                                    isEquals = flag1;
                                }
                                i++;
                            }
                        }
                    } while (true);
                    if (!(obj instanceof short[]))
                    {
                        break;
                    }
                    obj = (short[])obj;
                    obj1 = (short[])obj1;
                    if (isEquals && obj != obj1)
                    {
                        if (obj == null || obj1 == null)
                        {
                            isEquals = false;
                            return this;
                        }
                        if (obj.length != obj1.length)
                        {
                            isEquals = false;
                            return this;
                        }
                        i = 0;
                        while (i < obj.length && isEquals) 
                        {
                            short word0 = obj[i];
                            short word1 = obj1[i];
                            if (isEquals)
                            {
                                boolean flag2;
                                if (word0 == word1)
                                {
                                    flag2 = true;
                                } else
                                {
                                    flag2 = false;
                                }
                                isEquals = flag2;
                            }
                            i++;
                        }
                    }
                } while (true);
                if (!(obj instanceof char[]))
                {
                    break;
                }
                obj = (char[])obj;
                obj1 = (char[])obj1;
                if (isEquals && obj != obj1)
                {
                    if (obj == null || obj1 == null)
                    {
                        isEquals = false;
                        return this;
                    }
                    if (obj.length != obj1.length)
                    {
                        isEquals = false;
                        return this;
                    }
                    i = 0;
                    while (i < obj.length && isEquals) 
                    {
                        char c = obj[i];
                        char c1 = obj1[i];
                        if (isEquals)
                        {
                            boolean flag3;
                            if (c == c1)
                            {
                                flag3 = true;
                            } else
                            {
                                flag3 = false;
                            }
                            isEquals = flag3;
                        }
                        i++;
                    }
                }
            } while (true);
            if (!(obj instanceof byte[]))
            {
                break;
            }
            obj = (byte[])obj;
            obj1 = (byte[])obj1;
            if (isEquals && obj != obj1)
            {
                if (obj == null || obj1 == null)
                {
                    isEquals = false;
                    return this;
                }
                if (obj.length != obj1.length)
                {
                    isEquals = false;
                    return this;
                }
                i = 0;
                while (i < obj.length && isEquals) 
                {
                    byte byte0 = obj[i];
                    byte byte1 = obj1[i];
                    if (isEquals)
                    {
                        boolean flag4;
                        if (byte0 == byte1)
                        {
                            flag4 = true;
                        } else
                        {
                            flag4 = false;
                        }
                        isEquals = flag4;
                    }
                    i++;
                }
            }
        } while (true);
        if (!(obj instanceof double[])) goto _L2; else goto _L1
_L1:
        obj = (double[])obj;
        obj1 = (double[])obj1;
        if (!isEquals || obj == obj1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (obj == null || obj1 == null)
        {
            isEquals = false;
            return this;
        }
        if (obj.length != obj1.length)
        {
            isEquals = false;
            return this;
        }
        i = 0;
_L4:
        double d;
        double d1;
        if (i >= obj.length || !isEquals)
        {
            continue; /* Loop/switch isn't completed */
        }
        d = obj[i];
        d1 = obj1[i];
        if (isEquals)
        {
            break; /* Loop/switch isn't completed */
        }
_L5:
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        long l2 = Double.doubleToLongBits(d);
        long l4 = Double.doubleToLongBits(d1);
        if (isEquals)
        {
            boolean flag5;
            if (l2 == l4)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            isEquals = flag5;
        }
          goto _L5
        if (true) goto _L4; else goto _L2
_L2:
        if (!(obj instanceof float[]))
        {
            break MISSING_BLOCK_LABEL_1009;
        }
        obj = (float[])obj;
        obj1 = (float[])obj1;
        if (!isEquals || obj == obj1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (obj == null || obj1 == null)
        {
            isEquals = false;
            return this;
        }
        if (obj.length != obj1.length)
        {
            isEquals = false;
            return this;
        }
        i = 0;
_L7:
        float f;
        float f1;
        if (i >= obj.length || !isEquals)
        {
            continue; /* Loop/switch isn't completed */
        }
        f = obj[i];
        f1 = obj1[i];
        if (isEquals)
        {
            break; /* Loop/switch isn't completed */
        }
_L8:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        int k = Float.floatToIntBits(f);
        int i1 = Float.floatToIntBits(f1);
        if (isEquals)
        {
            boolean flag6;
            if (k == i1)
            {
                flag6 = true;
            } else
            {
                flag6 = false;
            }
            isEquals = flag6;
        }
          goto _L8
        if (true) goto _L7; else goto _L9
_L9:
        if (!(obj instanceof boolean[]))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (boolean[])obj;
        obj1 = (boolean[])obj1;
        if (isEquals && obj != obj1)
        {
            if (obj == null || obj1 == null)
            {
                isEquals = false;
                return this;
            }
            if (obj.length != obj1.length)
            {
                isEquals = false;
                return this;
            }
            i = 0;
            while (i < obj.length && isEquals) 
            {
                byte byte2 = obj[i];
                byte byte3 = obj1[i];
                if (isEquals)
                {
                    if (byte2 == byte3)
                    {
                        byte2 = 1;
                    } else
                    {
                        byte2 = 0;
                    }
                    isEquals = byte2;
                }
                i++;
            }
        }
        if (true) goto _L11; else goto _L10
_L10:
        obj = ((Object) ((Object[])obj));
        obj1 = ((Object) ((Object[])obj1));
        if (isEquals && obj != obj1)
        {
            if (obj == null || obj1 == null)
            {
                isEquals = false;
                return this;
            }
            if (obj.length != obj1.length)
            {
                isEquals = false;
                return this;
            }
            while (i < obj.length && isEquals) 
            {
                append(obj[i], obj1[i]);
                i++;
            }
        }
        if (true) goto _L11; else goto _L12
_L12:
    }
}
