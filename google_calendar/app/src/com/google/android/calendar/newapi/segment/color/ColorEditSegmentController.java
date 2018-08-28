// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.color;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.drawable.ColorCircle;
import com.google.android.calendar.newapi.model.CalendarListEntryHolder;
import com.google.android.calendar.newapi.model.ColorModification;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.color:
//            ColorEditSegment, SingleChoiceColorDialog

public class ColorEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, ColorEditSegment.Listener
{

    private List colors;

    public ColorEditSegmentController()
    {
    }

    private final void updateColors()
    {
        colors = new ArrayList();
        colors.addAll(CalendarApi.getColorFactory().getEventColorList());
        colors.add(((CalendarListEntryHolder)super.model).getCalendarListEntry().getColor());
    }

    private final void updateView()
    {
        Object obj = super.view;
        boolean flag = ((ColorModification)(CalendarListEntryHolder)super.model).canModifyColorOverride();
        if (obj != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        if (!flag)
        {
            return;
        }
        ColorEditSegment coloreditsegment = (ColorEditSegment)super.view;
        EntityColor entitycolor = ((ColorModification)(CalendarListEntryHolder)super.model).getColor();
        TextTileView texttileview = coloreditsegment.tile;
        obj = coloreditsegment.getResources();
        if (entitycolor instanceof EventColor)
        {
            obj = ((EventColor)entitycolor).getName();
        } else
        {
            obj = ((Resources) (obj)).getString(0x7f1301a3);
        }
        texttileview.setPrimaryText(new CharSequence[] {
            obj
        });
        coloreditsegment.colorCircle.setColor(entitycolor.getValue());
        coloreditsegment.tile.getIcon().invalidate();
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (ColorEditSegment)layoutinflater.inflate(0x7f0500c4, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateColors();
        updateView();
    }

    public final void onColorChanged()
    {
        updateView();
    }

    public final void onColorClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_114;
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
            com.google.android.calendar.common.dialog.SingleChoiceDialog singlechoicedialog = SingleChoiceColorDialog.newInstance(colors, colors.indexOf(((ColorModification)(CalendarListEntryHolder)super.model).getColor()), false, this);
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl1 = super.mFragmentManager;
            String s = SingleChoiceColorDialog.TAG;
            fragmentmanagerimpl1.beginTransaction().add(singlechoicedialog, s).commitAllowingStateLoss();
            ((ColorEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f130017));
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
                break MISSING_BLOCK_LABEL_114;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        EntityColor entitycolor;
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        entitycolor = (EntityColor)obj;
        Object obj1 = (ColorModification)(CalendarListEntryHolder)super.model;
        Iterator iterator;
        if (entitycolor instanceof EventColor)
        {
            obj = (EventColor)entitycolor;
        } else
        {
            obj = null;
        }
        ((ColorModification) (obj1)).setColorOverride(((EventColor) (obj)));
        updateView();
        obj = super.editScreenController;
        obj1 = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls0.$instance;
        iterator = ((EditScreenController) (obj)).segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L6
_L6:
        i = 0;
_L8:
        if (i != 0)
        {
            ((Consumer) (obj1)).accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = 0;
        if (true) goto _L8; else goto _L2
_L2:
        ColorEditSegment coloreditsegment = (ColorEditSegment)super.view;
        obj = requireContext().getResources();
        if (entitycolor instanceof EventColor)
        {
            obj = ((EventColor)entitycolor).getName();
        } else
        {
            obj = ((Resources) (obj)).getString(0x7f1301a3);
        }
        coloreditsegment.announceForAccessibility(requireContext().getResources().getString(0x7f13001b, new Object[] {
            obj
        }));
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final void onInitialize()
    {
        updateColors();
        updateView();
    }
}
