// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.Editable;
import android.text.Selection;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.common.view.fab.FloatingActionButton;
import com.google.android.calendar.groove.category.GrooveCategories;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveWizardFragment, BackButtonView, GrooveSubcategorySelectionFragment

public class CustomGrooveFragment extends GrooveWizardFragment
{
    public static interface Listener
    {

        public abstract void onCustomGrooveSelectionComplete(int i, String s);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/CustomGrooveFragment);
    private BackButtonView backArrow;
    private int categoryId;
    public EditText editText;
    public FloatingActionButton fab;
    private String question;
    private LinearLayout subcategoryContainer;
    public int type;

    public CustomGrooveFragment()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        type = getArguments().getInt("GROOVE_TYPE_KEY");
        categoryId = type & 0xff00;
        question = getArguments().getString("CUSTOM_QUESTION");
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        bundle = layoutinflater.inflate(0x7f050036, viewgroup, false);
        subcategoryContainer = (LinearLayout)bundle.findViewById(0x7f100147);
        mFrame = (ViewGroup)bundle.findViewById(0x7f100143);
        backArrow = (BackButtonView)bundle.findViewById(0x7f10011e);
        fab = (FloatingActionButton)bundle.findViewById(0x7f100146);
        editText = (EditText)bundle.findViewById(0x7f100047);
        bundle.findViewById(0x7f10013e).setFitsSystemWindows(true);
        adjustCardUi();
        viewgroup = question;
        layoutinflater = viewgroup;
        if (viewgroup == null)
        {
            layoutinflater = requireContext().getResources();
            if (GrooveCategories.instance == null)
            {
                GrooveCategories.instance = new GrooveCategories(layoutinflater);
            }
            layoutinflater = GrooveCategories.instance;
            int i = categoryId;
            layoutinflater = ((com.google.android.calendar.groove.category.GrooveCategories.Category)((GrooveCategories) (layoutinflater)).categories.get(i)).question;
        }
        editText.setHint(layoutinflater);
        editText.setOnEditorActionListener(new _cls3());
        editText.addTextChangedListener(new _cls4());
        backArrow.setOnClickListener(new _cls1());
        layoutinflater = requireContext().getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(layoutinflater);
        }
        layoutinflater = GrooveCategories.instance;
        int j = categoryId;
        layoutinflater = (com.google.android.calendar.groove.category.GrooveCategories.Category)((GrooveCategories) (layoutinflater)).categories.get(j);
        fab.setOnClickListener(new _cls2());
        fab.setSize(1);
        fab.hide(false);
        fab.setColor(((com.google.android.calendar.groove.category.GrooveCategories.Category) (layoutinflater)).fabColor);
        mFrame.setBackgroundColor(((com.google.android.calendar.groove.category.GrooveCategories.Category) (layoutinflater)).backgroundColor);
        layoutinflater = requireContext().getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(layoutinflater);
        }
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutinflater = new ContextThemeWrapper(layoutinflater, 0x7f140151);
        if ((type & 0xffff00ff) != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            viewgroup = GrooveCategories.getTextSuggestionsForType(type);
            if (viewgroup != null)
            {
                for (j = 0; j < viewgroup.length; j++)
                {
                    final String suggestion = viewgroup[j];
                    Button button = GrooveSubcategorySelectionFragment.createSubcategoryButton(layoutinflater, suggestion);
                    button.setOnClickListener(new _cls5());
                    subcategoryContainer.addView(button);
                }

            }
        } else
        {
            viewgroup = GrooveCategories.getSubcategoryListForCategory(type);
            for (int k = GrooveSubcategorySelectionFragment.getNumSubcategoriesToDisplay(categoryId); k < viewgroup.length; k++)
            {
                final com.google.android.calendar.groove.category.GrooveCategories.Subcategory subcategory = viewgroup[k];
                Button button1 = GrooveSubcategorySelectionFragment.createSubcategoryButton(layoutinflater, subcategory.name);
                button1.setOnClickListener(new _cls6());
                subcategoryContainer.addView(button1);
            }

        }
        setStatusBarTheme(1);
        return bundle;
    }

    public final void onPause()
    {
        super.onPause();
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        ((InputMethodManager)fragmentactivity.getSystemService("input_method")).hideSoftInputFromWindow(super.mView.getWindowToken(), 0);
    }

    public final void onResume()
    {
        super.onResume();
        editText.requestFocus();
        int i = editText.getText().length();
        Selection.setSelection(editText.getText(), i);
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        ((InputMethodManager)fragmentactivity.getSystemService("input_method")).showSoftInput(editText, 1);
    }

    final void saveSelection(int i, String s)
    {
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            setReenterTransition(createReenterTransition());
        }
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        ((Listener)fragmentactivity).onCustomGrooveSelectionComplete(i, s);
    }


    private class _cls3
        implements android.widget.TextView.OnEditorActionListener
    {

        private final CustomGrooveFragment this$0;

        public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
        {
            CustomGrooveFragment customgroovefragment = CustomGrooveFragment.this;
            keyevent = customgroovefragment.editText.getText().toString();
            if (!TextUtils.isEmpty(keyevent))
            {
                if (((Fragment) (customgroovefragment)).mHost == null)
                {
                    textview = null;
                } else
                {
                    textview = (FragmentActivity)((Fragment) (customgroovefragment)).mHost.mActivity;
                }
                if (textview instanceof Listener)
                {
                    i = customgroovefragment.type;
                    if (android.os.Build.VERSION.SDK_INT >= 22)
                    {
                        customgroovefragment.setReenterTransition(customgroovefragment.createReenterTransition());
                    }
                    if (((Fragment) (customgroovefragment)).mHost == null)
                    {
                        textview = null;
                    } else
                    {
                        textview = (FragmentActivity)((Fragment) (customgroovefragment)).mHost.mActivity;
                    }
                    ((Listener)textview).onCustomGrooveSelectionComplete(i, keyevent);
                }
            }
            return false;
        }

        _cls3()
        {
            this$0 = CustomGrooveFragment.this;
            super();
        }
    }


    private class _cls4
        implements TextWatcher
    {

        private final CustomGrooveFragment this$0;

        public final void afterTextChanged(Editable editable)
        {
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            if (!TextUtils.isEmpty(editText.getText().toString()))
            {
                charsequence = fab;
                if (((FloatingActionButton) (charsequence)).isHidden)
                {
                    AnimatorSet animatorset = new AnimatorSet();
                    animatorset.playTogether(new Animator[] {
                        ObjectAnimator.ofFloat(charsequence, "rotation", new float[] {
                            -180F, 0.0F
                        }), ObjectAnimator.ofFloat(charsequence, "scaleX", new float[] {
                            0.0F, 1.0F
                        }), ObjectAnimator.ofFloat(charsequence, "scaleY", new float[] {
                            0.0F, 1.0F
                        })
                    });
                    animatorset.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    animatorset.setDuration(100L);
                    animatorset.start();
                    charsequence.isHidden = false;
                }
                return;
            } else
            {
                fab.hide(true);
                return;
            }
        }

        _cls4()
        {
            this$0 = CustomGrooveFragment.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final CustomGrooveFragment this$0;

        public final void onClick(View view)
        {
            mFragmentManager.popBackStack();
        }

        _cls1()
        {
            this$0 = CustomGrooveFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final CustomGrooveFragment this$0;

        public final void onClick(View view)
        {
            CustomGrooveFragment customgroovefragment = CustomGrooveFragment.this;
            String s = customgroovefragment.editText.getText().toString();
            if (!TextUtils.isEmpty(s))
            {
                if (((Fragment) (customgroovefragment)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (customgroovefragment)).mHost.mActivity;
                }
                if (view instanceof Listener)
                {
                    int i = customgroovefragment.type;
                    if (android.os.Build.VERSION.SDK_INT >= 22)
                    {
                        customgroovefragment.setReenterTransition(customgroovefragment.createReenterTransition());
                    }
                    if (((Fragment) (customgroovefragment)).mHost == null)
                    {
                        view = null;
                    } else
                    {
                        view = (FragmentActivity)((Fragment) (customgroovefragment)).mHost.mActivity;
                    }
                    ((Listener)view).onCustomGrooveSelectionComplete(i, s);
                }
            }
        }

        _cls2()
        {
            this$0 = CustomGrooveFragment.this;
            super();
        }
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        private final CustomGrooveFragment this$0;
        private final String val$suggestion;

        public final void onClick(View view)
        {
            editText.setText(suggestion);
            saveSelection(type, editText.getText().toString());
        }

        _cls5()
        {
            this$0 = CustomGrooveFragment.this;
            suggestion = s;
            super();
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        private final CustomGrooveFragment this$0;
        private final com.google.android.calendar.groove.category.GrooveCategories.Subcategory val$subcategory;

        public final void onClick(View view)
        {
            editText.setText(subcategory.name);
            saveSelection(subcategory.type, editText.getText().toString());
        }

        _cls6()
        {
            this$0 = CustomGrooveFragment.this;
            subcategory = subcategory1;
            super();
        }
    }

}
