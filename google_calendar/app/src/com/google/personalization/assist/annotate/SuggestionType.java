// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class SuggestionType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final SuggestionType $VALUES[];
    public static final SuggestionType CALENDAR;
    private static final SuggestionType KEEP_SUGGEST;
    public static final SuggestionType REMINDER;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private SuggestionType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static SuggestionType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return REMINDER;

        case 1: // '\001'
            return CALENDAR;

        case 2: // '\002'
            return KEEP_SUGGEST;
        }
    }

    public static SuggestionType[] values()
    {
        return (SuggestionType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        REMINDER = new SuggestionType("REMINDER", 0, 0);
        CALENDAR = new SuggestionType("CALENDAR", 1, 1);
        KEEP_SUGGEST = new SuggestionType("KEEP_SUGGEST", 2, 2);
        $VALUES = (new SuggestionType[] {
            REMINDER, CALENDAR, KEEP_SUGGEST
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return SuggestionType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
