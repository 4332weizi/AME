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
    public @interface Emphasis {
    }

    public static final int EMPHASIS_N = 0;
    public static final int EMPHASIS_5 = 1;
    public static final int EMPHASIS_C = 5;

    private int emphasis = -1;
    private boolean markAsCopyright;
    private boolean markAsNonOriginal;
    private boolean errorProtectionEnabled = false;
    private boolean reservoirEnabled = true;
    private int bufferConstraint = MDB_DEFAULT;

    public Mp3HeaderStreamOptions emphasis(@Emphasis int emphasis) {
        this.emphasis = emphasis;
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
        this.errorProtectionEnabled = true;
        return this;
    }

    public Mp3HeaderStreamOptions disableErrorProtection() {
        this.errorProtectionEnabled = false;
        return this;
    }

    public Mp3HeaderStreamOptions disabledReservoir() {
        this.reservoirEnabled = false;
        return this;
    }

    public Mp3HeaderStreamOptions bufferConstraint(@BufferConstraint int bufferConstraint) {
        this.bufferConstraint = bufferConstraint;
        return this;
    }
}
