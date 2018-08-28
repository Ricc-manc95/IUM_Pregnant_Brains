// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

// Referenced classes of package android.support.v7.app:
//            AppCompatDialog, AlertController

public final class AlertDialog extends AppCompatDialog
    implements DialogInterface
{

    public final AlertController mAlert = new AlertController(getContext(), this, getWindow());

    protected AlertDialog(Context context, int i)
    {
        super(context, resolveDialogTheme(context, i));
    }

    static int resolveDialogTheme(Context context, int i)
    {
        if (i >>> 24 > 0)
        {
            return i;
        } else
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(0x7f0100ce, typedvalue, true);
            return typedvalue.resourceId;
        }
    }

    public final Button getButton(int i)
    {
        AlertController alertcontroller = mAlert;
        switch (i)
        {
        default:
            return null;

        case -1: 
            return alertcontroller.mButtonPositive;

        case -2: 
            return alertcontroller.mButtonNegative;

        case -3: 
            return alertcontroller.mButtonNeutral;
        }
    }

    protected final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        AlertController alertcontroller = mAlert;
        int i = alertcontroller.mButtonPanelSideLayout;
        i = alertcontroller.mAlertDialogLayout;
        alertcontroller.mDialog.setContentView(i);
        bundle = alertcontroller.mWindow.findViewById(0x7f1000d7);
        Object obj1 = bundle.findViewById(0x7f1000e0);
        Object obj2 = bundle.findViewById(0x7f1000d8);
        Object obj3 = bundle.findViewById(0x7f1000d5);
        Object obj = (ViewGroup)bundle.findViewById(0x7f1000de);
        Object obj4;
        View view;
        byte byte0;
        boolean flag;
        if (alertcontroller.mView != null)
        {
            bundle = alertcontroller.mView;
        } else
        if (alertcontroller.mViewLayoutResId != 0)
        {
            bundle = LayoutInflater.from(alertcontroller.mContext).inflate(alertcontroller.mViewLayoutResId, ((ViewGroup) (obj)), false);
        } else
        {
            bundle = null;
        }
        if (bundle != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || !AlertController.canTextInput(bundle))
        {
            alertcontroller.mWindow.setFlags(0x20000, 0x20000);
        }
        if (i != 0)
        {
            FrameLayout framelayout = (FrameLayout)alertcontroller.mWindow.findViewById(0x7f1000df);
            framelayout.addView(bundle, new android.view.ViewGroup.LayoutParams(-1, -1));
            if (alertcontroller.mViewSpacingSpecified)
            {
                framelayout.setPadding(alertcontroller.mViewSpacingLeft, alertcontroller.mViewSpacingTop, alertcontroller.mViewSpacingRight, alertcontroller.mViewSpacingBottom);
            }
            if (alertcontroller.mListView != null)
            {
                ((android.support.v7.widget.LinearLayoutCompat.LayoutParams)((ViewGroup) (obj)).getLayoutParams()).weight = 0.0F;
            }
        } else
        {
            ((ViewGroup) (obj)).setVisibility(8);
        }
        view = ((ViewGroup) (obj)).findViewById(0x7f1000e0);
        obj4 = ((ViewGroup) (obj)).findViewById(0x7f1000d8);
        bundle = ((ViewGroup) (obj)).findViewById(0x7f1000d5);
        obj1 = AlertController.resolvePanel(view, ((View) (obj1)));
        obj2 = AlertController.resolvePanel(((View) (obj4)), ((View) (obj2)));
        bundle = AlertController.resolvePanel(bundle, ((View) (obj3)));
        alertcontroller.mScrollView = (NestedScrollView)alertcontroller.mWindow.findViewById(0x7f1000da);
        alertcontroller.mScrollView.setFocusable(false);
        alertcontroller.mScrollView.setNestedScrollingEnabled(false);
        alertcontroller.mMessageView = (TextView)((ViewGroup) (obj2)).findViewById(0x102000b);
        if (alertcontroller.mMessageView != null)
        {
            if (alertcontroller.mMessage != null)
            {
                alertcontroller.mMessageView.setText(alertcontroller.mMessage);
            } else
            {
                alertcontroller.mMessageView.setVisibility(8);
                alertcontroller.mScrollView.removeView(alertcontroller.mMessageView);
                if (alertcontroller.mListView != null)
                {
                    ViewGroup viewgroup = (ViewGroup)alertcontroller.mScrollView.getParent();
                    i = viewgroup.indexOfChild(alertcontroller.mScrollView);
                    viewgroup.removeViewAt(i);
                    viewgroup.addView(alertcontroller.mListView, i, new android.view.ViewGroup.LayoutParams(-1, -1));
                } else
                {
                    ((ViewGroup) (obj2)).setVisibility(8);
                }
            }
        }
        i = 0;
        alertcontroller.mButtonPositive = (Button)bundle.findViewById(0x1020019);
        alertcontroller.mButtonPositive.setOnClickListener(alertcontroller.mButtonHandler);
        if (TextUtils.isEmpty(alertcontroller.mButtonPositiveText) && alertcontroller.mButtonPositiveIcon == null)
        {
            alertcontroller.mButtonPositive.setVisibility(8);
        } else
        {
            alertcontroller.mButtonPositive.setText(alertcontroller.mButtonPositiveText);
            if (alertcontroller.mButtonPositiveIcon != null)
            {
                alertcontroller.mButtonPositiveIcon.setBounds(0, 0, alertcontroller.mButtonIconDimen, alertcontroller.mButtonIconDimen);
                alertcontroller.mButtonPositive.setCompoundDrawables(alertcontroller.mButtonPositiveIcon, null, null, null);
            }
            alertcontroller.mButtonPositive.setVisibility(0);
            i = 1;
        }
        alertcontroller.mButtonNegative = (Button)bundle.findViewById(0x102001a);
        alertcontroller.mButtonNegative.setOnClickListener(alertcontroller.mButtonHandler);
        if (TextUtils.isEmpty(alertcontroller.mButtonNegativeText) && alertcontroller.mButtonNegativeIcon == null)
        {
            alertcontroller.mButtonNegative.setVisibility(8);
        } else
        {
            alertcontroller.mButtonNegative.setText(alertcontroller.mButtonNegativeText);
            if (alertcontroller.mButtonNegativeIcon != null)
            {
                alertcontroller.mButtonNegativeIcon.setBounds(0, 0, alertcontroller.mButtonIconDimen, alertcontroller.mButtonIconDimen);
                alertcontroller.mButtonNegative.setCompoundDrawables(alertcontroller.mButtonNegativeIcon, null, null, null);
            }
            alertcontroller.mButtonNegative.setVisibility(0);
            i |= 2;
        }
        alertcontroller.mButtonNeutral = (Button)bundle.findViewById(0x102001b);
        alertcontroller.mButtonNeutral.setOnClickListener(alertcontroller.mButtonHandler);
        if (TextUtils.isEmpty(alertcontroller.mButtonNeutralText) && alertcontroller.mButtonNeutralIcon == null)
        {
            alertcontroller.mButtonNeutral.setVisibility(8);
        } else
        {
            alertcontroller.mButtonNeutral.setText(alertcontroller.mButtonNeutralText);
            if (alertcontroller.mButtonPositiveIcon != null)
            {
                alertcontroller.mButtonPositiveIcon.setBounds(0, 0, alertcontroller.mButtonIconDimen, alertcontroller.mButtonIconDimen);
                alertcontroller.mButtonPositive.setCompoundDrawables(alertcontroller.mButtonPositiveIcon, null, null, null);
            }
            alertcontroller.mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        obj3 = alertcontroller.mContext;
        obj4 = new TypedValue();
        ((Context) (obj3)).getTheme().resolveAttribute(0x7f0100cd, ((TypedValue) (obj4)), true);
        if (((TypedValue) (obj4)).data != 0)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (byte0 != 0)
        {
            if (i == 1)
            {
                Button button = alertcontroller.mButtonPositive;
                android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)button.getLayoutParams();
                layoutparams1.gravity = 1;
                layoutparams1.weight = 0.5F;
                button.setLayoutParams(layoutparams1);
            } else
            if (i == 2)
            {
                Button button1 = alertcontroller.mButtonNegative;
                android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)button1.getLayoutParams();
                layoutparams2.gravity = 1;
                layoutparams2.weight = 0.5F;
                button1.setLayoutParams(layoutparams2);
            } else
            if (i == 4)
            {
                Button button2 = alertcontroller.mButtonNeutral;
                android.widget.LinearLayout.LayoutParams layoutparams3 = (android.widget.LinearLayout.LayoutParams)button2.getLayoutParams();
                layoutparams3.gravity = 1;
                layoutparams3.weight = 0.5F;
                button2.setLayoutParams(layoutparams3);
            }
        }
        if (i != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            bundle.setVisibility(8);
        }
        if (alertcontroller.mCustomTitleView != null)
        {
            android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-1, -2);
            ((ViewGroup) (obj1)).addView(alertcontroller.mCustomTitleView, 0, layoutparams);
            alertcontroller.mWindow.findViewById(0x7f1000e1).setVisibility(8);
        } else
        {
            alertcontroller.mIconView = (ImageView)alertcontroller.mWindow.findViewById(0x1020006);
            if (!TextUtils.isEmpty(alertcontroller.mTitle))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0 && alertcontroller.mShowTitle)
            {
                alertcontroller.mTitleView = (TextView)alertcontroller.mWindow.findViewById(0x7f1000e2);
                alertcontroller.mTitleView.setText(alertcontroller.mTitle);
                if (alertcontroller.mIconId != 0)
                {
                    alertcontroller.mIconView.setImageResource(alertcontroller.mIconId);
                } else
                if (alertcontroller.mIcon != null)
                {
                    alertcontroller.mIconView.setImageDrawable(alertcontroller.mIcon);
                } else
                {
                    alertcontroller.mTitleView.setPadding(alertcontroller.mIconView.getPaddingLeft(), alertcontroller.mIconView.getPaddingTop(), alertcontroller.mIconView.getPaddingRight(), alertcontroller.mIconView.getPaddingBottom());
                    alertcontroller.mIconView.setVisibility(8);
                }
            } else
            {
                alertcontroller.mWindow.findViewById(0x7f1000e1).setVisibility(8);
                alertcontroller.mIconView.setVisibility(8);
                ((ViewGroup) (obj1)).setVisibility(8);
            }
        }
        if (obj != null && ((ViewGroup) (obj)).getVisibility() != 8)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (obj1 != null && ((ViewGroup) (obj1)).getVisibility() != 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (bundle != null && bundle.getVisibility() != 8)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (byte0 == 0 && obj2 != null)
        {
            bundle = ((ViewGroup) (obj2)).findViewById(0x7f1000dc);
            if (bundle != null)
            {
                bundle.setVisibility(0);
            }
        }
        if (flag)
        {
            if (alertcontroller.mScrollView != null)
            {
                alertcontroller.mScrollView.setClipToPadding(true);
            }
            bundle = null;
            if (alertcontroller.mMessage != null || alertcontroller.mListView != null)
            {
                bundle = ((ViewGroup) (obj1)).findViewById(0x7f1000e3);
            }
            if (bundle != null)
            {
                bundle.setVisibility(0);
            }
        } else
        if (obj2 != null)
        {
            bundle = ((ViewGroup) (obj2)).findViewById(0x7f1000db);
            if (bundle != null)
            {
                bundle.setVisibility(0);
            }
        }
        if (alertcontroller.mListView instanceof AlertController.RecycleListView)
        {
            bundle = (AlertController.RecycleListView)alertcontroller.mListView;
            if (byte0 == 0 || !flag)
            {
                int l = bundle.getPaddingLeft();
                int j;
                int k;
                int i1;
                if (flag)
                {
                    j = bundle.getPaddingTop();
                } else
                {
                    j = ((AlertController.RecycleListView) (bundle)).mPaddingTopNoTitle;
                }
                i1 = bundle.getPaddingRight();
                if (byte0 != 0)
                {
                    k = bundle.getPaddingBottom();
                } else
                {
                    k = ((AlertController.RecycleListView) (bundle)).mPaddingBottomNoButtons;
                }
                bundle.setPadding(l, j, i1, k);
            }
        }
        if (i == 0)
        {
            if (alertcontroller.mListView != null)
            {
                bundle = alertcontroller.mListView;
            } else
            {
                bundle = alertcontroller.mScrollView;
            }
            if (bundle != null)
            {
                if (flag)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (byte0 != 0)
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 0;
                }
                i |= byte0;
                obj = alertcontroller.mWindow.findViewById(0x7f1000d9);
                obj1 = alertcontroller.mWindow.findViewById(0x7f1000dd);
                if (android.os.Build.VERSION.SDK_INT >= 23)
                {
                    ViewCompat.setScrollIndicators(bundle, i, 3);
                    if (obj != null)
                    {
                        ((ViewGroup) (obj2)).removeView(((View) (obj)));
                    }
                    if (obj1 != null)
                    {
                        ((ViewGroup) (obj2)).removeView(((View) (obj1)));
                    }
                } else
                {
                    bundle = ((Bundle) (obj));
                    if (obj != null)
                    {
                        bundle = ((Bundle) (obj));
                        if ((i & 1) == 0)
                        {
                            ((ViewGroup) (obj2)).removeView(((View) (obj)));
                            bundle = null;
                        }
                    }
                    obj = obj1;
                    if (obj1 != null)
                    {
                        obj = obj1;
                        if ((i & 2) == 0)
                        {
                            ((ViewGroup) (obj2)).removeView(((View) (obj1)));
                            obj = null;
                        }
                    }
                    if (bundle != null || obj != null)
                    {
                        if (alertcontroller.mMessage != null)
                        {
                            alertcontroller.mScrollView.mOnScrollChangeListener = new AlertController._cls2(alertcontroller, bundle, ((View) (obj)));
                            alertcontroller.mScrollView.post(new AlertController._cls3(alertcontroller, bundle, ((View) (obj))));
                        } else
                        if (alertcontroller.mListView != null)
                        {
                            alertcontroller.mListView.setOnScrollListener(new AlertController._cls4(alertcontroller, bundle, ((View) (obj))));
                            alertcontroller.mListView.post(new AlertController._cls5(alertcontroller, bundle, ((View) (obj))));
                        } else
                        {
                            if (bundle != null)
                            {
                                ((ViewGroup) (obj2)).removeView(bundle);
                            }
                            if (obj != null)
                            {
                                ((ViewGroup) (obj2)).removeView(((View) (obj)));
                            }
                        }
                    }
                }
            }
        }
        bundle = alertcontroller.mListView;
        if (bundle != null && alertcontroller.mAdapter != null)
        {
            bundle.setAdapter(alertcontroller.mAdapter);
            i = alertcontroller.mCheckedItem;
            if (i >= 0)
            {
                bundle.setItemChecked(i, true);
                bundle.setSelection(i);
            }
        }
    }

    public final boolean onKeyDown(int i, KeyEvent keyevent)
    {
        AlertController alertcontroller = mAlert;
        boolean flag;
        if (alertcontroller.mScrollView != null && alertcontroller.mScrollView.executeKeyEvent(keyevent))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public final boolean onKeyUp(int i, KeyEvent keyevent)
    {
        AlertController alertcontroller = mAlert;
        boolean flag;
        if (alertcontroller.mScrollView != null && alertcontroller.mScrollView.executeKeyEvent(keyevent))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return true;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    public final void setTitle(CharSequence charsequence)
    {
        super.setTitle(charsequence);
        AlertController alertcontroller = mAlert;
        alertcontroller.mTitle = charsequence;
        if (alertcontroller.mTitleView != null)
        {
            alertcontroller.mTitleView.setText(charsequence);
        }
    }
}
