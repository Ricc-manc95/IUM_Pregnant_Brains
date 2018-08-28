// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, AppCompatBackgroundHelper, ListPopupWindow, ForwardingListener

public final class AppCompatSpinner extends Spinner
{

    private static final int ATTRS_ANDROID_SPINNERMODE[] = {
        0x10102f1
    };
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    public int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    public DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    public final Rect mTempRect;

    public AppCompatSpinner(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100de);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, -1);
    }

    private AppCompatSpinner(Context context, AttributeSet attributeset, int i, int j)
    {
        this(context, attributeset, i, -1, null);
    }

    private AppCompatSpinner(Context context, AttributeSet attributeset, int i, int j, android.content.res.Resources.Theme theme)
    {
        Object obj;
        int l;
        obj = null;
        super(context, attributeset, i);
        mTempRect = new Rect();
        TintTypedArray tinttypedarray = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.Spinner, i, 0));
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        int k = android.support.v7.appcompat.R.styleable.Spinner_popupTheme;
        k = tinttypedarray.mWrapped.getResourceId(k, 0);
        Drawable drawable;
        if (k != 0)
        {
            theme = new ContextThemeWrapper(context, k);
        } else
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            theme = context;
        } else
        {
            theme = null;
        }
        mPopupContext = theme;
        if (mPopupContext == null) goto _L2; else goto _L1
_L1:
        l = j;
        if (j != -1)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        theme = context.obtainStyledAttributes(attributeset, ATTRS_ANDROID_SPINNERMODE, i, 0);
        k = j;
        if (theme.hasValue(0))
        {
            k = theme.getInt(0, 0);
        }
        l = k;
        if (theme != null)
        {
            theme.recycle();
            l = k;
        }
_L4:
        if (l == 1)
        {
            theme = new DropdownPopup(mPopupContext, attributeset, i);
            obj = mPopupContext;
            obj = new TintTypedArray(((Context) (obj)), ((Context) (obj)).obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.Spinner, i, 0));
            j = android.support.v7.appcompat.R.styleable.Spinner_android_dropDownWidth;
            mDropDownWidth = ((TintTypedArray) (obj)).mWrapped.getLayoutDimension(j, -2);
            drawable = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.Spinner_android_popupBackground);
            ((ListPopupWindow) (theme)).mPopup.setBackgroundDrawable(drawable);
            j = android.support.v7.appcompat.R.styleable.Spinner_android_prompt;
            theme.mHintText = tinttypedarray.mWrapped.getString(j);
            ((TintTypedArray) (obj)).mWrapped.recycle();
            mPopup = theme;
            mForwardingListener = new _cls1(theme);
        }
_L2:
        j = android.support.v7.appcompat.R.styleable.Spinner_android_entries;
        theme = tinttypedarray.mWrapped.getTextArray(j);
        if (theme != null)
        {
            context = new ArrayAdapter(context, 0x1090008, theme);
            context.setDropDownViewResource(0x7f05016a);
            setAdapter(context);
        }
        tinttypedarray.mWrapped.recycle();
        mPopupSet = true;
        if (mTempAdapter != null)
        {
            setAdapter(mTempAdapter);
            mTempAdapter = null;
        }
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        return;
        theme;
        theme = null;
_L7:
        l = j;
        if (theme != null)
        {
            theme.recycle();
            l = j;
        }
        if (true) goto _L4; else goto _L3
_L3:
        context;
        theme = ((android.content.res.Resources.Theme) (obj));
