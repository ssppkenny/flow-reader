APP_SHORT_COMMANDS := true
#APP_ABI := arm64-v8a armeabi-v7a x86 armeabi mips mips64 x86_64
APP_ABI = armeabi armeabi-v7a x86_64
APP_STL := c++_static
APP_CPPFLAGS := -std=c++11 -fopenmp
APP_CFLAGS = -fopenmp
APP_LDFLAGS = -fopenmp
NDK_ALL_ABIS=armeabi armeabi-v7a x86_64
APP_OPTIM := release
#APP_STL := c++_shared
#NDK_TOOLCHAIN_VERSION := 4.9
