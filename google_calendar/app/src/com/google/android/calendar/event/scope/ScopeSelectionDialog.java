// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScopeSelectionDialog extends DialogFragment
{
    public static abstract class Config
        implements Parcelable
    {

        public abstract Bundle additionalArguments();

        abstract int positiveButton();

        abstract List scopes();

        abstract int title();

        public Config()
        {
        }
    }

    public static interface OnScopeSelectedCallback
    {

        public abstract void onScopeSelected(int i, Config config);
    }

    public static abstract class Scope
        implements Parcelable
    {

        abstract int description();

        public abstract int value();

        public Scope()
        {
        }
    }


    public int checkedItem;

    public ScopeSelectionDialog()
    {
        checkedItem = 0;
    }

    public static ScopeSelectionDialog newInstance(Fragment fragment, Config config)
    {
        ScopeSelectionDialog scopeselectiondialog = new ScopeSelectionDialog();
        scopeselectiondialog.setTargetFragment(fragment, 0);
        fragment = new Bundle();
        fragment.putParcelable("ARGUMENT_CONFIG", config);
        scopeselectiondialog.setArguments(fragment);
        return scopeselectiondialog;
    }

    public Dialog getDialog()
    {
        return (AlertDialog)super.getDialog();
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Config config = (Config)getArguments().getParcelable("ARGUMENT_CONFIG");
        bundle = config.scopes();
        class .Lambda._cls0
            implements Function
        {

            private final ScopeSelectionDialog arg$1;

            public final Object apply(Object obj)
            {
                ScopeSelectionDialog scopeselectiondialog = arg$1;
                obj = (Scope)obj;
                return scopeselectiondialog.requireContext().getResources().getString(((Scope) (obj)).description());
            }

            .Lambda._cls0()
            {
                arg$1 = ScopeSelectionDialog.this;
            }
        }

        .Lambda._cls0 _lcls0;
        if (bundle instanceof FluentIterable)
        {
            bundle = (FluentIterable)bundle;
        } else
        {
            bundle = new com.google.common.collect.FluentIterable._cls1(bundle, bundle);
        }
        _lcls0 = new .Lambda._cls0();
        bundle = (Iterable)((FluentIterable) (bundle)).iterableDelegate.or(bundle);
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        }
        bundle = new com.google.common.collect.Iterables._cls5(bundle, _lcls0);
        class .Lambda._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            private final ScopeSelectionDialog arg$1;

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface = arg$1;
                Config config1 = (Config)dialoginterface.getArguments().getParcelable("ARGUMENT_CONFIG");
                i = ((AlertDialog)dialoginterface.getDialog()).getListView().getCheckedItemPosition();
                Scope scope = (Scope)config1.scopes().get(i);
                ((OnScopeSelectedCallback)((Fragment) (dialoginterface)).mTarget).onScopeSelected(scope.value(), config1);
            }

            .Lambda._cls1()
            {
                arg$1 = ScopeSelectionDialog.this;
            }
        }

        Object aobj[];
        if (bundle instanceof FluentIterable)
        {
            bundle = (FluentIterable)bundle;
        } else
        {
            bundle = new com.google.common.collect.FluentIterable._cls1(bundle, bundle);
        }
        bundle = (Iterable)((FluentIterable) (bundle)).iterableDelegate.or(bundle);
        aobj = (Object[])Array.newInstance(java/lang/String, 0);
        if (bundle instanceof Collection)
        {
            bundle = (Collection)bundle;
        } else
        {
            java.util.Iterator iterator = bundle.iterator();
            bundle = new ArrayList();
            Iterators.addAll(bundle, iterator);
        }
        aobj = (String[])bundle.toArray(aobj);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return (new android.app.AlertDialog.Builder(bundle)).setTitle(config.title()).setSingleChoiceItems(((CharSequence []) (aobj)), checkedItem, null).setPositiveButton(config.positiveButton(), new .Lambda._cls1()).setNegativeButton(0x1040000, null).create();
    }
}
