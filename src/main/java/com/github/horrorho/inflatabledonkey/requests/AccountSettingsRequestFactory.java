/* 
 * The MIT License
 *
 * Copyright 2015 Ahseya.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.horrorho.inflatabledonkey.requests;

import java.util.Objects;
import java.util.function.BiFunction;
import net.jcip.annotations.Immutable;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Authentication NSDictionary HttpUriRequest factory.
 *
 * @author Ahseya
 */
@Immutable
public final class AccountSettingsRequestFactory implements BiFunction<String, String, HttpUriRequest> {

    private final String url;
    private final Headers headers;

    public AccountSettingsRequestFactory(String url, Headers headers) {
        this.url = Objects.requireNonNull(url);
        this.headers = Objects.requireNonNull(headers);
    }

    public AccountSettingsRequestFactory(Headers headers) {
        this("https://setup.icloud.com/setup/get_account_settings", headers);
    }

    @Override
    public HttpUriRequest apply(String dsPrsID, String mmeAuthToken) {
        String authorization = Headers.basicToken(dsPrsID, mmeAuthToken);

        HttpPost request = new HttpPost(url);
        request.setHeader(headers.get(Headers.userAgent));
        request.setHeader(headers.get(Headers.xMmeClientInfo));
        request.setHeader("Authorization", authorization);

        return request;
    }
}
