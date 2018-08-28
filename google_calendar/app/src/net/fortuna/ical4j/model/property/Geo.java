// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.math.BigDecimal;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public final class Geo extends Property
{

    public static final long serialVersionUID = 0xf37b182db3010a8cL;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Geo()
    {
        super("GEO", PropertyFactoryImpl.instance);
        latitude = BigDecimal.valueOf(0L);
        longitude = BigDecimal.valueOf(0L);
    }

    public final String getValue()
    {
        String s = String.valueOf(latitude);
        String s1 = String.valueOf(longitude);
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append(";").append(s1).toString();
    }

    public final void setValue(String s)
    {
        String s1;
        boolean flag;
        flag = true;
        s1 = s.substring(0, s.indexOf(';'));
        if (s1 == null) goto _L2; else goto _L1
_L1:
        int j = s1.length();
        if (j != 0) goto _L3; else goto _L2
_L2:
        int i = 1;
_L7:
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            latitude = new BigDecimal(s1);
        } else
        {
            latitude = BigDecimal.valueOf(0L);
        }
        s = s.substring(s.indexOf(';') + 1);
        if (s == null) goto _L5; else goto _L4
_L4:
        j = s.length();
        if (j != 0) goto _L6; else goto _L5
_L5:
        i = 1;
_L9:
        if (i == 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            longitude = new BigDecimal(s);
            return;
        } else
        {
            longitude = BigDecimal.valueOf(0L);
            return;
        }
_L3:
        i = 0;
_L8:
label0:
        {
            if (i >= j)
            {
                break MISSING_BLOCK_LABEL_139;
            }
            if (Character.isWhitespace(s1.charAt(i)))
            {
                break label0;
            }
            i = 0;
        }
          goto _L7
        i++;
          goto _L8
        i = 1;
          goto _L7
_L6:
        i = 0;
_L10:
label1:
        {
            if (i >= j)
            {
                break MISSING_BLOCK_LABEL_191;
            }
            if (Character.isWhitespace(s.charAt(i)))
            {
                break label1;
            }
            i = 0;
        }
          goto _L9
        i++;
          goto _L10
        i = 1;
          goto _L9
    }

    public final void validate()
        throws ValidationException
    {
    }
}
