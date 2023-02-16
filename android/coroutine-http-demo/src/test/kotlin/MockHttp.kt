import com.caldremch.android.coroutine.http.demo.impl.BaseRespFactoryImpl
import com.caldremch.android.coroutine.http.demo.impl.ConvertStrategyImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpCommonHeaderImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpObsHandlerImpl
import com.caldremch.android.coroutine.http.demo.impl.HttpUrlConfigImpl
import com.caldremch.android.log.DebugLogInitializer
import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl
import com.caldremch.http.impl.HttpConvertImpl

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
object MockHttp {

   private fun initLog() {
        DebugLogInitializer.initLite(true)
    }

    private fun initHttp() {

        //实现端初始化
        HttpInitializer.registerIConvert(HttpConvertImpl::class.java)
        HttpInitializer.registerIGetExecute(GetExecuteImpl::class.java)
        HttpInitializer.registerIPostExecute(PostExecuteImpl::class.java)

        //使用者初始化
        HttpInitializer.Builder()
            .registerHeader(HttpCommonHeaderImpl::class.java)
            .registerBaseRespFactory(BaseRespFactoryImpl::class.java)
            .registerRequestEventCallback(HttpObsHandlerImpl::class.java)
            .registerServerUrlConfig(HttpUrlConfigImpl::class.java)
            .registerConvertStrategy(ConvertStrategyImpl::class.java)
            .build()
    }
    fun init(){
        initLog()
        initHttp()
    }
}
