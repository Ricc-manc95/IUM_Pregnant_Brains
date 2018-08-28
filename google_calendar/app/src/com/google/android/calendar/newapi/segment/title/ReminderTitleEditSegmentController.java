// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.accounts.Account;
import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.newapi.model.TaskHolder;
import com.google.android.calendar.newapi.model.TitleHolder;
import com.google.android.calendar.newapi.model.TitleModification;
import com.google.android.calendar.newapi.model.edit.AssistInformationEditHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.quickcreate.QuickCreateAvailability;
import com.google.android.calendar.newapi.quickcreate.QuickCreatePresenter;
import com.google.android.calendar.newapi.quickcreate.QuickCreatePresenterFactory;
import com.google.android.calendar.newapi.quickcreate.QuickCreateSession;
import com.google.android.calendar.newapi.quickcreate.QuickCreateType;
import com.google.android.calendar.newapi.quickcreate.ReminderResult;
import com.google.android.calendar.newapi.quickcreate.ReminderResultCreator;
import com.google.android.calendar.newapi.quickcreate.annotation.TaskAssistServiceFactory;
import com.google.android.calendar.newapi.quickcreate.text.ChipFactory;
import com.google.android.calendar.newapi.screen.EditScreen;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.task.assist.TaskAssistanceUtils;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.android.gms.reminders.model.Task;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleEditSegment

