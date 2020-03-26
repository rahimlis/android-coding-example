package com.example.app.core.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * This module can be used to protect sensible strings within app from
 * reverse engineering. For the sake of simplicity I return Strings as is here,
 * but they can be encrypted in native library which is far more expensive and
 * tough to reverse-engineer
 *
 * <p>
 * ex:
 * //  @Provides
 * //  @Named("url.base.some_service")
 * //  public static native String provideSomeSensitiveString();
 */
@Module
public class SecretsModule {

    @Provides
    @Named("pixabay.api.url")
    public static String providePixabayUrl() {
        return "https://pixabay.com/api/";
    }

    @Provides
    @Named("pixabay.api.key")
    public static String providePixabayApiKey() {
        return "15683712-e3f19715c80afc309ca731550";
    }
}
