// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import java.util.Set;

public class PreferenceDataStore
{

    public PreferenceDataStore()
    {
    }

    public boolean getBoolean(String s, boolean flag)
    {
        return flag;
    }

    public int getInt(String s, int i)
    {
        return i;
    }

    public String getString(String s, String s1)
    {
        return s1;
    }

    public Set getStringSet(String s, Set set)
    {
        return set;
    }

    public void putBoolean(String s, boolean flag)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putInt(String s, int i)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putString(String s, String s1)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putStringSet(String s, Set set)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }
}
