// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Contract, Reminders

public final class HabitData extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(HabitData.DEFAULT_INSTANCE);
        }
    }

    public static final class Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Type $VALUES[];
        private static final Type BUILD_SKILL;
        private static final Type BUILD_SKILL_COOK_SOMETHING_NEW;
        private static final Type BUILD_SKILL_HONE_CARPENTRY_SKILLS;
        private static final Type BUILD_SKILL_LEARN_INSTRUMENT_CUSTOM;
        private static final Type BUILD_SKILL_LEARN_KNOT;
        private static final Type BUILD_SKILL_LEARN_NEW_SOFTWARE;
        private static final Type BUILD_SKILL_LEARN_TO_CODE;
        private static final Type BUILD_SKILL_LEARN_TO_DRIVE;
        private static final Type BUILD_SKILL_LEARN_TO_FLY;
        private static final Type BUILD_SKILL_MAKE_ART_CUSTOM;
        private static final Type BUILD_SKILL_PRACTICE_LANGUAGE_CUSTOM;
        private static final Type BUILD_SKILL_PRACTICE_PHOTOGRAPHY;
        private static final Type BUILD_SKILL_SING;
        private static final Type EXERCISE;
        private static final Type EXERCISE_BIKE;
        private static final Type EXERCISE_HIKE;
        private static final Type EXERCISE_PLAY_BADMINTON;
        private static final Type EXERCISE_PLAY_BASEBALL;
        private static final Type EXERCISE_PLAY_BASKETBALL;
        private static final Type EXERCISE_PLAY_SOCCER;
        private static final Type EXERCISE_PLAY_TENNIS;
        private static final Type EXERCISE_ROCK_CLIMB;
        private static final Type EXERCISE_RUN;
        private static final Type EXERCISE_SWIM;
        private static final Type EXERCISE_WALK;
        private static final Type EXERCISE_WIGGLE_EARS;
        private static final Type EXERCISE_WORKOUT;
        private static final Type EXERCISE_YOGA;
        private static final Type FRIENDS_AND_FAMILY;
        private static final Type FRIENDS_AND_FAMILY_CALL_DAD;
        private static final Type FRIENDS_AND_FAMILY_CALL_MOM;
        private static final Type FRIENDS_AND_FAMILY_EAT_WITH_FAMILY;
        private static final Type FRIENDS_AND_FAMILY_GET_DINNER_WITH_FRIENDS;
        private static final Type FRIENDS_AND_FAMILY_HAVE_BBQ;
        private static final Type FRIENDS_AND_FAMILY_PLAN_DATE;
        private static final Type FRIENDS_AND_FAMILY_PLAN_FAMILY_VACATION;
        private static final Type FRIENDS_AND_FAMILY_PLAN_REUNION;
        private static final Type FRIENDS_AND_FAMILY_PLAY_BOARD_GAME;
        private static final Type FRIENDS_AND_FAMILY_REACH_OUT;
        private static final Type FRIENDS_AND_FAMILY_VISIT_FAMILY;
        private static final Type FRIENDS_AND_FAMILY_WALK_THE_DOG;
        private static final Type ME_TIME;
        private static final Type ME_TIME_COOK;
        private static final Type ME_TIME_GET_MASSAGE;
        private static final Type ME_TIME_HOBBY_CUSTOM;
        private static final Type ME_TIME_JOURNAL;
        private static final Type ME_TIME_LIE_IN_HAMMOCK;
        private static final Type ME_TIME_MEDITATE;
        private static final Type ME_TIME_PRAY;
        private static final Type ME_TIME_READ;
        private static final Type ME_TIME_SIT_IN_THE_GRASS;
        private static final Type ME_TIME_TAKE_NAP;
        private static final Type ME_TIME_TAKE_SELFIE;
        private static final Type ME_TIME_TAKE_THE_BOAT_OUT;
        private static final Type ME_TIME_WATCH_MOVIE;
        private static final Type ORGANIZE_MY_LIFE;
        private static final Type ORGANIZE_MY_LIFE_BUY_GROCERIES;
        private static final Type ORGANIZE_MY_LIFE_CHORES;
        private static final Type ORGANIZE_MY_LIFE_CLEAN;
        private static final Type ORGANIZE_MY_LIFE_CLEAN_THE_HOUSE;
        private static final Type ORGANIZE_MY_LIFE_CLEAR_EMAIL_INBOX;
        private static final Type ORGANIZE_MY_LIFE_DO_FINANCES;
        private static final Type ORGANIZE_MY_LIFE_DO_LAUNDRY;
        private static final Type ORGANIZE_MY_LIFE_MAKE_TODO_LIST;
        private static final Type ORGANIZE_MY_LIFE_PLAN_MY_DAY;
        private static final Type ORGANIZE_MY_LIFE_PLAN_THE_MONTH;
        private static final Type ORGANIZE_MY_LIFE_PLAN_THE_WEEK;
        private static final Type ORGANIZE_MY_LIFE_STUDY;
        public static final Type UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Type forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 100: // 'd'
                return EXERCISE;

            case 101: // 'e'
                return EXERCISE_WORKOUT;

            case 102: // 'f'
                return EXERCISE_RUN;

            case 103: // 'g'
                return EXERCISE_WALK;

            case 104: // 'h'
                return EXERCISE_YOGA;

            case 105: // 'i'
                return EXERCISE_HIKE;

            case 106: // 'j'
                return EXERCISE_SWIM;

            case 107: // 'k'
                return EXERCISE_BIKE;

            case 108: // 'l'
                return EXERCISE_ROCK_CLIMB;

            case 109: // 'm'
                return EXERCISE_PLAY_TENNIS;

            case 110: // 'n'
                return EXERCISE_PLAY_BADMINTON;

            case 111: // 'o'
                return EXERCISE_PLAY_BASEBALL;

            case 112: // 'p'
                return EXERCISE_PLAY_BASKETBALL;

            case 113: // 'q'
                return EXERCISE_PLAY_SOCCER;

            case 114: // 'r'
                return EXERCISE_WIGGLE_EARS;

            case 200: 
                return BUILD_SKILL;

            case 201: 
                return BUILD_SKILL_PRACTICE_LANGUAGE_CUSTOM;

            case 202: 
                return BUILD_SKILL_LEARN_TO_CODE;

            case 203: 
                return BUILD_SKILL_MAKE_ART_CUSTOM;

            case 204: 
                return BUILD_SKILL_LEARN_INSTRUMENT_CUSTOM;

            case 205: 
                return BUILD_SKILL_PRACTICE_PHOTOGRAPHY;

            case 206: 
                return BUILD_SKILL_HONE_CARPENTRY_SKILLS;

            case 207: 
                return BUILD_SKILL_SING;

            case 208: 
                return BUILD_SKILL_LEARN_KNOT;

            case 209: 
                return BUILD_SKILL_LEARN_NEW_SOFTWARE;

            case 210: 
                return BUILD_SKILL_COOK_SOMETHING_NEW;

            case 211: 
                return BUILD_SKILL_LEARN_TO_DRIVE;

            case 212: 
                return BUILD_SKILL_LEARN_TO_FLY;

            case 300: 
                return FRIENDS_AND_FAMILY;

            case 301: 
                return FRIENDS_AND_FAMILY_REACH_OUT;

            case 302: 
                return FRIENDS_AND_FAMILY_EAT_WITH_FAMILY;

            case 303: 
                return FRIENDS_AND_FAMILY_CALL_MOM;

            case 304: 
                return FRIENDS_AND_FAMILY_CALL_DAD;

            case 305: 
                return FRIENDS_AND_FAMILY_PLAN_DATE;

            case 306: 
                return FRIENDS_AND_FAMILY_GET_DINNER_WITH_FRIENDS;

            case 307: 
                return FRIENDS_AND_FAMILY_VISIT_FAMILY;

            case 308: 
                return FRIENDS_AND_FAMILY_HAVE_BBQ;

            case 309: 
                return FRIENDS_AND_FAMILY_PLAY_BOARD_GAME;

            case 310: 
                return FRIENDS_AND_FAMILY_PLAN_REUNION;

            case 311: 
                return FRIENDS_AND_FAMILY_PLAN_FAMILY_VACATION;

            case 312: 
                return FRIENDS_AND_FAMILY_WALK_THE_DOG;

            case 400: 
                return ME_TIME;

            case 401: 
                return ME_TIME_READ;

            case 402: 
                return ME_TIME_MEDITATE;

            case 403: 
                return ME_TIME_HOBBY_CUSTOM;

            case 404: 
                return ME_TIME_COOK;

            case 405: 
                return ME_TIME_JOURNAL;

            case 406: 
                return ME_TIME_PRAY;

            case 407: 
                return ME_TIME_WATCH_MOVIE;

            case 408: 
                return ME_TIME_TAKE_NAP;

            case 409: 
                return ME_TIME_GET_MASSAGE;

            case 410: 
                return ME_TIME_SIT_IN_THE_GRASS;

            case 411: 
                return ME_TIME_TAKE_THE_BOAT_OUT;

            case 412: 
                return ME_TIME_LIE_IN_HAMMOCK;

            case 413: 
                return ME_TIME_TAKE_SELFIE;

            case 500: 
                return ORGANIZE_MY_LIFE;

            case 501: 
                return ORGANIZE_MY_LIFE_PLAN_MY_DAY;

            case 502: 
                return ORGANIZE_MY_LIFE_CLEAN;

            case 503: 
                return ORGANIZE_MY_LIFE_CHORES;

            case 504: 
                return ORGANIZE_MY_LIFE_MAKE_TODO_LIST;

            case 505: 
                return ORGANIZE_MY_LIFE_BUY_GROCERIES;

            case 506: 
                return ORGANIZE_MY_LIFE_STUDY;

            case 507: 
                return ORGANIZE_MY_LIFE_DO_LAUNDRY;

            case 508: 
                return ORGANIZE_MY_LIFE_DO_FINANCES;

            case 509: 
                return ORGANIZE_MY_LIFE_PLAN_THE_WEEK;

            case 510: 
                return ORGANIZE_MY_LIFE_PLAN_THE_MONTH;

            case 511: 
                return ORGANIZE_MY_LIFE_CLEAR_EMAIL_INBOX;

            case 512: 
                return ORGANIZE_MY_LIFE_CLEAN_THE_HOUSE;
            }
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new Type("UNKNOWN", 0, 0);
            EXERCISE = new Type("EXERCISE", 1, 100);
            EXERCISE_WORKOUT = new Type("EXERCISE_WORKOUT", 2, 101);
            EXERCISE_RUN = new Type("EXERCISE_RUN", 3, 102);
            EXERCISE_WALK = new Type("EXERCISE_WALK", 4, 103);
            EXERCISE_YOGA = new Type("EXERCISE_YOGA", 5, 104);
            EXERCISE_HIKE = new Type("EXERCISE_HIKE", 6, 105);
            EXERCISE_SWIM = new Type("EXERCISE_SWIM", 7, 106);
            EXERCISE_BIKE = new Type("EXERCISE_BIKE", 8, 107);
            EXERCISE_ROCK_CLIMB = new Type("EXERCISE_ROCK_CLIMB", 9, 108);
            EXERCISE_PLAY_TENNIS = new Type("EXERCISE_PLAY_TENNIS", 10, 109);
            EXERCISE_PLAY_BADMINTON = new Type("EXERCISE_PLAY_BADMINTON", 11, 110);
            EXERCISE_PLAY_BASEBALL = new Type("EXERCISE_PLAY_BASEBALL", 12, 111);
            EXERCISE_PLAY_BASKETBALL = new Type("EXERCISE_PLAY_BASKETBALL", 13, 112);
            EXERCISE_PLAY_SOCCER = new Type("EXERCISE_PLAY_SOCCER", 14, 113);
            EXERCISE_WIGGLE_EARS = new Type("EXERCISE_WIGGLE_EARS", 15, 114);
            BUILD_SKILL = new Type("BUILD_SKILL", 16, 200);
            BUILD_SKILL_PRACTICE_LANGUAGE_CUSTOM = new Type("BUILD_SKILL_PRACTICE_LANGUAGE_CUSTOM", 17, 201);
            BUILD_SKILL_LEARN_TO_CODE = new Type("BUILD_SKILL_LEARN_TO_CODE", 18, 202);
            BUILD_SKILL_MAKE_ART_CUSTOM = new Type("BUILD_SKILL_MAKE_ART_CUSTOM", 19, 203);
            BUILD_SKILL_LEARN_INSTRUMENT_CUSTOM = new Type("BUILD_SKILL_LEARN_INSTRUMENT_CUSTOM", 20, 204);
            BUILD_SKILL_PRACTICE_PHOTOGRAPHY = new Type("BUILD_SKILL_PRACTICE_PHOTOGRAPHY", 21, 205);
            BUILD_SKILL_HONE_CARPENTRY_SKILLS = new Type("BUILD_SKILL_HONE_CARPENTRY_SKILLS", 22, 206);
            BUILD_SKILL_SING = new Type("BUILD_SKILL_SING", 23, 207);
            BUILD_SKILL_LEARN_KNOT = new Type("BUILD_SKILL_LEARN_KNOT", 24, 208);
            BUILD_SKILL_LEARN_NEW_SOFTWARE = new Type("BUILD_SKILL_LEARN_NEW_SOFTWARE", 25, 209);
            BUILD_SKILL_COOK_SOMETHING_NEW = new Type("BUILD_SKILL_COOK_SOMETHING_NEW", 26, 210);
            BUILD_SKILL_LEARN_TO_DRIVE = new Type("BUILD_SKILL_LEARN_TO_DRIVE", 27, 211);
            BUILD_SKILL_LEARN_TO_FLY = new Type("BUILD_SKILL_LEARN_TO_FLY", 28, 212);
            FRIENDS_AND_FAMILY = new Type("FRIENDS_AND_FAMILY", 29, 300);
            FRIENDS_AND_FAMILY_REACH_OUT = new Type("FRIENDS_AND_FAMILY_REACH_OUT", 30, 301);
            FRIENDS_AND_FAMILY_EAT_WITH_FAMILY = new Type("FRIENDS_AND_FAMILY_EAT_WITH_FAMILY", 31, 302);
            FRIENDS_AND_FAMILY_CALL_MOM = new Type("FRIENDS_AND_FAMILY_CALL_MOM", 32, 303);
            FRIENDS_AND_FAMILY_CALL_DAD = new Type("FRIENDS_AND_FAMILY_CALL_DAD", 33, 304);
            FRIENDS_AND_FAMILY_PLAN_DATE = new Type("FRIENDS_AND_FAMILY_PLAN_DATE", 34, 305);
            FRIENDS_AND_FAMILY_GET_DINNER_WITH_FRIENDS = new Type("FRIENDS_AND_FAMILY_GET_DINNER_WITH_FRIENDS", 35, 306);
            FRIENDS_AND_FAMILY_VISIT_FAMILY = new Type("FRIENDS_AND_FAMILY_VISIT_FAMILY", 36, 307);
            FRIENDS_AND_FAMILY_HAVE_BBQ = new Type("FRIENDS_AND_FAMILY_HAVE_BBQ", 37, 308);
            FRIENDS_AND_FAMILY_PLAY_BOARD_GAME = new Type("FRIENDS_AND_FAMILY_PLAY_BOARD_GAME", 38, 309);
            FRIENDS_AND_FAMILY_PLAN_REUNION = new Type("FRIENDS_AND_FAMILY_PLAN_REUNION", 39, 310);
            FRIENDS_AND_FAMILY_PLAN_FAMILY_VACATION = new Type("FRIENDS_AND_FAMILY_PLAN_FAMILY_VACATION", 40, 311);
            FRIENDS_AND_FAMILY_WALK_THE_DOG = new Type("FRIENDS_AND_FAMILY_WALK_THE_DOG", 41, 312);
            ME_TIME = new Type("ME_TIME", 42, 400);
            ME_TIME_READ = new Type("ME_TIME_READ", 43, 401);
            ME_TIME_MEDITATE = new Type("ME_TIME_MEDITATE", 44, 402);
            ME_TIME_HOBBY_CUSTOM = new Type("ME_TIME_HOBBY_CUSTOM", 45, 403);
            ME_TIME_COOK = new Type("ME_TIME_COOK", 46, 404);
            ME_TIME_JOURNAL = new Type("ME_TIME_JOURNAL", 47, 405);
            ME_TIME_PRAY = new Type("ME_TIME_PRAY", 48, 406);
            ME_TIME_WATCH_MOVIE = new Type("ME_TIME_WATCH_MOVIE", 49, 407);
            ME_TIME_TAKE_NAP = new Type("ME_TIME_TAKE_NAP", 50, 408);
            ME_TIME_GET_MASSAGE = new Type("ME_TIME_GET_MASSAGE", 51, 409);
            ME_TIME_SIT_IN_THE_GRASS = new Type("ME_TIME_SIT_IN_THE_GRASS", 52, 410);
            ME_TIME_TAKE_THE_BOAT_OUT = new Type("ME_TIME_TAKE_THE_BOAT_OUT", 53, 411);
            ME_TIME_LIE_IN_HAMMOCK = new Type("ME_TIME_LIE_IN_HAMMOCK", 54, 412);
            ME_TIME_TAKE_SELFIE = new Type("ME_TIME_TAKE_SELFIE", 55, 413);
            ORGANIZE_MY_LIFE = new Type("ORGANIZE_MY_LIFE", 56, 500);
            ORGANIZE_MY_LIFE_PLAN_MY_DAY = new Type("ORGANIZE_MY_LIFE_PLAN_MY_DAY", 57, 501);
            ORGANIZE_MY_LIFE_CLEAN = new Type("ORGANIZE_MY_LIFE_CLEAN", 58, 502);
            ORGANIZE_MY_LIFE_CHORES = new Type("ORGANIZE_MY_LIFE_CHORES", 59, 503);
            ORGANIZE_MY_LIFE_MAKE_TODO_LIST = new Type("ORGANIZE_MY_LIFE_MAKE_TODO_LIST", 60, 504);
            ORGANIZE_MY_LIFE_BUY_GROCERIES = new Type("ORGANIZE_MY_LIFE_BUY_GROCERIES", 61, 505);
            ORGANIZE_MY_LIFE_STUDY = new Type("ORGANIZE_MY_LIFE_STUDY", 62, 506);
            ORGANIZE_MY_LIFE_DO_LAUNDRY = new Type("ORGANIZE_MY_LIFE_DO_LAUNDRY", 63, 507);
            ORGANIZE_MY_LIFE_DO_FINANCES = new Type("ORGANIZE_MY_LIFE_DO_FINANCES", 64, 508);
            ORGANIZE_MY_LIFE_PLAN_THE_WEEK = new Type("ORGANIZE_MY_LIFE_PLAN_THE_WEEK", 65, 509);
            ORGANIZE_MY_LIFE_PLAN_THE_MONTH = new Type("ORGANIZE_MY_LIFE_PLAN_THE_MONTH", 66, 510);
            ORGANIZE_MY_LIFE_CLEAR_EMAIL_INBOX = new Type("ORGANIZE_MY_LIFE_CLEAR_EMAIL_INBOX", 67, 511);
            ORGANIZE_MY_LIFE_CLEAN_THE_HOUSE = new Type("ORGANIZE_MY_LIFE_CLEAN_THE_HOUSE", 68, 512);
            $VALUES = (new Type[] {
                UNKNOWN, EXERCISE, EXERCISE_WORKOUT, EXERCISE_RUN, EXERCISE_WALK, EXERCISE_YOGA, EXERCISE_HIKE, EXERCISE_SWIM, EXERCISE_BIKE, EXERCISE_ROCK_CLIMB, 
                EXERCISE_PLAY_TENNIS, EXERCISE_PLAY_BADMINTON, EXERCISE_PLAY_BASEBALL, EXERCISE_PLAY_BASKETBALL, EXERCISE_PLAY_SOCCER, EXERCISE_WIGGLE_EARS, BUILD_SKILL, BUILD_SKILL_PRACTICE_LANGUAGE_CUSTOM, BUILD_SKILL_LEARN_TO_CODE, BUILD_SKILL_MAKE_ART_CUSTOM, 
                BUILD_SKILL_LEARN_INSTRUMENT_CUSTOM, BUILD_SKILL_PRACTICE_PHOTOGRAPHY, BUILD_SKILL_HONE_CARPENTRY_SKILLS, BUILD_SKILL_SING, BUILD_SKILL_LEARN_KNOT, BUILD_SKILL_LEARN_NEW_SOFTWARE, BUILD_SKILL_COOK_SOMETHING_NEW, BUILD_SKILL_LEARN_TO_DRIVE, BUILD_SKILL_LEARN_TO_FLY, FRIENDS_AND_FAMILY, 
                FRIENDS_AND_FAMILY_REACH_OUT, FRIENDS_AND_FAMILY_EAT_WITH_FAMILY, FRIENDS_AND_FAMILY_CALL_MOM, FRIENDS_AND_FAMILY_CALL_DAD, FRIENDS_AND_FAMILY_PLAN_DATE, FRIENDS_AND_FAMILY_GET_DINNER_WITH_FRIENDS, FRIENDS_AND_FAMILY_VISIT_FAMILY, FRIENDS_AND_FAMILY_HAVE_BBQ, FRIENDS_AND_FAMILY_PLAY_BOARD_GAME, FRIENDS_AND_FAMILY_PLAN_REUNION, 
                FRIENDS_AND_FAMILY_PLAN_FAMILY_VACATION, FRIENDS_AND_FAMILY_WALK_THE_DOG, ME_TIME, ME_TIME_READ, ME_TIME_MEDITATE, ME_TIME_HOBBY_CUSTOM, ME_TIME_COOK, ME_TIME_JOURNAL, ME_TIME_PRAY, ME_TIME_WATCH_MOVIE, 
                ME_TIME_TAKE_NAP, ME_TIME_GET_MASSAGE, ME_TIME_SIT_IN_THE_GRASS, ME_TIME_TAKE_THE_BOAT_OUT, ME_TIME_LIE_IN_HAMMOCK, ME_TIME_TAKE_SELFIE, ORGANIZE_MY_LIFE, ORGANIZE_MY_LIFE_PLAN_MY_DAY, ORGANIZE_MY_LIFE_CLEAN, ORGANIZE_MY_LIFE_CHORES, 
                ORGANIZE_MY_LIFE_MAKE_TODO_LIST, ORGANIZE_MY_LIFE_BUY_GROCERIES, ORGANIZE_MY_LIFE_STUDY, ORGANIZE_MY_LIFE_DO_LAUNDRY, ORGANIZE_MY_LIFE_DO_FINANCES, ORGANIZE_MY_LIFE_PLAN_THE_WEEK, ORGANIZE_MY_LIFE_PLAN_THE_MONTH, ORGANIZE_MY_LIFE_CLEAR_EMAIL_INBOX, ORGANIZE_MY_LIFE_CLEAN_THE_HOUSE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Type.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Type(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Visibility extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Visibility $VALUES[];
        public static final Visibility DEFAULT;
        private static final Visibility PRIVATE;
        private static final Visibility PUBLIC;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Visibility forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return DEFAULT;

            case 1: // '\001'
                return PUBLIC;

            case 2: // '\002'
                return PRIVATE;
            }
        }

        public static Visibility[] values()
        {
            return (Visibility[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            DEFAULT = new Visibility("DEFAULT", 0, 0);
            PUBLIC = new Visibility("PUBLIC", 1, 1);
            PRIVATE = new Visibility("PRIVATE", 2, 2);
            $VALUES = (new Visibility[] {
                DEFAULT, PUBLIC, PRIVATE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Visibility.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Visibility(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final HabitData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String color_;
    public Contract contract_;
    public Reminders reminders_;
    public String summary_;
    public int type_;
    public int visibility_;

    private HabitData()
    {
        summary_ = "";
        color_ = "";
    }

    public static HabitData parseFrom(InputStream inputstream)
        throws IOException
    {
        GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
        if (generatedmessagelite != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
            boolean flag;
            if (byte0 == 1)
            {
                flag = true;
            } else
            if (byte0 == 0)
            {
                flag = false;
            } else
            {
                flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = generatedmessagelite;
                    } else
                    {
                        inputstream = null;
                    }
                    generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                }
            }
            if (!flag)
            {
                inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (inputstream == null)
                {
                    throw null;
                } else
                {
                    throw inputstream;
                }
            }
        }
        return (HabitData)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 195
    //                   1 200
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 142
    //                   6 146;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new HabitData();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Type.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = Visibility.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\006\000\001\001\t\006\000\000\000\001\f\000\003\t\003\005\b\004\006\f\005\b\t\007\t\b\002", new Object[] {
            "bitField0_", "type_", obj, "contract_", "color_", "visibility_", enumverifier, "reminders_", "summary_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/HabitData;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/HabitData;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/HabitData;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new HabitData();
        HabitData habitdata = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/HabitData, habitdata);
    }
}
