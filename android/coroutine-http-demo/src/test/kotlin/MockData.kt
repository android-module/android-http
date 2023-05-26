import com.google.gson.Gson
import com.google.gson.JsonObject

/**
 * @author Leon
 * @date 2023/2/16
 * @desc
 */
object MockData {

    const val NAME = "Caldremch"
    const val NAME1 = "Caldremch0"

    private val gson = Gson()

    val normalDataJson:String
        get() {
            val commonData = CommonData(NAME)
            val resp = BaseResp<CommonData>(commonData, 0, "操作成功")
            return gson.toJson(resp)
        }


    val normalListDataJson:String
        get() {
            val list = arrayListOf<CommonData>()
            for (i in 0..3){
                val commonData = CommonData(NAME+i)
                list.add(commonData)
            }
            val resp = BaseResp<List<CommonData>>(list, 0, "操作成功")
            return gson.toJson(resp)
        }
}
