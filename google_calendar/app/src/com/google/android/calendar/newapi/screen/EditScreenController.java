// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController;
import com.google.android.calendar.newapi.segment.common.navigation.OnNavigateAwayListener;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HostedFragment, SegmentMap, SegmentViews, EditScreen, 
//            DiscardChangesDialog

public abstract class EditScreenController extends HostedFragment
    implements com.google.android.calendar.newapi.common.Loader.Callback, DiscardChangesDialog.Callback, EditScreen.Listener
{

    public EditScreen editScreen;
    private boolean isRecreated;
    private Loader loader;
    private EditScreenModel model;
    public SegmentMap segments;

    public EditScreenController()
    {
    }

    public static EditScreenController fromViewScreen(EditScreenController editscreencontroller, ViewScreenModel viewscreenmodel)
    {
        Object obj = editscreencontroller.getArguments();
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = (Bundle)((Optional) (obj)).or(new Bundle());
        ((Bundle) (obj)).putParcelable("ARGS_VIEW_SCREEN_MODEL", viewscreenmodel);
        editscreencontroller.setArguments(((Bundle) (obj)));
        return editscreencontroller;
    }

    static final void lambda$notifyCalendarChanged$0$EditScreenController(boolean flag, boolean flag1, EditSegmentController editsegmentcontroller)
    {
        editsegmentcontroller.onCalendarChanged(flag, flag1);
    }

    static final void lambda$notifyLocationChanged$2$EditScreenController(boolean flag, EditSegmentController editsegmentcontroller)
    {
        editsegmentcontroller.onLocationChanged(flag);
    }

    static final void lambda$notifyTimeChanged$1$EditScreenController(boolean flag, boolean flag1, EditSegmentController editsegmentcontroller)
    {
        editsegmentcontroller.onTimeRelatedFieldChanged(flag, flag1);
    }

    private final void notifySegmentControllers(EditSegmentController editsegmentcontroller, Consumer consumer)
    {
        Iterator iterator = segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller1;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller1 = (EditSegmentController)iterator.next();
        if (editsegmentcontroller1 == editsegmentcontroller || editsegmentcontroller1 != null && editsegmentcontroller1.equals(editsegmentcontroller))
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
        if (editsegmentcontroller1 == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller1)).mFragmentManager;
        if (editsegmentcontroller1 == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller1)).mHost != null && ((Fragment) (editsegmentcontroller1)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L8:
        if (flag)
        {
            consumer.accept(editsegmentcontroller1);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller1)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller1)).mHost.mActivity;
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
_L4:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final void arrangeSegments()
    {
        EditScreen editscreen;
        Object obj;
        View view;
        editscreen = editScreen;
        obj = getOrderedSegments(segments);
        view = ((SegmentViews) (obj)).headerView;
        if (view == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        ViewGroup viewgroup = (ViewGroup)editscreen.findViewById(0x7f100265);
        viewgroup.removeAllViews();
        viewgroup.addView(view);
        obj = ((SegmentViews) (obj)).bodyViews;
        editscreen.segmentContainer.removeAllViews();
        int i = 0;
_L2:
        if (i >= ((ArrayList) (obj)).size())
        {
            break; /* Loop/switch isn't completed */
        }
        editscreen.segmentContainer.addView((View)((ArrayList) (obj)).get(i));
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        try
        {
            editscreen.setVisibility(0);
            editscreen.saveButton.setVisibility(0);
            editscreen.cancelButton.setVisibility(0);
            ViewCompat.requestApplyInsets(editscreen);
            return;
        }
        catch (IllegalStateException illegalstateexception)
        {
            LogUtils.e("EditScreenController", illegalstateexception, "Segments not created due to missing fragments.", new Object[0]);
        }
        return;
    }

    public abstract Loader createLoader();

    public abstract EditScreenModel createModel();

    public abstract SegmentMap createSegments();

    public abstract int getDiscardChangesMessage();

    public EditScreenModel getModel()
    {
        return model;
    }

    public abstract SegmentViews getOrderedSegments(SegmentMap segmentmap);

    protected String getPrimesLogTag()
    {
        return "";
    }

    public final Object getViewScreenController(Class class1)
    {
        Object obj1 = null;
        Object obj;
        Fragment fragment;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        fragment = ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager.findFragmentByTag("ViewScreenController");
        obj = obj1;
        if (class1.isInstance(fragment))
        {
            obj = Utils.uncheckedCast(fragment);
        }
        return obj;
    }

    public final void notifyCalendarChanged(EditSegmentController editsegmentcontroller, boolean flag, boolean flag1)
    {
        .Lambda._cls1 _lcls1;
        Iterator iterator;
        class .Lambda._cls1
            implements Consumer
        {

            private final boolean arg$1;
            private final boolean arg$2;

            public final void accept(Object obj)
            {
                EditScreenController.lambda$notifyCalendarChanged$0$EditScreenController(arg$1, arg$2, (EditSegmentController)obj);
            }

            .Lambda._cls1(boolean flag, boolean flag1)
            {
                arg$1 = flag;
                arg$2 = flag1;
            }
        }

        _lcls1 = new .Lambda._cls1(flag, flag1);
        iterator = segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller1;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag2;
        editsegmentcontroller1 = (EditSegmentController)iterator.next();
        if (editsegmentcontroller1 == editsegmentcontroller || editsegmentcontroller1 != null && editsegmentcontroller1.equals(editsegmentcontroller))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller1 == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller1)).mFragmentManager;
        if (editsegmentcontroller1 == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller1)).mHost != null && ((Fragment) (editsegmentcontroller1)).mAdded)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2) goto _L7; else goto _L6
