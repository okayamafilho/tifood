package br.com.okayamafilho.tifood.di

import br.com.okayamafilho.tifood.domain.usecase.AutenticacaoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

object AppModule {

    @Provides
    fun provideAutenticacaoUseCase() :AutenticacaoUseCase {
        return AutenticacaoUseCase()
    }
}