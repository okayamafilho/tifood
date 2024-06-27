package br.com.okayamafilho.tifood.di

import br.com.okayamafilho.tifood.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import br.com.okayamafilho.tifood.data.remote.firebase.repository.IAutenticacaoRepository
import br.com.okayamafilho.tifood.domain.usecase.AutenticacaoUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

object AppModule {

    @Provides
    fun provideAutenticacaoUseCase(): AutenticacaoUseCase {
        return AutenticacaoUseCase()
    }

    @Provides
    fun provideAutenticacaoRepository(
        firebaseAuth: FirebaseAuth
    ): IAutenticacaoRepository {
        return AutenticacaoRepositoryImpl(firebaseAuth)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}