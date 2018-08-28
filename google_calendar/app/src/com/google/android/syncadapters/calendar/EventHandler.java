// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Entity;
import android.content.EntityIterator;
import android.content.SyncResult;
import android.content.SyncStats;
import android.database.Cursor;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.LongSparseArray;
import android.util.SparseArray;
import com.android.calendarcommon2.DateException;
import com.android.calendarcommon2.Duration;
import com.android.calendarcommon2.ICalendar;
import com.android.calendarcommon2.LogUtils;
import com.android.calendarcommon2.RecurrenceSet;
import com.google.android.apiary.ItemAndEntityHandler;
import com.google.android.apiary.ParseException;
import com.google.android.apps.calendar.commonsync.constants.Constants;
import com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.syncadapters.timely.sql.SQLiteDatabaseUtils;
import com.google.android.calendar.api.common.ExtendedPropertiesUtils;
import com.google.android.gsf.Gservices;
import com.google.android.syncadapters.calendar.timely.contract.SyncHooks;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.android.syncadapters.calendar.timely.contract.TimelySync;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequest;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventLocation;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.StructuredLocation;
import com.google.api.services.calendar.model.TimeChangeProposal;
import com.google.common.base.Platform;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            AnalyticsLoggerExtensionFactory, Utilities, CalendarSyncAdapterApiary, ProviderHelper, 
//            TimeZoneUtils, CalendarSyncInfo, CalendarRequestExecutor, SyncAnalyticsLoggerExtension, 
//            SyncLog, SyncUtils

public final class EventHandler
    implements ItemAndEntityHandler, Constants
{

    private static final HashMap ENTRY_TYPE_TO_PROVIDER_ATTENDEE;
    private static final SparseArray PROVIDER_TYPE_TO_ENTRY_ATTENDEE;
    private static final String TIME_RELATED_FIELDS[] = {
        "dtstart", "dtend", "duration", "allDay", "rrule", "rdate", "exrule", "exdate", "eventTimezone", "eventEndTimezone", 
        "eventStatus", "original_id", "original_sync_id", "originalInstanceTime", "originalAllDay", "deleted"
    };
    private static final ImmutableSet UNSYNCED_EVENT_KEYS = ImmutableSet.construct(4, new Object[] {
        "reminders", "extendedProperties", "ifmatch", "userAgentPackage"
    });
    private Account account;
    private final SyncAnalyticsLoggerExtension analyticsLogger;
    private String calendarId;
    private LongSparseArray calendarIdToLocalSyncInfo;
    private final Calendar client;
    private final ContentResolver contentResolver;
    private final Pattern eventPlusPattern;
    private ContentProviderClient provider;
    private final CalendarRequestExecutor requestExecutor;
    private final SyncHooks syncHooks[];
    private TimelySync timelySync;

    EventHandler()
    {
        calendarIdToLocalSyncInfo = new LongSparseArray();
        client = null;
        contentResolver = null;
        eventPlusPattern = null;
        analyticsLogger = null;
        syncHooks = null;
        requestExecutor = null;
        timelySync = null;
    }

    public EventHandler(Calendar calendar, Account account1, ContentProviderClient contentproviderclient, ContentResolver contentresolver, String s, SyncHooks asynchooks[], CalendarRequestExecutor calendarrequestexecutor, 
            TimelySync timelysync)
    {
        calendarIdToLocalSyncInfo = new LongSparseArray();
        client = calendar;
        account = account1;
        provider = contentproviderclient;
        contentResolver = contentresolver;
        calendarId = s;
        if (AnalyticsLoggerExtensionFactory.analyticsLogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLoggerExtensionFactory#initialize() must be called first"));
        } else
        {
            analyticsLogger = AnalyticsLoggerExtensionFactory.analyticsLogger;
            syncHooks = asynchooks;
            requestExecutor = calendarrequestexecutor;
            timelySync = timelysync;
            eventPlusPattern = Pattern.compile(Gservices.getString(contentresolver, "google_calendar_event_plus_pattern", "^http[s]?://plus(\\.[a-z0-9]+)*\\.google\\.com/events/"), 2);
            return;
        }
    }

    private static void addAttendeesToEntry(ContentValues contentvalues, ArrayList arraylist, Event event, boolean flag, TimelySync timelysync)
    {
        ArrayList arraylist1;
        ArrayList arraylist2;
        int i;
        int i1;
        arraylist1 = new ArrayList();
        arraylist2 = (ArrayList)arraylist;
        i1 = arraylist2.size();
        i = 0;
_L16:
        Object obj;
        ContentValues contentvalues1;
        if (i >= i1)
        {
            break MISSING_BLOCK_LABEL_748;
        }
        android.content.Entity.NamedContentValues namedcontentvalues = (android.content.Entity.NamedContentValues)arraylist2.get(i);
        obj = namedcontentvalues.uri;
        contentvalues1 = namedcontentvalues.values;
        if (!android.provider.CalendarContract.Attendees.CONTENT_URI.equals(obj)) goto _L2; else goto _L1
_L1:
        EventAttendee eventattendee;
        eventattendee = new EventAttendee();
        eventattendee.displayName = contentvalues1.getAsString("attendeeName");
        String s = contentvalues1.getAsString("attendeeEmail");
        obj = contentvalues1.getAsString("attendeeIdentity");
        if (obj != null && ((String) (obj)).startsWith("gprofile:"))
        {
            obj = ((String) (obj)).substring(9);
        } else
        if (s != null && s.endsWith("@profile.calendar.google.com"))
        {
            obj = s.substring(0, s.indexOf("@profile.calendar.google.com"));
        } else
        if (contentvalues.containsKey("sync_data3"))
        {
            obj = contentvalues.getAsString("sync_data3");
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            eventattendee.id = ((String) (obj));
        } else
        {
            eventattendee.email = s;
        }
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).propose_new_time();
        if (!flag) goto _L4; else goto _L3
_L3:
        int j;
        int j1;
        obj = (ArrayList)arraylist;
        j1 = ((ArrayList) (obj)).size();
        j = 0;
_L8:
        if (j >= j1) goto _L6; else goto _L5
_L5:
        Object obj1;
        int l;
        obj1 = ((ArrayList) (obj)).get(j);
        l = j + 1;
        obj1 = (android.content.Entity.NamedContentValues)obj1;
        j = l;
        if (!android.provider.CalendarContract.ExtendedProperties.CONTENT_URI.equals(((android.content.Entity.NamedContentValues) (obj1)).uri)) goto _L8; else goto _L7
_L7:
        obj1 = ((android.content.Entity.NamedContentValues) (obj1)).values;
        j = l;
        if (obj1 == null) goto _L8; else goto _L9
_L9:
        j = l;
        if (!((ContentValues) (obj1)).getAsString("name").equals("eventAttendeeList")) goto _L8; else goto _L10
_L10:
        obj1 = ((ContentValues) (obj1)).getAsString("value");
        j = l;
        if (TextUtils.isEmpty(((CharSequence) (obj1)))) goto _L8; else goto _L11
_L11:
        Iterator iterator;
        obj = TimelyEventData.createAttendees(new AndroidJsonFactory(), ((String) (obj1)));
        class .Lambda._cls0
            implements Predicate
        {

            private final EventAttendee arg$1;

            public final boolean apply(Object obj3)
            {
                return EventHandler.lambda$getEventAttendeeFromList$0$EventHandler(arg$1, (EventAttendee)obj3);
            }

            .Lambda._cls0(EventAttendee eventattendee)
            {
                arg$1 = eventattendee;
            }
        }

        if (obj == null)
        {
            obj = Collections.emptyList();
        }
_L13:
        obj1 = new .Lambda._cls0(eventattendee);
        iterator = ((Iterable) (obj)).iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L6:
        obj = Collections.emptyList();
        continue; /* Loop/switch isn't completed */
_L4:
        if (timelysync != null && contentvalues != null)
        {
            long l1 = contentvalues.getAsLong("calendar_id").longValue();
            obj = timelysync.getEventAttendees(contentvalues.getAsString("_sync_id"), l1);
        } else
        {
            obj = Collections.emptyList();
        }
        if (true) goto _L13; else goto _L12
_L12:
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_703;
            }
            obj = iterator.next();
        } while (!((Predicate) (obj1)).apply(obj));
_L14:
        Object obj2 = (EventAttendee)obj;
        int k;
        if (obj2 != null)
        {
            obj = ((EventAttendee) (obj2)).timeChangeProposal;
            if (obj == null)
            {
                obj = new TimeChangeProposal();
            }
            eventattendee.timeChangeProposal = ((TimeChangeProposal) (obj));
            eventattendee.comment = Platform.nullToEmpty(((EventAttendee) (obj2)).comment);
        }
        k = contentvalues1.getAsInteger("attendeeStatus").intValue();
        obj2 = (String)PROVIDER_TYPE_TO_ENTRY_ATTENDEE.get(k);
        obj = obj2;
        if (obj2 == null)
        {
            LogUtils.e("EventHandler", "Unknown attendee status: %d", new Object[] {
                Integer.valueOf(k)
            });
            obj = "needsAction";
        }
        eventattendee.responseStatus = ((String) (obj));
        k = contentvalues1.getAsInteger("attendeeType").intValue();
        if (k == 2)
        {
            eventattendee.optional = Boolean.valueOf(true);
        } else
        if (RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled() && k == 3)
        {
            eventattendee.resource = Boolean.valueOf(true);
        }
        arraylist1.add(eventattendee);
_L2:
        i++;
        continue; /* Loop/switch isn't completed */
        obj = null;
          goto _L14
        if (arraylist1.size() > 0)
        {
            event.attendees = arraylist1;
        }
        return;
        if (true) goto _L16; else goto _L15
_L15:
    }

    private static Map addDeletedProperties(Map map, Map map1)
    {
        if (map != null) goto _L2; else goto _L1
_L1:
        return map1;
_L2:
        Object obj = map1;
        if (map1 == null)
        {
            obj = new HashMap();
        }
        map = map.keySet().iterator();
        do
        {
            map1 = ((Map) (obj));
            if (!map.hasNext())
            {
                continue;
            }
            map1 = (String)map.next();
            if (!((Map) (obj)).containsKey(map1))
            {
                ((Map) (obj)).put(map1, "");
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    private static void addEventExtrasToEntry(HashMap hashmap, Event event)
    {
        hashmap = hashmap.entrySet().iterator();
        do
        {
            if (!hashmap.hasNext())
            {
                break;
            }
            Object obj = (java.util.Map.Entry)hashmap.next();
            Object obj1 = (String)((java.util.Map.Entry) (obj)).getKey();
            if (((String) (obj1)).equals("endTimeUnspecified"))
            {
                obj = Utilities.getValueAsBoolean(((String) (obj1)), ((java.util.Map.Entry) (obj)).getValue());
                if (obj != null)
                {
                    event.endTimeUnspecified = ((Boolean) (obj));
                }
            } else
            if (((String) (obj1)).equals("secretEvent"))
            {
                obj = Utilities.getValueAsBoolean(((String) (obj1)), ((java.util.Map.Entry) (obj)).getValue());
                if (obj != null && ((Boolean) (obj)).booleanValue())
                {
                    event.visibility = "secret";
                }
            } else
            if (((String) (obj1)).equals("organizer"))
            {
                obj1 = new com.google.api.services.calendar.model.Event.Organizer();
                obj1.email = ((java.util.Map.Entry) (obj)).getValue().toString();
                event.organizer = ((com.google.api.services.calendar.model.Event.Organizer) (obj1));
            } else
            if (((String) (obj1)).equals("iCalUid"))
            {
                obj = ((java.util.Map.Entry) (obj)).getValue();
                if (obj instanceof CharSequence)
                {
                    event.iCalUID = obj.toString();
                }
            } else
            if (((String) (obj1)).equals("sequenceNumber"))
            {
                obj = getValueAsInteger(((java.util.Map.Entry) (obj)));
                if (obj != null)
                {
                    event.sequence = ((Integer) (obj));
                }
            }
        } while (true);
    }

    private static void addExtendedPropertiesAsContentValues(List list, Map map, String s)
    {
        if (map != null)
        {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                Object obj = (java.util.Map.Entry)iterator.next();
                map = (String)((java.util.Map.Entry) (obj)).getKey();
                obj = (String)((java.util.Map.Entry) (obj)).getValue();
                if (!((String) (obj)).isEmpty() && !map.startsWith("alarmReminder"))
                {
                    ContentValues contentvalues = new ContentValues();
                    String s1 = String.valueOf(s);
                    map = String.valueOf(map);
                    if (map.length() != 0)
                    {
                        map = s1.concat(map);
                    } else
                    {
                        map = new String(s1);
                    }
                    contentvalues.put("name", map);
                    contentvalues.put("value", ((String) (obj)));
                    list.add(contentvalues);
                }
            }
        }
    }

    private static void addExtendedPropertiesToEntry(ArrayList arraylist, Event event)
    {
        Object obj = event.extendedProperties;
        com.google.api.services.calendar.model.Event.ExtendedProperties extendedproperties = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj));
        if (obj == null)
        {
            extendedproperties = new com.google.api.services.calendar.model.Event.ExtendedProperties();
            event.extendedProperties = extendedproperties;
        }
        obj = extendedproperties.private__;
        event = ((Event) (obj));
        if (obj == null)
        {
            event = new HashMap();
            extendedproperties.private__ = event;
        }
        Map map = extendedproperties.shared;
        obj = map;
        if (map == null)
        {
            obj = new HashMap();
            extendedproperties.shared = ((Map) (obj));
        }
        arraylist = (ArrayList)arraylist;
        int k = arraylist.size();
        int i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj1 = arraylist.get(i);
            int j = i + 1;
            Object obj2 = (android.content.Entity.NamedContentValues)obj1;
            obj1 = ((android.content.Entity.NamedContentValues) (obj2)).uri;
            obj2 = ((android.content.Entity.NamedContentValues) (obj2)).values;
            i = j;
            if (android.provider.CalendarContract.ExtendedProperties.CONTENT_URI.equals(obj1))
            {
                String s = ((ContentValues) (obj2)).getAsString("name");
                obj2 = ((ContentValues) (obj2)).getAsString("value");
                if (s.startsWith("shared:"))
                {
                    ((Map) (obj)).put(s.substring(7), obj2);
                    i = j;
                } else
                if (s.startsWith("private:"))
                {
                    event.put(s.substring(8), obj2);
                    i = j;
                } else
                {
                    i = j;
                    if (!ExtendedPropertiesUtils.EXTENDED_PROPERTIES_BLACK_LIST.contains(s))
                    {
                        event.put(s, obj2);
                        i = j;
                    }
                }
            }
        } while (true);
    }

    public static void addRecurrenceToEntry(com.android.calendarcommon2.ICalendar.Component component, Event event)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = component.propsMap.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (String)iterator.next();
            if ("RRULE".equals(obj) || "RDATE".equals(obj) || "EXRULE".equals(obj) || "EXDATE".equals(obj))
            {
                obj = ((List)component.propsMap.get(obj)).iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    arraylist.add(((com.android.calendarcommon2.ICalendar.Property)((Iterator) (obj)).next()).toString());
                }
            }
        } while (true);
        event.recurrence = arraylist;
    }

    private static void addRemindersToEntry(ArrayList arraylist, Event event)
    {
        ArrayList arraylist1;
        ArrayList arraylist2;
        int i;
        int j;
        boolean flag;
        arraylist2 = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist = (ArrayList)arraylist;
        j = arraylist.size();
        i = 0;
        flag = false;
_L7:
        Object obj;
        Object obj1;
        int k;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_373;
        }
        obj = arraylist.get(i);
        i++;
        obj1 = (android.content.Entity.NamedContentValues)obj;
        obj = ((android.content.Entity.NamedContentValues) (obj1)).uri;
        obj1 = ((android.content.Entity.NamedContentValues) (obj1)).values;
        if (!android.provider.CalendarContract.Reminders.CONTENT_URI.equals(obj))
        {
            break MISSING_BLOCK_LABEL_326;
        }
        k = ((ContentValues) (obj1)).getAsInteger("method").intValue();
        obj = ((ContentValues) (obj1)).getAsInteger("minutes");
        obj1 = new EventReminder();
        obj1.minutes = ((Integer) (obj));
        k;
        JVM INSTR tableswitch 1 4: default 156
    //                   1 190
    //                   2 210
    //                   3 230
    //                   4 250;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        obj1.method = "popup";
        arraylist2.add(obj1);