_L6:
        flag2 = false;
_L8:
        if (flag2)
        {
            _lcls1.accept(editsegmentcontroller1);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller1)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller1)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag2 = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag2 = true;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag2 = false;
        if (true) goto _L8; else goto _L2
_L2:
        onCalendarChanged();
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void notifyEventSaved()
    {
        Consumer consumer;
        Iterator iterator;
        class .Lambda._cls8
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls8();

            public final void accept(Object obj)
            {
                ((EditSegmentController)obj).onEventSaved();
            }


            private .Lambda._cls8()
            {
            }
        }

        consumer = .Lambda._cls8..instance;
        iterator = segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == null || editsegmentcontroller != null && editsegmentcontroller.equals(null))
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
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L8:
        if (flag)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
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
_L4:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected void onCalendarChanged()
    {
    }

    public void onCancelClicked()
    {
        if (!model.isModified())
        {
            dismiss();
            return;
        } else
        {
            DiscardChangesDialog discardchangesdialog = DiscardChangesDialog.newInstance(this, getDiscardChangesMessage());
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
            String s = DiscardChangesDialog.TAG;
            fragmentmanagerimpl.beginTransaction().add(discardchangesdialog, s).commitAllowingStateLoss();
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (!TextUtils.isEmpty(getPrimesLogTag()))
        {
            PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
            if (performancemetriccollector == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory(String.format(null, "%s.Created", new Object[] {
                getPrimesLogTag()
            }));
        }
        if (bundle != null)
        {
            isRecreated = true;
            model = (EditScreenModel)bundle.getParcelable("INSTANCE_MODEL_KEY");
        } else
        {
            isRecreated = false;
            model = createModel();
            boolean flag;
            if (getArguments() != null && getArguments().containsKey("ARGS_VIEW_SCREEN_MODEL"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                model.mergeModel((ViewScreenModel)getArguments().getParcelable("ARGS_VIEW_SCREEN_MODEL"));
                return;
            }
        }
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        boolean flag = false;
        editScreen = new EditScreen(layoutinflater.getContext());
        layoutinflater = editScreen;
        int i;
        if (getChildFragmentManager().findFragmentById(0x7f10026b) != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        layoutinflater = layoutinflater.findViewById(0x7f10026b);
        if (layoutinflater != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            layoutinflater.setVisibility(i);
        }
        return editScreen;
    }

    public final void onDestroy()
    {
        if (!TextUtils.isEmpty(getPrimesLogTag()))
        {
            PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
            if (performancemetriccollector == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory(String.format(null, "%s.Destroyed", new Object[] {
                getPrimesLogTag()
            }));
        }
        super.onDestroy();
    }

    public final void onDestroyView()
    {
        editScreen = null;
        super.onDestroyView();
    }

    public void onDiscard()
    {
        dismiss();
    }

    public final void onDismissQuickCreateClicked()
    {
        class .Lambda._cls9
            implements Consumer
        {

            public static final Consumer $instance = new .Lambda._cls9();

            public final void accept(Object obj)
            {
                ((EditSegmentController)obj).onDismissQuickCreate();
            }


            private .Lambda._cls9()
            {
            }
        }

        notifySegmentControllers(null, .Lambda._cls9..instance);
    }

    public void onLoadingCompleted(boolean flag)
    {
        Object obj;
        EditScreenModel editscreenmodel;
        EditScreen editscreen;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        editscreenmodel = getModel();
        editscreen = editScreen;
        LoggingUtils.addAccountType(((android.content.Context) (obj)), editscreenmodel);
        if (obj != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).setCustomDimension(((android.content.Context) (obj)), 47, "new");
        }
        String s = editscreenmodel.getViewType();
        if (obj != null)
        {
            AnalyticsLogger analyticslogger2 = AnalyticsLoggerHolder.instance;
            if (analyticslogger2 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger2).trackEvent(((android.content.Context) (obj)), "edit_event", s);
        }
        if (obj != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackView(((android.content.Context) (obj)), "edit_screen");
        }
        if (editscreenmodel instanceof EventEditScreenModel)
        {
            Object obj2 = (EventEditScreenModel)editscreenmodel;
            Object obj1 = ((BasicEditScreenModel) (obj2)).getCalendarListEntry();
            if (obj1 != null)
            {
                obj1 = ((CalendarListEntry) (obj1)).getDescriptor().calendarId;
                obj2 = ((EventEditScreenModel) (obj2)).eventReferenceId;
                TextView textview = editscreen.saveButton;
                View view = editscreen.cancelButton;
                VisualElementAttacher visualelementattacher;
                if (!editscreenmodel.isNew())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                visualelementattacher = VisualElementHolder.instance;
                if (visualelementattacher == null)
                {
                    throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                }
                visualelementattacher = (VisualElementAttacher)visualelementattacher;
                visualelementattacher.attachEditElements$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQMD5INEEQQ9HL62TJ15TM62RJ75T9N8SJ9DPJJMJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(editscreen, textview, view, flag, ((String) (obj1)), ((String) (obj2)), ((String) (obj2)));
                visualelementattacher.recordImpression(((android.content.Context) (obj)), editscreen, editscreenmodel.getAccount());
            }
        }
    }

    public final void onLoadingFailure(Loader loader1, String s)
    {
        LogUtils.e("EditScreenController", "a%s", new Object[] {
            s
        });
        if (super.mHost == null)
        {
            loader1 = null;
        } else
        {
            loader1 = (FragmentActivity)super.mHost.mActivity;
        }
        Toast.makeText(loader1, 0x7f1301aa, 0).show();
        loader1 = getContext();
        s = getModel();
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).setCustomDimension(loader1, 47, "new");
        }
        s = s.getViewType();
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackEvent(loader1, "edit_event_failed", s);
        }
        dismiss();
    }

    public final void onLoadingSuccess(Loader loader1)
    {
        model.mergeModel((EditScreenModel)loader.getResult());
        if (segments != null)
        {
            return;
        }
        segments = createSegments();
        getChildFragmentManager().executePendingTransactions();
        EditSegmentController editsegmentcontroller;
        for (loader1 = segments.segmentControllers.values().iterator(); loader1.hasNext(); editsegmentcontroller.onInitialize())
        {
            editsegmentcontroller = (EditSegmentController)loader1.next();
            editsegmentcontroller.editScreenController = this;
        }

        arrangeSegments();
        boolean flag;
        if (!isRecreated)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        onLoadingCompleted(flag);
    }

    public final boolean onNavigateAway()
    {
        boolean flag;
        if (editScreen.findViewById(0x7f10026b).getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            OnNavigateAwayListener onnavigateawaylistener = (OnNavigateAwayListener)getChildFragmentManager().findFragmentByTag(EditFullScreenController.TAG);
            return onnavigateawaylistener != null && onnavigateawaylistener.onNavigateAway();
        }
        if (editScreen.quickCreateList.getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            notifySegmentControllers(null, .Lambda._cls9..instance);
            return true;
        }
        if (!model.isModified())
        {
            return false;
        } else
        {
            DiscardChangesDialog discardchangesdialog = DiscardChangesDialog.newInstance(this, getDiscardChangesMessage());
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
            String s = DiscardChangesDialog.TAG;
            fragmentmanagerimpl.beginTransaction().add(discardchangesdialog, s).commitAllowingStateLoss();
            return true;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_MODEL_KEY", model);
        super.onSaveInstanceState(bundle);
    }

    public final void onStart()
    {
        super.onStart();
        editScreen.listener = this;
        loader = createLoader();
        loader.setCallback(this);
        loader.load();
    }

    public final void onStop()
    {
        loader.setCallback(null);
        editScreen.listener = null;
        super.onStop();
    }
}
