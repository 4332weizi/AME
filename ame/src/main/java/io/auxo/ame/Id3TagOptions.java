package io.auxo.ame;

import java.util.HashMap;
import java.util.Map;

/**
 * ID3 tag options:
 * --tt <title>    audio/song title (max 30 chars for version 1 tag)
 * --ta <artist>   audio/song artist (max 30 chars for version 1 tag)
 * --tl <album>    audio/song album (max 30 chars for version 1 tag)
 * --ty <year>     audio/song year of issue (1 to 9999)
 * --tc <comment>  user-defined text (max 30 chars for v1 tag, 28 for v1.1)
 * --tn <track[/total]>   audio/song track number and (optionally) the total
 * number of tracks on the original recording. (track
 * and total each 1 to 255. just the track number
 * creates v1.1 tag, providing a total forces v2.0).
 * --tg <genre>    audio/song genre (name or number in list)
 * --ti <file>     audio/song albumArt (jpeg/png/gif file, v2.3 tag)
 * --tv <id=value> user-defined frame specified by id and value (v2.3 tag)
 * --add-id3v2     force addition of version 2 tag
 * --id3v1-only    add only a version 1 tag
 * --id3v2-only    add only a version 2 tag
 * --id3v2-utf16   add following options in unicode text encoding
 * --id3v2-latin1  add following options in latin-1 text encoding
 * --space-id3v1   pad version 1 tag with spaces instead of nulls
 * --pad-id3v2     same as '--pad-id3v2-size 128'
 * --pad-id3v2-size <value> adds version 2 tag, pad with extra <value> bytes
 * --genre-list    print alphabetically sorted ID3 genre list and exit
 * --ignore-tag-errors  ignore errors in values passed for tags
 * <p>
 * Note: A version 2 tag will NOT be added unless one of the input fields
 * won't fit in a version 1 tag (e.g. the title string is longer than 30
 * characters), or the '--add-id3v2' or '--id3v2-only' options are used,
 * or output is redirected to stdout.
 *
 * @author Victor Chiu
 */
public class Id3TagOptions {

    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String artist;
    /**
     * 专集
     */
    private String album;
    /**
     * 出品年代
     */
    private String year;
    /**
     * 备注
     */
    private String comment;
    /**
     * 类型
     * 一系列<a href="https://4332weizi.github.io/lame-mirror/doc/html/detailed.html#genre-list">艺术类型清单</a>
     * 中的一个
     */
    private String genre;
    /**
     * 类型
     * 一系列中的<a href="https://4332weizi.github.io/lame-mirror/doc/html/detailed.html#genre-list">艺术类型清单</a>
     * 中的一个编号
     */
    private int genreId;
    /**
     * 曲目
     */
    private int track;
    /**
     * 曲目
     */
    private int totalTrack;
    /**
     * 封面
     */
    private String albumArt;
    /**
     * 由id和value（v2.3）定义的文本或URL帧。
     * 用户自定义帧。
     */
    private Map<String, String> userDefinedFrames;
    private boolean forceAddId3v2;
    private boolean addId3v1Only;
    private boolean addId3v2Only;
    private boolean useUtf16;
    private boolean useLation1;
    private boolean space;
    private int pad = -1;
    private int padv2;
    /**
     * 忽略传入的标签值中的错误
     */
    private boolean ignoreErrors = false;

    public Id3TagOptions() {
        userDefinedFrames = new HashMap<>();
    }
}