_L8:
        boolean flag1;
        flag1 = flag;
        if (arraylist2.size() == 5)
        {
            break MISSING_BLOCK_LABEL_377;
        }
        if (true) goto _L7; else goto _L6
_L6:
        obj1.method = "popup";
        arraylist2.add(obj1);
          goto _L8
_L3:
        obj1.method = "email";
        arraylist2.add(obj1);
          goto _L8
_L4:
        obj1.method = "sms";
        arraylist2.add(obj1);
          goto _L8
_L5:
        obj1 = new ContentValues();
        int l = arraylist1.size();
        ((ContentValues) (obj1)).put("name", (new StringBuilder(33)).append("private:alarmReminder-").append(l).toString());
        ((ContentValues) (obj1)).put("value", ((Integer) (obj)));
        arraylist1.add(new android.content.Entity.NamedContentValues(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, ((ContentValues) (obj1))));
          goto _L8
        if (android.provider.CalendarContract.ExtendedProperties.CONTENT_URI.equals(obj) && ((ContentValues) (obj1)).getAsString("name").equals("clearDefaultReminders"))
        {
            flag = "1".equals(((ContentValues) (obj1)).getAsString("value"));
        }
          goto _L8
        flag1 = flag;
        if (arraylist2.size() > 0)
        {
            arraylist = new com.google.api.services.calendar.model.Event.Reminders();
            arraylist.useDefault = Boolean.valueOf(false);
            arraylist.overrides = arraylist2;
            event.reminders = arraylist;
        }
        if (flag1)
        {
            arraylist = new com.google.api.services.calendar.model.Event.Reminders();
            arraylist.useDefault = Boolean.valueOf(false);
            arraylist.overrides = new ArrayList();
            event.reminders = arraylist;
        }
        if (arraylist1.size() > 0)
        {
            addExtendedPropertiesToEntry(arraylist1, event);
        }
        return;
    }

    private final void addSubValuesOperations(List list, Long long1, Integer integer, Uri uri, String s, List list1, Entity entity)
    {
        Object obj = list1;
        if (entity == null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        obj = new ArrayList();
        LogUtils.v("EventHandler", "Optimizing update for %s/%s", new Object[] {
            long1, uri.getPath()
        });
        entity = (ArrayList)entity.getSubValues();
        int j1 = entity.size();
        int i = 0;
        do
        {
            if (i >= j1)
            {
                break;
            }
            Object obj1 = entity.get(i);
            int k = i + 1;
            obj1 = (android.content.Entity.NamedContentValues)obj1;
            i = k;
            if (((android.content.Entity.NamedContentValues) (obj1)).uri.equals(uri))
            {
                ((List) (obj)).add(((android.content.Entity.NamedContentValues) (obj1)).values);
                i = k;
            }
        } while (true);
        list1 = new ArrayList(list1);
        entity = new ArrayList(list1);
        arraylist = new ArrayList(((java.util.Collection) (obj)));
        Iterator iterator = arraylist.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj3 = (ContentValues)iterator.next();
            ContentValues contentvalues = new ContentValues(((ContentValues) (obj3)));
            contentvalues.remove("_id");
            obj3 = ((ContentValues) (obj3)).valueSet().iterator();
            do
            {
                if (!((Iterator) (obj3)).hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj3)).next();
                if (entry.getValue() == null)
                {
                    contentvalues.remove((String)entry.getKey());
                }
            } while (true);
            if (entity.remove(contentvalues))
            {
                iterator.remove();
            }
        } while (true);
        LogUtils.v("EventHandler", "Optimizing update: stale %d/%d, new %d/%d", new Object[] {
            Integer.valueOf(arraylist.size()), Integer.valueOf(((List) (obj)).size()), Integer.valueOf(entity.size()), Integer.valueOf(list1.size())
        });
        if (!arraylist.isEmpty()) goto _L4; else goto _L3
_L3:
        int j;
        LogUtils.v("EventHandler", "Optimized: skip delete, insert %d rows", new Object[] {
            Integer.valueOf(entity.size())
        });
        ((List) (obj)).clear();
        list1.clear();
        list1.addAll(entity);
        j = 0;
_L12:
        j;
        JVM INSTR tableswitch 0 2: default 456
    //                   0 937
    //                   1 809
    //                   2 828;
           goto _L5 _L6 _L7 _L8
_L5:
        LogUtils.wtf("EventHandler", "Unknown sub values update mode: %d", new Object[] {
            Integer.valueOf(j)
        });
        obj = list1;
        break; /* Loop/switch isn't completed */
_L4:
        if (arraylist.size() >= ((List) (obj)).size()) goto _L10; else goto _L9
_L9:
        hashset = new HashSet();
        l = ((List) (obj)).size();
        j = 0;
_L13:
        if (j >= l)
        {
            break MISSING_BLOCK_LABEL_945;
        }
        Object obj2 = ((ContentValues)((List) (obj)).get(j)).get(s);
        if (obj2 == null)
        {
            LogUtils.v("EventHandler", "Skipping optimize: cv[%s] == null", new Object[] {
                s
            });
            j = 0;
        } else
        {
label0:
            {
                if (hashset.add(obj2))
                {
                    break label0;
                }
                LogUtils.v("EventHandler", "Skipping optimize: cv[%s] == %s repeated", new Object[] {
                    s, obj2
                });
                j = 0;
            }
        }
_L14:
        if (j == 0) goto _L10; else goto _L11
_L11:
        LogUtils.v("EventHandler", "Optimized: delete %d rows, insert %d rows", new Object[] {
            Integer.valueOf(arraylist.size()), Integer.valueOf(entity.size())
        });
        ((List) (obj)).clear();
        ((List) (obj)).addAll(arraylist);
        list1.clear();
        list1.addAll(entity);
        j = 2;
          goto _L12
        j++;
          goto _L13
_L10:
        LogUtils.v("EventHandler", "Could not optimize: delete all (%d), insert %d rows", new Object[] {
            Integer.valueOf(((List) (obj)).size()), Integer.valueOf(list1.size())
        });
        ((List) (obj)).clear();
        j = 1;
          goto _L12
_L7:
        CalendarSyncAdapterApiary.addAsSyncAdapterDeleteOperation(list, uri, account, long1, false);
        obj = list1;
          goto _L2
_L8:
        entity = new ArrayList();
        int i1 = ((List) (obj)).size();
        for (j = 0; j < i1; j++)
        {
            entity.add(((ContentValues)((List) (obj)).get(j)).getAsString(s));
            if (entity.size() == 10 || j == i1 - 1)
            {
                CalendarSyncAdapterApiary.addAsSyncAdapterSecondaryDeleteOperation(list, uri, account, long1.longValue(), s, entity);
                entity.clear();
            }
        }

        obj = list1;
          goto _L2
_L6:
        obj = list1;
_L2:
        HashSet hashset;
        int l;
        for (s = ((List) (obj)).iterator(); s.hasNext(); CalendarSyncAdapterApiary.addAsSyncAdapterInsertOperation(list, uri, account, list1, long1, integer, false))
        {
            list1 = (ContentValues)s.next();
        }

        return;
        j = 1;
          goto _L14
    }

    private static String appendRecurrenceString(String s, String s1)
    {
        if (s == null)
        {
            return s1;
        } else
        {
            return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("\n").append(s1).toString();
        }
    }

    static int compareObjects(Comparable comparable, Comparable comparable1)
    {
        if (comparable == null)
        {
            return comparable1 != null ? -1 : 0;
        }
        if (comparable1 == null)
        {
            return 1;
        } else
        {
            return comparable.compareTo(comparable1);
        }
    }

    private final Event convertEntityToEvent(Entity entity, Entity entity1)
        throws RemoteException, ParseException
    {
        Event event;
        HashMap hashmap;
        ContentValues contentvalues;
        boolean flag;
        event = new Event();
        contentvalues = entity.getEntityValues();
        Object obj1 = entity.getSubValues();
        hashmap = getWriteListedValues(((ArrayList) (obj1)));
        Integer integer;
        String s;
        long l;
        long l1;
        boolean flag1;
        if (entity != entity1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!contentvalues.containsKey("eventStatus")) goto _L2; else goto _L1
_L1:
        integer = contentvalues.getAsInteger("eventStatus");
        entity = integer;
        if (integer == null)
        {
            entity = Integer.valueOf(0);
        }
        entity.intValue();
        JVM INSTR tableswitch 0 2: default 96
    //                   0 1036
    //                   1 1029
    //                   2 1022;
           goto _L3 _L4 _L5 _L6
_L3:
        LogUtils.e("EventHandler", "Unexpected value for status: %d; using tentative.", new Object[] {
            entity
        });
        entity = "tentative";
_L22:
        event.status = entity;
_L2:
        if (!contentvalues.containsKey("accessLevel")) goto _L8; else goto _L7
_L7:
        integer = contentvalues.getAsInteger("accessLevel");
        entity = integer;
        if (integer == null)
        {
            entity = Integer.valueOf(0);
        }
        entity.intValue();
        JVM INSTR tableswitch 0 3: default 188
    //                   0 1042
    //                   1 1049
    //                   2 1056
    //                   3 1063;
           goto _L9 _L10 _L11 _L12 _L13
_L9:
        LogUtils.e("EventHandler", "Unexpected value for visibility: %d; using default visibility.", new Object[] {
            entity
        });
        entity = "default";
_L19:
        event.visibility = entity;
_L8:
        if (!contentvalues.containsKey("availability")) goto _L15; else goto _L14
_L14:
        integer = contentvalues.getAsInteger("availability");
        entity = integer;
        if (integer == null)
        {
            entity = Integer.valueOf(0);
        }
        entity.intValue();
        JVM INSTR tableswitch 0 1: default 272
    //                   0 1070
    //                   1 1077;
           goto _L16 _L17 _L18
_L16:
        LogUtils.e("EventHandler", "Unexpected value for transparency: %d; using opaque transparency.", new Object[] {
            entity
        });
        entity = "opaque";
_L20:
        event.transparency = entity;
_L15:
        if (contentvalues.containsKey("title"))
        {
            event.summary = contentvalues.getAsString("title");
        }
        if (contentvalues.containsKey("description"))
        {
            event.description = contentvalues.getAsString("description");
        }
        addAttendeesToEntry(contentvalues, ((ArrayList) (obj1)), event, true, null);
        entity = new com.android.calendarcommon2.ICalendar.Component("DUMMY", null);
        RecurrenceSet.addPropertiesForRuleStr(entity, "RRULE", contentvalues.getAsString("rrule"));
        RecurrenceSet.addPropertyForDateStr(entity, "RDATE", contentvalues.getAsString("rdate"));
        RecurrenceSet.addPropertiesForRuleStr(entity, "EXRULE", contentvalues.getAsString("exrule"));
        RecurrenceSet.addPropertyForDateStr(entity, "EXDATE", contentvalues.getAsString("exdate"));
        Object obj;
        Object obj2;
        int i;
        int j;
        if (((com.android.calendarcommon2.ICalendar.Component) (entity)).propsMap.keySet().size() > 0)
        {
            addRecurrenceToEntry(entity, event);
            j = 1;
        } else
        {
            j = 0;
        }
        flag1 = Integer.valueOf(1).equals(contentvalues.getAsInteger("allDay"));
        obj = contentvalues.getAsString("eventTimezone");
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            entity = TimeZone.getDefault().getID();
            i = 0;
        } else
        {
            entity = TimeZoneUtils.checkForValidTimezoneId(((String) (obj)));
            ContentProviderClient contentproviderclient;
            Uri uri;
            String s1;
            int k;
            boolean flag2;
            if (entity != null)
            {
                obj = new ContentValues();
                ((ContentValues) (obj)).put("eventTimezone", entity);
                ProviderHelper providerhelper = ProviderHelper.asSyncAdapter(account);
                ContentProviderClient contentproviderclient1 = provider;
                Uri uri1 = android.provider.CalendarContract.Events.CONTENT_URI;
                String s2 = String.valueOf(contentvalues.get("_id"));
                providerhelper.update(contentproviderclient1, uri1, ((ContentValues) (obj)), (new StringBuilder(String.valueOf(s2).length() + 4)).append("_id=").append(s2).toString(), null);
                i = 1;
            } else
            {
                entity = ((Entity) (obj));
                i = 1;
            }
        }
        if (contentvalues.containsKey("dtstart"))
        {
            obj = contentvalues.getAsLong("dtstart");
            if (obj != null)
            {
                obj = createEventDateTime(((Long) (obj)).longValue(), flag1);
                if (j != 0 || i != 0)
                {
                    obj.timeZone = entity;
                }
                event.start = ((EventDateTime) (obj));
            } else
            {
                LogUtils.wtf("EventHandler", "Start time was null", new Object[0]);
            }
        }
        if (contentvalues.containsKey("dtend"))
        {
            obj = contentvalues.getAsLong("dtend");
            if (obj != null)
            {
                obj = createEventDateTime(((Long) (obj)).longValue(), flag1);
                if (j != 0 || i != 0)
                {
                    obj.timeZone = entity;
                }
                event.end = ((EventDateTime) (obj));
            }
        }
        addRemindersToEntry(((ArrayList) (obj1)), event);
        obj = entity1.getEntityValues().getAsInteger("hasExtendedProperties");
        if (obj != null && ((Integer) (obj)).intValue() != 0)
        {
            addExtendedPropertiesToEntry(((ArrayList) (obj1)), event);
        }
        l1 = -1L;
        obj = contentvalues.getAsString("original_sync_id");
        l = l1;
        if (contentvalues.containsKey("originalInstanceTime"))
        {
            l = l1;
            if (contentvalues.getAsLong("originalInstanceTime") != null)
            {
                l = contentvalues.getAsLong("originalInstanceTime").longValue();
            }
        }
        if (l != -1L && !TextUtils.isEmpty(((CharSequence) (obj))))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_890;
        }
        flag1 = getOriginalAllDay(provider, contentvalues.getAsInteger("original_id"), contentvalues.getAsInteger("originalAllDay"));
        (new Time(entity)).set(l);
        event.originalStartTime = createEventDateTime(l, flag1);
        event.recurringEventId = ((String) (obj));
        entity = ProviderHelper.asClient();
        obj = provider;
        obj1 = android.provider.CalendarContract.Events.CONTENT_URI;
        s = entity1.getEntityValues().getAsString("_id");
        entity = entity.query(((ContentProviderClient) (obj)), ((Uri) (obj1)), new String[] {
            "sync_data2", "hasAttendeeData"
        }, "_id=?", new String[] {
            s
        }, null);
        if (entity == null)
        {
            break MISSING_BLOCK_LABEL_890;
        }
        if (!entity.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_884;
        }
        j = entity.getInt(0);
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_865;
        }
        event.sequence = Integer.valueOf(j);
        if (entity.getInt(1) == 0)
        {
            event.attendeesOmitted = Boolean.valueOf(true);
        }
        entity.close();
        if (contentvalues.containsKey("_sync_id"))
        {
            entity = contentvalues.getAsString("_sync_id");
            event.id = entity;
        } else
        {
label0:
            {
                if (i != 0)
                {
                    break label0;
                }
                entity = ((Entity) (hashmap.get("iCalUid")));
                if (entity == null || !(entity instanceof CharSequence) || TextUtils.isEmpty(entity.toString()))
                {
                    entity = null;
                } else
                {
                    entity = entity.toString();
                }
                if (entity != null || flag)
                {
                    break label0;
                }
                entity = BaseEncoding.BASE32_HEX.lowerCase().omitPadding();
                obj = UUID.randomUUID().toString().getBytes();
                entity = entity.encode(((byte []) (obj)), 0, obj.length);
                obj = new ContentValues(1);
                ((ContentValues) (obj)).put("_sync_id", entity);
                obj2 = ProviderHelper.asSyncAdapter(account);
                contentproviderclient = provider;
                uri = android.provider.CalendarContract.Events.CONTENT_URI;
                s1 = String.valueOf(contentvalues.get("_id"));
                ((ProviderHelper) (obj2)).update(contentproviderclient, uri, ((ContentValues) (obj)), (new StringBuilder(String.valueOf(s1).length() + 4)).append("_id=").append(s1).toString(), null);
                event.id = entity;
            }
        }
        break MISSING_BLOCK_LABEL_916;
