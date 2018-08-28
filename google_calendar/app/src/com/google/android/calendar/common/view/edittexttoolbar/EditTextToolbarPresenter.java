// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.edittexttoolbar;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public final class EditTextToolbarPresenter
{

    public Callback callback;
    private final View clearButton;
    private final View customViewsContainer;
    public final View editContainer;
    public final EditText editText;
    public final TextWatcher queryTextWatcher = new _cls1();
    public final Button rightButton;
    public final Toolbar toolbar;

    public EditTextToolbarPresenter(Toolbar toolbar1)
    {
        toolbar = toolbar1;
        customViewsContainer = toolbar.findViewById(0x7f10018a);
        editContainer = toolbar.findViewById(0x7f10018b);
        boolean flag;
        if (customViewsContainer.getParent() == toolbar)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            editText = (EditText)editContainer.findViewById(0x7f10018c);
            editText.addTextChangedListener(queryTextWatcher);
            class .Lambda._cls0
                implements android.view.View.OnFocusChangeListener
            {

                private final EditTextToolbarPresenter arg$1;

                public final void onFocusChange(View view, boolean flag1)
                {
                    view = arg$1;
                    if (flag1 && ((EditTextToolbarPresenter) (view)).callback != null)
                    {
                        boolean flag2;
                        if (((EditTextToolbarPresenter) (view)).editContainer.getVisibility() == 0)
                        {
                            flag2 = true;
                        } else
                        {
                            flag2 = false;
                        }
                        if (flag2)
                        {
                            ((EditTextToolbarPresenter) (view)).callback.onFocus(((EditTextToolbarPresenter) (view)).editText.getText().toString());
                        }
                    }
                }

            .Lambda._cls0()
            {
                arg$1 = EditTextToolbarPresenter.this;
            }

                private class Callback
                {

                    public abstract void backPressed();

                    public abstract void onFocus(String s);

                    public abstract void onRightButtonPressed();

                    public abstract void searchStringChanged(String s);
                }

            }

            editText.setOnFocusChangeListener(new .Lambda._cls0());
            clearButton = toolbar.findViewById(0x7f10018d);
            class .Lambda._cls1
                implements android.view.View.OnClickListener
            {

                private final EditTextToolbarPresenter arg$1;

                public final void onClick(View view)
                {
                    arg$1.editText.setText("");
                }

            .Lambda._cls1()
            {
                arg$1 = EditTextToolbarPresenter.this;
            }
            }

            clearButton.setOnClickListener(new .Lambda._cls1());
            rightButton = (Button)toolbar.findViewById(0x7f10018e);
            class .Lambda._cls2
                implements android.view.View.OnClickListener
            {

                private final EditTextToolbarPresenter arg$1;

                public final void onClick(View view)
                {
                    view = arg$1;
                    if (((EditTextToolbarPresenter) (view)).callback != null)
                    {
                        ((EditTextToolbarPresenter) (view)).callback.onRightButtonPressed();
                    }
                }

            .Lambda._cls2()
            {
                arg$1 = EditTextToolbarPresenter.this;
            }
            }

            rightButton.setOnClickListener(new .Lambda._cls2());
            updateButtonVisibilities();
            toolbar1 = toolbar;
            class .Lambda._cls3
                implements android.view.View.OnClickListener
            {

                private final EditTextToolbarPresenter arg$1;

                public final void onClick(View view)
                {
                    view = arg$1;
                    if (((EditTextToolbarPresenter) (view)).callback != null)
                    {
                        ((EditTextToolbarPresenter) (view)).callback.backPressed();
                    }
                }

            .Lambda._cls3()
            {
                arg$1 = EditTextToolbarPresenter.this;
            }
            }

            .Lambda._cls3 _lcls3 = new .Lambda._cls3();
            toolbar1.ensureNavButtonView();
            toolbar1.mNavButtonView.setOnClickListener(_lcls3);
            return;
        }
    }

    public final void changeToDisplayMode(String s)
    {
        editContainer.setVisibility(8);
        toolbar.setTitle(s);
        customViewsContainer.getLayoutParams().width = -2;
        customViewsContainer.requestLayout();
    }

    public final void changeToEditMode()
    {
        editContainer.setVisibility(0);
        toolbar.setTitle("");
        customViewsContainer.getLayoutParams().width = -1;
        customViewsContainer.requestLayout();
    }

    public final void updateButtonVisibilities()
    {
        boolean flag = true;
        boolean flag1 = false;
        Object obj;
        int i;
        int j;
        if (editContainer.getVisibility() == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && !TextUtils.isEmpty(editText.getText()))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (TextUtils.isEmpty(rightButton.getText()))
        {
            flag = false;
        }
        obj = clearButton;
        if (i != 0)
        {
            j = 0;
        } else
        {
            j = 8;
        }
        ((View) (obj)).setVisibility(j);
        obj = rightButton;
        if (flag && i == 0)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 8;
        }
        ((Button) (obj)).setVisibility(i);
    }

    private class _cls1
        implements TextWatcher
    {

        private final EditTextToolbarPresenter this$0;

        public final void afterTextChanged(Editable editable)
        {
            updateButtonVisibilities();
            if (callback != null)
            {
                boolean flag;
                if (editContainer.getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    callback.searchStringChanged(editable.toString());
                }
            }
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls1()
        {
            this$0 = EditTextToolbarPresenter.this;
            super();
        }
    }

}
