// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;


// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            SmartRsvpBottomBar

public static final class isExpanded extends Enum
{

    private static final FOLLOW_UP_WITH_PROPOSAL $VALUES[];
    public static final FOLLOW_UP_WITH_PROPOSAL FOLLOW_UP_WITHOUT_PROPOSAL;
    public static final FOLLOW_UP_WITH_PROPOSAL FOLLOW_UP_WITH_PROPOSAL;
    public static final FOLLOW_UP_WITH_PROPOSAL RSVP_WITHOUT_TOGGLE;
    public static final FOLLOW_UP_WITH_PROPOSAL RSVP_WITH_PROPOSAL;
    public static final FOLLOW_UP_WITH_PROPOSAL RSVP_WITH_TOGGLE;
    public final boolean canHaveFollowUps;
    public final boolean hasProposal;
    public final boolean isExpanded;

    public static isExpanded[] values()
    {
        return (isExpanded[])$VALUES.clone();
    }

    static 
    {
        RSVP_WITHOUT_TOGGLE = new <init>("RSVP_WITHOUT_TOGGLE", 0, false, false, false);
        RSVP_WITH_TOGGLE = new <init>("RSVP_WITH_TOGGLE", 1, true, false, false);
        RSVP_WITH_PROPOSAL = new <init>("RSVP_WITH_PROPOSAL", 2, true, false, true);
        FOLLOW_UP_WITHOUT_PROPOSAL = new <init>("FOLLOW_UP_WITHOUT_PROPOSAL", 3, true, true, false);
        FOLLOW_UP_WITH_PROPOSAL = new <init>("FOLLOW_UP_WITH_PROPOSAL", 4, true, true, true);
        $VALUES = (new .VALUES[] {
            RSVP_WITHOUT_TOGGLE, RSVP_WITH_TOGGLE, RSVP_WITH_PROPOSAL, FOLLOW_UP_WITHOUT_PROPOSAL, FOLLOW_UP_WITH_PROPOSAL
        });
    }

    private (String s, int i, boolean flag, boolean flag1, boolean flag2)
    {
        super(s, i);
        canHaveFollowUps = flag;
        hasProposal = flag2;
        isExpanded = flag1;
    }
}