_L6:
        entity = "cancelled";
        continue; /* Loop/switch isn't completed */
_L5:
        entity = "confirmed";
        continue; /* Loop/switch isn't completed */
_L4:
        entity = "tentative";
        continue; /* Loop/switch isn't completed */
_L10:
        entity = "default";
          goto _L19
_L11:
        entity = "confidential";
          goto _L19
_L12:
        entity = "private";
          goto _L19
_L13:
        entity = "public";
          goto _L19
_L17:
        entity = "opaque";
          goto _L20
_L18:
        entity = "transparent";
          goto _L20
        entity1;
        entity.close();
        throw entity1;
        entity = null;
        if (contentvalues.containsKey("eventLocation"))
        {
            event.location = contentvalues.getAsString("eventLocation");
            setStructuredLocationForEvent(event, entity1.getEntityValues(), hashmap, flag);
        }
        obj = contentvalues.getAsLong("calendar_id");
        timelySync.addTitleContactAnnotationsToEntry(hashmap, entity, ((Long) (obj)), event);
        timelySync.addAttachmentsToEntry(hashmap, entity, ((Long) (obj)), event);
        obj2 = Features.instance;
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj2).ooo_sync();
        timelySync.addParticipantStatusToEntry(hashmap, entity, ((Long) (obj)), event);
        if (contentvalues.containsKey("guestsCanInviteOthers"))
        {
            if (contentvalues.getAsInteger("guestsCanInviteOthers").intValue() != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            event.guestsCanInviteOthers = Boolean.valueOf(flag2);
        }
        if (contentvalues.containsKey("guestsCanModify"))
        {
            if (contentvalues.getAsInteger("guestsCanModify").intValue() != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            event.guestsCanModify = Boolean.valueOf(flag2);
        }
        if (contentvalues.containsKey("guestsCanSeeGuests"))
        {
            if (contentvalues.getAsInteger("guestsCanSeeGuests").intValue() != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            event.guestsCanSeeOtherGuests = Boolean.valueOf(flag2);
        }
        if (contentvalues.containsKey("organizer"))
        {
            entity = new com.google.api.services.calendar.model.Event.Organizer();
            entity.email = contentvalues.getAsString("organizer");
            event.organizer = entity;
        }
        if (contentvalues.containsKey("eventColor_index"))
        {
            entity = contentvalues.getAsString("eventColor_index");
            if (TextUtils.isEmpty(entity))
            {
                event.colorId = Data.NULL_STRING;
            } else
            {
                event.colorId = entity;
            }
        }
        addEventExtrasToEntry(hashmap, event);
        timelySync.addConferenceDetailsToEvent(entity1, hashmap, flag, event, calendarId, account);
        entity = syncHooks;
        k = entity.length;
        for (i = 0; i < k; i++)
        {
            entity[i].onAfterConvertEntityToEvent(entity1, event, flag);
        }

        return event;
        if (true) goto _L22; else goto _L21
_L21:
    }

    private static EventDateTime createEventDateTime(long l, boolean flag)
    {
        EventDateTime eventdatetime = new EventDateTime();
        if (flag)
        {
            eventdatetime.date = new DateTime(true, l, null);
            return eventdatetime;
        } else
        {
            eventdatetime.dateTime = new DateTime(l, 0);
            return eventdatetime;
        }
    }

    private final int entryToContentValues(Event event, ContentValues contentvalues, TimelyEventData timelyeventdata, CalendarSyncInfo calendarsyncinfo, long l)
        throws RemoteException, ParseException
    {
        contentvalues.clear();
        timelyeventdata.structuredLocation = null;
        timelyeventdata.smartMailInfo = null;
        timelyeventdata.eventSource = null;
        timelyeventdata.backgroundImageUrl = null;
        timelyeventdata.titleContactAnnotations = null;
        timelyeventdata.eventGadget = null;
        timelyeventdata.attachments = null;
        timelyeventdata.conferenceData = null;
        timelyeventdata.responseSummary = null;
        timelyeventdata.participantStatus = null;
        timelyeventdata.attendees = null;
        contentvalues.putNull("sync_data7");
        contentvalues.put("deleted", Integer.valueOf(0));
        contentvalues.put("dirty", Integer.valueOf(0));
        String s1 = event.id;
        contentvalues.put("sync_data4", event.etag);
        contentvalues.put("_sync_id", s1);
        Object obj = event.iCalUID;
        if (obj != null)
        {
            contentvalues.put("sync_data1", ((String) (obj)));
        }
        obj = event.sequence;
        if (obj != null)
        {
            contentvalues.put("sync_data2", ((Integer) (obj)));
        }
        obj = event.status;
        Object obj1 = event.recurringEventId;
        Object obj2 = event.originalStartTime;
        Object obj3;
        String s;
        Object obj5;
        Object obj6;
        List list;
        String s2;
        com.android.calendarcommon2.ICalendar.Component component;
        Iterator iterator1;
        String s3;
        Iterator iterator2;
        int i;
        int j;
        int k;
        long l2;
        long l3;
        boolean flag;
        if (!TextUtils.isEmpty(((CharSequence) (obj1))) && obj2 != null)
        {
            j = 1;
            DateTime datetime = ((EventDateTime) (obj2)).date;
            long l1;
            if (datetime != null)
            {
                l1 = datetime.value;
                i = 1;
            } else
            {
                l1 = ((EventDateTime) (obj2)).dateTime.value;
                i = 0;
            }
            contentvalues.put("original_sync_id", ((String) (obj1)));
            contentvalues.put("originalInstanceTime", Long.valueOf((l1 / 1000L) * 1000L));
            contentvalues.put("originalAllDay", Integer.valueOf(i));
        } else
        {
            j = 0;
        }
        if (obj == null || "confirmed".equals(obj))
        {
            i = 1;
        } else
        if ("cancelled".equals(obj))
        {
            if (j == 0)
            {
                return 1;
            }
            i = 2;
        } else
        if ("tentative".equals(obj))
        {
            i = 0;
        } else
        {
            LogUtils.e("EventHandler", "Invalid status: %s", new Object[] {
                obj
            });
            return 2;
        }
        contentvalues.put("eventStatus", Integer.valueOf(i));
        obj = event.updated;
        if (obj != null)
        {
            contentvalues.put("sync_data5", ((DateTime) (obj)).toStringRfc3339());
        }
        if (calendarsyncinfo != null)
        {
            contentvalues.put("calendar_id", Long.valueOf(calendarsyncinfo.calendarId));
        }
        if (j != 0 && i == 2)
        {
            event = event.originalStartTime;
            timelyeventdata = ((EventDateTime) (event)).date;
            if (timelyeventdata != null)
            {
                contentvalues.put("dtstart", Long.valueOf(((DateTime) (timelyeventdata)).value));
            } else
            {
                contentvalues.put("dtstart", Long.valueOf(((EventDateTime) (event)).dateTime.value));
            }
            return 0;
        }
        obj2 = event.start;
        obj = ((EventDateTime) (obj2)).date;
        if (obj != null)
        {
            contentvalues.put("eventTimezone", "UTC");
            l2 = ((DateTime) (obj)).value;
            l3 = event.end.date.value;
            k = 1;
        } else
        {
            k = 0;
            obj1 = ((EventDateTime) (obj2)).timeZone;
            if (obj1 != null)
            {
                obj = calendarsyncinfo;
                calendarsyncinfo = ((CalendarSyncInfo) (obj1));
            } else
            {
                obj = calendarsyncinfo;
                if (calendarsyncinfo == null)
                {
                    obj = getOrCreateSyncInfoForCalendar(l);
                }
                if (obj != null)
                {
                    calendarsyncinfo = ((CalendarSyncInfo) (obj)).calendarTimezone;
                } else
                {
                    calendarsyncinfo = TimeZone.getDefault().getID();
                }
            }
            contentvalues.put("eventTimezone", calendarsyncinfo);
            l2 = ((EventDateTime) (obj2)).dateTime.value;
            l3 = event.end.dateTime.value;
            calendarsyncinfo = ((CalendarSyncInfo) (obj));
        }
        contentvalues.put("allDay", Integer.valueOf(k));
        contentvalues.put("dtstart", Long.valueOf((l2 / 1000L) * 1000L));
        obj = event.visibility;
        if (obj == null || ((String) (obj)).equals("default"))
        {
            contentvalues.put("accessLevel", Integer.valueOf(0));
        } else
        if (((String) (obj)).equals("confidential"))
        {
            contentvalues.put("accessLevel", Integer.valueOf(1));
        } else
        if (((String) (obj)).equals("private") || ((String) (obj)).equals("secret"))
        {
            contentvalues.put("accessLevel", Integer.valueOf(2));
        } else
        if (((String) (obj)).equals("public"))
        {
            contentvalues.put("accessLevel", Integer.valueOf(3));
        } else
        {
            LogUtils.e("EventHandler", "Unexpected visibility: %s", new Object[] {
                obj
            });
            return 2;
        }
        obj = event.transparency;
        if (obj == null || ((String) (obj)).equals("opaque"))
        {
            contentvalues.put("availability", Integer.valueOf(0));
        } else
        if (((String) (obj)).equals("transparent"))
        {
            contentvalues.put("availability", Integer.valueOf(1));
        } else
        {
            LogUtils.e("EventHandler", "Unexpected transparency: %s", new Object[] {
                obj
            });
            return 2;
        }
        obj1 = event.summary;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        contentvalues.put("title", ((String) (obj)));
        obj1 = event.description;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        contentvalues.put("description", ((String) (obj)));
        obj1 = event.location;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        contentvalues.put("eventLocation", ((String) (obj)));
        obj = event.htmlLink;
        if (obj != null && eventPlusPattern.matcher(((CharSequence) (obj))).find())
        {
            contentvalues.put("customAppUri", ((String) (obj)));
            contentvalues.put("customAppPackage", "com.google.android.apps.plus");
        }
        obj = event.colorId;
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            contentvalues.putNull("eventColor_index");
            contentvalues.putNull("eventColor");
        } else
        {
            contentvalues.put("eventColor_index", ((String) (obj)));
        }
        obj = event.reminders;
        if (obj != null)
        {
            if (((com.google.api.services.calendar.model.Event.Reminders) (obj)).useDefault.booleanValue())
            {
                obj = calendarsyncinfo;
                if (calendarsyncinfo == null)
                {
                    obj = getOrCreateSyncInfoForCalendar(l);
                }
                Object obj4;
                Iterator iterator;
                if (obj == null)
                {
                    calendarsyncinfo = null;
                } else
                if (k == 1)
                {
                    calendarsyncinfo = ((CalendarSyncInfo) (obj)).defaultAllDayReminders;
                } else
                {
                    calendarsyncinfo = ((CalendarSyncInfo) (obj)).defaultReminders;
                }
            } else
            {
                calendarsyncinfo = ((com.google.api.services.calendar.model.Event.Reminders) (obj)).overrides;
            }
            if (calendarsyncinfo != null && !calendarsyncinfo.isEmpty())
            {
                contentvalues.put("hasAlarm", Integer.valueOf(1));
            }
        }
        obj = event.extendedProperties;
        j = 0;
        i = j;
        if (obj == null) goto _L2; else goto _L1
_L1:
        calendarsyncinfo = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).private__;
        obj = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).shared;
        if (obj == null || ((Map) (obj)).isEmpty()) goto _L4; else goto _L3
_L3:
        i = 1;
_L2:
        contentvalues.put("hasExtendedProperties", Integer.valueOf(i));
        contentvalues.put("hasAlarm", Integer.valueOf(1));
        calendarsyncinfo = event.attendeesOmitted;
        flag = false;
        if (calendarsyncinfo != null)
        {
            flag = calendarsyncinfo.booleanValue();
        }
        if (flag)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        contentvalues.put("hasAttendeeData", Integer.valueOf(i));
        list = event.recurrence;
        calendarsyncinfo = null;
        obj4 = null;
        obj3 = null;
        obj = null;
        obj5 = null;
        obj1 = null;
        obj6 = null;
        obj2 = null;
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_2329;
        }
        iterator = list.iterator();
        calendarsyncinfo = obj4;
          goto _L5
_L4:
        i = j;
        if (calendarsyncinfo == null) goto _L2; else goto _L6
_L6:
        calendarsyncinfo = calendarsyncinfo.keySet().iterator();
        i = 0;
_L9:
        if (!calendarsyncinfo.hasNext()) goto _L2; else goto _L7
_L7:
        if (!((String)calendarsyncinfo.next()).startsWith("alarmReminder"))
        {
            j = 1;
        } else
        {
            j = i;
        }
        i = j;
        if (j == true) goto _L2; else goto _L8
_L8:
        i = j;
          goto _L9
_L5:
        do
        {
            {
                do
                {
                    if (!iterator.hasNext())
                    {
                        break MISSING_BLOCK_LABEL_1839;
                    }
                    s2 = (String)iterator.next();
                    if (s2 != null)
                    {
                        break;
                    }
                    LogUtils.w("EventHandler", "Invalid null recurrence line in event %s", new Object[] {
                        s1
                    });
                } while (true);
                try
                {
                    component = ICalendar.parseComponentImpl(new com.android.calendarcommon2.ICalendar.Component("DUMMY", null), s2.replaceAll("\r\n", "\n").replaceAll("\r", "\n").replaceAll("\n ", ""));
                }
                // Misplaced declaration of an exception variable
                catch (Event event)
                {
                    LogUtils.w("EventHandler", event, "Invalid recurrence line in event %s: %s", new Object[] {
                        s1, s2
                    });
                    return 2;
                }
                iterator1 = component.propsMap.keySet().iterator();
                obj6 = calendarsyncinfo;
                obj5 = obj;
                s = ((String) (obj1));
                obj3 = obj2;
            }
            obj2 = obj3;
            obj1 = s;
            obj = obj5;
            calendarsyncinfo = ((CalendarSyncInfo) (obj6));
        } while (!iterator1.hasNext());
        s3 = (String)iterator1.next();
        iterator2 = ((List)component.propsMap.get(s3)).iterator();
        obj2 = obj6;
        obj = obj5;
        obj1 = s;
        calendarsyncinfo = ((CalendarSyncInfo) (obj3));
        do
        {
            obj3 = calendarsyncinfo;
            s = ((String) (obj1));
            obj5 = obj;
            obj6 = obj2;
            if (!iterator2.hasNext())
            {
                break MISSING_BLOCK_LABEL_1509;
            }
            obj3 = (com.android.calendarcommon2.ICalendar.Property)iterator2.next();
            if (s3.equals("RRULE"))
            {
                try
                {
                    obj2 = appendRecurrenceString(((String) (obj2)), Utilities.sanitizeRecurrence(((com.android.calendarcommon2.ICalendar.Property) (obj3)).value));
                }
                // Misplaced declaration of an exception variable
                catch (Event event)
                {
                    LogUtils.w("EventHandler", "Invalid recurrence line in event %s: %s", new Object[] {
                        s1, s2
                    });
                    return 2;
                }
            } else
            if (s3.equals("RDATE"))
            {
                obj = appendRecurrenceString(((String) (obj)), getRecurrenceDate(((com.android.calendarcommon2.ICalendar.Property) (obj3))));
            } else
            if (s3.equals("EXRULE"))
            {
                try
                {
                    obj1 = appendRecurrenceString(((String) (obj1)), Utilities.sanitizeRecurrence(((com.android.calendarcommon2.ICalendar.Property) (obj3)).value));
                }
                // Misplaced declaration of an exception variable
                catch (Event event)
                {
                    LogUtils.w("EventHandler", "Invalid recurrence line in event %s: %s", new Object[] {
                        s1, s2
                    });
                    return 2;
                }
            } else
            if (s3.equals("EXDATE"))
            {
                calendarsyncinfo = appendRecurrenceString(calendarsyncinfo, getRecurrenceDate(((com.android.calendarcommon2.ICalendar.Property) (obj3))));
            } else
            {
                LogUtils.w("EventHandler", "Invalid recurrence line in event %s: %s", new Object[] {
                    s1, s2
                });
                return 2;
            }
        } while (true);
        break MISSING_BLOCK_LABEL_1509;
        if (true) goto _L5; else goto _L10