_L6:
        if (theme != null)
        {
            theme.recycle();
        }
        throw context;
        context;
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
          goto _L7
    }

    final int compatMeasureContentWidth(SpinnerAdapter spinneradapter, Drawable drawable)
    {
        if (spinneradapter == null)
        {
            return 0;
        }
        int i1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int j1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int i = Math.max(0, getSelectedItemPosition());
        int k1 = Math.min(spinneradapter.getCount(), i + 15);
        int j = Math.max(0, i - (15 - (k1 - i)));
        View view = null;
        int k = 0;
        i = 0;
        for (; j < k1; j++)
        {
            int l = spinneradapter.getItemViewType(j);
            if (l != i)
            {
                view = null;
                i = l;
            }
            view = spinneradapter.getView(j, view, this);
            if (view.getLayoutParams() == null)
            {
                view.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(i1, j1);
            k = Math.max(k, view.getMeasuredWidth());
        }

        if (drawable != null)
        {
            drawable.getPadding(mTempRect);
            return mTempRect.left + mTempRect.right + k;
        } else
        {
            return k;
        }
    }

    protected final void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.applySupportBackgroundTint();
        }
    }

    public final int getDropDownHorizontalOffset()
    {
        if (mPopup != null)
        {
            return ((ListPopupWindow) (mPopup)).mDropDownHorizontalOffset;
        } else
        {
            return super.getDropDownHorizontalOffset();
        }
    }

    public final int getDropDownVerticalOffset()
    {
        if (mPopup != null)
        {
            DropdownPopup dropdownpopup = mPopup;
            if (!((ListPopupWindow) (dropdownpopup)).mDropDownVerticalOffsetSet)
            {
                return 0;
            } else
            {
                return ((ListPopupWindow) (dropdownpopup)).mDropDownVerticalOffset;
            }
        } else
        {
            return super.getDropDownVerticalOffset();
        }
    }

    public final int getDropDownWidth()
    {
        if (mPopup != null)
        {
            return mDropDownWidth;
        } else
        {
            return super.getDropDownWidth();
        }
    }

    public final Drawable getPopupBackground()
    {
        if (mPopup != null)
        {
            return ((ListPopupWindow) (mPopup)).mPopup.getBackground();
        } else
        {
            return super.getPopupBackground();
        }
    }

    public final Context getPopupContext()
    {
        if (mPopup != null)
        {
            return mPopupContext;
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return super.getPopupContext();
        } else
        {
            return null;
        }
    }

    public final CharSequence getPrompt()
    {
        if (mPopup != null)
        {
            return mPopup.mHintText;
        } else
        {
            return super.getPrompt();
        }
    }

    protected final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mPopup != null && ((ListPopupWindow) (mPopup)).mPopup.isShowing())
        {
            mPopup.dismiss();
        }
    }

    protected final void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (mPopup != null && android.view.View.MeasureSpec.getMode(i) == 0x80000000)
        {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), android.view.View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
        if (mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
        {
            return true;
        } else
        {
            return super.onTouchEvent(motionevent);
        }
    }

    public final boolean performClick()
    {
        if (mPopup != null)
        {
            if (!((ListPopupWindow) (mPopup)).mPopup.isShowing())
            {
                mPopup.show();
            }
            return true;
        } else
        {
            return super.performClick();
        }
    }

    public final volatile void setAdapter(Adapter adapter)
    {
        setAdapter((SpinnerAdapter)adapter);
    }

    public final void setAdapter(SpinnerAdapter spinneradapter)
    {
        if (!mPopupSet)
        {
            mTempAdapter = spinneradapter;
        } else
        {
            super.setAdapter(spinneradapter);
            if (mPopup != null)
            {
                Context context;
                if (mPopupContext == null)
                {
                    context = getContext();
                } else
                {
                    context = mPopupContext;
                }
                mPopup.setAdapter(new DropDownAdapter(spinneradapter, context.getTheme()));
                return;
            }
        }
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if (mBackgroundTintHelper != null)
        {
            drawable = mBackgroundTintHelper;
            drawable.mBackgroundResId = -1;
            drawable.setInternalBackgroundTint(null);
            drawable.applySupportBackgroundTint();
        }
    }

    public final void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if (mBackgroundTintHelper != null)
        {
            mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    public final void setDropDownHorizontalOffset(int i)
    {
        if (mPopup != null)
        {
            mPopup.mDropDownHorizontalOffset = i;
            return;
        } else
        {
            super.setDropDownHorizontalOffset(i);
            return;
        }
    }

    public final void setDropDownVerticalOffset(int i)
    {
        if (mPopup != null)
        {
            DropdownPopup dropdownpopup = mPopup;
            dropdownpopup.mDropDownVerticalOffset = i;
            dropdownpopup.mDropDownVerticalOffsetSet = true;
            return;
        } else
        {
            super.setDropDownVerticalOffset(i);
            return;
        }
    }

    public final void setDropDownWidth(int i)
    {
        if (mPopup != null)
        {
            mDropDownWidth = i;
            return;
        } else
        {
            super.setDropDownWidth(i);
            return;
        }
    }

    public final void setPopupBackgroundDrawable(Drawable drawable)
    {
        if (mPopup != null)
        {
            ((ListPopupWindow) (mPopup)).mPopup.setBackgroundDrawable(drawable);
            return;
        } else
        {
            super.setPopupBackgroundDrawable(drawable);
            return;
        }
    }

    public final void setPopupBackgroundResource(int i)
    {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i));
    }

    public final void setPrompt(CharSequence charsequence)
    {
        if (mPopup != null)
        {
            mPopup.mHintText = charsequence;
            return;
        } else
        {
            super.setPrompt(charsequence);
            return;
        }
    }


    private class DropdownPopup extends ListPopupWindow
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

                        private final DropdownPopup this$1;

                        public final void onGlobalLayout()
                        {
                            DropdownPopup dropdownpopup = DropdownPopup.this;
                            AppCompatSpinner appcompatspinner = _fld0;
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
                    this$1 = DropdownPopup.this;
                    super();
                }
                    }

                    final _cls2 layoutListener = new _cls2();
                    ((ViewTreeObserver) (obj)).addOnGlobalLayoutListener(layoutListener);
                    class _cls3
                        implements android.widget.PopupWindow.OnDismissListener
                    {

                        private final DropdownPopup this$1;
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
                    this$1 = DropdownPopup.this;
                    layoutListener = ongloballayoutlistener;
                    super();
                }
                    }

                    obj = new _cls3();
                    super.mPopup.setOnDismissListener(((android.widget.PopupWindow.OnDismissListener) (obj)));
                    return;
                }
            }
        }


        public DropdownPopup(Context context, AttributeSet attributeset, int i)
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

                private final DropdownPopup this$1;

                public final void onItemClick(AdapterView adapterview, View view, int j, long l)
                {
                    setSelection(j);
                    if (getOnItemClickListener() != null)
                    {
                        performItemClick(view, j, mAdapter.getItemId(j));
                    }
                    dismiss();
                }

                _cls1()
                {
                    this$1 = DropdownPopup.this;
                    super();
                }
            }

            super.mItemClickListener = new _cls1();
        }
    }


    private class _cls1 extends ForwardingListener
    {

        private final AppCompatSpinner this$0;
        private final DropdownPopup val$popup;

        public final ShowableListMenu getPopup()
        {
            return popup;
        }

        public final boolean onForwardingStarted()
        {
            if (!((ListPopupWindow) (mPopup)).mPopup.isShowing())
            {
                mPopup.show();
            }
            return true;
        }

        _cls1(DropdownPopup dropdownpopup)
        {
            this$0 = AppCompatSpinner.this;
            popup = dropdownpopup;
            super(final_view);
        }
    }


    private class DropDownAdapter
        implements ListAdapter, SpinnerAdapter
    {

        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public final boolean areAllItemsEnabled()
        {
            ListAdapter listadapter = mListAdapter;
            if (listadapter != null)
            {
                return listadapter.areAllItemsEnabled();
            } else
            {
                return true;
            }
        }

        public final int getCount()
        {
            if (mAdapter == null)
            {
                return 0;
            } else
            {
                return mAdapter.getCount();
            }
        }

        public final View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            if (mAdapter == null)
            {
                return null;
            } else
            {
                return mAdapter.getDropDownView(i, view, viewgroup);
            }
        }

        public final Object getItem(int i)
        {
            if (mAdapter == null)
            {
                return null;
            } else
            {
                return mAdapter.getItem(i);
            }
        }

        public final long getItemId(int i)
        {
            if (mAdapter == null)
            {
                return -1L;
            } else
            {
                return mAdapter.getItemId(i);
            }
        }

        public final int getItemViewType(int i)
        {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            return getDropDownView(i, view, viewgroup);
        }

        public final int getViewTypeCount()
        {
            return 1;
        }

        public final boolean hasStableIds()
        {
            return mAdapter != null && mAdapter.hasStableIds();
        }

        public final boolean isEmpty()
        {
            return getCount() == 0;
        }

        public final boolean isEnabled(int i)
        {
            ListAdapter listadapter = mListAdapter;
            if (listadapter != null)
            {
                return listadapter.isEnabled(i);
            } else
            {
                return true;
            }
        }

        public final void registerDataSetObserver(DataSetObserver datasetobserver)
        {
            if (mAdapter != null)
            {
                mAdapter.registerDataSetObserver(datasetobserver);
            }
        }

        public final void unregisterDataSetObserver(DataSetObserver datasetobserver)
        {
            if (mAdapter != null)
            {
                mAdapter.unregisterDataSetObserver(datasetobserver);
            }
        }

        public DropDownAdapter(SpinnerAdapter spinneradapter, android.content.res.Resources.Theme theme)
        {
            mAdapter = spinneradapter;
            if (spinneradapter instanceof ListAdapter)
            {
                mListAdapter = (ListAdapter)spinneradapter;
            }
            if (theme != null)
            {
                if (android.os.Build.VERSION.SDK_INT >= 23 && (spinneradapter instanceof ThemedSpinnerAdapter))
                {
                    spinneradapter = (ThemedSpinnerAdapter)spinneradapter;
                    if (spinneradapter.getDropDownViewTheme() != theme)
                    {
                        spinneradapter.setDropDownViewTheme(theme);
                    }
                } else
                if (spinneradapter instanceof android.support.v7.widget.ThemedSpinnerAdapter)
                {
                    spinneradapter = (android.support.v7.widget.ThemedSpinnerAdapter)spinneradapter;
                    if (spinneradapter.getDropDownViewTheme() == null)
                    {
                        spinneradapter.setDropDownViewTheme$51662RJ4E9NMIP1FCDNMST35DPQ2USJ5ECNL4PBJDTQN4OR5ECI58Q35DLIJMAAM0();
                        return;
                    }
                }
            }
        }
    }

}
