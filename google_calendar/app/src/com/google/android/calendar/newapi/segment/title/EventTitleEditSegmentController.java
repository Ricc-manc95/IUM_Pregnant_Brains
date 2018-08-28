// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.newapi.common.net.LocationResolver;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.CalendarListEntryHolder;
import com.google.android.calendar.newapi.model.TitleHolder;
import com.google.android.calendar.newapi.model.TitleModification;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditLogMetricsHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.quickcreate.EventResult;
import com.google.android.calendar.newapi.quickcreate.EventResultCreator;
import com.google.android.calendar.newapi.quickcreate.QuickCreateAvailability;
import com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker;
import com.google.android.calendar.newapi.quickcreate.QuickCreatePresenter;
import com.google.android.calendar.newapi.quickcreate.QuickCreatePresenterFactory;
import com.google.android.calendar.newapi.quickcreate.QuickCreateSession;
import com.google.android.calendar.newapi.quickcreate.QuickCreateType;
import com.google.android.calendar.newapi.quickcreate.annotation.TaskAssistServiceFactory;
import com.google.android.calendar.newapi.quickcreate.text.ChipDeletionWatcher;
import com.google.android.calendar.newapi.quickcreate.text.ChipFactory;
import com.google.android.calendar.newapi.quickcreate.text.ChipSelectionWatcher;
import com.google.android.calendar.newapi.screen.EditScreen;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.AnnotationType;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleEditSegment

