package com.project.bibit_test.helper.avatar

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint
import java.util.*

class AvatarGenerator {
    companion object {
        private lateinit var uiContext: Context
        private var texSize = 0F

        fun avatarImage(
            context: Context,
            size: Int,
            shape: Int,
            name: String,
            specificColor: Int): BitmapDrawable {
            return avatarImageGenerate(context, size, shape, name, specificColor)
        }

        private fun avatarImageGenerate(
            context: Context,
            size: Int,
            shape: Int,
            name: String,
            specificColor: Int
        ): BitmapDrawable {
            uiContext = context

            texSize = calTextSize(size)
            val label = firstCharacter(name)
            val textPaint = textPainter()
            val painter = painter()
            painter.isAntiAlias = true
            val areaRect = Rect(0, 0, size, size)

            if (shape == AvatarConstants.RECTANGLE) {
                painter.color = specificColor
            } else {
                painter.color = Color.TRANSPARENT
            }

            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawRect(areaRect, painter)

            //reset painter
            if (shape ==  AvatarConstants.RECTANGLE) {
                painter.color = Color.TRANSPARENT
            } else {
                painter.color = specificColor
            }

            val bounds = RectF(areaRect)
            bounds.right = textPaint.measureText(label, 0, 1)
            bounds.bottom = textPaint.descent() - textPaint.ascent()

            bounds.left += (areaRect.width() - bounds.right) / 2.0f
            bounds.top += (areaRect.height() - bounds.bottom) / 2.0f

            canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)
            canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)
            return BitmapDrawable(uiContext.resources, bitmap)

        }


        private fun firstCharacter(name: String): String {
            return name.first().toString().toUpperCase(Locale.ROOT)
        }

        private fun textPainter(): TextPaint {
            val textPaint = TextPaint()
            textPaint.isAntiAlias = true
            textPaint.textSize = texSize * uiContext.resources.displayMetrics.scaledDensity
            textPaint.color = Color.WHITE
            return textPaint
        }

        private fun painter(): Paint {
            return Paint()
        }

        private fun calTextSize(size: Int): Float {
            return (size / 5).toFloat()
        }
    }
}