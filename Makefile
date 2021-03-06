MKDIR = mkdir -p
BUILD ?= build
RELEASE ?= $(BUILD)/Release

build: binding.klib
build: libmodule.so
build: module.node

module_api.h: node_modules
	konanc module.kt -no{main,stdlib} -r node_modules/@datkt/napi -l napi -p dynamic -o module

binding.klib: module_api.h
	cinterop -def binding.def -pkg napi -o binding

libmodule.so: binding.klib
	konanc module.kt -l binding -p dynamic -o module

module.node: $(RELEASE)/module.node
$(RELEASE)/module.node: libmodule.so
	mv -f $^ $@

clean:
	rm -rf *.node *.so *klib *.h build/ ./build-*/ ./*-build/
