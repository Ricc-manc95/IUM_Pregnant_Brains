// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devrel.hats.proto;


public final class QuestionType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final QuestionType $VALUES[];
    public static final QuestionType MULTIPLE_CHOICE;
    public static final QuestionType MULTIPLE_SELECT;
    public static final QuestionType OPEN_TEXT;
    public static final QuestionType RATING;
    private static final QuestionType UNKNOWN_QUESTION_TYPE;
    public static final QuestionType UNRECOGNIZED;
    public final int value;

    private QuestionType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static QuestionType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_QUESTION_TYPE;

        case 1: // '\001'
            return MULTIPLE_CHOICE;

        case 2: // '\002'
            return MULTIPLE_SELECT;

        case 3: // '\003'
            return OPEN_TEXT;

        case 4: // '\004'
            return RATING;
        }
    }

    public static QuestionType[] values()
    {
        return (QuestionType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        UNKNOWN_QUESTION_TYPE = new QuestionType("UNKNOWN_QUESTION_TYPE", 0, 0);
        MULTIPLE_CHOICE = new QuestionType("MULTIPLE_CHOICE", 1, 1);
        MULTIPLE_SELECT = new QuestionType("MULTIPLE_SELECT", 2, 2);
        OPEN_TEXT = new QuestionType("OPEN_TEXT", 3, 3);
        RATING = new QuestionType("RATING", 4, 4);
        UNRECOGNIZED = new QuestionType("UNRECOGNIZED", 5, -1);
        $VALUES = (new QuestionType[] {
            UNKNOWN_QUESTION_TYPE, MULTIPLE_CHOICE, MULTIPLE_SELECT, OPEN_TEXT, RATING, UNRECOGNIZED
        });
        new _cls2();
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return QuestionType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
