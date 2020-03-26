package com.example.app.core.di.features.image

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.platform.arch.ViewModelKey
import com.example.app.core.platform.ui.widget.image.ImageLoader
import com.example.app.features.image.domain.Image
import com.example.app.features.image.presentation.ImageDiff
import com.example.app.features.image.presentation.ImageListAdapter
import com.example.app.features.image.presentation.ImageViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ImageActivityModule.ImageViewModelModule::class])
class ImageActivityModule {

    @Provides
    @ImageActivityScope
    fun provideLayoutManager(context: Context): RecyclerView.LayoutManager {
        val lm = GridLayoutManager(context, 2)
        //lm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return lm
    }

    @Provides
    @ImageActivityScope
    fun provideImageDiff(): DiffUtil.ItemCallback<Image> = ImageDiff()


    @Provides
    @ImageActivityScope
    fun provideImageListAdapter(
        diffCallback: DiffUtil.ItemCallback<Image>,
        layoutRes: Int,
        imageLoader: ImageLoader
    ) = ImageListAdapter(diffCallback, layoutRes, imageLoader)

    @Provides
    @ImageActivityScope
    fun provideImageItemLayoutResource() = R.layout.item_image


    @Module
    interface ImageViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(ImageViewModel::class)
        fun provideImageViewModel(imageViewModel: ImageViewModel): ViewModel
    }
}