// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.event.scope.ScopeSelectionDialog;
import com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils;
import com.google.android.calendar.newapi.screen.EventSaveModificator;
import com.google.android.calendar.utils.flow.Flow;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class EventSaveFlow extends Flow
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback, DrivePermissionChecker.Listener
{
    public static final class Factory
    {

        public Factory()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onSaveCompleted(boolean flag, Event event1, int i);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/event/EventSaveFlow);
    public EventModifications event;
    public EventClient eventClient;
    private EventSaveModificator eventSaveModificator;
    public List saveScopes;
    public int scope;
    public boolean showConfirmationToast;

    public EventSaveFlow()
    {
        eventClient = CalendarApi.Events;
        eventSaveModificator = new EventSaveModificator();
        scope = 0;
    }

    private final void maybeShowNotificationDialogAndSave()
    {
        if (super.mFragmentManager.isDestroyed())
        {
            return;
        }
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final EventSaveFlow arg$1;

            public final ListenableFuture apply(Object obj)
            {
                EventSaveFlow eventsaveflow;
                boolean flag;
                boolean flag1;
                boolean flag2;
                flag2 = true;
                flag1 = true;
                eventsaveflow = arg$1;
                obj = (Event)obj;
                Object obj1 = Features.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj1).notify_guests();
                obj1 = eventsaveflow.event;
                if (!AccountUtil.isGoogleAccount(((EventModifications) (obj1)).getCalendar().account) || !GuestNotificationDialogUtils.isStronglyModified(((EventModifications) (obj1))) && !GuestNotificationDialogUtils.hasModifiedGuests(((EventModifications) (obj1))))
                {
                    break MISSING_BLOCK_LABEL_129;
                }
                if (AttendeeUtils.hasGuests(((Event) (obj1))) || GuestNotificationDialogUtils.hasRemovedGuests(((EventModifications) (obj1))))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break MISSING_BLOCK_LABEL_129;
                }
                flag = true;
_L1:
                if (!flag)
                {
                    obj = com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED;
                    if (obj == null)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
                    }
                }
                break MISSING_BLOCK_LABEL_144;
                flag = false;
                  goto _L1
                Object obj2;
                Context context;
                if (obj != null)
                {
                    eventsaveflow.event.getGooglePrivateDataModification().setGuestNotification(((Event) (obj)).getGooglePrivateData().getGuestNotification());
                }
                obj2 = eventsaveflow.event;
                context = eventsaveflow.getContext();
                if (!((EventModifications) (obj2)).isNewEvent()) goto _L3; else goto _L2
_L2:
                int i = 0x7f1302d6;
_L4:
                obj2 = GuestNotificationDialogUtils.maybeExtendDialogText(((Event) (obj2)), context, context.getString(i));
                Object obj3;
                com.google.common.base.Predicate predicate;
                if (eventsaveflow.event.isNewEvent())
                {
                    obj = "create";
                } else
                {
                    obj = "update";
                }
                return GuestNotificationDialogUtils.showNotificationDialogAsync(eventsaveflow.event, eventsaveflow.getContext(), ((String) (obj2)), ((String) (obj)));
_L3:
                if (!GuestNotificationDialogUtils.hasModifiedGuests(((EventModifications) (obj2))))
                {
                    break MISSING_BLOCK_LABEL_809;
                }
                if (!GuestNotificationDialogUtils.hasNewGuests(((EventModifications) (obj2))) || !GuestNotificationDialogUtils.hasRemovedGuests(((EventModifications) (obj2))))
                {
                    break MISSING_BLOCK_LABEL_578;
                }
                if (!GuestNotificationDialogUtils.isStronglyModified(((EventModifications) (obj2))))
                {
                    break MISSING_BLOCK_LABEL_565;
                }
                obj = ((EventModifications) (obj2)).getAttendees();
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj3 = com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils..Lambda._cls1.$instance;
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((com.google.common.base.Predicate) (obj3)));
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj3 = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
                obj = ((EventModifications) (obj2)).getAttendeeModifications().getOriginal();
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                predicate = com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils..Lambda._cls1.$instance;
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (predicate == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), predicate);
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
                obj3 = new com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils..Lambda._cls0(((EventModifications) (obj2)), ((List) (obj3)));
                if (Iterators.indexOf(((Iterable) (obj)).iterator(), ((com.google.common.base.Predicate) (obj3))) == -1)
                {
                    break MISSING_BLOCK_LABEL_565;
                }
                i = ((flag1) ? 1 : 0);
