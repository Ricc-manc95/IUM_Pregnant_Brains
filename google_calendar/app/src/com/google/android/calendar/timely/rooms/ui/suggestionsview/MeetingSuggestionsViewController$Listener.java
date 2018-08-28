// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.common.collect.ImmutableSet;

public interface 
{

    public abstract void onAcceptRoom(Room room);

    public abstract void onExpandMeetingLocation(AttendeeGroup attendeegroup, ImmutableSet immutableset);

    public abstract void onExpandSection();

    public abstract void onFilterBarClicked();

    public abstract void onRemoveRoom(Room room);

    public abstract void onToggleAudioRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();

    public abstract void onToggleVideoRequirement$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NN4RRFDLPIUP31EHGIUGBKEHIMSP35CL3N4RRLE0TIILG_0();
}
