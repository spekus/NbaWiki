package com.example.nbawiki.model.dto

import com.example.nbawiki.model.presentation.PresenationModel

interface Dto {

    fun <T :  Dto> T.asPresentationModel() : PresenationModel
    fun getPresentationModel() : PresenationModel
}
