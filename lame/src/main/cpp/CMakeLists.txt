cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

aux_source_directory(${CMAKE_HOME_DIRECTORY} AME_SRC_LIST)

add_library( lame
			 SHARED
			 ${AME_SRC_LIST})

find_library( log-lib log )

target_link_libraries( lame ${log-lib} )