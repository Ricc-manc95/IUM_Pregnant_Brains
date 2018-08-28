// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdm

public final class zzcdn
{

    private static void zza(String s, Object obj, StringBuffer stringbuffer, StringBuffer stringbuffer1)
        throws IllegalAccessException, InvocationTargetException
    {
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!(obj instanceof zzcdm)) goto _L4; else goto _L3
_L3:
        Class class1;
        Method amethod[];
        int i;
        int j1;
        int j2;
        j2 = stringbuffer.length();
        if (s != null)
        {
            stringbuffer1.append(stringbuffer).append(zzmP(s)).append(" <\n");
            stringbuffer.append("  ");
        }
        class1 = obj.getClass();
        Field afield[] = class1.getFields();
        int k2 = afield.length;
        i = 0;
        while (i < k2) 
        {
            Object obj2 = afield[i];
            int l = ((Field) (obj2)).getModifiers();
            String s1 = ((Field) (obj2)).getName();
            if (!"cachedSize".equals(s1) && (l & 1) == 1 && (l & 8) != 8 && !s1.startsWith("_") && !s1.endsWith("_"))
            {
                Class class2 = ((Field) (obj2)).getType();
                obj2 = ((Field) (obj2)).get(obj);
                if (class2.isArray())
                {
                    if (class2.getComponentType() == Byte.TYPE)
                    {
                        zza(s1, obj2, stringbuffer, stringbuffer1);
                    } else
                    {
                        int i1;
                        int i2;
                        if (obj2 == null)
                        {
                            i1 = 0;
                        } else
                        {
                            i1 = Array.getLength(obj2);
                        }
                        i2 = 0;
                        while (i2 < i1) 
                        {
                            zza(s1, Array.get(obj2, i2), stringbuffer, stringbuffer1);
                            i2++;
                        }
                    }
                } else
                {
                    zza(s1, obj2, stringbuffer, stringbuffer1);
                }
            }
            i++;
        }
        amethod = class1.getMethods();
        j1 = amethod.length;
        i = 0;
_L15:
        Object obj1;
        if (i >= j1)
        {
            break MISSING_BLOCK_LABEL_465;
        }
        obj1 = amethod[i].getName();
        if (!((String) (obj1)).startsWith("set")) goto _L6; else goto _L5
_L5:
        String s2 = ((String) (obj1)).substring(3);
        obj1 = String.valueOf(s2);
        if (((String) (obj1)).length() == 0) goto _L8; else goto _L7
_L7:
        obj1 = "has".concat(((String) (obj1)));
_L12:
        obj1 = class1.getMethod(((String) (obj1)), new Class[0]);
        if (!((Boolean)((Method) (obj1)).invoke(obj, new Object[0])).booleanValue()) goto _L6; else goto _L9
_L9:
        obj1 = String.valueOf(s2);
        if (((String) (obj1)).length() == 0) goto _L11; else goto _L10
_L10:
        obj1 = "get".concat(((String) (obj1)));
_L13:
        obj1 = class1.getMethod(((String) (obj1)), new Class[0]);
        zza(s2, ((Method) (obj1)).invoke(obj, new Object[0]), stringbuffer, stringbuffer1);
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L8:
        obj1 = new String("has");
          goto _L12
        obj1;
          goto _L6
_L11:
        obj1 = new String("get");
          goto _L13
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
          goto _L6
        if (s != null)
        {
            stringbuffer.setLength(j2);
            stringbuffer1.append(stringbuffer).append(">\n");
        }
_L2:
        return;
_L4:
        s = zzmP(s);
        stringbuffer1.append(stringbuffer).append(s).append(": ");
        if (obj instanceof String)
        {
            obj = (String)obj;
            s = ((String) (obj));
            if (!((String) (obj)).startsWith("http"))
            {
                s = ((String) (obj));
                if (((String) (obj)).length() > 200)
                {
                    s = String.valueOf(((String) (obj)).substring(0, 200)).concat("[...]");
                }
            }
            int k1 = s.length();
            obj = new StringBuilder(k1);
            int j = 0;
            while (j < k1) 
            {
                char c = s.charAt(j);
                if (c >= ' ' && c <= '~' && c != '"' && c != '\'')
                {
                    ((StringBuilder) (obj)).append(c);
                } else
                {
                    ((StringBuilder) (obj)).append(String.format("\\u%04x", new Object[] {
                        Integer.valueOf(c)
                    }));
                }
                j++;
            }
            s = ((StringBuilder) (obj)).toString();
            stringbuffer1.append("\"").append(s).append("\"");
        } else
        if (obj instanceof byte[])
        {
            s = (byte[])obj;
            if (s == null)
            {
                stringbuffer1.append("\"\"");
            } else
            {
                stringbuffer1.append('"');
                int k = 0;
                while (k < s.length) 
                {
                    int l1 = s[k] & 0xff;
                    if (l1 == 92 || l1 == 34)
                    {
                        stringbuffer1.append('\\').append((char)l1);
                    } else
                    if (l1 >= 32 && l1 < 127)
                    {
                        stringbuffer1.append((char)l1);
                    } else
                    {
                        stringbuffer1.append(String.format("\\%03o", new Object[] {
                            Integer.valueOf(l1)
                        }));
                    }
                    k++;
                }
                stringbuffer1.append('"');
            }
        } else
        {
            stringbuffer1.append(obj);
        }
        stringbuffer1.append("\n");
        return;
        if (true) goto _L15; else goto _L14
_L14:
    }

    public static String zzg(zzcdm zzcdm1)
    {
        if (zzcdm1 == null)
        {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer();
        try
        {
            zza(null, zzcdm1, new StringBuffer(), stringbuffer);
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            zzcdm1 = String.valueOf(zzcdm1.getMessage());
            if (zzcdm1.length() != 0)
            {
                return "Error printing proto: ".concat(zzcdm1);
            } else
            {
                return new String("Error printing proto: ");
            }
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            zzcdm1 = String.valueOf(zzcdm1.getMessage());
            if (zzcdm1.length() != 0)
            {
                return "Error printing proto: ".concat(zzcdm1);
            } else
            {
                return new String("Error printing proto: ");
            }
        }
        return stringbuffer.toString();
    }

    private static String zzmP(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while (i < s.length()) 
        {
            char c = s.charAt(i);
            if (i == 0)
            {
                stringbuffer.append(Character.toLowerCase(c));
            } else
            if (Character.isUpperCase(c))
            {
                stringbuffer.append('_').append(Character.toLowerCase(c));
            } else
            {
                stringbuffer.append(c);
            }
            i++;
        }
        return stringbuffer.toString();
    }
}
