// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.calendar.model:
//            Action, Article, CloudMediaObject, EmailMessage, 
//            EventReservation, Event2, ExperimentalSmartMail, FlightReservation, 
//            GenericSmartMail, LodgingReservation, Movie, Offer, 
//            Order, ParcelDelivery, Product, Promotion, 
//            RestaurantReservation, Restaurant

public final class SmartMailInfo extends GenericJson
{

    public List actions;
    public List articles;
    public String authenticatedScheme;
    public List cloudMediaObjects;
    public List emailMessages;
    public List eventReservations;
    public List events;
    public List experimentalSmartMails;
    public List flightReservations;
    public List genericSmartmails;
    public Boolean hideMessageSnippet;
    public List lodgingReservations;
    public String messageId;
    public List movies;
    public List offers;
    public List orders;
    public List parcelDeliverys;
    public List products;
    public List promotions;
    public List restaurantReservations;
    public List restaurants;
    public String senderDomain;
    public List socialNetworkComments;
    public List socialNetworkPosts;
    public String source;
    public List trips;
    public List typedSmartmails;
    public List updates;
    public List videos;

    public SmartMailInfo()
    {
    }

    public final volatile GenericJson clone()
    {
        return (SmartMailInfo)clone();
    }

    public final volatile GenericData clone()
    {
        return (SmartMailInfo)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (SmartMailInfo)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (SmartMailInfo)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (SmartMailInfo)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/calendar/model/Action);
        Data.nullOf(com/google/api/services/calendar/model/Article);
        Data.nullOf(com/google/api/services/calendar/model/CloudMediaObject);
        Data.nullOf(com/google/api/services/calendar/model/EmailMessage);
        Data.nullOf(com/google/api/services/calendar/model/EventReservation);
        Data.nullOf(com/google/api/services/calendar/model/Event2);
        Data.nullOf(com/google/api/services/calendar/model/ExperimentalSmartMail);
        Data.nullOf(com/google/api/services/calendar/model/FlightReservation);
        Data.nullOf(com/google/api/services/calendar/model/GenericSmartMail);
        Data.nullOf(com/google/api/services/calendar/model/LodgingReservation);
        Data.nullOf(com/google/api/services/calendar/model/Movie);
        Data.nullOf(com/google/api/services/calendar/model/Offer);
        Data.nullOf(com/google/api/services/calendar/model/Order);
        Data.nullOf(com/google/api/services/calendar/model/ParcelDelivery);
        Data.nullOf(com/google/api/services/calendar/model/Product);
        Data.nullOf(com/google/api/services/calendar/model/Promotion);
        Data.nullOf(com/google/api/services/calendar/model/RestaurantReservation);
        Data.nullOf(com/google/api/services/calendar/model/Restaurant);
    }
}