_L5:
                if (i != 0)
                {
                    i = 0x7f1302db;
                } else
                {
                    i = 0x7f1302d8;
                }
                  goto _L4
                i = 0;
                  goto _L5
                if (GuestNotificationDialogUtils.hasNewGuests(((EventModifications) (obj2))))
                {
                    obj = ((EventModifications) (obj2)).getAttendeeModifications().getOriginal();
                    if (obj instanceof FluentIterable)
                    {
                        obj = (FluentIterable)obj;
                    } else
                    {
                        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                    }
                    obj3 = com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils..Lambda._cls1.$instance;
                    obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (obj3 == null)
                    {
                        throw new NullPointerException();
                    }
                    obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((com.google.common.base.Predicate) (obj3)));
                    if (obj instanceof FluentIterable)
                    {
                        obj = (FluentIterable)obj;
                    } else
                    {
                        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                    }
                    obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
                    if (GuestNotificationDialogUtils.isStronglyModified(((EventModifications) (obj2))) && ((List) (obj)).size() > 0)
                    {
                        i = ((flag2) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        i = 0x7f1302da;
                    } else
                    {
                        i = 0x7f1302d9;
                    }
                } else
                {
                    if (!GuestNotificationDialogUtils.hasRemovedGuests(((EventModifications) (obj2))))
                    {
                        break MISSING_BLOCK_LABEL_809;
                    }
                    if (GuestNotificationDialogUtils.isStronglyModified(((EventModifications) (obj2))) && AttendeeUtils.hasGuests(((Event) (obj2))))
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        i = 0x7f1302e0;
                    } else
                    {
                        i = 0x7f1302df;
                    }
                }
                  goto _L4
                i = 0x7f1302e1;
                  goto _L4
            }

            .Lambda._cls0()
            {
                arg$1 = EventSaveFlow.this;
            }
        }

        ListenableFuture listenablefuture = AbstractTransformFuture.create(GuestNotificationDialogUtils.maybeReadEvent(event), new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
        class .Lambda._cls1
            implements Consumer
        {

            private final EventSaveFlow arg$1;

            public final void accept(Object obj)
            {
                Object obj1;
                final Object guestNotification;
                EventModifications eventmodifications;
                Object obj2;
                boolean flag4;
                flag4 = true;
                obj1 = arg$1;
                guestNotification = (com.google.android.calendar.api.event.GooglePrivateData.GuestNotification)obj;
                LatencyLoggerHolder.get().markAt(4);
                ApiUtils.setDefaultCalendar(((EventSaveFlow) (obj1)).event.getCalendarListEntry());
                obj2 = ((Fragment) (obj1)).getContext();
                eventmodifications = ((EventSaveFlow) (obj1)).event;
                if (!eventmodifications.isAllDayEvent() && eventmodifications.getTimeZoneId() == null)
                {
                    eventmodifications.setTimeZoneId(TimeZone.getTimeZone(Utils.getTimeZoneId(((Context) (obj2)))).getID());
                }
                if (eventmodifications.getVisibility() == 3)
                {
                    eventmodifications.setAvailability(1);
                }
                if (!eventmodifications.getAttendees().isEmpty() && eventmodifications.getAttendeeModifications().isModified() && CalendarType.calculateType(eventmodifications.getOrganizer().email) == 6)
                {
                    obj = eventmodifications.getAttendeeModifications();
                    AttendeeDescriptor attendeedescriptor = eventmodifications.getOrganizer();
                    String s = attendeedescriptor.email;
                    com.google.android.calendar.api.event.attendee.Response.Builder builder = new com.google.android.calendar.api.event.attendee.Response.Builder();
                    builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
                    ((AttendeeModifications) (obj)).addAttendee(new Attendee(attendeedescriptor, s, 1, 1, new Response(builder)));
                }
                if (eventmodifications.isDescriptionModified() && !TextUtils.isEmpty(eventmodifications.getDescription()))
                {
                    eventmodifications.setDescription(eventmodifications.getDescription().trim());
                }
                if (!eventmodifications.getAttendeeModifications().isModified() && !eventmodifications.getLocationModification().isModified()) goto _L2; else goto _L1
_L1:
                obj = CalendarApi.EventPermissionsFactory.create(eventmodifications);
                if (((EventPermissions) (obj)).getStructuredLocationPermissions().canAddLocations() && ((EventPermissions) (obj)).getStructuredLocationPermissions().canRemoveLocation()) goto _L3; else goto _L2
_L2:
                obj = Features.instance;
                StructuredLocationModifications structuredlocationmodifications;
                Object obj3;
                boolean flag;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                if (((FeatureConfig)obj).ooo())
                {
                    boolean flag1;
                    if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 && eventmodifications.isAllDayEvent())
                    {
                        obj = TimeZone.getTimeZone("UTC");
                        TimeZone timezone = Utils.getTimeZone(((Context) (obj2)));
                        obj2 = CalendarUtils.createTimeInNewTimeZoneRetainingFields(eventmodifications.getStartMillis(), ((TimeZone) (obj)), timezone);
                        obj = CalendarUtils.createTimeInNewTimeZoneRetainingFields(eventmodifications.getEndMillis(), ((TimeZone) (obj)), timezone);
                        eventmodifications.setToTimedWithTimes(((Calendar) (obj2)).getTimeInMillis(), ((Calendar) (obj)).getTimeInMillis());
                    }
                }
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                  goto _L4
_L3:
                structuredlocationmodifications = eventmodifications.getLocationModification();
                obj = structuredlocationmodifications.getEventLocations().iterator();
                if (((Iterator) (obj)).hasNext())
                {
                    obj = ((Iterator) (obj)).next();
                } else
                {
                    obj = null;
                }
                obj3 = (EventLocation)obj;
                if (obj3 == null)
                {
                    obj = RoomUtils.addRoomsToLocation(RoomUtils.removeRoomsFromLocation(null, RoomUtils.getOriginalRooms(eventmodifications)), RoomUtils.getRooms(eventmodifications));
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        obj3 = new com.google.android.calendar.api.event.location.EventLocation.Builder();
                        if (obj == null)
                        {
                            throw new NullPointerException();
                        }
                        obj3.name = (String)obj;
                        structuredlocationmodifications.addEventLocation(new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj3))));
                    }
                } else
                {
                    structuredlocationmodifications.removeEventLocation(((EventLocation) (obj3)));
                    obj = new com.google.android.calendar.api.event.location.EventLocation.Builder(((EventLocation) (obj3)));
                    obj3 = RoomUtils.addRoomsToLocation(RoomUtils.removeRoomsFromLocation(((EventLocation) (obj3)).name, RoomUtils.getOriginalRooms(eventmodifications)), RoomUtils.getRooms(eventmodifications));
                    if (obj3 == null)
                    {
                        throw new NullPointerException();
                    }
                    obj.name = (String)obj3;
                    obj = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj)));
                    if (LegacyUtils.isLegacyLocationOrEmpty(((EventLocation) (obj))) && TextUtils.isEmpty(((EventLocation) (obj)).name))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        structuredlocationmodifications.addEventLocation(((EventLocation) (obj)));
                    }
                }
                continue; /* Loop/switch isn't completed */
