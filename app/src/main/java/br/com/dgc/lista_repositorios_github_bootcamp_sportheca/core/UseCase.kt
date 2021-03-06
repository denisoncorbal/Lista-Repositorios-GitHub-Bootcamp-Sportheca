package br.com.dgc.lista_repositorios_github_bootcamp_sportheca.core

import kotlinx.coroutines.flow.Flow
import java.lang.UnsupportedOperationException

abstract class UseCase<Param, Source> {
    abstract suspend fun execute(param: Param): Flow<Source>

    abstract suspend fun execute(param: Param, param2: Int): Flow<Source>

    open suspend operator fun invoke(param: Param) = execute(param)

    abstract class NoParam<Source> : UseCase<None, Flow<Source>>(){
        abstract suspend fun execute(): Flow<Source>

        final override suspend fun execute(param: None): Flow<Flow<Source>> =
            throw UnsupportedOperationException()

        suspend operator fun invoke(): Flow<Source> = execute()
    }

    abstract class NoSource<Params>: UseCase<Params, Unit>(){
        override suspend fun invoke(param: Params): Flow<Unit> = execute(param)
    }

    object None
}