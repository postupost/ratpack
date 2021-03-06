/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.server.internal

import ratpack.handling.Context
import ratpack.server.BindAddress
import spock.lang.Specification
import spock.lang.Unroll

class DefaultPublicAddressSpec extends Specification {

  @Unroll
  def "Get URL #publicURL, #scheme, #bindHost:#bindPort -> #expected"() {
    given:
    def port2 = bindPort
    def host2 = bindHost
    BindAddress bindAddress = new BindAddress() {
      int getPort() {
        return port2
      }

      String getHost() {
        return host2
      }
    }
    def context = Mock(Context) {
      getBindAddress() >> bindAddress
    }
    def url = publicURL ? new URI(publicURL) : null

    DefaultPublicAddress publicAddress = new DefaultPublicAddress(url, scheme)
    expect:
    publicAddress.getAddress(context).toString() == expected

    where:
    publicURL             | scheme  | bindHost    | bindPort || expected
    null                  | "http"  | "localhost" | 80       || "http://localhost:80"
    null                  | "https" | "localhost" | 80       || "https://localhost:80"
    "http://example.com"  | "http"  | "localhost" | 80       || "http://example.com"
    "https://example.com" | "https" | "localhost" | 80       || "https://example.com"

  }


}
