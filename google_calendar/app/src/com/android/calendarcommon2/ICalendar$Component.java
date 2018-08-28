// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public final class parent
{

    public List children;
    public final String name;
    public final toString parent;
    public final LinkedHashMap propsMap = new LinkedHashMap();

    private final void toString(StringBuilder stringbuilder)
    {
        stringbuilder.append("BEGIN");
        stringbuilder.append(":");
        stringbuilder.append(name);
        stringbuilder.append("\n");
        for (Iterator iterator = propsMap.keySet().iterator(); iterator.hasNext();)
        {
            Object obj = (String)iterator.next();
            obj = ((List)propsMap.get(obj)).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                ((propsMap)((Iterator) (obj)).next()).oString(stringbuilder);
                stringbuilder.append("\n");
            }
        }

        if (children != null)
        {
            for (Iterator iterator1 = children.iterator(); iterator1.hasNext(); stringbuilder.append("\n"))
            {
                ((children)iterator1.next()).toString(stringbuilder);
            }

        }
        stringbuilder.append("END");
        stringbuilder.append(":");
        stringbuilder.append(name);
    }

    public final void addProperty(name name1)
    {
        String s = name1.ame;
        ArrayList arraylist1 = (ArrayList)propsMap.get(s);
        ArrayList arraylist = arraylist1;
        if (arraylist1 == null)
        {
            arraylist = new ArrayList();
            propsMap.put(s, arraylist);
        }
        arraylist.add(name1);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        toString(stringbuilder);
        stringbuilder.append("\n");
        return stringbuilder.toString();
    }

    public (String s,  )
    {
        children = null;
        name = s;
        parent = ;
    }
}
