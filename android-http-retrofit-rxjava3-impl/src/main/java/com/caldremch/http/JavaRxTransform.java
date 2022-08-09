package com.caldremch.http;
import com.caldremch.http.core.abs.IConvert;
import com.caldremch.http.core.model.ResponseBodyWrapper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
/**
 * Created by Leon on 2022/7/8
 */
public class JavaRxTransform {
    public static <R> ObservableTransformer<ResponseBody, R> transformer(Class<R> clazz, IConvert<ResponseBody> convert) {
        return new ObservableTransformer<ResponseBody, R>() {
            @Override
            public @NonNull ObservableSource<R> apply(@NonNull Observable<ResponseBody> upstream) {
                return upstream.flatMap(new Function<ResponseBody, ObservableSource<? extends R>>() {
                            @Override
                            public ObservableSource<? extends R> apply(ResponseBody responseBody) throws Throwable {
                                return Observable.create(new ObservableOnSubscribe<R>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<R> emitter) throws Throwable {
                                        emitter.onNext(convert.convert(new ResponseBodyWrapper<>(responseBody), clazz));
                                    }
                                });
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