_L10:
        try
        {
            new RecurrenceSet(calendarsyncinfo, ((String) (obj)), ((String) (obj1)), ((String) (obj2)));
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            LogUtils.e("EventHandler", event, "Unable to parse recurrence in event %s: %s", new Object[] {
                s1, list
            });
            return 2;
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            if (event.getMessage() == null || !event.getMessage().contains("EventRecurrence$InvalidFormatException"))
            {
                throw event;
            } else
            {
                LogUtils.e("EventHandler", event, "Unable to parse recurrence in event %s: %s", new Object[] {
                    s1, list
                });
                return 2;
            }
        }
        l2 = (l3 - l2) / 1000L;
        if (k == 0)
        {
            contentvalues.put("duration", (new StringBuilder(22)).append("P").append(l2).append("S").toString());
        } else
        {
            l2 /= 0x15180L;
            contentvalues.put("duration", (new StringBuilder(22)).append("P").append(l2).append("D").toString());
        }
_L11:
        contentvalues.put("rrule", calendarsyncinfo);
        contentvalues.put("rdate", ((String) (obj)));
        contentvalues.put("exrule", ((String) (obj1)));
        contentvalues.put("exdate", ((String) (obj2)));
        calendarsyncinfo = event.guestsCanInviteOthers;
        flag = true;
        if (calendarsyncinfo != null)
        {
            flag = calendarsyncinfo.booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("guestsCanInviteOthers", Integer.valueOf(i));
        calendarsyncinfo = event.guestsCanModify;
        flag = false;
        if (calendarsyncinfo != null)
        {
            flag = calendarsyncinfo.booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("guestsCanModify", Integer.valueOf(i));
        calendarsyncinfo = event.guestsCanSeeOtherGuests;
        flag = true;
        if (calendarsyncinfo != null)
        {
            flag = calendarsyncinfo.booleanValue();
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("guestsCanSeeGuests", Integer.valueOf(i));
        obj1 = event.organizer;
        if (obj1 != null)
        {
            calendarsyncinfo = null;
            obj = ((com.google.api.services.calendar.model.Event.Organizer) (obj1)).self;
            flag = false;
            if (obj != null)
            {
                flag = ((Boolean) (obj)).booleanValue();
            }
            obj = calendarsyncinfo;
            if (flag)
            {
                if (l < 0L)
                {
                    LogUtils.w("EventHandler", new Throwable(), "Missing calendarId, can't get owner", new Object[0]);
                    obj = calendarsyncinfo;
                } else
                {
                    obj = getCalendarOwnerAccount(l);
                }
            }
            calendarsyncinfo = ((CalendarSyncInfo) (obj));
            if (obj == null)
            {
                calendarsyncinfo = ((com.google.api.services.calendar.model.Event.Organizer) (obj1)).email;
            }
            obj = calendarsyncinfo;
            if (calendarsyncinfo == null)
            {
                obj1 = ((com.google.api.services.calendar.model.Event.Organizer) (obj1)).id;
                obj = calendarsyncinfo;
                if (obj1 != null)
                {
                    calendarsyncinfo = String.valueOf(obj1);
                    obj = String.valueOf("@profile.calendar.google.com");
                    if (((String) (obj)).length() != 0)
                    {
                        obj = calendarsyncinfo.concat(((String) (obj)));
                    } else
                    {
                        obj = new String(calendarsyncinfo);
                    }
                }
            }
            contentvalues.put("organizer", ((String) (obj)));
        }
        timelySync.apiaryEventToTimelyExtras(event, timelyeventdata);
        return 0;
        contentvalues.put("dtend", Long.valueOf((l3 / 1000L) * 1000L));
        obj2 = obj6;
        obj1 = obj5;
        obj = obj3;
          goto _L11
    }

    private final Entity fetchEntity(String s, String as[])
        throws RemoteException, ParseException
    {
        s = newEntityIterator(s, as, 1);
        if (!s.hasNext())
        {
            break MISSING_BLOCK_LABEL_35;
        }
        as = (Entity)s.next();
        s.close();
        return as;
        s.close();
        return null;
        as;
        s.close();
        throw as;
    }

    private final String getCalendarOwnerAccount(long l)
        throws RemoteException, ParseException
    {
        String s;
        Cursor cursor;
        s = null;
        cursor = ProviderHelper.asClient().query(provider, android.provider.CalendarContract.Calendars.CONTENT_URI.buildUpon().appendPath(String.valueOf(l)).build(), new String[] {
            "ownerAccount"
        }, null, null, null);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_76;
        }
        s = cursor.getString(0);
        cursor.close();
        return s;
        cursor.close();
        return null;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
    }

    private final Event getFallbackAfterError(String s, String s1, String s2, Entity entity, int i)
        throws IOException
    {
        try
        {
            CalendarRequestExecutor calendarrequestexecutor = requestExecutor;
            com.google.api.services.calendar.Calendar.Events events = new com.google.api.services.calendar.Calendar.Events(client);
            s = new com.google.api.services.calendar.Calendar.Events.Get(events, s, s1);
            events.this$0.initialize(s);
            s.maxAttendees = Integer.valueOf(i);
            s = (Event)calendarrequestexecutor.execute("API: Get Event", s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            analyticsLogger.logEventHttpException("EventHandler::GetFallbackAfterError", s, s2, entity);
            switch (((HttpResponseException) (s)).statusCode)
            {
            default:
                throw s;

            case 404: 
            case 414: 
                return null;
            }
        }
        return s;
    }

    private final CalendarSyncInfo getOrCreateSyncInfoForCalendar(long l)
        throws RemoteException, ParseException
    {
        Cursor cursor;
label0:
        {
            CalendarSyncInfo calendarsyncinfo1 = (CalendarSyncInfo)calendarIdToLocalSyncInfo.get(l);
            CalendarSyncInfo calendarsyncinfo = calendarsyncinfo1;
            if (calendarsyncinfo1 == null)
            {
                calendarsyncinfo = new CalendarSyncInfo();
                String s = String.valueOf(l);
                calendarsyncinfo.calendarId = l;
                cursor = ProviderHelper.asClient().query(provider, android.provider.CalendarContract.Calendars.CONTENT_URI, new String[] {
                    "cal_sync1", "calendar_timezone", "calendar_access_level", "account_name", "account_type"
                }, "_id=?", new String[] {
                    s
                }, null);
                if (cursor.getCount() != 1)
                {
                    break label0;
                }
                cursor.moveToFirst();
                calendarsyncinfo.calendarSyncId = cursor.getString(0);
                calendarsyncinfo.calendarTimezone = cursor.getString(1);
                calendarsyncinfo.accessLevel = cursor.getInt(2);
                calendarsyncinfo.account = new Account(cursor.getString(3), cursor.getString(4));
                cursor.close();
                timelySync.fillSyncInfo(calendarsyncinfo, s);
                calendarIdToLocalSyncInfo.put(l, calendarsyncinfo);
            }
            return calendarsyncinfo;
        }
        cursor.close();
        throw new ParseException("Could not get calendar details.");
    }

    private static boolean getOriginalAllDay(ContentProviderClient contentproviderclient, Integer integer, Integer integer1)
    {
        Object obj;
        boolean flag;
        obj = null;
        flag = Integer.valueOf(1).equals(integer1);
        if (integer != null) goto _L2; else goto _L1
_L1:
        LogUtils.w("EventHandler", "No original event local id for an exception of a recurring event.", new Object[0]);
_L4:
        return flag;
_L2:
        integer1 = ProviderHelper.asClient();
        Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        integer = String.valueOf(integer);
        integer = (new StringBuilder(String.valueOf(integer).length() + 4)).append("_id=").append(integer).toString();
        integer = integer1.query(contentproviderclient, uri, new String[] {
            "allDay"
        }, integer, null, null);
        contentproviderclient = integer;
        if (!integer.moveToFirst())
        {
            break; /* Loop/switch isn't completed */
        }
        contentproviderclient = integer;
        boolean flag1 = Integer.valueOf(1).equals(Integer.valueOf(integer.getInt(0)));
        flag = flag1;
        if (integer != null)
        {
            integer.close();
            return flag1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (integer != null)
        {
            integer.close();
        }
_L5:
        return flag;
        integer1;
        integer = null;
_L8:
        contentproviderclient = integer;
        LogUtils.w("EventHandler", integer1, "Could not calculate originalAllDay value for an exception to recurring event.", new Object[0]);
        if (integer != null)
        {
            integer.close();
        }
          goto _L5
        contentproviderclient;
        integer = obj;
_L7:
        if (integer != null)
        {
            integer.close();
        }
        throw contentproviderclient;
        integer1;
        integer = contentproviderclient;
        contentproviderclient = integer1;
        if (true) goto _L7; else goto _L6
_L6:
        integer1;
          goto _L8
    }

    private static String getRecurrenceDate(com.android.calendarcommon2.ICalendar.Property property)
    {
        Object obj = (ArrayList)property.paramsMap.get("TZID");
        if (obj == null || ((ArrayList) (obj)).size() == 0)
        {
            obj = null;
        } else
        {
            obj = (com.android.calendarcommon2.ICalendar.Parameter)((ArrayList) (obj)).get(0);
        }
        if (obj != null)
        {
            obj = ((com.android.calendarcommon2.ICalendar.Parameter) (obj)).value;
            property = property.value;
            return (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(property).length())).append(((String) (obj))).append(";").append(property).toString();
        } else
        {
            return property.value;
        }
    }

    private static Set getReminderSet(com.google.api.services.calendar.model.Event.Reminders reminders)
    {
        Object obj;
        if (reminders == null)
        {
            obj = null;
        } else
        {
            TreeSet treeset = new TreeSet(new _cls2());
            obj = treeset;
            if (reminders != null)
            {
                obj = treeset;
                if (reminders.overrides != null)
                {
                    treeset.addAll(reminders.overrides);
                    return treeset;
                }
            }
        }
        return ((Set) (obj));
    }

    private static List getRemindersFromExtendedProperties(com.google.api.services.calendar.model.Event.ExtendedProperties extendedproperties)
    {
        if (extendedproperties == null || extendedproperties.private__ == null)
        {
            return Collections.emptyList();
        }
        ArrayList arraylist = new ArrayList();
        extendedproperties = extendedproperties.private__.entrySet().iterator();
        do
        {
            if (!extendedproperties.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)extendedproperties.next();
            if (((String)entry.getKey()).startsWith("alarmReminder"))
            {
                try
                {
                    ContentValues contentvalues = new ContentValues();
                    contentvalues.put("method", Integer.valueOf(4));
                    contentvalues.put("minutes", Integer.valueOf(Integer.parseInt((String)entry.getValue())));
                    arraylist.add(contentvalues);
                }
                catch (NumberFormatException numberformatexception)
                {
                    LogUtils.wtf("EventHandler", numberformatexception, "Minutes value could not be parsed for alarm reminder.", new Object[0]);
                }
            }
        } while (true);
        return arraylist;
    }

    private static Integer getValueAsInteger(java.util.Map.Entry entry)
    {
        Object obj = entry.getValue();
        if (obj instanceof CharSequence)
        {
            Integer integer;
            try
            {
                integer = Integer.valueOf(obj.toString());
            }
            catch (NumberFormatException numberformatexception)
            {
                LogUtils.e("EventHandler", "Cannot parse Integer value for %s at key %s", new Object[] {
                    obj, entry.getKey()
                });
                return null;
            }
            return integer;
        } else
        {
            LogUtils.e("EventHandler", "Cannot cast value for %s to an Integer: %s", new Object[] {
                entry.getKey(), obj
            });
            return null;
        }
    }

    private static HashMap getWriteListedValues(ArrayList arraylist)
    {
        HashMap hashmap = new HashMap(ExtendedPropertiesUtils.EXTENDED_PROPERTIES_WRITE_LIST.size());
        arraylist = (ArrayList)arraylist;
        int k = arraylist.size();
        int i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj = arraylist.get(i);
            int j = i + 1;
            obj = (android.content.Entity.NamedContentValues)obj;
            i = j;
            if (android.provider.CalendarContract.ExtendedProperties.CONTENT_URI.equals(((android.content.Entity.NamedContentValues) (obj)).uri))
            {
                obj = ((android.content.Entity.NamedContentValues) (obj)).values;
                String s = ((ContentValues) (obj)).getAsString("name");
                i = j;
                if (ExtendedPropertiesUtils.EXTENDED_PROPERTIES_WRITE_LIST.contains(s))
                {
                    hashmap.put(s, ((ContentValues) (obj)).getAsString("value"));
                    i = j;
                }
            }
        } while (true);
        return hashmap;
    }

    private static boolean hasOnlyUnsyncedKeys(Event event)
    {
        for (event = event.keySet().iterator(); event.hasNext();)
        {
            String s = (String)event.next();
            if (!UNSYNCED_EVENT_KEYS.contains(s))
            {
                return false;
            }
        }

        return true;
    }

    static boolean isEqual(Object obj, Object obj1)
    {
        if (obj == null)
        {
            return obj1 == null;
        } else
        {
            return obj.equals(obj1);
        }
    }

    static final boolean lambda$getEventAttendeeFromList$0$EventHandler(EventAttendee eventattendee, EventAttendee eventattendee1)
    {
        String s = eventattendee.email;
        String s1 = eventattendee1.email;
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            eventattendee = eventattendee.id;
            eventattendee1 = eventattendee1.id;
            if (eventattendee == eventattendee1 || eventattendee != null && eventattendee.equals(eventattendee1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    private static long parseDuration(String s)
    {
        if (s == null)
        {
            return -1L;
        }
        Duration duration = new Duration();
        long l;
        try
        {
            duration.parse(s);
            l = duration.getMillis();
        }
        catch (DateException dateexception)
        {
            LogUtils.w("EventHandler", "Bad DURATION: %s", new Object[] {
                s
            });
            return -1L;
        }
        return l;
    }

    private static void setDtendFromDuration(ContentValues contentvalues, long l)
    {
        Long long1;
label0:
        {
            if (l >= 0L)
            {
                long1 = contentvalues.getAsLong("dtstart");
                if (long1 != null)
                {
                    break label0;
                }
                LogUtils.e("EventHandler", "Event has DURATION but no DTSTART", new Object[0]);
            }
            return;
        }
        contentvalues.put("dtend", Long.valueOf(long1.longValue() + l));
    }

    private static void setStructuredLocationForEvent(Event event, ContentValues contentvalues, Map map, boolean flag)
    {
label0:
        {
            map = (String)map.get("locationExtra");
            if (!TextUtils.isEmpty(map))
            {
                event.structuredLocation = TimelyEventData.createStructuredLocation(new AndroidJsonFactory(), map);
                return;
            }
            map = EventExtrasFlags.fromExisting(contentvalues, "sync_data9");
            if (flag && event.location == null && map != null)
            {
                EventLocation eventlocation;
                boolean flag1;
                if ((((EventExtrasFlags) (map)).flags & 0x10) != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    break label0;
                }
            }
            map = new StructuredLocation();
            eventlocation = new EventLocation();
            eventlocation.name = contentvalues.getAsString("eventLocation");
            map.locations = Collections.singletonList(eventlocation);
            event.structuredLocation = map;
            return;
        }
        event.structuredLocation = null;
    }

    private static boolean shouldCreateEvent(Entity entity)
    {
        entity = getWriteListedValues(entity.getSubValues());
        return entity.containsKey("iCalUid") || "1".equals(entity.get("shouldCreateEvent"));
    }

    private final void updateCalendarProviderWithEvent(Event event, Entity entity)
    {
        ContentValues contentvalues = new ContentValues();
        TimelyEventData timelyeventdata = new TimelyEventData();
        long l;
        if (entity != null)
        {
            l = entity.getEntityValues().getAsLong("calendar_id").longValue();
        } else
        {
            l = -1L;
        }
        try
        {
            entryToContentValues(event, contentvalues, timelyeventdata, null, l);
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            return;
        }
        try
        {
            ProviderHelper.asSyncAdapter(account).update(provider, android.provider.CalendarContract.Events.CONTENT_URI, contentvalues, "_id=?", new String[] {
                entity.getEntityValues().getAsString("_id")
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Event event)
        {
            return;
        }
    }

    public static String upgradeTimelyExtrasFlags(String s, String s1)
    {
        return String.valueOf((new EventExtrasFlags(EventExtrasFlags.builder().setSmartMailAvailable(Boolean.parseBoolean(s)).setImageAvailable(Boolean.parseBoolean(s1)).flags)).flags);
    }

    public final void applyItemToEntity(List list, Event event, Entity entity, boolean flag, SyncResult syncresult, Object obj)
        throws RemoteException, ParseException
    {
        Object obj1;
        Object obj5;
        int i;
        int j;
        long l1;
        obj1 = entity;
        do
        {
            SyncLog.start("Convert event to provider operations");
            LogUtils.v("EventHandler", "============= applyItemToEntity =============", new Object[0]);
            LogUtils.v("EventHandler", "event is %s", new Object[] {
                event
            });
            LogUtils.v("EventHandler", "entity is %s", new Object[] {
                obj1
            });
            entity = (CalendarSyncInfo)obj;
            if (event == null || obj1 == null)
            {
                break;
            }
            obj = ((Entity) (obj1)).getEntityValues().getAsString("original_sync_id");
            if (TextUtils.equals(event.recurringEventId, ((CharSequence) (obj))))
            {
                break;
            }
            LogUtils.v("EventHandler", "split update into delete + insert", new Object[0]);
            if (entity == null)
            {
                entity = getOrCreateSyncInfoForCalendar(((Entity) (obj1)).getEntityValues().getAsLong("calendar_id").longValue());
            }
            if (entity == null)
            {
                break;
            }
            applyItemToEntity(list, null, ((Entity) (obj1)), false, syncresult, entity);
            obj1 = null;
            flag = false;
            obj = entity;
        } while (true);
        SyncHooks asynchooks[];
        int i1;
        if (obj1 == null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj = new ContentValues();
        obj5 = new TimelyEventData();
        if (obj1 != null)
        {
            l1 = ((Entity) (obj1)).getEntityValues().getAsLong("calendar_id").longValue();
        } else
        if (entity != null)
        {
            l1 = ((CalendarSyncInfo) (entity)).calendarId;
        } else
        {
            l1 = -1L;
        }
        if (event == null)
        {
            j = 1;
        } else
        {
            j = entryToContentValues(event, ((ContentValues) (obj)), ((TimelyEventData) (obj5)), entity, l1);
        }
        if (flag)
        {
            ((ContentValues) (obj)).put("dirty", Integer.valueOf(0));
        }
        asynchooks = syncHooks;
        i1 = asynchooks.length;
        for (int k = 0; k < i1; k++)
        {
            asynchooks[k].onBeforeApplyEventToEntity(event, ((Entity) (obj1)), ((ContentValues) (obj)));
        }

        if (j != 1) goto _L2; else goto _L1
_L1:
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        event = ((Entity) (obj1)).getEntityValues().getAsLong("_id");
        CalendarSyncAdapterApiary.addAsSyncAdapterDeleteOperation(list, ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, event.longValue()), account, null, false);
        entity = ((Entity) (obj1)).getEntityValues().getAsString("_sync_id");
        SyncLog.start("DB: timelyData.delete");
        timelySync.removeTimelyEventData(entity, l1);
        SyncLog.stop("DB: timelyData.delete");
        event = ((Entity) (obj1)).getEntityValues().getAsBoolean("dirty");
        if (event != null && event.booleanValue() && !TextUtils.isEmpty(entity))
        {
            obj = SQLiteDatabaseUtils.makeWhere(new String[] {
                "calendar_id=?", "_sync_id=?", "lastSynced=?"
            });
            obj1 = ProviderHelper.asSyncAdapter(account);
            event = android.provider.CalendarContract.Events.CONTENT_URI;
            if (((ProviderHelper) (obj1)).account != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                event = ProviderHelper.toAsSyncAdapterUri(event, ((ProviderHelper) (obj1)).account);
            }
            list.add(ContentProviderOperation.newDelete(event).withSelection(((String) (obj)), new String[] {
                String.valueOf(l1), entity, "1"
            }).withYieldAllowed(false).build());
        }
        i = 3;
_L40:
        i;
        JVM INSTR tableswitch 1 3: default 568
    //                   1 2746
    //                   2 2765
    //                   3 2784;
           goto _L5 _L6 _L7 _L8
_L5:
        SyncLog.stop("Convert event to provider operations");
        return;
_L2:
        Object obj2;
        Object obj4;
        if (j != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        LogUtils.v("EventHandler", "Got eventEntry from server: %s", new Object[] {
            obj
        });
        Integer integer = ((ContentValues) (obj)).getAsInteger("sync_data9");
        obj2 = integer;
        if (integer == null)
        {
            obj2 = Integer.valueOf(0);
        }
        obj4 = ((ContentValues) (obj)).getAsString("_sync_id");
        if (i == 0) goto _L10; else goto _L9
_L9:
        Object obj3;
        Object obj7;
        if (((TimelyEventData) (obj5)).hasTimelyData())
        {
            SyncLog.start("DB: timelyData.update");
            timelySync.insertOrUpdateEventData(((String) (obj4)), l1, ((TimelyEventData) (obj5)));
            SyncLog.stop("DB: timelyData.update");
        }
        obj3 = SyncUtils.getEventExtrasFlagsValue(event, ((TimelyEventData) (obj5)));
        i = ((Integer) (obj2)).intValue();
        ((ContentValues) (obj)).put("sync_data9", Integer.valueOf(((EventExtrasFlags) (obj3)).flags | i));
        obj3 = ProviderHelper.asSyncAdapter(account);
        obj2 = android.provider.CalendarContract.EventsEntity.CONTENT_URI;
        if (((ProviderHelper) (obj3)).account != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            obj2 = ProviderHelper.toAsSyncAdapterUri(((Uri) (obj2)), ((ProviderHelper) (obj3)).account);
        }
        list.add(ContentProviderOperation.newAssertQuery(((Uri) (obj2))).withSelection(SQLiteDatabaseUtils.makeWhere(new String[] {
            "_sync_id=?", "calendar_id=?"
        }), new String[] {
            obj4, String.valueOf(((ContentValues) (obj)).getAsInteger("calendar_id"))
        }).withExpectedCount(0).build());
        j = list.size();
        CalendarSyncAdapterApiary.addAsSyncAdapterInsertOperation(list, android.provider.CalendarContract.Events.CONTENT_URI, account, ((ContentValues) (obj)), null, null, false);
        obj3 = null;
        i = 1;
        obj2 = Integer.valueOf(j);
_L17:
        obj = entity;
        if (entity == null)
        {
            obj = entity;
            if (event.reminders != null)
            {
                obj = entity;
                if (event.reminders.useDefault.booleanValue())
                {
                    obj = getOrCreateSyncInfoForCalendar(l1);
                }
            }
        }
        obj7 = new ArrayList();
        if (event.reminders == null) goto _L12; else goto _L11
_L11:
        if (!event.reminders.useDefault.booleanValue() || obj != null) goto _L14; else goto _L13
_L13:
        LogUtils.wtf("EventHandler", "No syncInfo present for event with default reminders", new Object[0]);
_L12:
        entity = Collections.emptyList();
_L25:
        entity = entity.iterator();
        while (entity.hasNext()) 
        {
            obj = (EventReminder)entity.next();
            obj4 = new ContentValues();
            obj5 = ((EventReminder) (obj)).method;
            String s;
            boolean flag1;
            int l;
            if ("popup".equals(obj5))
            {
                ((ContentValues) (obj4)).put("method", Integer.valueOf(1));
            } else
            if ("email".equals(obj5))
            {
                ((ContentValues) (obj4)).put("method", Integer.valueOf(2));
            } else
            if ("sms".equals(obj5))
            {
                ((ContentValues) (obj4)).put("method", Integer.valueOf(3));
            } else
            {
                ((ContentValues) (obj4)).put("method", Integer.valueOf(1));
                LogUtils.e("EventHandler", "Unknown reminder method: %s should not happen!", new Object[] {
                    obj5
                });
            }
            ((ContentValues) (obj4)).put("minutes", Integer.valueOf(((EventReminder) (obj)).minutes.intValue()));
            ((List) (obj7)).add(obj4);
        }
        break MISSING_BLOCK_LABEL_1794;
_L10:
        obj3 = ((Entity) (obj1)).getEntityValues().getAsLong("_id");
        obj7 = EventExtrasFlags.builder(((Entity) (obj1)).getEntityValues(), "sync_data9");
        if (((TimelyEventData) (obj5)).hasTimelyData())
        {
            obj7 = new EventExtrasFlags((new com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder(SyncUtils.getEventExtrasFlagsValue(event, ((TimelyEventData) (obj5))).flags)).flags);
            ((ContentValues) (obj)).put("sync_data9", Integer.valueOf(((Integer) (obj2)).intValue() | ((EventExtrasFlags) (obj7)).flags));
            if (((TimelyEventData) (obj5)).structuredLocation != null)
            {
                LogUtils.d("EventHandler", "Sync Id: %s, location: %s", new Object[] {
                    obj4, ((TimelyEventData) (obj5)).structuredLocation
                });
            }
            SyncLog.start("DB: timelyData.update");
            timelySync.insertOrUpdateEventData(((String) (obj4)), l1, ((TimelyEventData) (obj5)));
            SyncLog.stop("DB: timelyData.update");
        } else
        {
            EventExtrasFlags.builder();
            obj7.flags = ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj7)).flags & 0x433;
            if (event != null && event.endTimeUnspecified != null && event.endTimeUnspecified.booleanValue())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj7)).setEndTimeUnspecified(flag);
            ((ContentValues) (obj)).put("sync_data9", Integer.valueOf(((Integer) (obj2)).intValue() | (new EventExtrasFlags(((com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder) (obj7)).flags)).flags));
            SyncLog.start("DB: timelyData.delete");
            timelySync.removeTimelyEventData(((String) (obj4)), l1);
            SyncLog.stop("DB: timelyData.delete");
        }
        obj2 = Features.instance;
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj2).notify_guests();
        ((ContentValues) (obj)).putNull("sync_data6");
        obj2 = ((Entity) (obj1)).getEntityValues();
        if (!"1".equals(((ContentValues) (obj2)).getAsString("sync_data7"))) goto _L16; else goto _L15
_L15:
        LogUtils.v("EventHandler", "Performing full update for undeleted event id=%d.", new Object[] {
            ((ContentValues) (obj2)).getAsLong("_id")
        });
_L20:
        CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(list, android.provider.CalendarContract.Events.CONTENT_URI, account, ((ContentValues) (obj)), ((Long) (obj3)), null, true);
        i = 2;
        obj2 = null;
          goto _L17
_L16:
        obj4 = TIME_RELATED_FIELDS;
        if (obj4 == null || obj4.length == 0)
        {
            throw new IllegalArgumentException("No fields specified");
        }
        l = obj4.length;
        i = 0;
_L21:
        if (i >= l)
        {
            break MISSING_BLOCK_LABEL_1586;
        }
        obj5 = obj4[i];
        obj7 = ((ContentValues) (obj2)).getAsString(((String) (obj5)));
        s = ((ContentValues) (obj)).getAsString(((String) (obj5)));
        if (obj7 == s || obj7 != null && obj7.equals(s))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 || !((ContentValues) (obj)).containsKey(((String) (obj5)))) goto _L19; else goto _L18
_L18:
        i = 1;
_L22:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_1592;
        }
          goto _L20
_L19:
        i++;
          goto _L21
        i = 0;
          goto _L22
        obj2 = new ContentValues(((ContentValues) (obj)));
        obj4 = TIME_RELATED_FIELDS;
        flag1 = obj4.length;
        i = 0;
_L24:
        obj = obj2;
        if (i >= flag1) goto _L20; else goto _L23
_L23:
        ((ContentValues) (obj2)).remove(obj4[i]);
        i++;
          goto _L24
          goto _L20
_L14:
        if (!event.reminders.useDefault.booleanValue())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (event.start.date != null)
        {
            entity = ((CalendarSyncInfo) (obj)).defaultAllDayReminders;
        } else
        {
            entity = ((CalendarSyncInfo) (obj)).defaultReminders;
        }
          goto _L25
        if (event.reminders.overrides == null) goto _L12; else goto _L26
_L26:
        entity = event.reminders.overrides;
          goto _L25
        ((List) (obj7)).addAll(getRemindersFromExtendedProperties(event.extendedProperties));
        if (event.attendees == null) goto _L28; else goto _L27
_L27:
        entity = event.attendees.iterator();
_L31:
        if (!entity.hasNext()) goto _L28; else goto _L29
_L29:
        obj = (EventAttendee)entity.next();
        if (((EventAttendee) (obj)).id == null) goto _L31; else goto _L30
_L30:
        obj = ((EventAttendee) (obj)).self;
        flag = false;
        ArrayList arraylist;
        HashMap hashmap;
        if (obj != null)
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (!flag) goto _L31; else goto _L32
_L32:
        flag1 = true;
_L35:
        if (flag1 != 0)
        {
            entity = getCalendarOwnerAccount(l1);
        } else
        {
            entity = null;
        }
        if (event.attendees != null && !event.attendees.isEmpty()) goto _L34; else goto _L33
_L33:
        entity = Collections.emptyList();
_L38:
        obj = event.extendedProperties;
        arraylist = new ArrayList();
        if (obj != null)
        {
            addExtendedPropertiesAsContentValues(arraylist, ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).shared, "shared:");
            addExtendedPropertiesAsContentValues(arraylist, ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).private__, "private:");
        }
        hashmap = new HashMap();
        if (event.hangoutLink != null)
        {
            hashmap.put("hangoutLink", event.hangoutLink);
        }
        obj = event.conferenceData;
        if (obj != null && ((ConferenceData) (obj)).conferences != null && !((ConferenceData) (obj)).conferences.isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 != 0)
        {
            hashmap.put("includeHangout", "1");
        }
        if (event.endTimeUnspecified != null)
        {
            ArrayList arraylist1;
            Object obj6;
            Iterator iterator;
            ContentValues contentvalues;
            EventAttendee eventattendee;
            String s1;
            if (event.endTimeUnspecified.booleanValue())
            {
                obj = "1";
            } else
            {
                obj = "0";
            }
            hashmap.put("endTimeUnspecified", obj);
        }
        if (event.visibility != null)
        {
            if (event.visibility.equals("secret"))
            {
                event = "1";
            } else
            {
                event = "0";
            }
            hashmap.put("secretEvent", event);
        }
        addExtendedPropertiesAsContentValues(arraylist, hashmap, "");
        addSubValuesOperations(list, ((Long) (obj3)), ((Integer) (obj2)), android.provider.CalendarContract.Reminders.CONTENT_URI, "minutes", ((List) (obj7)), ((Entity) (obj1)));
        addSubValuesOperations(list, ((Long) (obj3)), ((Integer) (obj2)), android.provider.CalendarContract.Attendees.CONTENT_URI, "attendeeEmail", entity, ((Entity) (obj1)));
        addSubValuesOperations(list, ((Long) (obj3)), ((Integer) (obj2)), android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, "name", arraylist, ((Entity) (obj1)));
        continue; /* Loop/switch isn't completed */
