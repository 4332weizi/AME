cmake_minimum_required(VERSION 3.4.1)

set( DIST_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../../../../dist )

# 添加mp3lame源文件
aux_source_directory( ${CMAKE_HOME_DIRECTORY} LAME_SRC_LIST )
aux_source_directory( ${CMAKE_HOME_DIRECTORY}/vector LAME_VECTOR_SRC_LIST )

add_library( mp3lame
			 SHARED
			 ${LAME_SRC_LIST}
			 ${LAME_VECTOR_SRC_LIST} )

# 设定动态链接库输出路径
set_target_properties( mp3lame
                       PROPERTIES
                       LIBRARY_OUTPUT_DIRECTORY
                       "${DIST_DIR}/mp3lame/lib/${ANDROID_ABI}" )

# 拷贝头文件到分发路径
add_custom_command(TARGET mp3lame POST_BUILD
                                  COMMAND "${CMAKE_COMMAND}" -E
                                  copy "${CMAKE_CURRENT_SOURCE_DIR}/lame.h"
                                  "${DIST_DIR}/mp3lame/include/lame.h"
                                  COMMENT "Copying lame to output directory" )