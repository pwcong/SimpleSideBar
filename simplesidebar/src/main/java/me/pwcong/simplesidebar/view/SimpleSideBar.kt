package me.pwcong.simplesidebar.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

import me.pwcong.simplesidebar.R

class SimpleSideBar : View {
    private var onLetterTouchedChangeListener: OnLetterTouchedChangeListener? = null

    fun setOnLetterTouchedChangeListener(onLetterTouchedChangeListener: OnLetterTouchedChangeListener?) {
        this.onLetterTouchedChangeListener = onLetterTouchedChangeListener
    }

    // 索引字母数组
    private val alphabet = arrayOf(
        "A", "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X",
        "Y", "Z"
    )

    // 当前选择的索引字母的下标
    private var currentChoosenAlphabetIndex = -1

    private val mPaint = Paint()

    private val alphabetTextSize = 20

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val viewHeight = height
        val viewWidth = width
        val heightPerAlphabet = viewHeight / alphabet.size

        for (i in alphabet.indices) {
            mPaint.color = Color.BLACK
            mPaint.setTypeface(Typeface.DEFAULT_BOLD)
            mPaint.textSize = alphabetTextSize.toFloat()
            mPaint.isAntiAlias = true

            if (currentChoosenAlphabetIndex == i) {
                mPaint.color = Color.YELLOW
                mPaint.textSize = alphabetTextSize.toFloat()
                mPaint.isFakeBoldText = true
            }

            val xPos = viewWidth / 2 - mPaint.measureText(alphabet[i]) / 2
            val yPos = (heightPerAlphabet * i + heightPerAlphabet).toFloat()
            canvas.drawText(alphabet[i], xPos, yPos, mPaint)
            mPaint.reset()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val action = event.action

        val touchYPos = event.y


        val currentTouchIndex = (touchYPos / height * alphabet.size).toInt()


        when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                setBackgroundResource(R.color.grey_active)
                currentChoosenAlphabetIndex = currentTouchIndex

                // 如果接口
                if (onLetterTouchedChangeListener != null && currentTouchIndex < alphabet.size && currentTouchIndex > -1) {
                    onLetterTouchedChangeListener!!.onTouchedLetterChange(alphabet[currentTouchIndex])
                }

                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                setBackgroundResource(R.color.grey)
                currentChoosenAlphabetIndex = -1
                invalidate()
            }

            else -> {}
        }
        return true
    }

    interface OnLetterTouchedChangeListener {
        fun onTouchedLetterChange(letterTouched: String?)
    }
}
