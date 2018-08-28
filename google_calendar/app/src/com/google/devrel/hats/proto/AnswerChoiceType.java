// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devrel.hats.proto;


public final class AnswerChoiceType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final AnswerChoiceType $VALUES[];
    public static final AnswerChoiceType NONE_OF_ABOVE;
    private static final AnswerChoiceType OTHER_TEXT;
    private static final AnswerChoiceType UNKNOWN_ANSWER_CHOICE_TYPE;
    public static final AnswerChoiceType UNRECOGNIZED;
    public static final AnswerChoiceType USER_DEFINED;
    public final int value;

    private AnswerChoiceType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static AnswerChoiceType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_ANSWER_CHOICE_TYPE;

        case 1: // '\001'
            return USER_DEFINED;

        case 2: // '\002'
            return NONE_OF_ABOVE;

        case 3: // '\003'
            return OTHER_TEXT;
        }
    }

    public static AnswerChoiceType[] values()
    {
        return (AnswerChoiceType[])$VALUES.clone();
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
        UNKNOWN_ANSWER_CHOICE_TYPE = new AnswerChoiceType("UNKNOWN_ANSWER_CHOICE_TYPE", 0, 0);
        USER_DEFINED = new AnswerChoiceType("USER_DEFINED", 1, 1);
        NONE_OF_ABOVE = new AnswerChoiceType("NONE_OF_ABOVE", 2, 2);
        OTHER_TEXT = new AnswerChoiceType("OTHER_TEXT", 3, 3);
        UNRECOGNIZED = new AnswerChoiceType("UNRECOGNIZED", 4, -1);
        $VALUES = (new AnswerChoiceType[] {
            UNKNOWN_ANSWER_CHOICE_TYPE, USER_DEFINED, NONE_OF_ABOVE, OTHER_TEXT, UNRECOGNIZED
        });
        new _cls2();
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return AnswerChoiceType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
