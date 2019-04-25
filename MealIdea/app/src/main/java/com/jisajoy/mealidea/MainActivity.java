package com.jisajoy.mealidea;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    SearchView mSearchMeals;
    RecyclerView mSearchResultRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchMeals = findViewById(R.id.searchView);
        mSearchResultRV = findViewById(R.id.search_result_rv);

        mSearchResultRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultRV.setHasFixedSize(true);



    }

    @SuppressLint("CheckResult")
    private void setUpSearchObservable() {
        RxSearchObservable.fromView(mSearchMeals)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) {
                        if (text.isEmpty()) {
                            /*textViewResult.setText("");*/
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String query) {
                        return dataFromNetwork(query);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) {
                        /*textViewResult.setText(result);*/
                    }
                });
    }

    private Observable<String> dataFromNetwork(final String query) {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Boolean, String>() {
                    @Override
                    public String apply(@NonNull Boolean value) {
                        return query;
                    }
                });
    }
}
