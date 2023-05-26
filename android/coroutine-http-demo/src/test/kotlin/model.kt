/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
class CommonData(val name:String)

class BaseResp<T>(
    val data:T,
    val code:Int,
    val errorMsg:String
)
