package com.android.example.thirdlib.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.example.R;
import com.android.example.common.log.Logger;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class Rxjava2DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_detail);
        if (getIntent() != null) {
            int test = getIntent().getIntExtra("test", 0);
            Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "{" + test + "}");
            doTest(test);
        }
    }

    private void doTest(int test) {
        Class clazz = this.getClass();
        try {
            Method testMethod = clazz.getDeclaredMethod("test" + test);
            testMethod.invoke(this);
        } catch (Exception e) {
            Logger.get().e(Rxjava2DetailActivity.class.getSimpleName(), "doTest error", e);
        }
    }

    private void test0() {
        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "test0");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observable emitter 1");
                emitter.onNext(1);
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observable emitter 2");
                emitter.onNext(2);
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observable emitter 3");
                emitter.onNext(3);
                emitter.onComplete();
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observable emitter 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            int i = 0;
            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observer onSubscribe isDisposed:" + d.isDisposed());
                disposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observer onNext value:" + integer);
                i++;
                if (i == 2) {
                    disposable.dispose();
                    Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observer onNext isDisposed:" + disposable.isDisposed());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observer onError");
            }

            @Override
            public void onComplete() {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Observer onComplete");
            }
        });

    }

    private void test1() {
        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "test1");
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onNext("D");
            }
        });
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });
        Observable.zip(stringObservable, integerObservable, new BiFunction<String, Integer, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + s);
            }
        });
    }

    private void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This result is " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + s);
            }
        });
    }

    private void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + s);
                    }
                });
    }

    private void test4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + s);
                    }
                });
    }

    private void test5() {
        Observable.just(1, 2, 3, 4).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "预处理 : " + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test6() {
        Observable.just(1, 2, 3, -1, -2, -3).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer < 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test7() {
        Observable.just(1, 2, 3, 4, 5).skip(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test8() {
        Observable.just(1, 2, 3, 4, 5).take(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test9() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "timer start : " + df.format(LocalDateTime.now()));
            Observable.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "timer " + aLong + " " + df.format(LocalDateTime.now()));
                        }
                    });
        }
    }

    private void test10() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "interval start : " + df.format(LocalDateTime.now()));
            Observable.interval(3, 2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "interval aLong : " + aLong + " " + df.format(LocalDateTime.now()));
                        }
                    });
        }
    }

    private void test11() {
        Observable.just(1, 2, 3, 4, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test12() {
        Single.just(new Random().nextInt()).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "onSuccess:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.get().e(Rxjava2DetailActivity.class.getSimpleName(), "onError", e);
            }
        });
    }

    private void test13() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test14() {
        Observable.just(1, 1, 1, 2, 2, 3, 4, 5).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test15() {
        Observable.just(1, 2, 3, 4, 5).buffer(3, 2).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "buffer size : " + integers.size());
                for (Integer integer : integers) {
                    Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "buffer value : " + integer);
                }
            }
        });
    }

    private void test16() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(400);
                emitter.onNext(2);
                Thread.sleep(520);
                emitter.onNext(3);
                Thread.sleep(100);
                emitter.onNext(4);
                Thread.sleep(800);
                emitter.onNext(5);
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
                    }
                });
    }

    private void test17() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void test18() {
        Observable.just(1, 2, 3).last(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept : " + integer);
            }
        });
    }

    private void test19() {
        Observable.merge(Observable.just(1, 3, 5), Observable.just(2, 4, 6)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept:" + integer);
            }
        });
    }

    private void test20() {
        Observable.just(1, 2, 3).reduce(new BiFunction<Integer, Integer, Integer>() {
            @NonNull
            @Override
            public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept:" + integer);
            }
        });
    }

    private void test21() {
        Observable.just(1, 2, 3).scan(new BiFunction<Integer, Integer, Integer>() {
            @NonNull
            @Override
            public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept:" + integer);
            }
        });
    }

    private void test22() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(15)
                .window(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "Sub Devide begin...");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept:" + aLong);
                                    }
                                });
                    }
                });
    }

    /**
     * onNext() 会通知每个观察者，仅此而已
     */
    public void test23() {
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 1 accept:" + integer);
            }
        });

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);

        publishSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 2 accept:" + integer);
            }
        });

        publishSubject.onNext(4);
        publishSubject.onNext(5);
    }

    /**
     * 在 调用 onComplete() 之前，除了 subscribe() 其它的操作都会被缓存，
     * 在调用 onComplete() 之后只有最后一个 onNext() 会生效
     */
    public void test24() {
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 1 accept:" + integer);
            }
        });

        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);

        asyncSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 2 accept:" + integer);
            }
        });

        asyncSubject.onNext(4);
        asyncSubject.onNext(5);
        asyncSubject.onComplete();
    }

    /**
     * BehaviorSubject 的最后一次 onNext() 操作会被缓存，然后在 subscribe() 后立刻推给新注册的 Observer
     * 2020-12-04 18:34:34.353 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>{25}
     * 2020-12-04 18:34:34.430 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 1 accept:1
     * 2020-12-04 18:34:34.430 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 1 accept:2
     * 2020-12-04 18:34:34.433 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 1 accept:3
     * 2020-12-04 18:34:34.433 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 2 accept:3
     * 2020-12-04 18:34:34.434 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 1 accept:4
     * 2020-12-04 18:34:34.434 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 2 accept:4
     * 2020-12-04 18:34:34.434 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 1 accept:5
     * 2020-12-04 18:34:34.434 16509-16509/com.android.example D/DefaultLogger: [Rxjava2DetailActivity]=>>subscribe 2 accept:5
     */
    public void test25() {
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 1 accept:" + integer);
            }
        });

        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.onNext(3);

        behaviorSubject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "subscribe 2 accept:" + integer);
            }
        });

        behaviorSubject.onNext(4);
        behaviorSubject.onNext(5);
        behaviorSubject.onComplete();
    }

    /**
     * 只关心结果，也就是说 Completable 是没有 onNext 的，要么成功要么出错，不关心过程，在 subscribe 后的某个时间点返回结果
     */
    public void test26() {
        Completable.timer(1, TimeUnit.SECONDS).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    /**
     * 专用于解决背压问题
     */
    public void test27() {
        Flowable.just(1, 2, 3, 4).reduce(100, new BiFunction<Integer, Integer, Integer>() {
            @NonNull
            @Override
            public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.get().d(Rxjava2DetailActivity.class.getSimpleName(), "accept:" + integer);
            }
        });
    }
}