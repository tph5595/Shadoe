VPATH=build:..

BUILD_PATH=build
RELEASE_PATH=release
INSTALL_PATH=../../bin

CFLAGS=-arch ppc -fno-common -O2 -pipe -fmessage-length=0
LFLAGS=-arch ppc -Wl,-single_module -dynamiclib -compatibility_version 1
INCLUDE=
LIBS=-lc

CC=gcc
CPP=g++
LN=g++
AR=ar cqs
RANLIB=ranlib
RM=rm -rf
CP=cp -f
LIPO=lipo

FILES=../Samples.cpp

OUT=libJNIWrapperSampleDLL.dylib
INTEL_SRC=../macosx_intel
INTEL_OUT=$(INTEL_SRC)/$(OUT)
INTEL64_SRC=../macosx_intel64
INTEL64_OUT=$(INTEL64_SRC)/$(OUT)

BUILD_FILES=$(notdir $(FILES))
OBJ_FILES=$(addprefix $(BUILD_PATH)/, $(addsuffix .o, $(basename $(BUILD_FILES))))

OUT_LIB=$(addsuffix .a, $(basename $(OUT)))

install: release
	@echo [$(CP)] release/$(OUT) $(INSTALL_PATH)
	@$(CP) release/$(OUT) $(INSTALL_PATH) 

release: $(OUT) $(INTEL_OUT)
	@echo [$(LIPO)] $(INTEL_OUT) $(INTEL64_OUT) $(OUT) "-->" $(RELEASE_PATH)/$(OUT)
	@$(LIPO) -create $(INTEL_OUT) $(INTEL64_OUT) $(OUT) -output $(RELEASE_PATH)/$(OUT)

$(OUT): $(BUILD_PATH) $(OBJ_FILES)
	@echo [$(CPP)] $(OUT)
	@$(CPP) $(LFLAGS) $(LIBS) $(OBJ_FILES) -o $(OUT)
	@echo [$(AR)] $(OUT_LIB)
	@$(AR) $(OUT_LIB) $(OBJ_FILES)
	@echo [$(RANLIB)] $(OUT_LIB)
	@$(RANLIB) $(OUT_LIB)

$(BUILD_PATH):
	@if [ ! -d $(BUILD_PATH) ]; then mkdir -p $(BUILD_PATH); fi

$(BUILD_PATH)/%.o: %.cpp
	@echo [$(CPP)] $< "-->" $@
	@$(CPP) -c $(CFLAGS) $(INCLUDE) -o $@ $<

$(BUILD_PATH)/%.o: %.c
	@echo [$(CC)] $< "-->" $@
	@$(CC) -c $(CFLAGS) $(INCLUDE) -o $@ $<

$(BUILD_PATH)/%.o: %.m
	@echo [$(CC)] $< "-->" $@
	@$(CC) -c $(CFLAGS) $(INCLUDE) -o $@ $<

clean:
	@echo [$(RM)] $(OUT_LIB) $(BUILD_PATH)
	@$(RM) $(OUT_LIB) $(BUILD_PATH)