_L4:
                if (!((FeatureConfig)obj).ooo()) goto _L6; else goto _L5
_L5:
                boolean flag2;
                if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (flag2) goto _L7; else goto _L6
_L6:
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                break; /* Loop/switch isn't completed */
_L7:
                obj = eventmodifications.getParticipantStatus().getOutOfOffice();
                if (!((OutOfOffice) (obj)).isAutoDeclineEnabled() && ((OutOfOffice) (obj)).getCalendarDeclineMessage() != null)
                {
                    eventmodifications.setParticipantStatus(new AutoValue_UserStatus(((OutOfOffice) (obj)).toBuilder().setCalendarDeclineMessage(null).build()));
                }
                if (true) goto _L6; else goto _L8
_L8:
                if (((FeatureConfig)obj).ooo())
                {
                    boolean flag3;
                    if (eventmodifications != null && eventmodifications.getParticipantStatus() != null && eventmodifications.getParticipantStatus().getOutOfOffice() != null)
                    {
                        flag3 = flag4;
                    } else
                    {
                        flag3 = false;
                    }
                    if (flag3)
                    {
                        eventmodifications.getNotificationModifications().setNotifications(Collections.emptyList());
                    }
                }
                if (((EventSaveFlow) (obj1)).event.isNewEvent())
                {
                    obj = ((EventSaveFlow) (obj1)).eventClient.create(((EventSaveFlow) (obj1)).event, ((com.google.android.calendar.api.event.GooglePrivateData.GuestNotification) (guestNotification)));
                } else
                {
                    obj = ((EventSaveFlow) (obj1)).eventClient.update(((EventSaveFlow) (obj1)).event, ((EventSaveFlow) (obj1)).scope, ((com.google.android.calendar.api.event.GooglePrivateData.GuestNotification) (guestNotification)));
                }
                obj1 = ((_cls1) (obj1)). new _cls1();
                guestNotification = CalendarExecutor.MAIN;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), ((java.util.concurrent.Executor) (guestNotification)));
                return;
                if (true) goto _L2; else goto _L9
