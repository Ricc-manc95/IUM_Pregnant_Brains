// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devrel.hats.proto;


public final class QuestionResponseStatus extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final QuestionResponseStatus $VALUES[];
    public static final QuestionResponseStatus ANSWERED;
    private static final QuestionResponseStatus CLOSED_EARLY;
    public static final QuestionResponseStatus NOT_ANSWERED;
    private static final QuestionResponseStatus SKIPPED;
    private static final QuestionResponseStatus UNKNOWN_QUESTION_RESPONSE_STATUS;
    public static final QuestionResponseStatus UNRECOGNIZED;
    public final int value;

    private QuestionResponseStatus(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static QuestionResponseStatus forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_QUESTION_RESPONSE_STATUS;

        case 1: // '\001'
            return ANSWERED;

        case 2: // '\002'
            return NOT_ANSWERED;

        case 3: // '\003'
            return SKIPPED;

        case 4: // '\004'
            return CLOSED_EARLY;
        }
    }

    public static QuestionResponseStatus[] values()
    {
        return (QuestionResponseStatus[])$VALUES.clone();
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
        UNKNOWN_QUESTION_RESPONSE_STATUS = new QuestionResponseStatus("UNKNOWN_QUESTION_RESPONSE_STATUS", 0, 0);
        ANSWERED = new QuestionResponseStatus("ANSWERED", 1, 1);
        NOT_ANSWERED = new QuestionResponseStatus("NOT_ANSWERED", 2, 2);
        SKIPPED = new QuestionResponseStatus("SKIPPED", 3, 3);
        CLOSED_EARLY = new QuestionResponseStatus("CLOSED_EARLY", 4, 4);
        UNRECOGNIZED = new QuestionResponseStatus("UNRECOGNIZED", 5, -1);
        $VALUES = (new QuestionResponseStatus[] {
            UNKNOWN_QUESTION_RESPONSE_STATUS, ANSWERED, NOT_ANSWERED, SKIPPED, CLOSED_EARLY, UNRECOGNIZED
        });
        new _cls2();
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return QuestionResponseStatus.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
