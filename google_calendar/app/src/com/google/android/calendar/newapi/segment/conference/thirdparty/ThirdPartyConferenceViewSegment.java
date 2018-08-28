// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.apps.calendar.util.concurrent.Cancelable;
import com.google.android.apps.calendar.util.concurrent.CancelableHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.expandable.ExpandableViewSegment;
import com.google.android.calendar.newapi.segment.conference.thirdparty.adapter.ConferenceAdapter;
import com.google.android.calendar.newapi.segment.conference.thirdparty.adapter.ConferenceDataAdapter;
import com.google.android.calendar.newapi.segment.conference.thirdparty.utils.ThirdPartyConferenceUtils;
import com.google.android.calendar.newapi.segment.note.ConferenceTileView;
import com.google.android.calendar.newapi.segment.note.NoteHtmlConverter;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.snackbar.SnackbarShower;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference.thirdparty:
//            ConferenceSolutionResources

public final class ThirdPartyConferenceViewSegment extends ExpandableViewSegment
{

    private final EventHolder model;
    private final TextTileView moreTile = (TextTileView)findViewById(0x7f10029c);
    private final ConferenceTileView noteTile = (ConferenceTileView)findViewById(0x7f10029b);
    private final TextTileView phoneTile = (TextTileView)findViewById(0x7f10025e);
    private final CancelableHolder setIconImageCancelableHolder = new CancelableHolder();
    private final TextTileView sipTile = (TextTileView)findViewById(0x7f10029a);
    private final SnackbarShower snackbarShower;
    private final TextTileView solutionTile;
    private final TextTileView videoTile = (TextTileView)findViewById(0x7f10025c);

    public ThirdPartyConferenceViewSegment(Context context, SnackbarShower snackbarshower, EventHolder eventholder)
    {
        super(context, 0x7f0500eb);
        snackbarShower = snackbarshower;
        model = eventholder;
        solutionTile = super.titleView;
        solutionTile.setIconDrawable(0x7f020122);
        solutionTile.getIcon().setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
    }

    private static void updateTile(TextTileView texttileview, Optional optional, boolean flag)
    {
        if (!optional.isPresent())
        {
            texttileview.setVisibility(8);
        } else
        {
            ConferenceAdapter conferenceadapter = (ConferenceAdapter)optional.get();
            texttileview.setPrimaryText(new CharSequence[] {
                conferenceadapter.getPrimaryText()
            });
            optional = texttileview.primaryLine;
            optional.setEllipsize(android.text.TextUtils.TruncateAt.END);
            optional.setMaxLines(1);
            optional = ImmutableList.builder();
            conferenceadapter.addAccessInfo(conferenceadapter.meetingCode, 0x7f13032e, optional);
            conferenceadapter.addAccessInfo(conferenceadapter.accessCode, 0x7f130048, optional);
            conferenceadapter.addAccessInfo(conferenceadapter.passcode, 0x7f13037b, optional);
            conferenceadapter.addAccessInfo(conferenceadapter.password, 0x7f13037c, optional);
            conferenceadapter.addAccessInfo(conferenceadapter.pin, 0x7f130383, optional);
            optional.forceCopy = true;
            optional = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (optional)).contents, ((com.google.common.collect.ImmutableList.Builder) (optional)).size);
            if (optional == null)
            {
                optional = null;
            } else
            {
                optional = (CharSequence[])optional.toArray(new CharSequence[optional.size()]);
            }
            texttileview.setSecondaryText(optional);
            texttileview.setVisibility(0);
            texttileview.setOnClickListener(conferenceadapter);
            if (flag)
            {
                texttileview.setOnLongClickListener(conferenceadapter);
                return;
            }
        }
    }

    public final void updateUi()
    {
        byte byte0 = 8;
        Object obj = ConferenceDataAdapter.fromConferenceData(getContext(), snackbarShower, model.getEvent().getConferenceData());
        if (!ThirdPartyConferenceUtils.showThirdPartyConferenceSegment(true, model.getEvent().getConferenceData()))
        {
            setVisibility(8);
        } else
        {
            int i;
            if (((ConferenceDataAdapter) (obj)).videoEntryPoint.isPresent() || ((ConferenceDataAdapter) (obj)).phoneEntryPoint.isPresent())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (this != null)
            {
                Object obj1;
                Object obj2;
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                setVisibility(i);
            }
            solutionTile.setPrimaryText(new CharSequence[] {
                ((ConferenceDataAdapter) (obj)).solutionName
            });
            obj1 = setIconImageCancelableHolder;
            obj2 = solutionTile.getIcon();
            obj2 = CalendarFutures.asyncGet(ConferenceSolutionResources.iconMax24(getResources().getDisplayMetrics(), model.getEvent().getConferenceData().getConferenceSolution()), new com.google.android.apps.calendar.image.ImageViewUtils..Lambda._cls0(((ImageView) (obj2))), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
            ((Cancelable)((CancelableHolder) (obj1)).previousCancelableRef.getAndSet(obj2)).cancel(true);
            obj1 = ((ConferenceDataAdapter) (obj)).videoEntryPoint;
            updateTile(videoTile, ((Optional) (obj1)), true);
            obj1 = ((ConferenceDataAdapter) (obj)).phoneEntryPoint;
            updateTile(phoneTile, ((Optional) (obj1)), true);
            obj1 = ((ConferenceDataAdapter) (obj)).sipEntryPoint;
            updateTile(sipTile, ((Optional) (obj1)), true);
            obj1 = ((ConferenceDataAdapter) (obj)).moreEntryPoint;
            updateTile(moreTile, ((Optional) (obj1)), false);
            obj1 = ((ConferenceDataAdapter) (obj)).notes;
            obj = noteTile;
            if (!Platform.stringIsNullOrEmpty(((String) (obj1))))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj != null)
            {
                if (i != 0)
                {
                    byte0 = 0;
                }
                ((View) (obj)).setVisibility(byte0);
            }
            if (!Platform.stringIsNullOrEmpty(((String) (obj1))))
            {
                obj = obj1;
                if (NoteHtmlConverter.isHtml(((String) (obj1))))
                {
                    obj = NoteHtmlConverter.toFormattedText(((String) (obj1)));
                }
                obj = (ConferenceTileView)noteTile.setPrimaryText(new CharSequence[] {
                    obj
                });
                return;
            }
        }
    }
}
