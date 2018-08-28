// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.flinging.tile.FlingingTileManager;
import com.google.android.apps.calendar.flinging.tile.OnFlingListener;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.meetings.util.MeetingsUtils;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.model.ConferenceEvent;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.segment.conference.thirdparty.utils.ThirdPartyConferenceUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.common.base.Platform;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceActions, ConferenceTypes, MeetTileUpdater, PhoneNumberDetailsFactory

public class ConferenceViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final TextTileView allButtonTiles[];
    private final TextTileView flingingTile;
    private final FlingingTileManager flingingTileManager;
    private final EventFragmentHostActivity fragmentHostActivity;
    private final TextTileView liveStreamTile;
    private final ConferenceEvent model;
    private Conference moreOptionsConference;
    private final TextTileView moreOptionsTile;
    private final TextTileView phoneTile;
    private Conference videoConference;
    private final TextTileView videoTile;

    public ConferenceViewSegment(Context context, LifecycleOwner lifecycleowner, OnFlingListener onflinglistener, ConferenceEvent conferenceevent)
    {
        super(context);
        boolean flag = context instanceof EventFragmentHostActivity;
        lifecycleowner = String.valueOf(context);
        lifecycleowner = (new StringBuilder(String.valueOf(lifecycleowner).length() + 51)).append("Context must be EventFragmentHostActivity, but was ").append(lifecycleowner).toString();
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf(lifecycleowner));
        } else
        {
            fragmentHostActivity = (EventFragmentHostActivity)context;
            model = conferenceevent;
            setOrientation(1);
            inflate(context, 0x7f0500ca, this);
            flingingTile = (TextTileView)findViewById(0x7f10025b);
            videoTile = (TextTileView)findViewById(0x7f10025c);
            liveStreamTile = (TextTileView)findViewById(0x7f10025d);
            liveStreamTile.setOnClickListener(this);
            phoneTile = (TextTileView)findViewById(0x7f10025e);
            phoneTile.setOnClickListener(this);
            moreOptionsTile = (TextTileView)findViewById(0x7f10025a);
            moreOptionsTile.setOnClickListener(this);
            allButtonTiles = (new TextTileView[] {
                flingingTile, videoTile, liveStreamTile, phoneTile
            });
            flingingTileManager = new FlingingTileManager();
            return;
        }
    }

    private final boolean isThirdConferenceTile(int i)
    {
        return RemoteFeatureConfig.CONFERENCE_PSTN.enabled() && i == 0x7f10025a && moreOptionsConference != null && moreOptionsConference.getType() == 5;
    }

    public void onClick(View view)
    {
        VisualElementAttacher visualelementattacher;
        int i;
        i = view.getId();
        if (i == 0x7f10025c)
        {
            ConferenceActions.joinVideoConference(getContext(), videoConference, ((CalendarHolder)model).getAccount());
        } else
        if (i == 0x7f10025d)
        {
            ConferenceActions.joinLiveStream(getContext(), ((EventHolder)model).getEvent());
        } else
        if (i == 0x7f10025e)
        {
            ConferenceActions.dialPhoneConference(getContext(), fragmentHostActivity, model, ((EventHolder)model).getEvent());
        } else
        {
label0:
            {
                if (!isThirdConferenceTile(i))
                {
                    break label0;
                }
                boolean flag;
                if (!TextUtils.isEmpty(moreOptionsConference.getPassCode()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
                ConferenceActions.showMoreThorPhones(getContext(), moreOptionsConference, ((EventHolder)model).getEvent());
            }
        }
_L1:
        visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).recordTap(getContext(), view, ((CalendarHolder)model).getAccount());
            return;
        }
        if (isThirdConferenceTile(i) && moreOptionsConference.getGatewayAccess() == 1)
        {
            ConferenceActions.showInteropInstructions(getContext(), moreOptionsConference, ((EventHolder)model).getEvent());
        }
          goto _L1
    }

    public final void updateUi()
    {
        Event event;
        boolean flag5;
        byte byte0;
        byte0 = 8;
        flag5 = false;
        event = ((EventHolder)model).getEvent();
        if (!ThirdPartyConferenceUtils.showThirdPartyConferenceSegment(RemoteFeatureConfig.THIRD_PARTY_CONFERENCES.enabled(), event.getConferenceData())) goto _L2; else goto _L1
_L1:
        setVisibility(8);
_L4:
        return;
_L2:
label0:
        {
            Object obj = event.getConferenceData();
            videoConference = ConferenceTypes.getVideoConference(getContext(), ((com.google.android.calendar.api.event.conference.ConferenceData) (obj)));
            moreOptionsConference = ConferenceTypes.getPhoneNumbersLinkConference(((com.google.android.calendar.api.event.conference.ConferenceData) (obj)));
            obj = ConferenceTypes.getLiveStreamConference(getContext(), ((com.google.android.calendar.api.event.conference.ConferenceData) (obj)));
            int i;
            boolean flag2;
            boolean flag4;
            int k1;
            if (ConferenceDataUtils.getConferenceType(((EventHolder)model).getEvent().getConferenceData()) == 3)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!ConferenceDataUtils.isCreationPending(((EventHolder)model).getEvent().getConferenceData()))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (videoConference != null || obj != null || ConferenceDataUtils.isCreationPending(((EventHolder)model).getEvent().getConferenceData()))
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (this != null)
            {
                TextTileView texttileview;
                if (flag4)
                {
                    k1 = 0;
                } else
                {
                    k1 = 8;
                }
                setVisibility(k1);
            }
            texttileview = phoneTile;
            if (i != 0 && flag2)
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            if (texttileview != null)
            {
                if (k1 != 0)
                {
                    k1 = 0;
                } else
                {
                    k1 = 8;
                }
                texttileview.setVisibility(k1);
            }
            texttileview = moreOptionsTile;
            if (texttileview != null)
            {
                texttileview.setVisibility(8);
            }
            if (!flag4)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!flag2)
            {
                texttileview = videoTile;
                if (i != 0)
                {
                    i = 0x7f130134;
                } else
                {
                    i = 0x7f1302e5;
                }
                texttileview.setPrimaryText(new CharSequence[] {
                    texttileview.getResources().getString(i, new Object[0])
                });
                texttileview = videoTile;
                texttileview.setSecondaryText(new CharSequence[] {
                    texttileview.getResources().getString(0x7f130133, new Object[0])
                });
                videoTile.setOnClickListener(null);
            } else
            {
                videoTile.setOnClickListener(this);
                videoTile.treatAsButton(true);
                if (i != 0)
                {
                    MeetTileUpdater meettileupdater = new MeetTileUpdater(flingingTile, videoTile, liveStreamTile, phoneTile, moreOptionsTile);
                    PhoneNumberDetails phonenumberdetails = PhoneNumberDetailsFactory.create(model, event);
                    obj1 = VisualElementHolder.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                    }
                    ((VisualElementAttacher)obj1).attachJoinConference(meettileupdater.flingingTile, meettileupdater.videoTile, meettileupdater.liveStreamTile, meettileupdater.phoneTile, meettileupdater.moreOptionsTile, phonenumberdetails);
                    obj1 = ConferenceTypes.getVideoConference(meettileupdater.context, event.getConferenceData());
                    Object obj3 = meettileupdater.videoTile;
                    boolean flag;
                    if (obj1 != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (obj3 != null)
                    {
                        int k;
                        if (flag)
                        {
                            k = 0;
                        } else
                        {
                            k = 8;
                        }
                        ((View) (obj3)).setVisibility(k);
                    }
                    if (flag)
                    {
                        obj3 = meettileupdater.videoTile;
                        ((TextTileView) (obj3)).setPrimaryText(new CharSequence[] {
                            ((TextTileView) (obj3)).getResources().getString(0x7f130314, new Object[0])
                        });
                        meettileupdater.videoTile.setSecondaryText(new CharSequence[] {
                            MeetTileUpdater.getLabel(((Conference) (obj1)))
                        });
                        obj1 = VisualElementHolder.instance;
                        if (obj1 == null)
                        {
                            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                        }
                        ((VisualElementAttacher)obj1).recordImpression(meettileupdater.context, meettileupdater.videoTile);
                    }
                    obj1 = ConferenceTypes.getLiveStreamConference(meettileupdater.context, event.getConferenceData());
                    obj3 = meettileupdater.liveStreamTile;
                    if (obj1 != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (obj3 != null)
                    {
                        int l;
                        if (flag != 0)
                        {
                            l = 0;
                        } else
                        {
                            l = 8;
                        }
                        ((View) (obj3)).setVisibility(l);
                    }
                    if (flag != 0)
                    {
                        obj1 = meettileupdater.liveStreamTile;
                        ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
                            ((TextTileView) (obj1)).getResources().getString(0x7f130317, new Object[0])
                        });
                    }
                    obj1 = meettileupdater.phoneTile;
                    if (phonenumberdetails != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (obj1 != null)
                    {
                        l = byte0;
                        if (flag != 0)
                        {
                            l = 0;
                        }
                        ((View) (obj1)).setVisibility(l);
                    }
                    if (flag != 0)
                    {
                        obj1 = meettileupdater.phoneTile;
                        String s;
                        if (ExperimentalOptions.isFlingingEnabled$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLK___0())
                        {
                            flag = 0x7f130313;
                        } else
                        {
                            flag = 0x7f130312;
                        }
                        ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
                            ((TextTileView) (obj1)).getResources().getString(flag, new Object[0])
                        });
                        obj3 = meettileupdater.context.getResources().getConfiguration().locale;
                        obj1 = phonenumberdetails.getFormattedPhoneNumber(((Locale) (obj3)));
                        s = phonenumberdetails.region().getCountry();
                        if (!Platform.stringIsNullOrEmpty(s))
                        {
                            obj1 = String.format("(%s) %s", new Object[] {
                                s, obj1
                            });
                        }
                        obj3 = MeetingsUtils.formatPin(((Locale) (obj3)), phonenumberdetails.passCode());
                        if (Platform.stringIsNullOrEmpty(((String) (obj3))))
                        {
                            meettileupdater.phoneTile.setSecondaryText(new CharSequence[] {
                                obj1
                            });
                        } else
                        {
                            TextTileView texttileview2 = meettileupdater.phoneTile;
                            texttileview2.setSecondaryText(new CharSequence[] {
                                texttileview2.getResources().getString(0x7f130382, new Object[] {
                                    obj1, obj3
                                })
                            });
                            meettileupdater.phoneTile.getSecondaryTextView().setContentDescription(meettileupdater.context.getString(0x7f130382, new Object[] {
                                obj1, String.valueOf(phonenumberdetails.passCode()).concat("#").toString().replace(" ", "").replace("", " ").trim()
                            }));
                        }
                        obj1 = VisualElementHolder.instance;
                        if (obj1 == null)
                        {
                            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                        }
                        ((VisualElementAttacher)obj1).recordImpression(meettileupdater.context, meettileupdater.phoneTile);
                    }
                    obj1 = ConferenceTypes.getPhoneNumbersLinkConference(event.getConferenceData());
                    if (RemoteFeatureConfig.CONFERENCE_PSTN.enabled() && obj1 != null)
                    {
                        boolean flag1;
                        if (!TextUtils.isEmpty(((Conference) (obj1)).getPassCode()))
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (flag1 || ((Conference) (obj1)).getGatewayAccess() == 1)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (flag1)
                        {
                            if (((Conference) (obj1)).getGatewayAccess() == 1)
                            {
                                meettileupdater.moreOptionsTile.setPrimaryText(new CharSequence[] {
                                    meettileupdater.context.getString(0x7f13033e)
                                });
                            }
                            obj1 = meettileupdater.moreOptionsTile;
                            if (obj1 != null)
                            {
                                ((View) (obj1)).setVisibility(0);
                            }
                            obj1 = VisualElementHolder.instance;
                            if (obj1 == null)
                            {
                                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                            }
                            ((VisualElementAttacher)obj1).recordImpression(meettileupdater.context, meettileupdater.moreOptionsTile);
                        }
                    }
                } else
                {
                    obj1 = videoTile;
                    ((TextTileView) (obj1)).setPrimaryText(new CharSequence[] {
                        ((TextTileView) (obj1)).getResources().getString(0x7f1302e4, new Object[0])
                    });
                    videoTile.setSecondaryText(new CharSequence[] {
                        videoConference.getName()
                    });
                }
            }
            getContext();
            if (!ExperimentalOptions.isFlingingEnabled$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLK___0())
            {
                Object obj1 = Features.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj1).broadcasting();
                if (!RemoteFeatureConfig.BROADCASTING.enabled())
                {
                    break label0;
                }
            }
            TextTileView atexttileview[] = allButtonTiles;
            int i1 = atexttileview.length;
            flag1 = false;
            while (flag1 < i1) 
            {
                Object obj2 = atexttileview[flag1];
                if (((TextTileView) (obj2)).hasOnClickListeners())
                {
                    obj2 = ((TextTileView) (obj2)).primaryLine;
                    if (android.os.Build.VERSION.SDK_INT >= 23)
                    {
                        ((TextView) (obj2)).setTextAppearance(0x7f140003);
                    } else
                    {
                        ((TextView) (obj2)).setTextAppearance(((TextView) (obj2)).getContext(), 0x7f140003);
                    }
                    ((TextView) (obj2)).setPaddingRelative(0, Math.round(TypedValue.applyDimension(1, 2.0F, ((TextView) (obj2)).getContext().getResources().getDisplayMetrics())), 0, 0);
                }
                flag1++;
            }
        }
        TextTileView atexttileview1[] = allButtonTiles;
        int j1 = atexttileview1.length;
        boolean flag3 = false;
        int j = ((flag5) ? 1 : 0);
        while (j < j1) 
        {
            TextTileView texttileview1 = atexttileview1[j];
            if (!flag3 && texttileview1.getVisibility() == 0)
            {
                texttileview1.setIconDrawable(0x7f020203);
                flag3 = true;
            } else
            {
                texttileview1.setIconDrawable(null);
            }
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static 
    {
        com/google/android/calendar/newapi/segment/conference/ConferenceViewSegment.getSimpleName();
    }
}
