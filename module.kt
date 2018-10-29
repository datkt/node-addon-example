//import node.napi_value
import napi.*
import kotlinx.cinterop.*

class NValue(rawPtr: NativePtr): CStructVar(rawPtr) {
  companion object : Type(4, 4)
}

fun Init(env: napi_env, exports: napi_value): napi_value {
  memScoped {
    //var fn: CValuesRef<napi_valueVar>? = null
    var fn = alloc<NValue>()
    var ptr = fn.ptr as CValuesRef<napi_valueVar>
    val add: napi_callback? = staticCFunction(::Add)
    var status = napi_create_function(env, "Add", 0, add, null, ptr)

    if (napi_status.napi_ok != status) {
      throw Error("Failed to create N-API function: ${status}")
    }

    //status = napi_set_named_property(env, exports, "add", ptr);

    if (napi_status.napi_ok != status) {
      throw Error("Failed to create N-API function: ${status}")
    }
  }

  println("HI VIP")
  return exports
}

fun Add(env: napi_env?, info: napi_callback_info?): napi_value? {
  val argv: Array<napi_value> = emptyArray()
  var x: Int
  var y: Int

  return argv[0]
  //x.usePi
}
