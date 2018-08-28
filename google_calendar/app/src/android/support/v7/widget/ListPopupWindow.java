// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            AppCompatPopupWindow, DropDownListView

public class ListPopupWindow
    implements ShowableListMenu
{

    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    public View mDropDownAnchorView;
    public int mDropDownGravity;
    private int mDropDownHeight;
    public int mDropDownHorizontalOffset;
    public DropDownListView mDropDownList;
    public int mDropDownVerticalOffset;
    public boolean mDropDownVerticalOffsetSet;
    public int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    public Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    public final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    public android.widget.AdapterView.OnItemClickListener mItemClickListener;
    public int mListItemExpandMaximum;
    public boolean mModal;
    private DataSetObserver mObserver;
    public boolean mOverlapAnchor;
    public boolean mOverlapAnchorSet;
    public PopupWindow mPopup;
    public int mPromptPosition;
    public final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    public ListPopupWindow(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        mDropDownHeight = -2;
        mDropDownWidth = -2;
        mDropDownWindowLayoutType = 1002;
        mDropDownGravity = 0;
        mDropDownAlwaysVisible = false;
        mForceIgnoreOutsideTouch = false;
        mListItemExpandMaximum = 0x7fffffff;
        mPromptPosition = 0;
        mResizePopupRunnable = new ResizePopupRunnable();
        mTouchInterceptor = new PopupTouchInterceptor();
        mScrollListener = new PopupScrollListener();
        mHideSelector = new ListSelectorHider();
        mTempRect = new Rect();
        mContext = context;
        mHandler = new Handler(context.getMainLooper());
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ListPopupWindow, i, j);
        mDropDownHorizontalOffset = typedarray.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        mDropDownVerticalOffset = typedarray.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (mDropDownVerticalOffset != 0)
        {
            mDropDownVerticalOffsetSet = true;
        }
        typedarray.recycle();
        mPopup = new AppCompatPopupWindow(context, attributeset, i, j);
        mPopup.setInputMethodMode(1);
    }

    private final int getMaxAvailableHeight(View view, int i, boolean flag)
    {
        if (sGetMaxAvailableHeightMethod == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        int j = ((Integer)sGetMaxAvailableHeightMethod.invoke(mPopup, new Object[] {
            view, Integer.valueOf(i), Boolean.valueOf(flag)
        })).intValue();
        return j;
        Exception exception;
        exception;
        return mPopup.getMaxAvailableHeight(view, i);
    }

    DropDownListView createDropDownListView(Context context, boolean flag)
    {
        return new DropDownListView(context, flag);
    }

    public final void dismiss()
    {
        mPopup.dismiss();
        mPopup.setContentView(null);
        mDropDownList = null;
        mHandler.removeCallbacks(mResizePopupRunnable);
    }

    public final ListView getListView()
    {
        return mDropDownList;
    }

    public final boolean isShowing()
    {
        return mPopup.isShowing();
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if (mObserver != null) goto _L2; else goto _L1
_L1:
        mObserver = new PopupDataSetObserver();
_L4:
        mAdapter = listadapter;
        if (listadapter != null)
        {
            listadapter.registerDataSetObserver(mObserver);
        }
        if (mDropDownList != null)
        {
            mDropDownList.setAdapter(mAdapter);
        }
        return;
_L2:
        if (mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mObserver);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setContentWidth(int i)
    {
        Drawable drawable = mPopup.getBackground();
        if (drawable != null)
        {
            drawable.getPadding(mTempRect);
            mDropDownWidth = mTempRect.left + mTempRect.right + i;
            return;
        } else
        {
            mDropDownWidth = i;
            return;
        }
    }

    public void show()
    {
        int i;
        int l;
        int i1;
        i1 = 0;
        boolean flag;
        if (mDropDownList == null)
        {
            Object obj = mContext;
            new _cls2();
            if (!mModal)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mDropDownList = createDropDownListView(((Context) (obj)), flag);
            mDropDownList.setAdapter(mAdapter);
            mDropDownList.setOnItemClickListener(mItemClickListener);
            mDropDownList.setFocusable(true);
            mDropDownList.setFocusableInTouchMode(true);
            mDropDownList.setOnItemSelectedListener(new _cls3());
            mDropDownList.setOnScrollListener(mScrollListener);
            obj = mDropDownList;
            mPopup.setContentView(((View) (obj)));
        } else
        {
            mPopup.getContentView();
        }
        obj = mPopup.getBackground();
        if (obj != null)
        {
            ((Drawable) (obj)).getPadding(mTempRect);
            i = mTempRect.top + mTempRect.bottom;
            if (!mDropDownVerticalOffsetSet)
            {
                mDropDownVerticalOffset = -mTempRect.top;
            }
        } else
        {
            mTempRect.setEmpty();
            i = 0;
        }
        if (mPopup.getInputMethodMode() == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = getMaxAvailableHeight(mDropDownAnchorView, mDropDownVerticalOffset, flag);
        if (mDropDownHeight != -1) goto _L2; else goto _L1
_L1:
        i = l + i;
_L16:
        int j;
        if (mPopup.getInputMethodMode() == 2)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        PopupWindowCompat.setWindowLayoutType(mPopup, mDropDownWindowLayoutType);
        if (!mPopup.isShowing()) goto _L4; else goto _L3
_L3:
        if (ViewCompat.isAttachedToWindow(mDropDownAnchorView)) goto _L6; else goto _L5
_L5:
        return;
_L2:
        mDropDownWidth;
        JVM INSTR tableswitch -2 -1: default 328
    //                   -2 389
    //                   -1 429;
           goto _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_429;
_L7:
        j = android.view.View.MeasureSpec.makeMeasureSpec(mDropDownWidth, 0x40000000);
_L10:
        j = mDropDownList.measureHeightOfChildrenCompat(j, 0, -1, l, -1);
        Object obj1;
        Exception exception;
        View view;
        int k;
        int j1;
        if (j > 0)
        {
            i = mDropDownList.getPaddingTop() + mDropDownList.getPaddingBottom() + i + 0;
        } else
        {
            i = 0;
        }
        i += j;
        continue; /* Loop/switch isn't completed */
_L8:
        j = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x80000000);
          goto _L10
        j = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x40000000);
          goto _L10
_L6:
        if (mDropDownWidth == -1)
        {
            k = -1;
        } else
        if (mDropDownWidth == -2)
        {
            k = mDropDownAnchorView.getWidth();
        } else
        {
            k = mDropDownWidth;
        }
        if (mDropDownHeight != -1) goto _L12; else goto _L11
_L11:
        if (l == 0)
        {
            i = -1;
        }
        if (l != 0)
        {
            obj1 = mPopup;
            if (mDropDownWidth == -1)
            {
                l = -1;
            } else
            {
                l = 0;
            }
            ((PopupWindow) (obj1)).setWidth(l);
            mPopup.setHeight(0);
        } else
        {
            obj1 = mPopup;
            l = i1;
            if (mDropDownWidth == -1)
            {
                l = -1;
            }
            ((PopupWindow) (obj1)).setWidth(l);
            mPopup.setHeight(-1);
        }
_L13:
        mPopup.setOutsideTouchable(true);
        obj1 = mPopup;
        view = mDropDownAnchorView;
        i1 = mDropDownHorizontalOffset;
        j1 = mDropDownVerticalOffset;
        l = k;
        if (k < 0)
        {
            l = -1;
        }
        k = i;
        if (i < 0)
        {
            k = -1;
        }
        ((PopupWindow) (obj1)).update(view, i1, j1, l, k);
        return;
_L12:
        if (mDropDownHeight != -2)
        {
            i = mDropDownHeight;
        }
        if (true) goto _L13; else goto _L4
_L4:
        if (mDropDownWidth == -1)
        {
            k = -1;
        } else
        if (mDropDownWidth == -2)
        {
            k = mDropDownAnchorView.getWidth();
        } else
        {
            k = mDropDownWidth;
        }
        if (mDropDownHeight == -1)
        {
            i = -1;
        } else
        if (mDropDownHeight != -2)
        {
            i = mDropDownHeight;
        }
        mPopup.setWidth(k);
        mPopup.setHeight(i);
        if (sClipToWindowEnabledMethod != null)
        {
            try
            {
                sClipToWindowEnabledMethod.invoke(mPopup, new Object[] {
                    Boolean.valueOf(true)
                });
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception) { }
        }
        mPopup.setOutsideTouchable(true);
        mPopup.setTouchInterceptor(mTouchInterceptor);
        if (mOverlapAnchorSet)
        {
            PopupWindowCompat.setOverlapAnchor(mPopup, mOverlapAnchor);
        }
        if (sSetEpicenterBoundsMethod != null)
        {
            try
            {
                sSetEpicenterBoundsMethod.invoke(mPopup, new Object[] {
                    mEpicenterBounds
                });
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", exception);
            }
        }
        mPopup.showAsDropDown(mDropDownAnchorView, mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
        mDropDownList.setSelection(-1);
        if (!mModal || mDropDownList.isInTouchMode())
        {
            obj1 = mDropDownList;
            if (obj1 != null)
            {
                obj1.mListSelectionHidden = true;
                ((DropDownListView) (obj1)).requestLayout();
            }
        }
        if (mModal) goto _L5; else goto _L14
_L14:
        mHandler.post(mHideSelector);
        return;
        if (true) goto _L16; else goto _L15
_L15:
    }

    static 
    {
        try
        {
            sClipToWindowEnabledMethod = android/widget/PopupWindow.getDeclaredMethod("setClipToScreenEnabled", new Class[] {
                Boolean.TYPE
            });
        }
        catch (NoSuchMethodException nosuchmethodexception2) { }
        try
        {
            sGetMaxAvailableHeightMethod = android/widget/PopupWindow.getDeclaredMethod("getMaxAvailableHeight", new Class[] {
                android/view/View, Integer.TYPE, Boolean.TYPE
            });
        }
        catch (NoSuchMethodException nosuchmethodexception1) { }
        try
        {
            sSetEpicenterBoundsMethod = android/widget/PopupWindow.getDeclaredMethod("setEpicenterBounds", new Class[] {
                android/graphics/Rect
            });
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            return;
        }
    }

    private class ResizePopupRunnable
        implements Runnable
    {

        private final ListPopupWindow this$0;

        public final void run()
        {
            if (mDropDownList != null && ViewCompat.isAttachedToWindow(mDropDownList) && mDropDownList.getCount() > mDropDownList.getChildCount() && mDropDownList.getChildCount() <= mListItemExpandMaximum)
            {
                mPopup.setInputMethodMode(2);
                show();
            }
        }

        ResizePopupRunnable()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class PopupTouchInterceptor
        implements android.view.View.OnTouchListener
    {

        private final ListPopupWindow this$0;

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
            int i;
            int j;
            int k;
            i = motionevent.getAction();
            j = (int)motionevent.getX();
            k = (int)motionevent.getY();
            if (i != 0 || mPopup == null || !mPopup.isShowing() || j < 0 || j >= mPopup.getWidth() || k < 0 || k >= mPopup.getHeight()) goto _L2; else goto _L1
_L1:
            mHandler.postDelayed(mResizePopupRunnable, 250L);
_L4:
            return false;
_L2:
            if (i == 1)
            {
                mHandler.removeCallbacks(mResizePopupRunnable);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        PopupTouchInterceptor()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class PopupScrollListener
        implements android.widget.AbsListView.OnScrollListener
    {

        private final ListPopupWindow this$0;

        public final void onScroll(AbsListView abslistview, int i, int j, int k)
        {
        }

        public final void onScrollStateChanged(AbsListView abslistview, int i)
        {
            boolean flag = true;
            if (i == 1)
            {
                if (mPopup.getInputMethodMode() == 2)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (i == 0 && mPopup.getContentView() != null)
                {
                    mHandler.removeCallbacks(mResizePopupRunnable);
                    mResizePopupRunnable.run();
                }
            }
        }

        PopupScrollListener()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class ListSelectorHider
        implements Runnable
    {

        private final ListPopupWindow this$0;

        public final void run()
        {
            DropDownListView dropdownlistview = mDropDownList;
            if (dropdownlistview != null)
            {
                dropdownlistview.mListSelectionHidden = true;
                dropdownlistview.requestLayout();
            }
        }

        ListSelectorHider()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class PopupDataSetObserver extends DataSetObserver
    {

        private final ListPopupWindow this$0;

        public final void onChanged()
        {
            if (mPopup.isShowing())
            {
                show();
            }
        }

        public final void onInvalidated()
        {
            dismiss();
        }

        PopupDataSetObserver()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final ListPopupWindow this$0;

        public final void run()
        {
            View view = mDropDownAnchorView;
            if (view != null && view.getWindowToken() != null)
            {
                show();
            }
        }

        _cls2()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    private class _cls3
        implements android.widget.AdapterView.OnItemSelectedListener
    {

        private final ListPopupWindow this$0;

        public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
        {
            if (i != -1)
            {
                adapterview = mDropDownList;
                if (adapterview != null)
                {
                    adapterview.mListSelectionHidden = false;
                }
            }
        }

        public final void onNothingSelected(AdapterView adapterview)
        {
        }

        _cls3()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }

}
