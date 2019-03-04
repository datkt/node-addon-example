import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.CStructVar
import kotlinx.cinterop.NativePtr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value

import napi.napi_create_string_utf8
import napi.napi_set_named_property
import napi.napi_create_function
import napi.napi_callback_info
import napi.napi_valueVar
import napi.napi_status
import napi.napi_value
import napi.napi_env

fun Init(env: napi_env, exports: napi_value): napi_value {
  memScoped {
    val fn = alloc<napi_valueVar>()
    var status = napi_create_function(
      env,
      "Hello", 0,
      staticCFunction(::Hello), null,
      fn.ptr
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
  memScoped {
    val hello = alloc<napi_valueVar>()
    napi_create_string_utf8(env, "hello", 5, hello.ptr)
    return hello.value
  }
}
