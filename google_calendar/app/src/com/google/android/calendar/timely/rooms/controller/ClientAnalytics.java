// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.loggers.CalendarClientLogger;
import com.google.android.calendar.timely.rooms.data.RoomHierarchy;
import com.google.android.calendar.timely.rooms.data.RoomRecommendations;
import com.google.android.calendar.timely.rooms.net.RoomResponse;
import java.util.Collection;
import java.util.Collections;

final class ClientAnalytics
{

    public final Account account;
    public final String calendarEventReference;
    public RoomResponse currentRoomResponse;
    public int lastResponse$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TP6URRDECNM6RREEHP6UR3CCLP2UGRCD5IMST21DPGMOUBKD5HN692ICLPN0RREEDIL8UBGCKTG____0;
    public final CalendarClientLogger logger;

    ClientAnalytics(Context context, Account account1, String s)
    {
        logger = CalendarClientLogger.getInstance(context);
        account = account1;
        calendarEventReference = s;
    }

    final void logRoomsShown(boolean flag)
    {
        CalendarClientLogger calendarclientlogger;
        String s;
        String s1;
        Object obj;
        if (currentRoomResponse == null)
        {
            return;
        }
        calendarclientlogger = logger;
        s = calendarEventReference;
        s1 = currentRoomResponse.getResponseId();
        obj = currentRoomResponse;
        if (((RoomResponse) (obj)).getRoomRecommendations() == null) goto _L2; else goto _L1
_L1:
        boolean flag1;
        boolean flag2;
        boolean flag3;
        obj = ((RoomResponse) (obj)).getRoomRecommendations().getRoomSuggestions();
        boolean flag4;
        if (obj == null || ((Collection) (obj)).isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L2; else goto _L3
_L3:
        flag2 = true;
_L11:
        if (flag) goto _L5; else goto _L4
_L4:
        if (currentRoomResponse.hasFlatList()) goto _L7; else goto _L6
_L6:
        obj = currentRoomResponse;
        if (((RoomResponse) (obj)).getRoomHierarchy() == null) goto _L9; else goto _L8
_L8:
        obj = ((RoomResponse) (obj)).getRoomHierarchy().nodes;
        if (obj == null || ((Collection) (obj)).isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L9; else goto _L10
_L10:
        flag1 = true;
_L12:
        if (!flag1) goto _L5; else goto _L7
_L7:
        flag = true;
_L13:
        flag4 = currentRoomResponse.hasFlatList();
        obj = currentRoomResponse;
        if (((RoomResponse) (obj)).getRoomHierarchy() == null)
        {
            break MISSING_BLOCK_LABEL_288;
        }
        obj = ((RoomResponse) (obj)).getRoomHierarchy().nodes;
        if (obj == null || ((Collection) (obj)).isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_288;
        }
        flag3 = true;
_L14:
        calendarclientlogger.log(account, calendarclientlogger.getRoomBookingProto(com.google.calendar.client.events.logging.CalendarClientLogEvent.Type.ROOMS_SHOWN, s1, null, s, Boolean.valueOf(flag2), Boolean.valueOf(flag), Boolean.valueOf(flag4), Boolean.valueOf(flag3), null, null, null, null, Collections.emptyList()));
        return;
_L2:
        flag2 = false;
          goto _L11
_L9:
        flag1 = false;
          goto _L12
_L5:
        flag = false;
          goto _L13
        flag3 = false;
          goto _L14
    }
}
