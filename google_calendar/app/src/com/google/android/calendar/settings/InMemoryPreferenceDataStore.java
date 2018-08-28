// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.support.v7.preference.PreferenceDataStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class InMemoryPreferenceDataStore extends PreferenceDataStore
{

    private Map store;

    public InMemoryPreferenceDataStore()
    {
        store = new HashMap();
    }

    public final boolean getBoolean(String s, boolean flag)
    {
        Object obj = Boolean.valueOf(flag);
        if (store.containsKey(s))
        {
            obj = store.get(s);
        }
        return ((Boolean)obj).booleanValue();
    }

    public final int getInt(String s, int i)
    {
        Object obj = Integer.valueOf(i);
        if (store.containsKey(s))
        {
            obj = store.get(s);
        }
        return ((Integer)obj).intValue();
    }

    public final String getString(String s, String s1)
    {
        if (store.containsKey(s))
        {
            s = ((String) (store.get(s)));
        } else
        {
            s = s1;
        }
        return (String)s;
    }

    public final Set getStringSet(String s, Set set)
    {
        if (store.containsKey(s))
        {
            s = ((String) (store.get(s)));
        } else
        {
            s = set;
        }
        return (Set)s;
    }

    public final void putBoolean(String s, boolean flag)
    {
        store.put(s, Boolean.valueOf(flag));
    }

    public final void putInt(String s, int i)
    {
        store.put(s, Integer.valueOf(i));
    }

    public final void putString(String s, String s1)
    {
        store.put(s, s1);
    }

    public final void putStringSet(String s, Set set)
    {
        store.put(s, set);
    }
}
