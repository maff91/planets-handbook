package com.maff.planetshandbook.ui

/**
 * Created by maff on 11/4/2017.
 */

interface PresenterView<T: BasePresenter>
{
    fun setPresenter(presenter: T)
}