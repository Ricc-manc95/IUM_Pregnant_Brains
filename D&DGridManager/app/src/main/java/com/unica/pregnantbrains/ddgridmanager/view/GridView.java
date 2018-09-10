package com.unica.pregnantbrains.ddgridmanager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.LineCollection;
import com.unica.pregnantbrains.ddgridmanager.model.TokenCollection;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Line;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.DrawInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.EraserInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridRepositioningInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridViewInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.TokenManipulationInteractionMode;

public class GridView extends View {
    Paint paint = new Paint();

    boolean shouldDrawTokens = false;

    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleDetector;
    private GridViewInteractionMode mGestureListener;
    private LineCollection mActiveLines;

    public int newLineColor = Color.BLACK; /***QUI SI SCEGLIERANNO I COLORI DELLE LINEE*/
    public int newLineStrokeWidth = 2;
    public GridData mData;

    public GridView(Context context, GridData data) {
        super(context);

        mData = data;
        mActiveLines = data.mBackgroundLines;

        setFocusable(true);
        setFocusableInTouchMode(true);

        paint.setAntiAlias(true);
        this.setTokenManipulationMode();
        this.setOnDragListener(mOnDrag);
    }

    public void setZoomPanMode() {
        setGestureListener(new GridViewInteractionMode(this));
    }

    public void setTokenManipulationMode() {
        setGestureListener(new TokenManipulationInteractionMode(this));
        shouldDrawTokens = true;
    }

    public void setDrawMode() {
        setGestureListener(new DrawInteractionMode(this));
    }

    public void setEraseMode() {
        setGestureListener(new EraserInteractionMode(this));
    }

    public void setResizeGridMode() {
        setGestureListener(new GridRepositioningInteractionMode(this));
    }

    public void useBackgroundLayer() {
        mActiveLines = mData.mBackgroundLines;
        shouldDrawTokens = false;
    }

    public void useAnnotationLayer() {
        mActiveLines = mData.mAnnotationLines;
        shouldDrawTokens = true;
    }

    public void setEraseAnnotationMode() {
        setGestureListener(new EraserInteractionMode(this));
        useAnnotationLayer();
        shouldDrawTokens = true;
    }

    private void setGestureListener(GridViewInteractionMode listener) {
        gestureDetector = new GestureDetector(this.getContext(), listener);
        gestureDetector.setOnDoubleTapListener(listener);
        scaleDetector = new ScaleGestureDetector(this.getContext(), listener);
        mGestureListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        this.gestureDetector.onTouchEvent(ev);
        this.scaleDetector.onTouchEvent(ev);

        //If a finger was removed, optimize the lines by removing unused points.
        //TODO(tim.bocek): Only do this if we are erasing.
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            this.mGestureListener.onUp(ev);
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // White background
        mData.grid.draw(canvas, mData.transformer);
        mData.mBackgroundLines.drawAllLines(canvas, mData.transformer);
        mData.tokens.drawAllTokens(canvas, getGridSpaceTransformer());

        if (this.shouldDrawTokens) {
            mData.mAnnotationLines.drawAllLines(canvas, mData.transformer);
        }

        this.mGestureListener.draw(canvas);
    }

    public CoordinateTransformer getTransformer() {
        return this.mData.transformer;
    }

    public Line createLine() {
       return mActiveLines.createLine(this.newLineColor, this.newLineStrokeWidth);
    }

    public void clearAll() {
        this.mData.mBackgroundLines.clear();
        this.mData.mAnnotationLines.clear();
        this.mData.tokens.clear();
        invalidate();
    }

    public void optimizeActiveLines() {
        mActiveLines.optimize();
    }

    public void placeToken(Token t) {
        PointF attemptedLocationScreenSpace = new PointF(this.getWidth() / 2, this.getHeight() / 2);
        PointF attemptedLocationGridSpace = this.mData.grid.gridSpaceToScreenSpaceTransformer(this.mData.transformer).screenSpaceToWorldSpace(attemptedLocationScreenSpace);
        mData.tokens.placeTokenNearby(t, attemptedLocationGridSpace, mData.grid);
        this.mData.tokens.addToken(t);
        invalidate();
    }

    public View.OnDragListener mOnDrag = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            Log.d("DRAG", Integer.toString(event.getAction()));
            if (event.getAction() == DragEvent.ACTION_DROP) {
                Token toAdd = (Token) event.getLocalState();
                PointF location = getGridSpaceTransformer().screenSpaceToWorldSpace(new PointF(event.getX(), event.getY()));
                location = mData.grid.getNearestSnapPoint(location, toAdd.getSize());
                toAdd.setLocation(location);
                mData.tokens.addToken(toAdd);
                invalidate();
                return true;
            }
            else {
                return event.getAction() == DragEvent.ACTION_DRAG_STARTED;
            }
        }
    };
    public LineCollection getActiveLines() {
        return mActiveLines;
    }
    public TokenCollection getTokens() {
        return mData.tokens;
    }
    public CoordinateTransformer getGridSpaceTransformer() {
        return mData.grid.gridSpaceToScreenSpaceTransformer(mData.transformer);
    }

    public void setNewLineStroke (int i) {
        switch (i) {
            case 0:
                this.newLineStrokeWidth = 2;
            case 1:
                this.newLineStrokeWidth = 4;
            case 2:
                this.newLineStrokeWidth = 6;
            case 3:
                this.newLineStrokeWidth = 8;
            case 4:
                this.newLineStrokeWidth = 10;
            case 5:
                this.newLineStrokeWidth = 12;
        }
    }
}