_L28:
        flag1 = false;
          goto _L35
_L34:
        arraylist1 = new ArrayList();
        iterator = event.attendees.iterator();
_L36:
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_2719;
        }
        eventattendee = (EventAttendee)iterator.next();
        contentvalues = new ContentValues();
        obj6 = eventattendee.displayName;
        obj = obj6;
        if (obj6 == null)
        {
            obj = "";
        }
        contentvalues.put("attendeeName", ((String) (obj)));
        obj = eventattendee.email;
        if (obj != null)
        {
            contentvalues.put("attendeeEmail", ((String) (obj)));
        }
        obj6 = eventattendee.id;
        if (obj6 != null)
        {
            obj = eventattendee.self;
            flag = false;
            if (obj != null)
            {
                flag = ((Boolean) (obj)).booleanValue();
            }
            contentvalues.put("attendeeIdNamespace", "com.google");
            obj = String.valueOf("gprofile:");
            s1 = String.valueOf(obj6);
            if (s1.length() != 0)
            {
                obj = ((String) (obj)).concat(s1);
            } else
            {
                obj = new String(((String) (obj)));
            }
            contentvalues.put("attendeeIdentity", ((String) (obj)));
            if (flag)
            {
                obj = entity;
            } else
            {
                obj = String.valueOf(obj6);
                obj6 = String.valueOf("@profile.calendar.google.com");
                if (((String) (obj6)).length() != 0)
                {
                    obj = ((String) (obj)).concat(((String) (obj6)));
                } else
                {
                    obj = new String(((String) (obj)));
                }
            }
            contentvalues.put("attendeeEmail", ((String) (obj)));
        }
        s1 = eventattendee.responseStatus;
        obj6 = (Integer)ENTRY_TYPE_TO_PROVIDER_ATTENDEE.get(s1);
        obj = obj6;
        if (obj6 == null)
        {
            LogUtils.w("EventHandler", "Unknown attendee status %s", new Object[] {
                s1
            });
            obj = Integer.valueOf(0);
        }
        contentvalues.put("attendeeStatus", ((Integer) (obj)));
        obj = eventattendee.organizer;
        flag = false;
        if (obj != null)
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (flag)
        {
            flag1 = 2;
        } else
        {
            flag1 = true;
        }
        contentvalues.put("attendeeRelationship", Integer.valueOf(flag1));
        if (!RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
        {
            break MISSING_BLOCK_LABEL_2659;
        }
        obj = eventattendee.resource;
        flag = false;
        if (obj != null)
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_2659;
        }
        contentvalues.put("attendeeType", Integer.valueOf(3));
