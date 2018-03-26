原文参考：https://svn.code.sf.net/p/lame/svn/trunk/lame/doc/html/detailed.html  

#Input options
By default, LAME accepts a PCM audio sample inside a .WAV container as the input file, in 8, 16, 24 and 32 bits integer and in IEEE FLOAT. If it is compiled with libsndfile, then it also supports the extra formats that the library supports.
There is also support for RAW PCM data and and piped input  

 选项 | 描述 
 - | - 
- |  | For piped/streamed Input, use "-" as the input and/or output file name.
--out-dir path | If no explicit output file is specified, a file will be written at given path. Ignored when using piped/streamed input
--mp1input1 | Input file is an MPEG layer I file. Implicit if extension is .mp1
--mp2input1 | Input file is an MPEG layer II file. Implicit if extension is .mp2
--mp3input1 | Input file is an MPEG layer III file. Implicit if extension is .mp3
--scale number | Scale input (multiply PCM data) by number.
--scale-l number | Scale channel 0 (left) input (multiply PCM data ) by number
--scale-r number | Scale channel 1 (right) input (multiply PCM data) by number
--gain number | apply Gain adjustment in decibels, range -20.0 to +12.0.
--swap-channel | Swap Left and Right channels
--nogap file1 file2 ... | Gapless encoding for a set of contiguous files
--nogapout dir | Output dir for gapless encoding (must precede --nogap)
--nogaptags | Allow the use of VBR tags in gapless encoding

#Input options for RAW PCM  

 选项 | 描述 
 - | - 
-r | Assume input file is raw PCM.
-s number | Input sampling frequency in kHz (Default is 44.1Khz)
--signed / --unsigned | Input is signed (default) or unsigned
--bitwidth w | Input bit width is w (default 16)
-x | Force byte-swapping of input stream
--little-endian / --big-endian | Input is little-endian (default) or big-endian
-a | Downmix from stereo to mono file for mono encoding. Needed with RAW input for the -mm mode to do the downmix.

#Constant Bit Rate (CBR)

 选项 | 描述 
 - | - 
-b number | Set the bitrate in kbps, default 128 kbps
--cbr | Enforce use of constant bitrate
--comp ratio | Choose bitrate to obtain the specified compression ratio
--freeformat | Produce a free format bitstream instead of the standard one. Supported bitrates between 8 and 640 kbps. Scarce support by decoders.

#Variable Bit Rate (ABR)

 选项 | 描述 
 - | - 
--abr number | Specify average bitrate desired (instead of quality)

#Variable Bit Rate (VBR)

 选项 | 描述 
 - | - 
-V number | Quality setting for VBR. default number=4.<br>Decimal values can be specified, like: 4.51<br>0=highest quality, bigger files. 9.999=lowest quality, smaller files
--vbr-old | Use old variable bitrate (VBR) routine
-b number | Specify a minimum allowed bitrate. default 32 kbps (See -F setting below)
-B number | Specify a maximum allowed bitrate. default 320 kbps
-F | Enforce -b option for digital silence frames too, rather than encoding them at 8 or 32 kbps.
-t | Disable VBR informational tag
-T | Enable and force writing LAME Tag
-Y | Allows -V2, -V1 and -V0 to not to encode the highest frequencies accurately, if doing so causes disproportional increases in bitrate. This is the same that CBR and ABR modes do.

#Operational options

 选项 | 描述 
 - | - 
--preset type | Enables some preconfigured settings. Check below for each of the valid values
--priority number | Sets the process priority:<br>0,1 = Low priority <br>2 = normal priority (default)<br>3,4 = High priority
--flush | Flush output stream as soon as possible
--decode | Input=mp3 file, output=wav
--decode-mp3delay samples | Set the encoder delay to use to decode the input .mp3 file
-t | Disable writing wav header when using --decode
--noasm type | Disable assembly optimizations for mmx/3dnow/sse

#Noise shaping & psycho acoustic algorithms

 选项 | 描述 
 - | - 
-q number | number = 0...9. Default -q 3<br>-q 0: Highest quality, very slow<br>-q 9: Poor quality, but fast<br>Please, see notes in the detailed description
-m mode | joint, simple, force, dual-mono, mono, left, right. default is j.<br>joint = joins the best possible of MS and LR stereo<br>simple = force LR stereo on all frames<br>force = force MS stereo on all frames.

#Filter and resampling options:

 选项 | 描述 
 - | - 
--resample sfreq | Sampling frequency of output file(kHz)- default=automatic
--lowpass number | Frequency(kHz), lowpass filter cutoff above freq. Range [0.001..50]kHz or [50..50000]Hz
--lowpass-width number | Frequency(kHz), lowpass window width. Range [0.001..16]kHz or [16..50000]Hz - (See further restriction in the detailed explanation)
--highpass number | Frequency(kHz), highpass filter cutoff below freq. Range [0.001..16]kHz or [16..50000]Hz (See further restriction in the detailed explanation)
--highpass-width number | Frequency(kHz), highpass window width - (See further restriction in the detailed explanation)

#ID3 tag Information

 选项 | 描述 
 - | - 
