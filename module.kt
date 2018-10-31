import kotlinx.cinterop.*
import napi.*

class NValue(rawPtr: NativePtr): CStructVar(rawPtr) {
  companion object : Type(8, 8)
}

fun Init(env: napi_env, exports: napi_value): napi_value {
  memScoped {
    val fn = alloc<napi_valueVar>()
    var status = napi_create_function(
      env, "Hello", 0, staticCFunction(::Hello), null, fn.ptr
    )

    if (napi_status.napi_ok != status) {
      throw Error("Failed to create N-API function: ${status}")
    }

    status = napi_set_named_property(env, exports, "hello", fn.value)

    if (napi_status.napi_ok != status) {
      throw Error("Failed to create N-API function: ${status}")
    }
  }

  return exports
}

fun Hello(env: napi_env?, info: napi_callback_info?): napi_value? {
  return null
}
