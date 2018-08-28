// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneParams

public class TimeZoneLoader
{

    public static final String TAG = LogUtils.getLogTag(com/android/timezonepicker/TimeZoneLoader);
    private Context context;
    public HashMap countryCodeToNameMap;
    private final String palestineDisplayName;
    public Date timeToDisplay;
    public HashMap timeZoneIdToLabelMap;

    public TimeZoneLoader(Context context1, long l)
    {
        context = context1;
        timeToDisplay = new Date(l);
        palestineDisplayName = context1.getResources().getString(0x7f13037a);
    }

    final HashMap loadArraysToMap(int i, int j)
    {
        boolean flag = false;
        String as[] = context.getResources().getStringArray(i);
        String as1[] = context.getResources().getStringArray(j);
        if (as.length != as1.length)
        {
            LogUtils.wtf(TAG, "Arrays that should represent mapping have lengths %d and %d", new Object[] {
                Integer.valueOf(as.length), Integer.valueOf(as1.length)
            });
        }
        j = Math.min(as.length, as1.length);
        HashMap hashmap = new HashMap(j);
        for (i = ((flag) ? 1 : 0); i < j; i++)
        {
            hashmap.put(as[i], as1[i]);
        }

        return hashmap;
    }

    final HashSet loadTimeZonesFromBackwardAndZoneTab(ArrayList arraylist)
    {
        Object obj;
        HashSet hashset;
        Object obj3;
        hashset = new HashSet();
        obj3 = context.getAssets();
        obj = null;
        Object obj1 = ((AssetManager) (obj3)).open("backward");
        Object obj2;
        obj = obj1;
        obj2 = obj1;
        Object obj4 = new BufferedReader(new InputStreamReader(((InputStream) (obj1))));
_L4:
        obj = obj1;
        obj2 = obj1;
        String s = ((BufferedReader) (obj4)).readLine();
        if (s == null) goto _L2; else goto _L1
_L1:
        obj = obj1;
        obj2 = obj1;
        if (s.startsWith("#")) goto _L4; else goto _L3
_L3:
        obj = obj1;
        obj2 = obj1;
        if (s.length() <= 0) goto _L4; else goto _L5
_L5:
        obj = obj1;
        obj2 = obj1;
        String as[] = s.split("\t+");
        s = as[1];
        obj = obj1;
        obj2 = obj1;
        Object obj5 = as[as.length - 1];
        obj = obj1;
        obj2 = obj1;
        if (TimeZone.getTimeZone(s) != null) goto _L7; else goto _L6
_L6:
        obj = obj1;
        obj2 = obj1;
        LogUtils.e(TAG, "Timezone not found: %s", new Object[] {
            s
        });
          goto _L4
        obj1;
        obj2 = obj;
        LogUtils.e(TAG, "Failed to read 'backward' file.", new Object[0]);
        obj2 = obj;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_200;
        }
        ((InputStream) (obj)).close();
        obj2 = obj;
_L15:
        obj = obj2;
        obj1 = ((AssetManager) (obj3)).open("zone.tab");
        obj = obj1;
        obj3 = new BufferedReader(new InputStreamReader(((InputStream) (obj))));
_L11:
        obj1 = ((BufferedReader) (obj3)).readLine();
        if (obj1 == null) goto _L9; else goto _L8
_L8:
        if (((String) (obj1)).startsWith("#")) goto _L11; else goto _L10
_L10:
        obj1 = ((String) (obj1)).split("\t");
        obj4 = obj1[2];
        s = obj1[0];
        obj5 = TimeZone.getTimeZone(((String) (obj4)));
        if (obj5 != null) goto _L13; else goto _L12
_L12:
        LogUtils.e(TAG, "Timezone not found: %s", new Object[] {
            obj4
        });
          goto _L11
        arraylist;
        obj2 = obj;
_L23:
        obj = obj2;
        LogUtils.e(TAG, "Failed to read 'zone.tab'.", new Object[0]);
        if (obj2 != null)
        {
            try
            {
                ((InputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch (ArrayList arraylist)
            {
                return hashset;
            }
        }
        return hashset;
_L7:
        obj = obj1;
        obj2 = obj1;
        hashset.add(obj5);
        obj = obj1;
        obj2 = obj1;
        LogUtils.d(TAG, "# Dropping identical time zone from backward: %s", new Object[] {
            obj5
        });
          goto _L4
        arraylist;
_L24:
        IOException ioexception2;
        if (obj2 != null)
        {
            try
            {
                ((InputStream) (obj2)).close();
            }
            catch (IOException ioexception) { }
        }
        throw arraylist;
_L2:
        obj2 = obj1;
        if (obj1 == null) goto _L15; else goto _L14
_L14:
        ((InputStream) (obj1)).close();
        obj2 = obj1;
          goto _L15
        obj;
        obj2 = obj1;
          goto _L15
_L13:
        if (s != null) goto _L17; else goto _L16
_L16:
        if (((String) (obj4)).startsWith("Etc/GMT")) goto _L17; else goto _L18
_L18:
        hashset.add(obj4);
          goto _L11
        arraylist;
_L22:
        if (obj != null)
        {
            try
            {
                ((InputStream) (obj)).close();
            }
            catch (IOException ioexception1) { }
        }
        throw arraylist;
_L17:
        obj2 = (String)countryCodeToNameMap.get(s);
        obj1 = obj2;
        if (obj2 != null) goto _L20; else goto _L19
_L19:
        obj1 = Locale.getDefault();
        if (!"PS".equalsIgnoreCase(s))
        {
            break MISSING_BLOCK_LABEL_567;
        }
        obj1 = palestineDisplayName;
_L21:
        countryCodeToNameMap.put(s, obj1);
_L20:
        arraylist.add(TimeZoneParams.builder(((TimeZone) (obj5)), timeToDisplay, ((String) (obj1)), (String)timeZoneIdToLabelMap.get(((TimeZone) (obj5)).getID())).build());
        hashset.add(obj4);
        LogUtils.d(TAG, "# Adding time zone: %s##%s", new Object[] {
            obj4, ((TimeZone) (obj5)).getDisplayName()
        });
          goto _L11
        obj1 = (new Locale(((Locale) (obj1)).getLanguage(), s)).getDisplayCountry(((Locale) (obj1)));
          goto _L21
_L9:
        if (obj != null)
        {
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (ArrayList arraylist)
            {
                return hashset;
            }
            return hashset;
        } else
        {
            break MISSING_BLOCK_LABEL_329;
        }
          goto _L11
        ioexception2;
        obj2 = obj;
          goto _L15
        arraylist;
          goto _L22
        arraylist;
          goto _L23
        arraylist;
        obj2 = null;
          goto _L24
    }

}
