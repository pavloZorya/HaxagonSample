package com.pavlo.zoria.haxagonalsample.view.user.presenter

import android.util.Log
import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.UserInfrastructurePort
import com.pavlo.zoria.haxagonalsample.utils.TrampolineSchedulerProvider
import com.pavlo.zoria.haxagonalsample.view.user.UsersListContract
import com.pavlo.zoria.haxagonalsample.view.user.UsersListPresenter
import io.mockk.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

internal class UsersListPresenterTest {
    private var view = mockk<UsersListContract.View>()
    private var service = mockk<UserInfrastructurePort>()
    private var schedulerProvider = TrampolineSchedulerProvider()

    @Before
    fun setUp() {
        mockkStatic(Log::class)
    }

    @Test
    fun testEmittingEmptyList() {
        //given
        val presenter =
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        val users = emptyList<User>()

        every { service.getAllDataEmitter() } returns Observable.just(users)

        //when
        presenter.handleEvents()
        //then
        verify { view.showUsers(emptyList()) }
    }

    @Test
    fun testToggleToPauseEventHandling() {
        //given
        val presenter = spyk(
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        )
        val observable = Observable.just(emptyList<User>())

        every { service.getAllDataEmitter() } returns observable
        //when
        presenter.handleEvents()
        presenter.toggleHandling()

        //then
        verify { presenter.stopHandling() }
//        verify { presenter.getProperty("disposable")?.invoke("dispose") }
    }

    @Test
    fun testToggleWithParametersToPauseEventHandling() {
        //given
        val presenter = spyk(
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        )
        val observable = Observable.just(emptyList<User>())

        every { service.getAllDataEmitter() } returns observable
        //when
        presenter.toggleHandling(false)

        //then
        verify { presenter.stopHandling() }
    }

    @Test
    fun testToggleToStartEventHandling() {
        //given
        val presenter = spyk(
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        )
        val observable = Observable.just(emptyList<User>())

        every { service.getAllDataEmitter() } returns observable

        //when
        presenter.stopHandling()
        presenter.toggleHandling()

        //then
        verify {
            presenter.handleEvents()
        }
    }

    @Test
    fun testToggleWithParameterToStartEventHandling() {
        //given
        val presenter = spyk(
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        )
        val observable = Observable.just(emptyList<User>())

        every { service.getAllDataEmitter() } returns observable

        //when
        presenter.toggleHandling(true)

        //then
        verify {
            presenter.handleEvents()
        }
    }


    @Test
    fun testSubscribeViewStartHandling() {
        //given
        val presenter = spyk(
            UsersListPresenter(
                view,
                service,
                schedulerProvider
            )
        )
        val observable = Observable.just(emptyList<User>())

        every { service.getAllDataEmitter() } returns observable

        //when
        presenter.subscribeView(view)

        //then
        verify {
            presenter.handleEvents()
        }
    }
}