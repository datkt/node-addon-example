MKDIR = mkdir -p

build: napi.klib
build: binding.klib
build: libmodule.so
build: module.node

napi.klib:
	cinterop -def napi.def -pkg napi -o napi

module_api.h: napi.klib
	konanc module.kt -no{main,stdlib} -l napi -p dynamic -o module

binding.klib: module_api.h
	cinterop -def binding.def -pkg napi -o binding

libmodule.so: binding.klib
	konanc module.kt -l binding -p dynamic -o module

module.node: libmodule.so
	cp -f $^ $@

clean:
	rm -rf *.node *.so *klib *.h build/ ./build-*/ ./*-build/
