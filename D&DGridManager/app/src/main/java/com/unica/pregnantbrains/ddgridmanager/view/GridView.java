package com.unica.pregnantbrains.ddgridmanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.unica.pregnantbrains.ddgridmanager.model.CoordinateTransformer;
import com.unica.pregnantbrains.ddgridmanager.model.GridData;
import com.unica.pregnantbrains.ddgridmanager.model.LineCollection;
import com.unica.pregnantbrains.ddgridmanager.model.PointF;
import com.unica.pregnantbrains.ddgridmanager.model.TokenCollection;
import com.unica.pregnantbrains.ddgridmanager.model.Line;
import com.unica.pregnantbrains.ddgridmanager.model.Token;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.DrawInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.EraserInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridRepositioningInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.GridViewInteractionMode;
import com.unica.pregnantbrains.ddgridmanager.view.interaction.TokenManipulationInteractionMode;

public final class GridView extends View {
    Paint paint = new Paint();

    boolean shouldDrawTokens = false;

    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleDetector;
    private GridViewInteractionMode mGestureListener;
    private LineCollection mActiveLines;

    public int newLineColor = Color.BLACK; /***QUI SI SCEGLIERANNO I COLORI DELLE LINEE*/
    public float newLineStrokeWidth = .2f;
    private GridData mData;

    public GridView(Context context) {
        super(context);
        //mData = data;
        //mActiveLines = data.mBackgroundLines;

        setFocusable(true);
        setFocusableInTouchMode(true);

        paint.setAntiAlias(true);
        this.setTokenManipulationMode();
        this.setOnDragListener(mOnDrag);
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
        mActiveLines = getData().mBackgroundLines;
        shouldDrawTokens = false;
    }

    public void useAnnotationLayer() {
        mActiveLines = getData().mAnnotationLines;
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
        getData().grid.draw(canvas, getData().transformer);
        //getData().mBackgroundLines.drawAllLines(canvas, getData().transformer);

        //canvas.save();
        //getData().transformer.setMatrix(canvas);
        getData().getBackgroundLines().drawAllLines(canvas, getData().transformer);
        //canvas.restore();

        getData().tokens.drawAllTokens(canvas, getGridSpaceTransformer());

        if (this.shouldDrawTokens) {
            //getData().mAnnotationLines.drawAllLines(canvas, getData().transformer);
            //canvas.save();
            //getData().transformer.setMatrix(canvas);
            getData().getAnnotationLines().drawAllLines(canvas, getData().transformer);
            //canvas.restore();
        }

        this.mGestureListener.draw(canvas);
    }

    public CoordinateTransformer getTransformer() {
        return this.getData().transformer;
    }

    public Line createLine() {
        if (mActiveLines == null) {
            this.mActiveLines = getData().mBackgroundLines;
        }
       return mActiveLines.createLine(this.newLineColor, this.newLineStrokeWidth);
    }

    public void clearAll() {
        this.getData().mBackgroundLines.clear();
        this.getData().mAnnotationLines.clear();
        this.getData().tokens.clear();
        invalidate();
    }

    public void optimizeActiveLines() {
        mActiveLines.optimize();
    }

    public void placeToken(Token t) {
        PointF attemptedLocationScreenSpace = new PointF(this.getWidth() / 2, this.getHeight() / 2);
        PointF attemptedLocationGridSpace = this.getData().grid.gridSpaceToScreenSpaceTransformer(this.getData().transformer).screenSpaceToWorldSpace(attemptedLocationScreenSpace);
        getData().tokens.placeTokenNearby(t, attemptedLocationGridSpace, getData().grid);
        this.getData().tokens.addToken(t);
        invalidate();
    }

    public View.OnDragListener mOnDrag = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            Log.d("DRAG", Integer.toString(event.getAction()));
            if (event.getAction() == DragEvent.ACTION_DROP) {
                Token toAdd = (Token) event.getLocalState();
                PointF location = getGridSpaceTransformer().screenSpaceToWorldSpace(new PointF(event.getX(), event.getY()));
                location = getData().grid.getNearestSnapPoint(location, toAdd.getSize());
                toAdd.setLocation(location);
                getData().tokens.addToken(toAdd);
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
        return getData().tokens;
    }
    public CoordinateTransformer getGridSpaceTransformer() {
        return getData().grid.gridSpaceToScreenSpaceTransformer(getData().transformer);
    }

    public void setNewLineStroke (int i) {
        float w;
        switch (i) {
            case 0:
                w = .05f;
                break;
            case 1:
                w = .1f;
                break;
            case 2:
                w = .2f;
                break;
            case 3:
                w = .4f;
                break;
            default:
                w = .05f;
        }
        this.newLineStrokeWidth = w;
    }

    public void setNewLineColor(int c){

        this.newLineColor=c;
    }

    public void setData(GridData data) {
        boolean useBackgroundLines = (mData == null) || this.mActiveLines == mData.getBackgroundLines();
        mData = data;
        this.mActiveLines = useBackgroundLines ? mData.getBackgroundLines() : mData.getAnnotationLines();
        invalidate();
    }
    public GridData getData() {
        return mData;
    }

    public Bitmap getPreview() {
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        getData().grid.drawBackground(canvas);
        //getData().mBackgroundLines.drawAllLines(canvas, getData().transformer);
        //canvas.save();
        //getData().transformer.setMatrix(canvas);
        getData().getBackgroundLines().drawAllLines(canvas, getData().transformer);
        //canvas.restore();

        getData().tokens.drawAllTokens(canvas, getGridSpaceTransformer());
        //getData().mAnnotationLines.drawAllLines(canvas, getData().transformer);
        //canvas.save();
        //getData().transformer.setMatrix(canvas);
        getData().getAnnotationLines().drawAllLines(canvas, getData().transformer);
        //canvas.restore();

        return bitmap;
    }
}