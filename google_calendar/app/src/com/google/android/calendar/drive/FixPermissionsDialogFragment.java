// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.text.BidiFormatter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.android.calendar.utils.timely.TimelyUtils;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.drive:
//            PotentialFix, FixPermissionDialogState

public final class FixPermissionsDialogFragment extends DialogFragment
    implements android.content.DialogInterface.OnClickListener, android.view.View.OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener
{

    private String accountName;
    private FixPermissionDialogState dialogState;
    private View firstSpace;
    private Spinner firstSpinner;
    private View moreOptions;
    private int numFiles;
    private ArrayList potentialFixes;
    private RadioGroup radioGroup;
    private RadioButton secondButton;
    private View secondButtonDivider;
    private View secondButtonExtras;
    private Spinner secondSpinner;

    public FixPermissionsDialogFragment()
    {
    }

    static int getRoleStringId(String s)
    {
        if ("READER".equals(s))
        {
            return 0x7f130227;
        }
        if ("COMMENTER".equals(s))
        {
            return 0x7f130225;
        }
        return !"WRITER".equals(s) ? 0 : 0x7f130226;
    }

    private static Spinner setupRadioButton(final Context extraText, View view, PotentialFix potentialfix, boolean flag, int i, int j)
    {
        final Object spinner;
        Resources resources;
        final View buttonExtras;
        int k;
        int l;
        if (flag)
        {
            k = 0x7f1001ad;
            l = 0x7f1001ae;
        } else
        {
            k = 0x7f1001b1;
            l = 0x7f1001b2;
        }
        buttonExtras = (RadioButton)view.findViewById(k);
        spinner = potentialfix.optionType;
        resources = extraText.getResources();
        if ("ADD_COLLABORATORS".equals(spinner))
        {
            spinner = resources.getString(0x7f130228);
            k = 0x7f13022a;
        } else
        if ("INCREASE_PUBLIC_VISIBILITY".equals(spinner))
        {
            spinner = resources.getString(0x7f13022c);
            k = 0x7f130229;
        } else
        {
            spinner = Html.fromHtml(resources.getString(0x7f13022b, new Object[] {
                BidiFormatter.getInstance().unicodeWrap(potentialfix.domainDisplayName)
            }));
            k = 0;
        }
        buttonExtras.setText(((CharSequence) (spinner)));
        buttonExtras = view.findViewById(l);
        spinner = (Spinner)buttonExtras.findViewById(0x7f1001b5);
        extraText = new _cls1(extraText, 0x7f05001c, potentialfix.allowedRoles);
        extraText.setDropDownViewResource(0x1090009);
        ((Spinner) (spinner)).setAdapter(extraText);
        ((Spinner) (spinner)).setSelection(i);
        if (k != 0)
        {
            extraText = (TextView)buttonExtras.findViewById(0x7f1001b6);
            extraText.setText(k);
            extraText.setVisibility(0);
            view.post(new _cls2());
        }
        if (!potentialfix.fixesEverything)
        {
            extraText = (TextView)buttonExtras.findViewById(0x7f1001b7);
            extraText.setText(resources.getQuantityString(0x7f12001a, j));
            extraText.setVisibility(0);
        }
        return ((Spinner) (spinner));
    }

    private final void showSecondOption()
    {
        firstSpace.setVisibility(0);
        moreOptions.setVisibility(8);
        secondButtonDivider.setVisibility(0);
        secondButton.setVisibility(0);
        secondButtonExtras.setVisibility(0);
    }

    public final void onCheckedChanged(RadioGroup radiogroup, int i)
    {
        if (i == 0x7f1001ad)
        {
            firstSpinner.setEnabled(true);
            secondSpinner.setEnabled(false);
            return;
        }
        if (i == 0x7f1001b1)
        {
            firstSpinner.setEnabled(false);
            secondSpinner.setEnabled(true);
            return;
        } else
        {
            firstSpinner.setEnabled(false);
            secondSpinner.setEnabled(false);
            return;
        }
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == -1)
        {
            int j = radioGroup.getCheckedRadioButtonId();
            int k = firstSpinner.getSelectedItemPosition();
            int l = secondSpinner.getSelectedItemPosition();
            String s;
            Object obj;
            FixPermissionDialogState fixpermissiondialogstate;
            if (j == 0x7f1001ad)
            {
                dialoginterface = (PotentialFix)potentialFixes.get(0);
                s = (String)firstSpinner.getSelectedItem();
            } else
            if (j == 0x7f1001b1)
            {
                dialoginterface = (PotentialFix)potentialFixes.get(1);
                s = (String)secondSpinner.getSelectedItem();
            } else
            {
                s = null;
                dialoginterface = null;
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = TimelyUtils.getVersionSharedPreferences(((Context) (obj))).edit();
            if (0x7f1001b3 == j)
            {
                i = 0;
            } else
            if (0x7f1001ad == j)
            {
                i = 1;
            } else
            {
                i = 2;
            }
            ((android.content.SharedPreferences.Editor) (obj)).putInt("acl_fixer_dialog_option", i);
            ((android.content.SharedPreferences.Editor) (obj)).apply();
            fixpermissiondialogstate = new FixPermissionDialogState(j, k, l);
            obj = super.mTarget;
            if (obj instanceof Listener)
            {
                obj = (Listener)obj;
            } else
            {
                obj = null;
            }
            ((Listener) (obj))._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFCHP6ITJ55T86UT35DPQ6IOBC8PKNGEQCD9GNCO9FDHGMSPPFADQ74QBECSTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TI74QBMCKNKCQBOA1IN4RB9EDPMIRRE8HKM2R3FCT9N8OBKCKTKOQJ1EPGIUTBKD5M2UGBIE9GNIJ39EDQ3MIACC5N68SJFD5I2UORFDPQ6ARJK5T1MURJKCLS78EQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(dialoginterface, s, fixpermissiondialogstate, potentialFixes, numFiles, accountName);
        }
    }

    public final void onClick(View view)
    {
        Object obj = null;
        int i = view.getId();
        if (i == 0x7f1001af)
        {
            showSecondOption();
        } else
        if (i == 0x7f1001b4)
        {
            String s;
            if (super.mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)super.mHost.mActivity;
            }
            s = view.getResources().getString(0x7f13007e);
            if (super.mHost == null)
            {
                view = obj;
            } else
            {
                view = (FragmentActivity)super.mHost.mActivity;
            }
            ActivityUtils.startActivityForUrl(view, s, "acl-fixer-dialog");
            return;
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj;
        Object obj1;
        Context context;
        Object obj2;
        int i;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new android.app.AlertDialog.Builder(((Context) (obj)));
        context = ((android.app.AlertDialog.Builder) (obj)).getContext();
        obj2 = LayoutInflater.from(context);
        obj1 = getArguments();
        dialogState = (FixPermissionDialogState)((Bundle) (obj1)).getParcelable("dialogState");
        potentialFixes = ((Bundle) (obj1)).getParcelableArrayList("potentialFixes");
        numFiles = ((Bundle) (obj1)).getInt("numFiles");
        accountName = ((Bundle) (obj1)).getString("accountName");
        if (bundle != null)
        {
            dialogState = null;
        }
        obj1 = ((LayoutInflater) (obj2)).inflate(0x7f050073, null);
        ((View) (obj1)).findViewById(0x7f1001b4).setOnClickListener(this);
        obj2 = ((LayoutInflater) (obj2)).inflate(0x7f050072, null);
        radioGroup = (RadioGroup)((View) (obj2)).findViewById(0x7f1001ac);
        bundle = (PotentialFix)potentialFixes.get(0);
        if (dialogState != null)
        {
            i = dialogState.firstSpinnerPosition;
        } else
        {
            i = 0;
        }
        firstSpinner = setupRadioButton(context, ((View) (obj2)), bundle, true, i, numFiles);
        secondButtonExtras = ((View) (obj2)).findViewById(0x7f1001b2);
        if (potentialFixes.size() > 1)
        {
            moreOptions = ((View) (obj2)).findViewById(0x7f1001af);
            bundle = (PotentialFix)potentialFixes.get(1);
            if (dialogState != null)
            {
                i = dialogState.secondSpinnerPosition;
            } else
            {
                i = 0;
            }
            secondSpinner = setupRadioButton(context, ((View) (obj2)), bundle, false, i, numFiles);
            secondButtonDivider = ((View) (obj2)).findViewById(0x7f1001b0);
            secondButton = (RadioButton)((View) (obj2)).findViewById(0x7f1001b1);
            secondButtonExtras.setVisibility(8);
            firstSpace = ((View) (obj2)).findViewById(0x7f1001ae).findViewById(0x7f1001b8);
            showSecondOption();
        } else
        {
            secondButtonExtras.setVisibility(8);
            secondSpinner = (Spinner)secondButtonExtras.findViewById(0x7f1001b5);
            secondSpinner.setId(0x7f10001b);
        }
        if (dialogState == null) goto _L2; else goto _L1
_L1:
        radioGroup.check(dialogState.checkedRadioButtonId);
_L4:
        secondSpinner.setId(0x7f10001b);
        radioGroup.setOnCheckedChangeListener(this);
        onCheckedChanged(radioGroup, radioGroup.getCheckedRadioButtonId());
        ((RadioButton)((View) (obj2)).findViewById(0x7f1001b3)).setText(0x7f13022f);
        return ((android.app.AlertDialog.Builder) (obj)).setCustomTitle(((View) (obj1))).setView(((View) (obj2))).setPositiveButton(0x7f130438, this).setNegativeButton(0x7f130100, null).create();
_L2:
        int j;
        int k;
        int l;
        if (potentialFixes.size() > 1)
        {
            k = 2;
        } else
        {
            k = 1;
        }
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        l = TimelyUtils.getVersionSharedPreferences(bundle).getInt("acl_fixer_dialog_option", k);
        j = l;
        if (l != 1)
        {
            j = l;
            if (l != 2)
            {
                j = l;
                if (l != 0)
                {
                    j = k;
                }
            }
        }
        k = j;
        if (j == 2)
        {
            k = j;
            if (potentialFixes.size() <= 1)
            {
                k = 1;
            }
        }
        if (k != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = 0x7f1001b3;
_L5:
        radioGroup.check(j);
        if (true) goto _L4; else goto _L3
_L3:
        if (1 == k)
        {
            j = 0x7f1001ad;
        } else
        {
            j = 0x7f1001b1;
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    private class _cls1 extends ArrayAdapter
    {

        public final View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            view = (TextView)super.getDropDownView(i, view, viewgroup);
            view.setText(FixPermissionsDialogFragment.getRoleStringId((String)getItem(i)));
            return view;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            view = (TextView)super.getView(i, view, viewgroup);
            view.setText(FixPermissionsDialogFragment.getRoleStringId((String)getItem(i)));
            return view;
        }

        _cls1(Context context, int i, List list)
        {
            super(context, i, list);
        }
    }


    private class _cls2
        implements Runnable
    {

        private final View val$buttonExtras;
        private final TextView val$extraText;
        private final Spinner val$spinner;

        public final void run()
        {
            Rect rect = new Rect();
            Rect rect1 = new Rect();
            spinner.getHitRect(rect);
            extraText.getHitRect(rect1);
            rect.bottom = rect1.bottom;
            buttonExtras.setTouchDelegate(new TouchDelegate(rect, spinner));
        }

        _cls2()
        {
            spinner = spinner1;
            extraText = textview;
            buttonExtras = view;
            super();
        }
    }


    private class Listener
    {

        public abstract void showOutsideDomainWarningDialog$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFCHP6ITJ55T86UT35DPQ6IOBC8PKNGEQCD9GNCO9FDHGMSPPFADQ74QBECSTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM6OBCCLN68OBI5TI74QBMCKNKCQBOA1IN4RB9EDPMIRRE8HKM2R3FCT9N8OBKCKTKOQJ1EPGIUTBKD5M2UGBIE9GNIJ39EDQ3MIACC5N68SJFD5I2UORFDPQ6ARJK5T1MURJKCLS78EQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(PotentialFix potentialfix, String s, FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s1);
    }

}
