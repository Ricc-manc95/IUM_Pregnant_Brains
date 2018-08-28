// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package android.support.v7.app:
//            AlertDialog, AlertController

public final class mTheme
{

    public final tParams P;
    private final int mTheme;

    public final AlertDialog create()
    {
        AlertDialog alertdialog = new AlertDialog(P.mContext, mTheme);
        tParams tparams = P;
        AlertController alertcontroller = alertdialog.mAlert;
        if (tparams.mCustomTitleView != null)
        {
            alertcontroller.mCustomTitleView = tparams.mCustomTitleView;
        } else
        {
            if (tparams.mTitle != null)
            {
                obj = tparams.mTitle;
                alertcontroller.mTitle = ((CharSequence) (obj));
                if (alertcontroller.mTitleView != null)
                {
                    alertcontroller.mTitleView.setText(((CharSequence) (obj)));
                }
            }
            if (tparams.mIcon != null)
            {
                obj = tparams.mIcon;
                alertcontroller.mIcon = ((android.graphics.drawable.Drawable) (obj));
                alertcontroller.mIconId = 0;
                if (alertcontroller.mIconView != null)
                {
                    if (obj != null)
                    {
                        alertcontroller.mIconView.setVisibility(0);
                        alertcontroller.mIconView.setImageDrawable(((android.graphics.drawable.Drawable) (obj)));
                    } else
                    {
                        alertcontroller.mIconView.setVisibility(8);
                    }
                }
            }
        }
        if (tparams.mMessage != null)
        {
            CharSequence charsequence = tparams.mMessage;
            alertcontroller.mMessage = charsequence;
            if (alertcontroller.mMessageView != null)
            {
                alertcontroller.mMessageView.setText(charsequence);
            }
        }
        if (tparams.mPositiveButtonText != null)
        {
            alertcontroller.setButton(-1, tparams.mPositiveButtonText, tparams.mPositiveButtonListener, null, null);
        }
        if (tparams.mNegativeButtonText != null)
        {
            alertcontroller.setButton(-2, tparams.mNegativeButtonText, tparams.mNegativeButtonListener, null, null);
        }
        if (tparams.mNeutralButtonText != null)
        {
            alertcontroller.setButton(-3, tparams.mNeutralButtonText, tparams.mNeutralButtonListener, null, null);
        }
        if (tparams.mItems != null || tparams.mAdapter != null)
        {
            cleListView clelistview = (cleListView)tparams.mInflater.inflate(alertcontroller.mListLayout, null);
            Object obj;
            if (tparams.mIsMultiChoice)
            {
                obj = new tParams._cls1(tparams, tparams.mContext, alertcontroller.mMultiChoiceItemLayout, 0x1020014, tparams.mItems, clelistview);
            } else
            {
                int i;
                if (tparams.mIsSingleChoice)
                {
                    i = alertcontroller.mSingleChoiceItemLayout;
                } else
                {
                    i = alertcontroller.mListItemLayout;
                }
                if (tparams.mAdapter != null)
                {
                    obj = tparams.mAdapter;
                } else
                {
                    obj = new kedItemAdapter(tparams.mContext, i, 0x1020014, tparams.mItems);
                }
            }
            alertcontroller.mAdapter = ((android.widget.ListAdapter) (obj));
            alertcontroller.mCheckedItem = tparams.mCheckedItem;
            if (tparams.mOnClickListener != null)
            {
                clelistview.setOnItemClickListener(new tParams._cls3(tparams, alertcontroller));
            } else
            if (tparams.mOnCheckboxClickListener != null)
            {
                clelistview.setOnItemClickListener(new tParams._cls4(tparams, clelistview, alertcontroller));
            }
            if (tparams.mIsSingleChoice)
            {
                clelistview.setChoiceMode(1);
            } else
            if (tparams.mIsMultiChoice)
            {
                clelistview.setChoiceMode(2);
            }
            alertcontroller.mListView = clelistview;
        }
        if (tparams.mView != null)
        {
            alertcontroller.mView = tparams.mView;
            alertcontroller.mViewLayoutResId = 0;
            alertcontroller.mViewSpacingSpecified = false;
        }
        alertdialog.setCancelable(P.mCancelable);
        if (P.mCancelable)
        {
            alertdialog.setCanceledOnTouchOutside(true);
        }
        alertdialog.setOnCancelListener(P.mOnCancelListener);
        alertdialog.setOnDismissListener(null);
        if (P.mOnKeyListener != null)
        {
            alertdialog.setOnKeyListener(P.mOnKeyListener);
        }
        return alertdialog;
    }

    public tParams(Context context)
    {
        this(context, AlertDialog.resolveDialogTheme(context, 0));
    }

    private ialogTheme(Context context, int i)
    {
        P = new tParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
        mTheme = i;
    }
}