_L37:
        arraylist1.add(contentvalues);
          goto _L36
        obj = eventattendee.optional;
        flag = false;
        if (obj != null)
        {
            flag = ((Boolean) (obj)).booleanValue();
        }
        if (flag)
        {
            contentvalues.put("attendeeType", Integer.valueOf(2));
        } else
        {
            contentvalues.put("attendeeType", Integer.valueOf(1));
        }
          goto _L37
        entity = arraylist1;
          goto _L38
_L6:
        list = syncresult.stats;
        list.numInserts = ((SyncStats) (list)).numInserts + 1L;
        continue; /* Loop/switch isn't completed */
_L7:
        list = syncresult.stats;
        list.numUpdates = ((SyncStats) (list)).numUpdates + 1L;
        continue; /* Loop/switch isn't completed */
_L8:
        list = syncresult.stats;
        list.numDeletes = ((SyncStats) (list)).numDeletes + 1L;
        if (true) goto _L5; else goto _L4
_L4:
        i = 0;
        if (true) goto _L40; else goto _L39
_L39:
    }

    public final String getEntitySelection()
    {
        return "_sync_id IS NULL OR (_sync_id IS NOT NULL AND lastSynced = 0 AND (dirty != 0 OR deleted != 0))";
    }

    public final String itemToSourceId(Object obj)
    {
        return ((Event)obj).id;
    }

    public final EntityIterator newEntityIterator(String s, String as[], int i)
        throws RemoteException, ParseException
    {
        String s1;
        if (i <= 0)
        {
            s1 = null;
        } else
        {
            s1 = (new StringBuilder(21)).append("_id LIMIT ").append(i).toString();
        }
        return android.provider.CalendarContract.EventsEntity.newEntityIterator(ProviderHelper.asSyncAdapter(account).query(provider, android.provider.CalendarContract.EventsEntity.CONTENT_URI, null, s, as, s1), provider);
    }

    public final ArrayList sendEntityToServer(Entity entity, SyncResult syncresult)
        throws RemoteException, IOException, ParseException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        ArrayList arraylist;
        Object obj6;
        String s;
        int i;
        int j;
        int k;
        int l;
        int i1;
        boolean flag1;
        SyncLog.start("Send event to server");
        obj6 = entity.getEntityValues();
        calendarId = ((ContentValues) (obj6)).getAsString("cal_sync1");
        if (calendarId == null)
        {
            LogUtils.w("EventHandler", "Entity with no calendar id found: %d", new Object[] {
                ((ContentValues) (obj6)).getAsLong("_id")
            });
            return null;
        }
        if ("local android etag magic value".equals(((ContentValues) (obj6)).getAsString("sync_data4")))
        {
            LogUtils.v("EventHandler", "Entity with magic ETAG found: %s", new Object[] {
                entity
            });
            return null;
        }
        obj = ((ContentValues) (obj6)).getAsString("_sync_id");
        if (obj != null && ((String) (obj)).startsWith("SYNC_ERROR: "))
        {
            ((ContentValues) (obj6)).put("_sync_id", null);
            obj3 = null;
        } else
        {
            obj3 = obj;
        }
        s = calendarId;
        if (((ContentValues) (obj6)).getAsInteger("deleted").intValue() != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0 && (obj3 == null || shouldCreateEvent(entity)))
        {
            LogUtils.d("EventHandler", "Local event deleted. Do nothing", new Object[0]);
            return null;
        }
        obj1 = Features.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj1).notify_guests();
        obj1 = ((ContentValues) (obj6)).getAsInteger("sync_data6");
        if (obj1 != null)
        {
            flag1 = Integer.valueOf(com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED.ordinal()).equals(obj1);
        } else
        {
            flag1 = true;
        }
        l = TrafficStats.getThreadStatsTag();
        k = 0;
        setDtendFromDuration(((ContentValues) (obj6)), parseDuration(((ContentValues) (obj6)).getAsString("duration")));
        arraylist = new ArrayList();
        i1 = Gservices.getInt(contentResolver, "google_calendar_sync_max_attendees", 50);
        i = k;
        if (!((ContentValues) (obj6)).containsKey("mutators")) goto _L2; else goto _L1
_L1:
        i = k;
        obj2 = ((ContentValues) (obj6)).getAsString("mutators");
_L8:
        if (obj3 == null) goto _L4; else goto _L3
_L3:
        i = k;
        if (!shouldCreateEvent(entity)) goto _L5; else goto _L4
_L4:
        i = k;
        if (((ContentValues) (obj6)).containsKey("eventColor_index"))
        {
            break MISSING_BLOCK_LABEL_494;
        }
        i = k;
        LogUtils.v("EventHandler", "Missing EVENT_COLOR_KEY, fetching from provider", new Object[0]);
        i = k;
        obj = ProviderHelper.asClient().query(provider, ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, ((ContentValues) (obj6)).getAsLong("_id").longValue()), new String[] {
            "eventColor", "eventColor_index"
        }, null, null, null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_494;
        }
        if (((Cursor) (obj)).moveToFirst())
        {
            ((ContentValues) (obj6)).put("eventColor", Long.valueOf(((Cursor) (obj)).getLong(0)));
            ((ContentValues) (obj6)).put("eventColor_index", ((Cursor) (obj)).getString(1));
        }
        i = k;
        ((Cursor) (obj)).close();
        i = k;
        if (((ContentValues) (obj6)).containsKey("originalInstanceTime"))
        {
            break MISSING_BLOCK_LABEL_522;
        }
        i = k;
        if (!((ContentValues) (obj6)).containsKey("original_id"))
        {
            break MISSING_BLOCK_LABEL_566;
        }
        i = k;
        ((ContentValues) (obj6)).remove("rrule");
        i = k;
        ((ContentValues) (obj6)).remove("rdate");
        i = k;
        ((ContentValues) (obj6)).remove("exrule");
        i = k;
        ((ContentValues) (obj6)).remove("exdate");
        j = l | 1;
        i = j;
        TrafficStats.setThreadStatsTag(j);
        i = j;
        obj1 = convertEntityToEvent(entity, entity);
        i = j;
        if (((Event) (obj1)).iCalUID != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0) goto _L7; else goto _L6
_L6:
        i = j;
        obj3 = new com.google.api.services.calendar.Calendar.Events(client);
        i = j;
        obj = new com.google.api.services.calendar.Calendar.Events.CalendarImport(((com.google.api.services.calendar.Calendar.Events) (obj3)), s, ((Event) (obj1)));
        i = j;
        ((com.google.api.services.calendar.Calendar.Events) (obj3)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj)));
        i = j;
        obj.supportsAttachments = Boolean.valueOf(true);
        i = j;
        obj.supportsAllDayReminders = Boolean.valueOf(true);
        i = j;
        obj.proposeTimeChangeVersion = Integer.valueOf(1);
_L20:
        i = j;
        obj3 = (CalendarRequest)((GenericData) (obj)).set("userAgentPackage", obj2);
        i = j;
        obj = (Event)requestExecutor.execute("API: Insert Event", ((CalendarRequest) (obj)));
        i = j;
_L35:
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        applyItemToEntity(arraylist, ((Event) (obj)), entity, true, syncresult, null);
        SyncLog.stop("Send event to server");
        return arraylist;
_L2:
        obj2 = null;
          goto _L8
        entity;
        i = k;
        ((Cursor) (obj)).close();
        i = k;
        throw entity;
        entity;
_L160:
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        throw entity;
_L7:
        i = j;
        obj3 = new com.google.api.services.calendar.Calendar.Events(client);
        i = j;
        obj = new com.google.api.services.calendar.Calendar.Events.Insert(((com.google.api.services.calendar.Calendar.Events) (obj3)), s, ((Event) (obj1)));
        i = j;
        ((com.google.api.services.calendar.Calendar.Events) (obj3)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj)));
        i = j;
        obj.sendNotifications = Boolean.valueOf(flag1);
        i = j;
        obj.supportsAttachments = Boolean.valueOf(true);
        i = j;
        obj.supportsAllDayReminders = Boolean.valueOf(true);
        i = j;
        obj.supportsConferenceData = Boolean.valueOf(true);
        i = j;
        obj.proposeTimeChangeVersion = Integer.valueOf(1);
        i = j;
        if (((Event) (obj1)).conferenceData == null) goto _L10; else goto _L9
_L9:
        i = j;
        if (((Event) (obj1)).conferenceData.conferenceSolution == null) goto _L10; else goto _L11
_L11:
        i = 1;
_L163:
        if (i != 0) goto _L13; else goto _L12
_L12:
        i = j;
        if (((Event) (obj1)).conferenceData == null) goto _L15; else goto _L14
_L14:
        i = j;
        if (((Event) (obj1)).conferenceData.createRequest == null) goto _L15; else goto _L16
_L16:
        i = j;
        if (Platform.stringIsNullOrEmpty(((Event) (obj1)).conferenceData.createRequest.requestId)) goto _L15; else goto _L17
_L17:
        i = 1;
          goto _L18
_L162:
        if (i == 0) goto _L20; else goto _L19
_L19:
        i = j;
        obj.conferenceDataVersion = Integer.valueOf(1);
          goto _L20
_L23:
        i = j;
        analyticsLogger.logEventHttpException(((String) (obj)), ((HttpResponseException) (obj3)), ((String) (obj2)), entity);
        i = j;
        k = ((HttpResponseException) (obj3)).statusCode;
        if (k != 403) goto _L22; else goto _L21
_L21:
        i = j;
        entity = ((Event) (obj1)).recurringEventId;
        if (entity == null)
        {
            break MISSING_BLOCK_LABEL_1211;
        }
        i = j;
        LogUtils.w("EventHandler", "Refresh original event", new Object[0]);
        i = j;
        obj = requestExecutor;
        i = j;
        obj1 = new com.google.api.services.calendar.Calendar.Events(client);
        i = j;
        obj2 = new com.google.api.services.calendar.Calendar.Events.Get(((com.google.api.services.calendar.Calendar.Events) (obj1)), s, entity);
        i = j;
        ((com.google.api.services.calendar.Calendar.Events) (obj1)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj2)));
        i = j;
        applyItemToEntity(arraylist, (Event)((CalendarRequestExecutor) (obj)).execute("API: Get Event", ((CalendarRequest) (obj2))), fetchEntity("_sync_id=?", new String[] {
            entity
        }), true, syncresult, null);
        i = j;
        CalendarSyncAdapterApiary.addAsSyncAdapterDeleteOperation(arraylist, ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, ((ContentValues) (obj6)).getAsLong("_id").longValue()), account, null, false);
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return arraylist;
_L165:
        obj = "EventHandler::InsertEvent";
          goto _L23
_L22:
        if (k < 400 || k >= 500) goto _L25; else goto _L24
_L24:
        i = j;
        entity = ((GoogleJsonResponseException) (obj3)).details;
        if (entity == null) goto _L27; else goto _L26
_L26:
        i = j;
        entity = ((GoogleJsonError) (entity)).message;
_L29:
        i = j;
        obj = String.valueOf(entity);
        i = j;
        if (!"Invalid sequence value.".equals(obj))
        {
            break; /* Loop/switch isn't completed */
        }
        i = j;
        CalendarSyncAdapterApiary.addAsSyncAdapterDeleteOperation(arraylist, ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, ((ContentValues) (obj6)).getAsLong("_id").longValue()), account, null, false);
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return arraylist;
_L27:
        i = j;
        entity = Integer.valueOf(k);
        if (true) goto _L29; else goto _L28
_L28:
        i = j;
        syncresult = new ContentValues();
        i = j;
        syncresult.put("dirty", Integer.valueOf(0));
        i = j;
        entity = String.valueOf("SYNC_ERROR: ");
        i = j;
        obj = String.valueOf(obj);
        i = j;
        if (((String) (obj)).length() == 0) goto _L31; else goto _L30
_L30:
        i = j;
        entity = entity.concat(((String) (obj)));
_L32:
        i = j;
        syncresult.put("_sync_id", entity);
        i = j;
        CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(arraylist, android.provider.CalendarContract.Events.CONTENT_URI, account, syncresult, ((ContentValues) (obj6)).getAsLong("_id"), null, true);
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return arraylist;
_L31:
        i = j;
        entity = new String(entity);
        if (true) goto _L32; else goto _L25
