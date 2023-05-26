import com.caldremch.http.core.HttpInitializer
import com.caldremch.http.core.HttpManager
import com.caldremch.http.execute.GetExecuteImpl
import com.caldremch.http.execute.PostExecuteImpl
import com.caldremch.http.impl.HttpConvertImpl
import impl.BaseRespFactoryImpl
//import impl.ConvertStrategyImpl
import impl.HttpCommonHeaderImpl
import impl.HttpObsHandlerImpl
import impl.HttpUrlConfigImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import model.LoginInfoResp
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
@ExperimentalCoroutinesApi
class HttpUnitTest {

    @BeforeEach
    fun setUp(){
        //实现端初始化
        HttpInitializer.registerIConvert(HttpConvertImpl::class.java)
        HttpInitializer.registerIGetExecute(GetExecuteImpl::class.java)
        HttpInitializer.registerIPostExecute(PostExecuteImpl::class.java)

        //使用者初始化
        HttpInitializer.Builder()
            .registerHeader(HttpCommonHeaderImpl::class.java)
            .registerRequestEventCallback(HttpObsHandlerImpl::class.java)
            .registerServerUrlConfig(HttpUrlConfigImpl::class.java)
            .registerBaseRespFactory(BaseRespFactoryImpl::class.java)
            .registerConvertStrategy(ConvertStrategyImpl::class.java)
            .build()
    }

    @Test
    fun hasData() = runTest{
        val resp = HttpManager.post("/login").execute(LoginInfoResp::class.java)
        // TODO: 这里需要修改为notNull类型
        Assertions.assertNull(resp.getData())
    }

    @AfterEach
    fun tearDown(){

    }

}
