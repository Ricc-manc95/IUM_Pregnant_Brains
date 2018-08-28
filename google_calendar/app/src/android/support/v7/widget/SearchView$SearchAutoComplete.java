// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

// Referenced classes of package android.support.v7.widget:
//            AppCompatAutoCompleteTextView, SearchView

public static class getThreshold extends AppCompatAutoCompleteTextView
{

    public boolean mHasPendingShowSoftInputRequest;
    private final Runnable mRunShowSoftInputIfNecessary;
    private int mThreshold;

    private final void setImeVisibility(boolean flag)
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService("input_method");
        if (!flag)
        {
            mHasPendingShowSoftInputRequest = false;
            removeCallbacks(mRunShowSoftInputIfNecessary);
            inputmethodmanager.hideSoftInputFromWindow(getWindowToken(), 0);
            return;
        }
        if (inputmethodmanager.isActive(this))
        {
            mHasPendingShowSoftInputRequest = false;
            removeCallbacks(mRunShowSoftInputIfNecessary);
            inputmethodmanager.showSoftInput(this, 0);
            return;
        } else
        {
            mHasPendingShowSoftInputRequest = true;
            return;
        }
    }

    public boolean enoughToFilter()
    {
        return mThreshold <= 0 || super.enoughToFilter();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        editorinfo = super.onCreateInputConnection(editorinfo);
        if (mHasPendingShowSoftInputRequest)
        {
            removeCallbacks(mRunShowSoftInputIfNecessary);
            post(mRunShowSoftInputIfNecessary);
        }
        return editorinfo;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        android.util.DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int j = configuration.screenHeightDp;
        if (i >= 960 && j >= 720 && configuration.orientation == 2)
        {
            i = 256;
        } else
        if (i >= 600 || i >= 640 && j >= 480)
        {
            i = 192;
        } else
        {
            i = 160;
        }
        setMinWidth((int)TypedValue.applyDimension(1, i, displaymetrics));
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        SearchView.onTextFocusChanged();
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            if (keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                android.view.AutoComplete autocomplete = getKeyDispatcherState();
                if (autocomplete != null)
                {
                    autocomplete.Tracking(keyevent, this);
                }
                return true;
            }
            if (keyevent.getAction() == 1)
            {
                android.view.AutoComplete autocomplete1 = getKeyDispatcherState();
                if (autocomplete1 != null)
                {
                    autocomplete1.eUpEvent(keyevent);
                }
                if (keyevent.isTracking() && !keyevent.isCanceled())
                {
                    throw new NullPointerException();
                }
            }
        }
        return super.onKeyPreIme(i, keyevent);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if (flag)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    public void performCompletion()
    {
    }

    protected void replaceText(CharSequence charsequence)
    {
    }

    public void setThreshold(int i)
    {
        super.setThreshold(i);
        mThreshold = i;
    }

    public _cls1(Context context)
    {
        this(context, null);
    }

    public <init>(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100d3);
    }

    public _cls1.this._cls0(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        class _cls1
            implements Runnable
        {

            private final SearchView.SearchAutoComplete this$0;

            public final void run()
            {
                SearchView.SearchAutoComplete searchautocomplete = SearchView.SearchAutoComplete.this;
                if (searchautocomplete.mHasPendingShowSoftInputRequest)
                {
                    ((InputMethodManager)searchautocomplete.getContext().getSystemService("input_method")).showSoftInput(searchautocomplete, 0);
                    searchautocomplete.mHasPendingShowSoftInputRequest = false;
                }
            }

            _cls1()
            {
                this$0 = SearchView.SearchAutoComplete.this;
                super();
            }
        }

        mRunShowSoftInputIfNecessary = new _cls1();
        mThreshold = getThreshold();
    }
}
