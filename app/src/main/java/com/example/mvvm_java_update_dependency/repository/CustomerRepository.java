package com.example.mvvm_java_update_dependency.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.mvvm_java_update_dependency.database.CustomerDatabase;
import com.example.mvvm_java_update_dependency.database.dao.CustomerDao;
import com.example.mvvm_java_update_dependency.database.dao.MessageDao;
import com.example.mvvm_java_update_dependency.model.Customer;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class CustomerRepository {

    private static final String TAG = "MovieRepository";

    private CustomerDao customerDao;
    private MessageDao messageDao;
    //private MovieDao movieDao;
    private Flowable<List<CustomerDao>> allGenres;
    //private Flowable<List<Movie>> allMovies;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public CustomerRepository(Application application){
        CustomerDatabase movieDatabase = CustomerDatabase.getInstance(application);
        customerDao = movieDatabase.customerDao();
        messageDao = movieDatabase.messageDao();
        //movieDao = movieDatabase.movieDao();

    }

    //Get all Customer
    public Flowable<List<Customer>> getAllCustomer(){
        return customerDao.getAllCustomer();
    }

    public List<Customer> getAllCustomerList(){
        return customerDao.getAllCustomerList();
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    //Get all Movie under the specific Genre
//    public Flowable<List<Customer>> getAllMovies(int genre_id){
//        return movieDao.getAllMoviesByGenre(genre_id);
//    }

    //Insert Customer
    public void insertCustomer(final Customer customer){
        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                customerDao.insert(customer);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: Called");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Called");
                isLoading.setValue(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        });
    }

    //Update Customer
    public void updateCustomer(final Customer customer){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                customerDao.update(customer);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Called");
                    }
                });
    }

    //Delete Customer
    public void deleteCustomer(final Customer customer){
        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                customerDao.delete(customer);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: Called");
                        //deleteAllMoviesByGenre(customer.getUid());
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage());
                    }
                });
    }

    //Delete all Customer
    public void deleteAllCustomer(){

        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                customerDao.deleteAllCustomer();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                        //deleteAllMovies();
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Called"+e.getMessage());
                    }
                });
    }


//    //Insert Movie
//    public void insertMovie(final Movie movie){
//
//        isLoading.setValue(true);
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                movieDao.insert(movie);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                        isLoading.setValue(false);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: Called"+e.getMessage());
//                    }
//                });
//    }
//
//    //Update Movie
//    public void updateMovie(final Movie movie){
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                movieDao.update(movie);
//            }
//        }).observeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: Called"+e.getMessage());
//                    }
//                });
//    }
//
//    //Delete Movie
//    public void deleteMovie(final Movie movie){
//        isLoading.setValue(true);
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                movieDao.delete(movie);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                        isLoading.setValue(false);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: "+e.getMessage());
//                    }
//                });
//    }
//
//    //Delete all Movie
//    public void deleteAllMovies(){
//        isLoading.setValue(true);
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                movieDao.deleteAllMovies();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                        isLoading.setValue(false);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: "+e.getMessage());
//                    }
//                });
//    }
//
//    //Delete all Movies By Genre
//    public void deleteAllMoviesByGenre(final int genre_id){
//        isLoading.setValue(true);
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                movieDao.deleteAllMoviesUnderGenre(genre_id);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                        isLoading.setValue(false);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: "+e.getMessage());
//                    }
//                });
//    }

}
