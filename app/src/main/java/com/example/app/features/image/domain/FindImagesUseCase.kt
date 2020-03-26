package com.example.app.features.image.domain

import com.example.app.core.common.UseCase
import com.example.app.core.platform.scheduler.SchedulerProvider
import com.example.app.features.image.data.ImageRepository
import io.reactivex.Single
import javax.inject.Inject

class FindImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    private val schedulerProvider: SchedulerProvider
) : UseCase<String, Single<List<Image>>> {

    override fun run(param: String): Single<List<Image>> {
        return imageRepository.findImages(param)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}