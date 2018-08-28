// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import android.text.TextUtils;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneManager, TimeZoneParams

public final class this._cls0 extends Filter
{

    private final TimeZoneManager this$0;

    private final boolean hasPrefixInOneOfWords(String s, String s1)
    {
        if (s != null)
        {
            s = s.toLowerCase();
            String as[] = s.split(" ");
            int k = as.length;
            int i = 0;
            int j = 0;
            while (i < k) 
            {
                String s2 = as[i];
                String s3 = s.substring(j);
                boolean flag;
                if (s3 == null)
                {
                    flag = false;
                } else
                {
                    flag = s3.toLowerCase().startsWith(s1);
                }
                if (flag)
                {
                    return true;
                }
                j += s2.length() + 1;
                i++;
            }
        }
        return false;
    }

    protected final android.widget.eZoneFilter performFiltering(CharSequence charsequence)
    {
        android.widget.eZoneFilter ezonefilter = new android.widget.eZoneGroup();
        if (TextUtils.isEmpty(charsequence))
        {
            ezonefilter.eZoneGroup = 0;
            ezonefilter.eZoneGroup = null;
            return ezonefilter;
        }
        charsequence = charsequence.toString().toLowerCase();
        ArrayList arraylist = new ArrayList();
label0:
        do
        {
            TimeZoneParams timezoneparams;
            Object obj;
label1:
            do
            {
                for (Iterator iterator = timeZoneGroupsList.iterator(); iterator.hasNext();)
                {
                    obj = (ist)iterator.next();
                    timezoneparams = ((ist) (obj)).imeZone;
                    if (hasPrefixInOneOfWords(timezoneparams.getDisplayName(), charsequence) || hasPrefixInOneOfWords(timezoneparams.getCountry(), charsequence))
                    {
                        arraylist.add(timezoneparams.toBuilder().ryAbbreviation(null).bbreviation(null).bbreviation());
                    } else
                    {
                        String s = timezoneparams.getNameAbbreviation();
                        boolean flag;
                        if (s == null)
                        {
                            flag = false;
                        } else
                        {
                            flag = s.toLowerCase().startsWith(charsequence);
                        }
                        if (flag)
                        {
                            arraylist.add(timezoneparams.toBuilder().ryAbbreviation(null).ryAbbreviation());
                        } else
                        {
                            String s1 = timezoneparams.getCountryAbbreviation();
                            boolean flag1;
                            if (s1 == null)
                            {
                                flag1 = false;
                            } else
                            {
                                flag1 = s1.toLowerCase().startsWith(charsequence);
                            }
                            if (!flag1)
                            {
                                continue label1;
                            }
                            arraylist.add(timezoneparams.toBuilder().bbreviation(null).bbreviation());
                        }
                    }
                }

                break label0;
            } while (charsequence.length() <= 0);
            obj = ((bbreviation) (obj)).ds.iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                String s2 = (String)((Iterator) (obj)).next();
                boolean flag2;
                if (s2 == null)
                {
                    flag2 = false;
                } else
                {
                    flag2 = s2.toLowerCase().startsWith(charsequence);
                }
                if (flag2)
                {
                    arraylist.add(timezoneparams.toBuilder().ryAbbreviation(null).bbreviation(null).bbreviation(s2).bbreviation());
                }
            }
        } while (true);
        ezonefilter.build = arraylist;
        ezonefilter.build = arraylist.size();
        return ezonefilter;
    }

    protected final void publishResults(CharSequence charsequence, android.widget.eZoneFilter ezonefilter)
    {
        ezonefilter = (List)ezonefilter.eZoneFilter;
        TimeZoneManager timezonemanager = TimeZoneManager.this;
        if (ezonefilter == null && !timezonemanager.initialTimeZones.isEmpty())
        {
            timezonemanager.onDisplay = timezonemanager.initialTimeZones;
        } else
        if (ezonefilter == null || ezonefilter.isEmpty())
        {
            timezonemanager.onDisplay = Arrays.asList(new TimeZoneParams[] {
                TimeZoneParams.STUB
            });
        } else
        {
            timezonemanager.onDisplay = ezonefilter;
        }
        timezonemanager.listener.DataChanged(charsequence);
    }

    ()
    {
        this$0 = TimeZoneManager.this;
        super();
    }
}
