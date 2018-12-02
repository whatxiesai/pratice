package com.example.administrator.rxjavapractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mvp.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxJavaActivity";
    
    private Button btn_create;
    private Button btn_from;
    private Button btn_just;
    private Button btn_map;
    private Button btn_flatmap;
    private Button btn_zip;
    private Button btn_zipwith;
    private Button btn_retry;
    private Button btn_filter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_activity);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(this);
        btn_from = findViewById(R.id.btn_from);
        btn_from.setOnClickListener(this);
        btn_just = findViewById(R.id.btn_just);
        btn_just.setOnClickListener(this);

        btn_map = findViewById(R.id.btn_map);
        btn_map.setOnClickListener(this);

        btn_flatmap = findViewById(R.id.btn_flatMap);
        btn_flatmap.setOnClickListener(this);

        btn_zip = findViewById(R.id.btn_zip);
        btn_zip.setOnClickListener(this);

        btn_zipwith = findViewById(R.id.btn_zipwith);
        btn_zipwith.setOnClickListener(this);

        btn_retry = findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(this);

        btn_filter = findViewById(R.id.btn_filter);
        btn_filter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        switch (id) {
            case R.id.btn_create:
                create();
                break;
                
            case R.id.btn_from:
                from();
                break;

            case R.id.btn_just:
                just();
                break;

            case R.id.btn_map:
                map();
                break;

            case R.id.btn_flatMap:
                flatmap();
                break;

            case R.id.btn_zip:
                zip();
                break;

            case R.id.btn_zipwith:
                zipWith();
                break;

            case R.id.btn_retry:
                retry();
                break;

            case R.id.btn_filter:
                filter();
                break;

                default:
                    break;
        }
    }

    private void filter() {
        Observable.just(1,2,3,4,5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 4;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        })
    }

    private void retry() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    for (int i = 0; i < 5; i++) {
                        if (i == 3) {
                            throw new Exception("出错了");
                        }
                        subscriber.onNext(i);
                    }

                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).retry(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", );
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: ");
            }
        });
    }

    /**
     * 将本身与其他obserable合并
     */
    private void zipWith() {
        Observable.just(10, 20, 30)
                .zipWith(Observable.just("a", "b", "c"), new Func2<Integer, String, String>() {
                    @Override
                    public String call(Integer integer, String s) {
                        return integer + s;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        })
    }

    private void zip() {
        // zip 将两个obserable严格的合成一个
        Observable<Integer> observable = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(10,20,30);
        Observable.zip(observable, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: " + integer);
            }
        });
    }

    private void flatmap() {
        // flatmap将多个obserable转成一个obserable发送
        String[] citys = {"北京", "上海", "天津"};
        Observable.from(citys)
                .flatMap(new Func1<String, Observable<WeatherData>>() {
                    @Override
                    public Observable<WeatherData> call(String s) {
                        return getCityWeatherData(s);
                    }
                }).subscribe(new Action1<WeatherData>() {
            @Override
            public void call(WeatherData weatherData) {
                Log.d(TAG, "call: " + weatherData.city + " " + weatherData.state);
            }
        });
    }

    /**
     * 获取城市天气
     * @param city
     * @return
     */
    private Observable<WeatherData> getCityWeatherData(final String city) {
        return Observable.just(city)
                .map(new Func1<String, WeatherData>() {
                    @Override
                    public WeatherData call(String s) {
                        WeatherData weatherData = new WeatherData();
                        weatherData.city = s;
                        weatherData.state = "晴";
                        return weatherData;
                    }
                });
    }


    /**
     * map一种类型转化成另一种类型
     *
     * flatmap一种类型转化成obserable类型
     */
    private void map() {
        // map实例，在该示例中，将ingeger转化成string
        Observable.just(0,1,2,3,4,5)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "转换";
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
            }
        });
    }

    private void just() {
        Observable.just(0, 1, 2, 3, 4, 5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: " + integer);
                    }
                });
    }

    private void from() {
        Integer[] arr = {0,1,2,3,4};
        Observable<Integer> from = Observable.from(arr);
        from.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });
     }

    private void create() {
        // 观察者和被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: ");
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("我被执行了");
                    subscriber.onCompleted();
                }
            }
        });
        
        // 完成订阅
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        });

    }
}

