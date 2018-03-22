Android MP3 Encoder
=====================
添加依赖
-------
```groovy
dependencies {
  implementation 'io.auxo.ame:ame-lite:0.1'
}
```

AME-lite使用方法
-------

转码过程属于耗时操作，注意不要在UI线程中执行。
```java
Mp3Encoder.Options options = new Mp3Encoder.Options()
        .sampleRate(44000)
        .bitrate(120)
        .numChannels(2)
        .quality(3)
        .mode(Mp3Encoder.Options.STEREO);

Mp3Encoder.Callback callback = new Mp3Encoder.Callback() {
    @Override
    public void onStart() {             
    }

    @Override
    public void onProgress(int total, int current) {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError() {
    }
};

Mp3Encoder.encode(input, output, options, callback);
// Mp3Encoder.encode(input, output);
// Mp3Encoder.encode(input, output, options);
// Mp3Encoder.encode(input, output, callback);
```

License
-------

    Copyright (c) 2018 Victor Chiu

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.