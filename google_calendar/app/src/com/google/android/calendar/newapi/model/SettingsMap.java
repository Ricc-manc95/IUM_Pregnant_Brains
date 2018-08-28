// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.settings.Settings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SettingsMap
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Map settingsMap;

    SettingsMap(Parcel parcel)
    {
        int j = parcel.readInt();
        HashMap hashmap = new HashMap(j);
        for (int i = 0; i < j; i++)
        {
            hashmap.put((Account)parcel.readParcelable(android/accounts/Account.getClassLoader()), parcel.readParcelable(com/google/android/calendar/api/settings/Settings.getClassLoader()));
        }

        this(((Map) (hashmap)));
    }

    private SettingsMap(Map map)
    {
        settingsMap = Collections.unmodifiableMap(map);
    }

    public static SettingsMap create(Settings asettings[])
    {
        if (asettings == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = new HashMap(asettings.length);
        int j = asettings.length;
        for (int i = 0; i < j; i++)
        {
            Settings settings = asettings[i];
            hashmap.put(settings.getDescriptor(), settings);
        }

        return new SettingsMap(hashmap);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(settingsMap.size());
        java.util.Map.Entry entry;
        for (Iterator iterator = settingsMap.entrySet().iterator(); iterator.hasNext(); parcel.writeParcelable((Parcelable)entry.getValue(), 0))
        {
            entry = (java.util.Map.Entry)iterator.next();
            parcel.writeParcelable((Parcelable)entry.getKey(), 0);
        }

    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SettingsMap(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SettingsMap[i];
        }

        _cls1()
        {
        }
    }

}
