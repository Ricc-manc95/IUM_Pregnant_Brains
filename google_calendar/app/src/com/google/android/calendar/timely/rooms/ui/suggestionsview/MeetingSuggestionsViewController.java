// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.calendar.timely.widgets.spinner.LabeledSpinner;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemManager

public final class MeetingSuggestionsViewController
{

    public final ViewGroup container;
    public final View contentView;
    public final View filterBar;
    public final TextView filterText;
    public Listener listener;
    public final LabeledSpinner loadingSpinner;
    public final UiItemManager roomsUiManager;
    public final RecyclerView roomsView;
    private final UiItemManager.Listener uiItemManagerListener = new _cls1();

    public MeetingSuggestionsViewController(ViewGroup viewgroup, View view, RecyclerView recyclerview, UiItemManager uiitemmanager, LabeledSpinner labeledspinner, View view1, int i)
    {
        boolean flag;
        if (labeledspinner.getVisibility() == 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            container = viewgroup;
            contentView = view;
            roomsView = recyclerview;
            roomsUiManager = uiitemmanager;
            roomsUiManager.listener = uiItemManagerListener;
            loadingSpinner = labeledspinner;
            loadingSpinner.setLabels(ImmutableList.of(contentView.getResources().getString(0x7f130326)));
            loadingSpinner.setSpinnerColor(i);
            filterBar = view1;
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final MeetingSuggestionsViewController arg$1;

                public final void onClick(View view2)
                {
                    view2 = arg$1;
                    if (((MeetingSuggestionsViewController) (view2)).listener != null)
                    {
                        ((MeetingSuggestionsViewController) (view2)).listener.onFilterBarClicked();
                    }
                }

            .Lambda._cls0()
            {
                arg$1 = MeetingSuggestionsViewController.this;
            }

                private class Listener
                {

                    public abstract void onAcceptRoom(Room room);

                    public abstract void onExpandMeetingLocation(AttendeeGroup attendeegroup, ImmutableSet immutableset);

                    public abstract void onExpandSection();

                    public abstract void onFilterBarClicked();

                    public abstract void onRemoveRoom(Room room);

                    public abstract void onToggleAudioRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();

                    public abstract void onToggleVideoRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();
                }

            }

            filterBar.setOnClickListener(new .Lambda._cls0());
            filterText = (TextView)filterBar.findViewById(0x7f100341);
            return;
        }
    }

    private class _cls1
        implements UiItemManager.Listener
    {

        private final MeetingSuggestionsViewController this$0;

        public final void acceptSuggestion(AttendeeGroup attendeegroup, SuggestedRoom suggestedroom)
        {
            UiItemManager uiitemmanager = roomsUiManager;
            uiitemmanager.suggestedRoomTileByLocation.remove(attendeegroup, suggestedroom);
            AddedRoom addedroom = uiitemmanager.getAddedRoom(suggestedroom.room, attendeegroup.getCriteria());
            uiitemmanager.addedRoomsByHierarchyNodeId.put(attendeegroup.getHierarchyNodeId(), addedroom);
            uiitemmanager.uiItemList.replaceItem(suggestedroom, addedroom);
            uiitemmanager.collapseLocation(attendeegroup);
            if (listener != null)
            {
                listener.onAcceptRoom(suggestedroom.room);
            }
        }

        public final void changeCapacityRequirementClicked$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0()
        {
            Toast.makeText(container.getContext(), "coming soon", 0).show();
        }

        public final void expandSection()
        {
            if (listener != null)
            {
                listener.onExpandSection();
            }
        }

        public final void expandSuggestions(AttendeeGroup attendeegroup, ImmutableSet immutableset)
        {
            if (listener != null)
            {
                listener.onExpandMeetingLocation(attendeegroup, immutableset);
            }
        }

        public final void removeRoom(AddedRoom addedroom)
        {
            UiItemManager uiitemmanager = roomsUiManager;
            String s = addedroom.room.getHierarchyNodeId();
            uiitemmanager.addedRoomsByHierarchyNodeId.remove(s, addedroom);
            uiitemmanager.nodeExpansionStateByHierarchyNodeId.put(s, Boolean.valueOf(uiitemmanager.addedRoomsByHierarchyNodeId.get(s).isEmpty()));
            uiitemmanager.uiItemList.removeItem(addedroom);
            if (listener != null)
            {
                listener.onRemoveRoom(addedroom.room);
            }
        }

        public final void toggleAudioRequirement(AttendeeGroup attendeegroup)
        {
            Toast.makeText(container.getContext(), "coming soon", 0).show();
            if (listener != null)
            {
                listener._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();
            }
        }

        public final void toggleVideoRequirement(AttendeeGroup attendeegroup)
        {
            Toast.makeText(container.getContext(), "coming soon", 0).show();
            if (listener != null)
            {
                listener._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();
            }
        }

        _cls1()
        {
            this$0 = MeetingSuggestionsViewController.this;
            super();
        }
    }

}