public class EventTitleEditSegmentController extends EditSegmentController
    implements TitleEditSegment.Listener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/title/EventTitleEditSegmentController);
    private boolean askedForPermission;
    private QuickCreateHintTracker hintTracker;
    private QuickCreatePresenterFactory presenterFactory;
    private QuickCreatePresenter quickCreatePresenter;
    private Bundle savedInstance;
    private final TaskAssistServiceFactory serviceFactory = new TaskAssistServiceFactory();
    private QuickCreateSession session;

    public EventTitleEditSegmentController()
    {
        presenterFactory = new QuickCreatePresenterFactory();
        askedForPermission = false;
    }

    private final void applyQuickCreateResult()
    {
        Object obj;
        EventResult eventresult;
        if (quickCreatePresenter == null)
        {
            return;
        }
        eventresult = (EventResult)quickCreatePresenter.createResult();
        ((TitleModification)(EditScreenModel)super.model).setTitle(eventresult.getTitle());
        ((TitleEditSegment)view).setTitle(eventresult.getTitle());
        obj = ((TitleEditSegment)view).titleEditText;
        ((InputMethodManager)((View) (obj)).getContext().getSystemService("input_method")).hideSoftInputFromWindow(((View) (obj)).getWindowToken(), 0);
        ((TitleEditSegment)view).titleEditText.clearFocus();
        obj = eventresult.getLocation();
        if (obj == null) goto _L2; else goto _L1
_L1:
        Object obj2;
        Iterator iterator1;
        obj2 = ((EventModificationsHolder)(EditScreenModel)model).getEventModifications();
        EventLocation eventlocation;
        for (Iterator iterator = ((EventModifications) (obj2)).getLocation().getEventLocations().iterator(); iterator.hasNext(); ((EventModifications) (obj2)).getLocationModification().removeEventLocation(eventlocation))
        {
            eventlocation = (EventLocation)iterator.next();
        }

        ((EventModifications) (obj2)).getLocationModification().addEventLocation(((EventLocation) (obj)));
        ((EventEditLogMetricsHolder)(EditScreenModel)super.model).getLogMetrics().didQuickCreateSetLocation = true;
        obj = super.editScreenController;
        obj2 = new com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls7(true);
        iterator1 = ((EditScreenController) (obj)).segments.segmentControllers.values().iterator();
_L11:
        if (!iterator1.hasNext()) goto _L2; else goto _L3
_L3:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller = (EditSegmentController)iterator1.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L5; else goto _L4
_L4:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L7; else goto _L6
_L6:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L8; else goto _L7
_L7:
        flag = false;
_L9:
        if (flag)
        {
            ((Consumer) (obj2)).accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag = false;
        if (true) goto _L9; else goto _L2
_L2:
        Object obj1 = eventresult.getTimespan();
        byte byte0;
        if (obj1 != null)
        {
            EventModifications eventmodifications = ((EventModificationsHolder)(EditScreenModel)model).getEventModifications();
            int i;
            boolean flag1;
            if (((com.google.android.calendar.newapi.quickcreate.EventResult.Timespan) (obj1)).isAllDay())
            {
                eventmodifications.setToAllDayWithDates(((com.google.android.calendar.newapi.quickcreate.EventResult.Timespan) (obj1)).getStartMs(), ((com.google.android.calendar.newapi.quickcreate.EventResult.Timespan) (obj1)).getEndMs());
            } else
            {
                eventmodifications.setToTimedWithTimes(((com.google.android.calendar.newapi.quickcreate.EventResult.Timespan) (obj1)).getStartMs(), ((com.google.android.calendar.newapi.quickcreate.EventResult.Timespan) (obj1)).getEndMs());
            }
            eventmodifications.setEndTimeUnspecified(false);
            ((EventEditLogMetricsHolder)(EditScreenModel)super.model).getLogMetrics().didQuickCreateSetTime = true;
            notifyTimeChanged(true, false);
        }
        obj1 = hintTracker;
        flag1 = eventresult.wasConnectorUsed();
        i = ((QuickCreateHintTracker) (obj1)).usage;
        if (flag1)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        obj1.usage = Math.max(i, byte0);
        if (eventresult.wasSuggestionReceived())
        {
            obj1 = ((EventEditLogMetricsHolder)(EditScreenModel)super.model).getLogMetrics();
            obj1.quickCreateStatus = Math.max(((EventEditLogMetrics) (obj1)).quickCreateStatus, 2);
        }
        super.editScreenController.editScreen.setQuickCreateVisible(false);
        quickCreatePresenter.finish(getContext());
        quickCreatePresenter = null;
        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private final int getHint(boolean flag)
    {
        boolean flag2 = true;
        Configuration configuration = requireContext().getResources().getConfiguration();
        boolean flag1;
        if (configuration.screenHeightDp >= 490 || configuration.screenWidthDp >= 400)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag && flag1)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        return !flag1 ? 0x7f1301bb : 0x7f1301bc;
    }

    private final void showQuickCreate()
    {
        boolean flag;
        boolean flag1;
        flag1 = true;
        super.editScreenController.editScreen.setQuickCreateVisible(true);
        RecyclerView recyclerview = super.editScreenController.editScreen.quickCreateList;
        ShinobiEditText shinobiedittext = ((TitleEditSegment)super.view).titleEditText;
        QuickCreateSession quickcreatesession = session;
        EventResultCreator eventresultcreator = new EventResultCreator(new LocationResolver(getContext(), Locale.getDefault().getLanguage()));
        Object obj = hintTracker;
        com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter counter = ((QuickCreateHintTracker) (obj)).completedSessions;
        if (counter.value >= 0 && counter.value < counter.threshold)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        obj = ((QuickCreateHintTracker) (obj)).usedConnectors;
        if (((com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter) (obj)).value >= 0 && ((com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter) (obj)).value < ((com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter) (obj)).threshold)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L3
_L3:
        quickCreatePresenter = QuickCreatePresenterFactory.createPresenter(recyclerview, shinobiedittext, quickcreatesession, eventresultcreator, flag1, ((CalendarListEntryHolder)(EditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ImmutableList.of(AnnotationType.LOCAL_BUSINESS, AnnotationType.EVENT_TIME));
        return;
_L2:
        flag1 = false;
        if (true) goto _L3; else goto _L4
_L4:
    }

    private final void updateQuickCreateSession()
    {
        if (QuickCreateAvailability.checkForEvents(((TitleEditSegment)super.view).getContext(), ((CalendarListEntryHolder)(EditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications(), requireContext().getResources().getConfiguration().locale))
        {
            session = QuickCreateSession.create(getContext(), QuickCreateType.EVENT, session, ((CalendarListEntryHolder)(EditScreenModel)super.model).getCalendarListEntry().getDescriptor().account, serviceFactory);
            ((TitleEditSegment)super.view).titleEditText.setHint(getHint(true));
            Object obj = session;
            if (((QuickCreateSession) (obj)).warmupResult != null)
            {
                obj = ((QuickCreateSession) (obj)).warmupResult;
                _cls1 _lcls1 = new _cls1();
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                if (_lcls1 == null)
                {
                    throw new NullPointerException();
                }
                ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), _lcls1), calendarexecutor);
            }
            return;
        } else
        {
            session = null;
            ((TitleEditSegment)super.view).titleEditText.setHint(getHint(false));
            EventEditLogMetrics eventeditlogmetrics = ((EventEditLogMetricsHolder)(EditScreenModel)super.model).getLogMetrics();
            eventeditlogmetrics.quickCreateStatus = Math.max(eventeditlogmetrics.quickCreateStatus, 1);
            return;
        }
    }

    private final void updateView(boolean flag)
    {
        boolean flag1 = true;
        boolean flag2 = ((TitleModification)(EditScreenModel)super.model).canModifyTitle();
        if (quickCreatePresenter == null)
        {
            String s = ((TitleHolder)(EditScreenModel)super.model).getTitle();
            if (!flag2 && TextUtils.isEmpty(s))
            {
                s = getContext().getResources().getString(0x7f130358);
            }
            ((TitleEditSegment)super.view).setTitle(s);
        }
        Object obj = (TitleEditSegment)super.view;
        int i;
        if (!flag2)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj = ((TitleEditSegment) (obj)).titleEditText;
        if (i != 0)
        {
            flag1 = false;
        }
        ((ShinobiEditText) (obj)).setEnabled(flag1);
        obj = (TitleEditSegment)super.view;
        i = ((EditScreenModel)super.model).getColor().getValue();
        if (flag && ViewCompat.isAttachedToWindow(((TitleEditSegment) (obj)).rippleView))
        {
            int j = ((TitleEditSegment) (obj)).getWidth() / 2;
            int k = ((TitleEditSegment) (obj)).getHeight() / 2;
            float f = (float)(Math.hypot(((TitleEditSegment) (obj)).getWidth(), ((TitleEditSegment) (obj)).getHeight()) / 2D);
            Animator animator = ViewAnimationUtils.createCircularReveal(((TitleEditSegment) (obj)).rippleView, j, k, 0.0F, f);
            animator.addListener(new TitleEditSegment._cls2(((TitleEditSegment) (obj)), i));
            animator.setStartDelay(200L);
            animator.start();
            return;
        } else
        {
            ((TitleEditSegment) (obj)).setBackgroundColor(i);
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = new TitleEditSegment(layoutinflater.getContext(), null);
        layoutinflater.mListener = this;
        ShinobiEditText shinobiedittext = ((TitleEditSegment) (layoutinflater)).titleEditText;
        ChipFactory.setupLineHeightFor(shinobiedittext);
        shinobiedittext.addGlobalSpan(new ChipDeletionWatcher(shinobiedittext));
        shinobiedittext.onSelectionChangedListener = new ChipSelectionWatcher(shinobiedittext);
        return layoutinflater;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateQuickCreateSession();
        if (EditSegmentController.animationsOn && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        updateView(flag);
    }

    public final void onColorChanged()
    {
        updateView(EditSegmentController.animationsOn);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        savedInstance = bundle;
        if (bundle != null)
        {
            session = (QuickCreateSession)bundle.getParcelable("INSTANCE_SESSION");
            hintTracker = (QuickCreateHintTracker)bundle.getParcelable("INSTANCE_HINT_TRACKER");
            askedForPermission = bundle.getBoolean("INSTANCE_ASKED_FOR_PERMISSION");
            return;
        } else
        {
            bundle = getContext();
            hintTracker = new QuickCreateHintTracker(com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter.createFromSharedPref(bundle, 0x7f11002a, "com.google.android.calendar.event.quickcreate.hints.event_created"), com.google.android.calendar.newapi.quickcreate.QuickCreateHintTracker.Counter.createFromSharedPref(bundle, 0x7f110029, "com.google.android.calendar.event.quickcreate.hints.connector_accepted"));
            askedForPermission = AndroidPermissionUtils.hasLocationPermissions(getContext());
            return;
        }
    }

    public final void onDismissQuickCreate()
    {
        applyQuickCreateResult();
    }

    public final void onEventSaved()
    {
        QuickCreateHintTracker.saveToSharedPreferences(getContext(), hintTracker);
    }

    protected final void onInitialize()
    {
        boolean flag2;
        flag2 = true;
        updateQuickCreateSession();
        if (savedInstance != null)
        {
            Bundle bundle = savedInstance.getBundle("INSTANCE_QUICK_CREATE");
            if (bundle != null)
            {
                showQuickCreate();
                quickCreatePresenter.restoreState(bundle);
            }
        }
        updateView(false);
        if (savedInstance == null && ((EditScreenModel)super.model).isNew()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        if (flag)
        {
            ((TitleEditSegment)super.view).showKeyboard();
        }
        return;
_L2:
        flag = flag2;
        if (AndroidPermissionUtils.hasLocationPermissions(getContext()))
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag1;
        boolean flag3;
        if (PreferencesUtils.getLocationPermissionRequests(getContext()) > 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (super.mHost != null)
        {
            flag3 = super.mHost.onShouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION");
        } else
        {
            flag3 = false;
        }
        if (!flag3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag = flag2;
            if (flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onKeyboardDone()
    {
        applyQuickCreateResult();
    }

    public final void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        if (i != 42)
        {
            super.onRequestPermissionsResult(i, as, ai);
            return;
        } else
        {
            PreferencesUtils.incrementLocationPermissionRequest(getContext());
            ((TitleEditSegment)super.view).showKeyboard();
            return;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_SESSION", session);
        bundle.putParcelable("INSTANCE_HINT_TRACKER", hintTracker);
        if (quickCreatePresenter != null)
        {
            bundle.putBundle("INSTANCE_QUICK_CREATE", quickCreatePresenter.getState());
        }
        super.onSaveInstanceState(bundle);
    }

    public final void onTitleChanged(String s)
    {
        ((TitleModification)(EditScreenModel)super.model).setTitle(s);
        if (session != null)
        {
            s = super.editScreenController.editScreen;
            if (s != null)
            {
                boolean flag;
                if (((EditScreen) (s)).quickCreateList.getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    showQuickCreate();
                }
            }
        }
    }

    public final boolean onTitleTouched()
    {
        if (AndroidPermissionUtils.hasLocationPermissions(getContext()) || askedForPermission || session == null)
        {
            return false;
        } else
        {
            askedForPermission = true;
            requestPermissions(new String[] {
                "android.permission.ACCESS_COARSE_LOCATION"
            }, 42);
            return true;
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final EventTitleEditSegmentController this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(EventTitleEditSegmentController.TAG, throwable, "Quick create unreachable.", new Object[0]);
            throwable = ((EventEditLogMetricsHolder)(EditScreenModel)model).getLogMetrics();
            throwable.quickCreateStatus = Math.max(((EventEditLogMetrics) (throwable)).quickCreateStatus, 0);
        }

        public final void onSuccess(Object obj)
        {
            obj = ((EventEditLogMetricsHolder)(EditScreenModel)model).getLogMetrics();
            obj.quickCreateStatus = Math.max(((EventEditLogMetrics) (obj)).quickCreateStatus, 2);
        }

        _cls1()
        {
            this$0 = EventTitleEditSegmentController.this;
            super();
        }
    }

}
