package com.hamedrahimvand.scalingrecyclerview.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.hamedrahimvand.scalingrecyclerview.utils.ConverterUtils
import com.hamedrahimvand.scalingrecyclerview.R
import com.hamedrahimvand.scalingrecyclerview.model.ScalingRecyclerModel

/**
 * Created By Hamed Rahimvand on 3 December 2018
 */

class ScalingRecyclerAdapter(val context: Context, val dataList: ArrayList<ScalingRecyclerModel>) :
    RecyclerView.Adapter<ScalingRecyclerAdapter.ScalingRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScalingRecyclerViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_scaling_recyclerview, parent, false)
        return ScalingRecyclerViewHolder(view)
    }


    var imgWidth = 56
    var imgHeight = 56
    var firstTime = true
    var placeHolderDrawable: Drawable? = null
    var onErrorDrawable: Drawable? = null
    var firstTitleTextAppearance: Int? = null
    var secondTitleTextAppearance: Int? = null
    override fun getItemCount(): Int {
        return dataList.size
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: ScalingRecyclerViewHolder, position: Int) {
        val itemData = dataList[holder.adapterPosition]

        if (itemData.visibility != null) holder.colParent.visibility = itemData.visibility
        holder.txvFirstTitle.text = itemData.firstTitle
        holder.txvSecondTitle.text = itemData.secondTitle

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (secondTitleTextAppearance != null) {
                holder.txvSecondTitle.setTextAppearance(secondTitleTextAppearance!!)
            }
            if (firstTitleTextAppearance != null) {
                holder.txvFirstTitle.setTextAppearance(firstTitleTextAppearance!!)
            }
        } else {
            if (secondTitleTextAppearance != null) {
                holder.txvSecondTitle.setTextAppearance(context, secondTitleTextAppearance!!)
            }
            if (firstTitleTextAppearance != null) {
                holder.txvFirstTitle.setTextAppearance(context, firstTitleTextAppearance!!)
            }
        }

        //load images with glide
        loadGlide(
            itemData.imageUrl!!,
            holder.img,
            placeHolderDrawable,
            onErrorDrawable,
            imgWidth,
            imgHeight
        )

        //cut first and last item just for fix, the first and last item
        //dynamically imported to list
        if (position == 0 || position == dataList.size - 1) {
            holder.colParent.layoutParams.width = ConverterUtils.dpToPx(60f).toInt()
        } else {
            holder.colParent.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        //scale item for first time
        if (firstTime) {
            if (position == 1) {
                holder.frlSecondLayout.scaleX = 1.2f
                holder.frlSecondLayout.scaleY = 1.2f
                holder.frlThirdLayout.scaleX = 1.45f
                holder.frlThirdLayout.scaleY = 1.45f
            }
            if (position == 2) {
                firstTime = false
                holder.itemView.scaleX = 0.8f
                holder.itemView.scaleY = 0.8f
                holder.frlSecondLayout.scaleX = 1.0f
                holder.frlSecondLayout.scaleY = 1.0f
                holder.frlThirdLayout.scaleX = 1.0f
                holder.frlThirdLayout.scaleY = 1.0f
            }
        }
    }


    /**
     * Load image from url, after load,images will override size.
     * @param width The width in dp to use to load the resource.
     * @param height The height in dp to use to load the resource.
     */
    private fun loadGlide(
        photoUrl: String,
        imv: AppCompatImageView,
        placeHolder: Drawable?,
        errorDrawable: Drawable?,
        width: Int,
        height: Int
    ) {
        //use ConverterUtils#dpToPx to convert input dp to px
        this.imgWidth = ConverterUtils.dpToPx(width.toFloat()).toInt()
        this.imgHeight = ConverterUtils.dpToPx(height.toFloat()).toInt()

        Glide.with(imv.context).load(photoUrl)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeHolder)
            .error(errorDrawable)
            .override(this.imgWidth, this.imgHeight)
            .into(object : BitmapImageViewTarget(imv) {
                override fun setResource(resource: Bitmap?) {
                    val circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.resources, resource)
                    circularBitmapDrawable.isCircular = true
                    imv.setImageDrawable(circularBitmapDrawable)
                }
            })
    }


    class ScalingRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colParent =
            itemView.findViewById<ConstraintLayout>(R.id.col_parent)!!
        val img = itemView.findViewById<AppCompatImageView>(R.id.imv_cardImage)!!
        val txvFirstTitle = itemView.findViewById<AppCompatTextView>(R.id.txv_cardFirstTitle)!!
        val txvSecondTitle = itemView.findViewById<AppCompatTextView>(R.id.txv_cardSecondTitle)!!
        val frlSecondLayout = itemView.findViewById<FrameLayout>(R.id.frl_secondLayout)!!
        val frlThirdLayout = itemView.findViewById<FrameLayout>(R.id.frl_thirdLayout)!!
    }

}