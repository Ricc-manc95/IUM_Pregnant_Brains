// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListClient, CalendarListApiStoreImpl, CalendarListFilterOptions, CalendarDescriptor, 
//            CalendarListEntryModifications

public final class CalendarListClientFutureImpl
    implements CalendarListClient
{

    public final CalendarListApiStoreImpl api;

    public CalendarListClientFutureImpl(CalendarListApiStoreImpl calendarlistapistoreimpl)
    {
        if (calendarlistapistoreimpl == null)
        {
            throw new NullPointerException();
        } else
        {
            api = (CalendarListApiStoreImpl)calendarlistapistoreimpl;
            return;
        }
    }

    public final ListenableFuture count(CalendarListFilterOptions calendarlistfilteroptions)
    {
        class .Lambda._cls3
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final CalendarListFilterOptions arg$2;

            public final Object call()
            {
                Object obj = arg$1;
                CalendarListFilterOptions calendarlistfilteroptions1 = arg$2;
                CalendarListApiStoreImpl calendarlistapistoreimpl = ((CalendarListClientFutureImpl) (obj)).api;
                Object obj1 = new ArrayList();
                if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
                {
                    ((ArrayList) (obj1)).add("account_type".concat("!=?"));
                }
                ArrayList arraylist;
                boolean flag;
                int i;
                if (calendarlistfilteroptions1 == null || calendarlistfilteroptions1.isEmptyFilter())
                {
                    if (((ArrayList) (obj1)).isEmpty())
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
                    }
                } else
                {
                    if (calendarlistfilteroptions1.googleCalendarsOnly != null && calendarlistfilteroptions1.googleCalendarsOnly.booleanValue())
                    {
                        ((ArrayList) (obj1)).add("account_type".concat("=?"));
                    }
                    if (calendarlistfilteroptions1.primary != null)
                    {
                        if (calendarlistfilteroptions1.primary.booleanValue())
                        {
                            obj = "1";
                        } else
                        {
                            obj = "0";
                        }
                        ((ArrayList) (obj1)).add("(COALESCE(isPrimary, ownerAccount=account_name))".concat("=").concat(((String) (obj))));
                    }
                    if (calendarlistfilteroptions1.visible != null)
                    {
                        ((ArrayList) (obj1)).add("visible".concat("=?"));
                    }
                    if (calendarlistfilteroptions1.writable != null)
                    {
                        if (calendarlistfilteroptions1.writable.booleanValue())
                        {
                            obj = ">=?";
                        } else
                        {
                            obj = "<?";
                        }
                        ((ArrayList) (obj1)).add("calendar_access_level".concat(((String) (obj))));
                    }
                    obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
                }
                arraylist = new ArrayList();
                if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
                {
                    arraylist.add("com.google");
                }
                if (calendarlistfilteroptions1 == null || calendarlistfilteroptions1.isEmptyFilter())
                {
                    if (arraylist.isEmpty())
                    {
                        obj = null;
                    } else
                    {
                        obj = (String[])arraylist.toArray(new String[arraylist.size()]);
                    }
                } else
                {
                    if (calendarlistfilteroptions1.googleCalendarsOnly != null && calendarlistfilteroptions1.googleCalendarsOnly.booleanValue())
                    {
                        arraylist.add("com.google");
                    }
                    if (calendarlistfilteroptions1.visible != null)
                    {
                        if (calendarlistfilteroptions1.visible.booleanValue())
                        {
                            obj = "1";
                        } else
                        {
                            obj = "0";
                        }
                        arraylist.add(obj);
                    }
                    if (calendarlistfilteroptions1.writable != null)
                    {
                        arraylist.add(Integer.toString(CalendarAccessLevel.WRITER.level));
                    }
                    if (arraylist.isEmpty())
                    {
                        obj = null;
                    } else
                    {
                        obj = (String[])arraylist.toArray(new String[arraylist.size()]);
                    }
                }
                i = Cursors.count(CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Calendars.CONTENT_URI, LoadDetailsConstants.CALENDARS_PROJECTION, ((String) (obj1)), ((String []) (obj)), null), "CalendarList count");
                if (i >= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new VerifyException();
                } else
                {
                    return Integer.valueOf(i);
                }
            }

            .Lambda._cls3(CalendarListFilterOptions calendarlistfilteroptions)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = calendarlistfilteroptions;
            }
        }

        calendarlistfilteroptions = new .Lambda._cls3(calendarlistfilteroptions);
        ApiOperation apioperation = ApiOperation.CALENDAR_COUNT;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, calendarlistfilteroptions, apioperation));
    }

    public final void initialize(Context context)
    {
    }

    public final ListenableFuture list(CalendarListFilterOptions calendarlistfilteroptions)
    {
        class .Lambda._cls2
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final CalendarListFilterOptions arg$2;

            public final Object call()
            {
                Object obj = arg$1;
                CalendarListFilterOptions calendarlistfilteroptions1 = arg$2;
                CalendarListApiStoreImpl calendarlistapistoreimpl = ((CalendarListClientFutureImpl) (obj)).api;
                Object obj1 = new ArrayList();
                if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
                {
                    ((ArrayList) (obj1)).add("account_type".concat("!=?"));
                }
                String as[];
                ArrayList arraylist;
                if (calendarlistfilteroptions1 == null || calendarlistfilteroptions1.isEmptyFilter())
                {
                    if (((ArrayList) (obj1)).isEmpty())
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
                    }
                } else
                {
                    if (calendarlistfilteroptions1.googleCalendarsOnly != null && calendarlistfilteroptions1.googleCalendarsOnly.booleanValue())
                    {
                        ((ArrayList) (obj1)).add("account_type".concat("=?"));
                    }
                    if (calendarlistfilteroptions1.primary != null)
                    {
                        if (calendarlistfilteroptions1.primary.booleanValue())
                        {
                            as = "1";
                        } else
                        {
                            as = "0";
                        }
                        ((ArrayList) (obj1)).add("(COALESCE(isPrimary, ownerAccount=account_name))".concat("=").concat(as));
                    }
                    if (calendarlistfilteroptions1.visible != null)
                    {
                        ((ArrayList) (obj1)).add("visible".concat("=?"));
                    }
                    if (calendarlistfilteroptions1.writable != null)
                    {
                        if (calendarlistfilteroptions1.writable.booleanValue())
                        {
                            as = ">=?";
                        } else
                        {
                            as = "<?";
                        }
                        ((ArrayList) (obj1)).add("calendar_access_level".concat(as));
                    }
                    obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
                }
                arraylist = new ArrayList();
                if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
                {
                    arraylist.add("com.google");
                }
                if (calendarlistfilteroptions1 == null || calendarlistfilteroptions1.isEmptyFilter())
                {
                    if (arraylist.isEmpty())
                    {
                        as = null;
                    } else
                    {
                        as = (String[])arraylist.toArray(new String[arraylist.size()]);
                    }
                } else
                {
                    if (calendarlistfilteroptions1.googleCalendarsOnly != null && calendarlistfilteroptions1.googleCalendarsOnly.booleanValue())
                    {
                        arraylist.add("com.google");
                    }
                    if (calendarlistfilteroptions1.visible != null)
                    {
                        if (calendarlistfilteroptions1.visible.booleanValue())
                        {
                            as = "1";
                        } else
                        {
                            as = "0";
                        }
                        arraylist.add(as);
                    }
                    if (calendarlistfilteroptions1.writable != null)
                    {
                        arraylist.add(Integer.toString(CalendarAccessLevel.WRITER.level));
                    }
                    if (arraylist.isEmpty())
                    {
                        as = null;
                    } else
                    {
                        as = (String[])arraylist.toArray(new String[arraylist.size()]);
                    }
                }
                as = CalendarApi.getApiContentResolver().query(android.provider.CalendarContract.Calendars.CONTENT_URI, LoadDetailsConstants.CALENDARS_PROJECTION, ((String) (obj1)), as, null);
                if (as == null)
                {
                    throw new IOException("No cursor");
                }
                obj1 = new HashMap(as.getColumnCount());
                do
                {
                    if (!as.moveToNext())
                    {
                        break;
                    }
                    long l = as.getLong(0);
                    Optional optional = AccountUtil.newAccount(as.getString(14), as.getString(15));
                    if (optional.isPresent())
                    {
                        ((Map) (obj1)).put(String.valueOf(l), (Account)optional.get());
                    }
                } while (true);
                as.moveToPosition(-1);
                as = Cursors.apply(as, new CalendarListApiStoreImpl..Lambda._cls1(TimelyStore.acquire(CalendarApi.getApiAppContext()).loadAllNotifications(((Map) (obj1)), null), TimelyStore.acquire(CalendarApi.getApiAppContext()).loadAllConferenceTypes()), "CalendarList");
                if (as == null)
                {
                    throw new NullPointerException();
                }
                as = new com.google.common.base.Optional._cls1(as);
                Object aobj[] = (Object[])Array.newInstance(com/google/android/calendar/api/calendarlist/CalendarListEntry, 0);
                boolean flag;
                if (as instanceof Collection)
                {
                    as = (Collection)as;
                } else
                {
                    java.util.Iterator iterator = as.iterator();
                    as = new ArrayList();
                    Iterators.addAll(as, iterator);
                }
                as = (CalendarListEntry[])as.toArray(aobj);
                if (as != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                } else
                {
                    return (CalendarListEntry[])as;
                }
            }

            .Lambda._cls2(CalendarListFilterOptions calendarlistfilteroptions)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = calendarlistfilteroptions;
            }
        }

        calendarlistfilteroptions = new .Lambda._cls2(calendarlistfilteroptions);
        ApiOperation apioperation = ApiOperation.CALENDAR_LIST;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, calendarlistfilteroptions, apioperation));
    }

    public final ListenableFuture read(CalendarDescriptor calendardescriptor)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final CalendarDescriptor arg$2;

            public final Object call()
            {
                boolean flag = false;
                Object obj = arg$1;
                obj = CalendarListApiStoreImpl.read(arg$2);
                if (obj != null)
                {
                    flag = true;
                }
                if (!flag)
                {
                    throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
                } else
                {
                    return (CalendarListEntry)obj;
                }
            }

            .Lambda._cls0(CalendarDescriptor calendardescriptor)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = calendardescriptor;
            }
        }

        calendardescriptor = new .Lambda._cls0(calendardescriptor);
        ApiOperation apioperation = ApiOperation.CALENDAR_READ;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, calendardescriptor, apioperation));
    }

    public final ListenableFuture subscribe(Account account, String s)
    {
        class .Lambda._cls4
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final Account arg$2;
            private final String arg$3;

            public final Object call()
            {
                Object obj = arg$1;
                obj = arg$2;
                String s1 = arg$3;
                TimelyUtils.subscribeCalendar(CalendarApi.getApiAppContext(), ((Account) (obj)), s1, null);
                return null;
            }

            .Lambda._cls4(Account account, String s)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = account;
                arg$3 = s;
            }
        }

        account = new .Lambda._cls4(account, s);
        s = ApiOperation.CALENDAR_SUBSCRIBE;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, account, s));
    }

    public final ListenableFuture unsubscribe(Account account, String s)
    {
        class .Lambda._cls5
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final Account arg$2;
            private final String arg$3;

            public final Object call()
            {
                Object obj = arg$1;
                obj = arg$2;
                String s1 = arg$3;
                TimelyUtils.unsubscribeCalendar(CalendarApi.getApiAppContext(), ((Account) (obj)), s1);
                return null;
            }

            .Lambda._cls5(Account account, String s)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = account;
                arg$3 = s;
            }
        }

        account = new .Lambda._cls5(account, s);
        s = ApiOperation.CALENDAR_UNSUBSCRIBE;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, account, s));
    }

    public final ListenableFuture update(CalendarListEntryModifications calendarlistentrymodifications)
    {
        class .Lambda._cls1
            implements Callable
        {

            private final CalendarListClientFutureImpl arg$1;
            private final CalendarListEntryModifications arg$2;

            public final Object call()
            {
                CalendarListEntryModifications calendarlistentrymodifications1;
                boolean flag;
                flag = true;
                Object obj = arg$1;
                calendarlistentrymodifications1 = arg$2;
                if (calendarlistentrymodifications1.getOriginal() == null)
                {
                    throw new NullPointerException();
                }
                obj = ((CalendarListClientFutureImpl) (obj)).api;
                if (!calendarlistentrymodifications1.isModified()) goto _L2; else goto _L1
_L1:
                ContentValues contentvalues;
                Object obj1;
                int i;
                contentvalues = new ContentValues();
                if (calendarlistentrymodifications1.isVisibleModified())
                {
                    if (calendarlistentrymodifications1.isVisible())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    contentvalues.put("visible", Integer.valueOf(i));
                }
                if (calendarlistentrymodifications1.isSyncEnabledModified())
                {
                    if (calendarlistentrymodifications1.isSyncEnabled())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    contentvalues.put("sync_events", Integer.valueOf(i));
                }
                if (calendarlistentrymodifications1.isDisplayNameModified())
                {
                    contentvalues.put("calendar_displayName", calendarlistentrymodifications1.getDisplayName());
                }
                if (!calendarlistentrymodifications1.isColorModified()) goto _L4; else goto _L3
_L3:
                obj1 = calendarlistentrymodifications1.getColor();
                if (!(obj1 instanceof NamedCalendarColor)) goto _L6; else goto _L5
_L5:
                contentvalues.put("calendar_color_index", CalendarApi.getColorFactory().getCalendarColorKey((NamedCalendarColor)obj1));
                contentvalues.put("calendar_color", Integer.valueOf(((CalendarColor) (obj1)).getValue()));
_L4:
                if (contentvalues.size() > 0)
                {
                    obj1 = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, calendarlistentrymodifications1.getDescriptor().calendarKey.getLocalId());
                    CalendarApi.getApiContentResolver().update(((android.net.Uri) (obj1)), contentvalues, null, null);
                }
_L2:
                if (calendarlistentrymodifications1.areDefaultNotificationsModified(1))
                {
                    NotificationsTimelyStoreUtils.storeDefaultNotifications(calendarlistentrymodifications1.getDefaultNotifications(1), calendarlistentrymodifications1.getDescriptor(), false);
                }
                if (calendarlistentrymodifications1.areDefaultNotificationsModified(2))
                {
                    NotificationsTimelyStoreUtils.storeDefaultNotifications(calendarlistentrymodifications1.getDefaultNotifications(2), calendarlistentrymodifications1.getDescriptor(), true);
                }
                if (CalendarListApiStoreImpl.read(calendarlistentrymodifications1.getDescriptor()) != null)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                return Integer.valueOf(i);
_L6:
                if (obj1 instanceof CustomCalendarColor)
                {
                    contentvalues.put("calendar_color_index", "");
                    contentvalues.put("calendar_color", Integer.valueOf(((CustomCalendarColor)obj1).getOriginalValue()));
                }
                if (true) goto _L4; else goto _L7
_L7:
            }

            .Lambda._cls1(CalendarListEntryModifications calendarlistentrymodifications)
            {
                arg$1 = CalendarListClientFutureImpl.this;
                arg$2 = calendarlistentrymodifications;
            }
        }

        calendarlistentrymodifications = new .Lambda._cls1(calendarlistentrymodifications);
        ApiOperation apioperation = ApiOperation.CALENDAR_UPDATE;
        return (FluentFuture)CalendarExecutor.API.submit(MetricUtils.withMetrics(com.google.common.base.Predicates.ObjectPredicate.ALWAYS_TRUE, calendarlistentrymodifications, apioperation));
    }
}
