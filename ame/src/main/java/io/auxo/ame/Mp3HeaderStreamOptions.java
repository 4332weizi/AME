package io.auxo.ame;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * MP3 header/stream options:\n"
 * -e <emp>        de-emphasis n/5/c  (obsolete)
 * -c              mark as copyright
 * -o              mark as non-original
 * -p              error protection.  adds 16 bit checksum to every frame
 * .               (the checksum is computed correctly)
 * --nores         disable the bit reservoir
 * --strictly-enforce-ISO   comply as much as possible to ISO MPEG spec);
 * --buffer-constraint <constraint> available values for constraint:
 * .               default, strict, maximum
 *
 * @author Victor Chiu
 */
public class Mp3HeaderStreamOptions {

    @IntDef({MDB_DEFAULT, MDB_STRICT_ISO, MDB_MAXIMUM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferConstraint {
    }

    public static final int MDB_DEFAULT = 0;
    public static final int MDB_STRICT_ISO = 1;
    public static final int MDB_MAXIMUM = 2;

    @IntDef({EMPHASIS_N, EMPHASIS_5, EMPHASIS_C})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DeEmphasis {
    }

    public static final int EMPHASIS_N = 0;
    public static final int EMPHASIS_5 = 1;
    public static final int EMPHASIS_C = 5;

    /**
     * 去加重
     */
    private int deEmphasis = -1;
    /**
     * 标记为版权
     */
    private boolean markAsCopyright;
    /**
     * 标记为非原创
     */
    private boolean markAsNonOriginal;
    /**
     * 错误检测
     */
    private boolean errorProtection = false;
    /**
     * 缓冲区
     */
    private boolean bitReservoir = true;
    /**
     * 严格遵守ISO MPEG规范
     */
    private boolean strictlyEnforceISO = false;
    /**
     * 缓冲约束
     */
    private int bufferConstraint = MDB_DEFAULT;

    public Mp3HeaderStreamOptions deEmphasis(@DeEmphasis int deEmphasis) {
        this.deEmphasis = deEmphasis;
        return this;
    }

    public Mp3HeaderStreamOptions markAsCopyright() {
        this.markAsCopyright = true;
        return this;
    }

    public Mp3HeaderStreamOptions markAsNonOriginal() {
        this.markAsNonOriginal = true;
        return this;
    }

    public Mp3HeaderStreamOptions enableErrorProtection() {
        this.errorProtection = true;
        return this;
    }

    public Mp3HeaderStreamOptions disableErrorProtection() {
        this.errorProtection = false;
        return this;
    }

    public Mp3HeaderStreamOptions disabledBitReservoir() {
        this.bitReservoir = false;
        return this;
    }

    public Mp3HeaderStreamOptions strictlyEnforceISO(){
        this.strictlyEnforceISO = true;
        return this;
    }

    public Mp3HeaderStreamOptions bufferConstraint(@BufferConstraint int bufferConstraint) {
        this.bufferConstraint = bufferConstraint;
        return this;
    }
}