--tt title | Audio/song title (max 30 chars for version 1 tag)
--ta artist | Audio/song artist (max 30 chars for version 1 tag)
--tl album | Audio/song album (max 30 chars for version 1 tag)
--ty year | Audio/song year of issue (1 to 9999)
--tc comment | User-defined text (max 30 chars for v1 tag, 28 for v1.1)
--tn track[/total] | Audio/song track number and (optionally) the total number of tracks on the original recording.<br>(track and total each 1 to 255. just the track number creates v1.1 tag, providing a total forces v2.0).
--tg genre | Audio/song genre (name or number in list)
--ti file | Audio/song albumArt (jpeg/png/gif file, 128KB max, v2.3)
--tv id=value | Text or URL frame specified by id and value (v2.3 tag). User defined frame. Syntax: --tv "TXXX=description=content"
--add-id3v2 | Force addition of version 2 tag
--id3v1-only | Add only a version 1 tag
--id3v2-only | Add only a version 2 tag
--id3v2-latin1 | Add following options in ISO-8859-1 text encoding
--id3v2-utf16 | Add following options in unicode text encoding
--space-id3v1 | Pad version 1 tag with spaces instead of nulls
--pad-id3v2-size n | Adds ID3v2 tag with n padding bytes
--genre-list | Print alphabetically sorted ID3 genre list and exit
--ignore-tag-errors | Ignore errors in values passed for tags<br>Note: A version 2 tag will NOT be added unless one of the input fields won't fit in a version 1 tag (e.g. the title string is longer than 30 characters), or the '--add-id3v2' or '--id3v2-only' options are used, or output is redirected to stdout.

#MP3 header/stream options:

 选项 | 描述 
 - | - 
-e mode | De-emphasis n/5/c (obsolete)
-c | Mark as copyright
-o | Mark as non-original
-p | Error detection. adds 16 bit checksum to every frame (the checksum is computed correctly)
--strictly-enforce-ISO | Comply as much as possible to ISO MPEG spec
--replaygain-fast | Compute RG fast but slightly inaccurately (default)
--replaygain-accurate1 | Compute RG more accurately and find the peak sample
--noreplaygain | Disable ReplayGain analysis
--clipdetect1 | Enable --replaygain-accurate and print a message whether clipping occurs and how far the waveform is from full scale

#Verbosity:

 选项 | 描述 
 - | - 
--disptime secs | Print progress report every secs seconds
--nohist | Disable VBR histogram display
--silent / --quiet | Don't print anything on screen
--verbose | Print a lot of useful information
--version / --license | Print License information
--help / --usage | Shows the common list of switches. Add id3 or dev to get help for a specified topic
--longhelp | Shows the complete list of switches<br>Experimental switches for developers. Only enabled in alpha/debug versions. Can change from version to version
--nores | Disables the bit reservour. Each frame will become independent from previous ones, but the quality will be lower.
--buffer-constraint x | available values for x: default, strict, maximum
--noath | turns ATH down to a flat noise floor
--athshort | ignore GPSYCHO for short blocks, use ATH only
--athonly | ignore GPSYCHO completely, use ATH only
--athtype x | selects between different ATH types [0-4]
--athlower x | Lower the ATH by x.x dB's
--athaa-type n | ATH auto adjust: 0 'no' else 'loudness based'
--athaa-sensitivity x | activation offset in -/+ dB for ATH auto-adjustment
--short | Allow the use of short blocs (default)
--noshort | Disable use of short blocks
--allshort | Use only short blocks
--shortthreshold x[,y] | short block switching threshold, x for L/R/M channel, y for S channel
--temporal-masking x | x=0 disables, x=1 enables temporal masking effect
--notemp | Alias of --temporal-masking 0
--nssafejoint | M/S switching criterion
--nsmsfix x | M/S switching tuning [effective 0-3.5]
--ns-bass x | Adjust masking for sfbs 0 - 6 (long) 0 - 5 (short)
--ns-alto x | Adjust masking for sfbs 7 - 13 (long) 6 - 10 (short)
--ns-treble x | Adjust masking for sfbs 14 - 21 (long) 11 - 12 (short)
--ns-sfb21 x | Change ns-treble by x dB for sfb21
-Z [n] | always do calculate short block maskings
--substep | use pseudo substep noise shaping method types 0-2
-X n[,m] | selects between different noise measurements n for long block, m for short. if m is omitted, m = n
--vbr-new | In LAME 3.98, the new VBR was made default, and this switch may be used for experimental tweaks.

#Aliases and removed settings

 选项 | 描述 
 - | - 
-f | Alias of -q 7.
-h | Alias of -q 2.
-v | Alias of -V 4
-S | Alias of --silent
--alt-preset | Alias of --preset
--r3mix | Alias of -V 3
--vbr-mtrh | Alias of LAME 3.98 default vbr mode
--pad-id3v2 | Alias of --pad-id3v2-size 128
-d | No longer supported
-k | No longer supported
--cwlimit | No longer supported
--ogginput | No longer supported. Compile with libsndfile instead
--brief | Currently unused
--interch x | Currently unused
--nspsytune | Currently unused