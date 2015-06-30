/*
 * Copyright (c) 2015 Aleksei Polkanov. All rights reserved.
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.lifus.wro4j_runtime_taglib.model.resource.uri.root.strategy;

import static com.github.lifus.wro4j_runtime_taglib.model.resource.uri.root.strategy.PredefinedOptimizedResourceRootStrategy.VALUE_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.powermock.api.mockito.PowerMockito.when;

import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ro.isdc.wro.WroRuntimeException;

import com.github.lifus.wro4j_runtime_taglib.config.ConfigurationHelper;

/**
 * Tests for {@link PredefinedOptimizedResourceRootStrategy}.
 */
@PrepareForTest(ConfigurationHelper.class)
public class PredefinedOptimizedResourceRootStrategyTest extends PowerMockTestCase {

  private static String ROOT = "/wro/";

  private PredefinedOptimizedResourceRootStrategy predefinedOptimizedResourceRootStrategy;

  @Mock
  private ConfigurationHelper configurationHelper;

  @BeforeMethod
  public void setUp() {
    predefinedOptimizedResourceRootStrategy = new PredefinedOptimizedResourceRootStrategy(configurationHelper);
  }

  @Test
  public void shouldReturnValueFromWroConfiguration() {
    when(configurationHelper.containsKey(VALUE_KEY)).thenReturn(true);
    when(configurationHelper.getProperty(VALUE_KEY)).thenReturn(ROOT);

    assertThat(predefinedOptimizedResourceRootStrategy.getRoot(), is(ROOT));
  }

  @Test(expectedExceptions=WroRuntimeException.class)
  public void shouldThrowExceptionIfRootIsNotConfigured() {
    when(configurationHelper.containsKey(VALUE_KEY)).thenReturn(false);

    predefinedOptimizedResourceRootStrategy.getRoot();
  }

}
