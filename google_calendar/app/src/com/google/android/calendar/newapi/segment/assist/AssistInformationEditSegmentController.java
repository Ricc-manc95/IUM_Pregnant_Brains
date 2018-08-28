// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.assist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.model.AssistInformationHolder;
import com.google.android.calendar.newapi.model.edit.AssistInformationEditHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.base.Joiner;

// Referenced classes of package com.google.android.calendar.newapi.segment.assist:
//            AssistInformationEditSegment, AssistActionDialog

public class AssistInformationEditSegmentController extends EditSegmentController
    implements AssistActionDialog.Listener, AssistInformationEditSegment.Listener
{

    public AssistInformationEditSegmentController()
    {
    }

    private final void updateUi()
    {
        Object obj = ((AssistInformationHolder)super.model).getAssistInformation();
        Object obj1 = super.view;
        boolean flag;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (obj1 != null)
        {
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((View) (obj1)).setVisibility(j);
        }
        if (!flag)
        {
            return;
        }
        obj1 = (AssistInformationEditSegment)super.view;
        String s = ((TaskAssistHolder) (obj)).getDisplayText(getContext());
        android.text.SpannableString spannablestring = ((TaskAssistHolder) (obj)).getSecondaryDisplayText(getContext());
        int i = ((TaskAssistHolder) (obj)).getIconId();
        obj = ((TaskAssistHolder) (obj)).getDescription(getContext());
        ((AssistInformationEditSegment) (obj1)).tile.setContentDescription(((CharSequence) (obj)));
        ((AssistInformationEditSegment) (obj1)).tile.setTextAdaptively(s, spannablestring);
        if (i == 0)
        {
            ((AssistInformationEditSegment) (obj1)).tile.setIconDrawable(null);
            return;
        } else
        {
            ((AssistInformationEditSegment) (obj1)).tile.setIconDrawable(i);
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (AssistInformationEditSegment)layoutinflater.inflate(0x7f0500bc, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void executeAssistAction()
    {
        TaskAssistHolder taskassistholder = ((AssistInformationHolder)super.model).getAssistInformation();
        taskassistholder.gotoAssist(getContext());
        boolean flag = ExperimentalOptions.isTaskAssistStagingEnabled(getContext());
        taskassistholder.logClick(getContext(), "editor_action", flag);
    }

    public final void onAssistTileClicked()
    {
        Object obj2 = ((AssistInformationHolder)super.model).getAssistInformation();
        android.content.Context context = getContext();
        Object obj;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (obj2 == null || !((TaskAssistHolder) (obj2)).isClickable(context))
        {
            obj = null;
        } else
        {
            obj = (new Joiner(String.valueOf(' '))).skipNulls();
            Object obj1 = ((TaskAssistHolder) (obj2)).getAssistActionText(context);
            obj2 = ((TaskAssistHolder) (obj2)).getAssistInfoText(context);
            Object aobj[] = new Object[0];
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            obj1 = (new com.google.common.base.Joiner._cls3(aobj, obj1, obj2)).iterator();
            obj = ((Joiner) (obj)).appendTo(new StringBuilder(), ((java.util.Iterator) (obj1))).toString().trim();
        }
        obj = AssistActionDialog.createDialog(((String) (obj)), this);
        fragmentmanagerimpl = super.mFragmentManager;
        obj2 = AssistActionDialog.TAG;
        fragmentmanagerimpl.beginTransaction().add(((Fragment) (obj)), ((String) (obj2))).commitAllowingStateLoss();
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    public final void onTaskAssistChanged()
    {
        updateUi();
    }

    public final void removeAssist()
    {
        ((AssistInformationEditHolder)(AssistInformationHolder)super.model).setAssistInformation(null);
        updateUi();
    }
}
