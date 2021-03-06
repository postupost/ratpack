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

package ratpack.groovy.guice;

import com.google.inject.Module;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import ratpack.guice.ModuleRegistry;

/**
 * Groovy specific extensions to {@link ratpack.guice.ModuleRegistry}
 */
public interface GroovyModuleRegistry extends ModuleRegistry {

  <T extends Module> void config(@DelegatesTo.Target Class<T> moduleType, @DelegatesTo(genericTypeIndex = 0, strategy = Closure.DELEGATE_FIRST) Closure<?> closure);

  void init(@DelegatesTo(value = Void.class, strategy = Closure.OWNER_ONLY) Closure<?> closure);

  <T> void init(@DelegatesTo.Target Class<T> clazz, @DelegatesTo(genericTypeIndex = 0, strategy = Closure.DELEGATE_FIRST) Closure<?> closure);

}