_L25:
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return null;
_L5:
        if (j == 0) goto _L34; else goto _L33
_L33:
        i = l | 3;
        TrafficStats.setThreadStatsTag(i);
        obj1 = new com.google.api.services.calendar.Calendar.Events(client);
        obj = new com.google.api.services.calendar.Calendar.Events.Delete(((com.google.api.services.calendar.Calendar.Events) (obj1)), s, ((String) (obj3)));
        ((com.google.api.services.calendar.Calendar.Events) (obj1)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj)));
        obj.sendNotifications = Boolean.valueOf(flag1);
        obj1 = (com.google.api.services.calendar.Calendar.Events.Delete)((GenericData) (obj)).set("userAgentPackage", obj2);
        requestExecutor.execute("API: Delete Event", ((CalendarRequest) (obj)));
        obj = null;
          goto _L35
        obj;
        analyticsLogger.logEventHttpException("EventHandler::DeleteEvent", ((HttpResponseException) (obj)), ((String) (obj2)), entity);
        j = ((HttpResponseException) (obj)).statusCode;
        j;
        JVM INSTR lookupswitch 3: default 4854
    //                   403: 1704
    //                   404: 1721
    //                   414: 1726;
           goto _L36 _L37 _L38 _L39
_L37:
        obj = getFallbackAfterError(s, ((String) (obj3)), ((String) (obj2)), entity, i1);
          goto _L35
_L38:
        obj = null;
          goto _L35
_L39:
        obj = null;
          goto _L35
_L167:
        if (i != 0)
        {
            TrafficStats.incrementOperationCount(i, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return null;
_L34:
        j = l | 2;
        TrafficStats.setThreadStatsTag(j);
        obj = fetchEntity("_sync_id =? AND lastSynced =?", new String[] {
            obj, "1"
        });
        if (((ContentValues) (obj6)).containsKey("eventColor_index"))
        {
            break MISSING_BLOCK_LABEL_2020;
        }
        LogUtils.v("EventHandler", "Missing EVENT_COLOR_KEY, fetching from provider", new Object[0]);
        obj1 = ProviderHelper.asClient();
        ContentProviderClient contentproviderclient = provider;
        Uri uri = android.provider.CalendarContract.Events.CONTENT_URI;
        String s1 = entity.getEntityValues().getAsString("calendar_id");
        obj1 = ((ProviderHelper) (obj1)).query(contentproviderclient, uri, new String[] {
            "eventColor", "eventColor_index", "dirty"
        }, "_sync_id=? AND calendar_id=?", new String[] {
            obj3, s1
        }, null);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_2020;
        }
_L44:
        if (!((Cursor) (obj1)).moveToNext()) goto _L41; else goto _L40
_L40:
        Object obj4;
        long l2;
        l2 = ((Cursor) (obj1)).getLong(0);
        obj4 = ((Cursor) (obj1)).getString(1);
        if (((Cursor) (obj1)).getInt(2) != 1) goto _L43; else goto _L42
_L42:
        ((ContentValues) (obj6)).put("eventColor", Long.valueOf(l2));
        ((ContentValues) (obj6)).put("eventColor_index", ((String) (obj4)));
          goto _L44
        entity;
        ((Cursor) (obj1)).close();
        throw entity;
_L43:
        if (obj == null) goto _L44; else goto _L45
_L45:
        ContentValues contentvalues = ((Entity) (obj)).getEntityValues();
        contentvalues.put("eventColor", Long.valueOf(l2));
        contentvalues.put("eventColor_index", ((String) (obj4)));
          goto _L44
_L41:
        ((Cursor) (obj1)).close();
        if (obj != null) goto _L47; else goto _L46
_L46:
        obj1 = convertEntityToEvent(entity, entity);
        obj = obj1;
        if (!((ContentValues) (obj6)).containsKey("sync_data2"))
        {
            break MISSING_BLOCK_LABEL_2085;
        }
        obj = obj1;
        obj4 = ((ContentValues) (obj6)).getAsInteger("sync_data2");
        if (obj4 == null)
        {
            break MISSING_BLOCK_LABEL_2085;
        }
        obj = obj1;
        if (((Integer) (obj4)).intValue() == 0)
        {
            break MISSING_BLOCK_LABEL_2085;
        }
        obj = obj1;
        obj1.sequence = ((Integer) (obj4));
        obj = obj1;
        if (((ContentValues) (obj6)).getAsInteger("hasAttendeeData").intValue() != 0) goto _L49; else goto _L48
_L48:
        obj = obj1;
        obj1.attendeesOmitted = Boolean.valueOf(true);
_L49:
        obj = obj1;
        Object obj5 = new com.google.api.services.calendar.Calendar.Events(client);
        obj = obj1;
        obj4 = new com.google.api.services.calendar.Calendar.Events.Update(((com.google.api.services.calendar.Calendar.Events) (obj5)), s, ((String) (obj3)), ((Event) (obj1)));
        obj = obj1;
        ((com.google.api.services.calendar.Calendar.Events) (obj5)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj4)));
        obj = obj1;
        obj4.sendNotifications = Boolean.valueOf(flag1);
        obj = obj1;
        obj5 = (com.google.api.services.calendar.Calendar.Events.Update)((GenericData) (obj4)).set("maxAttendees", Integer.valueOf(i1));
        obj = obj1;
        if (!((ContentValues) (obj6)).containsKey("sync_data4")) goto _L51; else goto _L50
_L50:
        obj = obj1;
        obj5 = (com.google.api.services.calendar.Calendar.Events.Update)((GenericData) (obj4)).set("ifmatch", ((ContentValues) (obj6)).getAsString("sync_data4"));
_L51:
        obj = obj1;
        obj5 = (com.google.api.services.calendar.Calendar.Events.Update)((GenericData) (obj4)).set("userAgentPackage", obj2);
        obj = obj1;
        obj4.supportsAllDayReminders = Boolean.valueOf(true);
        obj = obj1;
        obj4.supportsAttachments = Boolean.valueOf(true);
        obj = obj1;
        obj4.supportsConferenceData = Boolean.valueOf(true);
        obj = obj1;
        obj4.proposeTimeChangeVersion = Integer.valueOf(1);
        obj = obj1;
        if (((Event) (obj1)).conferenceData == null) goto _L53; else goto _L52
_L52:
        obj = obj1;
        if (((Event) (obj1)).conferenceData.conferenceSolution == null) goto _L53; else goto _L54
_L54:
        i = 1;
_L64:
        if (i != 0) goto _L56; else goto _L55
_L55:
        obj = obj1;
        if (((Event) (obj1)).conferenceData == null) goto _L58; else goto _L57
_L57:
        obj = obj1;
        if (((Event) (obj1)).conferenceData.createRequest == null) goto _L58; else goto _L59
_L59:
        obj = obj1;
        if (Platform.stringIsNullOrEmpty(((Event) (obj1)).conferenceData.createRequest.requestId)) goto _L58; else goto _L60
_L60:
        i = 1;
          goto _L61
_L65:
        if (i == 0) goto _L63; else goto _L62
_L62:
        obj = obj1;
        obj4.conferenceDataVersion = Integer.valueOf(1);
_L63:
        obj = obj1;
        obj1 = (Event)requestExecutor.execute("API: Update Event", ((CalendarRequest) (obj4)));
        obj = obj1;
        i = j;
          goto _L35
_L53:
        i = 0;
          goto _L64
_L58:
        i = 0;
          goto _L61
_L168:
        i = 0;
          goto _L65
_L47:
        ContentValues contentvalues1;
        Object obj7;
        contentvalues1 = ((Entity) (obj)).getEntityValues();
        setDtendFromDuration(contentvalues1, parseDuration(contentvalues1.getAsString("duration")));
        obj1 = entity.getEntityValues();
        obj4 = ((Entity) (obj)).getEntityValues();
        obj5 = new ContentValues(((ContentValues) (obj1)));
        obj7 = ((ContentValues) (obj1)).keySet().iterator();
_L70:
        if (!((Iterator) (obj7)).hasNext()) goto _L67; else goto _L66
_L66:
        Object obj8;
        Object obj9;
        Object obj10;
        obj8 = (String)((Iterator) (obj7)).next();
        obj9 = ((ContentValues) (obj1)).get(((String) (obj8)));
        obj10 = ((ContentValues) (obj4)).get(((String) (obj8)));
        if (!((String) (obj8)).equals("eventColor_index") || !TextUtils.isEmpty((String)obj9) || !TextUtils.isEmpty((String)obj10)) goto _L69; else goto _L68
_L68:
        ((ContentValues) (obj5)).remove(((String) (obj8)));
          goto _L70
        obj;
        obj1 = null;
_L126:
        analyticsLogger.logEventHttpException("EventHandler::UpdateEvent", ((HttpResponseException) (obj)), ((String) (obj2)), entity);
        i = ((HttpResponseException) (obj)).statusCode;
        i;
        JVM INSTR lookupswitch 4: default 4886
    //                   403: 4338
    //                   404: 4359
    //                   412: 4377
    //                   414: 4368;
           goto _L71 _L72 _L73 _L74 _L75
_L71:
        if (i < 400 || i >= 500) goto _L77; else goto _L76
_L76:
        entity = new ContentValues();
        entity.put("dirty", Integer.valueOf(0));
        CalendarSyncAdapterApiary.addAsSyncAdapterUpdateOperation(arraylist, android.provider.CalendarContract.Events.CONTENT_URI, account, entity, ((ContentValues) (obj6)).getAsLong("_id"), null, true);
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return arraylist;
_L69:
        if (obj9 == null && obj10 == null) goto _L79; else goto _L78
_L78:
        if (obj9 == null) goto _L70; else goto _L80
_L80:
        if (!obj9.equals(obj10)) goto _L70; else goto _L79
_L79:
        ((ContentValues) (obj5)).remove(((String) (obj8)));
          goto _L70
_L67:
        int j1;
        int k1;
        int l1;
        if (TextUtils.isEmpty(((ContentValues) (obj4)).getAsString("rrule")) && !TextUtils.isEmpty(((ContentValues) (obj1)).getAsString("rrule")))
        {
            if (!((ContentValues) (obj5)).containsKey("dtstart"))
            {
                ((ContentValues) (obj5)).put("dtstart", ((ContentValues) (obj4)).getAsLong("dtstart"));
            }
            if (!((ContentValues) (obj5)).containsKey("dtend"))
            {
                ((ContentValues) (obj5)).put("dtend", ((ContentValues) (obj4)).getAsLong("dtend"));
            }
            if (!((ContentValues) (obj5)).containsKey("eventTimezone"))
            {
                ((ContentValues) (obj5)).put("eventTimezone", ((ContentValues) (obj4)).getAsString("eventTimezone"));
            }
            if (!((ContentValues) (obj5)).containsKey("allDay"))
            {
                ((ContentValues) (obj5)).put("allDay", ((ContentValues) (obj4)).getAsInteger("allDay"));
            }
        }
        j1 = ((ContentValues) (obj1)).getAsInteger("allDay").intValue();
        k1 = ((ContentValues) (obj4)).getAsInteger("allDay").intValue();
        if (((ContentValues) (obj5)).containsKey("dtstart") || ((ContentValues) (obj5)).containsKey("dtend"))
        {
            ((ContentValues) (obj5)).put("allDay", Integer.valueOf(j1));
        }
        if (((ContentValues) (obj5)).containsKey("allDay"))
        {
            ((ContentValues) (obj5)).put("dtstart", ((ContentValues) (obj1)).getAsLong("dtstart"));
            ((ContentValues) (obj5)).put("dtend", ((ContentValues) (obj1)).getAsLong("dtend"));
        }
        obj1 = new Entity(((ContentValues) (obj5)));
        obj4 = (ArrayList)entity.getSubValues();
        l1 = ((ArrayList) (obj4)).size();
        i = 0;
_L82:
        if (i >= l1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj5 = ((ArrayList) (obj4)).get(i);
        k = i + 1;
        obj5 = (android.content.Entity.NamedContentValues)obj5;
        i = k;
        if (!((android.content.Entity.NamedContentValues) (obj5)).uri.equals(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI))
        {
            continue; /* Loop/switch isn't completed */
        }
        ((Entity) (obj1)).addSubValue(android.provider.CalendarContract.ExtendedProperties.CONTENT_URI, ((android.content.Entity.NamedContentValues) (obj5)).values);
        i = k;
        if (true) goto _L82; else goto _L81
_L81:
        obj4 = convertEntityToEvent(((Entity) (obj1)), entity);
        if (j1 == k1) goto _L84; else goto _L83
_L83:
        if (j1 != 0) goto _L86; else goto _L85
_L85:
        if (((Event) (obj4)).start != null)
        {
            ((Event) (obj4)).start.date = Data.NULL_DATE_TIME;
        }
        if (((Event) (obj4)).end != null)
        {
            ((Event) (obj4)).end.date = Data.NULL_DATE_TIME;
        }
_L84:
        Object obj11;
        obj5 = entity.getEntityValues();
        obj7 = entity.getSubValues();
        obj8 = ((Entity) (obj)).getSubValues();
        setStructuredLocationForEvent(((Event) (obj4)), ((ContentValues) (obj5)), getWriteListedValues(((ArrayList) (obj7))), true);
        obj10 = new Event();
        obj9 = new Event();
        addRemindersToEntry(((ArrayList) (obj7)), ((Event) (obj10)));
        addRemindersToEntry(((ArrayList) (obj8)), ((Event) (obj9)));
        obj1 = ((Event) (obj10)).reminders;
        obj11 = ((Event) (obj9)).reminders;
        obj = getReminderSet(((com.google.api.services.calendar.model.Event.Reminders) (obj1)));
        obj11 = getReminderSet(((com.google.api.services.calendar.model.Event.Reminders) (obj11)));
        if (obj != null) goto _L88; else goto _L87
_L87:
        boolean flag;
        if (obj11 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L113:
        if (flag) goto _L90; else goto _L89
_L89:
        obj = obj1;
        if (obj1 != null) goto _L92; else goto _L91
_L91:
        obj = new com.google.api.services.calendar.model.Event.Reminders();
        obj.useDefault = Boolean.valueOf(false);
        obj.overrides = new ArrayList();
_L92:
        obj4.reminders = ((com.google.api.services.calendar.model.Event.Reminders) (obj));
_L90:
        addAttendeesToEntry(((ContentValues) (obj5)), ((ArrayList) (obj7)), ((Event) (obj10)), true, null);
        addAttendeesToEntry(((ContentValues) (obj5)), ((ArrayList) (obj8)), ((Event) (obj9)), false, timelySync);
        obj5 = ((Event) (obj10)).attendees;
        obj11 = ((Event) (obj9)).attendees;
        if (obj5 != null) goto _L94; else goto _L93
_L93:
        obj = null;
          goto _L95
_L115:
        if (flag) goto _L97; else goto _L96
_L96:
        obj = obj5;
        if (obj11 == null)
        {
            break MISSING_BLOCK_LABEL_3366;
        }
        obj = obj5;
        if (obj5 != null)
        {
            break MISSING_BLOCK_LABEL_3366;
        }
        obj = new ArrayList();
        obj4.attendees = ((List) (obj));
_L97:
        addExtendedPropertiesToEntry(((ArrayList) (obj7)), ((Event) (obj10)));
        addExtendedPropertiesToEntry(((ArrayList) (obj8)), ((Event) (obj9)));
        obj1 = ((Event) (obj10)).extendedProperties;
        obj5 = ((Event) (obj9)).extendedProperties;
        if (obj1 != null) goto _L99; else goto _L98
_L98:
        if (obj5 == null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
_L176:
        if (i != 0) goto _L101; else goto _L100
_L100:
        obj = obj1;
        if (obj5 == null) goto _L103; else goto _L102
_L102:
        obj = obj1;
        if (obj1 != null) goto _L105; else goto _L104
_L104:
        obj = new com.google.api.services.calendar.model.Event.ExtendedProperties();
_L105:
        obj.private__ = addDeletedProperties(((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj5)).private__, ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).private__);
        obj.shared = addDeletedProperties(((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj5)).shared, ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj)).shared);
