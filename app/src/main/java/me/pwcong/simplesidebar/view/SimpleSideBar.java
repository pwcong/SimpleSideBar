package me.pwcong.simplesidebar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import me.pwcong.simplesidebar.R;

/**
 * Created by pwcong on 2016/8/2.
 */
public class SimpleSideBar extends View {

    OnLetterTouchedChangeListener onLetterTouchedChangeListener;

    public void setOnLetterTouchedChangeListener(OnLetterTouchedChangeListener onLetterTouchedChangeListener) {
        this.onLetterTouchedChangeListener = onLetterTouchedChangeListener;
    }

    // 索引字母数组
    private String[] alphabet = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    // 当前选择的索引字母的下标
    private int currentChoosenAlphabetIndex=-1;

    private Paint mPaint=new Paint();

    private int alphabetTextSize=20;

    public SimpleSideBar(Context context) {
        super(context);
    }

    public SimpleSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewHeight=getHeight();
        int viewWidth=getWidth();
        int heightPerAlphabet=viewHeight/alphabet.length;

        for (int i=0;i<alphabet.length;i++){

            mPaint.setColor(Color.BLACK);
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mPaint.setTextSize(alphabetTextSize);
            mPaint.setAntiAlias(true);

            if(currentChoosenAlphabetIndex==i){

                mPaint.setColor(Color.YELLOW);
                mPaint.setTextSize(alphabetTextSize);
                mPaint.setFakeBoldText(true);

            }

            float xPos=viewWidth/2-mPaint.measureText(alphabet[i])/2;
            float yPos=heightPerAlphabet*i+heightPerAlphabet;
            canvas.drawText(alphabet[i],xPos,yPos,mPaint);
            mPaint.reset();

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int action = event.getAction();

        float touchYPos=event.getY();


        int currentTouchIndex= (int) (touchYPos/getHeight()*alphabet.length);


        switch (action){

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                setBackgroundResource(R.color.grey_600);
                currentChoosenAlphabetIndex=currentTouchIndex;

                // 如果接口
                if(onLetterTouchedChangeListener!=null&&currentTouchIndex<alphabet.length&&currentTouchIndex>-1){
                    onLetterTouchedChangeListener.onTouchedLetterChange(alphabet[currentTouchIndex]);
                }

                invalidate();


                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.color.grey_50);
                currentChoosenAlphabetIndex=-1;
                invalidate();
                break;
            default:break;

        }

        return true;
    }

    public interface OnLetterTouchedChangeListener{

        void onTouchedLetterChange(String letterTouched);

    }

}
