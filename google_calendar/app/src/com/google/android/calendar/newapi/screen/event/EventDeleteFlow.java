// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils;
import com.google.android.calendar.utils.flow.Flow;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

public class EventDeleteFlow extends Flow
    implements com.google.android.calendar.event.scope.ScopeSelectionDialog.OnScopeSelectedCallback, com.google.android.calendar.newapi.overflow.DeletionConfirmationDialog.OnDeleteConfirmedCallback
{
    public static final class Factory
    {

        public Factory()
        {
        }
    }

    public static interface Listener
    {

        public abstract void onEventDeleted(boolean flag, int i);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/event/EventDeleteFlow);
    public ListenableFuture deletePendingResult;
    public Event event;
    public EventClient eventClient;
    public boolean prompt;
    public int scope;

    public EventDeleteFlow()
    {
        eventClient = CalendarApi.Events;
        scope = 0;
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            event = (Event)bundle.getParcelable("INSTANCE_EVENT");
        }
    }

    public final void onDeleteConfirmed()
    {
        onScopeSelected(0, null);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_EVENT", event);
        super.onSaveInstanceState(bundle);
    }

    public final void onScopeSelected(int i, com.google.android.calendar.event.scope.ScopeSelectionDialog.Config config)
    {
        if (super.mFragmentManager.isDestroyed())
        {
            return;
        }
        scope = i;
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final EventDeleteFlow arg$1;

            public final ListenableFuture apply(Object obj)
            {
                EventDeleteFlow eventdeleteflow = arg$1;
                obj = (Event)obj;
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).notify_guests();
                if (!GuestNotificationDialogUtils.shouldShowDialogForEventDeletion(eventdeleteflow.event))
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
                if (obj != null)
                {
                    eventdeleteflow.event = ((Event) (obj));
                }
                obj = GuestNotificationDialogUtils.getGuestNotificationDialogStringForDeletion(eventdeleteflow.event, eventdeleteflow.getContext());
                return GuestNotificationDialogUtils.showNotificationDialogAsync(eventdeleteflow.event, eventdeleteflow.getContext(), ((String) (obj)), "delete");
            }

            .Lambda._cls0()
            {
                arg$1 = EventDeleteFlow.this;
            }
        }

        config = AbstractTransformFuture.create(GuestNotificationDialogUtils.maybeReadEvent(event), new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
        class .Lambda._cls1
            implements Consumer
        {

            private final EventDeleteFlow arg$1;

            public final void accept(Object obj)
            {
                Object obj1 = arg$1;
                obj = (com.google.android.calendar.api.event.GooglePrivateData.GuestNotification)obj;
                obj1.deletePendingResult = ((EventDeleteFlow) (obj1)).eventClient.delete(((EventDeleteFlow) (obj1)).event.getDescriptor(), ((EventDeleteFlow) (obj1)).scope, ((com.google.android.calendar.api.event.GooglePrivateData.GuestNotification) (obj)));
                obj = ((EventDeleteFlow) (obj1)).deletePendingResult;
                obj1 = ((_cls1) (obj1)). new _cls1();
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), calendarexecutor);
                    return;
                }
            }

            .Lambda._cls1()
            {
                arg$1 = EventDeleteFlow.this;
            }

            private class _cls1
                implements FutureCallback
            {

                private final EventDeleteFlow this$0;

                public final void onFailure(Throwable throwable)
                {
                    EventDeleteFlow eventdeleteflow;
                    Object obj;
                    Fragment fragment;
                    LogUtils.e(EventDeleteFlow.TAG, throwable, "Unable to delete", new Object[0]);
                    eventdeleteflow = EventDeleteFlow.this;
                    class .Lambda._cls2
                        implements Consumer
                    {

                        private final EventDeleteFlow arg$1;
                        private final boolean arg$2;

                        public final void accept(Object obj1)
                        {
                            EventDeleteFlow eventdeleteflow1 = arg$1;
                            boolean flag1 = arg$2;
                            ((Listener)obj1).onEventDeleted(flag1, eventdeleteflow1.scope);
                        }

                        .Lambda._cls2(boolean flag)
                        {
                            arg$1 = EventDeleteFlow.this;
                            arg$2 = flag;
                        }
                    }

                    obj = eventdeleteflow. new .Lambda._cls2(false);
                    fragment = ((Fragment) (eventdeleteflow)).mTarget;
                    if (fragment == null) goto _L2; else goto _L1
_L1:
                    if (fragment == null) goto _L4; else goto _L3
_L3:
                    android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
                    if (fragment == null) goto _L6; else goto _L5
_L5:
                    boolean flag;
                    if (fragment.mHost != null && fragment.mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L7; else goto _L6
_L6:
                    flag = false;
_L12:
                    if (!flag) goto _L2; else goto _L8
_L8:
                    flag = true;
_L14:
                    if (flag)
                    {
                        ((Consumer) (obj)).accept(fragment);
                    }
                    throwable = eventdeleteflow.getContext();
                    if (throwable != null)
                    {
                        Toast.makeText(throwable, 0x7f1301ab, 0).show();
                    }
                    if (eventdeleteflow == null)
                    {
                        break MISSING_BLOCK_LABEL_336;
                    }
                    obj = ((Fragment) (eventdeleteflow)).mFragmentManager;
                    if (eventdeleteflow == null) goto _L10; else goto _L9
_L9:
                    if (((Fragment) (eventdeleteflow)).mHost != null && ((Fragment) (eventdeleteflow)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L11; else goto _L10
_L10:
                    flag = false;
_L15:
                    if (flag)
                    {
                        ((Fragment) (eventdeleteflow)).mFragmentManager.beginTransaction().remove(eventdeleteflow);
                    }
                    return;
_L7:
label0:
                    {
                        if (fragment.mHost == null)
                        {
                            throwable = null;
                        } else
                        {
                            throwable = (FragmentActivity)fragment.mHost.mActivity;
                        }
                        if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
                        {
                            break label0;
                        }
                        flag = false;
                    }
                      goto _L12
                    if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
                    flag = true;
                      goto _L12
_L4:
                    flag = false;
                      goto _L12
_L2:
                    flag = false;
                      goto _L14
_L11:
                    if (((Fragment) (eventdeleteflow)).mHost == null)
                    {
                        throwable = null;
                    } else
                    {
                        throwable = (FragmentActivity)((Fragment) (eventdeleteflow)).mHost.mActivity;
                    }
                    if (throwable == null || throwable.isDestroyed() || throwable.isFinishing())
                    {
                        flag = false;
                    } else
                    {
                        if (obj == null || ((FragmentManager) (obj)).isDestroyed())
                        {
                            break MISSING_BLOCK_LABEL_336;
                        }
                        flag = true;
                    }
                      goto _L15
                    flag = false;
                      goto _L15
                }

                public final void onSuccess(Object obj)
                {
                    EventDeleteFlow eventdeleteflow;
                    Object obj1;
                    Fragment fragment;
                    eventdeleteflow = EventDeleteFlow.this;
                    obj1 = eventdeleteflow. new .Lambda._cls2(true);
                    fragment = ((Fragment) (eventdeleteflow)).mTarget;
                    if (fragment == null) goto _L2; else goto _L1
_L1:
                    if (fragment == null) goto _L4; else goto _L3
_L3:
                    android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
                    if (fragment == null) goto _L6; else goto _L5
_L5:
                    boolean flag;
                    if (fragment.mHost != null && fragment.mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L7; else goto _L6
_L6:
                    flag = false;
_L12:
                    if (!flag) goto _L2; else goto _L8
_L8:
                    flag = true;
_L14:
                    if (flag)
                    {
                        ((Consumer) (obj1)).accept(fragment);
                    }
                    if (eventdeleteflow.getContext() == null);
                    if (eventdeleteflow == null)
                    {
                        break MISSING_BLOCK_LABEL_310;
                    }
                    obj1 = ((Fragment) (eventdeleteflow)).mFragmentManager;
                    if (eventdeleteflow == null) goto _L10; else goto _L9
_L9:
                    if (((Fragment) (eventdeleteflow)).mHost != null && ((Fragment) (eventdeleteflow)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag) goto _L11; else goto _L10
_L10:
                    flag = false;
_L15:
                    if (flag)
                    {
                        ((Fragment) (eventdeleteflow)).mFragmentManager.beginTransaction().remove(eventdeleteflow);
                    }
                    return;
_L7:
label0:
                    {
                        if (fragment.mHost == null)
                        {
                            obj = null;
                        } else
                        {
                            obj = (FragmentActivity)fragment.mHost.mActivity;
                        }
                        if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
                        {
                            break label0;
                        }
                        flag = false;
                    }
                      goto _L12
                    if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L4; else goto _L13
_L13:
                    flag = true;
                      goto _L12
_L4:
                    flag = false;
                      goto _L12
_L2:
                    flag = false;
                      goto _L14
_L11:
                    if (((Fragment) (eventdeleteflow)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (eventdeleteflow)).mHost.mActivity;
                    }
                    if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
                    {
                        flag = false;
                    } else
                    {
                        if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
                        {
                            break MISSING_BLOCK_LABEL_310;
                        }
                        flag = true;
                    }
                      goto _L15
                    flag = false;
                      goto _L15
                }

                _cls1()
                {
                    this$0 = EventDeleteFlow.this;
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
            config.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(config, futurecallback), _lcls0);
            return;
        }
    }

    public final void onStop()
    {
        super.onStop();
        if (deletePendingResult != null)
        {
            deletePendingResult.cancel(false);
        }
    }

}
