package com.caldremch.http.core.utils.lazy;



/**
 * Created by Leon on 2022/10/29
 */
public class SynchronizedLazyImpl<T> implements Lazy<T> {

    public JavaDsl<T> initializer;
    private volatile Object _value;
    private final Object lock = this;

    public SynchronizedLazyImpl(JavaDsl<T> initializer) {
        this.initializer = initializer;
        this._value = UNINITIALIZED_VALUE.INSTANCE;
    }

    @Override
    public T getValue() {
        final Object _v1 = _value;
        if(_v1 != UNINITIALIZED_VALUE.INSTANCE){
            return (T) _value;
        }
        synchronized (lock){
            final Object _v2 = _value;
            if(_v2 != UNINITIALIZED_VALUE.INSTANCE){
                return (T) _v2;
            }
            Object typedValue = this.initializer.invoke();
            _value = typedValue;
             this.initializer =null;
             return (T) typedValue;
        }

    }

    @Override
    public boolean isInitialized() {
        return _value != UNINITIALIZED_VALUE.INSTANCE;
    }

    @Override
    public String toString() {
        if(isInitialized()) return getValue().toString();
        return "Lazy value not initialized yet.";
    }
}
