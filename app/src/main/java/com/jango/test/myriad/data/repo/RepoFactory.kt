package com.jango.test.myriad.data.repo

/**
 * Factory contract for the Repository factories
 */
interface RepoFactory {

    fun getRepository(repo:Repo):UnsplashRepo
}

enum class Repo{
    NETWORK_REPO
}