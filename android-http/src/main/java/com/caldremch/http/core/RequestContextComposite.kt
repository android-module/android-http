package com.caldremch.http.core

/**
 * Created by Leon on 2022/8/10.
 */
class RequestContextComposite {

    private val ctxSet by lazy { HashSet<IRequestContext>() }

    fun add(ctx:IRequestContext){
        ctxSet.add(ctx)
    }

    fun clear(){
        ctxSet.forEach {
            it.cancel()
        }
    }
}