_L9:
            }

            .Lambda._cls1()
            {
                arg$1 = EventSaveFlow.this;
            }

            private class _cls1
                implements FutureCallback
            {

                private final EventSaveFlow this$0;
                private final com.google.android.calendar.api.event.GooglePrivateData.GuestNotification val$guestNotification;

                public final void onFailure(Throwable throwable)
                {
                    Object obj;
                    EventSaveFlow eventsaveflow;
                    Object obj1;
                    Object obj2;
                    boolean flag1;
                    obj = null;
                    flag1 = false;
                    eventsaveflow = EventSaveFlow.this;
                    obj1 = guestNotification;
                    obj2 = eventsaveflow.getContext();
                    if (obj2 == null) goto _L2; else goto _L1
_L1:
                    if (throwable == null) goto _L4; else goto _L3
_L3:
                    LogUtils.e(EventSaveFlow.TAG, throwable, "Unable to save", new Object[0]);
                    Toast.makeText(((Context) (obj2)), 0x7f1301ab, 0).show();
                    class .Lambda._cls2
                        implements Consumer
                    {

                        private final EventSaveFlow arg$1;

                        public final void accept(Object obj3)
                        {
                            EventSaveFlow eventsaveflow1 = arg$1;
                            ((Listener)obj3).onSaveCompleted(false, null, eventsaveflow1.scope);
                        }

                        .Lambda._cls2()
                        {
                            arg$1 = EventSaveFlow.this;
                        }
                    }

                    obj2 = eventsaveflow. new .Lambda._cls2();
                    obj1 = ((Fragment) (eventsaveflow)).mTarget;
                    if (obj1 == null) goto _L6; else goto _L5
_L5:
                    if (obj1 == null) goto _L8; else goto _L7
_L7:
                    android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (obj1)).mFragmentManager;
                    if (obj1 == null) goto _L10; else goto _L9
_L9:
                    boolean flag;
                    if (((Fragment) (obj1)).mHost != null && ((Fragment) (obj1)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L11; else goto _L10
_L10:
                    flag = false;
_L13:
                    if (!flag) goto _L6; else goto _L12
_L12:
                    flag = true;
_L15:
                    if (flag)
                    {
                        ((Consumer) (obj2)).accept(obj1);
                    }
                    flag = flag1;
                    if (eventsaveflow != null)
                    {
                        obj1 = ((Fragment) (eventsaveflow)).mFragmentManager;
                        flag = flag1;
                        if (eventsaveflow != null)
                        {
                            if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (!flag)
                            {
                                flag = flag1;
                            } else
                            {
                                if (((Fragment) (eventsaveflow)).mHost == null)
                                {
                                    throwable = ((Throwable) (obj));
                                } else
                                {
                                    throwable = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                                }
                                flag = flag1;
                                if (throwable != null)
                                {
                                    flag = flag1;
                                    if (!throwable.isDestroyed())
                                    {
                                        flag = flag1;
                                        if (!throwable.isFinishing())
                                        {
                                            flag = flag1;
                                            if (obj1 != null)
                                            {
                                                flag = flag1;
                                                if (!((FragmentManager) (obj1)).isDestroyed())
                                                {
                                                    flag = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (flag)
                    {
                        ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
                    }
_L2:
                    return;
_L11:
label0:
                    {
                        if (((Fragment) (obj1)).mHost == null)
                        {
                            throwable = null;
                        } else
                        {
                            throwable = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
                        }
                        if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
                        {
                            break label0;
                        }
                        flag = false;
                    }
                      goto _L13
                    if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L8; else goto _L14
_L14:
                    flag = true;
                      goto _L13
_L8:
                    flag = false;
                      goto _L13
_L6:
                    flag = false;
                      goto _L15
_L4:
                    int i;
label1:
                    {
                        boolean flag2;
                        if (true)
                        {
                            if (false)
                            {
                                ((Context) (obj2)).getContentResolver().notifyChange(null, null);
                            }
                            if (!eventsaveflow.showConfirmationToast)
                            {
                                break label1;
                            }
                            obj = eventsaveflow.getContext();
                            flag2 = eventsaveflow.event.isNewEvent();
                            throwable = Features.instance;
                            if (throwable == null)
                            {
                                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                            }
                        } else
                        {
                            throw new NullPointerException();
                        }
                        ((FeatureConfig)throwable).notify_guests();
                        class .Lambda._cls3
                            implements Consumer
                        {

                            private final EventSaveFlow arg$1;
                            private final Event arg$2;

                            public final void accept(Object obj3)
                            {
                                EventSaveFlow eventsaveflow1 = arg$1;
                                Event event1 = arg$2;
                                ((Listener)obj3).onSaveCompleted(true, event1, eventsaveflow1.scope);
                            }

                        .Lambda._cls3(Event event1)
                        {
                            arg$1 = EventSaveFlow.this;
                            arg$2 = event1;
                        }
                        }

                        if (obj1 != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
                        {
                            if (flag2)
                            {
                                i = 0x7f130143;
                            } else
                            {
                                i = 0x7f130426;
                            }
                        } else
                        {
                            if (false && AttendeeUtils.hasGuests(null))
                            {
                                i = 1;
                            } else
                            {
                                i = 0;
                            }
                            if (flag2)
                            {
                                if (i != 0)
                                {
                                    i = 0x7f130144;
                                } else
                                {
                                    i = 0x7f130143;
                                }
                            } else
                            if (i != 0)
                            {
                                i = 0x7f130427;
                            } else
                            {
                                i = 0x7f130426;
                            }
                        }
                        Toast.makeText(((Context) (obj)), i, 0).show();
                    }
                    obj2 = eventsaveflow. new .Lambda._cls3(null);
                    obj = ((Fragment) (eventsaveflow)).mTarget;
                    if (obj == null) goto _L17; else goto _L16
_L16:
                    if (obj == null) goto _L19; else goto _L18
_L18:
                    obj1 = ((Fragment) (obj)).mFragmentManager;
                    if (obj == null) goto _L21; else goto _L20
_L20:
                    if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0) goto _L22; else goto _L21
_L21:
                    i = 0;
_L27:
                    if (i == 0) goto _L17; else goto _L23
_L23:
                    i = 1;
_L29:
                    if (i != 0)
                    {
                        ((Consumer) (obj2)).accept(obj);
                    }
                    if (eventsaveflow == null)
                    {
                        break MISSING_BLOCK_LABEL_848;
                    }
                    obj = ((Fragment) (eventsaveflow)).mFragmentManager;
                    if (eventsaveflow == null) goto _L25; else goto _L24
_L24:
                    if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0) goto _L26; else goto _L25
_L25:
                    i = 0;
_L30:
                    if (i != 0)
                    {
                        ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
                        return;
                    }
                      goto _L2
_L22:
label2:
                    {
                        if (((Fragment) (obj)).mHost == null)
                        {
                            throwable = null;
                        } else
                        {
                            throwable = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                        }
                        if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
                        {
                            break label2;
                        }
                        i = 0;
                    }
                      goto _L27
                    if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed()) goto _L19; else goto _L28
_L28:
                    i = 1;
                      goto _L27
_L19:
                    i = 0;
                      goto _L27
_L17:
                    i = 0;
                      goto _L29
_L26:
                    if (((Fragment) (eventsaveflow)).mHost == null)
                    {
                        throwable = null;
                    } else
                    {
                        throwable = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                    }
                    if (throwable == null || throwable.isDestroyed() || throwable.isFinishing())
                    {
                        i = 0;
                    } else
                    {
                        if (obj == null || ((FragmentManager) (obj)).isDestroyed())
                        {
                            break MISSING_BLOCK_LABEL_848;
                        }
                        i = 1;
                    }
                      goto _L30
                    i = 0;
                      goto _L30
                }

                public final void onSuccess(Object obj)
                {
                    Object obj1;
                    EventSaveFlow eventsaveflow;
                    Object obj2;
                    Object obj3;
                    Object obj4;
                    boolean flag1;
                    obj1 = null;
                    flag1 = false;
                    obj2 = (Event)obj;
                    eventsaveflow = EventSaveFlow.this;
                    obj3 = guestNotification;
                    obj4 = eventsaveflow.getContext();
                    if (obj4 == null) goto _L2; else goto _L1
_L1:
                    if (true) goto _L4; else goto _L3
_L3:
                    LogUtils.e(EventSaveFlow.TAG, null, "Unable to save", new Object[0]);
                    Toast.makeText(((Context) (obj4)), 0x7f1301ab, 0).show();
                    obj2 = eventsaveflow. new .Lambda._cls2();
                    obj3 = ((Fragment) (eventsaveflow)).mTarget;
                    if (obj3 == null) goto _L6; else goto _L5
_L5:
                    if (obj3 == null) goto _L8; else goto _L7
_L7:
                    obj4 = ((Fragment) (obj3)).mFragmentManager;
                    if (obj3 == null) goto _L10; else goto _L9
_L9:
                    boolean flag;
                    if (((Fragment) (obj3)).mHost != null && ((Fragment) (obj3)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L11; else goto _L10
_L10:
                    flag = false;
_L13:
                    if (!flag) goto _L6; else goto _L12
_L12:
                    flag = true;
_L15:
                    if (flag)
                    {
                        ((Consumer) (obj2)).accept(obj3);
                    }
                    flag = flag1;
                    if (eventsaveflow != null)
                    {
                        obj2 = ((Fragment) (eventsaveflow)).mFragmentManager;
                        flag = flag1;
                        if (eventsaveflow != null)
                        {
                            if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (!flag)
                            {
                                flag = flag1;
                            } else
                            {
                                if (((Fragment) (eventsaveflow)).mHost == null)
                                {
                                    obj = obj1;
                                } else
                                {
                                    obj = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                                }
                                flag = flag1;
                                if (obj != null)
                                {
                                    flag = flag1;
                                    if (!((Activity) (obj)).isDestroyed())
                                    {
                                        flag = flag1;
                                        if (!((Activity) (obj)).isFinishing())
                                        {
                                            flag = flag1;
                                            if (obj2 != null)
                                            {
                                                flag = flag1;
                                                if (!((FragmentManager) (obj2)).isDestroyed())
                                                {
                                                    flag = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (flag)
                    {
                        ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
                    }
_L2:
                    return;
_L11:
label0:
                    {
                        if (((Fragment) (obj3)).mHost == null)
                        {
                            obj = null;
                        } else
                        {
                            obj = (FragmentActivity)((Fragment) (obj3)).mHost.mActivity;
                        }
                        if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
                        {
                            break label0;
                        }
                        flag = false;
                    }
                      goto _L13
                    if (obj4 == null || ((FragmentManager) (obj4)).isDestroyed()) goto _L8; else goto _L14
_L14:
                    flag = true;
                      goto _L13
_L8:
                    flag = false;
                      goto _L13
_L6:
                    flag = false;
                      goto _L15
_L4:
                    int i;
                    if (obj2 == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (Uri)((Event) (obj2)).getDescriptor().getKey().uri().orNull();
                    }
                    if (obj != null)
                    {
                        ((Context) (obj4)).getContentResolver().notifyChange(((Uri) (obj)), null);
                    }
                    if (eventsaveflow.showConfirmationToast)
                    {
                        obj = eventsaveflow.getContext();
                        boolean flag2 = eventsaveflow.event.isNewEvent();
                        obj1 = Features.instance;
                        if (obj1 == null)
                        {
                            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                        }
                        ((FeatureConfig)obj1).notify_guests();
                        if (obj3 != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
                        {
                            if (flag2)
                            {
                                i = 0x7f130143;
                            } else
                            {
                                i = 0x7f130426;
                            }
                        } else
                        {
                            if (obj2 != null && AttendeeUtils.hasGuests(((Event) (obj2))))
                            {
                                i = 1;
                            } else
                            {
                                i = 0;
                            }
                            if (flag2)
                            {
                                if (i != 0)
                                {
                                    i = 0x7f130144;
                                } else
                                {
                                    i = 0x7f130143;
                                }
                            } else
                            if (i != 0)
                            {
                                i = 0x7f130427;
                            } else
                            {
                                i = 0x7f130426;
                            }
                        }
                        Toast.makeText(((Context) (obj)), i, 0).show();
                    }
                    obj1 = eventsaveflow. new .Lambda._cls3(((Event) (obj2)));
                    obj2 = ((Fragment) (eventsaveflow)).mTarget;
                    if (obj2 == null) goto _L17; else goto _L16
_L16:
                    if (obj2 == null) goto _L19; else goto _L18
_L18:
                    obj3 = ((Fragment) (obj2)).mFragmentManager;
                    if (obj2 == null) goto _L21; else goto _L20
_L20:
                    if (((Fragment) (obj2)).mHost != null && ((Fragment) (obj2)).mAdded)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0) goto _L22; else goto _L21
_L21:
                    i = 0;
_L27:
                    if (i == 0) goto _L17; else goto _L23
_L23:
                    i = 1;
_L29:
                    if (i != 0)
                    {
                        ((Consumer) (obj1)).accept(obj2);
                    }
                    if (eventsaveflow == null)
                    {
                        break MISSING_BLOCK_LABEL_885;
                    }
                    obj1 = ((Fragment) (eventsaveflow)).mFragmentManager;
                    if (eventsaveflow == null) goto _L25; else goto _L24
_L24:
                    if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0) goto _L26; else goto _L25
_L25:
                    i = 0;
_L30:
                    if (i != 0)
                    {
                        ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
                        return;
                    }
                      goto _L2
_L22:
label1:
                    {
                        if (((Fragment) (obj2)).mHost == null)
                        {
                            obj = null;
                        } else
                        {
                            obj = (FragmentActivity)((Fragment) (obj2)).mHost.mActivity;
                        }
                        if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
                        {
                            break label1;
                        }
                        i = 0;
                    }
                      goto _L27
                    if (obj3 == null || ((FragmentManager) (obj3)).isDestroyed()) goto _L19; else goto _L28
_L28:
                    i = 1;
                      goto _L27
_L19:
                    i = 0;
                      goto _L27
_L17:
                    i = 0;
                      goto _L29
_L26:
                    if (((Fragment) (eventsaveflow)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                    }
                    if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
                    {
                        i = 0;
                    } else
                    {
                        if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
                        {
                            break MISSING_BLOCK_LABEL_885;
                        }
                        i = 1;
                    }
                      goto _L30
                    i = 0;
                      goto _L30
                }

                _cls1()
                {
                    this$0 = EventSaveFlow.this;
                    guestNotification = guestnotification;
                    super();
                }
            }

        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newStrongFailureLoggingCallback(new .Lambda._cls1(), TAG, "Could not read event from ContentProvider", new Object[0]);
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, futurecallback), _lcls0);
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            event = (EventModifications)bundle.getParcelable("INSTANCE_EVENT");
            scope = bundle.getInt("MODIFICATION_SCOPE");
            showConfirmationToast = bundle.getBoolean("SHOW_CONFIRMATION_TOAST");
            saveScopes = bundle.getIntegerArrayList("SAVE_SCOPES");
        }
    }

    public final void onDrivePermissionsCheckFlowFinished()
    {
        saveWithPermissionsFixed();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_EVENT", event);
        bundle.putInt("MODIFICATION_SCOPE", scope);
        bundle.putBoolean("SHOW_CONFIRMATION_TOAST", showConfirmationToast);
        bundle.putIntegerArrayList("SAVE_SCOPES", new ArrayList(saveScopes));
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        scope = i;
        maybeShowNotificationDialogAndSave();
    }

    final void saveWithPermissionsFixed()
    {
        boolean flag1 = false;
        if (saveScopes.size() <= 1) goto _L2; else goto _L1
_L1:
        Object obj2;
        boolean flag;
        Object obj = saveScopes;
        Object obj1 = (new com.google.android.calendar.event.scope..AutoValue_ScopeSelectionDialog_Config.Builder()).additionalArguments(new Bundle());
        obj = new ArrayList(((java.util.Collection) (obj)));
        Collections.sort(((List) (obj)));
        obj2 = com.google.android.calendar.event.scope.ScopeSelectionUtils..Lambda._cls0.$instance;
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj)), ((com.google.common.base.Function) (obj2)));
        } else
        {
            obj = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj)), ((com.google.common.base.Function) (obj2)));
        }
        obj1 = ((com.google.android.calendar.event.scope.ScopeSelectionDialog.Config.Builder) (obj1)).scopes(((List) (obj))).title(0x7f1301b8).positiveButton(0x7f130142).build();
        flag = flag1;
        if (this == null) goto _L4; else goto _L3
_L3:
        obj2 = super.mFragmentManager;
        flag = flag1;
        if (this == null) goto _L4; else goto _L5
_L5:
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = flag1;
_L4:
        if (flag)
        {
            ScopeSelectionDialog.newInstance(this, ((com.google.android.calendar.event.scope.ScopeSelectionDialog.Config) (obj1))).show(super.mFragmentManager, null);
        }
        return;
_L7:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        flag = flag1;
        if (fragmentactivity != null)
        {
            flag = flag1;
            if (!fragmentactivity.isDestroyed())
            {
                flag = flag1;
                if (!fragmentactivity.isFinishing())
                {
                    flag = flag1;
                    if (obj2 != null)
                    {
                        flag = flag1;
                        if (!((FragmentManager) (obj2)).isDestroyed())
                        {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L2
_L2:
        int i;
        if (saveScopes.isEmpty())
        {
            i = 0;
        } else
        {
            i = ((Integer)Collections.max(saveScopes)).intValue();
        }
        scope = i;
        maybeShowNotificationDialogAndSave();
        return;
    }

}
