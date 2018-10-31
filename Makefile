MKDIR = mkdir -p

build: binding.klib
build: libmodule.so
build: module.node

module_api.h:
	konanc module.kt -no{main,stdlib} -r node_modules/@datkt -l napi/napi -p dynamic -o module

binding.klib: module_api.h
	cinterop -def binding.def -pkg napi -o binding

libmodule.so: binding.klib
	konanc module.kt -l binding -p dynamic -o module

module.node: libmodule.so
	cp -f $^ $@

clean:
	rm -rf *.node *.so *klib *.h build/ ./build-*/ ./*-build/
