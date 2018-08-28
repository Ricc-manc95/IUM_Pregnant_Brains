// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import android.accounts.Account;
import com.google.android.calendar.utils.account.AccountUtil;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class accountsWithDeviceOrder
    implements Comparator
{

    private final Map accountsWithDeviceOrder;

    public final int compare(Object obj, Object obj1)
    {
        Object obj2;
        int i;
        byte byte0;
        boolean flag;
        boolean flag1;
        boolean flag2;
        obj2 = null;
        byte0 = 2;
        flag2 = true;
        flag1 = true;
        flag = false;
        obj = (o)obj;
        obj1 = (o)obj1;
        i = ((o) (obj)).getOrder() - ((o) (obj1)).getOrder();
        if (i == 0) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        obj = (o.getOrder)obj;
        obj1 = (o.getOrder)obj1;
        if (((o.getOrder) (obj))..equals((() (obj1)).)) goto _L4; else goto _L3
_L3:
        if (AccountUtil.isGoogleAccount((() (obj)).))
        {
            i = 0;
        } else
        if (AccountUtil.isExchangeAccount((() (obj)).))
        {
            i = 1;
        } else
        {
            i = 2;
        }
        if (!AccountUtil.isGoogleAccount((() (obj1)).)) goto _L6; else goto _L5
_L5:
        byte0 = 0;
_L8:
        if (i != byte0)
        {
            return i - byte0;
        }
        break; /* Loop/switch isn't completed */
_L6:
        if (AccountUtil.isExchangeAccount((() (obj1)).))
        {
            byte0 = 1;
        }
        if (true) goto _L8; else goto _L7
_L7:
        Integer integer;
        Integer integer1;
        integer = (Integer)accountsWithDeviceOrder.get(((accountsWithDeviceOrder) (obj)).);
        integer1 = (Integer)accountsWithDeviceOrder.get(((accountsWithDeviceOrder) (obj1)).);
        if (integer != null && integer1 != null)
        {
            return integer.intValue() - integer1.intValue();
        }
        if (integer == null && integer1 == null) goto _L10; else goto _L9
_L9:
        if (integer == null) goto _L12; else goto _L11
_L11:
        i = -1;
_L14:
        return i;
_L12:
        i = ((flag1) ? 1 : 0);
        if (integer1 != null) goto _L1; else goto _L13
_L13:
        i = ((flag) ? 1 : 0);
          goto _L14
_L10:
        if ((() (obj)). == null)
        {
            obj = null;
        } else
        {
            obj = (() (obj))..name;
        }
        if ((() (obj1)). == null)
        {
            obj1 = obj2;
        } else
        {
            obj1 = (() (obj1))..name;
        }
        return ((String) (obj)).compareToIgnoreCase(((String) (obj1)));
_L4:
        i = (() (obj)).y - ((y) (obj1)).y;
        if (i != 0)
        {
            return i;
        }
        int j;
        if (obj instanceof y)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (obj1 instanceof y)
        {
            j = ((flag2) ? 1 : 0);
        } else
        {
            j = 0;
        }
        j = i - j;
        i = j;
        if (j != 0) goto _L1; else goto _L15
_L15:
        i = ((flag) ? 1 : 0);
        if (obj instanceof y)
        {
            obj = (y)obj;
            obj1 = (y)obj1;
            return ((y) (obj)).yName.toUpperCase().compareTo(((yName) (obj1)).yName.toUpperCase());
        }
          goto _L14
    }

    public o(Account aaccount[])
    {
        boolean flag = false;
        super();
        int i;
        if (aaccount == null)
        {
            i = 0;
        } else
        {
            i = aaccount.length;
        }
        accountsWithDeviceOrder = new HashMap(i);
        if (aaccount != null)
        {
            for (i = ((flag) ? 1 : 0); i < aaccount.length; i++)
            {
                accountsWithDeviceOrder.put(aaccount[i], Integer.valueOf(i));
            }

        }
    }
}
