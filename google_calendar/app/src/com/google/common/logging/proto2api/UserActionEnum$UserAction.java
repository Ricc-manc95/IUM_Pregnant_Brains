// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.logging.proto2api;


public final class value extends Enum
    implements com.google.protobuf.Enum.UserAction
{

    private static final forNumber $VALUES[];
    private static final forNumber ACTION_BY_TIMER;
    private static final forNumber ARROW_KEYS;
    private static final forNumber AUTOMATED;
    private static final forNumber BACK_BUTTON;
    private static final forNumber DIRECTIONAL_MOVEMENT;
    private static final forNumber DOUBLE_CLICK;
    private static final forNumber DOUBLE_TAP;
    private static final forNumber DRAG;
    private static final forNumber DROP;
    private static final forNumber FORCE_TOUCH;
    private static final forNumber GENERIC_CLICK;
    private static final forNumber HEAD_MOVEMENT;
    private static final forNumber HOVER;
    private static final forNumber INPUT_KEYBOARD;
    private static final forNumber INPUT_TEXT;
    private static final forNumber INPUT_VOICE;
    private static final forNumber INTO_BOUNDING_BOX;
    private static final forNumber KEYBOARD_ENTER;
    private static final forNumber KEY_PRESS;
    private static final forNumber LEFT_CLICK;
    private static final forNumber LONG_PRESS;
    private static final forNumber MOUSE_CLICK;
    private static final forNumber MOUSE_WHEEL;
    private static final forNumber MULTI_KEY_PRESS;
    private static final forNumber NAVIGATE;
    private static final forNumber OUT_OF_BOUNDING_BOX;
    private static final forNumber PINCH;
    private static final forNumber PINCH_CLOSED;
    private static final forNumber PINCH_OPEN;
    private static final forNumber RESIZE_BROWSER;
    private static final forNumber RIGHT_CLICK;
    private static final forNumber ROLL;
    private static final forNumber ROTATE_SCREEN;
    private static final forNumber SCROLL_BAR;
    private static final forNumber SHAKE;
    private static final forNumber SWIPE;
    private static final forNumber TAP;
    private static final forNumber UNASSIGNED_USER_ACTION_ID;
    private static final forNumber UNKNOWN_ACTION;
    private static final forNumber USER;
    public static final com.google.protobuf.Enum.UserAction internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNASSIGNED_USER_ACTION_ID;

        case 1: // '\001'
            return AUTOMATED;

        case 2: // '\002'
            return USER;

        case 3: // '\003'
            return GENERIC_CLICK;

        case 4: // '\004'
            return TAP;

        case 5: // '\005'
            return KEYBOARD_ENTER;

        case 6: // '\006'
            return MOUSE_CLICK;

        case 7: // '\007'
            return LEFT_CLICK;

        case 8: // '\b'
            return RIGHT_CLICK;

        case 9: // '\t'
            return HOVER;

        case 10: // '\n'
            return INTO_BOUNDING_BOX;

        case 11: // '\013'
            return OUT_OF_BOUNDING_BOX;

        case 12: // '\f'
            return PINCH;

        case 13: // '\r'
            return PINCH_OPEN;

        case 14: // '\016'
            return PINCH_CLOSED;

        case 15: // '\017'
            return INPUT_TEXT;

        case 16: // '\020'
            return INPUT_KEYBOARD;

        case 17: // '\021'
            return INPUT_VOICE;

        case 18: // '\022'
            return RESIZE_BROWSER;

        case 19: // '\023'
            return ROTATE_SCREEN;

        case 20: // '\024'
            return DIRECTIONAL_MOVEMENT;

        case 21: // '\025'
            return SWIPE;

        case 22: // '\026'
            return SCROLL_BAR;

        case 23: // '\027'
            return MOUSE_WHEEL;

        case 24: // '\030'
            return ARROW_KEYS;

        case 25: // '\031'
            return NAVIGATE;

        case 26: // '\032'
            return BACK_BUTTON;

        case 27: // '\033'
            return UNKNOWN_ACTION;

        case 28: // '\034'
            return HEAD_MOVEMENT;

        case 29: // '\035'
            return SHAKE;

        case 30: // '\036'
            return DRAG;

        case 31: // '\037'
            return LONG_PRESS;

        case 32: // ' '
            return KEY_PRESS;

        case 33: // '!'
            return ACTION_BY_TIMER;

        case 34: // '"'
            return DOUBLE_CLICK;

        case 35: // '#'
            return DOUBLE_TAP;

        case 36: // '$'
            return ROLL;

        case 37: // '%'
            return DROP;

        case 38: // '&'
            return FORCE_TOUCH;

        case 39: // '\''
            return MULTI_KEY_PRESS;
        }
    }

    public static MULTI_KEY_PRESS[] values()
    {
        return (MULTI_KEY_PRESS[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNASSIGNED_USER_ACTION_ID = new <init>("UNASSIGNED_USER_ACTION_ID", 0, 0);
        AUTOMATED = new <init>("AUTOMATED", 1, 1);
        USER = new <init>("USER", 2, 2);
        GENERIC_CLICK = new <init>("GENERIC_CLICK", 3, 3);
        TAP = new <init>("TAP", 4, 4);
        KEYBOARD_ENTER = new <init>("KEYBOARD_ENTER", 5, 5);
        MOUSE_CLICK = new <init>("MOUSE_CLICK", 6, 6);
        LEFT_CLICK = new <init>("LEFT_CLICK", 7, 7);
        RIGHT_CLICK = new <init>("RIGHT_CLICK", 8, 8);
        HOVER = new <init>("HOVER", 9, 9);
        INTO_BOUNDING_BOX = new <init>("INTO_BOUNDING_BOX", 10, 10);
        OUT_OF_BOUNDING_BOX = new <init>("OUT_OF_BOUNDING_BOX", 11, 11);
        PINCH = new <init>("PINCH", 12, 12);
        PINCH_OPEN = new <init>("PINCH_OPEN", 13, 13);
        PINCH_CLOSED = new <init>("PINCH_CLOSED", 14, 14);
        INPUT_TEXT = new <init>("INPUT_TEXT", 15, 15);
        INPUT_KEYBOARD = new <init>("INPUT_KEYBOARD", 16, 16);
        INPUT_VOICE = new <init>("INPUT_VOICE", 17, 17);
        RESIZE_BROWSER = new <init>("RESIZE_BROWSER", 18, 18);
        ROTATE_SCREEN = new <init>("ROTATE_SCREEN", 19, 19);
        DIRECTIONAL_MOVEMENT = new <init>("DIRECTIONAL_MOVEMENT", 20, 20);
        SWIPE = new <init>("SWIPE", 21, 21);
        SCROLL_BAR = new <init>("SCROLL_BAR", 22, 22);
        MOUSE_WHEEL = new <init>("MOUSE_WHEEL", 23, 23);
        ARROW_KEYS = new <init>("ARROW_KEYS", 24, 24);
        NAVIGATE = new <init>("NAVIGATE", 25, 25);
        BACK_BUTTON = new <init>("BACK_BUTTON", 26, 26);
        UNKNOWN_ACTION = new <init>("UNKNOWN_ACTION", 27, 27);
        HEAD_MOVEMENT = new <init>("HEAD_MOVEMENT", 28, 28);
        SHAKE = new <init>("SHAKE", 29, 29);
        DRAG = new <init>("DRAG", 30, 30);
        LONG_PRESS = new <init>("LONG_PRESS", 31, 31);
        KEY_PRESS = new <init>("KEY_PRESS", 32, 32);
        ACTION_BY_TIMER = new <init>("ACTION_BY_TIMER", 33, 33);
        DOUBLE_CLICK = new <init>("DOUBLE_CLICK", 34, 34);
        DOUBLE_TAP = new <init>("DOUBLE_TAP", 35, 35);
        ROLL = new <init>("ROLL", 36, 36);
        DROP = new <init>("DROP", 37, 37);
        FORCE_TOUCH = new <init>("FORCE_TOUCH", 38, 38);
        MULTI_KEY_PRESS = new <init>("MULTI_KEY_PRESS", 39, 39);
        $VALUES = (new .VALUES[] {
            UNASSIGNED_USER_ACTION_ID, AUTOMATED, USER, GENERIC_CLICK, TAP, KEYBOARD_ENTER, MOUSE_CLICK, LEFT_CLICK, RIGHT_CLICK, HOVER, 
            INTO_BOUNDING_BOX, OUT_OF_BOUNDING_BOX, PINCH, PINCH_OPEN, PINCH_CLOSED, INPUT_TEXT, INPUT_KEYBOARD, INPUT_VOICE, RESIZE_BROWSER, ROTATE_SCREEN, 
            DIRECTIONAL_MOVEMENT, SWIPE, SCROLL_BAR, MOUSE_WHEEL, ARROW_KEYS, NAVIGATE, BACK_BUTTON, UNKNOWN_ACTION, HEAD_MOVEMENT, SHAKE, 
            DRAG, LONG_PRESS, KEY_PRESS, ACTION_BY_TIMER, DOUBLE_CLICK, DOUBLE_TAP, ROLL, DROP, FORCE_TOUCH, MULTI_KEY_PRESS
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return UserActionEnum.UserAction.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
