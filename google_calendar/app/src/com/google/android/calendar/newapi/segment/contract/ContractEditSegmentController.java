// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitContractModifications;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.event.CustomDurationDialog;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.groove.PreferredTimesDialog;
import com.google.android.calendar.newapi.model.HabitModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.contract:
//            ContractEditSegment, FrequencyChoice, FrequencyChoiceCreator, DurationChoiceCreator

public class ContractEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, com.google.android.calendar.event.CustomDurationDialog.OnCustomDurationSetListener, com.google.android.calendar.groove.PreferredTimesDialog.OnPreferredTimesSelectedListener, ContractEditSegment.Listener
{

    private DurationChoiceCreator durationChoiceCreator;
    private FrequencyChoiceCreator frequencyChoiceCreator;

    public ContractEditSegmentController()
    {
    }

    private final void updateDuration()
    {
        int i = ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().getDurationMinutes();
        ContractEditSegment contracteditsegment = (ContractEditSegment)super.view;
        String s = ReminderUtils.constructTimeIntervalString(requireContext().getResources(), i);
        contracteditsegment.buttonDuration.setPrimaryText(new CharSequence[] {
            s
        });
    }

    private final void updateFrequency()
    {
        Object obj = new FrequencyChoice(((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications());
        ContractEditSegment contracteditsegment = (ContractEditSegment)super.view;
        obj = GrooveUtils.getFrequencyString(frequencyChoiceCreator.resources, ((FrequencyChoice) (obj)).interval, ((FrequencyChoice) (obj)).numInstances);
        contracteditsegment.buttonFrequency.setPrimaryText(new CharSequence[] {
            obj
        });
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (ContractEditSegment)layoutinflater.inflate(0x7f0500cb, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        frequencyChoiceCreator = new FrequencyChoiceCreator(requireContext().getResources());
        durationChoiceCreator = new DurationChoiceCreator(requireContext().getResources());
        bundle = super.mFragmentManager;
        Fragment fragment = bundle.findFragmentByTag(PreferredTimesDialog.TAG);
        if (fragment != null && (fragment instanceof PreferredTimesDialog))
        {
            ((PreferredTimesDialog)fragment).listener = this;
        }
        bundle = bundle.findFragmentByTag(CustomDurationDialog.TAG);
        if (bundle != null && (bundle instanceof CustomDurationDialog))
        {
            ((CustomDurationDialog)bundle).listener = this;
        }
    }

    public final void onCustomDurationDialogCancel()
    {
    }

    public final void onCustomDurationSet(int i)
    {
        ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().setDurationMinutes(i);
        updateDuration();
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 34
    //                   1 98;
           goto _L1 _L2 _L3
_L1:
        throw new UnsupportedOperationException("Unknown request code");
_L2:
        obj = (FrequencyChoice)obj;
        ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().setInterval(((FrequencyChoice) (obj)).interval);
        ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().setNumInstancesPerInterval(((FrequencyChoice) (obj)).numInstances);
        updateFrequency();
_L11:
        return;
_L3:
        i = ((Integer)obj).intValue();
        if (i != -1) goto _L5; else goto _L4
_L4:
        int j = ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().getDurationMinutes();
        if (this == null) goto _L7; else goto _L6
_L6:
        Object obj1 = super.mFragmentManager;
        if (this == null) goto _L9; else goto _L8
_L8:
        if (super.mHost != null && super.mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L10; else goto _L9
_L9:
        i = 0;
_L14:
        if (i != 0)
        {
            obj1 = new com.google.android.calendar.event.CustomDurationDialog.Builder(j);
            ((com.google.android.calendar.event.CustomDurationDialog.Builder) (obj1)).args.putInt("max_duration_in_minutes", 600);
            ((com.google.android.calendar.event.CustomDurationDialog.Builder) (obj1)).args.putInt("max_duration_error_msg", 0x7f130147);
            ((com.google.android.calendar.event.CustomDurationDialog.Builder) (obj1)).args.putInt("min_duration_in_minutes", 1);
            ((com.google.android.calendar.event.CustomDurationDialog.Builder) (obj1)).args.putInt("min_duration_error_msg", 0x7f130249);
            obj = new CustomDurationDialog();
            ((Fragment) (obj)).setArguments(((com.google.android.calendar.event.CustomDurationDialog.Builder) (obj1)).args);
            obj.listener = this;
            obj1 = super.mFragmentManager;
            String s = CustomDurationDialog.TAG;
            ((FragmentManager) (obj1)).beginTransaction().add(((Fragment) (obj)), s).commitAllowingStateLoss();
            return;
        }
          goto _L11
_L10:
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing()) goto _L13; else goto _L12
_L12:
        i = 0;
          goto _L14
_L13:
        if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed()) goto _L7; else goto _L15
_L15:
        i = 1;
          goto _L14
_L7:
        i = 0;
          goto _L14
_L5:
        ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().setDurationMinutes(i);
        updateDuration();
        return;
    }

    public final void onDurationClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        flag = false;
_L4:
        FragmentActivity fragmentactivity;
        if (!flag)
        {
            return;
        } else
        {
            Object obj = durationChoiceCreator.createList(((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().getInterval(), ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().getDurationMinutes());
            obj = SingleChoiceTextDialog.newIntegerBasedInstance(((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).labels, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).selectedPosition, this, 1);
            super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "SingleChoiceTextDialog").commitAllowingStateLoss();
            return;
        }
_L3:
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
        } else
        {
            if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_107;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    public final void onFrequencyClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        flag = false;
_L4:
        FragmentActivity fragmentactivity;
        if (!flag)
        {
            return;
        } else
        {
            Object obj = new FrequencyChoice(((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications());
            obj = frequencyChoiceCreator.createList(((FrequencyChoice) (obj)));
            obj = SingleChoiceTextDialog.newParcelableBaseInstance(((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).labels, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).selectedPosition, this, 0);
            super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "SingleChoiceTextDialog").commitAllowingStateLoss();
            return;
        }
_L3:
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
        } else
        {
            if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_107;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    protected final void onInitialize()
    {
        Object obj1 = new FrequencyChoice(((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications());
        Object obj = (ContractEditSegment)super.view;
        obj1 = GrooveUtils.getFrequencyString(frequencyChoiceCreator.resources, ((FrequencyChoice) (obj1)).interval, ((FrequencyChoice) (obj1)).numInstances);
        ((ContractEditSegment) (obj)).buttonFrequency.setPrimaryText(new CharSequence[] {
            obj1
        });
        int i = ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().getDurationMinutes();
        obj = (ContractEditSegment)super.view;
        obj1 = ReminderUtils.constructTimeIntervalString(requireContext().getResources(), i);
        ((ContractEditSegment) (obj)).buttonDuration.setPrimaryText(new CharSequence[] {
            obj1
        });
        obj1 = (ContractEditSegment)super.view;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = GrooveUtils.getPreferredTimeString(((FragmentActivity) (obj)).getResources(), ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications());
        ((ContractEditSegment) (obj1)).buttonPreferredTimes.setPrimaryText(new CharSequence[] {
            obj
        });
    }

    public final void onPreferredTimeClicked()
    {
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (!flag)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        Object obj = ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications();
        PreferredTimesDialog preferredtimesdialog = new PreferredTimesDialog();
        Object obj1 = new boolean[3];
        if (GrooveUtils.allOrNoPreferredTimesSelected(((HabitContract) (obj))))
        {
            obj1[2] = true;
            obj1[1] = true;
            obj1[0] = true;
        } else
        {
            obj1[0] = ((HabitContract) (obj)).isMorningPreferable();
            obj1[1] = ((HabitContract) (obj)).isAfternoonPreferable();
            obj1[2] = ((HabitContract) (obj)).isEveningPreferable();
        }
        preferredtimesdialog.checkedItems = ((boolean []) (obj1));
        preferredtimesdialog.listener = this;
        obj = super.mFragmentManager;
        obj1 = PreferredTimesDialog.TAG;
        ((FragmentManager) (obj)).beginTransaction().add(preferredtimesdialog, ((String) (obj1))).commitAllowingStateLoss();
        return;
    }

    public final void onPreferredTimesSelected(boolean aflag[])
    {
        ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications().setMorningPreferable(aflag[0]).setAfternoonPreferable(aflag[1]).setEveningPreferable(aflag[2]);
        aflag = (ContractEditSegment)super.view;
        String s = GrooveUtils.getPreferredTimeString(requireContext().getResources(), ((HabitModificationsHolder)super.model).getHabitModifications().getContractModifications());
        ((ContractEditSegment) (aflag)).buttonPreferredTimes.setPrimaryText(new CharSequence[] {
            s
        });
    }
}
