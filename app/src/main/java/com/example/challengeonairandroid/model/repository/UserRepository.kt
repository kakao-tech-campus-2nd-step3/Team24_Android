package com.example.challengeonairandroid.model.repository

import com.example.challengeonairandroid.model.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) {
    suspend fun getUserData(): User = withContext(Dispatchers.IO) {
        try {
            // 먼저 로컬 데이터베이스에서 사용자 정보를 가져옵니다.
            val localUser = userDao.getUser()

            if (localUser != null) {
                // 로컬에 데이터가 있으면 그것을 반환합니다.
                localUser
            } else {
                // 로컬에 데이터가 없으면 API로부터 데이터를 가져옵니다.
                val remoteUser = userApi.fetchUserData()

                // 가져온 데이터를 로컬 데이터베이스에 저장합니다.
                userDao.insertUser(remoteUser)

                remoteUser
            }
        } catch (e: Exception) {
            // 에러 처리. 여기서는 간단히 기본 User 객체를 반환합니다.
            User(
                userId = -1,
                userName = "Unknown"
            )
        }
    }

    suspend fun updateUserData(user: User) = withContext(Dispatchers.IO) {
        try {
            // API를 통해 사용자 정보를 업데이트합니다.
            userApi.updateUserData(user)

            // 로컬 데이터베이스의 사용자 정보도 업데이트합니다.
            userDao.updateUser(user)
        } catch (e: Exception) {
            // 에러 처리
            throw e
        }
    }
}

// UserApi 인터페이스 (실제 구현은 별도로 해야 합니다)
interface UserApi {
    suspend fun fetchUserData(): User
    suspend fun updateUserData(user: User)
}

// UserDao 인터페이스 (Room 라이브러리를 사용한다고 가정합니다)
interface UserDao {
    suspend fun getUser(): User?
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
}