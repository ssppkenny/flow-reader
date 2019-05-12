LOCAL_PATH := $(call my-dir)

CVROOT := $(LOCAL_PATH)/../../../opencv/sdk/native/jni

include $(CLEAR_VARS)

OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=STATIC
include $(CVROOT)/OpenCV.mk


LOCAL_MODULE := native-lib


LOCAL_C_INCLUDES := \
		$(LOCAL_PATH)/../djvu/djvulibre \
		$(LOCAL_PATH)/../libjpeg-turbo \
		$(LOCAL_PATH)/.. \
		$(LOCAL_PATH)/../lz4 \
		$(LOCAL_PATH)/../boost/include \
		$(CVROOT)/include


LOCAL_STATIC_LIBRARIES := djvu libjpeg liblz4  libopencv_imgproc libopencv_imgcodecs libopencv_core flann cpufeatures libboost_system libboost_graph


LOCAL_CFLAGS += -DHAVE_CONFIG_H -std=c++11 -frtti -fexceptions -fopenmp -w -O2 -DNDEBUG
LOCAL_LDLIBS += -llog -lstdc++ -lz -L$(SYSROOT)/usr/lib
LOCAL_LDFLAGS += -ldl -landroid -fopenmp

LOCAL_C_INCLUDES += ImageLoader.h



LOCAL_SRC_FILES := \
	native-lib.cpp


include $(BUILD_SHARED_LIBRARY)


