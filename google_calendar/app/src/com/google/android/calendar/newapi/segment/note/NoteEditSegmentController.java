// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.note:
//            NoteEditSegment, NoteHtmlConverter

public class NoteEditSegmentController extends EditSegmentController
    implements NoteEditSegment.Listener
{
    public static class RemoveFormattingDialog extends DialogFragment
        implements android.content.DialogInterface.OnClickListener
    {

        public static final String FRAGMENT_TAG = com/google/android/calendar/newapi/segment/note/NoteEditSegmentController$RemoveFormattingDialog.getName();

        public void onCancel(DialogInterface dialoginterface)
        {
            super.onCancel(dialoginterface);
            dialoginterface = (NoteEditSegmentController)super.mTarget;
            if (dialoginterface != null)
            {
                if (((SegmentController) (dialoginterface)).view == null);
            }
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            if (i == -1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            dialoginterface = (NoteEditSegmentController)super.mTarget;
            if (dialoginterface != null && ((SegmentController) (dialoginterface)).view != null && i != 0)
            {
                String s = NoteHtmlConverter.toPlainText(((EventModificationsHolder)((SegmentController) (dialoginterface)).model).getEventModifications().getDescription());
                ((EventModificationsHolder)((SegmentController) (dialoginterface)).model).getEventModifications().setDescription(s);
                ((NoteEditSegment)((SegmentController) (dialoginterface)).view).setNote(s, true);
                ((NoteEditSegment)((SegmentController) (dialoginterface)).view).noteEditText.requestFocus();
                dialoginterface.hasRemovedFormatting = true;
            }
        }

        public final Dialog onCreateDialog(Bundle bundle)
        {
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            bundle = new android.support.v7.app.AlertDialog.Builder(bundle);
            ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mMessage = ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mContext.getText(0x7f130467);
            ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mPositiveButtonText = ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mContext.getText(0x7f130468);
            ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mPositiveButtonListener = this;
            ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mNegativeButtonText = ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mContext.getText(0x7f130100);
            ((android.support.v7.app.AlertDialog.Builder) (bundle)).P.mNegativeButtonListener = this;
            return bundle.create();
        }


        public RemoveFormattingDialog()
        {
        }
    }


    public boolean hasRemovedFormatting;

    public NoteEditSegmentController()
    {
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (NoteEditSegment)layoutinflater.inflate(0x7f0500dc, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        View view = super.view;
        flag = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().canModifyDescription();
        if (view != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            hasRemovedFormatting = bundle.getBoolean("has_removed_formatting");
        }
    }

    protected final void onInitialize()
    {
        Object obj = super.view;
        boolean flag = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().canModifyDescription();
        if (obj != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        obj = ((EventModificationsHolder)super.model).getEventModifications().getDescription();
        if (!hasRemovedFormatting && NoteHtmlConverter.isHtml(((String) (obj))))
        {
            obj = NoteHtmlConverter.toFormattedText(((String) (obj)));
            ((NoteEditSegment)super.view).setNote(((CharSequence) (obj)), false);
            return;
        } else
        {
            ((NoteEditSegment)super.view).setNote(((CharSequence) (obj)), true);
            hasRemovedFormatting = true;
            return;
        }
    }

    public final void onNoteChanged(String s)
    {
        ((EventModificationsHolder)super.model).getEventModifications().setDescription(s);
    }

    public final void onNoteEditStart()
    {
        if (hasRemovedFormatting) goto _L2; else goto _L1
_L1:
        if (this == null) goto _L4; else goto _L3
_L3:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L6; else goto _L5
_L5:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L9:
        if (flag)
        {
            RemoveFormattingDialog removeformattingdialog = new RemoveFormattingDialog();
            removeformattingdialog.setTargetFragment(this, 0);
            removeformattingdialog.show(super.mFragmentManager, RemoveFormattingDialog.FRAGMENT_TAG);
        }
_L2:
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
        if (true) goto _L9; else goto _L8
_L8:
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("has_removed_formatting", hasRemovedFormatting);
    }
}
