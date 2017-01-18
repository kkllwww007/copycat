/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package io.atomix.copycat.protocol.websocket.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.atomix.copycat.error.CopycatError;
import io.atomix.copycat.protocol.response.QueryResponse;

/**
 * Web socket query response.
 *
 * @author <a href="http://github.com/kuujo>Jordan Halterman</a>
 */
public class WebSocketQueryResponse extends QueryResponse implements WebSocketResponse {
  private final long id;

  @JsonCreator
  public WebSocketQueryResponse(
    @JsonProperty("id") long id,
    @JsonProperty("status") Status status,
    @JsonProperty("error") CopycatError error,
    @JsonProperty("index") long index,
    @JsonProperty("eventIndex") long eventIndex,
    @JsonProperty("result") Object result) {
    super(status, error, index, eventIndex, result);
    this.id = id;
  }

  @Override
  @JsonGetter("id")
  public long id() {
    return id;
  }

  @Override
  @JsonGetter("type")
  public Type type() {
    return Types.QUERY_RESPONSE;
  }

  @Override
  @JsonGetter("status")
  public Status status() {
    return super.status();
  }

  @Override
  @JsonGetter("error")
  public CopycatError error() {
    return super.error();
  }

  @Override
  @JsonGetter("index")
  public long index() {
    return super.index();
  }

  @Override
  @JsonGetter("eventIndex")
  public long eventIndex() {
    return super.eventIndex();
  }

  @Override
  @JsonGetter("result")
  public Object result() {
    return super.result();
  }

  /**
   * Web socket query response builder.
   */
  public static class Builder extends QueryResponse.Builder {
    private final long id;

    public Builder(long id) {
      this.id = id;
    }

    @Override
    public QueryResponse build() {
      return new WebSocketQueryResponse(id, status, error, index, eventIndex, result);
    }
  }
}