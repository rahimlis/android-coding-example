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
    @Named("giphy.api.url")
    public static String provideGiphyUrl() {
        return "https://api.giphy.com";
    }

    @Provides
    @Named("giphy.api.key")
    public static String provideGiphyApiKey() {
        return "Qmerxz8yWwu7N9EfuvJJuX0lYkPXssOe";
    }
}
