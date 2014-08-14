/*
 * Copyright (c) 2014 Aleksey Polkanov. All rights reserved.
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package by.wro.runtime.taglib.manager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.WroManager.Builder;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.util.ObjectFactory;
import by.wro.runtime.taglib.model.group.name.GroupNameExtractorDecorator;
import by.wro.runtime.taglib.model.group.name.cache.GroupNameCacheInitializer;
import by.wro.runtime.taglib.model.resource.uri.cache.ResourceUriCacheInitializer;

/**
 * Tests for {@link WroTaglibManagerFactoryDecorator}.
 */
@PrepareForTest(WroManager.class)
public class WroTaglibManagerFactoryDecoratorTest extends PowerMockTestCase {

  private WroTaglibManagerFactoryDecorator decorator;

  @Mock
  private WroManagerFactory wroManagerFactory;
  @Mock
  private WroManager wroManager;
  @Mock
  private Builder builder;

  @BeforeMethod
  public void setUp() throws Exception {
    decorator = new WroTaglibManagerFactoryDecorator(wroManagerFactory, mock(GroupNameCacheInitializer.class), mock(ResourceUriCacheInitializer.class), mock(InjectorInitializer.class));
  }

  @Test
  public void shouldDecorateGroupExtractor() {
    givenGroupExtractorHasBeenSetUp();

    whenBuildingManager();

    thenShouldDecorateGroupExtractor();
  }

  @Test
  public void shouldRegisterResourceChangedCallbackFactory() {
    givenGroupExtractorHasBeenSetUp();
    
    whenBuildingManager();
    
    thenShouldRegisterCallback();
  }

  private void givenGroupExtractorHasBeenSetUp() {
    when(wroManagerFactory.create()).thenReturn(wroManager);
    when(wroManager.getGroupExtractor()).thenReturn(mock(GroupExtractor.class));
  }

  private void whenBuildingManager() {
    decorator.onBeforeBuild(builder);
  }

  private void thenShouldDecorateGroupExtractor() {
    verify(builder).setGroupExtractor(any(GroupNameExtractorDecorator.class));
  }

  @SuppressWarnings("unchecked")
  private void thenShouldRegisterCallback() {
    verify(wroManager).registerCallback(any(ObjectFactory.class));
  }

}
