// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

// Referenced classes of package android.support.v7.app:
//            AppCompatDialog, AppCompatDelegateImpl, AppCompatDelegate

public final class AlertController
{
    public static class RecycleListView extends ListView
    {

        public final int mPaddingBottomNoButtons;
        public final int mPaddingTopNoTitle;

        public RecycleListView(Context context)
        {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.RecycleListView);
            mPaddingBottomNoButtons = context.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.RecycleListView_paddingBottomNoButtons, -1);
            mPaddingTopNoTitle = context.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.RecycleListView_paddingTopNoTitle, -1);
        }
    }


    public ListAdapter mAdapter;
    public int mAlertDialogLayout;
    public final android.view.View.OnClickListener mButtonHandler = new _cls1();
    public final int mButtonIconDimen;
    public Button mButtonNegative;
    public Drawable mButtonNegativeIcon;
    public Message mButtonNegativeMessage;
    public CharSequence mButtonNegativeText;
    public Button mButtonNeutral;
    public Drawable mButtonNeutralIcon;
    public Message mButtonNeutralMessage;
    public CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    public int mButtonPanelSideLayout;
    public Button mButtonPositive;
    public Drawable mButtonPositiveIcon;
    public Message mButtonPositiveMessage;
    public CharSequence mButtonPositiveText;
    public int mCheckedItem;
    public final Context mContext;
    public View mCustomTitleView;
    public final AppCompatDialog mDialog;
    public Handler mHandler;
    public Drawable mIcon;
    public int mIconId;
    public ImageView mIconView;
    public int mListItemLayout;
    public int mListLayout;
    public ListView mListView;
    public CharSequence mMessage;
    public TextView mMessageView;
    public int mMultiChoiceItemLayout;
    public NestedScrollView mScrollView;
    public boolean mShowTitle;
    public int mSingleChoiceItemLayout;
    public CharSequence mTitle;
    public TextView mTitleView;
    public View mView;
    public int mViewLayoutResId;
    public int mViewSpacingBottom;
    public int mViewSpacingLeft;
    public int mViewSpacingRight;
    public boolean mViewSpacingSpecified;
    public int mViewSpacingTop;
    public final Window mWindow;

    public AlertController(Context context, AppCompatDialog appcompatdialog, Window window)
    {
        mViewSpacingSpecified = false;
        mIconId = 0;
        mCheckedItem = -1;
        mButtonPanelLayoutHint = 0;
        mContext = context;
        mDialog = appcompatdialog;
        mWindow = window;
        mHandler = new ButtonHandler(appcompatdialog);
        context = context.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.AlertDialog, 0x7f0100cb, 0);
        mAlertDialogLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_android_layout, 0);
        mButtonPanelSideLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        mListLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_listLayout, 0);
        mMultiChoiceItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        mSingleChoiceItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        mListItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_listItemLayout, 0);
        mShowTitle = context.getBoolean(android.support.v7.appcompat.R.styleable.AlertDialog_showTitle, true);
        mButtonIconDimen = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.AlertDialog_buttonIconDimen, 0);
        context.recycle();
        if (appcompatdialog.mDelegate == null)
        {
            appcompatdialog.mDelegate = new AppCompatDelegateImpl(appcompatdialog.getContext(), appcompatdialog.getWindow(), appcompatdialog);
        }
        appcompatdialog.mDelegate.requestWindowFeature(1);
    }

    static boolean canTextInput(View view)
    {
        if (view.onCheckIsTextEditor())
        {
            return true;
        }
        if (!(view instanceof ViewGroup))
        {
            return false;
        }
        view = (ViewGroup)view;
        for (int i = view.getChildCount(); i > 0;)
        {
            int j = i - 1;
            i = j;
            if (canTextInput(view.getChildAt(j)))
            {
                return true;
            }
        }

        return false;
    }

    static void manageScrollIndicators(View view, View view1, View view2)
    {
        boolean flag = false;
        int i;
        if (view1 != null)
        {
            if (view.canScrollVertically(-1))
            {
                i = 0;
            } else
            {
                i = 4;
            }
            view1.setVisibility(i);
        }
        if (view2 != null)
        {
            if (view.canScrollVertically(1))
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 4;
            }
            view2.setVisibility(i);
        }
    }

    static ViewGroup resolvePanel(View view, View view1)
    {
        if (view == null)
        {
            android.view.ViewParent viewparent;
            if (view1 instanceof ViewStub)
            {
                view = ((ViewStub)view1).inflate();
            } else
            {
                view = view1;
            }
            return (ViewGroup)view;
        }
        if (view1 != null)
        {
            viewparent = view1.getParent();
            if (viewparent instanceof ViewGroup)
            {
                ((ViewGroup)viewparent).removeView(view1);
            }
        }
        if (view instanceof ViewStub)
        {
            view = ((ViewStub)view).inflate();
        }
        return (ViewGroup)view;
    }

    public final void setButton(int i, CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener, Message message, Drawable drawable)
    {
        if (onclicklistener != null)
        {
            message = mHandler.obtainMessage(i, onclicklistener);
        }
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Button does not exist");

        case -1: 
            mButtonPositiveText = charsequence;
            mButtonPositiveMessage = message;
            mButtonPositiveIcon = drawable;
            return;

        case -2: 
            mButtonNegativeText = charsequence;
            mButtonNegativeMessage = message;
            mButtonNegativeIcon = drawable;
            return;

        case -3: 
            mButtonNeutralText = charsequence;
            mButtonNeutralMessage = message;
            mButtonNeutralIcon = drawable;
            return;
        }
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final AlertController this$0;

        public final void onClick(View view)
        {
            if (view == mButtonPositive && mButtonPositiveMessage != null)
            {
                view = Message.obtain(mButtonPositiveMessage);
            } else
            if (view == mButtonNegative && mButtonNegativeMessage != null)
            {
                view = Message.obtain(mButtonNegativeMessage);
            } else
            if (view == mButtonNeutral && mButtonNeutralMessage != null)
            {
                view = Message.obtain(mButtonNeutralMessage);
            } else
            {
                view = null;
            }
            if (view != null)
            {
                view.sendToTarget();
            }
            mHandler.obtainMessage(1, mDialog).sendToTarget();
        }

        _cls1()
        {
            this$0 = AlertController.this;
            super();
        }
    }


    private class ButtonHandler extends Handler
    {

        private WeakReference mDialog;

        public final void handleMessage(Message message)
        {
            switch (message.what)
            {
            case 0: // '\0'
            default:
                return;

            case -3: 
            case -2: 
            case -1: 
                ((android.content.DialogInterface.OnClickListener)message.obj).onClick((DialogInterface)mDialog.get(), message.what);
                return;

            case 1: // '\001'
                ((DialogInterface)message.obj).dismiss();
                break;
            }
        }

        public ButtonHandler(DialogInterface dialoginterface)
        {
            mDialog = new WeakReference(dialoginterface);
        }
    }

}
