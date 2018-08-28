// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import android.content.Context;
import android.os.Bundle;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.social.analytics.AnalyticsEvent;
import com.google.android.libraries.social.analytics.AuthenticationProvider;
import com.google.android.libraries.social.analytics.events.UserEvent;
import com.google.android.libraries.social.analytics.events.handler.lite.UserEventLiteProtoPopulator;
import com.google.android.libraries.social.analytics.impl.EventHandler;

final class UserEventHandler
    implements EventHandler
{

    private final AuthenticationProvider authProvider;
    public final GcoreClearcutLogger gcoreClearcutLogger;
    public final UserEventLiteProtoPopulator populator;

    UserEventHandler(AuthenticationProvider authenticationprovider, GcoreClearcutLogger gcoreclearcutlogger, UserEventLiteProtoPopulator usereventliteprotopopulator)
    {
        authProvider = authenticationprovider;
        gcoreClearcutLogger = gcoreclearcutlogger;
        populator = usereventliteprotopopulator;
    }

    public final boolean handleEvent(AnalyticsEvent analyticsevent, Bundle bundle)
    {
        if (analyticsevent instanceof UserEvent)
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final UserEventHandler arg$1;
                private final AnalyticsEvent arg$2;
                private final Bundle arg$3;

                public final void run()
                {
                    Object obj1 = arg$1;
                    Object obj2 = arg$2;
                    Object obj = arg$3;
                    Object obj4 = (UserEvent)obj2;
                    obj = ((Bundle) (obj)).getString("CALENDAR_EVENT_HANDLER_ACCOUNT_NAME");
                    Object obj3 = (com.google.calendar.client.events.logging.CalendarClientVisualElementMetadata.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarClientVisualElementMetadata.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    UserEventLiteProtoPopulator usereventliteprotopopulator = ((UserEventHandler) (obj1)).populator;
                    obj2 = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    Object obj5 = ((UserEvent) (obj4)).visualElementPath.visualElements.iterator();
                    do
                    {
                        if (!((Iterator) (obj5)).hasNext())
                        {
                            break;
                        }
                        VisualElement visualelement = (VisualElement)((Iterator) (obj5)).next();
                        int j;
                        if ((((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance).bitField0_ & 1) == 1)
                        {
                            int i = visualelement.tag.id;
                            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
                            com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto ancestryvisualelementproto = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance;
                            if (!ancestryvisualelementproto.pathToRootElementId_.isModifiable())
                            {
                                ancestryvisualelementproto.pathToRootElementId_ = GeneratedMessageLite.mutableCopy(ancestryvisualelementproto.pathToRootElementId_);
                            }
                            ancestryvisualelementproto.pathToRootElementId_.addInt(i);
                        } else
                        {
                            j = visualelement.tag.id;
                            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
                            com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto ancestryvisualelementproto1 = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance;
                            ancestryvisualelementproto1.bitField0_ = ancestryvisualelementproto1.bitField0_ | 1;
                            ancestryvisualelementproto1.elementId_ = j;
                        }
                        if ((((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance).bitField0_ & 2) == 2)
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        if (j == 0 && (visualelement instanceof Indexed))
                        {
                            Object obj6 = ((Indexed)visualelement).getIndex();
                            if (obj6 != null)
                            {
                                j = ((Integer) (obj6)).intValue();
                                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
                                obj6 = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance;
                                obj6.bitField0_ = ((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto) (obj6)).bitField0_ | 2;
                                obj6.elementIndex_ = j;
                            }
                        }
                        if (obj3 != null && !com/google/android/libraries/social/analytics/visualelement/VisualElement.equals(visualelement.getClass()) && !(visualelement instanceof IndexedVisualElement))
                        {
                            VisualElementLiteMetadataHandler visualelementlitemetadatahandler = (VisualElementLiteMetadataHandler)usereventliteprotopopulator.handlers.get(visualelement.getClass());
                            if (visualelementlitemetadatahandler != null)
                            {
                                visualelementlitemetadatahandler.handleMetadata(visualelement, ((com.google.protobuf.MessageLite.Builder) (obj3)));
                            }
                        }
                    } while (true);
                    int k = ((UserEvent) (obj4)).userAction;
                    if (k != -1)
                    {
                        obj5 = com.google.common.logging.proto2api.UserActionEnum.UserAction.forNumber(k);
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
                        obj4 = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto.Builder) (obj2)).instance;
                        if (obj5 == null)
                        {
                            throw new NullPointerException();
                        }
                        obj4.bitField0_ = ((com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto) (obj4)).bitField0_ | 4;
                        obj4.userAction_ = ((com.google.common.logging.proto2api.UserActionEnum.UserAction) (obj5)).value;
                    }
                    if (false)
                    {
                        throw new NullPointerException();
                    }
                    obj5 = (com.google.calendar.client.events.logging.CalendarClientEventsExtension.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarClientEventsExtension.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    obj4 = (com.google.calendar.client.events.logging.CalendarClientVisualElementEntry.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarClientVisualElementEntry.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    CalendarClientVisualElementEntry calendarclientvisualelemententry = (CalendarClientVisualElementEntry)((com.google.calendar.client.events.logging.CalendarClientVisualElementEntry.Builder) (obj4)).instance;
                    calendarclientvisualelemententry.visualElementMetadata_ = (CalendarClientVisualElementMetadata)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).build();
                    calendarclientvisualelemententry.bitField0_ = calendarclientvisualelemententry.bitField0_ | 2;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    obj3 = (CalendarClientVisualElementEntry)((com.google.calendar.client.events.logging.CalendarClientVisualElementEntry.Builder) (obj4)).instance;
                    obj3.ancestryVisualElement_ = (com.google.common.logging.AncestryVisualElement.AncestryVisualElementProto)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).build();
                    obj3.bitField0_ = ((CalendarClientVisualElementEntry) (obj3)).bitField0_ | 1;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).copyOnWrite();
                    obj2 = (CalendarClientEventsExtension)((com.google.calendar.client.events.logging.CalendarClientEventsExtension.Builder) (obj5)).instance;
                    obj2.visualElementEntry_ = (CalendarClientVisualElementEntry)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
                    obj2.bitField0_ = ((CalendarClientEventsExtension) (obj2)).bitField0_ | 4;
                    obj2 = (CalendarClientEventsExtension)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).build();
                    obj1 = ((UserEventHandler) (obj1)).gcoreClearcutLogger;
                    obj2.getClass();
                    class .Lambda._cls1
                        implements GcoreClearcutMessageProducer
                    {

                        private final CalendarClientEventsExtension arg$1;

                        public final byte[] toProtoBytes()
                        {
                            return arg$1.toByteArray();
                        }

                            .Lambda._cls1(CalendarClientEventsExtension calendarclienteventsextension)
                            {
                                arg$1 = calendarclienteventsextension;
                            }
                    }

                    obj1 = ((GcoreClearcutLogger) (obj1)).newEvent(new .Lambda._cls1(((CalendarClientEventsExtension) (obj2))));
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        ((GcoreClearcutLogEventBuilder) (obj1)).setUploadAccountName(((String) (obj)));
                    }
                    ((GcoreClearcutLogEventBuilder) (obj1)).logAsync();
                }

            .Lambda._cls0(AnalyticsEvent analyticsevent, Bundle bundle)
            {
                arg$1 = UserEventHandler.this;
                arg$2 = analyticsevent;
                arg$3 = bundle;
            }
            }

            CalendarExecutor.BACKGROUND.execute(new .Lambda._cls0(analyticsevent, bundle));
            return true;
        } else
        {
            return false;
        }
    }

    public final void populateBundle(Context context, AnalyticsEvent analyticsevent, Bundle bundle)
    {
        bundle.putString("CALENDAR_EVENT_HANDLER_ACCOUNT_NAME", analyticsevent.getAccountName(context, authProvider));
    }
}
