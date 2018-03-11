cmake_minimum_required(VERSION 3.4.1)

set( DIST_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../../../../dist )

# 添加ame-lite源文件
aux_source_directory( ${CMAKE_HOME_DIRECTORY} AME_LITE_SRC_LIST )

add_library( ame-lite
			 SHARED
			 ${AME_LITE_SRC_LIST} )

# 添加mp3lame动态链接库
add_library( mp3lame SHARED IMPORTED )
set_target_properties( mp3lame PROPERTIES IMPORTED_LOCATION
                       ${DIST_DIR}/mp3lame/lib/${ANDROID_ABI}/libmp3lame.so )

# 添加mp3lame中的头文件
target_include_directories( ame-lite
                            PRIVATE
                            ${DIST_DIR}/mp3lame/include )

find_library( log-lib log )

target_link_libraries( ame-lite
                       mp3lame
                       ${log-lib} )

# 设定动态链接库输出路径
set_target_properties( ame-lite
                       PROPERTIES
                       LIBRARY_OUTPUT_DIRECTORY
                       "${DIST_DIR}/ame-lite/lib/${ANDROID_ABI}" )