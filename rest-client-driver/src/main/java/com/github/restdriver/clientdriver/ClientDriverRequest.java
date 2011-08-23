/**
 * Copyright © 2010-2011 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.restdriver.clientdriver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Class for encapsulating an HTTP request.
 */
public final class ClientDriverRequest {

    /**
     * HTTP method enum for specifying which method you expect to be called with.
     */
    public enum Method {
        GET, POST, PUT, DELETE, OPTIONS, HEAD
    }

    private final Object path;
    private final Multimap<String, Object> params;
    private final Map<String, Object> headers;

    private Method method;
    private Object bodyContent;
    private Object bodyContentType;

    /**
     * Constructor taking String.
     * 
     * @param path
     *            The mandatory argument is the path which will be listened on
     */
    public ClientDriverRequest(String path) {
        this.path = path;
        method = Method.GET;
        params = HashMultimap.create();
        headers = new HashMap<String, Object>();
    }

    /**
     * Constructor taking Pattern.
     * 
     * @param path
     *            The mandatory argument is the path which will be listened on
     */
    public ClientDriverRequest(Pattern path) {
        this.path = path;
        method = Method.GET;
        params = HashMultimap.create();
        headers = new HashMap<String, Object>();
    }

    /**
     * Get the path.
     * 
     * @return the path which requests are expected on.
     */
    public Object getPath() {
        return path;
    }

    /**
     * @param withMethod
     *            the method to set
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withMethod(Method withMethod) {
        this.method = withMethod;
        return this;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Setter for expecting query-string parameters on the end of the url.
     * 
     * @param key
     *            The key from ?key=value
     * @param value
     *            The value from ?key=value in the form of a String
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    /**
     * Setter for expecting query-string parameters on the end of the url.
     * 
     * @param key
     *            The key from ?key=value
     * @param value
     *            The value from ?key=value in the form of a Pattern
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withParam(String key, Pattern value) {
        params.put(key, value);
        return this;
    }

    /**
     * @return the params
     */
    public Map<String, Collection<Object>> getParams() {
        return params.asMap();
    }

    /**
     * toString.
     * 
     * @return a String representation of the request
     */
    @Override
    public String toString() {
        return "ClientDriverRequest: " + method + " " + path.toString() + "; ";
    }

    /**
     * @return the bodyContent
     */
    public Object getBodyContent() {
        return bodyContent;
    }

    /**
     * @return the bodyContentType
     */
    public Object getBodyContentType() {
        return bodyContentType;
    }

    /**
     * Setter for expecting body content and type, where content is in the form of a String and type is in the form of a
     * String.
     * 
     * @param withBodyContent
     *            the bodyContent to set
     * @param withContentType
     *            eg "text/plain"
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withBody(String withBodyContent, String withContentType) {
        bodyContent = withBodyContent;
        bodyContentType = withContentType;
        return this;
    }

    /**
     * Setter for expecting body content and type, where content is in the form of a String and type is in the form of a
     * Pattern.
     * 
     * @param withBodyContent
     *            the bodyContent to set
     * @param contentType
     *            eg "text/plain"
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withBody(String withBodyContent, Pattern contentType) {
        bodyContent = withBodyContent;
        bodyContentType = contentType;
        return this;
    }

    /**
     * Setter for expecting body content and type, where content is in the form of a Pattern and type is in the form of
     * a String.
     * 
     * @param withBodyContent
     *            the bodyContent to set
     * @param contentType
     *            eg "text/plain"
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withBody(Pattern withBodyContent, String contentType) {
        bodyContent = withBodyContent;
        bodyContentType = contentType;
        return this;
    }

    /**
     * Setter for expecting body content and type, where content is in the form of a Pattern and type is in the form of
     * a Pattern.
     * 
     * @param withBodyContent
     *            the bodyContent to set
     * @param contentType
     *            eg "text/plain"
     * @return the object you called the method on, so you can chain these calls.
     */
    public ClientDriverRequest withBody(Pattern withBodyContent, Pattern contentType) {
        this.bodyContent = withBodyContent;
        bodyContentType = contentType;
        return this;
    }

    /**
     * Setter for expecting a specific header name and value pair.
     * 
     * @param withHeaderName
     *            the headerName to match on
     * @param withHeaderValue
     *            the headerValue to match on
     * @return the object you called the method on, so you can chain these calls
     */
    public ClientDriverRequest withHeader(String withHeaderName, String withHeaderValue) {
        headers.put(withHeaderName, withHeaderValue);
        return this;
    }

    /**
     * Setter for expecting a specific header name and value pair, where value is in the form of a Pattern.
     * 
     * @param withHeaderName
     *            the headerName to match on
     * @param withHeaderValue
     *            the headerValue to match on
     * @return the object you called the method on, so you can chain these calls
     */
    public ClientDriverRequest withHeader(String withHeaderName, Pattern withHeaderValue) {
        headers.put(withHeaderName, withHeaderValue);
        return this;
    }

    /**
     * @return the headers
     * @return
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

}
