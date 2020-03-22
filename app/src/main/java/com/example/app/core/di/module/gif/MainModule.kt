package com.example.app.core.di.module.gif

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.app.R
import com.example.app.features.gif.domain.model.Gif
import com.example.app.core.di.scopes.MainScope
import com.example.app.features.gif.presentation.GifDiff
import com.example.app.features.gif.presentation.MainViewModel
import com.example.app.features.gif.presentation.SearchAdapter
import com.example.app.core.platform.ui.widget.image.ImageLoader
import com.example.app.core.platform.arch.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainModule.MainViewModelModule::class])
class MainModule {

    @Provides
    @MainScope
    fun provideLayoutManager(): RecyclerView.LayoutManager =
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

    @Provides
    @MainScope
    fun provideGifDiff(): DiffUtil.ItemCallback<Gif> = GifDiff()


    @Provides
    @MainScope
    fun provideSearchAdapter(
        diffCallback: DiffUtil.ItemCallback<Gif>,
        layoutRes: Int,
        imageLoader: ImageLoader
    ) = SearchAdapter(diffCallback, layoutRes, imageLoader)

    @Provides
    @MainScope
    fun provideSearchLayoutResource() = R.layout.item_search_result


    @Module
    interface MainViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
    }

}