public class ReminderTitleEditSegmentController extends EditSegmentController
    implements TitleEditSegment.Listener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/title/ReminderTitleEditSegmentController);
    private String preQuickCreateTitle;
    private QuickCreatePresenterFactory presenterFactory;
    private QuickCreatePresenter quickCreatePresenter;
    private Bundle savedInstance;
    private final TaskAssistServiceFactory serviceFactory = new TaskAssistServiceFactory();
    private QuickCreateSession session;

    public ReminderTitleEditSegmentController()
    {
        presenterFactory = new QuickCreatePresenterFactory();
    }

    private final void applyQuickCreateResult()
    {
        boolean flag2 = true;
        if (quickCreatePresenter == null)
        {
            return;
        }
        ReminderResult reminderresult = (ReminderResult)quickCreatePresenter.createResult();
        String s = reminderresult.getTitle();
        ((TitleModification)(EditScreenModel)super.model).setTitle(s);
        ((TitleEditSegment)super.view).setTitle(s);
        Object obj = preQuickCreateTitle;
        byte abyte0[] = ((TaskHolder)(EditScreenModel)super.model).getTask().getAssistance();
        com.google.android.calendar.newapi.quickcreate.ReminderResult.TaskAssistanceInfo taskassistanceinfo = reminderresult.getTaskAssistance();
        boolean flag;
        if (!TextUtils.isEmpty(taskassistanceinfo.getAssistanceQuery()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            class .Lambda._cls0
                implements Consumer
            {

                private final ReminderTitleEditSegmentController arg$1;

                public final void accept(Object obj1)
                {
                    ReminderTitleEditSegmentController remindertitleeditsegmentcontroller = arg$1;
                    obj1 = (byte[])obj1;
                    ((AssistInformationEditHolder)(EditScreenModel)((SegmentController) (remindertitleeditsegmentcontroller)).model).setAssistInformation(((byte []) (obj1)));
                    remindertitleeditsegmentcontroller.notifyTaskAssistChanged();
                }

            .Lambda._cls0()
            {
                arg$1 = ReminderTitleEditSegmentController.this;
            }
            }

            if (TaskAssistanceUtils.hasAssist(abyte0) && TaskAssistanceUtils.isAssistanceQueryValid(s, ((String) (obj))))
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                abyte0 = TaskAssistanceUtils.createEmptyTaskAssist();
            }
        } else
        if (!TaskAssistanceUtils.isAssistanceQueryValid(s, taskassistanceinfo.getAssistanceQuery()))
        {
            abyte0 = TaskAssistanceUtils.createEmptyTaskAssist();
        } else
        {
            boolean flag1;
            if (taskassistanceinfo.getAssistance() != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                abyte0 = taskassistanceinfo.getAssistance();
            } else
            {
                abyte0 = null;
            }
        }
        if (abyte0 == null)
        {
            abyte0 = getContext();
            obj = getContext();
            TaskAssistanceUtils.fetchAssistance(abyte0, new TaskAssistService(((Context) (obj)), ((EditScreenModel)super.model).getAccount().name, ExperimentalOptions.isTaskAssistStagingEnabled(((Context) (obj)))), s, reminderresult.getTaskAssistance().getAnnotationHint(), new .Lambda._cls0());
        } else
        {
            ((AssistInformationEditHolder)(EditScreenModel)super.model).setAssistInformation(abyte0);
            notifyTaskAssistChanged();
        }
        abyte0 = ((TitleEditSegment)view).titleEditText;
        ((InputMethodManager)abyte0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(abyte0.getWindowToken(), 0);
        ((TitleEditSegment)view).titleEditText.clearFocus();
        super.editScreenController.editScreen.setQuickCreateVisible(false);
        quickCreatePresenter.finish(getContext());
        quickCreatePresenter = null;
    }

    private final void showQuickCreate()
    {
        Object obj = super.editScreenController.editScreen;
        if (session == null || obj == null)
        {
            return;
        } else
        {
            preQuickCreateTitle = ((TitleHolder)(EditScreenModel)super.model).getTitle();
            ((EditScreen) (obj)).setQuickCreateVisible(true);
            obj = ((EditScreen) (obj)).quickCreateList;
            ShinobiEditText shinobiedittext = ((TitleEditSegment)super.view).titleEditText;
            QuickCreateSession quickcreatesession = session;
            Context context = getContext();
            Context context1 = getContext();
            quickCreatePresenter = QuickCreatePresenterFactory.createPresenter(((android.support.v7.widget.RecyclerView) (obj)), shinobiedittext, quickcreatesession, new ReminderResultCreator(context, new TaskAssistService(context1, ((EditScreenModel)super.model).getAccount().name, ExperimentalOptions.isTaskAssistStagingEnabled(context1))), false, ((EditScreenModel)super.model).getAccount(), ImmutableList.of());
            return;
        }
    }

    private final void updateQuickCreateSession()
    {
        if (QuickCreateAvailability.checkForReminders(((TitleEditSegment)super.view).getContext(), ((EditScreenModel)super.model).getAccount(), requireContext().getResources().getConfiguration().locale))
        {
            session = QuickCreateSession.create(getContext(), QuickCreateType.REMINDER, session, ((EditScreenModel)super.model).getAccount(), serviceFactory);
            QuickCreateSession quickcreatesession = session;
            if (quickcreatesession.warmupResult != null)
            {
                LogUtils.logOnFailure(quickcreatesession.warmupResult, TAG, "Quick create unreachable.", new Object[0]);
            }
            return;
        } else
        {
            session = null;
            return;
        }
    }

    private final void updateView(boolean flag)
    {
        if (quickCreatePresenter == null)
        {
            ((TitleEditSegment)super.view).setTitle(((TitleHolder)(EditScreenModel)super.model).getTitle());
        }
        ((TitleEditSegment)super.view).titleEditText.setEnabled(true);
        TitleEditSegment titleeditsegment = (TitleEditSegment)super.view;
        int i = ((EditScreenModel)super.model).getColor().getValue();
        if (flag && ViewCompat.isAttachedToWindow(titleeditsegment.rippleView))
        {
            int j = titleeditsegment.getWidth() / 2;
            int k = titleeditsegment.getHeight() / 2;
            float f = (float)(Math.hypot(titleeditsegment.getWidth(), titleeditsegment.getHeight()) / 2D);
            Animator animator = ViewAnimationUtils.createCircularReveal(titleeditsegment.rippleView, j, k, 0.0F, f);
            animator.addListener(new TitleEditSegment._cls2(titleeditsegment, i));
            animator.setStartDelay(200L);
            animator.start();
            return;
        } else
        {
            titleeditsegment.setBackgroundColor(i);
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = new TitleEditSegment(layoutinflater.getContext(), null);
        ((TitleEditSegment) (layoutinflater)).titleEditText.setHint(0x7f1301bd);
        layoutinflater.mListener = this;
        ChipFactory.setupLineHeightFor(((TitleEditSegment) (layoutinflater)).titleEditText);
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

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        savedInstance = bundle;
        if (bundle != null)
        {
            session = (QuickCreateSession)bundle.getParcelable("INSTANCE_SESSION");
        }
    }

    public final void onDismissQuickCreate()
    {
        applyQuickCreateResult();
    }

    protected final void onInitialize()
    {
        updateQuickCreateSession();
        if (savedInstance == null) goto _L2; else goto _L1
_L1:
        Bundle bundle = savedInstance.getBundle("INSTANCE_QUICK_CREATE");
        if (bundle != null)
        {
            showQuickCreate();
            quickCreatePresenter.restoreState(bundle);
        }
_L4:
        updateView(false);
        return;
_L2:
        if (((EditScreenModel)super.model).isNew())
        {
            showQuickCreate();
            ShinobiEditText shinobiedittext = ((TitleEditSegment)super.view).titleEditText;
            shinobiedittext.getClass();
            shinobiedittext.post(new com.google.android.calendar.newapi.common.Keyboard..Lambda._cls0(shinobiedittext));
            shinobiedittext.setOnFocusChangeListener(com.google.android.calendar.newapi.common.Keyboard..Lambda._cls1.$instance);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onKeyboardDone()
    {
        applyQuickCreateResult();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_SESSION", session);
        bundle.putString("INSTANCE_PRE_QUICK_CREATE_TITLE", preQuickCreateTitle);
        if (quickCreatePresenter != null)
        {
            bundle.putBundle("INSTANCE_QUICK_CREATE", quickCreatePresenter.getState());
        }
        super.onSaveInstanceState(bundle);
    }

    public final void onTitleChanged(String s)
    {
        ((TitleModification)(EditScreenModel)super.model).setTitle(s);
        if (quickCreatePresenter == null)
        {
            showQuickCreate();
        }
    }

    public final boolean onTitleTouched()
    {
        return false;
    }

}
