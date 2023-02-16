import MockData.normalDataJson
import MockData.normalListDataJson
import com.caldremch.android.coroutine.http.demo.impl.ConvertStrategyImpl
import com.caldremch.http.core.abs.IConvertStrategy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Leon
 * @date 2023/2/16
 * @desc 单元测试参考:  https://github.com/mockk/mockk
 */
class ConvertUnitTest {

    @BeforeTest
    fun setUp(){
        MockHttp.init()
    }

    /**
     * 验证场景:
     * 1.正常的: data, code, int
     *
     */
    @Test
    fun convertTest(){
        val mock: IConvertStrategy = ConvertStrategyImpl()
        val resp =  mock.convertCommon<CommonData>(normalDataJson, CommonData::class.java)
        assertEquals(MockData.NAME, resp.getData()?.name)
    }

    @Test
    fun convertListTest(){
        val mock: IConvertStrategy = ConvertStrategyImpl()
        val type = object : TypeToken<List<CommonData>>(){}.type
        val resp =  mock.convertCommon<List<CommonData>>(normalListDataJson, type)
        println("转换结果: $resp")
        assertEquals(MockData.NAME1, resp.getData()?.first()?.name)
    }
}
