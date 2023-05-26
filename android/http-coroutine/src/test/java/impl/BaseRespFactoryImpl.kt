package impl

import com.caldremch.http.core.framework.base.IBaseResp
import com.caldremch.http.core.framework.base.IBaseRespFactory

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
class BaseRespFactoryImpl : IBaseRespFactory {
    class MyAppBaseResp<T>(
        private val data:T?,
        private val code:Int? = null,
        private val errorMsg:String? = null
    ) : IBaseResp<T>{
        override fun getData(): T? {
            return data
        }

        override fun getErrorMsg(): String? {
            return errorMsg
        }

        override fun getCode(): Int? {
            return code
        }

        override fun isSuccess(): Boolean {
            return code == 0
        }

    }

    override fun <T> create(
        data: T?,
        code: Int?,
        errorMsg: String?,
        time: Long?,
        codeString: String?
    ): IBaseResp<T> {
        return MyAppBaseResp(data, code, errorMsg)
    }
}
