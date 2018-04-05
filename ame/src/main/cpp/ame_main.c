#include <stdio.h>

#ifdef STDC_HEADERS

# include <stdlib.h>
# include <string.h>

#else
# ifndef HAVE_STRCHR
#  define strchr index
#  define strrchr rindex
# endif

char *strchr(), *strrchr();

# ifndef HAVE_MEMCPY
#  define memcpy(d, s, n) bcopy ((s), (d), (n))
#  define memmove(d, s, n) bcopy ((s), (d), (n))
# endif
#endif

/*
 main.c is example code for how to use libmp3lame.a.  To use this library,
 you only need the library and lame.h.  All other .h files are private
 to the library.
*/
#include "lame.h"

#include "ame_main.h"
#include "frontend/console.h"

#ifdef WITH_DMALLOC
#include <dmalloc.h>
#endif

extern int lame_main(JNIEnv *env, lame_t gf, jstring input, jstring output, jobject options,
                     jobject callback);

/************************************************************************
*
* main
*
* PURPOSE:  MPEG-1,2 Layer III encoder with GPSYCHO
* psychoacoustic model.
*
************************************************************************/

FILE *lame_fopen(char const *file, char const *mode) {
    return fopen(file, mode);
}

char *lame_getenv(char const *var) {
    char *str = getenv(var);
    if (str) {
        return strdup(str);
    }
    return 0;
}

int ame_main(JNIEnv *env, jstring input, jstring output, jobject options,
             jobject callback) {

    lame_t gf;
    int ret;

    frontend_open_console();
    gf = lame_init(); /* initialize libmp3lame */
    if (NULL == gf) {
        error_printf("fatal error during initialization\n");
        ret = 1;
    } else {
        ret = lame_main(env, gf, input, output, options, callback);
        lame_close(gf);
    }
    frontend_close_console();

    return ret;
}