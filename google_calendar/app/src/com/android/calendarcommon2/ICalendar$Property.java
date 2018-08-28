// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public final class name
{

    public final String name;
    public LinkedHashMap paramsMap;
    public String value;

    public final void addParameter( )
    {
        ArrayList arraylist1 = (ArrayList)paramsMap.get(.name);
        ArrayList arraylist = arraylist1;
        if (arraylist1 == null)
        {
            arraylist = new ArrayList();
            paramsMap.put(.name, arraylist);
        }
        arraylist.add();
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        toString(stringbuilder);
        return stringbuilder.toString();
    }

    public final void toString(StringBuilder stringbuilder)
    {
        stringbuilder.append(name);
        for (Iterator iterator = paramsMap.keySet().iterator(); iterator.hasNext();)
        {
            Object obj = (String)iterator.next();
            obj = ((List)paramsMap.get(obj)).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                  = ()((Iterator) (obj)).next();
                stringbuilder.append(";");
                stringbuilder.append(.name);
                stringbuilder.append("=");
                stringbuilder.append(.value);
            }
        }

        stringbuilder.append(":");
        stringbuilder.append(value);
    }

    public (String s)
    {
        paramsMap = new LinkedHashMap();
        name = s;
    }
}
