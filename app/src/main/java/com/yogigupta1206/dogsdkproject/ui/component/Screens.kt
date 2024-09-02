package com.yogigupta1206.dogsdkproject.ui.component


sealed class Screens(val route: String) {
    data object HomePage : Screens("homepage")
    data object DogsListPage : Screens("dogsListPage")
}