_L103:
        obj4.extendedProperties = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj));
_L101:
        obj = getWriteListedValues(((ArrayList) (obj7)));
        obj1 = getWriteListedValues(((ArrayList) (obj8)));
        if (obj != null) goto _L107; else goto _L106
_L106:
        if (obj1 == null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
_L182:
        if (i != 0) goto _L109; else goto _L108
_L108:
        addEventExtrasToEntry(((HashMap) (obj)), ((Event) (obj4)));
_L109:
        obj = obj4;
        if (!((Event) (obj4)).isEmpty()) goto _L111; else goto _L110
_L110:
        obj = obj4;
        LogUtils.d("EventHandler", "No diffs found for event %s", new Object[] {
            obj3
        });
        obj = obj4;
        obj5 = ProviderHelper.asSyncAdapter(account);
        obj = obj4;
        obj1 = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, ((ContentValues) (obj6)).getAsLong("_id").longValue());
        obj = obj4;
        if (((ProviderHelper) (obj5)).account != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L112
_L173:
        obj = obj4;
        arraylist.add(ContentProviderOperation.newUpdate(((Uri) (obj1))).withValue("dirty", Integer.valueOf(0)).build());
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return arraylist;
_L86:
        if (((Event) (obj4)).start != null)
        {
            ((Event) (obj4)).start.dateTime = Data.NULL_DATE_TIME;
        }
        if (((Event) (obj4)).end != null)
        {
            ((Event) (obj4)).end.dateTime = Data.NULL_DATE_TIME;
        }
          goto _L84
_L88:
        flag = obj.equals(obj11);
          goto _L113
_L94:
        obj = new TreeSet(new _cls1());
        ((Set) (obj)).addAll(((java.util.Collection) (obj5)));
          goto _L95
_L170:
        obj1 = new TreeSet(new _cls1());
        ((Set) (obj1)).addAll(((java.util.Collection) (obj11)));
          goto _L114
_L172:
        flag = obj.equals(obj1);
          goto _L115
_L99:
        if (obj5 == null) goto _L117; else goto _L116
_L116:
        obj = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj1)).private__;
        obj9 = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj5)).private__;
        if (obj != null) goto _L119; else goto _L118
_L118:
        if (obj9 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L124:
        if (!flag) goto _L117; else goto _L120
_L120:
        obj = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj1)).shared;
        obj9 = ((com.google.api.services.calendar.model.Event.ExtendedProperties) (obj5)).shared;
        if (obj != null) goto _L122; else goto _L121
_L121:
        if (obj9 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L123
_L119:
        flag = obj.equals(obj9);
          goto _L124
_L122:
        flag = obj.equals(obj9);
          goto _L123
_L180:
        flag = obj.equals(obj1);
          goto _L125
_L178:
        i = 0;
        break MISSING_BLOCK_LABEL_3505;
_L174:
        obj = obj4;
        try
        {
            obj1 = ProviderHelper.toAsSyncAdapterUri(((Uri) (obj1)), ((ProviderHelper) (obj5)).account);
            break; /* Loop/switch isn't completed */
        }
        catch (GoogleJsonResponseException googlejsonresponseexception)
        {
            obj1 = obj;
            obj = googlejsonresponseexception;
        }
        finally
        {
            i = j;
        }
          goto _L126
_L111:
        obj = obj4;
        flag = hasOnlyUnsyncedKeys(((Event) (obj4)));
        if (!flag) goto _L128; else goto _L127
_L127:
        obj = obj4;
        obj1 = contentvalues1.getAsString("description");
        if (obj1 != null) goto _L130; else goto _L129
_L129:
        obj1 = " ";
_L147:
        obj = obj4;
        obj4.description = ((String) (obj1));
_L128:
        obj = obj4;
        if (((ContentValues) (obj6)).getAsInteger("hasAttendeeData").intValue() != 0) goto _L132; else goto _L131
_L131:
        obj = obj4;
        obj4.attendeesOmitted = Boolean.valueOf(true);
_L132:
        obj = obj4;
        obj5 = new com.google.api.services.calendar.Calendar.Events(client);
        obj = obj4;
        obj1 = new com.google.api.services.calendar.Calendar.Events.Patch(((com.google.api.services.calendar.Calendar.Events) (obj5)), s, ((String) (obj3)), ((Event) (obj4)));
        obj = obj4;
        ((com.google.api.services.calendar.Calendar.Events) (obj5)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj1)));
        if (flag1 && !flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = obj4;
        obj1.sendNotifications = Boolean.valueOf(flag);
        obj = obj4;
        obj5 = (com.google.api.services.calendar.Calendar.Events.Patch)((GenericData) (obj1)).set("maxAttendees", Integer.valueOf(i1));
        obj = obj4;
        obj5 = (com.google.api.services.calendar.Calendar.Events.Patch)((GenericData) (obj1)).set("userAgentPackage", obj2);
        obj = obj4;
        obj1.supportsAllDayReminders = Boolean.valueOf(true);
        obj = obj4;
        obj1.supportsAttachments = Boolean.valueOf(true);
        obj = obj4;
        obj1.supportsConferenceData = Boolean.valueOf(true);
        obj = obj4;
        obj1.proposeTimeChangeVersion = Integer.valueOf(1);
        obj = obj4;
        if (((Event) (obj4)).conferenceData == null) goto _L134; else goto _L133
_L133:
        obj = obj4;
        if (((Event) (obj4)).conferenceData.conferenceSolution == null) goto _L134; else goto _L135
_L135:
        i = 1;
_L148:
        if (i != 0) goto _L137; else goto _L136
_L136:
        obj = obj4;
        if (((Event) (obj4)).conferenceData == null) goto _L139; else goto _L138
_L138:
        obj = obj4;
        if (((Event) (obj4)).conferenceData.createRequest == null) goto _L139; else goto _L140
_L140:
        obj = obj4;
        if (Platform.stringIsNullOrEmpty(((Event) (obj4)).conferenceData.createRequest.requestId)) goto _L139; else goto _L141
_L141:
        i = 1;
          goto _L142
_L149:
        if (i == 0) goto _L144; else goto _L143
_L143:
        obj = obj4;
        obj1.conferenceDataVersion = Integer.valueOf(1);
_L144:
        obj = obj4;
        obj1 = (Event)requestExecutor.execute("API: Patch Event", ((CalendarRequest) (obj1)));
        obj = obj1;
        i = j;
          goto _L35
_L130:
        obj = obj4;
        if (!((String) (obj1)).endsWith(" ")) goto _L146; else goto _L145
_L145:
        obj = obj4;
        obj1 = ((String) (obj1)).substring(0, ((String) (obj1)).length() - 1);
          goto _L147
_L146:
        obj = obj4;
        obj1 = String.valueOf(obj1).concat(" ");
          goto _L147
_L134:
        i = 0;
          goto _L148
_L139:
        i = 0;
          goto _L142
_L183:
        i = 0;
          goto _L149
_L72:
        obj = getFallbackAfterError(s, ((String) (obj3)), ((String) (obj2)), entity, i1);
        i = j;
          goto _L35
_L73:
        obj = null;
        i = j;
          goto _L35
_L75:
        obj = null;
        i = j;
          goto _L35
_L74:
        obj4 = requestExecutor;
        obj5 = new com.google.api.services.calendar.Calendar.Events(client);
        obj6 = new com.google.api.services.calendar.Calendar.Events.Get(((com.google.api.services.calendar.Calendar.Events) (obj5)), s, ((String) (obj3)));
        ((com.google.api.services.calendar.Calendar.Events) (obj5)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj6)));
        obj6.maxAttendees = Integer.valueOf(i1);
        obj4 = (Event)((CalendarRequestExecutor) (obj4)).execute("API: Get Event", ((CalendarRequest) (obj6)));
        obj1.sequence = ((Event) (obj4)).sequence;
        updateCalendarProviderWithEvent(((Event) (obj4)), entity);
        updateCalendarProviderWithEvent(((Event) (obj1)), entity);
        obj5 = new com.google.api.services.calendar.Calendar.Events(client);
        obj4 = new com.google.api.services.calendar.Calendar.Events.Patch(((com.google.api.services.calendar.Calendar.Events) (obj5)), s, ((String) (obj3)), ((Event) (obj1)));
        ((com.google.api.services.calendar.Calendar.Events) (obj5)).this$0.initialize(((com.google.api.client.googleapis.services.AbstractGoogleClientRequest) (obj4)));
        if (!flag1) goto _L151; else goto _L150
_L150:
        if (hasOnlyUnsyncedKeys(((Event) (obj1)))) goto _L151; else goto _L152
_L152:
        flag = true;
_L158:
        obj4.sendNotifications = Boolean.valueOf(flag);
        obj5 = (com.google.api.services.calendar.Calendar.Events.Patch)((GenericData) (obj4)).set("maxAttendees", Integer.valueOf(i1));
        obj5 = (com.google.api.services.calendar.Calendar.Events.Patch)((GenericData) (obj4)).set("userAgentPackage", obj2);
        obj4.supportsAllDayReminders = Boolean.valueOf(true);
        obj4.supportsAttachments = Boolean.valueOf(true);
        obj4.supportsConferenceData = Boolean.valueOf(true);
        obj4.proposeTimeChangeVersion = Integer.valueOf(1);
        if (((Event) (obj1)).conferenceData != null && ((Event) (obj1)).conferenceData.conferenceSolution != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L154; else goto _L153
_L153:
        if (((Event) (obj1)).conferenceData != null && ((Event) (obj1)).conferenceData.createRequest != null && !Platform.stringIsNullOrEmpty(((Event) (obj1)).conferenceData.createRequest.requestId))
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L155
_L159:
        if (i == 0) goto _L157; else goto _L156
_L156:
        obj4.conferenceDataVersion = Integer.valueOf(1);
_L157:
        obj1 = (Event)requestExecutor.execute("API: Patch Event", ((CalendarRequest) (obj4)));
        obj = obj1;
        i = j;
          goto _L35
_L151:
        flag = false;
          goto _L158
_L184:
        i = 0;
          goto _L159
        obj1;
        analyticsLogger.logEventHttpException("EventHandler::UpdateEventRetry", ((HttpResponseException) (obj)), ((String) (obj2)), entity);
        obj = getFallbackAfterError(s, ((String) (obj3)), ((String) (obj2)), entity, i1);
        i = j;
          goto _L35
_L77:
        if (j != 0)
        {
            TrafficStats.incrementOperationCount(j, 1);
        }
        TrafficStats.setThreadStatsTag(l);
        return null;
        entity;
          goto _L160
_L18:
        if (i == 0) goto _L161; else goto _L13
_L13:
        i = 1;
          goto _L162
_L10:
        i = 0;
          goto _L163
_L15:
        i = 0;
          goto _L18
_L161:
        i = 0;
          goto _L162
        obj3;
        if (k == 0) goto _L165; else goto _L164
_L164:
        obj = "EventHandler::ImportEvent";
          goto _L23
_L36:
        if (j < 400 || j >= 500) goto _L167; else goto _L166
_L166:
        obj = null;
          goto _L35
_L61:
        if (i == 0) goto _L168; else goto _L56
_L56:
        i = 1;
          goto _L65
_L95:
        if (obj11 != null) goto _L170; else goto _L169
_L169:
        obj1 = null;
_L114:
        if (obj != null) goto _L172; else goto _L171
_L171:
        if (obj1 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L115
_L112:
        if (i != 0) goto _L174; else goto _L173
_L123:
        if (!flag) goto _L117; else goto _L175
_L175:
        i = 1;
          goto _L176
_L117:
        i = 0;
          goto _L176
_L107:
        if (obj1 == null) goto _L178; else goto _L177
_L177:
        if (obj != null) goto _L180; else goto _L179
_L179:
        if (obj1 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L125:
        if (!flag) goto _L178; else goto _L181
_L181:
        i = 1;
          goto _L182
_L142:
        if (i == 0) goto _L183; else goto _L137
_L137:
        i = 1;
          goto _L149
_L155:
        if (i == 0) goto _L184; else goto _L154
_L154:
        i = 1;
          goto _L159
    }

    static 
    {
        Object obj = new HashMap();
        ((HashMap) (obj)).put("needsAction", Integer.valueOf(3));
        ((HashMap) (obj)).put("accepted", Integer.valueOf(1));
        ((HashMap) (obj)).put("declined", Integer.valueOf(2));
        ((HashMap) (obj)).put("tentative", Integer.valueOf(4));
        ENTRY_TYPE_TO_PROVIDER_ATTENDEE = ((HashMap) (obj));
        PROVIDER_TYPE_TO_ENTRY_ATTENDEE = new SparseArray();
        java.util.Map.Entry entry;
        Integer integer;
        for (obj = ((HashMap) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext(); PROVIDER_TYPE_TO_ENTRY_ATTENDEE.put(integer.intValue(), (String)entry.getKey()))
        {
            entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            integer = (Integer)entry.getValue();
            if (PROVIDER_TYPE_TO_ENTRY_ATTENDEE.get(integer.intValue()) != null)
            {
                String s = String.valueOf(integer);
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 30)).append("value ").append(s).append(" was already encountered").toString());
            }
        }

    }

    private class _cls2
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (EventReminder)obj;
            obj1 = (EventReminder)obj1;
            int j = EventHandler.compareObjects(((EventReminder) (obj)).method, ((EventReminder) (obj1)).method);
            int i = j;
            if (j == 0)
            {
                i = EventHandler.compareObjects(((EventReminder) (obj)).minutes, ((EventReminder) (obj1)).minutes);
            }
            return i;
        }

        _cls2()
        {
        }
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
label0:
            {
                obj = (EventAttendee)obj;
                obj1 = (EventAttendee)obj1;
                int j = EventHandler.compareObjects(((EventAttendee) (obj)).email, ((EventAttendee) (obj1)).email);
                int i = j;
                if (j == 0)
                {
                    i = EventHandler.compareObjects(((EventAttendee) (obj)).responseStatus, ((EventAttendee) (obj1)).responseStatus);
                }
                j = i;
                if (i == 0)
                {
                    j = EventHandler.compareObjects(((EventAttendee) (obj)).displayName, ((EventAttendee) (obj1)).displayName);
                }
                i = j;
                if (j == 0)
                {
                    i = EventHandler.compareObjects(((EventAttendee) (obj)).optional, ((EventAttendee) (obj1)).optional);
                }
                j = i;
                if (i == 0)
                {
                    j = i;
                    if (RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
                    {
                        j = EventHandler.compareObjects(((EventAttendee) (obj)).resource, ((EventAttendee) (obj1)).resource);
                    }
                }
                i = j;
                if (j == 0)
                {
                    FeatureConfig featureconfig = Features.instance;
                    if (featureconfig == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)featureconfig).response_comments();
                    featureconfig = Features.instance;
                    if (featureconfig == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)featureconfig).propose_new_time();
                    i = EventHandler.compareObjects(((EventAttendee) (obj)).comment, ((EventAttendee) (obj1)).comment);
                }
                j = i;
                if (i == 0)
                {
                    FeatureConfig featureconfig1 = Features.instance;
                    if (featureconfig1 == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)featureconfig1).propose_new_time();
                    if (!EventHandler.isEqual(((EventAttendee) (obj)).timeChangeProposal, ((EventAttendee) (obj1)).timeChangeProposal))
                    {
                        break label0;
                    }
                    j = 0;
                }
                return j;
            }
            return 1;
        }

        _cls1()
        {
        }
    }

}
