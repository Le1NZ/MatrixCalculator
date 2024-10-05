package ru.pokrovskii.matrixcalculator.state

import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenter

interface MainScreenUiEffect {
    data object SomethingWentWrong : MainScreenUiEffect
}