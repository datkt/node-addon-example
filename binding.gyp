{
  "targets": [{
    "target_name": "addon",
    "type": "none",
    "actions": [{
      "action_name": "compile",
      "inputs": [ ],
      "outputs": [ "module_api.h", "module.klib" ],
      "message": "compile module.kt",
      "action": [
        "make"
      ]
    }]
  }]
}
