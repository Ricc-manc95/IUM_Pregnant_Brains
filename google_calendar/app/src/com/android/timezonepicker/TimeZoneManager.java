// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import android.text.TextUtils;
import android.widget.Filter;
import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneParams, TimeZoneLoader

public class TimeZoneManager
{
    public static interface DataListener
    {

        public abstract void onDataChanged(CharSequence charsequence);
    }

    public final class TimeZoneFilter extends Filter
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

        protected final android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            android.widget.Filter.FilterResults filterresults = new android.widget.Filter.FilterResults();
            if (TextUtils.isEmpty(charsequence))
            {
                filterresults.count = 0;
                filterresults.values = null;
                return filterresults;
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
                        obj = (TimeZoneGroup)iterator.next();
                        timezoneparams = ((TimeZoneGroup) (obj)).timeZone;
                        if (hasPrefixInOneOfWords(timezoneparams.getDisplayName(), charsequence) || hasPrefixInOneOfWords(timezoneparams.getCountry(), charsequence))
                        {
                            arraylist.add(timezoneparams.toBuilder().setCountryAbbreviation(null).setNameAbbreviation(null).build());
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
                                arraylist.add(timezoneparams.toBuilder().setCountryAbbreviation(null).build());
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
                                arraylist.add(timezoneparams.toBuilder().setNameAbbreviation(null).build());
                            }
                        }
                    }

                    break label0;
                } while (charsequence.length() <= 0);
                obj = ((TimeZoneGroup) (obj)).ids.iterator();
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
                        arraylist.add(timezoneparams.toBuilder().setCountryAbbreviation(null).setNameAbbreviation(null).setCity(s2).build());
                    }
                }
            } while (true);
            filterresults.values = arraylist;
            filterresults.count = arraylist.size();
            return filterresults;
        }

        protected final void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            filterresults = (List)filterresults.values;
            TimeZoneManager timezonemanager = TimeZoneManager.this;
            if (filterresults == null && !timezonemanager.initialTimeZones.isEmpty())
            {
                timezonemanager.onDisplay = timezonemanager.initialTimeZones;
            } else
            if (filterresults == null || filterresults.isEmpty())
            {
                timezonemanager.onDisplay = Arrays.asList(new TimeZoneParams[] {
                    TimeZoneParams.STUB
                });
            } else
            {
                timezonemanager.onDisplay = filterresults;
            }
            timezonemanager.listener.onDataChanged(charsequence);
        }

        TimeZoneFilter()
        {
            this$0 = TimeZoneManager.this;
            super();
        }
    }

    static final class TimeZoneGroup
    {

        public List ids;
        public TimeZoneParams timeZone;

        final void addId(String s)
        {
            s = s.substring(s.lastIndexOf('/') + 1).replace('_', ' ');
            if (!s.toLowerCase().startsWith("gmt"))
            {
                ids.add(s);
            }
        }

        TimeZoneGroup(TimeZoneParams timezoneparams)
        {
            ids = new ArrayList();
            timeZone = timezoneparams;
            addId(timezoneparams.getId());
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/android/timezonepicker/TimeZoneManager);
    public TimeZoneFilter filter;
    public String filteringRequest;
    public List initialTimeZoneIds;
    public List initialTimeZones;
    public DataListener listener;
    public TimeZoneLoader loader;
    public List onDisplay;
    public List timeZoneGroupsList;

    public TimeZoneManager(List list, TimeZoneLoader timezoneloader)
    {
        filter = new TimeZoneFilter();
        filteringRequest = null;
        initialTimeZoneIds = list;
        onDisplay = new ArrayList();
        loader = timezoneloader;
    }

    public static long getIdByIndex(int i)
    {
        return (long)i;
    }

    static List groupUpTimeZones(List list)
    {
        ArrayList arraylist = new ArrayList();
        if (list.isEmpty())
        {
            return arraylist;
        }
        TimeZoneGroup timezonegroup = new TimeZoneGroup((TimeZoneParams)list.get(0));
        arraylist.add(timezonegroup);
        Iterator iterator = list.iterator();
        list = timezonegroup;
        while (iterator.hasNext()) 
        {
            TimeZoneParams timezoneparams = (TimeZoneParams)iterator.next();
            if (timezoneparams.compareTo(((TimeZoneGroup) (list)).timeZone) == 0)
            {
                list.addId(timezoneparams.getId());
            } else
            {
                list = new TimeZoneGroup(timezoneparams);
                arraylist.add(list);
            }
        }
        return arraylist;
    }

    static final boolean lambda$findTimeZone$1$TimeZoneManager(String s, TimeZoneParams timezoneparams)
    {
        return timezoneparams.getId().equals(s);
    }

}
