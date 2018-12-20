package com.jango.test.myriad.data.repo

/**
 * Concrete implementation of @{RepoFactory} to generate object of different repositories for the dependents
 */
object RepoFactoryImpl : RepoFactory {
    override fun getRepository(repo: Repo): UnsplashRepo {
        when(repo){
            Repo.NETWORK_REPO -> {
                return NetworkUnsplashRepo()
            }
        }
    }
}