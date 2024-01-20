package ru.otus.basicarchitecture.service

interface InterestsService {

    suspend fun getAvailableInterests(): List<String>

}