/*
 * Copyright (c) 2014 Aleksey Polkanov. All rights reserved.
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package by.wro.runtime.taglib.model.resource.uri.root;


/**
 * Base class for {@link OptimizedResourcesRootProvider} with a single root. 
 */
public abstract class AbstractOptimizedResourcesRootProvider implements OptimizedResourcesRootProvider {

  private String url;

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRoot() {
    if (url == null) { // no synchronization intended
      url = findOutRoot();
    }
    return url;
  }

  /**
   * Finds out root URL of WroFilter.
   *
   * Idempotent method.
   *
   * @return root of WroFilter.
   */
  protected abstract String findOutRoot();

}
