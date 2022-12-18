package ca.on.hojat.gamenews.shared.commons.widgets

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ca.on.hojat.gamenews.core.extensions.getColor
import ca.on.hojat.gamenews.core.extensions.getDimensionPixelSize
import ca.on.hojat.gamenews.core.extensions.layoutInflater
import ca.on.hojat.gamenews.core.extensions.setColor
import ca.on.hojat.gamenews.core.extensions.setTextSizeInPx
import ca.on.hojat.gamenews.shared.R
import ca.on.hojat.gamenews.shared.databinding.ViewInfoBinding
import ca.on.hojat.gamenews.core.extensions.getFont
import ca.on.hojat.gamenews.core.extensions.getString
import ca.on.hojat.gamenews.core.extensions.setLayoutParamsSize
import ca.on.hojat.gamenews.core.extensions.topMargin

class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewInfoBinding.inflate(context.layoutInflater, this)

    private var isDescriptionTextVisible: Boolean
        set(value) {
            binding.descriptionTv.isVisible = value
        }
        get() = binding.descriptionTv.isVisible

    private var iconSize: Int = getDimensionPixelSize(R.dimen.info_view_icon_size)
        set(value) {
            field = value
            binding.iconIv.setLayoutParamsSize(value)
        }

    private var titleTextTopMargin: Int
        set(value) {
            binding.titleTv.topMargin = value
        }
        get() = binding.titleTv.topMargin

    private var descriptionTextTopMargin: Int
        set(value) {
            binding.descriptionTv.topMargin = value
        }
        get() = binding.descriptionTv.topMargin

    @get:ColorInt
    var iconColor: Int = getColor(R.color.info_view_icon_color)
        set(@ColorInt value) {
            field = value
            icon = icon
        }

    @get:ColorInt
    var titleTextColor: Int
        set(@ColorInt value) {
            binding.titleTv.setTextColor(value)
        }
        get() = binding.titleTv.currentTextColor

    @get:ColorInt
    var descriptionTextColor: Int
        set(@ColorInt value) {
            binding.descriptionTv.setTextColor(value)
        }
        get() = binding.descriptionTv.currentTextColor

    private var titleTextSize: Float
        set(value) {
            binding.titleTv.setTextSizeInPx(value)
        }
        get() = binding.titleTv.textSize

    private var descriptionTextSize: Float
        set(value) {
            binding.descriptionTv.setTextSizeInPx(value)
        }
        get() = binding.descriptionTv.textSize

    private var titleTextTypeface: Typeface
        set(value) {
            binding.titleTv.typeface = value
        }
        get() = binding.titleTv.typeface

    private var descriptionTextTypeface: Typeface
        set(value) {
            binding.descriptionTv.typeface = value
        }
        get() = binding.descriptionTv.typeface

    private var titleText: CharSequence
        set(value) {
            binding.titleTv.text = value
        }
        get() = binding.titleTv.text

    private var descriptionText: CharSequence
        set(value) {
            isDescriptionTextVisible = value.isNotBlank()
            binding.descriptionTv.text = value
        }
        get() = binding.descriptionTv.text

    var icon: Drawable?
        set(value) {
            binding.iconIv.setImageDrawable(value?.setColor(iconColor))
        }
        get() = binding.iconIv.drawable

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        attrs?.let { extractAttributes(it, defStyleAttr) }
    }

    private fun extractAttributes(attrs: AttributeSet, defStyleAttr: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.InfoView,
            defStyleAttr = defStyleAttr
        ) {
            iconSize = getDimensionPixelSize(R.styleable.InfoView_infoView_iconSize, iconSize)
            titleTextSize = getDimension(R.styleable.InfoView_infoView_titleTextSize, titleTextSize)
            descriptionTextSize =
                getDimension(R.styleable.InfoView_infoView_descriptionTextSize, descriptionTextSize)
            titleTextTopMargin = getDimensionPixelSize(
                R.styleable.InfoView_infoView_titleTextTopMargin,
                titleTextTopMargin
            )
            descriptionTextTopMargin = getDimensionPixelSize(
                R.styleable.InfoView_infoView_descriptionTextTopMargin,
                descriptionTextTopMargin
            )
            iconColor = getColor(R.styleable.InfoView_infoView_iconColor, iconColor)
            titleTextColor = getColor(R.styleable.InfoView_infoView_titleTextColor, titleTextColor)
            descriptionTextColor =
                getColor(R.styleable.InfoView_infoView_descriptionTextColor, descriptionTextColor)
            titleTextTypeface =
                getFont(context, R.styleable.InfoView_infoView_titleTextFont, titleTextTypeface)
            descriptionTextTypeface = getFont(
                context,
                R.styleable.InfoView_infoView_descriptionTextFont,
                descriptionTextTypeface
            )
            icon = getDrawable(R.styleable.InfoView_infoView_icon)
            titleText = getString(R.styleable.InfoView_infoView_titleText, titleText)
            descriptionText =
                getString(R.styleable.InfoView_infoView_descriptionText, descriptionText)
        }
    }
}
