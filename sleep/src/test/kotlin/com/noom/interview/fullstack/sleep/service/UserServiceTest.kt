package com.noom.interview.fullstack.sleep.service

import com.noom.interview.fullstack.sleep.exception.UserException
import com.noom.interview.fullstack.sleep.model.User
import com.noom.interview.fullstack.sleep.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest{

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `should return user when found`() {
        val user = User()
        whenever(userRepository.findById(anyLong())).thenReturn(Optional.of(user))

        val result: User = userService.getUser(1L)

        assertNotNull(result)
    }

    @Test
    fun `should return empty when user not found`() {
        whenever(userRepository.findById(anyLong())).thenReturn(Optional.empty())

        val exception = org.junit.jupiter.api.assertThrows<UserException> {
            userService.getUser(-1L)
        }

        assertEquals("User not found", exception.message)
    }
}