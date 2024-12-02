package com.example.yes1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class PercentageCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var percentage: Int = 0
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arcRect = RectF()
    private var gradientShader: Shader? = null

    // Store the start and end colors for the gradient
    private var startColor: Int = Color.RED // Default start color
    private var endColor: Int = Color.GREEN // Default end color

    init {
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = 15f
        circlePaint.strokeCap = Paint.Cap.ROUND

        textPaint.color = Color.BLACK
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 35f
    }

    fun setPercentage(percentage: Int) {
        this.percentage = percentage.coerceIn(0, 100)
        invalidate()
    }

    fun setGradientColors(startColor: Int, endColor: Int) {
        this.startColor = startColor
        this.endColor = endColor

        gradientShader = LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(),
            intArrayOf(startColor, endColor),
            null,
            Shader.TileMode.CLAMP
        )
        circlePaint.shader = gradientShader
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Recreate the gradient with the updated view size
        setGradientColors(startColor, endColor)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY) - circlePaint.strokeWidth / 2

        arcRect.set(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        if (circlePaint.shader == null) {
            circlePaint.color = Color.LTGRAY
        }

        val sweepAngle = (360 * percentage) / 100f
        canvas.drawArc(arcRect, -90f, sweepAngle, false, circlePaint)

        val textY = centerY - ((textPaint.descent() + textPaint.ascent()) / 2)
        canvas.drawText("$percentage%", centerX, textY, textPaint)
    }
}
