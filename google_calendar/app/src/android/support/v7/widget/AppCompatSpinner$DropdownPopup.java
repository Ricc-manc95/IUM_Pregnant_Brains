// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow, AppCompatSpinner, ViewUtils, DropDownListView

final class _cls1 extends ListPopupWindow
{

    public ListAdapter mAdapter;
    public CharSequence mHintText;
    public final Rect mVisibleRect = new Rect();
    public final AppCompatSpinner this$0;

    final void computeContentWidth()
    {
        Drawable drawable = super.mPopup.getBackground();
        int i;
        int l;
        int i1;
        int j1;
        if (drawable != null)
        {
            drawable.getPadding(mTempRect);
            int j;
            int k;
            if (ViewUtils.isLayoutRtl(AppCompatSpinner.this))
            {
                i = mTempRect.right;
            } else
            {
                i = -mTempRect.left;
            }
        } else
        {
            Rect rect = mTempRect;
            mTempRect.right = 0;
            rect.left = 0;
            i = 0;
        }
        l = getPaddingLeft();
        i1 = getPaddingRight();
        j1 = getWidth();
        if (mDropDownWidth == -2)
        {
            j = compatMeasureContentWidth((SpinnerAdapter)mAdapter, super.mPopup.getBackground());
            k = getContext().getResources().getDisplayMetrics().widthPixels - mTempRect.left - mTempRect.right;
            if (j > k)
            {
                j = k;
            }
            setContentWidth(Math.max(j, j1 - l - i1));
        } else
        if (mDropDownWidth == -1)
        {
            setContentWidth(j1 - l - i1);
        } else
        {
            setContentWidth(mDropDownWidth);
        }
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this))
        {
            i = (j1 - i1 - super.mDropDownWidth) + i;
        } else
        {
            i += l;
        }
        super.mDropDownHorizontalOffset = i;
    }

    public final void setAdapter(ListAdapter listadapter)
    {
        super.setAdapter(listadapter);
        mAdapter = listadapter;
    }

    public final void show()
    {
        boolean flag = super.mPopup.isShowing();
        computeContentWidth();
        super.mPopup.setInputMethodMode(2);
        super.show();
        super.mDropDownList.setChoiceMode(1);
        int i = getSelectedItemPosition();
        Object obj = super.mDropDownList;
        if (super.mPopup.isShowing() && obj != null)
        {
            obj.mListSelectionHidden = false;
            ((DropDownListView) (obj)).setSelection(i);
            if (((DropDownListView) (obj)).getChoiceMode() != 0)
            {
                ((DropDownListView) (obj)).setItemChecked(i, true);
            }
        }
        if (!flag)
        {
            if ((obj = getViewTreeObserver()) != null)
            {
                class _cls2
                    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
                {

                    private final AppCompatSpinner.DropdownPopup this$1;

                    public final void onGlobalLayout()
                    {
                        AppCompatSpinner.DropdownPopup dropdownpopup = AppCompatSpinner.DropdownPopup.this;
                        AppCompatSpinner appcompatspinner = this$0;
                        boolean flag1;
                        if (ViewCompat.isAttachedToWindow(appcompatspinner) && appcompatspinner.getGlobalVisibleRect(dropdownpopup.mVisibleRect))
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (!flag1)
                        {
                            dismiss();
                            return;
                        } else
                        {
                            computeContentWidth();
                            show();
                            return;
                        }
                    }

            _cls2()
            {
                this$1 = AppCompatSpinner.DropdownPopup.this;
                super();
            }
                }

                final _cls2 layoutListener = new _cls2();
                ((ViewTreeObserver) (obj)).addOnGlobalLayoutListener(layoutListener);
                class _cls3
                    implements android.widget.PopupWindow.OnDismissListener
                {

                    private final AppCompatSpinner.DropdownPopup this$1;
                    private final android.view.ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

                    public final void onDismiss()
                    {
                        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
                        if (viewtreeobserver != null)
                        {
                            viewtreeobserver.removeGlobalOnLayoutListener(layoutListener);
                        }
                    }

            _cls3()
            {
                this$1 = AppCompatSpinner.DropdownPopup.this;
                layoutListener = ongloballayoutlistener;
                super();
            }
                }

                obj = new _cls3();
                super.mPopup.setOnDismissListener(((android.widget.r) (obj)));
                return;
            }
        }
    }


    public _cls1.this._cls1(Context context, AttributeSet attributeset, int i)
    {
        this$0 = AppCompatSpinner.this;
        super(context, attributeset, i);
        super.mDropDownAnchorView = AppCompatSpinner.this;
        super.mModal = true;
        super.mPopup.setFocusable(true);
        super.mPromptPosition = 0;
        class _cls1
            implements android.widget.AdapterView.OnItemClickListener
        {

            private final AppCompatSpinner.DropdownPopup this$1;

            public final void onItemClick(AdapterView adapterview, View view, int j, long l)
            {
                setSelection(j);
                if (getOnItemClickListener() != null)
                {
                    performItemClick(view, j, mAdapter.getItemId(j));
                }
                dismiss();
            }

            _cls1(AppCompatSpinner appcompatspinner)
            {
                this$1 = AppCompatSpinner.DropdownPopup.this;
                super();
            }
        }

        super.mItemClickListener = new _cls1(AppCompatSpinner.this);
    }
}
