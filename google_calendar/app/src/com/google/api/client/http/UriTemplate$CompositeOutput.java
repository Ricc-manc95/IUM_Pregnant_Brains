// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import java.util.Map;

// Referenced classes of package com.google.api.client.http:
//            UriTemplate

final class ES extends Enum
{

    private static final SIMPLE $VALUES[];
    private static final SIMPLE AMP;
    private static final SIMPLE DOT;
    private static final SIMPLE FORWARD_SLASH;
    private static final SIMPLE HASH;
    private static final SIMPLE PLUS;
    private static final SIMPLE QUERY;
    private static final SIMPLE SEMI_COLON;
    public static final SIMPLE SIMPLE;
    public final String explodeJoiner;
    public final String outputPrefix;
    public final Character propertyPrefix;
    public final boolean requiresVarAssignment;
    public final boolean reservedExpansion;

    public static ES[] values()
    {
        return (ES[])$VALUES.clone();
    }

    static 
    {
        PLUS = new <init>("PLUS", 0, Character.valueOf('+'), "", ",", false, true);
        HASH = new <init>("HASH", 1, Character.valueOf('#'), "#", ",", false, true);
        DOT = new <init>("DOT", 2, Character.valueOf('.'), ".", ".", false, false);
        FORWARD_SLASH = new <init>("FORWARD_SLASH", 3, Character.valueOf('/'), "/", "/", false, false);
        SEMI_COLON = new <init>("SEMI_COLON", 4, Character.valueOf(';'), ";", ";", true, false);
        QUERY = new <init>("QUERY", 5, Character.valueOf('?'), "?", "&", true, false);
        AMP = new <init>("AMP", 6, Character.valueOf('&'), "&", "&", true, false);
        SIMPLE = new <init>("SIMPLE", 7, null, "", ",", false, false);
        $VALUES = (new .VALUES[] {
            PLUS, HASH, DOT, FORWARD_SLASH, SEMI_COLON, QUERY, AMP, SIMPLE
        });
    }

    private (String s, int i, Character character, String s1, String s2, boolean flag, boolean flag1)
    {
        super(s, i);
        propertyPrefix = character;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        outputPrefix = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        explodeJoiner = (String)s2;
        requiresVarAssignment = flag;
        reservedExpansion = flag1;
        if (character != null)
        {
            UriTemplate.COMPOSITE_PREFIXES.put(character, this);
        }
    }
}
