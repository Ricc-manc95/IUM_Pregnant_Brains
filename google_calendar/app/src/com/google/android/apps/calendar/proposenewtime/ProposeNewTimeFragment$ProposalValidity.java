// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;


final class errorMessage extends Enum
{

    private static final IN_THE_PAST $VALUES[];
    public static final IN_THE_PAST END_BEFORE_START;
    public static final IN_THE_PAST IN_THE_PAST;
    public static final IN_THE_PAST PROPOSAL_SAME_AS_INITIAL;
    public static final IN_THE_PAST VALID;
    public final Integer errorMessage;
    public final boolean isValid;

    public static errorMessage[] values()
    {
        return (errorMessage[])$VALUES.clone();
    }

    static 
    {
        VALID = new <init>("VALID", 0, true, null);
        END_BEFORE_START = new <init>("END_BEFORE_START", 1, false, Integer.valueOf(0x7f13030a));
        PROPOSAL_SAME_AS_INITIAL = new <init>("PROPOSAL_SAME_AS_INITIAL", 2, false, Integer.valueOf(0x7f1303b8));
        IN_THE_PAST = new <init>("IN_THE_PAST", 3, false, Integer.valueOf(0x7f1303b7));
        $VALUES = (new .VALUES[] {
            VALID, END_BEFORE_START, PROPOSAL_SAME_AS_INITIAL, IN_THE_PAST
        });
    }

    private (String s, int i, boolean flag, Integer integer)
    {
        super(s, i);
        isValid = flag;
        errorMessage = integer;
    }
}
