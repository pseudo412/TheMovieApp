package com.example.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.themovieapp.data.models.MovieModelImpl
import com.example.themovieapp.mvp.views.MainView
import com.example.themovieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl : ViewModel(), MovieDetailsPresenter {
    //Model
    private val mMovieModel = MovieModelImpl

    //View
    private var mView: MovieDetailsView? = null

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        //MovieDetails
        mMovieModel.getMovieDetails(movieId.toString()) {
            mView?.showError(it)
        }?.observe(owner) {
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        //Credits
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(),
            onSuccess = {
                mView?.showCreditsByMovie(cast = it.first, crew = it.second)
            }, onFailure = {
                mView?.showError(it)
            })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}

}